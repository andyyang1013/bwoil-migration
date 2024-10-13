package com.bwoil.c2b.migration.steps.operation.cashcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin.OriginOperationCommandActivityJoin;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target.BwoilOperationCommandActivityJoin;
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

@Configuration("bwoilOperationCommandActivityJoinMigration")
public class BwoilOperationCommandActivityJoinMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCommandActivityJoinMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCommandActivityJoinMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationCommandActivityJoin, BwoilOperationCommandActivityJoin>().factory(stepBuilderFactory).name("运营服务-口令活动参与列表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationCommandActivityJoinMigrationReader")
    public ItemReader<OriginOperationCommandActivityJoin> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_command_activity_join";
        return new StepReaderBuilder<OriginOperationCommandActivityJoin>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCommandActivityJoin.class).multithreaded(true).sortKeys("join_id").build();
    }

    @Bean("bwoilOperationCommandActivityJoinMigrationProcessor")
    public ItemProcessor<OriginOperationCommandActivityJoin, BwoilOperationCommandActivityJoin> processor() {
        return item -> {
            BwoilOperationCommandActivityJoin target = new BwoilOperationCommandActivityJoin();
            target.setJoinId(item.getJoinId());
            target.setActivityId(item.getActivityId());
            target.setMemberId(item.getMemberId());
            target.setGiveCouponIds(item.getGiveCouponIds());
            target.setCreateTime(TimeStampUtil.getTimestampWithNull(item.getCreateTime()));
            return target;
        };
    }

    @Bean("bwoilOperationCommandActivityJoinMigrationWriter")
    public ItemWriter<BwoilOperationCommandActivityJoin> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_command_activity_join (join_id, activity_id, member_id, give_coupon_ids, create_time) " +
                "VALUES (:joinId, :activityId, :memberId, :giveCouponIds, :createTime)";
        return new StepWriterBuilder<BwoilOperationCommandActivityJoin>().dataSource(dataSource).sql(sqlStr).build();
    }

}
