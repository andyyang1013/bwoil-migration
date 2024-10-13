package com.bwoil.c2b.migration.core.listener;

import com.bwoil.c2b.migration.core.report.Record;
import com.bwoil.c2b.migration.core.report.ReportHandler;
import com.bwoil.c2b.migration.core.util.CacheUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import java.io.IOException;
import java.util.List;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private long start;

    @Override
    public void afterJob(JobExecution jobExecution) {
        long end = System.currentTimeMillis();
        try {
            List<Record> records = ReportHandler.addRecords(jobExecution);
            log.info("[{} report] : {}", jobExecution.getJobInstance().getJobName() ,new ObjectMapper().writeValueAsString(records));
        } catch (IOException e) {
            log.error("保存报表出错", e);
        }
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB [{}] FINISHED!(EXECUTE TIME {}ms) Time to verify the results", jobExecution.getJobInstance().getJobName(), end - start);
            CacheUtil.cacheCompleteJob(jobExecution.getJobInstance().getJobName(),true);
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        start = System.currentTimeMillis();
    }
}
