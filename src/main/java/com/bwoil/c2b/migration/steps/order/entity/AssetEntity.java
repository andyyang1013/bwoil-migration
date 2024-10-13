package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AssetEntity {

    // 用户id  默认：0
    private Integer memberId;

    // 资产类型 PROD: 产品、CZ: 充值 LC: 理财账户 RP: 红包
    private String assetType;

    // 当前余额  默认：0.00
    private BigDecimal curAdvance;

    // 当前可用余额  默认：0.00
    private BigDecimal advance;

    // 冻结金额  默认：0.00
    private BigDecimal freezeAdvance;

    // 上次余额  默认：0.00
    private BigDecimal lastAdvance;

    // 创建时间  默认：CURRENT_TIMESTAMP
    private Date createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    private BigDecimal recharge;

    private BigDecimal redEnvelope;

    private BigDecimal freezeRecharge;

    private BigDecimal freezeRedEnvelope;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getCurAdvance() {
        return curAdvance;
    }

    public void setCurAdvance(BigDecimal curAdvance) {
        this.curAdvance = curAdvance;
    }

    public BigDecimal getAdvance() {
        return advance;
    }

    public void setAdvance(BigDecimal advance) {
        this.advance = advance;
    }

    public BigDecimal getFreezeAdvance() {
        return freezeAdvance;
    }

    public void setFreezeAdvance(BigDecimal freezeAdvance) {
        this.freezeAdvance = freezeAdvance;
    }

    public BigDecimal getLastAdvance() {
        return lastAdvance;
    }

    public void setLastAdvance(BigDecimal lastAdvance) {
        this.lastAdvance = lastAdvance;
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

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(BigDecimal redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    public BigDecimal getFreezeRecharge() {
        return freezeRecharge;
    }

    public void setFreezeRecharge(BigDecimal freezeRecharge) {
        this.freezeRecharge = freezeRecharge;
    }

    public BigDecimal getFreezeRedEnvelope() {
        return freezeRedEnvelope;
    }

    public void setFreezeRedEnvelope(BigDecimal freezeRedEnvelope) {
        this.freezeRedEnvelope = freezeRedEnvelope;
    }

}