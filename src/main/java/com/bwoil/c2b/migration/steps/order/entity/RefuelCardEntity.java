package com.bwoil.c2b.migration.steps.order.entity;

import java.math.BigDecimal;

public class RefuelCardEntity {

    // 用户id  默认：0
    private Integer memberId;

    // 加油卡卡号
    private String cardNo;

    // 总金额  默认：0.00
    private BigDecimal amount;

    // 已充值总额  默认：0.00
    private BigDecimal rechAmount;

    // 待充值总额  默认：0.00
    private BigDecimal unrechAmount;

    // 状态:-1:删除 0:正常  默认：0
    private String status;

    //绑定时间
    private String bindTime;

    // 充值时间  默认：CURRENT_TIMESTAMP
    private String createTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private String lastUpdateTime;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRechAmount() {
        return rechAmount;
    }

    public void setRechAmount(BigDecimal rechAmount) {
        this.rechAmount = rechAmount;
    }

    public BigDecimal getUnrechAmount() {
        return unrechAmount;
    }

    public void setUnrechAmount(BigDecimal unrechAmount) {
        this.unrechAmount = unrechAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


}