package com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target;


import java.util.Date;

public class BwoilOperationCashcouponQueue {

    private Integer queueId;
    private Date createTime;
    private Date updateTime;
    private String queueCpnname;
    private String queueMobile;
    private Integer queueType;
    private Integer queueStatus;
    private Integer sendType;
    private String sendMobile;
    private String coupons;


    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getQueueCpnname() {
        return queueCpnname;
    }

    public void setQueueCpnname(String queueCpnname) {
        this.queueCpnname = queueCpnname;
    }

    public String getQueueMobile() {
        return queueMobile;
    }

    public void setQueueMobile(String queueMobile) {
        this.queueMobile = queueMobile;
    }

    public Integer getQueueType() {
        return queueType;
    }

    public void setQueueType(Integer queueType) {
        this.queueType = queueType;
    }

    public Integer getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(Integer queueStatus) {
        this.queueStatus = queueStatus;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }
}
