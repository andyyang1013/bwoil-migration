package com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationRedcouponActivityOrder {

    private long id;
    private String cpnBn;
    private String cpnName;
    private String orderId;
    private String mobile;
    private Date orderCreateTime;
    private String goodName;
    private BigDecimal totalAmount;
    private BigDecimal realPayAmount;
    private BigDecimal cpnsMoney;
    private BigDecimal goilMoney;
    private BigDecimal reduceMoney;
    private BigDecimal discountMoney;
    private String status;
    private Date createTime;
    private Date lastmodify;
    //满减订单状态
    private Integer orderStatus;
    //会员id
    private Integer memberId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


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


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
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


    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Date lastmodify) {
        this.lastmodify = lastmodify;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
