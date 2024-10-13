package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class RedeemEntity {

    private Long id;
    // 交易编号
    private Long tradeBn;

    // 储油通卡号
    private String cardBn;

    // 产品名称
    private String productName;

    // 卡类型已中台为准
    private String cardType;

    // 会员Id
    private Integer memberId;

    // 会员名称
    private String memberName;

    // 会员手机号
    private String mobile;

    // 购买时间  默认：0000-00-00 00:00:00
    private String buyTime;

    // 购买金额  默认：0.00
    private BigDecimal buyMoney;

    // 购买升数  默认：0.00
    private BigDecimal buyLiter;

    // 购买油价  默认：0.00
    private BigDecimal buyPrice;

    // 交易类型 schedual: 定时任务 customer: 客户主动 adm: 管理员强制赎回
    private String tradeType;

    // 兑付额度:金额产品对应金额，升数产品对应升数  默认：0.00
    private BigDecimal saleAmount;

    // 兑付金额  默认：0.00
    private BigDecimal saleMoney;

    // 兑付升数  默认：0.00
    private BigDecimal saleLiter;

    // 兑付油价  默认：0.00
    private BigDecimal oilPrice;

    // 已兑现金券抵扣  默认：0.00
    private BigDecimal cpnsMoney;

    // 已兑油箱抵扣  默认：0.00
    private BigDecimal profitOil;

    // 已兑折扣金额  默认：0.00
    private BigDecimal discountMoney;

    // 已兑满减金额  默认：0.00
    private BigDecimal reduceMoney;

    // 已兑赠送金额  默认：0.00
    private BigDecimal profitMoney;

    // 已兑赠送升数  默认：0.00
    private BigDecimal profitLiter;

    // 已兑固定利息  默认：0.00
    private BigDecimal fixedInteret;

    // 已兑浮动收益  默认：0.00
    private BigDecimal floatInteret;

    // 手续费(元)  默认：0.00
    private BigDecimal fee;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTradeBn() {
        return tradeBn;
    }

    public void setTradeBn(Long tradeBn) {
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(BigDecimal buyMoney) {
        this.buyMoney = buyMoney;
    }

    public BigDecimal getBuyLiter() {
        return buyLiter;
    }

    public void setBuyLiter(BigDecimal buyLiter) {
        this.buyLiter = buyLiter;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public BigDecimal getSaleLiter() {
        return saleLiter;
    }

    public void setSaleLiter(BigDecimal saleLiter) {
        this.saleLiter = saleLiter;
    }

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public BigDecimal getCpnsMoney() {
        return cpnsMoney;
    }

    public void setCpnsMoney(BigDecimal cpnsMoney) {
        this.cpnsMoney = cpnsMoney;
    }

    public BigDecimal getProfitOil() {
        return profitOil;
    }

    public void setProfitOil(BigDecimal profitOil) {
        this.profitOil = profitOil;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public BigDecimal getProfitMoney() {
        return profitMoney;
    }

    public void setProfitMoney(BigDecimal profitMoney) {
        this.profitMoney = profitMoney;
    }

    public BigDecimal getProfitLiter() {
        return profitLiter;
    }

    public void setProfitLiter(BigDecimal profitLiter) {
        this.profitLiter = profitLiter;
    }

    public BigDecimal getFixedInteret() {
        return fixedInteret;
    }

    public void setFixedInteret(BigDecimal fixedInteret) {
        this.fixedInteret = fixedInteret;
    }

    public BigDecimal getFloatInteret() {
        return floatInteret;
    }

    public void setFloatInteret(BigDecimal floatInteret) {
        this.floatInteret = floatInteret;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

}