package com.bwoil.c2b.migration.steps.operation.feedback.pojo.origin;

public class OriginOperationFeedback {

    // ID
    private Integer feedbackId;

    // 获赠人ID
    private Integer memberId;

    // 用户名）
    private String moblie;

    // 反馈时间
    private Long createtime;

    // 处理时间
    private Long optTime;

    // 状态  默认：0
    private String status;
    // 反馈图片
    private String images;

    // 反馈内容
    private String content;

    // 处理意见
    private String adminContent;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getOptTime() {
        return optTime;
    }

    public void setOptTime(Long optTime) {
        this.optTime = optTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdminContent() {
        return adminContent;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }
}
