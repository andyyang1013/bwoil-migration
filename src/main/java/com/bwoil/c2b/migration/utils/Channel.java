package com.bwoil.c2b.migration.utils;

/**
 *  支付渠道
 */
public enum Channel {
    /**
     * 微信支付，即微信 APP 支付
     */
    WX_APP(1,"wx", "微信 APP 支付"),
    /**
     * 微信公众号支付
     */
    WX_PUB(2,"wx_pub", "微信公众号支付"),
    /**
     * 微信公众号扫码支付
     */
    WX_PUB_QR(3,"wx_qr", "微信公众号扫码支付"),
    /**
     * 微信H5支付
     */
    WX_WAP(4,"wx_h5", "微信H5支付"),

    /**
     * 网关支付通称(由系统根据规则选择网关通道，可能是银联，可能是富友等)
     */
    GATEWQY(5,"gateway", "网关支付"),

    /**
     * 快捷支付统称，系统会根据规则选择快捷通道，可能是宝付，有可能是易宝
     */
    FASTPAY(6,"fast", "快捷支付"),

    /**
     * Apple Pay
     */
    APPLE_PAY(7,"apple_pay", "Apple支付"),

    /**
     * 宝付认证支付
     */
    BAOFU_DIRECT(18,"fast", "宝付认证支付"),

    /**
     * 宝付快捷支付
     */
    BAOFU(8,"protocol", "宝付快捷支付"),

    /**
     * 易宝投资通（快捷支付）
     */
    YEEPAY(9,"yeepay", "易宝投资通"),

    /**
     * 银联PC
     */
    UNIONPAY_PC(10,"union_pc", "银联PC"),
    /**
     * 银联WAP
     */
    UNIONPAY_WAP(11,"union_wap", "银联WAP"),

    /**
     * 银联APP
     */
    UNIONPAY_APP(12,"union_app", "银联APP"),

    /**
     * 银联
     */
    UNIONPAY(13,"union", "银联"),

    /**
     * 微众银行
     */
    WEBANK_APP(14,"webank_app", "微众银行"),

    /**
     * 富友支付
     */
    FUYOU_PC(15,"fuyou_pc", "富友支付"),

    /**
     * 彩之云支付
     */
    COLOR_PC(16,"color_pc", "彩之云支付"),

    /**
     * 快捷支付
     */
    QUICK_APP(17,"quick_app", "快捷APP支付"),

    QUICK_PC(18,"quick_pc", "快捷PC支付"),

    QUICK_WAP(19,"quick_wap", "快捷WAP支付"),

    CCB_PC(20,"ccb_pc", "龙支付PC支付"),

    SHANGHAI_APP(21,"shanghai_app", "上海银行快捷APP支付"),

    WX_NATIVE(22,"wx_native", "微信扫码支付"),

    WX_FACE(23,"wx_face", "微信人脸支付"),

    /**
     * 余额支付
     */
    BALANCE(0,"balance", "余额支付"),
    /**
     * 历史数据兼容渠道
     */
    GUANGHUI(91,"guanghui", "光汇支付"),
    ALI(92,"ali", "支付宝"),
    OFFLINE(93,"offline", "线下支付"),
    ONLINE(94,"online", "线上支付"),
    JD(95,"jd", "京东支付");

    private int code;
    private String channelName;
    private String desc;

    Channel(int code, String channelName, String desc)
    {
        this.code = code;
        this.channelName=channelName;
        this.desc=desc;
    }
    public int getCode(){
        return this.code;
    }

    public String getDesc() {
        return desc;
    }

    public String getChannelName() {
        return channelName;
    }

    public final static Channel valueFromCode(int code){
        for(Channel c:Channel.values()){
            if(c.getCode()==code){
                return c;
            }
        }
        return null;
    }
    public final static Channel valueFromName(String code){
        for(Channel c:Channel.values()){
            if(c.getChannelName().equalsIgnoreCase(code)){
                return c;
            }
        }
        return null;
    }

    public final static Channel valueFromNameString(String channelString,String channelName) {
        for(Channel c:Channel.values()){
            if(c.getChannelName().equalsIgnoreCase(channelName)&&c.toString().equals(channelString)){
                return c;
            }
        }
        return null;
    }

    public final static Channel valueFromNameString(String channelString) {
        for(Channel c:Channel.values()){
            if(c.toString().equalsIgnoreCase(channelString)){
                return c;
            }
        }
        return null;
    }
}

