package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;


public class BwoilOperationPromotionCommissionRate {

    private long id;
    private String goodsTypeBn;
    private long goodsAmount;
    private double goodsRate;
    private String operatorName;
    private long status;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getGoodsTypeBn() {
        return goodsTypeBn;
    }

    public void setGoodsTypeBn(String goodsTypeBn) {
        this.goodsTypeBn = goodsTypeBn;
    }


    public long getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(long goodsAmount) {
        this.goodsAmount = goodsAmount;
    }


    public double getGoodsRate() {
        return goodsRate;
    }

    public void setGoodsRate(double goodsRate) {
        this.goodsRate = goodsRate;
    }


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }


    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
