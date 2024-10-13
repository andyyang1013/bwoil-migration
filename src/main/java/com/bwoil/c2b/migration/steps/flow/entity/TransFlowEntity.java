package com.bwoil.c2b.migration.steps.flow.entity;

import java.math.BigDecimal;

public class TransFlowEntity {

    // 资金流水ID
    private Long flowId;

    // 关联表id
    private String referenceId;

    // 交易类型
    private String tradeType;

    // 会员ID
    private Integer memberId;

    // 金额  默认：0.00
    private BigDecimal amount;

    // 状态
    private String flowStatus;

    // 操作说明
    private String flowDesc;

    // 备注
    private String remark;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;
    
    private Long startTime;
    
    private Long endTime;

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	public String getFlowDesc() {
		return flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
    
}