package com.bwoil.c2b.migration.steps.member.pojo.origin;

/**
 * 绑定第三方平台（微信）
 *
 * @author yangda
 */
public class OriginMemberBindTag {

    // ID
    private Integer id;

    // 绑定平台
    private String tagType;

    // 绑定平台唯一ID
    private String openId;

    // 绑定平台的昵称
    private String tagName;

    // 绑定会员
    private String memberId;

    private String disabled;

    // 创建时间
    private Integer createtime;


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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }
}
