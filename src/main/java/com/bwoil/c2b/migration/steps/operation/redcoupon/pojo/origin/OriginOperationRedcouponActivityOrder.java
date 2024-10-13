package com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationRedcouponActivityOrder {

    private long id;
    // 活动编号
    private String cpnBn;
    //活动名字
    private String cpnName;
    // 订单号  默认：0
    private String orderId;

    //会员id
    private Integer memberId;

    // 手机号
    private String loginAccount;

    // 下单时间  默认：CURRENT_TIMESTAMP
    private Long orderCreateTime;

    // 商品名字
    private String goodName;

    // 订单总金额  默认：0.00
    private BigDecimal totalAmount;

    // 现金券减免  默认：0.00
    private BigDecimal cpnsMoney;

    // 油箱抵用金额  默认：0.00
    private BigDecimal goilMoney;

    // 满减金额  默认：0.00
    private BigDecimal reduceMoney;

    // 商品本身打折金额  默认：0.00
    private BigDecimal discountMoney;

    public String getCpnBn() {
        return cpnBn;
    }

    public void setCpnBn(String cpnBn) {
        this.cpnBn = cpnBn;
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }


    public Long getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Long orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    public BigDecimal getCpnsMoney() {
        return cpnsMoney;
    }

    public void setCpnsMoney(BigDecimal cpnsMoney) {
        this.cpnsMoney = cpnsMoney;
    }

    public BigDecimal getGoilMoney() {
        return goilMoney;
    }

    public void setGoilMoney(BigDecimal goilMoney) {
        this.goilMoney = goilMoney;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }


    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
