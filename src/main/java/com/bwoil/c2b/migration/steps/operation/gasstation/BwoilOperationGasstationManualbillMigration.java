package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationManualbill;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationManualbill;
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

@Configuration("bwoilOperationGasstationManualbillMigration")
public class BwoilOperationGasstationManualbillMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationManualbillMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationManualbillMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationManualbill, BwoilOperationGasstationManualbill>().factory(stepBuilderFactory).name("运营服务-加油站bill明细表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationManualbillMigrationReader")
    public ItemReader<OriginOperationGasstationManualbill> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_refuel_manualbill";
        return new StepReaderBuilder<OriginOperationGasstationManualbill>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationManualbill.class).multithreaded(true).sortKeys("bill_id").build();
    }

    @Bean("bwoilOperationGasstationManualbillMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationManualbill, BwoilOperationGasstationManualbill> processor() {
        return item -> {
            BwoilOperationGasstationManualbill target = new BwoilOperationGasstationManualbill();
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

    @Bean("bwoilOperationGasstationManualbillMigrationWriter")
    public ItemWriter<BwoilOperationGasstationManualbill> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_manualbill " +
                "(bill_id, account_no, pay_bn, financial_type, pay_type, costs_name, station_name, total_amount, bank_name, bank_account, station_id, costs_introductions, status, message, complete_time, created_time) VALUES " +
                "(:billId, :accountNo, :payBn, :financialType, :payType, :costsName, :stationName, :totalAmount, :bankName, :bankAccount, :stationId, :costsIntroductions, :status, :message, :completeTime, :createdTime)";
        return new StepWriterBuilder<BwoilOperationGasstationManualbill>().dataSource(dataSource).sql(sqlStr).build();
    }
}
