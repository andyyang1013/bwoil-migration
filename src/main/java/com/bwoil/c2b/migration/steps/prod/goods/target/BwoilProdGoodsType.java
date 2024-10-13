package com.bwoil.c2b.migration.steps.prod.goods.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by qinls on 2019/3/7.
 */

public class BwoilProdGoodsType {
    private Integer id;
    // 类型名称
    private String name;

    // 类型编号
    private String typeBn;

    //别名
    private String alias;

    // 所属类型（litre: 金额产品、amount: 储油产品）
    private String typeAttr;

    // 最大折扣
    private BigDecimal maxDiscount;

    // 单日限购量(金额/升数)
    private BigDecimal dayLimitAmout;

    // 年限购量(金额/升数)
    private BigDecimal yearLimitAmout;

    // 最大持有量(金额/升数)
    private BigDecimal maxAmount;

    // 规格ID数组
    private String specIds;

    // ios描述
    private String iosDesc;

    // andriod描述
    private String androidDesc;

    // 类型描述
    private String typeDesc;

    // 商品详情描述
    private String detailDesc;

    // M 详情描述
    private String mDesc;

    // 兑付描述
    private String saleDesc;

    // 特点描述图片
    private String keywordImage;

    // 标签
    private String label;

    // 排序
    private Integer pOrder;

    // 备注
    private String remarks;

    // pc分类显示(1.储油卡 2.加油卡 3.保值卡)
    private String pcCatalog;

    // app分类显示(1.储油卡 2.加油卡 3.保值卡)
    private String appCatalog;

    // m站分类显示(1.储油卡 2.加油卡 3.保值卡)
    private String mCatalog;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeBn() {
        return typeBn;
    }

    public void setTypeBn(String typeBn) {
        this.typeBn = typeBn;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTypeAttr() {
        return typeAttr;
    }

    public void setTypeAttr(String typeAttr) {
        this.typeAttr = typeAttr;
    }

    public BigDecimal getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(BigDecimal maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public BigDecimal getDayLimitAmout() {
        return dayLimitAmout;
    }

    public void setDayLimitAmout(BigDecimal dayLimitAmout) {
        this.dayLimitAmout = dayLimitAmout;
    }

    public BigDecimal getYearLimitAmout() {
        return yearLimitAmout;
    }

    public void setYearLimitAmout(BigDecimal yearLimitAmout) {
        this.yearLimitAmout = yearLimitAmout;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds;
    }

    public String getIosDesc() {
        return iosDesc;
    }

    public void setIosDesc(String iosDesc) {
        this.iosDesc = iosDesc;
    }

    public String getAndroidDesc() {
        return androidDesc;
    }

    public void setAndroidDesc(String androidDesc) {
        this.androidDesc = androidDesc;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getSaleDesc() {
        return saleDesc;
    }

    public void setSaleDesc(String saleDesc) {
        this.saleDesc = saleDesc;
    }

    public String getKeywordImage() {
        return keywordImage;
    }

    public void setKeywordImage(String keywordImage) {
        this.keywordImage = keywordImage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getpOrder() {
        return pOrder;
    }

    public void setpOrder(Integer pOrder) {
        this.pOrder = pOrder;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPcCatalog() {
        return pcCatalog;
    }

    public void setPcCatalog(String pcCatalog) {
        this.pcCatalog = pcCatalog;
    }

    public String getAppCatalog() {
        return appCatalog;
    }

    public void setAppCatalog(String appCatalog) {
        this.appCatalog = appCatalog;
    }

    public String getmCatalog() {
        return mCatalog;
    }

    public void setmCatalog(String mCatalog) {
        this.mCatalog = mCatalog;
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
