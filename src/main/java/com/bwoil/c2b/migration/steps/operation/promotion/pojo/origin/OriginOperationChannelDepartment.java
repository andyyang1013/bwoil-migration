package com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin;

public class OriginOperationChannelDepartment {

    // id
    private Long departmentId;

    // 父节点  默认：0
    private Long parentId;

    // 部门栏目名称
    private String nodeName;

    // 数据来源:1分销账户,2注册来源,3支付方式  默认：1
    private Integer type;

    // 注册来源
    private String source;

    // 支付方式
    private String payAppId;

    private Long createTime;

    private Long uptime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPayAppId() {
        return payAppId;
    }

    public void setPayAppId(String payAppId) {
        this.payAppId = payAppId;
    }
}