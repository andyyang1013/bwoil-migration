package com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin;


public class OriginOperationCommandActivity {

    // 活动ID
    private String activityId;

    // 活动编号
    private String activityBn;

    // 活动名称
    private String activityName;

    // 活动说明
    private String activityDesc;

    // 领券口令
    private String activityWord;

    // 领取总量  默认：0
    private String activityTotalNum;

    // 已领取总量  默认：0
    private String activityJoinNum;

    // 赠送现金券
    private String activityCoupons;

    // 活动状态  默认：0
    private String activityStatus;

    // 开始时间
    private Long activityStartDate;

    // 结束时间
    private Long activityEndDate;

    // 创建人
    private String createName;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Long createTime;

    // 最后修改人
    private String modifyName;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Long lastmodify;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityBn() {
        return activityBn;
    }

    public void setActivityBn(String activityBn) {
        this.activityBn = activityBn;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivityWord() {
        return activityWord;
    }

    public void setActivityWord(String activityWord) {
        this.activityWord = activityWord;
    }

    public String getActivityTotalNum() {
        return activityTotalNum;
    }

    public void setActivityTotalNum(String activityTotalNum) {
        this.activityTotalNum = activityTotalNum;
    }

    public String getActivityJoinNum() {
        return activityJoinNum;
    }

    public void setActivityJoinNum(String activityJoinNum) {
        this.activityJoinNum = activityJoinNum;
    }

    public String getActivityCoupons() {
        return activityCoupons;
    }

    public void setActivityCoupons(String activityCoupons) {
        this.activityCoupons = activityCoupons;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Long getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(Long activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public Long getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(Long activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Long getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Long lastmodify) {
        this.lastmodify = lastmodify;
    }
}
