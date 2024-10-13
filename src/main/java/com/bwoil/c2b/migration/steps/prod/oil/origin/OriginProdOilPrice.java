package com.bwoil.c2b.migration.steps.prod.oil.origin;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by qinls on 2019/3/8.
 */

public class OriginProdOilPrice {
    private Integer oilPriceId;

    // 油价日期
    private Date oilDate;

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
    private Integer operatorId;

    // 维护人姓名
    private String operatorName;

    // 维护时间
    private int operatorTime;

    // 最后修改时间
    private int lastmodify;


    public Integer getOilPriceId() {
        return oilPriceId;
    }

    public void setOilPriceId(Integer oilPriceId) {
        this.oilPriceId = oilPriceId;
    }

    public Date getOilDate() {
        return oilDate;
    }

    public void setOilDate(Date oilDate) {
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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(int operatorTime) {
        this.operatorTime = operatorTime;
    }

    public int getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(int lastmodify) {
        this.lastmodify = lastmodify;
    }
}
