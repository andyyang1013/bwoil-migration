package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;

import java.math.BigDecimal;

/**
 * @Date 2019/3/7 11:15
 * @Author wenyue
 * @Description:
 **/
public class RuleEntity {


    private Integer couponId;

    private BigDecimal refuelMoney;

    private Integer num;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getRefuelMoney() {
        return refuelMoney;
    }

    public void setRefuelMoney(BigDecimal refuelMoney) {
        this.refuelMoney = refuelMoney;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
