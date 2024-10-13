package com.bwoil.c2b.migration.steps.pay.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class WechatPayEntity {
    // id
    private Integer bankId;

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
    private BigDecimal limitEachAmount;

    // 单日限制开关
    private String limitDayEnable;

    // 单日限制金额
    private BigDecimal limitDayAmount;

    // 每月限制开关
    private String limitMonthEnable;

    // 每月限制金额
    private BigDecimal limitMonthAmount;

    // 开通银联在线支付开关
    private String openOnlinePay;

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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
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

    public BigDecimal getLimitEachAmount() {
        return limitEachAmount;
    }

    public void setLimitEachAmount(BigDecimal limitEachAmount) {
        this.limitEachAmount = limitEachAmount;
    }

    public String getLimitDayEnable() {
        return limitDayEnable;
    }

    public void setLimitDayEnable(String limitDayEnable) {
        this.limitDayEnable = limitDayEnable;
    }

    public BigDecimal getLimitDayAmount() {
        return limitDayAmount;
    }

    public void setLimitDayAmount(BigDecimal limitDayAmount) {
        this.limitDayAmount = limitDayAmount;
    }

    public String getLimitMonthEnable() {
        return limitMonthEnable;
    }

    public void setLimitMonthEnable(String limitMonthEnable) {
        this.limitMonthEnable = limitMonthEnable;
    }

    public BigDecimal getLimitMonthAmount() {
        return limitMonthAmount;
    }

    public void setLimitMonthAmount(BigDecimal limitMonthAmount) {
        this.limitMonthAmount = limitMonthAmount;
    }

    public String getOpenOnlinePay() {
        return openOnlinePay;
    }

    public void setOpenOnlinePay(String openOnlinePay) {
        this.openOnlinePay = openOnlinePay;
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
}