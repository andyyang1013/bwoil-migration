package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CardUpdateEntity {

	private String cardBn;
	
	private String transStatus;
	
	private Date recentlySaleDate;

	private BigDecimal canSaleAmount;

	private BigDecimal totalAmount;

	private Integer totalCnt;

	private BigDecimal realPayAmount;

	private String orderId;

	private String id;

	public Date getRecentlySaleDate() {
		return recentlySaleDate;
	}

	public void setRecentlySaleDate(Date recentlySaleDate) {
		this.recentlySaleDate = recentlySaleDate;
	}

	public String getCardBn() {
		return cardBn;
	}

	public void setCardBn(String cardBn) {
		this.cardBn = cardBn;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public BigDecimal getCanSaleAmount() {
		return canSaleAmount;
	}

	public void setCanSaleAmount(BigDecimal canSaleAmount) {
		this.canSaleAmount = canSaleAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public BigDecimal getRealPayAmount() {
		return realPayAmount;
	}

	public void setRealPayAmount(BigDecimal realPayAmount) {
		this.realPayAmount = realPayAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CardUpdateEntity{" +
				"cardBn='" + cardBn + '\'' +
				", transStatus='" + transStatus + '\'' +
				", recentlySaleDate=" + recentlySaleDate +
				", canSaleAmount=" + canSaleAmount +
				'}';
	}

}
