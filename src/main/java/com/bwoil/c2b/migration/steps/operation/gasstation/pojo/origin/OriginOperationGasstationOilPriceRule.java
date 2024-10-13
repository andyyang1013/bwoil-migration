package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.util.Date;

public class OriginOperationGasstationOilPriceRule {

    private Integer goilPriceId;

    private Integer operatorTime;

    private Integer operatorId;

    private String operatorName;

    private Integer auditId;

    private String auditName;

    private Integer auditTime;

    private Date goilDate;

    public Date getGoilDate() {
        return goilDate;
    }

    public void setGoilDate(Date goilDate) {
        this.goilDate = goilDate;
    }

    private String goilSalePrices;

    private String status;

    private Integer lastmodify;

    private Integer companyId;

    public Integer getGoilPriceId() {
        return goilPriceId;
    }

    public void setGoilPriceId(Integer goilPriceId) {
        this.goilPriceId = goilPriceId;
    }

    public Integer getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Integer operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Integer auditTime) {
        this.auditTime = auditTime;
    }


    public String getGoilSalePrices() {
        return goilSalePrices;
    }

    public void setGoilSalePrices(String goilSalePrices) {
        this.goilSalePrices = goilSalePrices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Integer lastmodify) {
        this.lastmodify = lastmodify;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
