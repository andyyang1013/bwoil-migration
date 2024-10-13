package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

public class OriginOperationPromotionShare {
    // 被推广人ID  默认：0
    private Long buyerMemberId;

    // 被推广会员编号
    private String buyerShopBn;

    // 被推广账号
    private String buyerMobile;

    // 被推广会员注册时间
    private Long buyerRegisterTime;

    // 被推广人注册来源
    private String buyerSource;

    // 是否实名 0：未实名，1：已实名  默认：0
    private String buyerIsTruename;

    //实名时间
    private Long buyerAuthTime;

    // 推广人ID  默认：0
    private Long promotionMemberId;

    // 推广会员编号
    private String promotionShopBn;

    // 推广账号
    private String promotionMobile;

    // 推广会员注册时间
    private Long promotionRegisterTime;

    // 渠道ID  默认：0
    private Long channelId;

    // 佣金类型，：1：注册佣金，2：邀请好友返现
    private String shareType;

    // 佣金金额  默认：0.00
    private Double profit;

    // 状态，：0：未达标，1：达标  默认：0
    private String achieveStatus;

    // 达标时间
    private Long achieveTime;

    // 结算状态，：0：未结算，1：已结算  默认：0
    private String commissionStatus;

    // 结算时间
    private Long commissionTime;

    // 创建时间
    private Long createTime;

    public Long getBuyerMemberId() {
        return buyerMemberId;
    }

    public void setBuyerMemberId(Long buyerMemberId) {
        this.buyerMemberId = buyerMemberId;
    }

    public String getBuyerShopBn() {
        return buyerShopBn;
    }

    public void setBuyerShopBn(String buyerShopBn) {
        this.buyerShopBn = buyerShopBn;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public Long getBuyerRegisterTime() {
        return buyerRegisterTime;
    }

    public void setBuyerRegisterTime(Long buyerRegisterTime) {
        this.buyerRegisterTime = buyerRegisterTime;
    }

    public String getBuyerSource() {
        return buyerSource;
    }

    public void setBuyerSource(String buyerSource) {
        this.buyerSource = buyerSource;
    }

    public String getBuyerIsTruename() {
        return buyerIsTruename;
    }

    public void setBuyerIsTruename(String buyerIsTruename) {
        this.buyerIsTruename = buyerIsTruename;
    }

    public Long getPromotionMemberId() {
        return promotionMemberId;
    }

    public void setPromotionMemberId(Long promotionMemberId) {
        this.promotionMemberId = promotionMemberId;
    }

    public String getPromotionShopBn() {
        return promotionShopBn;
    }

    public void setPromotionShopBn(String promotionShopBn) {
        this.promotionShopBn = promotionShopBn;
    }

    public String getPromotionMobile() {
        return promotionMobile;
    }

    public void setPromotionMobile(String promotionMobile) {
        this.promotionMobile = promotionMobile;
    }

    public Long getPromotionRegisterTime() {
        return promotionRegisterTime;
    }

    public void setPromotionRegisterTime(Long promotionRegisterTime) {
        this.promotionRegisterTime = promotionRegisterTime;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getAchieveStatus() {
        return achieveStatus;
    }

    public void setAchieveStatus(String achieveStatus) {
        this.achieveStatus = achieveStatus;
    }

    public Long getAchieveTime() {
        return achieveTime;
    }

    public void setAchieveTime(Long achieveTime) {
        this.achieveTime = achieveTime;
    }

    public String getCommissionStatus() {
        return commissionStatus;
    }

    public void setCommissionStatus(String commissionStatus) {
        this.commissionStatus = commissionStatus;
    }

    public Long getCommissionTime() {
        return commissionTime;
    }

    public void setCommissionTime(Long commissionTime) {
        this.commissionTime = commissionTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getBuyerAuthTime() {
        return buyerAuthTime;
    }

    public void setBuyerAuthTime(Long buyerAuthTime) {
        this.buyerAuthTime = buyerAuthTime;
    }
}
