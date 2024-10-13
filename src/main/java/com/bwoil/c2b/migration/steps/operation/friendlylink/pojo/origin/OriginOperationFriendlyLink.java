package com.bwoil.c2b.migration.steps.operation.friendlylink.pojo.origin;

public class OriginOperationFriendlyLink {
    private String link_name;
    private String href;
    private String image_url;
    private Integer orderlist;
    private String hidden;

    public String getLink_name() {
        return link_name;
    }

    public String getHref() {
        return href;
    }

    public String getImage_url() {
        return image_url;
    }

    public Integer getOrderlist() {
        return orderlist;
    }

    public String getHidden() {
        return hidden;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setOrderlist(Integer orderlist) {
        this.orderlist = orderlist;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }
}
