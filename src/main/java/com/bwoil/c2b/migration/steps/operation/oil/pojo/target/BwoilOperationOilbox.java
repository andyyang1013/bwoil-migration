package com.bwoil.c2b.migration.steps.operation.oil.pojo.target;


public class BwoilOperationOilbox {

    private long id;
    private long memberId;
    private double oilMass;
    private double oilFrozen;
    private String status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }


    public double getOilMass() {
        return oilMass;
    }

    public void setOilMass(double oilMass) {
        this.oilMass = oilMass;
    }


    public double getOilFrozen() {
        return oilFrozen;
    }

    public void setOilFrozen(double oilFrozen) {
        this.oilFrozen = oilFrozen;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
