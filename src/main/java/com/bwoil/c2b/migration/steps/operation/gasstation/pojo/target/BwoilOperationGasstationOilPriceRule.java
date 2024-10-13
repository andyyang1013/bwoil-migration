package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.util.Date;

public class BwoilOperationGasstationOilPriceRule {

    private Integer id;

    private Date operatorTime;

    private Integer operatorId;

    private String operatorName;

    private Integer auditId;

    private String auditName;

    private Date auditTime;

    private Date goilDate;

    private String goilSalePrices;

    private String status;

    private Date lastmodify;

    private Integer companyId;

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getGoilDate() {
        return goilDate;
    }

    public void setGoilDate(Date goilDate) {
        this.goilDate = goilDate;
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

    public Date getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Date lastmodify) {
        this.lastmodify = lastmodify;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
