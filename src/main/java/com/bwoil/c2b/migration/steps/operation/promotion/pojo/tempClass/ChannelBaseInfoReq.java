package com.bwoil.c2b.migration.steps.operation.promotion.pojo.tempClass;

/**
 * @author wumr
 * @Description: TODO
 * @date 2019/3/5 16:34
 */
public class ChannelBaseInfoReq {

    //分销注册页成功页配置

    // @ApiModelProperty(value="注册成功图标", dataType="String")
    private String regSucImage;

    //@ApiModelProperty(value="下载按钮文本", dataType="String")
    private String regDownText;

    //@ApiModelProperty(value="下载按钮颜色", dataType="String")
    private String regDownColor;

    // @ApiModelProperty(value="成功页分享按钮", dataType="String")
    private String isShareCheck;

    //@ApiModelProperty(value="分享按钮文本", dataType="String")
    private String regSucShareBtn;

    //@ApiModelProperty(value="H5注册成功页配置是否添加按钮 1是 0不是 默认0", dataType="String")
    private String isAddCheck;

    //@ApiModelProperty(value="按钮文本", dataType="String")
    private String buttonText;

    //@ApiModelProperty(value="按钮链接", dataType="String")
    private String buttonLink;

    //@ApiModelProperty(value="规则说明", dataType="String")
    private String regSucRule;

    public String getRegSucImage() {
        return regSucImage;
    }

    public void setRegSucImage(String regSucImage) {
        this.regSucImage = regSucImage;
    }

    public String getRegDownText() {
        return regDownText;
    }

    public void setRegDownText(String regDownText) {
        this.regDownText = regDownText;
    }

    public String getRegDownColor() {
        return regDownColor;
    }

    public void setRegDownColor(String regDownColor) {
        this.regDownColor = regDownColor;
    }

    public String getIsShareCheck() {
        return isShareCheck;
    }

    public void setIsShareCheck(String isShareCheck) {
        this.isShareCheck = isShareCheck;
    }

    public String getRegSucShareBtn() {
        return regSucShareBtn;
    }

    public void setRegSucShareBtn(String regSucShareBtn) {
        this.regSucShareBtn = regSucShareBtn;
    }

    public String getIsAddCheck() {
        return isAddCheck;
    }

    public void setIsAddCheck(String isAddCheck) {
        this.isAddCheck = isAddCheck;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonLink() {
        return buttonLink;
    }

    public void setButtonLink(String buttonLink) {
        this.buttonLink = buttonLink;
    }

    public String getRegSucRule() {
        return regSucRule;
    }

    public void setRegSucRule(String regSucRule) {
        this.regSucRule = regSucRule;
    }
}
