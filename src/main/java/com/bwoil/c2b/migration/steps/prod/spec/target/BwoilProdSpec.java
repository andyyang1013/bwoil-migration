package com.bwoil.c2b.migration.steps.prod.spec.target;

import java.util.Date;

/**
 * Created by qinls on 2019/3/7.
 */
// 规格表 bwoil_prod_spec

public class BwoilProdSpec {
    private Integer id;
    // 规格名称
    private String specName;
    // 排序
    private Integer pOrder;
    //规格属性
    private String specAttr;
    // 是否可用Y/N
    private String disabled;
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

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getpOrder() {
        return pOrder;
    }

    public void setpOrder(Integer pOrder) {
        this.pOrder = pOrder;
    }

    public String getSpecAttr() {
        return specAttr;
    }

    public void setSpecAttr(String specAttr) {
        this.specAttr = specAttr;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
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
