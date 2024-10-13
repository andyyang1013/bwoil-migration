package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstationCashInfo {
    // 交易ID(主键)
    private Integer tradeId;

    // 加油扣款编号
    private String gasCashBn;

    // 发票号
    private String invoiceBn;

    // 实际加油升数
    private BigDecimal actualLitre;

    // 实际加油每升单价
    private BigDecimal actualPrice;

    // 实际加油金额
    private BigDecimal actualAmount;

    // 会员ID
    private Integer memberId;

    // 操作人
    private Integer userId;

    // 操作员ID
    private Integer operatorId;

    // 操作员
    private String operatorName;

    // 油品名称
    private String oilName;

    // 枪号
    private String gunNum;

    // 加油站ID
    private Integer stationId;

    // 来源  默认：pc
    private String source;

    // 加油类型  默认：fuel
    private String orderType;

    // 扣款时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getGasCashBn() {
        return gasCashBn;
    }

    public void setGasCashBn(String gasCashBn) {
        this.gasCashBn = gasCashBn;
    }

    public String getInvoiceBn() {
        return invoiceBn;
    }

    public void setInvoiceBn(String invoiceBn) {
        this.invoiceBn = invoiceBn;
    }

    public BigDecimal getActualLitre() {
        return actualLitre;
    }

    public void setActualLitre(BigDecimal actualLitre) {
        this.actualLitre = actualLitre;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public String getGunNum() {
        return gunNum;
    }

    public void setGunNum(String gunNum) {
        this.gunNum = gunNum;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
