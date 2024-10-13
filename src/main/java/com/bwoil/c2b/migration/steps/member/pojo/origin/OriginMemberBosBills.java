package com.bwoil.c2b.migration.steps.member.pojo.origin;

/**
 * 上海银行联名卡帐单表
 *
 * @author yangda
 */
public class OriginMemberBosBills {

    /**
     * 自增ID
     */
    private Integer billsId;

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
    private String billDiscount;

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
    private Integer lastmodify;


    public Integer getBillsId() {
        return billsId;
    }

    public void setBillsId(Integer billsId) {
        this.billsId = billsId;
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

    public Integer getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Integer lastmodify) {
        this.lastmodify = lastmodify;
    }

    public String getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(String billDiscount) {
        this.billDiscount = billDiscount;
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

    public String getBillSource() {
        return billSource;
    }

    public void setBillSource(String billSource) {
        this.billSource = billSource;
    }
}
