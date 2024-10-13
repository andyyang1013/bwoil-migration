package com.bwoil.c2b.migration.steps.member.pojo.target;

import java.util.Date;

/**
 * 绑定第三方平台（微信）
 *
 * @author yangda
 */
public class BwoilMemberBindTag {
    // ID
    private Integer id;

    // 绑定平台
    private String tagType;

    // 绑定平台唯一ID
    private String tagId;

    // 绑定平台的昵称
    private String tagName;

    // 绑定会员
    private Integer memberId;

    private String disabled;

    // 创建时间
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
