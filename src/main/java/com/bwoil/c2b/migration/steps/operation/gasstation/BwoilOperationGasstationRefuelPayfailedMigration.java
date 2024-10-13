package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationRefuelPayfailed;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationRefuelPayfailed;
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

@Configuration("bwoilOperationGasstationRefuelPayfailedMigration")
public class BwoilOperationGasstationRefuelPayfailedMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationRefuelPayfailedMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationRefuelPayfailedMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationRefuelPayfailed, BwoilOperationGasstationRefuelPayfailed>().factory(stepBuilderFactory).name("运营服务-加油站打款失败refuelPayfailed表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationRefuelPayfailedMigrationReader")
    public ItemReader<OriginOperationGasstationRefuelPayfailed> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_refuel_payfailed";
        return new StepReaderBuilder<OriginOperationGasstationRefuelPayfailed>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationRefuelPayfailed.class).multithreaded(true).sortKeys("payfailed_id").build();
    }

    @Bean("bwoilOperationGasstationRefuelPayfailedMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationRefuelPayfailed, BwoilOperationGasstationRefuelPayfailed> processor() {
        return item -> {
            BwoilOperationGasstationRefuelPayfailed target = new BwoilOperationGasstationRefuelPayfailed();
            BeanUtils.copyProperties(item, target);
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreatedTime(null);
            } else {
                target.setCreatedTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            if (item.getCompleteTime() == null || item.getCompleteTime() == 0) {
                target.setCompleteTime(null);
            } else {
                target.setCompleteTime(new Date(Long.parseLong(item.getCompleteTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationRefuelPayfailedMigrationWriter")
    public ItemWriter<BwoilOperationGasstationRefuelPayfailed> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_refuel_payfailed " +
                "(payfailed_id, order_id, pay_bn, pay_type, total_amount, status, message, complete_time, created_time) VALUES " +
                "(:payfailedId, :orderId, :payBn, :payType, :totalAmount, :status, :message, :completeTime, :createdTime)";
        return new StepWriterBuilder<BwoilOperationGasstationRefuelPayfailed>().dataSource(dataSource).sql(sqlStr).build();
    }
}
