package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

public class OriginOperationGasstationActivity {

    private Integer activityId;
    // 活动名称
    private String activityName;

    // 加油站ID
    private Integer stationId;

    // 分销渠道ID, json信息 {"channel1": id1, "channel2": id2}
    private String channelId;

    // 维护人姓名
    private String modifyName;

    // 创建人姓名
    private String createdName;

    // 状态,  0无效, 默认2待审核, 1生效, 3审核不通过
    private String status;

    // 维护时间  默认：CURRENT_TIMESTAMP
    private Integer modifyTime;

    // 创建时间  默认：0000-00-00 00:00:00
    private Integer createdTime;

    // 生效时间  默认：0000-00-00 00:00:00
    private Integer startDate;

    // 结束时间  默认：0000-00-00 00:00:00
    private Integer endDate;

    // 开始时间
    private String startHour;

    // 结束时间
    private String endHour;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
}
