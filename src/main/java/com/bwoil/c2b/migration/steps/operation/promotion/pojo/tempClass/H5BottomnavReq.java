package com.bwoil.c2b.migration.steps.operation.promotion.pojo.tempClass;

/**
 * @author wumr
 * @Description: TODO
 * @date 2019/3/6 12:07
 */
public class H5BottomnavReq {

    //@ApiModelProperty(value="图标", dataType="String")
    private String icon;

    // @ApiModelProperty(value="导航名字", dataType="String")
    private String name;

    // @ApiModelProperty(value="导航颜色", dataType="String")
    private String color;

    // @ApiModelProperty(value="大小", dataType="String")
    private String size;

    // @ApiModelProperty(value="链接地址", dataType="String")
    private String url;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
