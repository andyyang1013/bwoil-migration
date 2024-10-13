package com.bwoil.c2b.migration.steps.order.target;


import java.math.BigDecimal;

public class ChannelOrdersEntityTarg {

  private Long orderId;

  // 渠道订单号  默认：0
  private Long channelOrderId;

  // 渠道总订单号  默认：0
  private Long channelParentOrderId;

  // 产品标识号  默认：0
  private Long channelProductId;

  // 油卡号
  private Long cardBn;

  // 会员编号
  private Integer memberId;

  // 兑换码
  private String redeemCode;

  // 购买手机号
  private String buyAccount;

  // 会员手机号
  private String loginAccount;

  // 产品名称
  private String productName;

  // 产品金额  默认：0.000
  private BigDecimal price;

  // 期限  默认：0
  private Integer totalPeriod;

  // 总金额  默认：0.000
  private BigDecimal totalAmount;

  // 实付金额  默认：0.000
  private BigDecimal finalAmount;

  // 渠道名称 京东='jdshop'
  private String refuelChannel;

  // 创建时间  默认：CURRENT_TIMESTAMP
  private  Integer createtime;

  // 更新时间  默认：CURRENT_TIMESTAMP
  private  Integer lastModified;

  // 到账状态 默认0待收款, 1已收款, 2退款中, 3已退款
  private Integer payStatus;

  // 卡状态 默认active待兑换, redeemed已兑换, 已锁定locked, 已失效canceled
  private String status;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getChannelOrderId() {
    return channelOrderId;
  }

  public void setChannelOrderId(Long channelOrderId) {
    this.channelOrderId = channelOrderId;
  }

  public Long getChannelParentOrderId() {
    return channelParentOrderId;
  }

  public void setChannelParentOrderId(Long channelParentOrderId) {
    this.channelParentOrderId = channelParentOrderId;
  }

  public Long getChannelProductId() {
    return channelProductId;
  }

  public void setChannelProductId(Long channelProductId) {
    this.channelProductId = channelProductId;
  }

  public Long getCardBn() {
    return cardBn;
  }

  public void setCardBn(Long cardBn) {
    this.cardBn = cardBn;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }

  public String getRedeemCode() {
    return redeemCode;
  }

  public void setRedeemCode(String redeemCode) {
    this.redeemCode = redeemCode;
  }

  public String getBuyAccount() {
    return buyAccount;
  }

  public void setBuyAccount(String buyAccount) {
    this.buyAccount = buyAccount;
  }

  public String getLoginAccount() {
    return loginAccount;
  }

  public void setLoginAccount(String loginAccount) {
    this.loginAccount = loginAccount;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getTotalPeriod() {
    return totalPeriod;
  }

  public void setTotalPeriod(Integer totalPeriod) {
    this.totalPeriod = totalPeriod;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public BigDecimal getFinalAmount() {
    return finalAmount;
  }

  public void setFinalAmount(BigDecimal finalAmount) {
    this.finalAmount = finalAmount;
  }

  public String getRefuelChannel() {
    return refuelChannel;
  }

  public void setRefuelChannel(String refuelChannel) {
    this.refuelChannel = refuelChannel;
  }

  public Integer getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Integer createtime) {
    this.createtime = createtime;
  }

  public Integer getLastModified() {
    return lastModified;
  }

  public void setLastModified(Integer lastModified) {
    this.lastModified = lastModified;
  }

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
