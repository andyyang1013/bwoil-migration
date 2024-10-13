package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationOilDiscount;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationOilDiscount;
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

@Configuration("bwoilOperationGasstationOilDiscountMigration")
public class BwoilOperationGasstationOilDiscountMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationOilDiscountMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationOilDiscountMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationOilDiscount, BwoilOperationGasstationOilDiscount>().factory(stepBuilderFactory).name("运营服务-加油站协议OilDiscount信息的明细-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationOilDiscountMigrationReader")
    public ItemReader<OriginOperationGasstationOilDiscount> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_oil_discount";
        return new StepReaderBuilder<OriginOperationGasstationOilDiscount>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationOilDiscount.class).multithreaded(true).sortKeys("oil_discount_id").build();
    }

    @Bean("bwoilOperationGasstationOilDiscountMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationOilDiscount, BwoilOperationGasstationOilDiscount> processor() {
        return item -> {
            BwoilOperationGasstationOilDiscount target = new BwoilOperationGasstationOilDiscount();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getOilDiscountId());
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreatedTime(null);
            } else {
                target.setCreatedTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationOilDiscountMigrationWriter")
    public ItemWriter<BwoilOperationGasstationOilDiscount> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_oil_discount (id, agreement_id, discount_id, station_id, oil_id, data, created_time) VALUES (:id, :agreementId, :discountId, :stationId, :oilId, :data, :createdTime)";
        return new StepWriterBuilder<BwoilOperationGasstationOilDiscount>().dataSource(dataSource).sql(sqlStr).build();
    }
}
