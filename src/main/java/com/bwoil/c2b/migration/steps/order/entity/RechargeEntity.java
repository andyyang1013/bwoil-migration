package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeEntity {

    private String id;
    // 会员用户名
    private Integer memberId;

    // 会员手机号
    private String mobile;

    // 会员编号
    private String memberBn;

    // 会员名称
    private String memberName;

    // 交易编号
    private String tradeBn;

    // 支付单号
    private String payBn;

    // 到帐时间  默认：0000-00-00 00:00:00
    private String toAcctTime;

    // 支付方式  默认：0
    private String payWay;

    // 收款渠道  默认：0
    private String payChannel;

    // 支付单号
    private String payNo;

    // 第三方订单号
    private String orderReferNo;

    // 充值金额  默认：0.00
    private BigDecimal rechargeAmount;

    // 充值到帐后余额  默认：0.00
    private BigDecimal accountRemain;

    // 状态: 0:支付中 1: 已到账 2：支付失败  默认：0
    private String rechargeStatus;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    private String logId;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberBn() {
        return memberBn;
    }

    public void setMemberBn(String memberBn) {
        this.memberBn = memberBn;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getTradeBn() {
        return tradeBn;
    }

    public void setTradeBn(String tradeBn) {
        this.tradeBn = tradeBn;
    }

    public String getPayBn() {
        return payBn;
    }

    public void setPayBn(String payBn) {
        this.payBn = payBn;
    }

    public String getToAcctTime() {
        return toAcctTime;
    }

    public void setToAcctTime(String toAcctTime) {
        this.toAcctTime = toAcctTime;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOrderReferNo() {
        return orderReferNo;
    }

    public void setOrderReferNo(String orderReferNo) {
        this.orderReferNo = orderReferNo;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getAccountRemain() {
        return accountRemain;
    }

    public void setAccountRemain(BigDecimal accountRemain) {
        this.accountRemain = accountRemain;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}