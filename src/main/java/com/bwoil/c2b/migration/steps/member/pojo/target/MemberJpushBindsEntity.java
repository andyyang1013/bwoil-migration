package com.bwoil.c2b.migration.steps.member.pojo.target;


import java.util.Date;

public class MemberJpushBindsEntity {

    private Integer id;

    // 会员账号  默认：0
    private Integer memberId;

    // 会员编号
    private String shopBn;

    // 推送手机号
    private String mobile;

    // 推送注册ID
    private String registrationId;

    // 平台
    private String platform;

    // 访问次数
    private Integer accessTimes;

    // 推送标识
    private String tag;

    // 其它
    private String memo;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：0000-00-00 00:00:00
    private Date lastUpdateTime;

    // 状态(-1:删除 0:正常)  默认：0
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(Integer accessTimes) {
        this.accessTimes = accessTimes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}