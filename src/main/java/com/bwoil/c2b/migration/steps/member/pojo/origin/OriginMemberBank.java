package com.bwoil.c2b.migration.steps.member.pojo.origin;

public class OriginMemberBank {

    // id
    private Integer bankId;

    // 会员id
    private Integer memberId;

    // 银行名称
    private String bankName;

    // 支行地区
    private String bankRegion;

    // 支行名称
    private String bankSubName;

    // 银行编号(使用银联的标准)
    private String bankBn;

    // 银行卡号
    private byte[] bankNo;

    // 宝付绑定ID(1实名认证)
    private String bindId;

    // 宝付签约唯一码(2协议认证)
    private String protocolNo;

    // 宝付认证类型1实名认证2协议认证
    private String baofuType;

    // 绑定渠道
    private String channelCode;

    // 默认提现卡号(0:否 1:是)  默认：0
    private String defFlag;

    // 是否支持快捷 0:不支持 1:支持
    private String quickPay;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private int createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private int lastUpdateTime;

    // 状态(-1:删除 0:正常 1:预绑定)  默认：0
    private String status;

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

    public String getBankRegion() {
        return bankRegion;
    }

    public void setBankRegion(String bankRegion) {
        this.bankRegion = bankRegion;
    }

    public String getBankSubName() {
        return bankSubName;
    }

    public void setBankSubName(String bankSubName) {
        this.bankSubName = bankSubName;
    }

    public String getBankBn() {
        return bankBn;
    }

    public void setBankBn(String bankBn) {
        this.bankBn = bankBn;
    }

    public byte[] getBankNo() {
        return bankNo;
    }

    public void setBankNo(byte[] bankNo) {
        this.bankNo = bankNo;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getBaofuType() {
        return baofuType;
    }

    public void setBaofuType(String baofuType) {
        this.baofuType = baofuType;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getDefFlag() {
        return defFlag;
    }

    public void setDefFlag(String defFlag) {
        this.defFlag = defFlag;
    }

    public String getQuickPay() {
        return quickPay;
    }

    public void setQuickPay(String quickPay) {
        this.quickPay = quickPay;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(int lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
}
