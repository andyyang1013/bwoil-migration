package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationPromotionReport {

    // 报表名称
    private String reportName;
    // 推荐人的id
    private Integer memberId;

    // 推广人账户
    private String channelName;

    // 打款状态(0未打款, 1已打款)
    private Integer payStatus;

    // 报表创建日期
    private Date createTime;

    // 报表内容日期
    private Date reportContentTime;

    // 结算佣金
    private BigDecimal shareMount;

    // 购买实付总金额
    private BigDecimal realPayAmount;

    // 购买实付总金额
    private BigDecimal totalAmount;

    // 渠道类型
    private String channelType;

    //推广人编号
    private String promotionShopBn;


    // 时间类型，0为购买时间，1为用户注册时间
    private Integer dateType;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReportContentTime() {
        return reportContentTime;
    }

    public void setReportContentTime(Date reportContentTime) {
        this.reportContentTime = reportContentTime;
    }

    public BigDecimal getShareMount() {
        return shareMount;
    }

    public void setShareMount(BigDecimal shareMount) {
        this.shareMount = shareMount;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getPromotionShopBn() {
        return promotionShopBn;
    }

    public void setPromotionShopBn(String promotionShopBn) {
        this.promotionShopBn = promotionShopBn;
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }
}
