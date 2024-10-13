package com.bwoil.c2b.migration.steps.order.entity;

import java.util.Date;

public class OrderCancelEntity {

    // 订单号  默认：0
    private String orderId;

    // 取消原因类型:0-7  默认：0
    private String reasonType;

    // 订单备注
    private String reasonDesc;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date cancelTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }


}