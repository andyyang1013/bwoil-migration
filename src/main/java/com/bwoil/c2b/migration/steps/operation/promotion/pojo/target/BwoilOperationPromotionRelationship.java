package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;


import java.util.Date;

public class BwoilOperationPromotionRelationship {

    // 推荐人的id
    private Integer promotionMemberId;

    // 被推荐人的id
    private Integer buyerMemberId;

    // 被推荐人的id    -1删除, 0正常
    private Integer status;

    // 被推荐人和推荐人所属的渠道id
    private Integer channelId;

    // 被推荐人和推荐人所属的收益时效(天)
    private Integer duration;

    // 收益时效的截止日期
    private Date durationModify;

    // 被推广会员编号
    private String buyerShopBn;

    // 被推广账号
    private String buyerMobile;

    // 是否实名 0：未实名，1：已实名
    private Integer buyerIsTruename;

    // 被推广会员注册时间
    private Date buyerRegisterTime;

    // 被推广人注册来源
    private String buyerSource;

    // 推广会员编号
    private String promotionShopBn;

    // 推广账号
    private String promotionMobile;

    // 推广会员注册时间
    private Date promotionRegisterTime;

    public Integer getPromotionMemberId() {
        return promotionMemberId;
    }

    public void setPromotionMemberId(Integer promotionMemberId) {
        this.promotionMemberId = promotionMemberId;
    }

    public Integer getBuyerMemberId() {
        return buyerMemberId;
    }

    public void setBuyerMemberId(Integer buyerMemberId) {
        this.buyerMemberId = buyerMemberId;
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

    public Date getDurationModify() {
        return durationModify;
    }

    public void setDurationModify(Date durationModify) {
        this.durationModify = durationModify;
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

    public Integer getBuyerIsTruename() {
        return buyerIsTruename;
    }

    public void setBuyerIsTruename(Integer buyerIsTruename) {
        this.buyerIsTruename = buyerIsTruename;
    }

    public Date getBuyerRegisterTime() {
        return buyerRegisterTime;
    }

    public void setBuyerRegisterTime(Date buyerRegisterTime) {
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

    public Date getPromotionRegisterTime() {
        return promotionRegisterTime;
    }

    public void setPromotionRegisterTime(Date promotionRegisterTime) {
        this.promotionRegisterTime = promotionRegisterTime;
    }
}
