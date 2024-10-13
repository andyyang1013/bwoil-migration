package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationBaseMember;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationBaseMember;
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

/**
 * @ClassName bwoilOperationBaseMemberMigration
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/27 9:59
 **/
@Configuration("bwoilOperationBaseMemberMigration")
public class BwoilOperationBaseMemberMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationBaseMemberMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationBaseMemberMigrationStep")
    public Step step(@Qualifier("baseMemberMigrationReader") ItemReader<OriginOperationBaseMember> reader,
                     @Qualifier("baseMemberMigrationProcessor") ItemProcessor<OriginOperationBaseMember, BwoilOperationBaseMember> processor,
                     @Qualifier("baseMemberMigrationWriter") ItemWriter<BwoilOperationBaseMember> writer) {
        return new StepBuilder<OriginOperationBaseMember, BwoilOperationBaseMember>().factory(stepBuilderFactory).name("运营服务-基础会员表-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("baseMemberMigrationReader")
    public ItemReader<OriginOperationBaseMember> reader(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "SELECT  b.member_id,  b.realname buyer_member_name,  b.shop_bn buyer_shop_bn,  b.mobile buyer_mobile,  b.register_time buyer_register_time,  b.source buyer_source,  b.realname_auth buyer_is_truename,  b.auth_time buyer_auth_time,  p.member_id promotion_member_id,  p.realname promotion_member_name,  p.shop_bn promotion_shop_bn,  p.mobile promotion_mobile,  p.register_time promotion_register_time,  b.extra_channel_id channel_department_id,  d.parent_id channel_department_parent_id FROM  bwoil_member b LEFT JOIN `bwoil_member` p ON b.pcode = p.shop_bn LEFT JOIN bwoil_operation_promotion_channel_department d ON b.extra_channel_id = d.id";
        return new StepReaderBuilder<OriginOperationBaseMember>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationBaseMember.class).multithreaded(true).sortKeys("b.member_id").build();
    }

    @Bean("baseMemberMigrationProcessor")
    public ItemProcessor<OriginOperationBaseMember, BwoilOperationBaseMember> processor() {
        return item -> {
            BwoilOperationBaseMember target = new BwoilOperationBaseMember();
            BeanUtils.copyProperties(item, target);
            target.setBuyerMemberId(item.getMemberId());
            return target;
        };
    }

    @Bean("baseMemberMigrationWriter")
    public ItemWriter<BwoilOperationBaseMember> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = " INSERT INTO bwoil_operation_base_member " +
                " (buyer_member_id,buyer_member_name,buyer_shop_bn,buyer_mobile,buyer_register_time,buyer_source, " +
                " buyer_is_truename,buyer_auth_time,promotion_member_id,promotion_member_name,promotion_shop_bn, " +
                " promotion_mobile,promotion_register_time,channel_department_id,channel_department_parent_id) " +
                " VALUES " +
                " (:buyerMemberId,:buyerMemberName,:buyerShopBn,:buyerMobile,:buyerRegisterTime,:buyerSource, " +
                " :buyerIsTruename,:buyerAuthTime,:promotionMemberId,:promotionMemberName,:promotionShopBn, " +
                " :promotionMobile,:promotionRegisterTime,:channelDepartmentId,:channelDepartmentParentId)";
        return new StepWriterBuilder<BwoilOperationBaseMember>().dataSource(dataSource).sql(sqlStr).build();
    }
}
