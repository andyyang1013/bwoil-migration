package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;


public class BwoilOperationPromotionConfig {

    private long configId;
    private long channelId;
    private double promotionRate;
    private long duration;
    private long revisedDuration;
    private String isvalid;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp lastmodify;


    public long getConfigId() {
        return configId;
    }

    public void setConfigId(long configId) {
        this.configId = configId;
    }


    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }


    public double getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(double promotionRate) {
        this.promotionRate = promotionRate;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public long getRevisedDuration() {
        return revisedDuration;
    }

    public void setRevisedDuration(long revisedDuration) {
        this.revisedDuration = revisedDuration;
    }


    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public java.sql.Timestamp getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(java.sql.Timestamp lastmodify) {
        this.lastmodify = lastmodify;
    }

}
