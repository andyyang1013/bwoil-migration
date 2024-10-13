package com.bwoil.c2b.migration.steps.operation.promotion.pojo.tempClass;

import java.util.List;

/**
 * @author wumr
 * @Description: TODO
 * @date 2019/3/6 12:06
 */
public class H5BottomnavSettingReq {

    private String H5BottomnavOpen;

    //@ApiModelProperty(value="H5落地页配置详情", dataType="List")
    private List<H5BottomnavReq> H5Bottomnav;


    public String getH5BottomnavOpen() {
        return H5BottomnavOpen;
    }

    public void setH5BottomnavOpen(String h5BottomnavOpen) {
        H5BottomnavOpen = h5BottomnavOpen;
    }

    public List<H5BottomnavReq> getH5Bottomnav() {
        return H5Bottomnav;
    }

    public void setH5Bottomnav(List<H5BottomnavReq> h5Bottomnav) {
        H5Bottomnav = h5Bottomnav;
    }
}
