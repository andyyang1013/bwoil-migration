package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.util.Date;

public class BwoilOperationGasstationActivity {

    private Integer id;
    // 活动名称
    private String activityName;

    // 加油站ID
    private Integer stationId;

    // 分销渠道ID, json信息 {"channel1": id1, "channel2": id2}
    private String channelId;

    // 维护人姓名
    private String modifyName;

    // 创建人姓名
    private String createName;

    // 状态,  0无效, 默认2待审核, 1生效, 3审核不通过
    private String status;

    // 维护时间  默认：CURRENT_TIMESTAMP
    private Date modifyTime;

    // 创建时间  默认：0000-00-00 00:00:00
    private Date createTime;

    // 生效时间  默认：0000-00-00 00:00:00
    private Date startDate;

    // 结束时间  默认：0000-00-00 00:00:00
    private Date endDate;

    // 开始时间
    private Date startHour;

    // 结束时间
    private Date endHour;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }
}
