package com.bwoil.c2b.migration.steps.pay.pojo.target;

import java.util.Date;


public class PaymentEntity {
    // 支付单号
    private Integer id;

    // 订单号
    private String orderNo;

    // 支付状态,参考TradeStatus  默认：WAIT
    private String tradeStatus;

    // 支付单错误信息
    private String tradeErrorMsg;

    // 支付金额  默认：0.000
    private Integer amount;

    // 会员用户名
    private Integer memberId;


    // 支付类型：order 订单 recharge 充值  默认：'order'
    private String payType;

    // 支付方式
    private String payChannel;

    // 支付方式名称
    private String payChannelName;

    // 商品名称
    private String subject;

    // 收款账号
    private String payedAccount;

    // 收款银行
    private String payedBank;

    // 支付账户
    private String account;

    // 货币
    private String currency;

    // 支付网关费用
    private Integer payFee;

    // 支付IP
    private String ip;

    // 支付单交易编号
    private String tradeNo;

    // 第三方支付单号
    private String thirdTradeNo;

    // 支付返回地址
    private String returnUrl;

    // 第三方支付账户
    private String thirdpartyAccount;

    // 是否已充值成功
    private Boolean chargeOrder;

    // 是否组合支付单
    private Boolean combineOrder;

    // 是否已通知订单
    private Boolean notifyOrder;

    // 是否已发送ERP
    private Boolean sendERP;

    // 支付完成时间  默认：CURRENT_TIMESTAMP
    private Date payTime;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    /**
     * 确认时间
     */
    private Date confirmTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    //状态 默认0  删除 -1
    private String status;

    // 额外参数
    private String extra;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeErrorMsg() {
        return tradeErrorMsg;
    }

    public void setTradeErrorMsg(String tradeErrorMsg) {
        this.tradeErrorMsg = tradeErrorMsg;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public void setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPayedAccount() {
        return payedAccount;
    }

    public void setPayedAccount(String payedAccount) {
        this.payedAccount = payedAccount;
    }

    public String getPayedBank() {
        return payedBank;
    }

    public void setPayedBank(String payedBank) {
        this.payedBank = payedBank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPayFee() {
        return payFee;
    }

    public void setPayFee(Integer payFee) {
        this.payFee = payFee;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getThirdpartyAccount() {
        return thirdpartyAccount;
    }

    public void setThirdpartyAccount(String thirdpartyAccount) {
        this.thirdpartyAccount = thirdpartyAccount;
    }

    public Boolean getChargeOrder() {
        return chargeOrder;
    }

    public void setChargeOrder(Boolean chargeOrder) {
        this.chargeOrder = chargeOrder;
    }

    public Boolean getCombineOrder() {
        return combineOrder;
    }

    public void setCombineOrder(Boolean combineOrder) {
        this.combineOrder = combineOrder;
    }

    public Boolean getNotifyOrder() {
        return notifyOrder;
    }

    public void setNotifyOrder(Boolean notifyOrder) {
        this.notifyOrder = notifyOrder;
    }

    public Boolean getSendERP() {
        return sendERP;
    }

    public void setSendERP(Boolean sendERP) {
        this.sendERP = sendERP;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}