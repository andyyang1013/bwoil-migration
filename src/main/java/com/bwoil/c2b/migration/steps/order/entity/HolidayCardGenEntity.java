package com.bwoil.c2b.migration.steps.order.entity;

import java.util.Date;

public class HolidayCardGenEntity {

    // 购买用户
    private String buyuser;

    // 生成数量
    private Integer generatorCnt;

    // 密钥长度
    private Integer cryptLen;

    // 密钥类型json
    private String cryptTypes;

    // 货品ID
    private Integer productId;

    // 货品名称
    private String productName;

    // 操作人id
    private Integer operatorId;

    // 操作人
    private String operatorName;

    // 操作人ip信息
    private String operatorIp;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    public String getBuyuser() {
        return buyuser;
    }

    public void setBuyuser(String buyuser) {
        this.buyuser = buyuser;
    }

    public Integer getGeneratorCnt() {
        return generatorCnt;
    }

    public void setGeneratorCnt(Integer generatorCnt) {
        this.generatorCnt = generatorCnt;
    }

    public Integer getCryptLen() {
        return cryptLen;
    }

    public void setCryptLen(Integer cryptLen) {
        this.cryptLen = cryptLen;
    }

    public String getCryptTypes() {
        return cryptTypes;
    }

    public void setCryptTypes(String cryptTypes) {
        this.cryptTypes = cryptTypes;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


}