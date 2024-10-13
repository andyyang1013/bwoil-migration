package com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.origin;

public class OriginOperationRedcouponRule {

    private long ruleId;
    private long cpnId;
    private String ruleTypeBn;
    private String ruleDetail;
    private long rulePeriods;

    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }


    public long getCpnId() {
        return cpnId;
    }

    public void setCpnId(long cpnId) {
        this.cpnId = cpnId;
    }


    public String getRuleTypeBn() {
        return ruleTypeBn;
    }

    public void setRuleTypeBn(String ruleTypeBn) {
        this.ruleTypeBn = ruleTypeBn;
    }


    public String getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String ruleDetail) {
        this.ruleDetail = ruleDetail;
    }


    public long getRulePeriods() {
        return rulePeriods;
    }

    public void setRulePeriods(long rulePeriods) {
        this.rulePeriods = rulePeriods;
    }


}
