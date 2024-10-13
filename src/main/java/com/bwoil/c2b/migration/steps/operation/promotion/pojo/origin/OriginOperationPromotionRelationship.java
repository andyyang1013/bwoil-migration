package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

public class OriginOperationPromotionRelationship {
    // 推荐人的id
    private Integer promotionMemberId;

    // 被推荐人的id
    private Integer memberId;

    // 被推荐人的id    -1删除, 0正常
    private Integer status;

    // 被推荐人和推荐人所属的渠道id
    private Integer channelId;

    // 被推荐人和推荐人所属的收益时效(天)
    private Integer duration;

    // 被推广会员编号
    private String buyerShopBn;

    // 被推广账号
    private String buyerMobile;

    // 是否实名 0：未实名，1：已实名
    private String buyerIsTruename;

    // 被推广会员注册时间
    private Long buyerRegisterTime;

    // 被推广人注册来源
    private String buyerSource;

    // 推广会员编号
    private String promotionShopBn;

    // 推广账号
    private String promotionMobile;

    // 推广会员注册时间
    private Long promotionRegisterTime;

    public Integer getPromotionMemberId() {
        return promotionMemberId;
    }

    public void setPromotionMemberId(Integer promotionMemberId) {
        this.promotionMemberId = promotionMemberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getBuyerIsTruename() {
        return buyerIsTruename;
    }

    public void setBuyerIsTruename(String buyerIsTruename) {
        this.buyerIsTruename = buyerIsTruename;
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
}
