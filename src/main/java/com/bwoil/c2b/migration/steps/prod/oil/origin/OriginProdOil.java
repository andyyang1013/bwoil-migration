package com.bwoil.c2b.migration.steps.prod.oil.origin;

/**
 * Created by qinls on 2019/3/8.
 */

public class OriginProdOil {

    // 油品ID
    private Integer oilId;
    // 油品编号
    private String oilBn;
    // 油品名称
    private String oilName;
    // 加油站使用
    private String isStation;

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public String getOilBn() {
        return oilBn;
    }

    public void setOilBn(String oilBn) {
        this.oilBn = oilBn;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public String getIsStation() {
        return isStation;
    }

    public void setIsStation(String isStation) {
        this.isStation = isStation;
    }
}
