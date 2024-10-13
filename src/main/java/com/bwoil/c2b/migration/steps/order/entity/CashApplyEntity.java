package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class CashApplyEntity {

	private String memberName;
    // 会员提现ID
    private Long cashOutId;

    // 提现编号
    private String cashBn;

    // 会员id
    private Integer memberId;

    // 手机号
    private String mobile;

    // 会员编号
    private String memberBn;

    // 银行账户
    private String bankNo;

    // 账户可用余额  默认：0.00
    private BigDecimal availableBalance;

    // 提现金额
    private BigDecimal cashAmount;

    // 提现手续费
    private BigDecimal cashFee;

    // 账户提现账号id
    private Integer cashAccountId;

    // 提现状态:0 待审核,1 审核通过,2 审核未通过,3 已到账,4 出账失败,5 出账异常  默认：0
    private String cashStatus;

    // 提现到账:Y N  默认：N
    private String cashReceived;

    // 平台
    private String plat;

    // 设备信息
    private String devinfo;

    // 审核人ID
    private Integer auditOperatorId;

    // 审核人姓名
    private String auditOperatorName;

    // 审核IP
    private String auditIp;

    // 打款队列状态:0,1  默认：0
    private String remittanceStatus;

    // 备注
    private String remark;

    // 自动对账状态0未走自动对账流程1待对账2处理中3对账成功4对账失败5对账异常  默认：1
    private String autoReconStatus;

    // 审核方式：0人工审核1自动对账审核2(无审批方式)  默认：2
    private String autoType;

    // 打款方式0,1,2  默认：2
    private String cashPayType;

    // 线上打款通道
    private String cashPayChannel;

    // 打款方式申请人
    private String cashPayProposer;

    // 复核状态0,1,2  默认：0
    private String cashPayAuditStatus;

    // 复核人
    private String cashPayAuditor;

    // 复核备注
    private String cashPayRemark;

    // 对账状态0,1,2  默认：0
    private String accountStatus;

    // 充值账户可用余额  默认：0.00
    private BigDecimal rechargeAvailableBalance;

    // 红包账户可用余额  默认：0.00
    private BigDecimal redEnvelopeAvailableBalance;

    // 自动对账备注
    private String autoRemark;

    private String atime;
    private String auTime;
    private String finishTime;
    private String startTime;
    private String updateTime;
    private String accountTime;

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getAuTime() {
        return auTime;
    }

    public void setAuTime(String auTime) {
        this.auTime = auTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCashOutId() {
        return cashOutId;
    }

    public void setCashOutId(Long cashOutId) {
        this.cashOutId = cashOutId;
    }

    public String getCashBn() {
        return cashBn;
    }

    public void setCashBn(String cashBn) {
        this.cashBn = cashBn;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberBn() {
        return memberBn;
    }

    public void setMemberBn(String memberBn) {
        this.memberBn = memberBn;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public Integer getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(Integer cashAccountId) {
        this.cashAccountId = cashAccountId;
    }

    public String getCashStatus() {
        return cashStatus;
    }

    public void setCashStatus(String cashStatus) {
        this.cashStatus = cashStatus;
    }

    public String getCashReceived() {
        return cashReceived;
    }

    public void setCashReceived(String cashReceived) {
        this.cashReceived = cashReceived;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getDevinfo() {
        return devinfo;
    }

    public void setDevinfo(String devinfo) {
        this.devinfo = devinfo;
    }

    public Integer getAuditOperatorId() {
        return auditOperatorId;
    }

    public void setAuditOperatorId(Integer auditOperatorId) {
        this.auditOperatorId = auditOperatorId;
    }

    public String getAuditOperatorName() {
        return auditOperatorName;
    }

    public void setAuditOperatorName(String auditOperatorName) {
        this.auditOperatorName = auditOperatorName;
    }

    public String getAuditIp() {
        return auditIp;
    }

    public void setAuditIp(String auditIp) {
        this.auditIp = auditIp;
    }

    public String getRemittanceStatus() {
        return remittanceStatus;
    }

    public void setRemittanceStatus(String remittanceStatus) {
        this.remittanceStatus = remittanceStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAutoReconStatus() {
        return autoReconStatus;
    }

    public void setAutoReconStatus(String autoReconStatus) {
        this.autoReconStatus = autoReconStatus;
    }

    public String getAutoType() {
        return autoType;
    }

    public void setAutoType(String autoType) {
        this.autoType = autoType;
    }

    public String getCashPayType() {
        return cashPayType;
    }

    public void setCashPayType(String cashPayType) {
        this.cashPayType = cashPayType;
    }

    public String getCashPayChannel() {
        return cashPayChannel;
    }

    public void setCashPayChannel(String cashPayChannel) {
        this.cashPayChannel = cashPayChannel;
    }

    public String getCashPayProposer() {
        return cashPayProposer;
    }

    public void setCashPayProposer(String cashPayProposer) {
        this.cashPayProposer = cashPayProposer;
    }

    public String getCashPayAuditStatus() {
        return cashPayAuditStatus;
    }

    public void setCashPayAuditStatus(String cashPayAuditStatus) {
        this.cashPayAuditStatus = cashPayAuditStatus;
    }

    public String getCashPayAuditor() {
        return cashPayAuditor;
    }

    public void setCashPayAuditor(String cashPayAuditor) {
        this.cashPayAuditor = cashPayAuditor;
    }

    public String getCashPayRemark() {
        return cashPayRemark;
    }

    public void setCashPayRemark(String cashPayRemark) {
        this.cashPayRemark = cashPayRemark;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public BigDecimal getRechargeAvailableBalance() {
        return rechargeAvailableBalance;
    }

    public void setRechargeAvailableBalance(BigDecimal rechargeAvailableBalance) {
        this.rechargeAvailableBalance = rechargeAvailableBalance;
    }

    public BigDecimal getRedEnvelopeAvailableBalance() {
        return redEnvelopeAvailableBalance;
    }

    public void setRedEnvelopeAvailableBalance(BigDecimal redEnvelopeAvailableBalance) {
        this.redEnvelopeAvailableBalance = redEnvelopeAvailableBalance;
    }

    public String getAutoRemark() {
        return autoRemark;
    }

    public void setAutoRemark(String autoRemark) {
        this.autoRemark = autoRemark;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

}