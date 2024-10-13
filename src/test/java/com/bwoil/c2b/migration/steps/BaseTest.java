package com.bwoil.c2b.migration.steps;

import com.bwoil.c2b.migration.core.listener.JobCompletionNotificationListener;
import com.bwoil.c2b.migration.core.util.CacheUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected ApplicationContext applicationContext;

    @Test
    public void test() {
        Step step = null;
        Map<String, Step> stepMap = applicationContext.getBeansOfType(Step.class);
        for (Step s : stepMap.values()) {
            if (s.getName().equals(getStepName())) {
                step = s;
            }
        }

        Assert.assertNotNull("没有找到name为[]" + getStepName() + "的step", step);

        Job job = jobBuilderFactory.get(getStepName())
                .incrementer(new RunIdIncrementer())
                .listener(new JobCompletionNotificationListener())
                .start(step)
                .build();
        try {
            jobLauncher.run(job, new JobParameters(Collections.singletonMap("time", new JobParameter(System.currentTimeMillis()))));
            logger.info(CacheUtil.getStepReaderSql(getStepName()));
            logger.info(CacheUtil.getStepWriterSqlTemplate(getStepName()));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    public abstract String getStepName();
}
