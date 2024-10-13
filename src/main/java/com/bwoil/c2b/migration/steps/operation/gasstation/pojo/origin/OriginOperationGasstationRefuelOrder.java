package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.math.BigDecimal;

/**
 * @Integer 2019/3/7 10:37
 * @Author wenyue
 * @Description:
 **/
public class OriginOperationGasstationRefuelOrder {


    // 免单id(自增)
    private Integer fuelOrderId;
    // 订单号
    private String orderId;

    // 班次id
    private Integer shiftId;

    // 加油站id
    private Integer stationId;

    // 收银员ID
    private Integer staffId;

    // 油工ID  默认：0
    private String yougongId;

    // 交易单号b2c_member_advance.payment_id
    private String paymentId;

    // 加油会员ID
    private Integer memberId;

    // 枪号
    private String gunNum;

    // 付款金额
    private BigDecimal payMoney;

    // 加油小票号码
    private String invoiceNumber;

    // 油品协议价  默认：0.00
    private BigDecimal agreementsMoney;

    // you品名
    private String oilNumber;

    // 油品活动价  默认：0.00
    private BigDecimal activityMoney;

    // 油量（升）  默认：0.00
    private BigDecimal oilTotal;

    // 油价（元/升）  默认：0.00
    private BigDecimal oilPrice;

    // 短信交易码
    private String smsCode;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Integer created;

    // 是否已对账,创建结算报表即已对账1,回滚草稿则为未对账0  默认：0
    private String settlement;

    // 小票打印状态，  0为打印失败 1为打印成功  默认：0
    private String printStatus;

    // 小票打印奖励金额  默认：0.00
    private BigDecimal bonus;

    // 来源  默认：pc
    private String source;

    // scan=>扫码,fuel=>免单  默认：fuel
    private String type;

    // 实收用户金额  默认：0.00
    private BigDecimal paidSubscribers;

    // 优惠金额  默认：0.00
    private BigDecimal discountAmount;

    // 直付券金额  默认：0.00
    private BigDecimal cpnsMoney;

    // 渠道折扣  默认：10
    private String paidDiscount;


    // 微信直付折扣  默认：10
    private String weixinDiscount;

    // 结算方式  默认：erp
    private String clearingType;

    // 核算状态  默认：0
    private String accountingStatus;

    // 优惠内容
    private String discountText;

    // 协议金额结算方式  默认1每笔结算, 2线下结算
    private String agreementPayMethod;

    // 手续费金额结算方式  默认1每笔结算, 2线下结算
    private String feePayMethod;

    public Integer getFuelOrderId() {
        return fuelOrderId;
    }

    public void setFuelOrderId(Integer fuelOrderId) {
        this.fuelOrderId = fuelOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getYougongId() {
        return yougongId;
    }

    public void setYougongId(String yougongId) {
        this.yougongId = yougongId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getGunNum() {
        return gunNum;
    }

    public void setGunNum(String gunNum) {
        this.gunNum = gunNum;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getAgreementsMoney() {
        return agreementsMoney;
    }

    public void setAgreementsMoney(BigDecimal agreementsMoney) {
        this.agreementsMoney = agreementsMoney;
    }

    public String getOilNumber() {
        return oilNumber;
    }

    public void setOilNumber(String oilNumber) {
        this.oilNumber = oilNumber;
    }

    public BigDecimal getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(BigDecimal activityMoney) {
        this.activityMoney = activityMoney;
    }

    public BigDecimal getOilTotal() {
        return oilTotal;
    }

    public void setOilTotal(BigDecimal oilTotal) {
        this.oilTotal = oilTotal;
    }

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(String printStatus) {
        this.printStatus = printStatus;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPaidSubscribers() {
        return paidSubscribers;
    }

    public void setPaidSubscribers(BigDecimal paidSubscribers) {
        this.paidSubscribers = paidSubscribers;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getCpnsMoney() {
        return cpnsMoney;
    }

    public void setCpnsMoney(BigDecimal cpnsMoney) {
        this.cpnsMoney = cpnsMoney;
    }

    public String getPaidDiscount() {
        return paidDiscount;
    }

    public void setPaidDiscount(String paidDiscount) {
        this.paidDiscount = paidDiscount;
    }

    public String getWeixinDiscount() {
        return weixinDiscount;
    }

    public void setWeixinDiscount(String weixinDiscount) {
        this.weixinDiscount = weixinDiscount;
    }

    public String getClearingType() {
        return clearingType;
    }

    public void setClearingType(String clearingType) {
        this.clearingType = clearingType;
    }

    public String getAccountingStatus() {
        return accountingStatus;
    }

    public void setAccountingStatus(String accountingStatus) {
        this.accountingStatus = accountingStatus;
    }

    public String getDiscountText() {
        return discountText;
    }

    public void setDiscountText(String discountText) {
        this.discountText = discountText;
    }

    public String getAgreementPayMethod() {
        return agreementPayMethod;
    }

    public void setAgreementPayMethod(String agreementPayMethod) {
        this.agreementPayMethod = agreementPayMethod;
    }

    public String getFeePayMethod() {
        return feePayMethod;
    }

    public void setFeePayMethod(String feePayMethod) {
        this.feePayMethod = feePayMethod;
    }
}
