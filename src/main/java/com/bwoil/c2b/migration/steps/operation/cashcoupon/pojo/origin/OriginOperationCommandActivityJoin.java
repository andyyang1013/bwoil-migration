package com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin;

public class OriginOperationCommandActivityJoin {

    // 参与ID
    private String joinId;

    // 参与活动ID
    private String activityId;

    // 会员账号  默认：0
    private String memberId;

    // 赠送优惠券编号
    private String giveCouponIds;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Long createTime;

    public String getJoinId() {
        return joinId;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGiveCouponIds() {
        return giveCouponIds;
    }

    public void setGiveCouponIds(String giveCouponIds) {
        this.giveCouponIds = giveCouponIds;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
