package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class RefundEntity {

    // 退款单id
    private Integer refundId;

    // 订单号
    private String orderId;

    // 退款卡号
    private String cardBn;

    // 退款金额  默认：0.00
    private BigDecimal money;

    // 会员id
    private Integer memberId;

    // 会员账户
    private String mobile;

    // 会员编号
    private String memberBn;

    // 退款状态  默认：ready
    private String refundStatus;

    // 申请时间
    private String applyTime;

    // 审核时间  默认：0000-00-00 00:00:00
    private String auditTime;

    // 退款时间  默认：0000-00-00 00:00:00
    private String refundTime;

    // 审核人
    private Integer auditer;

    // 退款人
    private Integer consenter;

    // 退款原因
    private String reasion;

    // 审核备注
    private String auditMemo;

    // 确认退款备注
    private String confirmMemo;

    // 申请时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCardBn() {
        return cardBn;
    }

    public void setCardBn(String cardBn) {
        this.cardBn = cardBn;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getAuditer() {
        return auditer;
    }

    public void setAuditer(Integer auditer) {
        this.auditer = auditer;
    }

    public Integer getConsenter() {
        return consenter;
    }

    public void setConsenter(Integer consenter) {
        this.consenter = consenter;
    }

    public String getReasion() {
        return reasion;
    }

    public void setReasion(String reasion) {
        this.reasion = reasion;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public String getConfirmMemo() {
        return confirmMemo;
    }

    public void setConfirmMemo(String confirmMemo) {
        this.confirmMemo = confirmMemo;
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