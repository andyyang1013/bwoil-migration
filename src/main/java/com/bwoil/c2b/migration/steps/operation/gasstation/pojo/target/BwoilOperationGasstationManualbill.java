package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstationManualbill {
    // 自增主键
    private Integer billId;

    // 单号
    private String accountNo;

    // 打款单号
    private String payBn;

    // 财务类型
    private String financialType;

    // 打款类型  默认：REALTIME
    private String payType;

    // 费用类型
    private String costsName;

    // 客户名称
    private String stationName;

    // 金额  默认：0.00
    private BigDecimal totalAmount;

    // 开户行
    private String bankName;

    // 收款帐号
    private String bankAccount;

    // 加油站ID
    private Integer stationId;

    // 费用类型说明
    private String costsIntroductions;

    // 状态  默认：audit
    private String status;

    // 打款处理信息
    private String message;

    // 处理时间
    private Date completeTime;

    // 创建时间  默认：0000-00-00 00:00:00
    private Date createdTime;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
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

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCostsName() {
        return costsName;
    }

    public void setCostsName(String costsName) {
        this.costsName = costsName;
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

    public String getCostsIntroductions() {
        return costsIntroductions;
    }

    public void setCostsIntroductions(String costsIntroductions) {
        this.costsIntroductions = costsIntroductions;
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
