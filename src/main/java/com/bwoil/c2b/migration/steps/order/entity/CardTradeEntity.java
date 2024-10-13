package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CardTradeEntity {
    private String id;
    // 交易编号
    private String tradeBn;

    // 储油通卡号
    private String cardBn;

    // 产品名称
    private String productName;

    // 会员ID
    private Integer memberId;

    // 加油站ID  默认：0
    private Integer stationId;

    // 交易类型
    private String tradeType;

    // 类型
    private String tradeSubType;

    // 审核状态  默认：0
    private String auditStatus;

    // 当前兑付申请的升数、金额  默认：0.00000
    private BigDecimal tradeNums;

    // 即购买卡时平台卖出油价  默认：0.000
    private BigDecimal price;

    // 即购买交易数量所花费的钱  默认：0.000
    private BigDecimal pay;

    // 即兑付、回购时的平台买入价  默认：0.000
    private BigDecimal tradePrice;

    // 交易得到的收入，包含收益，不含手续费，每次交易完成后，需要更新卡表上的总收入  默认：0.000
    private BigDecimal income;

    // 用户成本  默认：0.000
    private BigDecimal cost;

    // 每张卡优惠券抵扣  默认：0.000
    private BigDecimal cpnsMoney;

    // 每张卡油箱抵扣  默认：0.000
    private BigDecimal goilMoney;

    // 每张卡折扣抵扣  默认：0.000
    private BigDecimal discount;

    // 每张卡满减金额  默认：0.000
    private BigDecimal reduceMoney;

    // 此次交易收入-此次交易的成本（交易数量*购买单价），也即兑付得到的额外收益+赠送部分，回购交易为卡的总收益（所有交易的兑付收入之和，包含本次回购的 - 购买金额），付款生成卡时候的收益为0  默认：0.000
    private BigDecimal profit;

    // 收益(不包含支付优惠)  默认：0.000
    private BigDecimal pureProfit;

    // 固定收益  默认：0.000
    private BigDecimal fixedProfit;

    // 浮动收益  默认：0.000
    private BigDecimal floatProfit;

    // 手续费(元)  默认：0.000
    private BigDecimal fee;

    // 交易对方会员ID（转让业务时即转让方会员ID）  默认：0
    private Integer toMemberId;

    // 金额卡，卡表上的累计兑付金额 + 当前交易表上的兑付申请数量，每次交易成功需要更新到卡表上  默认：0.000
    private BigDecimal totalCashAmount;

    // 金额卡，卡表上的累计回购金额 + 当前交易表上的申请数量，每次交易成功需要更新到卡表上  默认：0.000
    private BigDecimal totalBuybackAmount;

    // 金额卡，卡表上的累计转让金额 + 当前交易表上的转让申请数量，每次交易成功需要更新到卡表上  默认：0.000
    private BigDecimal totalTransferAmount;

    // 金额卡，卡表上的剩余金额 - 当前交易的申请数量，每次交易成功需要更新到卡表上  默认：0.000
    private BigDecimal remainAmount;

    // 升数卡，卡表上的累计兑付升数 + 当前交易表上的兑付申请数量，每次交易成功需要更新到卡表上  默认：0.00000
    private BigDecimal totalCashLitre;

    // 升数卡，卡表上的累计回购升数 + 当前交易表上的回购申请数量，每次交易成功需要更新到卡表上  默认：0.00000
    private BigDecimal totalBuybackLitre;

    // 升数卡，卡表上的累计转让升数 + 当前交易表上的转让申请数量，每次交易成功需要更新到卡表上  默认：0.00000
    private BigDecimal totalTransferLitre;

    // 升数卡，卡表上的剩余升数 - 当前交易表上的申请数量，每次交易成功需要更新到卡表上  默认：0.00000
    private BigDecimal remainLitre;

    // 升数或金额  默认：0.00000
    private BigDecimal giveNums;

    // 交易备注
    private String remark;

    // 发票/小票附件
    private String invoiceAttachment;

    // 交易开始时间  默认：0000-00-00 00:00:00
    private Date tradeStartTime;

    // 交易结束时间  默认：0000-00-00 00:00:00
    private Date tradeEndTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    private String startTime;
    private String endTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradeBn() {
        return tradeBn;
    }

    public void setTradeBn(String tradeBn) {
        this.tradeBn = tradeBn;
    }

    public String getCardBn() {
        return cardBn;
    }

    public void setCardBn(String cardBn) {
        this.cardBn = cardBn;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeSubType() {
        return tradeSubType;
    }

    public void setTradeSubType(String tradeSubType) {
        this.tradeSubType = tradeSubType;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public BigDecimal getTradeNums() {
        return tradeNums;
    }

    public void setTradeNums(BigDecimal tradeNums) {
        this.tradeNums = tradeNums;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCpnsMoney() {
        return cpnsMoney;
    }

    public void setCpnsMoney(BigDecimal cpnsMoney) {
        this.cpnsMoney = cpnsMoney;
    }

    public BigDecimal getGoilMoney() {
        return goilMoney;
    }

    public void setGoilMoney(BigDecimal goilMoney) {
        this.goilMoney = goilMoney;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getPureProfit() {
        return pureProfit;
    }

    public void setPureProfit(BigDecimal pureProfit) {
        this.pureProfit = pureProfit;
    }

    public BigDecimal getFixedProfit() {
        return fixedProfit;
    }

    public void setFixedProfit(BigDecimal fixedProfit) {
        this.fixedProfit = fixedProfit;
    }

    public BigDecimal getFloatProfit() {
        return floatProfit;
    }

    public void setFloatProfit(BigDecimal floatProfit) {
        this.floatProfit = floatProfit;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(Integer toMemberId) {
        this.toMemberId = toMemberId;
    }

    public BigDecimal getTotalCashAmount() {
        return totalCashAmount;
    }

    public void setTotalCashAmount(BigDecimal totalCashAmount) {
        this.totalCashAmount = totalCashAmount;
    }

    public BigDecimal getTotalBuybackAmount() {
        return totalBuybackAmount;
    }

    public void setTotalBuybackAmount(BigDecimal totalBuybackAmount) {
        this.totalBuybackAmount = totalBuybackAmount;
    }

    public BigDecimal getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(BigDecimal totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getTotalCashLitre() {
        return totalCashLitre;
    }

    public void setTotalCashLitre(BigDecimal totalCashLitre) {
        this.totalCashLitre = totalCashLitre;
    }

    public BigDecimal getTotalBuybackLitre() {
        return totalBuybackLitre;
    }

    public void setTotalBuybackLitre(BigDecimal totalBuybackLitre) {
        this.totalBuybackLitre = totalBuybackLitre;
    }

    public BigDecimal getTotalTransferLitre() {
        return totalTransferLitre;
    }

    public void setTotalTransferLitre(BigDecimal totalTransferLitre) {
        this.totalTransferLitre = totalTransferLitre;
    }

    public BigDecimal getRemainLitre() {
        return remainLitre;
    }

    public void setRemainLitre(BigDecimal remainLitre) {
        this.remainLitre = remainLitre;
    }

    public BigDecimal getGiveNums() {
        return giveNums;
    }

    public void setGiveNums(BigDecimal giveNums) {
        this.giveNums = giveNums;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInvoiceAttachment() {
        return invoiceAttachment;
    }

    public void setInvoiceAttachment(String invoiceAttachment) {
        this.invoiceAttachment = invoiceAttachment;
    }

    public Date getTradeStartTime() {
        return tradeStartTime;
    }

    public void setTradeStartTime(Date tradeStartTime) {
        this.tradeStartTime = tradeStartTime;
    }

    public Date getTradeEndTime() {
        return tradeEndTime;
    }

    public void setTradeEndTime(Date tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}