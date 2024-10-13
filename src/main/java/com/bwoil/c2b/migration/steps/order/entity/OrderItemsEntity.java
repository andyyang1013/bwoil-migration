package com.bwoil.c2b.migration.steps.order.entity;

public class OrderItemsEntity {

    private Long itemId;

    // 订单号
    private String orderId;

    // 货品ID  默认：0
    private Integer productId;

    // 货品编号
    private String productBn;

    // 货品名称
    private String productName;

    // 属性（1：大众储油；2：云储油）
    private String productAttr;

    // 商品类型ID
    private Integer typeId;

    // 类型编号
    private String typeBn;

    // 类型别名
    private String typeAlisa;

    // 规则明细id
    private Integer ruleDetailId;

    // 兑付规则JSON
    private String actionSolution;

    // 明细商品的规格属性JSON
    private String addon;

    // (兼容旧数据)明细商品类型product,pkg,gift,adjunct,transfer  默认：product
    private String itemType;

    // (兼容旧数据)商品形态:virtual,actual,transfer  默认：virtual
    private String cardType;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductBn() {
        return productBn;
    }

    public void setProductBn(String productBn) {
        this.productBn = productBn;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeBn() {
        return typeBn;
    }

    public void setTypeBn(String typeBn) {
        this.typeBn = typeBn;
    }

    public String getTypeAlisa() {
        return typeAlisa;
    }

    public void setTypeAlisa(String typeAlisa) {
        this.typeAlisa = typeAlisa;
    }

    public Integer getRuleDetailId() {
        return ruleDetailId;
    }

    public void setRuleDetailId(Integer ruleDetailId) {
        this.ruleDetailId = ruleDetailId;
    }

    public String getActionSolution() {
        return actionSolution;
    }

    public void setActionSolution(String actionSolution) {
        this.actionSolution = actionSolution;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}