package com.bwoil.c2b.migration.steps.prod.area.target;

import java.util.Date;

/**
 * Created by qinls on 2019/3/7.
 */

public class BwoilProdArea {
    private Integer id;

    // 地区名称
    private String localName;

    // 地区包的类别, 中国/外国等. 中国大陆的编号目前为mainland
    private String pkg;

    // 上一级地区的序号
    private Integer pRegionId;

    // 序号层级排列结构
    private String regionPath;

    // 地区层级
    private Integer regionGrade;

    // 区号
    private String areaCode;

    // 排序
    private Integer ordernum;

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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
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
