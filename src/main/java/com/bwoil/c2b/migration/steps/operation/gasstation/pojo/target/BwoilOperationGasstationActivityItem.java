package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.util.Date;

public class BwoilOperationGasstationActivityItem {

    private Integer id;

    // 活动ID
    private Integer activityId;

    // 折扣形式, 值1升数折扣, 2金额优惠折扣  默认：1
    private Integer discountId;

    // 油品ID
    private Integer oilId;

    // 输入的数据  如果是折扣形式值是1这就是数字, 如果是2就是json: {满多钱100: 折扣8}
    private String data;

    // 创建时间
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
