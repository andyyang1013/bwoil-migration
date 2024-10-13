package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.util.Date;

public class OriginOperationGasstationDiscountRule {

    private Integer stationDiscountRuleId;

    private String modifyName;

    private Integer companyId;

    private String createdName;

    private String status;

    private Integer modifyTime;

    private Integer createdTime;

    private Date effectiveTime;

    public Integer getStationDiscountRuleId() {
        return stationDiscountRuleId;
    }

    public void setStationDiscountRuleId(Integer stationDiscountRuleId) {
        this.stationDiscountRuleId = stationDiscountRuleId;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

}
