package com.bwoil.c2b.migration.core.monitor;

import com.bwoil.c2b.migration.core.util.CacheUtil;
import org.springframework.batch.core.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MigrationMonitor implements Runnable {

    private ApplicationContext context;

    private final List<Job> migrationJobs;

    private boolean flag = true;

    public MigrationMonitor(ApplicationContext context, List<Job> migrationJobs) {
        this.context = context;
        this.migrationJobs = migrationJobs;
    }

    @Override
    public void run() {
        if (migrationJobs == null || migrationJobs.size() == 0) {
            return;
        }
        while (true) {
            if (allJobsComplete()) {
                SpringApplication.exit(context);
                break;
            }
        }
    }

    private boolean allJobsComplete() {
        migrationJobs.forEach(job -> {
            Boolean complete = CacheUtil.getCompleteJob(job.getName());
            if (complete == null) {
                flag = false;
            }
        });
        return flag;
    }
}
