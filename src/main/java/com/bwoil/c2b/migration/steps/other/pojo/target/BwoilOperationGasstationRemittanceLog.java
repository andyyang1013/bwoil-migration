package com.bwoil.c2b.migration.steps.other.pojo.target;


public class BwoilOperationGasstationRemittanceLog {

    private Long logId;
    private Long orderId;
    private Long stationId;
    private String memberBn;
    private String status;
    private String message;
    private String reqId;
    private java.sql.Timestamp completeTime;
    private java.sql.Timestamp createdTime;


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }


    public String getMemberBn() {
        return memberBn;
    }

    public void setMemberBn(String memberBn) {
        this.memberBn = memberBn;
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


    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
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
