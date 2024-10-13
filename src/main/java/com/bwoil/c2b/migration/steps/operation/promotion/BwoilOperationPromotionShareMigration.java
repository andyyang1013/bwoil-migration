package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationPromotionShare;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationPromotionShare;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

@Configuration("bwoilOperationPromotionShareMigration")
public class BwoilOperationPromotionShareMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationPromotionShareMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationPromotionShareMigrationStep")
    public Step step(@Qualifier("promotionShareMigrationReader") ItemReader<OriginOperationPromotionShare> reader,
                     @Qualifier("promotionShareMigrationProcessor") ItemProcessor<OriginOperationPromotionShare, BwoilOperationPromotionShare> processor,
                     @Qualifier("promotionShareMigrationWriter") ItemWriter<BwoilOperationPromotionShare> writer) {
        return new StepBuilder<OriginOperationPromotionShare, BwoilOperationPromotionShare>().factory(stepBuilderFactory).name("运营服务-分享好友佣金-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("promotionShareMigrationReader")
    public ItemReader<OriginOperationPromotionShare> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT " +
                            " s.share_id, " +
                            " s.buyer_member_id, " +
                            " m.shop_member_bn buyer_shop_bn, " +
                            " pam.login_account buyer_mobile, " +
                            " m.regtime buyer_register_time, " +
                            " m.source buyer_source, " +
                            " s.is_truename buyer_is_truename, " +
                            " s.truename_time buyer_auth_time, " +
                            " s.promotion_member_id, " +
                            " m2.shop_member_bn promotion_shop_bn, " +
                            " pam2.login_account promotion_mobile, " +
                            " m2.regtime promotion_register_time, " +
                            " s.channel_id, " +
                            " s.share_type, " +
                            " s.profit, " +
                            " s.STATUS achieve_status, " +
                            " s.report_time achieve_time, " +
                            " s.STATUS commission_status, " +
                            " s.report_time commission_time, " +
                            " s.create_time " +
                        " FROM " +
                        " sdb_promotion_share s " +
                        " LEFT JOIN sdb_b2c_members m ON s.buyer_member_id = m.member_id " +
                        " LEFT JOIN sdb_b2c_members m2 ON s.promotion_member_id = m2.member_id " +
                        " LEFT JOIN sdb_pam_members pam ON pam.member_id = s.buyer_member_id AND pam.login_type='mobile'  " +
                        " LEFT JOIN sdb_pam_members pam2 ON pam2.member_id = s.promotion_member_id AND pam2.login_type='mobile' ";
        return new StepReaderBuilder<OriginOperationPromotionShare>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationPromotionShare.class).multithreaded(true).sortKeys("s.share_id").build();
    }

    @Bean("promotionShareMigrationProcessor")
    public ItemProcessor<OriginOperationPromotionShare, BwoilOperationPromotionShare> processor() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return item -> {
            BwoilOperationPromotionShare target = new BwoilOperationPromotionShare();
            // 被推广人ID
            target.setBuyerMemberId(item.getBuyerMemberId());
            // 被推广会员编号
            target.setBuyerShopBn(item.getBuyerShopBn());
            // 被推广账号
            target.setBuyerMobile(item.getBuyerMobile());
            // 推广人ID
            target.setPromotionMemberId(item.getPromotionMemberId());
            // 佣金类型
            switch (item.getShareType()) {
                // 注册佣金
                case "1":
                    target.setShareType(1L);
                    break;
                // 邀请好友返现
                case "2":
                    target.setShareType(2L);
                    break;
                default:
                    break;
            }
            // 佣金金额
            target.setProfit(item.getProfit());
            // 被推广会员注册时间
            target.setBuyerRegisterTime(TimeStampUtil.getTimestampWithNull(item.getBuyerRegisterTime()));
            // 被推广人注册来源
            target.setBuyerSource(item.getBuyerSource());
            // 是否实名
            switch (item.getBuyerIsTruename()) {
                // 未实名
                case "0":
                    target.setBuyerIsTruename(0L);
                    break;
                // 已实名
                case "1":
                    target.setBuyerIsTruename(1L);
                    break;
                default:
                    break;
            }
            // 认证时间
            target.setBuyerAuthTime(TimeStampUtil.getTimestampWithNull(item.getBuyerAuthTime()));
            // 推广会员编号
            target.setPromotionShopBn(item.getPromotionShopBn());
            // 推广账号
            target.setPromotionMobile(item.getPromotionMobile());
            // 推广会员注册时间
            target.setPromotionRegisterTime(TimeStampUtil.getTimestampWithNull(item.getPromotionRegisterTime()));
            // 渠道ID
            target.setChannelId(item.getChannelId());
            // 状态
            switch (item.getAchieveStatus()) {
                // 未达标
                case "0":
                    target.setAchieveStatus(0L);
                    break;
                // 达标
                case "1":
                    target.setAchieveStatus(1L);
                    break;
                default:
                    break;
            }
            // 达标时间
            target.setAchieveTime(TimeStampUtil.getTimestampWithNull(item.getAchieveTime()));
            // 结算状态
            switch (item.getAchieveStatus()) {
                // 未达标
                case "0":
                    target.setCommissionStatus(0L);
                    break;
                // 达标
                case "1":
                    target.setCommissionStatus(1L);
                    break;
                default:
                    break;
            }
            // 结算时间
            target.setCommissionTime(TimeStampUtil.getTimestampWithNull(item.getCommissionTime()));
            // 是否异常
            target.setIsException(0L);
            // 创建时间
            target.setCreateTime(TimeStampUtil.getTimestampWithDefault(item.getCreateTime()));
            return target;
        };
    }

    @Bean("promotionShareMigrationWriter")
    public ItemWriter<BwoilOperationPromotionShare> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_share  " +
                "(buyer_member_id,buyer_shop_bn,buyer_mobile,buyer_register_time,buyer_source,buyer_is_truename,buyer_auth_time, " +
                "promotion_member_id,promotion_shop_bn,promotion_mobile,promotion_register_time, " +
                "channel_id,share_type,profit,achieve_status,achieve_time,commission_status,commission_time,create_time) " +
                "VALUES " +
                "(:buyerMemberId,:buyerShopBn,:buyerMobile,:buyerRegisterTime,:buyerSource,:buyerIsTruename,:buyerAuthTime, " +
                ":promotionMemberId,:promotionShopBn,:promotionMobile,:promotionRegisterTime, " +
                ":channelId,:shareType,:profit,:achieveStatus,:achieveTime,:commissionStatus,:commissionTime,:createTime)";
        return new StepWriterBuilder<BwoilOperationPromotionShare>().dataSource(dataSource).sql(sqlStr).build();
    }
}
