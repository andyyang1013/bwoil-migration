package com.bwoil.c2b.migration.steps.pay.pojo.target;


import java.util.Date;

public class PaymentBehaviorEntity {

    // 记录ID
    private Integer recordId;

    // 会员用户名
    private String memberId;

    // 支付账户
    private String payAccount;

    // 支付单号
    private String paymentId;

    // 支付类型 online,offline,deposit  默认：online
    private String payType;

    // 支付方式名称
    private String channel;

    // 平台来源 pc,app,wap,weixin  默认：pc
    private String source;

    // 支付金额  默认：0
    private Long money;

    // 是否快捷支付  默认：0
    private Boolean quickPay;

    // 银行简码
    private String bankBn;

    // 支付完成时间  默认：CURRENT_TIMESTAMP
    private Date payTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Boolean getQuickPay() {
        return quickPay;
    }

    public void setQuickPay(Boolean quickPay) {
        this.quickPay = quickPay;
    }

    public String getBankBn() {
        return bankBn;
    }

    public void setBankBn(String bankBn) {
        this.bankBn = bankBn;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}