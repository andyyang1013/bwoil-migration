package com.bwoil.c2b.migration.steps.member.pojo.origin;

import java.util.Date;

public class OriginMemberColor {

    // id
    private Integer id;

    // 会员id
    private Integer memberId;

    // 第三方会员标识
    private String openid;

    // 第三方手机号
    private String mobile;

    // 渠道
    private String refuelchannel;

    // 用户状态(0:冻结 1:正常 2：注销)
    private String status;

    // 注册时间  默认：CURRENT_TIMESTAMP
    private Date registerTime;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRefuelchannel() {
        return refuelchannel;
    }

    public void setRefuelchannel(String refuelchannel) {
        this.refuelchannel = refuelchannel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}