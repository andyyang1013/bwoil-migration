package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class CardPlanUpdateEntity {

	private String cardBn;
	
	private BigDecimal cashRemain;
	
	private String saleFlag;

	private Integer id;

	private String transStatus;

	public String getCardBn() {
		return cardBn;
	}

	public void setCardBn(String cardBn) {
		this.cardBn = cardBn;
	}

	public BigDecimal getCashRemain() {
		return cashRemain;
	}

	public void setCashRemain(BigDecimal cashRemain) {
		this.cashRemain = cashRemain;
	}

	public String getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
}
