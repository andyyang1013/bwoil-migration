package com.bwoil.c2b.migration.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "migration")
public class MigrationProperties {
    private boolean launchJob = true;
    private String jobConfigPath = "jobs";
    private boolean validate = false;

    public boolean getLaunchJob() {
        return launchJob;
    }

    public void setLaunchJob(boolean launchJob) {
        this.launchJob = launchJob;
    }

    public String getJobConfigPath() {
        return jobConfigPath;
    }

    public void setJobConfigPath(String jobConfigPath) {
        this.jobConfigPath = jobConfigPath;
    }

    public boolean getValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
