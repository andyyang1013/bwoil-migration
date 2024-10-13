package com.bwoil.c2b.migration.core.report;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Record {
    private String serviceName;
    private String targetTable;
    private String targetTableComment;
    private String originTable;
    private String selectSql;
    private String insertSql;
    private int readSuccessCount;
    private int writeSuccessCount;
    private int readFailCount;
    private int processFailCount;
    private int writeFailCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS")
    private Date endTime;
    private long duration;
    private long speed;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public String getTargetTableComment() {
        return targetTableComment;
    }

    public void setTargetTableComment(String targetTableComment) {
        this.targetTableComment = targetTableComment;
    }

    public String getOriginTable() {
        return originTable;
    }

    public void setOriginTable(String originTable) {
        this.originTable = originTable;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }

    public String getInsertSql() {
        return insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }

    public int getReadSuccessCount() {
        return readSuccessCount;
    }

    public void setReadSuccessCount(int readSuccessCount) {
        this.readSuccessCount = readSuccessCount;
    }

    public int getWriteSuccessCount() {
        return writeSuccessCount;
    }

    public void setWriteSuccessCount(int writeSuccessCount) {
        this.writeSuccessCount = writeSuccessCount;
    }

    public int getReadFailCount() {
        return readFailCount;
    }

    public void setReadFailCount(int readFailCount) {
        this.readFailCount = readFailCount;
    }

    public int getProcessFailCount() {
        return processFailCount;
    }

    public void setProcessFailCount(int processFailCount) {
        this.processFailCount = processFailCount;
    }

    public int getWriteFailCount() {
        return writeFailCount;
    }

    public void setWriteFailCount(int writeFailCount) {
        this.writeFailCount = writeFailCount;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }
}
