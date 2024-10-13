package com.bwoil.c2b.migration.steps.pay.pojo.origin;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class OriginPayment {

    // 支付单号
    private BigInteger id;

    // 订单号
    private String orderNo;

    // 支付状态,参考TradeStatus  默认：WAIT
    private String tradeStatus;

    // 支付单错误信息
    private String tradeErrorMsg;

    // 支付金额  默认：0.000
    private Double amount;

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
    private BigDecimal payFee;

    // 支付IP
    private String ip;

    // 支付单交易编号
    private String paymentId;

    // 支付返回地址
    private String returnUrl;

    // 第三方支付账户
    private String thirdpartyAccount;

    // 支付完成时间  默认：CURRENT_TIMESTAMP
    private Date payTime;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    //状态 默认0  删除 -1
    private String status;

    // 是否组合支付单
    private boolean combineOrder;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getCombineOrder() {
        return combineOrder;
    }

    public void setCombineOrder(boolean combineOrder) {
        this.combineOrder = combineOrder;
    }
}