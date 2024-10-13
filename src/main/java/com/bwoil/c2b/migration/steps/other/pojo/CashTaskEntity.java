package com.bwoil.c2b.migration.steps.other.pojo;

import java.math.BigDecimal;


public class CashTaskEntity {

    // ID
    private Integer taskId;

    // 打款编号
    private String taskBn;

    // 会员id  默认：0
    private Integer memberId;

    // 会员账号
    private String memberAccount;

    // 会员编号
    private String memberBn;

    // 提现编号
    private String cashBn;

    // 提现金额  默认：0.00
    private BigDecimal cashAmount;

    // 审批时间  默认：0000-00-00 00:00:00
    private String auditTime;

    // 打款任务创建时间  默认：0000-00-00 00:00:00
    private String taskTime;

    // 打款时间  默认：0000-00-00 00:00:00
    private String remittanceTime;

    // 打款状态  默认：pending
    private String status;

    // 清结算流水号
    private String clearBn;

    // 打款通道
    private String channel;

    // 重试状态 Y: 重试 N: 未重试  默认：N
    private String isRetry;

    // 重新打款人
    private String reRemittance;

    // 备注
    private String remark;

    private String createTime;

    private String lastUpdateTime;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskBn() {
        return taskBn;
    }

    public void setTaskBn(String taskBn) {
        this.taskBn = taskBn;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberBn() {
        return memberBn;
    }

    public void setMemberBn(String memberBn) {
        this.memberBn = memberBn;
    }

    public String getCashBn() {
        return cashBn;
    }

    public void setCashBn(String cashBn) {
        this.cashBn = cashBn;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getRemittanceTime() {
        return remittanceTime;
    }

    public void setRemittanceTime(String remittanceTime) {
        this.remittanceTime = remittanceTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClearBn() {
        return clearBn;
    }

    public void setClearBn(String clearBn) {
        this.clearBn = clearBn;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIsRetry() {
        return isRetry;
    }

    public void setIsRetry(String isRetry) {
        this.isRetry = isRetry;
    }

    public String getReRemittance() {
        return reRemittance;
    }

    public void setReRemittance(String reRemittance) {
        this.reRemittance = reRemittance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}