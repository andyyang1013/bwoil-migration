package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationPromotionRelationship;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationPromotionRelationship;
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
import java.util.Calendar;
import java.util.Date;

@Configuration("bwoilOperationPromotionRelationshipMigration")
public class BwoilOperationPromotionRelationshipMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationPromotionRelationshipMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationPromotionRelationshipMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationPromotionRelationship, BwoilOperationPromotionRelationship>().factory(stepBuilderFactory).name("运营服务-推荐人分销Relationship关系表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationPromotionRelationshipMigrationReader")
    public ItemReader<OriginOperationPromotionRelationship> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT " +
                "  b.member_id, " +
                "  pm.login_account buyer_mobile, " +
                " b.realname_auth buyer_is_truename, " +
                " b.shop_member_bn buyer_shop_bn, " +
                " b.regtime buyer_register_time, " +
                " b.source buyer_source, " +
                " b.channel_id, " +
//                " b.duration_end_time duration, " +
                " c.duration, " +
                "  p.member_id promotion_member_id, " +
                "  pm2.login_account promotion_mobile, " +
                " p.regtime promotion_register_time, " +
                " p.shop_member_bn promotion_shop_bn " +
                "FROM " +
                " sdb_b2c_members b " +
                "LEFT JOIN sdb_b2c_members p ON b.pcode = p.shop_member_bn AND b.pcode <> ''  " +
                "LEFT JOIN sdb_pam_members pm ON b.member_id = pm.member_id AND pm.login_type = 'mobile' " +
                "LEFT JOIN sdb_pam_members pm2 ON p.member_id = pm2.member_id AND pm2.login_type = 'mobile' " +
                "LEFT JOIN sdb_promotion_config c ON b.channel_id = c.channel_id  " +
                "WHERE " +
                " b.pcode IS NOT NULL " +
                " AND b.pcode <> '' " +
                " AND p.member_id IS NOT NULL ";
        return new StepReaderBuilder<OriginOperationPromotionRelationship>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationPromotionRelationship.class).build();
    }

    @Bean("bwoilOperationPromotionRelationshipMigrationProcessor")
    public ItemProcessor<OriginOperationPromotionRelationship, BwoilOperationPromotionRelationship> processor() {
        return item -> {
            BwoilOperationPromotionRelationship target = new BwoilOperationPromotionRelationship();
            target.setBuyerMemberId(item.getMemberId());
            target.setBuyerMobile(item.getBuyerMobile());
            target.setBuyerIsTruename((item.getBuyerIsTruename() != null && item.getBuyerIsTruename().equals("true")) == true ? 1 : 0);
            target.setBuyerRegisterTime(item.getBuyerRegisterTime() == null ? null : new Date(item.getBuyerRegisterTime() * 1000));
            target.setBuyerSource(item.getBuyerSource());
            target.setBuyerShopBn(item.getBuyerShopBn());
            target.setChannelId(item.getChannelId());
            Integer duration = item.getDuration() == null ? 0 : item.getDuration();
            target.setDuration(duration);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.DATE, duration);
            target.setDurationModify(calendar.getTime());
            target.setPromotionMemberId(item.getPromotionMemberId());
            target.setPromotionMobile(item.getPromotionMobile());
            target.setPromotionRegisterTime(item.getPromotionRegisterTime() == null ? null : new Date(item.getPromotionRegisterTime()  * 1000));
            target.setPromotionShopBn(item.getPromotionShopBn());
            target.setStatus(0);
            return target;
        };
    }

    @Bean("bwoilOperationPromotionRelationshipMigrationWriter")
    public ItemWriter<BwoilOperationPromotionRelationship> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_relationship " +
                "(promotion_member_id, buyer_member_id,  buyer_is_truename, channel_id, duration, duration_modify, buyer_shop_bn, buyer_mobile, buyer_register_time, buyer_source, promotion_shop_bn, promotion_mobile, promotion_register_time , status) " +
                "VALUES (:promotionMemberId, :buyerMemberId,  :buyerIsTruename, :channelId, :duration, :durationModify, :buyerShopBn, :buyerMobile, :buyerRegisterTime, :buyerSource, :promotionShopBn, :promotionMobile, :promotionRegisterTime , :status)";
        return new StepWriterBuilder<BwoilOperationPromotionRelationship>().dataSource(dataSource).sql(sqlStr).build();
    }
}
