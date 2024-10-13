package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationGasstationDiscountItem {

    private Integer stationDiscountRuleItemId;
    // 定升加油卡折扣规则ID
    private Integer stationDiscountRuleId;

    // 油品ID
    private Integer oilId;

    // 充值升数条件
    private Integer litreLimit;

    // 优惠金额
    private BigDecimal discountMoney;

    public Integer getStationDiscountRuleItemId() {
        return stationDiscountRuleItemId;
    }

    public void setStationDiscountRuleItemId(Integer stationDiscountRuleItemId) {
        this.stationDiscountRuleItemId = stationDiscountRuleItemId;
    }

    public Integer getStationDiscountRuleId() {
        return stationDiscountRuleId;
    }

    public void setStationDiscountRuleId(Integer stationDiscountRuleId) {
        this.stationDiscountRuleId = stationDiscountRuleId;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public Integer getLitreLimit() {
        return litreLimit;
    }

    public void setLitreLimit(Integer litreLimit) {
        this.litreLimit = litreLimit;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }
}
