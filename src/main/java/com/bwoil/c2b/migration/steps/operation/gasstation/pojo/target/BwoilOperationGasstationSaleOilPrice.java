package com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target;


import java.math.BigDecimal;
import java.util.Date;

public class BwoilOperationGasstationSaleOilPrice {

    private Integer id;
    // 油站公司ID  默认：0
    private Integer companyId;

    // 加油站ID
    private Integer stationId;

    // 油品ID
    private Integer oilId;

    // 油站挂牌价(元/升)  默认：0.00
    private BigDecimal saleOilPrice;

    // 挂牌价一天内调整的次数  默认：0
    private Integer adjustTimes;

    // 油价调整幅度(%)  默认：0.00
    private BigDecimal priceRange;

    // 结算优惠0.0元/升   (协议表中的字段)
    private BigDecimal discount;

    // 维护人姓名
    private String modifyName;

    // 创建人姓名
    private String createName;

    // 状态, 失效删除-1, 待审核0默认, 审核ok启用1, 审核不通过2, 待提交3
    private String status;

    // 生效时间
    private Date startDate;

    // 维护时间
    private Date modifyTime;

    // 创建时间
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public BigDecimal getSaleOilPrice() {
        return saleOilPrice;
    }

    public void setSaleOilPrice(BigDecimal saleOilPrice) {
        this.saleOilPrice = saleOilPrice;
    }

    public Integer getAdjustTimes() {
        return adjustTimes;
    }

    public void setAdjustTimes(Integer adjustTimes) {
        this.adjustTimes = adjustTimes;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
