package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;

import java.math.BigDecimal;

/**
 * @ClassName BwoilOperationCommissionRate
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:58
 **/
public class BwoilOperationCommissionRate {
    // 商品类型bn
    private String goodsTypeBn;

    // 商品金额  默认：0
    private Long goodsAmount;

    // 佣金比例  默认：0.00
    private BigDecimal goodsRate;

    // 操作人名称
    private String operatorName;

    public String getGoodsTypeBn() {
        return goodsTypeBn;
    }

    public void setGoodsTypeBn(String goodsTypeBn) {
        this.goodsTypeBn = goodsTypeBn;
    }

    public Long getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Long goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getGoodsRate() {
        return goodsRate;
    }

    public void setGoodsRate(BigDecimal goodsRate) {
        this.goodsRate = goodsRate;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
