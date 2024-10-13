package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

public class OriginOperationGasstationGun {

    private Integer id;
    // 加油站id  默认：0
    private Integer stationId;

    // 石油品名 id  默认：0
    private Integer oilId;

    // 加油枪号  默认：0
    private Integer gunNum;

    // 更新时间  默认：0000-00-00 00:00:00
    private Integer updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getGunNum() {
        return gunNum;
    }

    public void setGunNum(Integer gunNum) {
        this.gunNum = gunNum;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
}

