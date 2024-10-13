package com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.origin;

public class OriginOperationRedcouponActivity {

    // 活动ID
    private long cpnId;

    // 活动编号
    private String cpnBn;

    // 活动名称
    private String cpnName;

    // 优惠形式  默认：1
    private String reduceType;

    // 使用开始时间
    private long cpnStartDate;

    // 使用结束时间
    private long cpnEndDate;

    // 方案状态  默认：1
    private String cpnStatus;

    // 可同时使用现金券  默认：0
    private String withCashcoupon;

    // 可同时使用油箱抵扣  默认：0
    private String withGoil;

    // 方案删除状态  默认：0
    private String isDeleted;

    // 创建时间
    private long createTime;

    // 最后修改时间
    private long lastmodify;

    // 移动端前台活动标语
    private String mActSlogan;

    // PC端前台活动标语
    private String pcActSlogan;

    // 移动端活动入口
    private String mActUrl;

    // PC端活动入口
    private String pcActUrl;

    // 备注
    private String remark;

    public long getCpnId() {
        return cpnId;
    }

    public void setCpnId(long cpnId) {
        this.cpnId = cpnId;
    }

    public String getCpnBn() {
        return cpnBn;
    }

    public void setCpnBn(String cpnBn) {
        this.cpnBn = cpnBn;
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName;
    }

    public String getReduceType() {
        return reduceType;
    }

    public void setReduceType(String reduceType) {
        this.reduceType = reduceType;
    }

    public long getCpnStartDate() {
        return cpnStartDate;
    }

    public void setCpnStartDate(long cpnStartDate) {
        this.cpnStartDate = cpnStartDate;
    }

    public long getCpnEndDate() {
        return cpnEndDate;
    }

    public void setCpnEndDate(long cpnEndDate) {
        this.cpnEndDate = cpnEndDate;
    }

    public String getCpnStatus() {
        return cpnStatus;
    }

    public void setCpnStatus(String cpnStatus) {
        this.cpnStatus = cpnStatus;
    }

    public String getWithCashcoupon() {
        return withCashcoupon;
    }

    public void setWithCashcoupon(String withCashcoupon) {
        this.withCashcoupon = withCashcoupon;
    }

    public String getWithGoil() {
        return withGoil;
    }

    public void setWithGoil(String withGoil) {
        this.withGoil = withGoil;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(long lastmodify) {
        this.lastmodify = lastmodify;
    }

    public String getmActSlogan() {
        return mActSlogan;
    }

    public void setmActSlogan(String mActSlogan) {
        this.mActSlogan = mActSlogan;
    }

    public String getPcActSlogan() {
        return pcActSlogan;
    }

    public void setPcActSlogan(String pcActSlogan) {
        this.pcActSlogan = pcActSlogan;
    }

    public String getmActUrl() {
        return mActUrl;
    }

    public void setmActUrl(String mActUrl) {
        this.mActUrl = mActUrl;
    }

    public String getPcActUrl() {
        return pcActUrl;
    }

    public void setPcActUrl(String pcActUrl) {
        this.pcActUrl = pcActUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
