package com.bwoil.c2b.migration.steps.order.entity;

import java.util.Date;


public class CardSeqEntity {

	private String maxCardBn;
    // 卡类型(类型别名+地区区号+油品编号左边两位+卡类型06虚拟卡/08实体卡)
    private String cardType;

    // 卡号序列
    private Integer cardSeq;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    public String getMaxCardBn() {
		return maxCardBn;
	}

	public void setMaxCardBn(String maxCardBn) {
		this.maxCardBn = maxCardBn;
	}

	public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getCardSeq() {
        return cardSeq;
    }

    public void setCardSeq(Integer cardSeq) {
        this.cardSeq = cardSeq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}