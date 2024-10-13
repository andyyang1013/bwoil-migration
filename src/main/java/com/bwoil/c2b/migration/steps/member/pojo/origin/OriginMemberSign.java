package com.bwoil.c2b.migration.steps.member.pojo.origin;

/**
 * 上海银行开户信息
 *
 * @author yangda
 */
public class OriginMemberSign {

    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 上海银行开户人
     */
    private String name;

    /**
     * 身份证号
     */
    private String cardNumber;

    /**
     * 上海银行卡号
     */
    private String cardNo;

    /**
     * 快捷支付协议号
     */
    private String cardCode;

    /**
     * 签约银行卡类型
     */
    private String cardType;

    /**
     * 账户等级
     */
    private String acctLvl;

    /**
     * 签约日期
     */
    private Integer authTime;

    /**
     * 最后修改日期
     */
    private Integer lastmodify;


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAcctLvl() {
        return acctLvl;
    }

    public void setAcctLvl(String acctLvl) {
        this.acctLvl = acctLvl;
    }

    public Integer getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Integer authTime) {
        this.authTime = authTime;
    }

    public Integer getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Integer lastmodify) {
        this.lastmodify = lastmodify;
    }
}
