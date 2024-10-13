package com.bwoil.c2b.migration.steps.operation.promotion.pojo.tempClass;

import java.util.List;

/**
 * @author wumr
 * @Description: TODO
 * @date 2019/3/5 15:08
 */
public class ChannelCouponReq {

    private String isOpen;

    private List<RegisterCouponReq> couponArray;

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public List<RegisterCouponReq> getCouponArray() {
        return couponArray;
    }

    public void setCouponArray(List<RegisterCouponReq> couponArray) {
        this.couponArray = couponArray;
    }
}
