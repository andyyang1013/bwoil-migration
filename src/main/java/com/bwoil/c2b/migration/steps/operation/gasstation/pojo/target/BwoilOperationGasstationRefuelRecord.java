package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstationRefuelRecord {

    private String recordId;

    // 会员编号
    private String shopBn;
    // 加油站名称
    private String stationName;
    //收银员用户名：该条记录中加油站的收银员用户名；
    private String cashierName;
    // 打款编号  默认：0
    private String payBn;

    // 加油总金额  默认：0.00   未优惠前得金额
    private BigDecimal totalAmount;

    // 实收用户金额  默认：0.00  加油总金额减去优惠金额
    private BigDecimal paidSubscribers;

    // 支付预存款金额  默认：0.00 该笔加油记录从用户预存款中扣减的金额
    private BigDecimal advenceAmount;

    // 支付加油卡金额  默认：0.00 购买加油卡的实付金额
    private BigDecimal refuelAmount;

    // 优惠金额  默认：0.00    优惠券减免金额
    private BigDecimal discountAmount;

    // 直付券支付  默认：0.00
    private BigDecimal cpnsMoney;

    // 协议金额  默认：0.00    平台和加油站之间的协议金额
    private BigDecimal agreementsMoney;

    // 活动优惠金额  默认：0.00  优惠活动减免的金额
    private BigDecimal activityMoney;

    // 油站挂牌价(元/升)  默认：0.00
    private BigDecimal oilPrice;

    // 加油升数  默认：0.00
    private BigDecimal oilTotal;

    private String status;

    private String payStatus;

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    // 油站ID
    private Integer stationId;

    // 下单时间
    private Date createtime;

    // 过期时间
    private Date expired;

    // 会员用户名
    private Integer memberId;

    // 手机号码
    private String mobilephone;

    // 验证时间
    private Date confirm;

    // 验证码
    private String confirmCode;

    // 验证操作人ID
    private Integer operatorId;

    // 验证操作人
    private String operatorName;

    // 油枪号
    private String gun;

    // 油品名称
    private String oilName;

    // 加油员工ID  默认：0
    private Integer oilersId;

    // 加油员工用户名(这个不是收银员把?)
    private String oilersName;

    // 下单当前的班次ID
    private Integer shiftId;

    private String ip;

    private String taxCompany;

    private String source;

    private String paymentId;

    private String paymethod;

    private String effective;

    private String paidDiscount;

    private String weixinDiscount;

    private String clearingType;

    //@ApiModelProperty(value="银行转账状态, 默认success支付成功, failed支付失败, 默认other是ERP打款, pending处理中，ready准备中", dataType="String")
    private String clearingStatus;

    //    @ApiModelProperty(value="银行转账失败信息", dataType="String")
    private String clearingErrorMsg;

    //    @ApiModelProperty(value="优惠信息", dataType="String")
    private String discountText;

    //    @ApiModelProperty(value="银行转账重复次数", dataType="Integer")
    private Integer bankPayNum;

    //    @ApiModelProperty(value="是否需要打印小票, 默认1需要, 2不需要", dataType="String")
    private String allowCprint;

    //    @ApiModelProperty(value="是否已打印小票, 默认0否, 1已打印", dataType="String")
    private String isCprint;

    //    @ApiModelProperty(value="手续费-结算方式  默认1每笔结算, 2线下结算", dataType="String")
    private String feePayMethod;

    //    @ApiModelProperty(value="协议折扣-结算方式  默认1每笔结算, 2线下结算", dataType="String")
    private String agreementPayMethod;

    //    @ApiModelProperty(value="结算手续费", dataType="BigDecimal")
    private BigDecimal feesAmount;

    // 结算金额  扣除结算手续费后打给加油站的金额
//    @ApiModelProperty(value="结算金额", dataType="BigDecimal")
    private BigDecimal clearingAmount;

    //    @ApiModelProperty(value="加油类型, litre定升加油, 默认amount定额加油", dataType="String")
    private String refuelType;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getShopBn() {
        return shopBn;
    }

    public void setShopBn(String shopBn) {
        this.shopBn = shopBn;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }


    public String getPayBn() {
        return payBn;
    }

    public void setPayBn(String payBn) {
        this.payBn = payBn;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidSubscribers() {
        return paidSubscribers;
    }

    public void setPaidSubscribers(BigDecimal paidSubscribers) {
        this.paidSubscribers = paidSubscribers;
    }

    public BigDecimal getAdvenceAmount() {
        return advenceAmount;
    }

    public void setAdvenceAmount(BigDecimal advenceAmount) {
        this.advenceAmount = advenceAmount;
    }

    public BigDecimal getRefuelAmount() {
        return refuelAmount;
    }

    public void setRefuelAmount(BigDecimal refuelAmount) {
        this.refuelAmount = refuelAmount;
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

    public BigDecimal getAgreementsMoney() {
        return agreementsMoney;
    }

    public void setAgreementsMoney(BigDecimal agreementsMoney) {
        this.agreementsMoney = agreementsMoney;
    }

    public BigDecimal getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(BigDecimal activityMoney) {
        this.activityMoney = activityMoney;
    }

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public BigDecimal getOilTotal() {
        return oilTotal;
    }

    public void setOilTotal(BigDecimal oilTotal) {
        this.oilTotal = oilTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Date getConfirm() {
        return confirm;
    }

    public void setConfirm(Date confirm) {
        this.confirm = confirm;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
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

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public Integer getOilersId() {
        return oilersId;
    }

    public void setOilersId(Integer oilersId) {
        this.oilersId = oilersId;
    }

    public String getOilersName() {
        return oilersName;
    }

    public void setOilersName(String oilersName) {
        this.oilersName = oilersName;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTaxCompany() {
        return taxCompany;
    }

    public void setTaxCompany(String taxCompany) {
        this.taxCompany = taxCompany;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
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

    public String getClearingStatus() {
        return clearingStatus;
    }

    public void setClearingStatus(String clearingStatus) {
        this.clearingStatus = clearingStatus;
    }

    public String getClearingErrorMsg() {
        return clearingErrorMsg;
    }

    public void setClearingErrorMsg(String clearingErrorMsg) {
        this.clearingErrorMsg = clearingErrorMsg;
    }

    public String getDiscountText() {
        return discountText;
    }

    public void setDiscountText(String discountText) {
        this.discountText = discountText;
    }

    public Integer getBankPayNum() {
        return bankPayNum;
    }

    public void setBankPayNum(Integer bankPayNum) {
        this.bankPayNum = bankPayNum;
    }

    public String getAllowCprint() {
        return allowCprint;
    }

    public void setAllowCprint(String allowCprint) {
        this.allowCprint = allowCprint;
    }

    public String getIsCprint() {
        return isCprint;
    }

    public void setIsCprint(String isCprint) {
        this.isCprint = isCprint;
    }

    public String getFeePayMethod() {
        return feePayMethod;
    }

    public void setFeePayMethod(String feePayMethod) {
        this.feePayMethod = feePayMethod;
    }

    public String getAgreementPayMethod() {
        return agreementPayMethod;
    }

    public void setAgreementPayMethod(String agreementPayMethod) {
        this.agreementPayMethod = agreementPayMethod;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }

    public BigDecimal getClearingAmount() {
        return clearingAmount;
    }

    public void setClearingAmount(BigDecimal clearingAmount) {
        this.clearingAmount = clearingAmount;
    }

    public String getRefuelType() {
        return refuelType;
    }

    public void setRefuelType(String refuelType) {
        this.refuelType = refuelType;
    }
}
