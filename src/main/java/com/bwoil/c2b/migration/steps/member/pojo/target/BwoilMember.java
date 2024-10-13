package com.bwoil.c2b.migration.steps.member.pojo.target;


import java.util.Date;

public class BwoilMember {

    // 会员id
    private Integer memberId;

    // 手机号
    private String mobile;

    // 注册手机号
    private String registerMobile;

    // 手机归属国家代码
    private String countryCode;

    // 用户头像id
    private String imageId;

    // 用户姓名
    private String realname;

    // 用户昵称
    private String nickName;

    // 性别(1:男2:女3:未知)
    private String sex;

    // 邮箱
    private String email;

    // 微信openid
    private String wechatOpid;

    // 实名认证标识(0：未实名 1:已实名)  默认：0
    private String realnameAuth;

    // 实名认证渠道
    private String authChannel;

    private Date authTime;

    // 登录密码
    private String password;

    // 交易密码
    private String tradePwd;

    // 是否开通云油账号(0:未开通 1:开通)  默认：0
    private String yunAcctFlag;


    //开通云油账号时间
    private Date yunAcctTime;

    // 身份证
    private String idenCode;

    // 会员编号
    private String shopBn;

    // 推荐人
    private String pcode;

    // 备注
    private String remark;

    // 平台来源,如: web,app
    private String source;

    // app应用市场来源
    private String marketSource;

    // 广告来源
    private String adSource;

    // 推广extra渠道ID
    private Integer extraChannelId;

    // 注册时间  默认：CURRENT_TIMESTAMP
    private Date registerTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    // 用户状态(-1 删除 0:正常 1:冻结 2：注销)  默认：0
    private String status;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegisterMobile() {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile) {
        this.registerMobile = registerMobile;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatOpid() {
        return wechatOpid;
    }

    public void setWechatOpid(String wechatOpid) {
        this.wechatOpid = wechatOpid;
    }

    public String getRealnameAuth() {
        return realnameAuth;
    }

    public void setRealnameAuth(String realnameAuth) {
        this.realnameAuth = realnameAuth;
    }

    public String getAuthChannel() {
        return authChannel;
    }

    public void setAuthChannel(String authChannel) {
        this.authChannel = authChannel;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getYunAcctFlag() {
        return yunAcctFlag;
    }

    public void setYunAcctFlag(String yunAcctFlag) {
        this.yunAcctFlag = yunAcctFlag;
    }

    public String getIdenCode() {
        return idenCode;
    }

    public void setIdenCode(String idenCode) {
        this.idenCode = idenCode;
    }

    public String getShopBn() {
        return shopBn;
    }

    public void setShopBn(String shopBn) {
        this.shopBn = shopBn;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMarketSource() {
        return marketSource;
    }

    public void setMarketSource(String marketSource) {
        this.marketSource = marketSource;
    }

    public String getAdSource() {
        return adSource;
    }

    public void setAdSource(String adSource) {
        this.adSource = adSource;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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

    public Integer getExtraChannelId() {
        return extraChannelId;
    }

    public void setExtraChannelId(Integer extraChannelId) {
        this.extraChannelId = extraChannelId;
    }

    public void setYunAcctTime(Date yunAcctTime) {
        this.yunAcctTime = yunAcctTime;
    }

    public Date getYunAcctTime() {
        return yunAcctTime;
    }
}
