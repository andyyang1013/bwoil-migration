package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

public class OriginOperationGasstationOil {

    private Integer stationOilId;
    // 油站公司ID
    private Integer companyId;

    // 加油站ID
    private Integer stationId;

    // 油品ID
    private Integer oilId;

    // 维护人姓名
    private String modifyName;

    // 创建人姓名
    private String createdName;

    // 状态, -1停用, 0启用  默认：0
    private String status;

    // 维护时间
    private Integer modifyTime;

    // 创建时间
    private Integer createdTime;

    public Integer getStationOilId() {
        return stationOilId;
    }

    public void setStationOilId(Integer stationOilId) {
        this.stationOilId = stationOilId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
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
}
