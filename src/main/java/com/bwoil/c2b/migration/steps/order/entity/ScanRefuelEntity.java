package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class ScanRefuelEntity {

    private String fuelOrderId;

    // ID
    private String memberId;

    // 手机号
    private String mobile;

    // 会员编号
    private String memberBn;

    // 订单号
    private String orderId;

    // 打款编号
    private String payBn;

    // 加油总金额  默认：0
    private BigDecimal fuelAmount;

    // 实收金额  默认：0
    private BigDecimal realPayAmount;

    // 协议金额  默认：0
    private BigDecimal protocolAmount;

    // 优惠金额  默认：0
    private BigDecimal profitAmount;

    // 结算手续费  默认：0
    private BigDecimal settleFeeAmount;

    // 支付加油卡金额  默认：0
    private BigDecimal payCardAmount;

    // 支付预存款金额  默认：0
    private BigDecimal payLcAmount;

    // 活动优惠金额  默认：0
    private BigDecimal profitAcitiveAmount;

    // 结算金额  默认：0
    private BigDecimal settleAmount;

    // 银行转账状态
    private String payStatus;

    // 过期时间  默认：0000-00-00 00:00:00
    private String expireTime;

    // 验证时间  默认：0000-00-00 00:00:00
    private String validTime;

    // 加油站id
    private String refuelStationId;

    // 加油站名称
    private String refuelStation;

    // 油品名称
    private String oilName;

    // 油枪号
    private String oilGanNo;

    // 结算方式
    private String settleWay;

    // 微信折扣  默认：0
    private BigDecimal wechatRate;

    // 渠道折扣  默认：0
    private BigDecimal channelRate;

    // 收银员名称
    private String waiter;

    // 油站挂牌价  默认：0
    private BigDecimal oilPrice;

    // 加油升数  默认：0
    private BigDecimal refuleLiter;

    // 验证码
    private String vcode;

    // 加油类型
    private String refuelType;

    // 订单是否成功
    private String isSucess;

    // 支付方式
    private String payWay;

    // 订单来源
    private String adSource;

    // 订单状态
    private String orderStatus;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    public String getFuelOrderId() {
        return fuelOrderId;
    }

    public void setFuelOrderId(String fuelOrderId) {
        this.fuelOrderId = fuelOrderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayBn() {
        return payBn;
    }

    public void setPayBn(String payBn) {
        this.payBn = payBn;
    }

    public BigDecimal getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(BigDecimal fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public BigDecimal getProtocolAmount() {
        return protocolAmount;
    }

    public void setProtocolAmount(BigDecimal protocolAmount) {
        this.protocolAmount = protocolAmount;
    }

    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    public BigDecimal getSettleFeeAmount() {
        return settleFeeAmount;
    }

    public void setSettleFeeAmount(BigDecimal settleFeeAmount) {
        this.settleFeeAmount = settleFeeAmount;
    }

    public BigDecimal getPayCardAmount() {
        return payCardAmount;
    }

    public void setPayCardAmount(BigDecimal payCardAmount) {
        this.payCardAmount = payCardAmount;
    }

    public BigDecimal getPayLcAmount() {
        return payLcAmount;
    }

    public void setPayLcAmount(BigDecimal payLcAmount) {
        this.payLcAmount = payLcAmount;
    }

    public BigDecimal getProfitAcitiveAmount() {
        return profitAcitiveAmount;
    }

    public void setProfitAcitiveAmount(BigDecimal profitAcitiveAmount) {
        this.profitAcitiveAmount = profitAcitiveAmount;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getRefuelStationId() {
        return refuelStationId;
    }

    public void setRefuelStationId(String refuelStationId) {
        this.refuelStationId = refuelStationId;
    }

    public String getRefuelStation() {
        return refuelStation;
    }

    public void setRefuelStation(String refuelStation) {
        this.refuelStation = refuelStation;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public String getOilGanNo() {
        return oilGanNo;
    }

    public void setOilGanNo(String oilGanNo) {
        this.oilGanNo = oilGanNo;
    }

    public String getSettleWay() {
        return settleWay;
    }

    public void setSettleWay(String settleWay) {
        this.settleWay = settleWay;
    }

    public BigDecimal getWechatRate() {
        return wechatRate;
    }

    public void setWechatRate(BigDecimal wechatRate) {
        this.wechatRate = wechatRate;
    }

    public BigDecimal getChannelRate() {
        return channelRate;
    }

    public void setChannelRate(BigDecimal channelRate) {
        this.channelRate = channelRate;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public BigDecimal getRefuleLiter() {
        return refuleLiter;
    }

    public void setRefuleLiter(BigDecimal refuleLiter) {
        this.refuleLiter = refuleLiter;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getRefuelType() {
        return refuelType;
    }

    public void setRefuelType(String refuelType) {
        this.refuelType = refuelType;
    }

    public String getIsSucess() {
        return isSucess;
    }

    public void setIsSucess(String isSucess) {
        this.isSucess = isSucess;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getAdSource() {
        return adSource;
    }

    public void setAdSource(String adSource) {
        this.adSource = adSource;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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