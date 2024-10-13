package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;


public class OriginOperationGasstationRefuelCouponRule {

    private Integer ruleId;
    // 加油站id
    private Integer stationId;

    // 加油站名称
    private String stationName;

    // 优惠券列表
    private String couponList;

    // 可用起始时间
    private Integer startTime;

    // 可用结束时间
    private Integer endTime;

    // 维护时间
    private Integer modifyTime;

    // 维护人姓名
    private String modifyUsername;

    // 创建时间
    private Integer createTime;

    // 状态, 默认audit待审核, invalid失效, valid启用
    private String status;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCouponList() {
        return couponList;
    }

    public void setCouponList(String couponList) {
        this.couponList = couponList;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUsername() {
        return modifyUsername;
    }

    public void setModifyUsername(String modifyUsername) {
        this.modifyUsername = modifyUsername;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
