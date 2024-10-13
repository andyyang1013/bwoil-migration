package com.bwoil.c2b.migration.steps.operation.refuel.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationRefuelProducts {


    private Long id;
    // 聚合产品id
    private Integer proid;

    // 面值  默认：0.00
    private BigDecimal amount;

    // 售价  默认：0.00
    private BigDecimal priceSale;

    // 成本  默认：0.00
    private BigDecimal price;

    // 数量  默认：1
    private Integer num;

    // 加油卡类型 （1:中石化、2:中石油；默认为1)  默认：1
    private String chargeType;

    // 服务商 （1:聚合、2:欧飞；默认为1)  默认：1
    private String providerType;

    // 创建时间
    private Date createtime;

    // 状态(-1删除 0 正常)  默认：0
    private String status;


    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
