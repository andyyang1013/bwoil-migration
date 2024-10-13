package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationCoupons;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationCoupons;
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
import java.util.Date;

@Configuration("bwoilOperationGasstationCouponsMigration")
public class BwoilOperationGasstationCouponsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationCouponsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationCouponsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationCoupons, BwoilOperationGasstationCoupons>().factory(stepBuilderFactory).name("运营服务-加油站的优惠Coupons配置-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationCouponsMigrationReader")
    public ItemReader<OriginOperationGasstationCoupons> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_gasstation_coupons";
        return new StepReaderBuilder<OriginOperationGasstationCoupons>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationCoupons.class).multithreaded(true).sortKeys("coupon_id").build();
    }

    @Bean("bwoilOperationGasstationCouponsMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationCoupons, BwoilOperationGasstationCoupons> processor() {
        return item -> {
            BwoilOperationGasstationCoupons target = new BwoilOperationGasstationCoupons();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getCouponId());
            if (item.getCreateTime() == null || item.getCreateTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreateTime().toString()) * 1000L));
            }
            if (item.getEndDate() == null || item.getEndDate() == 0) {
                target.setEndDate(null);
            } else {
                target.setEndDate(new Date(Long.parseLong(item.getEndDate().toString()) * 1000L));
            }
            if (item.getStartDate() == null || item.getStartDate() == 0) {
                target.setStartDate(null);
            } else {
                target.setStartDate(new Date(Long.parseLong(item.getStartDate().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationCouponsMigrationWriter")
    public ItemWriter<BwoilOperationGasstationCoupons> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_coupons (id, channel_id, station_id, coupon_rate, start_date, end_date, isvalid, create_time) VALUES " +
                "(:id, :channelId, :stationId, :couponRate, :startDate, :endDate, :isvalid, :createTime)";
        return new StepWriterBuilder<BwoilOperationGasstationCoupons>().dataSource(dataSource).sql(sqlStr).build();
    }
}
