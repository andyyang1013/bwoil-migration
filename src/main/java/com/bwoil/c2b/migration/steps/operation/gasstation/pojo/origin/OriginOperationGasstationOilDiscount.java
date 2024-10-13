package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationGasstationOilDiscount {

    private Integer oilDiscountId;
    // 合作协议ID
    private Integer agreementId;

    // 折扣形式, 值1升数折扣, 2金额优惠折扣
    private String discountId;

    // 加油站ID
    private Integer stationId;

    // 油品ID
    private Integer oilId;

    // 输入的数据
    private BigDecimal data;

    // 创建时间
    private Integer createdTime;

    public Integer getOilDiscountId() {
        return oilDiscountId;
    }

    public void setOilDiscountId(Integer oilDiscountId) {
        this.oilDiscountId = oilDiscountId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
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

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }
}
