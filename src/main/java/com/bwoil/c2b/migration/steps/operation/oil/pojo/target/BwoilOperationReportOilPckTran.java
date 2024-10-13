package com.bwoil.c2b.migration.steps.operation.oil.pojo.target;


import java.sql.Timestamp;

public class BwoilOperationReportOilPckTran {

    private String id;
    private String activityName;
    private String tranType;
    private Double tranQuantity;
    private Double oilBoxMass;
    private Double tranOilPrice;
    private Double tranOilPriceTotal;
    private String orderBn;
    private String memberAccount;
    private String memberMobile;
    private Timestamp tranTime;
    private String tranBn;
    private String oilRemark;
    private Timestamp createTime;
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public Double getTranQuantity() {
        return tranQuantity;
    }

    public void setTranQuantity(Double tranQuantity) {
        this.tranQuantity = tranQuantity;
    }

    public Double getOilBoxMass() {
        return oilBoxMass;
    }

    public void setOilBoxMass(Double oilBoxMass) {
        this.oilBoxMass = oilBoxMass;
    }

    public Double getTranOilPrice() {
        return tranOilPrice;
    }

    public void setTranOilPrice(Double tranOilPrice) {
        this.tranOilPrice = tranOilPrice;
    }

    public Double getTranOilPriceTotal() {
        return tranOilPriceTotal;
    }

    public void setTranOilPriceTotal(Double tranOilPriceTotal) {
        this.tranOilPriceTotal = tranOilPriceTotal;
    }

    public String getOrderBn() {
        return orderBn;
    }

    public void setOrderBn(String orderBn) {
        this.orderBn = orderBn;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Timestamp getTranTime() {
        return tranTime;
    }

    public void setTranTime(Timestamp tranTime) {
        this.tranTime = tranTime;
    }

    public String getTranBn() {
        return tranBn;
    }

    public void setTranBn(String tranBn) {
        this.tranBn = tranBn;
    }

    public String getOilRemark() {
        return oilRemark;
    }

    public void setOilRemark(String oilRemark) {
        this.oilRemark = oilRemark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
