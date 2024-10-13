package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstationSettleReport {

    private Integer id;
    // 加油站id
    private Integer stationId;

    // 报表名称
    private String reportName;

    // 结算编码  默认：0
    private String settlementBn;

    // 班次的总数  默认：0
    private Integer staffCount;

    // 结算总金额
    private BigDecimal amount;

    // 小票打印奖励金额(奖励给员工的)
    private BigDecimal bonus;

    // 创建时间
    private Date createTime;

    // 起始时间
    private Date startTime;

    // 结束时间
    private Date endTime;

    // 付款时间,erp付款后记录
    private Date payTime;

    // 结算状态, 默认0草稿，1已提交，2已付款，3已确认
    private Integer status;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
