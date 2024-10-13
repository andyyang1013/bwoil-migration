package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstationManualpay {
    // 自增主键
    private Integer clearingId;

    // 打款编号
    private String payBn;

    // 打款类型
    private String payType;

    // 加油站名称
    private String stationName;

    // 打款金额  默认：0.00
    private BigDecimal totalAmount;

    // 开户银行
    private String bankName;

    // 开户支行
    private String bankArea;

    // 银行帐号
    private String bankAccount;

    // 加油站ID
    private Integer stationId;

    // 打款状态  默认：success
    private String status;

    // 打款处理信息
    private String message;

    // 处理时间
    private Date completeTime;

    // 创建时间  默认：0000-00-00 00:00:00
    private Date createdTime;

    // 款项类型  默认：2
    private String type;

    // 企业清结算方式  默认：0
    private String isType;

    public Integer getClearingId() {
        return clearingId;
    }

    public void setClearingId(Integer clearingId) {
        this.clearingId = clearingId;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankArea() {
        return bankArea;
    }

    public void setBankArea(String bankArea) {
        this.bankArea = bankArea;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsType() {
        return isType;
    }

    public void setIsType(String isType) {
        this.isType = isType;
    }
}
