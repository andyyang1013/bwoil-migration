package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstation {

    // 加油站ID
    private Integer stationId;

    // 加油站ERP编号
    private String stationBn;

    //加油站的二维码图片id
    private String imageId;

    // 加油站名称
    private String stationName;

    // 加油站所在地区
    private String area;

    // 加油站地址
    private String addr;

    // 联系人
    private String contact;

    // 手机
    private String mobile;

    // 固定电话
    private String phone;

    // 邮箱
    private String email;

    // 加油站打印机编号
    private String printerSn;

    // 加油站打印机的key值
    private String gasKey;

    // 是否允许云端打印：1:允许，2:不允许  默认：1
    private String allowCprint;

    // 是否开通直付功能：1:开通，2:不开通  默认：2
    private String allowPayStation;

    // 微信折扣  默认：10.00
    private BigDecimal weixinDiscount;

    // 是否开通允许加油  默认1状态true启用, false不启用
    private String status;

    // 每日打款限额  默认：0.00
    private BigDecimal quotaamountperday;

    // 会员ID
    private Integer memberId;

    // 开户银行
    private String bankName;

    // 开户支行
    private String bankArea;

    // 网点编号
    private String bankNo;

    // 账户名称
    private String trueName;

    // 银行帐号
    private String bankAccount;

    // 创建时间
    private Date createTime;

    // 最后修改时间
    private Date lastmodify;

    // 是否有子加油站  默认：0
    private Integer isParent;

    // 下属子油站
    private String childStation;

    // 父级加油站ID  默认：0
    private Integer parentId;

    // 结算扣款费率  默认：0
    private String feerate;

    // 结算打款方式  默认：REALTIME
    private String remittancetype;

    // 手续费-结算方式  默认：1
    private String feePayMethod;

    // 协议折扣-结算方式  默认：1
    private String agreementPayMethod;

    // 结算打款时间
    private String remittanceperiod;

    // 经度坐标  默认：0.00000
    private BigDecimal longitude;

    // 纬度坐标  默认：0.00000
    private BigDecimal latitude;

    // 品牌公司id
    private Integer companyId;

    // 品牌公司名称
    private String companyName;

    // 设备SN编号码
    private String deviceSn;

    // 设备SN版本
    private String deviceVersion;

    // 设备SN更新时间
    private String deviceUpdatetime;

    // 设备SN强制升级标志
    private String deviceStatus;


    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationBn() {
        return stationBn;
    }

    public void setStationBn(String stationBn) {
        this.stationBn = stationBn;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrinterSn() {
        return printerSn;
    }

    public void setPrinterSn(String printerSn) {
        this.printerSn = printerSn;
    }

    public String getGasKey() {
        return gasKey;
    }

    public void setGasKey(String gasKey) {
        this.gasKey = gasKey;
    }

    public String getAllowCprint() {
        return allowCprint;
    }

    public void setAllowCprint(String allowCprint) {
        this.allowCprint = allowCprint;
    }

    public String getAllowPayStation() {
        return allowPayStation;
    }

    public void setAllowPayStation(String allowPayStation) {
        this.allowPayStation = allowPayStation;
    }

    public BigDecimal getWeixinDiscount() {
        return weixinDiscount;
    }

    public void setWeixinDiscount(BigDecimal weixinDiscount) {
        this.weixinDiscount = weixinDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getQuotaamountperday() {
        return quotaamountperday;
    }

    public void setQuotaamountperday(BigDecimal quotaamountperday) {
        this.quotaamountperday = quotaamountperday;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankArea() {
        return bankArea;
    }

    public void setBankArea(String bankArea) {
        this.bankArea = bankArea;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Date lastmodify) {
        this.lastmodify = lastmodify;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getChildStation() {
        return childStation;
    }

    public void setChildStation(String childStation) {
        this.childStation = childStation;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFeerate() {
        return feerate;
    }

    public void setFeerate(String feerate) {
        this.feerate = feerate;
    }

    public String getRemittancetype() {
        return remittancetype;
    }

    public void setRemittancetype(String remittancetype) {
        this.remittancetype = remittancetype;
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

    public String getRemittanceperiod() {
        return remittanceperiod;
    }

    public void setRemittanceperiod(String remittanceperiod) {
        this.remittanceperiod = remittanceperiod;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceUpdatetime() {
        return deviceUpdatetime;
    }

    public void setDeviceUpdatetime(String deviceUpdatetime) {
        this.deviceUpdatetime = deviceUpdatetime;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
