package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

import java.math.BigDecimal;

/**
 * @ClassName OriginOperationCommissionRate
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:55
 **/
public class OriginOperationCommissionRate {
    private String type_bn;
    private String goods_spec;
    private BigDecimal goods_rate;
    private Integer rate_type;
    private String login_name;

    public String getType_bn() {
        return type_bn;
    }

    public void setType_bn(String type_bn) {
        this.type_bn = type_bn;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public BigDecimal getGoods_rate() {
        return goods_rate;
    }

    public void setGoods_rate(BigDecimal goods_rate) {
        this.goods_rate = goods_rate;
    }

    public Integer getRate_type() {
        return rate_type;
    }

    public void setRate_type(Integer rate_type) {
        this.rate_type = rate_type;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
}
