package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

import java.math.BigDecimal;

public class OriginOperationPromotionReport {

    // 报表名称
    private String report_name;

    // 结算佣金
    private BigDecimal settlement_money;

    // 打款状态：0：未打款，1：已打款，2：打款失败
    private String settlement_status;

    // 渠道类型(1：个人，2：公司，3：电销，4：地推 ，5：加油站 )
    private String channel_type;

    // 报表创建日期s
    private Long report_time;

    // 报表创建日期
    private Long report_add_time;

    // 最后修改时间
    private Long lastmodify;

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public BigDecimal getSettlement_money() {
        return settlement_money;
    }

    public void setSettlement_money(BigDecimal settlement_money) {
        this.settlement_money = settlement_money;
    }

    public String getSettlement_status() {
        return settlement_status;
    }

    public void setSettlement_status(String settlement_status) {
        this.settlement_status = settlement_status;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public Long getReport_time() {
        return report_time;
    }

    public void setReport_time(Long report_time) {
        this.report_time = report_time;
    }

    public Long getReport_add_time() {
        return report_add_time;
    }

    public void setReport_add_time(Long report_add_time) {
        this.report_add_time = report_add_time;
    }

    public Long getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Long lastmodify) {
        this.lastmodify = lastmodify;
    }
}
