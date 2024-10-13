package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;


public class BwoilOperationGasstationRefuelPayfailed {

    // 自增主键
    private Integer payfailedId;

    // 订单号
    private String orderId;

    // 打款编号
    private String payBn;

    // 打款类型
    private String payType;

    // 打款金额  默认：0.00
    private BigDecimal totalAmount;

    // 打款状态  默认：success
    private String status;

    // 打款处理信息
    private String message;

    // 处理时间
    private Date completeTime;

    // 创建时间
    private Date createdTime;


    public Integer getPayfailedId() {
        return payfailedId;
    }

    public void setPayfailedId(Integer payfailedId) {
        this.payfailedId = payfailedId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayBn() {
        return payBn;
    }

    public void setPayBn(String payBn) {
        this.payBn = payBn;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
