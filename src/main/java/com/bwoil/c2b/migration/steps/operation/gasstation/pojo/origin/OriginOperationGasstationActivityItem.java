package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

public class OriginOperationGasstationActivityItem {

    private Integer oilActivityId;

    // 活动ID
    private Integer activityId;

    // 折扣形式, 值1升数折扣, 2金额优惠折扣  默认：1
    private Integer discountId;

    // 油品ID
    private Integer oilId;

    // 输入的数据  如果是折扣形式值是1这就是数字, 如果是2就是json: {满多钱100: 折扣8}
    private String data;

    // 创建时间
    private Integer createdTime;

    public Integer getOilActivityId() {
        return oilActivityId;
    }

    public void setOilActivityId(Integer oilActivityId) {
        this.oilActivityId = oilActivityId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }
}
