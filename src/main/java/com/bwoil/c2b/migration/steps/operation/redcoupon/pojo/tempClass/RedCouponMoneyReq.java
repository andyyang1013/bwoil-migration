package com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.tempClass;

import java.math.BigDecimal;

/**
 * @author wumr
 * @Description: TODO
 * @date 2019/3/6 17:21
 */
public class RedCouponMoneyReq {
    // @ApiModelProperty(value=" 订单金额", dataType="BigDecimal")
    private BigDecimal ruleOrderMoney;

    //@ApiModelProperty(value=" 抵扣金额", dataType="BigDecimal")
    private BigDecimal ruleMoney;

    public BigDecimal getRuleOrderMoney() {
        return ruleOrderMoney;
    }

    public void setRuleOrderMoney(BigDecimal ruleOrderMoney) {
        this.ruleOrderMoney = ruleOrderMoney;
    }

    public BigDecimal getRuleMoney() {
        return ruleMoney;
    }

    public void setRuleMoney(BigDecimal ruleMoney) {
        this.ruleMoney = ruleMoney;
    }
}
