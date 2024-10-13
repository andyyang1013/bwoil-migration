package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin;

/**
 * @Date 2019/3/20 17:45
 * @Author wenyue
 * @Description:
 **/
public class OriginOperationGasstationManualtpye {

    // 序号
    private Integer typeId;

    // 序号  默认：1
    private Long serialno;

    // 财务类型 0应收, 1应付
    private String financialType;

    // 费用类型
    private String costsName;

    // 费用类型说明
    private String costsIntroductions;

    // 是否有效  默认：1
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Integer createdTime;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getSerialno() {
        return serialno;
    }

    public void setSerialno(Long serialno) {
        this.serialno = serialno;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public String getCostsName() {
        return costsName;
    }

    public void setCostsName(String costsName) {
        this.costsName = costsName;
    }

    public String getCostsIntroductions() {
        return costsIntroductions;
    }

    public void setCostsIntroductions(String costsIntroductions) {
        this.costsIntroductions = costsIntroductions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }
}
