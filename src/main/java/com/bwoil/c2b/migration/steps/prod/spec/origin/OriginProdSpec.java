package com.bwoil.c2b.migration.steps.prod.spec.origin;

//旧数据表 规格表 sdb_b2c_specification

public class OriginProdSpec {
    // 规格id
    private Integer specId;
    // 规格名称
    private String specName;
    // 显示方式
    private String specShowType;
    // 类型
    private String specType;
    // 规格备注
    private String specMemo;
    // 排序
    private Integer pOrder;
    //规格属性
    private String specAttr;
    //
    private String disabled;
    //规格别名
    private String alias;


    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecShowType() {
        return specShowType;
    }

    public void setSpecShowType(String specShowType) {
        this.specShowType = specShowType;
    }

    public String getSpecType() {
        return specType;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }

    public String getSpecMemo() {
        return specMemo;
    }

    public void setSpecMemo(String specMemo) {
        this.specMemo = specMemo;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
