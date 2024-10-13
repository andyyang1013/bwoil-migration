package com.bwoil.c2b.migration.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.target")
public class TargetDataSourceProperties {
    /**
     * target datasource url
     */
    private String url;
    /**
     * target datasource username
     */
    private String username;
    /**
     * target datasource password
     */
    private String password;
    /**
     * target datasource driver class name
     */
    private String driverClassName;
    /**
     * target datasource max pool size
     */
    private int maximumPoolSize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }
}
