package com.bwoil.c2b.migration.steps.prod.oil.target;

import java.util.Date;

/**
 * Created by qinls on 2019/3/8.
 */

public class BwoilProdOil {
    // 油品ID
    private Integer id;
    // 油品编号
    private String oilBn;
    // 油品名称
    private String oilName;
    // 加油站使用
    private String isStation;
    // 状态:-1:删除 0:正常  默认：0
    private String status;
    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;
    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
