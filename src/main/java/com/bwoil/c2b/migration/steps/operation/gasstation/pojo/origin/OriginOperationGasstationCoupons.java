package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationGasstationCoupons {


    private Integer couponId;

    // 渠道ID  (已经停用, 不需要了)
    private Integer channelId;

    // 加油站ID
    private Integer stationId;

    // 优惠比例
    private BigDecimal couponRate;

    // 开始时间  默认：0
    private Integer startDate;

    // 结束时间  默认：0
    private Integer endDate;

    // 是否有效, false无效, 默认true有效,  (创建的时候都有效true, 优惠时间后就无效)
    private String isvalid;

    // 创建时间  默认：0
    private Integer createTime;

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

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public BigDecimal getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(BigDecimal couponRate) {
        this.couponRate = couponRate;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
}
