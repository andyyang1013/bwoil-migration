package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationGasstationSettleReport {

    private Integer settlementId;
    // 加油站id
    private Integer stationId;

    // 报表名称
    private String name;

    // 结算总金额
    private BigDecimal amount;
    // 小票打印奖励金额(奖励给员工的)
    private BigDecimal bonus;

    // 创建时间
    private Integer created;

    // 结算编码  默认：0
    private String settlementBn;

    // 班次的总数  默认：0
    private Integer staffCount;

    // 起始时间
    private Integer startTime;

    // 结束时间
    private Integer endTime;

    // 付款时间,erp付款后记录
    private Integer payTime;

    // 结算状态, 默认0草稿，1已提交，2已付款，3已确认
    private Integer status;

    public Integer getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getSettlementBn() {
        return settlementBn;
    }

    public void setSettlementBn(String settlementBn) {
        this.settlementBn = settlementBn;
    }

    public Integer getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(Integer staffCount) {
        this.staffCount = staffCount;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
