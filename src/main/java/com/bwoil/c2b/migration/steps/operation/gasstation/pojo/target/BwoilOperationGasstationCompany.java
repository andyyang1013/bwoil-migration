package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.util.Date;

public class BwoilOperationGasstationCompany {
    // 品牌公司ID
    private Integer companyId;

    // 品牌公司名称
    private String companyName;

    // 品牌公司联系人
    private String companyContact;

    // 品牌公司联系电话
    private String companyTel;
    //-1删除, 0正常
    private String status;

    // 创建时间
    private Date createTime;

    // 最后修改时间
    private Date lastmodify;

    // 提示备注
    private String companyDess;

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

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCompanyDess() {
        return companyDess;
    }

    public void setCompanyDess(String companyDess) {
        this.companyDess = companyDess;
    }
}
