package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class OilCardEntity {

    // 卡ID
    private Integer cardId;

    // 订单号
    private String orderId;

    // 卡号
    private String cardBn;

    // 名称
    private String cardName;

    // 会员ID
    private Integer memberId;

    // 卡类型已中台为准
    private String cardType;

    // 产品属性（litre: 储油产品、amount: 金额产品）
    private String prodAttr;

    // 地区名称
    private String regionName;

    // 地区ID
    private Integer regionId;

    // 油品名称
    private String oilName;

    // 油品ID
    private Integer oilId;

    // 油价  默认：0.00
    private BigDecimal oilPrice;

    // 状态 0: 冻结 1：正常
    private String cardStatus;

    // 交易状态 0: 储油中 1：已兑付 2: 强制退款
    private String transStatus;

    // 总额,金额或升  默认：0.00
    private BigDecimal totalAmount;

    // 冻结,金额或升  默认：0.00
    private BigDecimal freezeAmount;

    //可对付额度
    private BigDecimal canSaleAmount;

    // 已兑付,金额或升  默认：0.00
    private BigDecimal saleAmount;

    // 购买时总金额  默认：0.00
    private BigDecimal buyAmount;

    // 实际支付金额  默认：0.00
    private BigDecimal payAmout;

    // 优惠券抵扣金额  默认：0.00
    private BigDecimal cpnsMoney;

    // 优惠券抵扣升数  默认：0.00
    private BigDecimal cpnsLiter;

    // 油箱抵扣  默认：0.00
    private BigDecimal oilDeduction;

    // 订单满减金额  默认：0.00
    private BigDecimal reduceMoney;

    // 赠送折扣
    private BigDecimal profitDiscount;

    // 折扣
    private BigDecimal ruleDiscount;

    // 年化收益率(%)  默认：0.00
    private BigDecimal incomeRate;

    // 浮动收益(%)  默认：0.00
    private BigDecimal floatRate;

    // 管理费收费比(%)  默认：0.00
    private String saleRate;

    // 卡张数  默认：1
    private Integer cardCnt;

    // 期限(月)
    private Integer peroid;

    // 期数
    private Integer term;

    // 锁定截止日期
    private String unlockDate;

    // 强制兑付日
    private String forceSaleDate;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    private String recentlySaleDate;
    
    private  BigDecimal remainLitre ;
    private  BigDecimal totalCashLitre;

    private String termUnit;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getProdAttr() {
        return prodAttr;
    }

    public void setProdAttr(String prodAttr) {
        this.prodAttr = prodAttr;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getCanSaleAmount() {
        return canSaleAmount;
    }

    public void setCanSaleAmount(BigDecimal canSaleAmount) {
        this.canSaleAmount = canSaleAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getPayAmout() {
        return payAmout;
    }

    public void setPayAmout(BigDecimal payAmout) {
        this.payAmout = payAmout;
    }

    public BigDecimal getCpnsMoney() {
        return cpnsMoney;
    }

    public void setCpnsMoney(BigDecimal cpnsMoney) {
        this.cpnsMoney = cpnsMoney;
    }

    public BigDecimal getCpnsLiter() {
        return cpnsLiter;
    }

    public void setCpnsLiter(BigDecimal cpnsLiter) {
        this.cpnsLiter = cpnsLiter;
    }

    public BigDecimal getOilDeduction() {
        return oilDeduction;
    }

    public void setOilDeduction(BigDecimal oilDeduction) {
        this.oilDeduction = oilDeduction;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public BigDecimal getProfitDiscount() {
        return profitDiscount;
    }

    public void setProfitDiscount(BigDecimal profitDiscount) {
        this.profitDiscount = profitDiscount;
    }

    public BigDecimal getRuleDiscount() {
        return ruleDiscount;
    }

    public void setRuleDiscount(BigDecimal ruleDiscount) {
        this.ruleDiscount = ruleDiscount;
    }

    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(BigDecimal incomeRate) {
        this.incomeRate = incomeRate;
    }

    public BigDecimal getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(BigDecimal floatRate) {
        this.floatRate = floatRate;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
    }

    public Integer getCardCnt() {
        return cardCnt;
    }

    public void setCardCnt(Integer cardCnt) {
        this.cardCnt = cardCnt;
    }

    public Integer getPeroid() {
        return peroid;
    }

    public void setPeroid(Integer peroid) {
        this.peroid = peroid;
    }

    public String getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(String unlockDate) {
        this.unlockDate = unlockDate;
    }

    public String getForceSaleDate() {
        return forceSaleDate;
    }

    public void setForceSaleDate(String forceSaleDate) {
        this.forceSaleDate = forceSaleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRecentlySaleDate() {
        return recentlySaleDate;
    }

    public void setRecentlySaleDate(String recentlySaleDate) {
        this.recentlySaleDate = recentlySaleDate;
    }

	public BigDecimal getRemainLitre() {
		return remainLitre;
	}

	public void setRemainLitre(BigDecimal remainLitre) {
		this.remainLitre = remainLitre;
	}

	public BigDecimal getTotalCashLitre() {
		return totalCashLitre;
	}

	public void setTotalCashLitre(BigDecimal totalCashLitre) {
		this.totalCashLitre = totalCashLitre;
	}

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }
}