package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationBaseOrder {

    // 订单ID
    private String id;

    // 会员用户名
    private Integer memberId;

    // 会员手机号
    private String mobile;

    // 货品ID
    private Integer productId;

    // 货品名称
    private String productName;

    // 地区ID
    private Integer regionId;

    // 地区名称
    private String regionName;

    // 油品ID
    private Integer oilId;

    // 油品名称
    private String oilName;

    // 最近一条油品价格
    private BigDecimal oilPrice;

    // 订单状态:active,dead,finish  默认：active
    private String orderStatus;

    // 付款状态:0,1,2,3,4,5   默认：0
    private String payStatus;

    // 支付方式  默认：0
    private String payWay;

    // 支付时间
    private Date payTime;

    // 是否还有保价费:Y,N  默认：N
    private String isProtect;

    // 保价费  默认：0.00
    private BigDecimal costProtect;

    // 购买张数
    private Integer totalCnt;

    // 期数
    private Integer term;

    // 订单总金额  默认：0.00
    private BigDecimal totalAmount;

    // 升数  默认：0.00
    private BigDecimal totalLitre;

    // 订单实际支付金额  默认：0.00
    private BigDecimal realPayAmount;

    // 订单现金券减免  默认：0.00
    private BigDecimal cpnsMoney;

    // 订单油箱抵用金额  默认：0.00
    private BigDecimal goilMoney;

    // 订单满减金额  默认：0.00
    private BigDecimal reduceMoney;

    // 订单折扣金额  默认：0.00
    private BigDecimal discountMoney;

    // 红包折扣金额  默认：0.00
    private BigDecimal redpacketMoney;

    // 优惠券id array[]
    private String cpsIds;

    // 推广渠道ID  默认：0
    private Integer channelId;

    // 广告来源
    private String adSource;

    // 订单备注图标  默认：b1
    private String markType;

    // 订单备注
    private String markText;

    // 支付单号
    private String payNo;

    // 第三方订单号
    private String orderReferNo;

    // 是否通知第三方:Y,N  默认：N
    private String referNotice;

    // 平台来源:pc,app,wap,weixin  默认：pc
    private String source;

    // 设备信息
    private String devinfo;

    // 线下交易号
    private String linepayment;

    // IP地址
    private String ip;

    // 部门id
    private Integer channelDepartmentId;

    // 部门父类id
    private Integer channelDepartmentParentId;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    // 商品类型
    private String productCategory;

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getIsProtect() {
        return isProtect;
    }

    public void setIsProtect(String isProtect) {
        this.isProtect = isProtect;
    }

    public BigDecimal getCostProtect() {
        return costProtect;
    }

    public void setCostProtect(BigDecimal costProtect) {
        this.costProtect = costProtect;
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalLitre() {
        return totalLitre;
    }

    public void setTotalLitre(BigDecimal totalLitre) {
        this.totalLitre = totalLitre;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public BigDecimal getCpnsMoney() {
        return cpnsMoney;
    }

    public void setCpnsMoney(BigDecimal cpnsMoney) {
        this.cpnsMoney = cpnsMoney;
    }

    public BigDecimal getGoilMoney() {
        return goilMoney;
    }

    public void setGoilMoney(BigDecimal goilMoney) {
        this.goilMoney = goilMoney;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getRedpacketMoney() {
        return redpacketMoney;
    }

    public void setRedpacketMoney(BigDecimal redpacketMoney) {
        this.redpacketMoney = redpacketMoney;
    }

    public String getCpsIds() {
        return cpsIds;
    }

    public void setCpsIds(String cpsIds) {
        this.cpsIds = cpsIds;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getAdSource() {
        return adSource;
    }

    public void setAdSource(String adSource) {
        this.adSource = adSource;
    }

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOrderReferNo() {
        return orderReferNo;
    }

    public void setOrderReferNo(String orderReferNo) {
        this.orderReferNo = orderReferNo;
    }

    public String getReferNotice() {
        return referNotice;
    }

    public void setReferNotice(String referNotice) {
        this.referNotice = referNotice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDevinfo() {
        return devinfo;
    }

    public void setDevinfo(String devinfo) {
        this.devinfo = devinfo;
    }

    public String getLinepayment() {
        return linepayment;
    }

    public void setLinepayment(String linepayment) {
        this.linepayment = linepayment;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
