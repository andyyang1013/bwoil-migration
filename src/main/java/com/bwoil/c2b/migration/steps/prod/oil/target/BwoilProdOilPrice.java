package com.bwoil.c2b.migration.steps.prod.oil.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by qinls on 2019/3/8.
 */

public class BwoilProdOilPrice {
    private Integer id;

    // 油价日期
    private String oilDate;

    // 地区ID
    private Integer regionId;

    // 地区名称
    private String regionName;

    // 油品ID
    private Integer oilId;

    // 油品名称
    private String oilName;

    // 平台卖出价(元/升)
    private BigDecimal salePrice;

    // 平台买入价(元/升)
    private BigDecimal buyPrice;

    // 审核状态 0: 待审核 1: 审核通过 2: 审核拒绝   默认：0
    private String auditStatus;

    // 维护人ID
    private Integer applyerId;

    // 维护人姓名
    private String applyerName;

    // 审核人id
    private Integer auditorId;

    // 审核人
    private String auditorName;

    // 审核备注
    private String auditorRemark;

    // 审核时间  默认：0000-00-00 00:00:00
    private Date authTime;

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

    public String getOilDate() {
        return oilDate;
    }

    public void setOilDate(String oilDate) {
        this.oilDate = oilDate;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getApplyerId() {
        return applyerId;
    }

    public void setApplyerId(Integer applyerId) {
        this.applyerId = applyerId;
    }

    public String getApplyerName() {
        return applyerName;
    }

    public void setApplyerName(String applyerName) {
        this.applyerName = applyerName;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getAuditorRemark() {
        return auditorRemark;
    }

    public void setAuditorRemark(String auditorRemark) {
        this.auditorRemark = auditorRemark;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
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
