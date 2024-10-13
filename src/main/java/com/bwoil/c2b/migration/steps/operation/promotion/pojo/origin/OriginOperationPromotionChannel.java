package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

public class OriginOperationPromotionChannel {

    private Integer channelId;

    // 渠道类型(1：个人，2：合作商，3：电销，4：地推， 5：加油站)  默认：1
    private String channelType;

    // 渠道ID
    private Long parentId;

    // 部门类型
    private Long departmentType;

    // 第三方  默认：defaultype
    private String businessType;

    // 合作商用户ID  默认：0
    private Long memberId;

    // 账户名称
    private String channelName;

    // 是否有效  默认：true
    private String isvalid;

    // 是否快速注册  默认：false
    private String isfastreg;

    // 封面图片
    private String shareImage;

    // 链接地址
    private String shareImageUrl;

    // 账期提醒
    private String accountDesc;

    // 收益规则
    private String rateDesc;

    // 分享内容
    private String promotionDesc;

    // 说明
    private String description;

    // 分销注册页成功页配置
    private String channelBaseInfo;

    // h5注册页配置
    private String h5ButtonSetting;

    // H5落地页导航栏配置
    private String h5BottomnavSetting;

    // 送油
    private String salesOilSend;

    // 送券配置
    private String salesCouponSend;

    // 创建时间  默认：0
    private Long createTime;

    private String stype;//临时字段 类型集合

    private String statusList; // 临时字段 状态集合

    private String temp; //临时字段 存放油券

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Long getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Long departmentType) {
        this.departmentType = departmentType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public String getIsfastreg() {
        return isfastreg;
    }

    public void setIsfastreg(String isfastreg) {
        this.isfastreg = isfastreg;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelBaseInfo() {
        return channelBaseInfo;
    }

    public void setChannelBaseInfo(String channelBaseInfo) {
        this.channelBaseInfo = channelBaseInfo;
    }

    public String getH5ButtonSetting() {
        return h5ButtonSetting;
    }

    public void setH5ButtonSetting(String h5ButtonSetting) {
        this.h5ButtonSetting = h5ButtonSetting;
    }

    public String getH5BottomnavSetting() {
        return h5BottomnavSetting;
    }

    public void setH5BottomnavSetting(String h5BottomnavSetting) {
        this.h5BottomnavSetting = h5BottomnavSetting;
    }

    public String getSalesOilSend() {
        return salesOilSend;
    }

    public void setSalesOilSend(String salesOilSend) {
        this.salesOilSend = salesOilSend;
    }

    public String getSalesCouponSend() {
        return salesCouponSend;
    }

    public void setSalesCouponSend(String salesCouponSend) {
        this.salesCouponSend = salesCouponSend;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getStatusList() {
        return statusList;
    }

    public void setStatusList(String statusList) {
        this.statusList = statusList;
    }
}
