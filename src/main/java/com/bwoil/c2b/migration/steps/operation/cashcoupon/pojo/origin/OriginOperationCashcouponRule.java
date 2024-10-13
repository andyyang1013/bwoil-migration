package com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationCashcouponRule {

    private Integer ruleId;

    private Integer cpnId;

    private String ruleTypeBn;

    private BigDecimal ruleOrderMoney;

    private Integer rulePeriods;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getCpnId() {
        return cpnId;
    }

    public void setCpnId(Integer cpnId) {
        this.cpnId = cpnId;
    }

    public String getRuleTypeBn() {
        return ruleTypeBn;
    }

    public void setRuleTypeBn(String ruleTypeBn) {
        this.ruleTypeBn = ruleTypeBn;
    }

    public BigDecimal getRuleOrderMoney() {
        return ruleOrderMoney;
    }

    public void setRuleOrderMoney(BigDecimal ruleOrderMoney) {
        this.ruleOrderMoney = ruleOrderMoney;
    }

    public Integer getRulePeriods() {
        return rulePeriods;
    }

    public void setRulePeriods(Integer rulePeriods) {
        this.rulePeriods = rulePeriods;
    }
}
