package com.bwoil.c2b.migration.steps.other.pojo.target;


public class BwoilOperationGasstationManualbillOrder {

    private Long id;
    private String orderId;
    private String accountNo;
    private String payBn;
    private String payType;
    private Double totalAmount;
    private Double refundAmount;
    private String status;
    private String message;
    private java.sql.Timestamp completeTime;
    private java.sql.Timestamp createdTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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


    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
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


    public java.sql.Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(java.sql.Timestamp completeTime) {
        this.completeTime = completeTime;
    }


    public java.sql.Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(java.sql.Timestamp createdTime) {
        this.createdTime = createdTime;
    }

}
