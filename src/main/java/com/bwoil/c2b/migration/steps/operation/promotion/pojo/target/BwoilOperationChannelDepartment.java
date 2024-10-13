package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;

import java.sql.Timestamp;

public class BwoilOperationChannelDepartment {

    // id
    private Long id;

    // 父节点  默认：0
    private Long parentId;

    // 部门栏目名称
    private String nodeName;

    // 数据来源:1分销账户,2注册来源,3支付方式  默认：1
    private Integer type;

    // 注册来源
    private String registerSource;

    // 支付方式
    private String payMethod;

    private Timestamp createTime;

    private Timestamp uptime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUptime() {
        return uptime;
    }

    public void setUptime(Timestamp uptime) {
        this.uptime = uptime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}