package com.bwoil.c2b.migration.core;

import com.bwoil.c2b.migration.core.config.MigrationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MigrationExecutor {

    private static final Logger logger = LoggerFactory.getLogger(MigrationExecutor.class);

    private final JobLauncher jobLauncher;

    private final List<Job> migrationJobs;

    private final MigrationProperties migrationProperties;

    @Autowired
    public MigrationExecutor(JobLauncher jobLauncher, List<Job> migrationJobs, MigrationProperties migrationProperties) {
        this.jobLauncher = jobLauncher;
        this.migrationJobs = migrationJobs;
        this.migrationProperties = migrationProperties;
    }

    public void exe() {
        if (migrationProperties.getLaunchJob()) {
            launchJobs();
        }
    }

    /**
     * 启动迁移作业
     */
    private void launchJobs() {
        migrationJobs.forEach(job -> {
            try {
                logger.info("=================启动【{}】=================", job.getName());
                jobLauncher.run(job, new JobParameters(Collections.singletonMap("time", new JobParameter(System.currentTimeMillis()))));
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                e.printStackTrace();
            }
        });
    }

}
