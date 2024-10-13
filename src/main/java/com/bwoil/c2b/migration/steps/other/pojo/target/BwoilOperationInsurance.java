package com.bwoil.c2b.migration.steps.other.pojo.target;

import java.math.BigDecimal;

/**
 * @ClassName BwoilOperationInsurance
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:58
 **/
public class BwoilOperationInsurance {
    // 自增ID
    private Integer insuranceId;

    // 会员ID
    private Integer memberId;

    // 会员编号
    private String shopBn;

    // 客户姓名
    private String customerName;

    // 手机号
    private String mobile;

    // 证件类型,0:身份证,1护照  默认：0
    private Integer certificateType;

    // 证件号码
    private String certificateBn;

    // 投保公司
    private String insuranceCompany;

    // 产品代码
    private String productCode;

    // 投保日期
    private Long insuranceDate;

    // 保险单号
    private String insuranceBn;

    // 保险生效时间
    private Long fromTime;

    // 保险失效时间
    private Long toTime;

    // 有效期
    private Integer period;

    // 保险金额  默认：0.000
    private BigDecimal insuranceMoney;

    // 免赔限额  默认：0.000
    private BigDecimal insuranceLimit;

    // 保险费  默认：0.000
    private BigDecimal premium;

    // 电子保单图片
    private String insuranceImage;

    // 保障状态:0新建,1认证成功,2资产达标,3投保中,4投保成功,5即将到期,6投保失败  默认：0
    private Integer insuranceStatus;

    // 备注
    private String remarks;

    // 创建时间
    private Long createTime;

    // 最后修改时间
    private Long lastmodify;

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getShopBn() {
        return shopBn;
    }

    public void setShopBn(String shopBn) {
        this.shopBn = shopBn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateBn() {
        return certificateBn;
    }

    public void setCertificateBn(String certificateBn) {
        this.certificateBn = certificateBn;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Long insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getInsuranceBn() {
        return insuranceBn;
    }

    public void setInsuranceBn(String insuranceBn) {
        this.insuranceBn = insuranceBn;
    }

    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(BigDecimal insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public BigDecimal getInsuranceLimit() {
        return insuranceLimit;
    }

    public void setInsuranceLimit(BigDecimal insuranceLimit) {
        this.insuranceLimit = insuranceLimit;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getInsuranceImage() {
        return insuranceImage;
    }

    public void setInsuranceImage(String insuranceImage) {
        this.insuranceImage = insuranceImage;
    }

    public Integer getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(Integer insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Long lastmodify) {
        this.lastmodify = lastmodify;
    }
}
