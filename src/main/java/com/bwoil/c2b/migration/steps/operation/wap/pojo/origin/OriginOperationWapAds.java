package com.bwoil.c2b.migration.steps.operation.wap.pojo.origin;

public class OriginOperationWapAds {
    // 广告ID
    private Integer adId;

    // 链接地址
    private String imageUrl;

    // 图片的超链接地址
    private String imageHyperlink;

    // 轮播标题
    private String adTitle;

    // 广告类型:home首页,service加油服务  默认：home
    private String adType;

    // 跳转功能
    private String adJumpusage;

    // 说明
    private String instruction;

    // 排序  默认：0
    private Short sort;

    // 位置
    private String position;

    // 状态  默认：false
    private String status;

    // 是否可以分享  默认：false
    private String share;

    // 分享图标
    private String shareBanner;

    // 分享标题
    private String sharetitle;

    // 关联商品分类
    private String typeBn;

    // 分享内容描述
    private String shareContent;

    // 添加时间
    private long addTime;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageHyperlink() {
        return imageHyperlink;
    }

    public void setImageHyperlink(String imageHyperlink) {
        this.imageHyperlink = imageHyperlink;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdJumpusage() {
        return adJumpusage;
    }

    public void setAdJumpusage(String adJumpusage) {
        this.adJumpusage = adJumpusage;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getShareBanner() {
        return shareBanner;
    }

    public void setShareBanner(String shareBanner) {
        this.shareBanner = shareBanner;
    }

    public String getSharetitle() {
        return sharetitle;
    }

    public void setSharetitle(String sharetitle) {
        this.sharetitle = sharetitle;
    }

    public String getTypeBn() {
        return typeBn;
    }

    public void setTypeBn(String typeBn) {
        this.typeBn = typeBn;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}
