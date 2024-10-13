package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.util.Date;

public class BwoilOperationGasstationRefuelPaylog {

    // 日志ID，自增主键
    private Integer logId;

    // 扫码加油订单号
    private Long orderId;

    // 日志内容
    private String logMemo;

    // 日志类型  默认：order
    private String logBehavior;

    // 发送通知  默认：true
    private String logNotice;

    // 操作时间
    private Date operatorTime;

    // 日志数据
    private String logData;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLogMemo() {
        return logMemo;
    }

    public void setLogMemo(String logMemo) {
        this.logMemo = logMemo;
    }

    public String getLogBehavior() {
        return logBehavior;
    }

    public void setLogBehavior(String logBehavior) {
        this.logBehavior = logBehavior;
    }

    public String getLogNotice() {
        return logNotice;
    }

    public void setLogNotice(String logNotice) {
        this.logNotice = logNotice;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }
}
