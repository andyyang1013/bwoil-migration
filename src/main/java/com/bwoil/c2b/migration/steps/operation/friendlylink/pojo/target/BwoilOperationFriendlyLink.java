package com.bwoil.c2b.migration.steps.operation.friendlylink.pojo.target;


public class BwoilOperationFriendlyLink {

    // 链接名称
    private String linkName;

    // 链接地址
    private String linkAddr;

    // 链接图片
    private String linkImage;

    // 排序号  默认：0
    private Integer sortNum;

    // 是否隐藏(0 不隐藏 1 隐藏)   默认：0
    private Integer isHidden;

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }
}
