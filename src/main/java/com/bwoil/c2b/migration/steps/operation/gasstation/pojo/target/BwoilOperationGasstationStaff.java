package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.util.Date;

public class BwoilOperationGasstationStaff {

    private Integer staffId;

    // 加油站id
    private Integer stationId;

    // 油站管理员id
    private Integer parentMemberId;

    // 登录名
    private String userName;

    // 手机号
    private String mobile;

    // 密码
    private String password;

    // 真是姓名
    private String trueName;

    // 职位，1为收银员，2为财务人员, 3油工
    private Integer typeId;

    // 用户状态1可用,0禁用  默认：1
    private Integer status;

    // 最后修改时间
    private Date lastmodify;

    // 创建时间
    private Date created;

    // 输入密码错误次数，3次密码锁定  默认：0
    private Integer pwdError;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getParentMemberId() {
        return parentMemberId;
    }

    public void setParentMemberId(Integer parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Date lastmodify) {
        this.lastmodify = lastmodify;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getPwdError() {
        return pwdError;
    }

    public void setPwdError(Integer pwdError) {
        this.pwdError = pwdError;
    }
}
