package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

import java.util.Date;

public class OriginOperationBaseMember {
    // 被推荐人的id
    private Integer memberId;

    // 被推荐人名称
    private String buyerMemberName;

    // 被推广会员编号
    private String buyerShopBn;

    // 被推广账号
    private String buyerMobile;

    // 被推广会员注册时间
    private Date buyerRegisterTime;

    // 被推广人注册来源
    private String buyerSource;

    // 是否实名 0：未实名，1：已实名  默认：0
    private Integer buyerIsTruename;

    // 认证时间
    private Date buyerAuthTime;

    // 推荐人的id
    private Integer promotionMemberId;

    // 推荐人名称
    private String promotionMemberName;

    // 推广会员编号
    private String promotionShopBn;

    // 推广账号
    private String promotionMobile;

    // 推广会员注册时间
    private Date promotionRegisterTime;

    // 部门id
    private Integer channelDepartmentId;

    // 部门父类id
    private Integer channelDepartmentParentId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getBuyerMemberName() {
        return buyerMemberName;
    }

    public void setBuyerMemberName(String buyerMemberName) {
        this.buyerMemberName = buyerMemberName;
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

    public Integer getBuyerIsTruename() {
        return buyerIsTruename;
    }

    public void setBuyerIsTruename(Integer buyerIsTruename) {
        this.buyerIsTruename = buyerIsTruename;
    }

    public Date getBuyerAuthTime() {
        return buyerAuthTime;
    }

    public void setBuyerAuthTime(Date buyerAuthTime) {
        this.buyerAuthTime = buyerAuthTime;
    }

    public Integer getPromotionMemberId() {
        return promotionMemberId;
    }

    public void setPromotionMemberId(Integer promotionMemberId) {
        this.promotionMemberId = promotionMemberId;
    }

    public String getPromotionMemberName() {
        return promotionMemberName;
    }

    public void setPromotionMemberName(String promotionMemberName) {
        this.promotionMemberName = promotionMemberName;
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

    public Integer getChannelDepartmentId() {
        return channelDepartmentId;
    }

    public void setChannelDepartmentId(Integer channelDepartmentId) {
        this.channelDepartmentId = channelDepartmentId;
    }

    public Integer getChannelDepartmentParentId() {
        return channelDepartmentParentId;
    }

    public void setChannelDepartmentParentId(Integer channelDepartmentParentId) {
        this.channelDepartmentParentId = channelDepartmentParentId;
    }
}
