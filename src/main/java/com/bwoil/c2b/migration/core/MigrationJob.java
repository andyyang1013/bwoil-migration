package com.bwoil.c2b.migration.core;

import java.util.List;

public class MigrationJob {
    private String jobName;
    private String jobType;
    private List<MigrationStep> steps;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public List<MigrationStep> getSteps() {
        return steps;
    }

    public void setSteps(List<MigrationStep> steps) {
        this.steps = steps;
    }
}
