package com.bwoil.c2b.migration.steps.member.pojo.target;

import java.util.Date;

/**
 * 上海银行联名卡帐单表
 *
 * @author yangda
 */
public class BwoilMemberBosBills {

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 手机号码
     */
    private String loginAccount;

    /**
     * 姓名
     */
    private String trueName;

    /**
     * 联名卡卡号
     */
    private String cardNo;

    /**
     * 是否可折扣
     */
    private String isDiscount;

    /**
     * 卡类型
     */
    private String billType;

    /**
     * 首次绑定
     */
    private String billFirstmonth;

    /**
     * 帐单月份
     */
    private Integer billMonth;

    /**
     * ECIF客户号
     */
    private String billEcif;

    /**
     * 数据来源
     */
    private String billSource;

    /**
     * 录入日期
     */
    private Date lastModify;


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

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillFirstmonth() {
        return billFirstmonth;
    }

    public void setBillFirstmonth(String billFirstmonth) {
        this.billFirstmonth = billFirstmonth;
    }

    public Integer getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(Integer billMonth) {
        this.billMonth = billMonth;
    }

    public String getBillEcif() {
        return billEcif;
    }

    public void setBillEcif(String billEcif) {
        this.billEcif = billEcif;
    }

    public String getBillSource() {
        return billSource;
    }

    public void setBillSource(String billSource) {
        this.billSource = billSource;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }
}
