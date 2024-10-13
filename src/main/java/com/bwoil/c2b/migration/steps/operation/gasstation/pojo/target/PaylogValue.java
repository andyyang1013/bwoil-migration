package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;

import java.math.BigDecimal;

public class PaylogValue {

    //stationBn'=>$result['station_bn'],'orderNo'=>$order_id,'memberNo'=>$memInfo['shop_member_bn'],'amount'=>$valid['total_amount']
    private String stationBn;
    private String orderNo;
    private String memberNo;
    private BigDecimal amount; //打款金额
    private BigDecimal orderAmount; //金额

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStationBn() {
        return stationBn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStationBn(String stationBn) {
        this.stationBn = stationBn;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }


}