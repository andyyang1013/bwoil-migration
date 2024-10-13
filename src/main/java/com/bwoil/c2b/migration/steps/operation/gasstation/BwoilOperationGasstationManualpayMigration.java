package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationManualpay;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationManualpay;
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

@Configuration("bwoilOperationGasstationManualpayMigration")
public class BwoilOperationGasstationManualpayMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationManualpayMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationManualpayMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationManualpay, BwoilOperationGasstationManualpay>().factory(stepBuilderFactory).name("运营服务-加油站pay的明细表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationManualpayMigrationReader")
    public ItemReader<OriginOperationGasstationManualpay> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_refuel_paybn";
        return new StepReaderBuilder<OriginOperationGasstationManualpay>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationManualpay.class).multithreaded(true).sortKeys("clearing_id").build();
    }

    @Bean("bwoilOperationGasstationManualpayMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationManualpay, BwoilOperationGasstationManualpay> processor() {
        return item -> {
            BwoilOperationGasstationManualpay target = new BwoilOperationGasstationManualpay();
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

    @Bean("bwoilOperationGasstationManualpayMigrationWriter")
    public ItemWriter<BwoilOperationGasstationManualpay> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_manual_paybn " +
                "(clearing_id, pay_bn, pay_type, station_name, total_amount, bank_name, bank_area, bank_account, station_id, status, message, complete_time, created_time, type, is_type) VALUES " +
                "(:clearingId, :payBn, :payType, :stationName, :totalAmount, :bankName, :bankArea, :bankAccount, :stationId, :status, :message, :completeTime, :createdTime, :type, :isType)";
        return new StepWriterBuilder<BwoilOperationGasstationManualpay>().dataSource(dataSource).sql(sqlStr).build();
    }
}
