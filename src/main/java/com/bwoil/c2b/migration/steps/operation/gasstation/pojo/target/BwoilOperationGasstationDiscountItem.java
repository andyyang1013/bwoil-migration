package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;

public class BwoilOperationGasstationDiscountItem {

    private Integer id;
    // 定升加油卡折扣规则ID
    private Integer discountId;

    // 油品ID
    private Integer oilId;

    // 充值升数条件
    private Integer litreLimit;

    // 优惠金额
    private BigDecimal discountMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
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
