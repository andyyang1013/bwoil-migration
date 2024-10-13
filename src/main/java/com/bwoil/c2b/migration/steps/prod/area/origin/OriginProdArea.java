package com.bwoil.c2b.migration.steps.prod.area.origin;

public class OriginProdArea {

    // 区域序号
    private Integer regionId;
    // 地区名称
    private String localName;
    // 地区包的类别, 中国/外国等. 中国大陆的编号目前为 mainland
//    private String package;
    // 上一级地区的序号
    private Integer pRegionId;
    // 序号层级排列结构
    private String regionPath;
    // 地区层级
    private Integer regionGrade;
    // 排序
    private Integer ordernum;
    //
    private String disabled;
    // 区号
    private String areaCode;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public Integer getpRegionId() {
        return pRegionId;
    }

    public void setpRegionId(Integer pRegionId) {
        this.pRegionId = pRegionId;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public Integer getRegionGrade() {
        return regionGrade;
    }

    public void setRegionGrade(Integer regionGrade) {
        this.regionGrade = regionGrade;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}
