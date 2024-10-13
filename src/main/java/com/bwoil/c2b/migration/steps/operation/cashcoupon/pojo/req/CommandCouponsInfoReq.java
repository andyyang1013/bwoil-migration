package com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.req;

public class CommandCouponsInfoReq {

    private Integer couponActivityId;

    private Integer num;

    public Integer getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(Integer couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
