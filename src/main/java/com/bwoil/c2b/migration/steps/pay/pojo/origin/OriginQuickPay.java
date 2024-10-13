package com.bwoil.c2b.migration.steps.pay.pojo.origin;


import java.util.Date;

public class OriginQuickPay {

    // id
    private Integer setId;

    // 银行名称
    private String bankName;

    // 卡种类
    private String cardType;

    // 银行标识
    private String bankBn;

    // 银行大LOGO
    private String bigLogoId;

    // 银行小LOGO
    private String smallLogoId;

    // 每笔限制开关
    private String limitEachEnable;

    // 每笔限制金额
    private String limitEachAmount;

    // 单日限制开关
    private String limitDayEnable;

    // 单日限制金额
    private String limitDayAmount;

    // 每月限制开关
    private String limitMonthEnable;

    // 每月限制金额
    private String limitMonthAmount;

    // 开通银联在线支付开关
    private String openOnlinePay;

    // 宝付协议类型(1.普通认证2.协议认证)
    private String baofuType;

    // 银行排序
    private Integer bankOrder;

    // 是否启用该配置
    private String isUsed;

    // 状态(1:停用 0:启用)
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：0000-00-00 00:00:00
    private Date lastUpdateTime;

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBankBn() {
        return bankBn;
    }

    public void setBankBn(String bankBn) {
        this.bankBn = bankBn;
    }

    public String getBigLogoId() {
        return bigLogoId;
    }

    public void setBigLogoId(String bigLogoId) {
        this.bigLogoId = bigLogoId;
    }

    public String getSmallLogoId() {
        return smallLogoId;
    }

    public void setSmallLogoId(String smallLogoId) {
        this.smallLogoId = smallLogoId;
    }

    public String getLimitEachEnable() {
        return limitEachEnable;
    }

    public void setLimitEachEnable(String limitEachEnable) {
        this.limitEachEnable = limitEachEnable;
    }


    public String getLimitDayEnable() {
        return limitDayEnable;
    }

    public void setLimitDayEnable(String limitDayEnable) {
        this.limitDayEnable = limitDayEnable;
    }


    public String getLimitMonthEnable() {
        return limitMonthEnable;
    }

    public void setLimitMonthEnable(String limitMonthEnable) {
        this.limitMonthEnable = limitMonthEnable;
    }

    public String getOpenOnlinePay() {
        return openOnlinePay;
    }

    public void setOpenOnlinePay(String openOnlinePay) {
        this.openOnlinePay = openOnlinePay;
    }

    public String getBaofuType() {
        return baofuType;
    }

    public void setBaofuType(String baofuType) {
        this.baofuType = baofuType;
    }

    public Integer getBankOrder() {
        return bankOrder;
    }

    public void setBankOrder(Integer bankOrder) {
        this.bankOrder = bankOrder;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLimitEachAmount() {
        return limitEachAmount;
    }

    public void setLimitEachAmount(String limitEachAmount) {
        this.limitEachAmount = limitEachAmount;
    }

    public String getLimitDayAmount() {
        return limitDayAmount;
    }

    public void setLimitDayAmount(String limitDayAmount) {
        this.limitDayAmount = limitDayAmount;
    }

    public String getLimitMonthAmount() {
        return limitMonthAmount;
    }

    public void setLimitMonthAmount(String limitMonthAmount) {
        this.limitMonthAmount = limitMonthAmount;
    }
}