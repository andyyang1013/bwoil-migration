package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class RedeemPlanEntity {

    private String cashTimeId;
    // 卡编号
    private String cardBn;

    // 会员ID
    private Integer memberId;

    // 兑付日期
    private String saleDate;

    // 期别
    private Integer term;

    // 兑付额度升数/金额  默认：0.00
    private BigDecimal cashAmount;

    // 本期剩余兑付升数/金额  默认：0.00
    private BigDecimal cashRemain;

    // 本期累计兑付升数/金额  默认：0.00
    private BigDecimal cashTotal;

    // 是否强制兑付:Y:是 N:否
    private String forceSale;

    // 已兑付:Y:是 N:否
    private String saleFlag;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    public String getCashTimeId() {
        return cashTimeId;
    }

    public void setCashTimeId(String cashTimeId) {
        this.cashTimeId = cashTimeId;
    }

    public String getCardBn() {
        return cardBn;
    }

    public void setCardBn(String cardBn) {
        this.cardBn = cardBn;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getCashRemain() {
        return cashRemain;
    }

    public void setCashRemain(BigDecimal cashRemain) {
        this.cashRemain = cashRemain;
    }

    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    public String getForceSale() {
        return forceSale;
    }

    public void setForceSale(String forceSale) {
        this.forceSale = forceSale;
    }

    public String getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag;
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