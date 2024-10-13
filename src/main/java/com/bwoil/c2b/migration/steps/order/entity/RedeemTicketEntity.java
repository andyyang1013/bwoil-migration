package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class RedeemTicketEntity {
    private String fuelId;
    // 卡编号
    private String cardBn;

    // 0:自动审核 1:手工审核
    private String authType;

    // 兑付流水号
    private String redeemBn;

    // 会员ID
    private Integer memberId;

    // 会员编号
    private String memberBn;

    // 加油金额  默认：0.00
    private BigDecimal amount;

    // 发票代码
    private String ticktCode;

    // 发票号码
    private String ticktNo;

    // 收款单位
    private String ticktCompany;

    // 发票日期  默认：0000-00-00 00:00:00
    private String ticktDate;

    // 纳税人识别码
    private String taxIden;

    // 发票/小票附件id
    private String attachmentImage;

    // 审核状态0,1,2  默认：0
    private String auditStatus;

    // 审核人
    private Integer operatorId;

    // 审核时间  默认：0000-00-00 00:00:00
    private String auditTime;

    // 审核IP
    private String auditIp;

    // 审核备注
    private String auditRemark;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    public String getFuelId() {
        return fuelId;
    }

    public void setFuelId(String fuelId) {
        this.fuelId = fuelId;
    }

    public String getCardBn() {
        return cardBn;
    }

    public void setCardBn(String cardBn) {
        this.cardBn = cardBn;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getRedeemBn() {
        return redeemBn;
    }

    public void setRedeemBn(String redeemBn) {
        this.redeemBn = redeemBn;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberBn() {
        return memberBn;
    }

    public void setMemberBn(String memberBn) {
        this.memberBn = memberBn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTicktCode() {
        return ticktCode;
    }

    public void setTicktCode(String ticktCode) {
        this.ticktCode = ticktCode;
    }

    public String getTicktNo() {
        return ticktNo;
    }

    public void setTicktNo(String ticktNo) {
        this.ticktNo = ticktNo;
    }

    public String getTicktCompany() {
        return ticktCompany;
    }

    public void setTicktCompany(String ticktCompany) {
        this.ticktCompany = ticktCompany;
    }

    public String getTicktDate() {
        return ticktDate;
    }

    public void setTicktDate(String ticktDate) {
        this.ticktDate = ticktDate;
    }

    public String getTaxIden() {
        return taxIden;
    }

    public void setTaxIden(String taxIden) {
        this.taxIden = taxIden;
    }

    public String getAttachmentImage() {
        return attachmentImage;
    }

    public void setAttachmentImage(String attachmentImage) {
        this.attachmentImage = attachmentImage;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditIp() {
        return auditIp;
    }

    public void setAuditIp(String auditIp) {
        this.auditIp = auditIp;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
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