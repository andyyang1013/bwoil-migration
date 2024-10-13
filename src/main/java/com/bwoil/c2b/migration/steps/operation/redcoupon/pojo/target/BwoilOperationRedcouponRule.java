package com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.target;


import java.util.Date;

public class BwoilOperationRedcouponRule {

    private long ruleId;
    private long cpnId;
    private String ruleTypeBn;
    private String ruleDetail;
    private long rulePeriods;
    private String status;
    private Date createTime;
    private Date lastmodify;


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

}
