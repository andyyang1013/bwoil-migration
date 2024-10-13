package com.bwoil.c2b.migration.steps.operation.promotion.pojo.target;


public class BwoilOperationPromotionChannelDepartment {

    private long id;
    private long parentId;
    private String nodeName;
    private String type;
    private String registerSource;
    private String payMethod;
    private String status;
    private java.sql.Timestamp uptime;
    private java.sql.Timestamp createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Timestamp getUptime() {
        return uptime;
    }

    public void setUptime(java.sql.Timestamp uptime) {
        this.uptime = uptime;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

}
