package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OilCardCycleEntity {

    // 卡编号
    private String cardBn;

    // 会员ID
    private Integer memberId;

    // 发票报销/扫码加油限额(元)  默认：0.00
    private BigDecimal cycleAmount;

    // 发票报销/扫码加油频率(天)
    private Short cycleDay;

    // 本期剩余兑付升数/金额  默认：0.00
    private BigDecimal cycleCashRemain;

    // 上次兑付时间  默认：0000-00-00 00:00:00
    private Date lastCashTime;

    // 上次兑付时间  默认：0000-00-00 00:00:00
    private Integer cashTime;

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

    public BigDecimal getCycleAmount() {
        return cycleAmount;
    }

    public void setCycleAmount(BigDecimal cycleAmount) {
        this.cycleAmount = cycleAmount;
    }

    public Short getCycleDay() {
        return cycleDay;
    }

    public void setCycleDay(Short cycleDay) {
        this.cycleDay = cycleDay;
    }

    public BigDecimal getCycleCashRemain() {
        return cycleCashRemain;
    }

    public void setCycleCashRemain(BigDecimal cycleCashRemain) {
        this.cycleCashRemain = cycleCashRemain;
    }

    public Date getLastCashTime() {
        return lastCashTime;
    }

    public void setLastCashTime(Date lastCashTime) {
        this.lastCashTime = lastCashTime;
    }

    public Integer getCashTime() {
        return cashTime;
    }

    public void setCashTime(Integer cashTime) {
        this.cashTime = cashTime;
    }

}