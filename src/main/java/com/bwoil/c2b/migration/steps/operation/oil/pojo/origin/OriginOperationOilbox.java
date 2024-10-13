package com.bwoil.c2b.migration.steps.operation.oil.pojo.origin;

public class OriginOperationOilbox {
    private long memberId;
    private double goilGive;
    private double goilFreeze;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public double getGoilGive() {
        return goilGive;
    }

    public void setGoilGive(double goilGive) {
        this.goilGive = goilGive;
    }

    public double getGoilFreeze() {
        return goilFreeze;
    }

    public void setGoilFreeze(double goilFreeze) {
        this.goilFreeze = goilFreeze;
    }
}
