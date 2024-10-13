package com.bwoil.c2b.migration.core.config;

import com.bwoil.c2b.migration.core.MigrationJob;
import com.bwoil.c2b.migration.core.MigrationStep;
import com.bwoil.c2b.migration.core.listener.JobCompletionNotificationListener;
import com.bwoil.c2b.migration.core.util.CacheUtil;
import com.bwoil.c2b.migration.core.util.SqlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.*;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(MigrationProperties.class)
@EnableBatchProcessing
public class MigrationConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MigrationConfiguration.class);

    private final JobBuilderFactory jobBuilderFactory;

    private final ApplicationContext applicationContext;

    private final DataSource originDataSource;

    private final DataSource targetDataSource;

    private final MigrationProperties migrationProperties;

    public MigrationConfiguration(JobBuilderFactory jobBuilderFactory, ApplicationContext applicationContext, @Qualifier("originDataSource") DataSource originDataSource, @Qualifier("targetDataSource") DataSource targetDataSource, MigrationProperties migrationProperties) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.applicationContext = applicationContext;
        this.originDataSource = originDataSource;
        this.targetDataSource = targetDataSource;
        this.migrationProperties = migrationProperties;
    }

    @Bean
    public List<Job> migrationJobs() throws JsonProcessingException {
        return initJobs();
    }

    private List<Job> initJobs() throws JsonProcessingException {
        logger.info(new ObjectMapper().writeValueAsString(migrationProperties));
        if (StringUtils.isBlank(migrationProperties.getJobConfigPath())) {
            throw new RuntimeException("没有找到作业文件，请检查指定的参数--migration.jobConfigPath");
        }
        List<MigrationJob> migrationJobs = loadJobConfig();
        printJobs(migrationJobs);
        if (migrationProperties.getValidate()) {
            if (!validateJobConfig(migrationJobs)) {
                throw new RuntimeException("作业校验失败，请检查日志并修复");
            }
        }
        return translate(migrationJobs);
    }

    private void printJobs(List<MigrationJob> migrationJobs) {
        migrationJobs.forEach(migrationJob -> {
            try {
                logger.info(new ObjectMapper().writeValueAsString(migrationJob));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean validateJobConfig(List<MigrationJob> migrationJobs) {
        final boolean[] result = {true};
        migrationJobs.forEach(migrationJob -> {
            String jobName = migrationJob.getJobName();
            logger.info("=================开始校验【{}】=================", jobName);
            List<MigrationStep> steps = migrationJob.getSteps();
            steps.forEach(s -> {
                Step step = (Step) applicationContext.getBean(s.getStep());
                String stepName = step.getName();
                logger.info(stepName);
                String stepReaderSql = CacheUtil.getStepReaderSql(stepName);
                String stepWriterSqlTemplate = CacheUtil.getStepWriterSqlTemplate(stepName);
                boolean valid = validateData(stepWriterSqlTemplate);
                if (valid) {
                    logger.info("{} OK", s.getStep());
                } else {
                    logger.error("{} ERROR", s.getStep());
                    result[0] = false;
                }
                logger.info("==================================");
            });
        });
        return result[0];
    }

    private boolean validateData(String stepWriterSqlTemplate) {
        int count;
        String rSql = "select count(0) from " + SqlUtil.getTable(stepWriterSqlTemplate);
        ResultSet resultSet;
        try (Connection connection = targetDataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(rSql)) {
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            return false;
        }
        return count == 0;
    }

    private boolean validateSql(String stepReaderSql, String stepWriterSqlTemplate) {
        logger.info(stepReaderSql);
        logger.info(stepWriterSqlTemplate);
        try {
            int count = countData(stepReaderSql, stepWriterSqlTemplate);
            logger.info("load data {} rows", count);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int countData(String stepReaderSql, String stepWriterSqlTemplate) throws SQLException {
        int count;
        String rSql = "select count(0) from (" + stepReaderSql + ") mmm";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = originDataSource.getConnection();
            preparedStatement = connection.prepareStatement(rSql);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            connection = targetDataSource.getConnection();
            preparedStatement = connection.prepareStatement(rSql);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return count;
    }

    private List<Job> translate(List<MigrationJob> migrationJobs) {
        List<Job> list = new ArrayList<>();
        migrationJobs.forEach(migrationJob -> {
            String jobName = migrationJob.getJobName();
            String jobType = migrationJob.getJobType();
            List<MigrationStep> steps = migrationJob.getSteps();
            switch (jobType) {
                case "simple":
                    list.add(simpleStepJob(jobName, getStep(steps.get(0))));
                    break;
                case "sequential":
                    list.add(sequentialStepsJob(jobName, getStep(steps)));
                    break;
                case "parallel":
                    list.add(parallelStepsJob(jobName, getStep(steps)));
                default:
                    break;
            }
        });
        return list;
    }

    private List<Step> getStep(List<MigrationStep> steps) {
        List<Step> list = new ArrayList<>();
        steps.forEach(migrationStep -> {
            Step bean = (Step) applicationContext.getBean(migrationStep.getStep());
            list.add(bean);
        });
        return list;
    }

    private Step getStep(MigrationStep migrationStep) {
        return (Step) applicationContext.getBean(migrationStep.getStep());
    }

    private List<MigrationJob> loadJobConfig() {
        List<MigrationJob> list = new ArrayList<>();
        List<File> files = (List<File>) FileUtils.listFiles(new File(migrationProperties.getJobConfigPath()), TrueFileFilter.INSTANCE, null);
        files.forEach(file -> {
            try {
                list.add(new ObjectMapper().readValue(file, MigrationJob.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    /**
     * 单步骤数据迁移作业
     *
     * @param jobName 作业名称
     * @param step    数据迁移步骤
     * @return 作业实例
     */
    private Job simpleStepJob(String jobName, Step step) {
        return jobBuilderFactory.get(jobName).listener(new JobCompletionNotificationListener()).start(step).build();
    }

    /**
     * 顺序执行步骤的数据迁移作业
     *
     * @param jobName  作业名称
     * @param stepList 数据迁移的有序步骤列表
     * @return 作业实例
     */
    private Job sequentialStepsJob(String jobName, List<Step> stepList) {
        JobBuilder jobBuilder = jobBuilderFactory.get(jobName).listener(new JobCompletionNotificationListener());
        SimpleJobBuilder simpleJobBuilder = jobBuilder.start(stepList.get(0));
        for (int i = 1; i < stepList.size(); i++) {
            simpleJobBuilder = simpleJobBuilder.next(stepList.get(i));
        }
        return simpleJobBuilder.build();
    }

    /**
     * 并行执行步骤的数据迁移作业
     *
     * @param jobName  作业名称
     * @param stepList 数据迁移的步骤列表
     * @return 作业实例
     */
    private Job parallelStepsJob(String jobName, List<Step> stepList) {
        TaskExecutor executor = new SimpleAsyncTaskExecutor(jobName);
        JobBuilder jobBuilder = jobBuilderFactory.get(jobName).listener(new JobCompletionNotificationListener());
        JobFlowBuilder jobFlowBuilder = jobBuilder.start(new FlowBuilder<SimpleFlow>(stepList.get(0).getName()).start(stepList.get(0)).build());
        FlowBuilder<FlowJobBuilder> flowBuilder = null;
        for (int i = 1; i < stepList.size(); i++) {
            Flow flow = new FlowBuilder<SimpleFlow>(stepList.get(i).getName()).start(stepList.get(i)).build();
            flowBuilder = jobFlowBuilder.split(executor).add(flow);
        }
        return flowBuilder == null ? null : flowBuilder.end().build();
    }
}
