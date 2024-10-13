package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CashFlowEntity {

    // 日志id
    private Long logId;

    // 用户id  默认：0
    private Integer memberId;

    // 操作员ID
    private Integer opId;

    // 出入金额  默认：0.000
    private BigDecimal money;

    // 管理备注
    private String message;

    // 支付单号
    private String paymentId;

    // 订单号
    private String orderId;

    // 支付方式
    private String paymethod;

    // 业务摘要
    private String memo;

    // 交易编号
    private String tradeBn;

    // 卡号
    private String cardBn;

    // 交易类型:cash,buyback,transfer,fuel,recharge
    private String tradeType;

    // 日志类型
    private String logType;

    // 存入金额  默认：0.00
    private BigDecimal importMoney;

    // 支出金额  默认：0.00
    private BigDecimal explodeMoney;

    // 当前余额  默认：0.00
    private BigDecimal memberAdvance;

    // 添加之前余额  默认：0.00
    private BigDecimal validAdvance;

    // 手续费  默认：0.00
    private BigDecimal feeAmt;

    // 费率  默认：0.00
    private BigDecimal feeRate;

    // 是否是冻结金额:Y N  默认：N
    private String isFreeze;

    // 失效:Y N  默认：N
    private String disabled;

    // 账户类型finance,recharge,red_envelope  默认：finance
    private String accountType;

    // 交易时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    private String mtime;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTradeBn() {
        return tradeBn;
    }

    public void setTradeBn(String tradeBn) {
        this.tradeBn = tradeBn;
    }

    public String getCardBn() {
        return cardBn;
    }

    public void setCardBn(String cardBn) {
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

    public BigDecimal getMemberAdvance() {
        return memberAdvance;
    }

    public void setMemberAdvance(BigDecimal memberAdvance) {
        this.memberAdvance = memberAdvance;
    }

    public BigDecimal getValidAdvance() {
        return validAdvance;
    }

    public void setValidAdvance(BigDecimal validAdvance) {
        this.validAdvance = validAdvance;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }


}