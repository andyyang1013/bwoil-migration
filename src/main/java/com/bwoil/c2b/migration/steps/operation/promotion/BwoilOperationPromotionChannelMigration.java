package com.bwoil.c2b.migration.steps.operation.promotion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationPromotionChannel;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationPromotionChannel;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.tempClass.*;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Configuration("bwoilOperationPromotionChannelMigration")
public class BwoilOperationPromotionChannelMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationPromotionChannelMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationPromotionChannelMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationPromotionChannel, BwoilOperationPromotionChannel>().factory(stepBuilderFactory).name("运营服务-分销渠道列表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationPromotionChannelMigrationReader")
    public ItemReader<OriginOperationPromotionChannel> reader(@Qualifier("originDataSource") DataSource dataSource) {
        //================以逗号分割 油和券=========================//
        String sqlStr = "SELECT p.*,group_concat(sales_status) as statusList,group_concat(sales_type) as stype,group_concat(sales_text order by sales_type asc) AS temp FROM " +
                "sdb_promotion_channel AS p LEFT JOIN sdb_promotion_sales AS s ON p.channel_id = s.channel_id  GROUP BY p.channel_id";
        return new StepReaderBuilder<OriginOperationPromotionChannel>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationPromotionChannel.class).build();
    }

    @Bean("bwoilOperationPromotionChannelMigrationProcessor")
    public ItemProcessor<OriginOperationPromotionChannel, BwoilOperationPromotionChannel> processor() {
        return item -> {
            BwoilOperationPromotionChannel target = new BwoilOperationPromotionChannel();
            BeanUtils.copyProperties(item, target);
            target.setChannelId(item.getChannelId());
            target.setCreateTime(new Date(item.getCreateTime() * 1000));
            target.setUpdateTime(new Date(item.getCreateTime() * 1000));
            target.setStatus("0");

            ChannelBaseInfoReq baseInfo = new ChannelBaseInfoReq();  //分销注册页成功页配置
            H5ButtonSettingReq h5ButtonSet = new H5ButtonSettingReq(); //h5注册页配置

            if (item.getChannelBaseInfo() != null) {
                JSONObject channelBaseInfo = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getChannelBaseInfo()));
                Set<String> info = channelBaseInfo.keySet();
                for (String key : info) {
                    String value = channelBaseInfo.getString(key);
                    if (key.equals("share_image")) { /*======================h5注册页配置s==========================*/
                        h5ButtonSet.setShareImage(value);
                    } else if (key.equals("share_image_url")) {
                        h5ButtonSet.setShareImageUrl(value);
                    } else if (key.equals("reg_title_text")) {
                        h5ButtonSet.setRegTitleText(value);
                    } else if (key.equals("reg_invite_text")) {
                        h5ButtonSet.setRegInviteText(value);
                    } else if (key.equals("reg_btn_text")) {
                        h5ButtonSet.setRegBtnText(value);
                    } else if (key.equals("reg_btn_color")) {
                        h5ButtonSet.setRegBtnColor(value);
                    } else if (key.equals("reg_bg_color")) {
                        h5ButtonSet.setRegBgColor(value);
                    } else if (key.equals("reg_cy_intro")) {
                        h5ButtonSet.setRegCyIntro(value);
                    } else if (key.equals("reg_share_title")) {
                        h5ButtonSet.setRegShareTitle(value);
                    } else if (key.equals("promotion_desc")) {
                        h5ButtonSet.setPromotionDesc(value);
                    } else if (key.equals("reg_share_image")) {
                        h5ButtonSet.setRegShareImage(value); /*======================h5注册页配置e==========================*/
                    } else if (key.equals("reg_suc_image")) {  /*======================分销注册页成功页配置s==========================*/
                        baseInfo.setRegSucImage(value);
                    } else if (key.equals("reg_down_text")) {
                        baseInfo.setRegSucImage(value);
                    } else if (key.equals("reg_down_color")) {
                        baseInfo.setRegSucImage(value);
                    } else if (key.equals("is_share_check")) {
                        baseInfo.setIsShareCheck(value);
                    } else if (key.equals("share_btn_text")) {
                        baseInfo.setIsShareCheck(value);
                    } else if (key.equals("reg_suc_share_btn")) {
                        baseInfo.setIsShareCheck(value);
                    } else if (key.equals("reg_suc_rule")) {
                        baseInfo.setRegSucRule(value);
                    } else {
                        //todo
                    }
                }
                target.setH5ButtonSetting(JSON.toJSONString(h5ButtonSet));
            }
            if (item.getH5ButtonSetting() != null) {
                JSONObject h5ButtonSetting = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getH5ButtonSetting()));
                JSONObject tempH5 = h5ButtonSetting.getJSONObject("button2");
                Set<String> info = tempH5.keySet();
                for (String key : info) {
                    String value = tempH5.getString(key);
                    if (key.equals("text")) {
                        baseInfo.setButtonText(value);
                    } else if (key.equals("link")) {
                        baseInfo.setButtonLink(value);
                        baseInfo.setIsAddCheck("1");
                    }
                }
            }
            if (baseInfo != null) {
                String end = JSON.toJSONString(baseInfo);
                if (!end.equals("{}")) {
                    target.setChannelBaseInfo(end);
                }
            }
            if (item.getH5BottomnavSetting() != null) {
                JSONObject h5BottomnavSetting = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getH5BottomnavSetting()));
                H5BottomnavSettingReq nav = new H5BottomnavSettingReq();
                nav.setH5BottomnavOpen(h5BottomnavSetting.getString("is_open"));
                List<H5BottomnavReq> reqList = new ArrayList<>();
                JSONObject q1 = h5BottomnavSetting.getJSONObject("setting");
                for (int j = 0; j < q1.size(); j++) {
                    String tempJ = q1.getString(String.valueOf(j));
                    H5BottomnavReq req = JSON.parseObject(tempJ, H5BottomnavReq.class);
                    reqList.add(req);
                }
                nav.setH5Bottomnav(reqList);
                target.setH5BottomnavSetting(JSON.toJSONString(nav));
            }
            if (item.getIsfastreg().equals("true")) {
                target.setIsfastreg("1");
            } else {
                target.setIsfastreg("0");
            }
            if (item.getIsvalid().equals("true")) {
                target.setIsvalid("1");
            } else {
                target.setIsvalid("0");
            }
            if (item.getStype() != null) {
                String[] stype = item.getStype().split(","); //类型集合   //查询出来已经 按照油券明细表中sales_type排序 1红包(废弃数据) 2 现金券 3油
                String[] statusList = item.getStatusList().split(","); //状态集合
                String[] tpOilCoupList = item.getTemp().split(",");
                //索引index一致 index0 index1 inex2   //salesOilSend  salesCouponSend
                for (int i = 0; i < stype.length; i++) {
                    if (stype[i].equals("2") && !tpOilCoupList[i].equals("N;")) {  //券
                        List<RegisterCouponReq> recou = new ArrayList<>();
                        ChannelCouponReq coupon = new ChannelCouponReq();
                        coupon.setIsOpen(statusList[i]);
                        JSONObject t0 = JSON.parseObject(PhpSeriailizeUtil.getPhpString(tpOilCoupList[i]));
                        Set<String> it0 = t0.keySet();
                        for (String key : it0) {
                            RegisterCouponReq tmid = new RegisterCouponReq();
                            tmid.setCpnId(Integer.parseInt(key));
                            tmid.setGiveNum(Integer.parseInt(t0.getString(key)));
                            recou.add(tmid);
                        }
                        coupon.setCouponArray(recou);
                        target.setSalesCouponSend(JSON.toJSONString(coupon));
                    }
                    if (stype[i].equals("3") && !tpOilCoupList[i].equals("N;")) { //油
                        OilSendInfoReq oil = new OilSendInfoReq();
                        List<Integer> ids = new ArrayList<>();
                        oil.setIsOpen(statusList[i]);
                        JSONObject t1 = JSON.parseObject(PhpSeriailizeUtil.getPhpString(tpOilCoupList[i]));
                        Set<String> it1 = t1.keySet();
                        for (String key : it1) {
                            ids.add(t1.getIntValue(key));
                        }
                        oil.setOilId(ids);
                        target.setSalesOilSend(JSON.toJSONString(oil));
                    }
                }
            }
            return target;
        };
    }

    @Bean("bwoilOperationPromotionChannelMigrationWriter")
    public ItemWriter<BwoilOperationPromotionChannel> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_channel (channel_id, channel_type,department_parent_id,department_type_id, business_type, member_id, channel_name, isvalid, isfastreg, account_desc, rate_desc, promotion_desc, description, share_image, share_image_url, channel_base_info, h5_button_setting, h5_bottomnav_setting, sales_oil_send, sales_coupon_send, create_time, update_time, status) VALUES (:channelId, :channelType,:departmentType,:parentId, :businessType, :memberId, :channelName, :isvalid, :isfastreg, :accountDesc, :rateDesc, :promotionDesc, :description, :shareImage, :shareImageUrl, :channelBaseInfo, :h5ButtonSetting, :h5BottomnavSetting, :salesOilSend, :salesCouponSend, :createTime, :updateTime, :status)";
        return new StepWriterBuilder<BwoilOperationPromotionChannel>().dataSource(dataSource).sql(sqlStr).build();
    }
}
