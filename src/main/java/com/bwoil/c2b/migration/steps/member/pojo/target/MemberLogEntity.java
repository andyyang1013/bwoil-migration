package com.bwoil.c2b.migration.steps.member.pojo.target;

import java.util.Date;

public class MemberLogEntity {

    // ID
    private Integer logId;

    // 会员ID
    private Integer memberId;

    // 会员账号
    private String memberAccount;

    // 日志类型 login(登录）、info(会员信息）、identity（实名认证）、trade_pwd（交易密码）、login_pwd（登录密码）、regist（注册）
    private String objectType;

    // IP
    private String ip;

    // 内网IP
    private String inIp;

    // MAC地址
    private String mac;

    // 终端类型
    private String terminal;

    // 日志内容
    private String logMemo;

    // 操作人ID
    private Integer operatorId;

    // 操作人姓名
    private String operatorName;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    // 状态:0：成功，1：失败
    private String status;

    // 日志数据
    private String logData;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInIp() {
        return inIp;
    }

    public void setInIp(String inIp) {
        this.inIp = inIp;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getLogMemo() {
        return logMemo;
    }

    public void setLogMemo(String logMemo) {
        this.logMemo = logMemo;
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

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }
}