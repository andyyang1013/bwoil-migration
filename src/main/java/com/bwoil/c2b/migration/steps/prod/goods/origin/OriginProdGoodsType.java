package com.bwoil.c2b.migration.steps.prod.goods.origin;


import java.math.BigDecimal;

public class OriginProdGoodsType {
    private Integer typeId;
    private String name;
    private String typeBn;
    private String alias;
    private String type;
    private BigDecimal discountMax;
    private String dailyLimit;
    private String yearLimit;
    private String holdLimit;
    private String descapp;
    private String descAndroid;
    private String pShortDesc;
    private String pHonourDesc;
    private String keywordImage;
    private String label;
    private Integer sort;
    private String remarks;
    private String apptypeList;
    private String disabled;  // 默认：false

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeBn() {
        return typeBn;
    }

    public void setTypeBn(String typeBn) {
        this.typeBn = typeBn;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDiscountMax() {
        return discountMax;
    }

    public void setDiscountMax(BigDecimal discountMax) {
        this.discountMax = discountMax;
    }

    public String getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(String dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public String getYearLimit() {
        return yearLimit;
    }

    public void setYearLimit(String yearLimit) {
        this.yearLimit = yearLimit;
    }

    public String getHoldLimit() {
        return holdLimit;
    }

    public void setHoldLimit(String holdLimit) {
        this.holdLimit = holdLimit;
    }

    public String getDescapp() {
        return descapp;
    }

    public void setDescapp(String descapp) {
        this.descapp = descapp;
    }

    public String getDescAndroid() {
        return descAndroid;
    }

    public void setDescAndroid(String descAndroid) {
        this.descAndroid = descAndroid;
    }

    public String getpShortDesc() {
        return pShortDesc;
    }

    public void setpShortDesc(String pShortDesc) {
        this.pShortDesc = pShortDesc;
    }

    public String getpHonourDesc() {
        return pHonourDesc;
    }

    public void setpHonourDesc(String pHonourDesc) {
        this.pHonourDesc = pHonourDesc;
    }

    public String getKeywordImage() {
        return keywordImage;
    }

    public void setKeywordImage(String keywordImage) {
        this.keywordImage = keywordImage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getApptypeList() {
        return apptypeList;
    }

    public void setApptypeList(String apptypeList) {
        this.apptypeList = apptypeList;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
