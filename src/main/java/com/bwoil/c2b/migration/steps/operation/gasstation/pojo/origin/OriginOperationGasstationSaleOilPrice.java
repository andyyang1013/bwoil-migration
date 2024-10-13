package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationGasstationSaleOilPrice {

    private Integer oilPriceId;
    // 油站公司ID  默认：0
    private Integer companyId;

    // 加油站ID
    private Integer stationId;

    // 油品ID
    private Integer oilId;

    // 油站挂牌价(元/升)  默认：0.00
    private BigDecimal oilPrice;

    // 挂牌价一天内调整的次数  默认：0
    private Integer adjustTimes;

    // 油价调整幅度(%)  默认：0.00
    private BigDecimal priceRange;


    // 维护人姓名
    private String modifyName;

    // 创建人姓名
    private String createdName;

    // 状态, 失效删除-1, 待审核0默认, 审核ok启用1, 审核不通过2, 待提交3
    private String status;

    // 结算优惠0.0元/升   (协议表中的字段)
    private BigDecimal discount;

    // 生效时间
    private Integer startDate;

    // 维护时间
    private Integer modifyTime;

    // 创建时间
    private Integer createdTime;

    public Integer getOilPriceId() {
        return oilPriceId;
    }

    public void setOilPriceId(Integer oilPriceId) {
        this.oilPriceId = oilPriceId;
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

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public Integer getAdjustTimes() {
        return adjustTimes;
    }

    public void setAdjustTimes(Integer adjustTimes) {
        this.adjustTimes = adjustTimes;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
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
