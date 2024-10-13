package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class RefuelOrderEntity {

    private Long orderId;

    // 加油总额  默认：0.00
    private BigDecimal orderAmount;

    // 购买单价  默认：0.00
    private BigDecimal orderCash;

    // 产品id
    private Integer proid;

    // 数量
    private Integer num;

    // 加油卡类型 （1:中石化、2:中石油；默认为1)  默认：1
    private String chargeType;

    // 渠道类型 （1:聚合、2:欧飞；默认为1)  默认：1
    private String providerType;

    // 充值卡号
    private String cardBn;

    // 持卡人手机号
    private String cardTel;

    // 持卡人
    private String cardHolder;

    // 流水号  默认：0
    private String sporderId;

    // 会员名称
    private Integer memberId;

    // 交易编号
    private String cardTradeBn;

    // 储油卡卡号
    private String ycyCardBn;

    // 充值状态:0充值中 1成功 2失败 9撤销  默认：0
    private String fuelStatus;

    // 无效 Y:是 N:否  默认：N
    private String disabled;

    // 是否再次尝试:Y:是 N:否  默认：N
    private String retry;

    // 备注
    private String memo;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 充值时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;


    public String getCreateTime() {
        return createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderCash() {
        return orderCash;
    }

    public void setOrderCash(BigDecimal orderCash) {
        this.orderCash = orderCash;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getCardBn() {
        return cardBn;
    }

    public void setCardBn(String cardBn) {
        this.cardBn = cardBn;
    }

    public String getCardTel() {
        return cardTel;
    }

    public void setCardTel(String cardTel) {
        this.cardTel = cardTel;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getSporderId() {
        return sporderId;
    }

    public void setSporderId(String sporderId) {
        this.sporderId = sporderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCardTradeBn() {
        return cardTradeBn;
    }

    public void setCardTradeBn(String cardTradeBn) {
        this.cardTradeBn = cardTradeBn;
    }

    public String getYcyCardBn() {
        return ycyCardBn;
    }

    public void setYcyCardBn(String ycyCardBn) {
        this.ycyCardBn = ycyCardBn;
    }

    public String getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(String fuelStatus) {
        this.fuelStatus = fuelStatus;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getRetry() {
        return retry;
    }

    public void setRetry(String retry) {
        this.retry = retry;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}