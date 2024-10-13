package com.bwoil.c2b.migration.steps.operation.promotion.pojo.tempClass;

import java.util.List;

/**
 * @author wumr
 * @Description: TODO
 * @date 2019/3/5 15:07
 */
public class OilSendInfoReq {


    private String isOpen;

    private List<Integer> oilId;

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public List<Integer> getOilId() {
        return oilId;
    }

    public void setOilId(List<Integer> oilId) {
        this.oilId = oilId;
    }
}
