package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AssetDetailEntity {

    // 日志id
    private Long logId;

    // 用户id  默认：0
    private Integer memberId;

    // 操作员ID
    private Integer opId;

    // 出入金额  默认：0.00
    private BigDecimal money;

    // 管理备注
    private String message;

    // 支付单号
    private String paymentId;

    // 订单号
    private Long orderId;

    // 支付方式
    private String paymethod;

    // 交易编号
    private String tradeBn;

    // 卡号
    private Long cardBn;

    // 交易类型 
    private String tradeType;

    // 日志类型
    private String logType;

    // 存入金额  默认：0.00
    private BigDecimal importMoney;

    // 支出金额  默认：0.00
    private BigDecimal explodeMoney;

    // 可用金额  默认：0.00
    private BigDecimal validAdvance;

    // 当前余额  默认：0.00
    private BigDecimal memberAdvance;

    // 商店余额  默认：0.00
    private BigDecimal shopAdvance;

    // 是否是冻结金额(Y N)  默认：N
    private String isFreeze;

    // 失效(Y N)  默认：N
    private String disabled;

    // 账户类型  默认：finance
    private String accountType;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    // 业务摘要
    private String memo;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getTradeBn() {
        return tradeBn;
    }

    public void setTradeBn(String tradeBn) {
        this.tradeBn = tradeBn;
    }

    public Long getCardBn() {
        return cardBn;
    }

    public void setCardBn(Long cardBn) {
        this.cardBn = cardBn;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public BigDecimal getImportMoney() {
        return importMoney;
    }

    public void setImportMoney(BigDecimal importMoney) {
        this.importMoney = importMoney;
    }

    public BigDecimal getExplodeMoney() {
        return explodeMoney;
    }

    public void setExplodeMoney(BigDecimal explodeMoney) {
        this.explodeMoney = explodeMoney;
    }

    public BigDecimal getValidAdvance() {
        return validAdvance;
    }

    public void setValidAdvance(BigDecimal validAdvance) {
        this.validAdvance = validAdvance;
    }

    public BigDecimal getMemberAdvance() {
        return memberAdvance;
    }

    public void setMemberAdvance(BigDecimal memberAdvance) {
        this.memberAdvance = memberAdvance;
    }

    public BigDecimal getShopAdvance() {
        return shopAdvance;
    }

    public void setShopAdvance(BigDecimal shopAdvance) {
        this.shopAdvance = shopAdvance;
    }

    public String getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(String isFreeze) {
        this.isFreeze = isFreeze;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}