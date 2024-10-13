package com.bwoil.c2b.migration.steps.member.pojo.target;

import java.util.Date;

/**
 * @author yangda
 * @dec 上海银行开户信息
 */
public class BwoilMemberSign {
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
    private String identifyId;

    /**
     * 上海银行卡号
     */
    private String cardNo;

    /**
     * 签约银行卡类型
     */
    private String cardType;

    /**
     * 快捷支付协议号
     */
    private String signCode;

    /**
     * 签约日期
     */
    private Date signTime;

    /**
     * 最后修改日期
     */
    private Date lastUpdateTime;

    /**
     * 账户等级
     */
    private String acctLv;

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

    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getAcctLv() {
        return acctLv;
    }

    public void setAcctLv(String acctLv) {
        this.acctLv = acctLv;
    }

}
