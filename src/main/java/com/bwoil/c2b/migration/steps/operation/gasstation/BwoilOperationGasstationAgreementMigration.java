package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationAgreement;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationAgreement;
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

@Configuration("bwoilOperationGasstationAgreementMigration")
public class BwoilOperationGasstationAgreementMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationAgreementMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationAgreementMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationAgreement, BwoilOperationGasstationAgreement>().factory(stepBuilderFactory).name("运营服务-加油站协议Agreement相关信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationAgreementMigrationReader")
    public ItemReader<OriginOperationGasstationAgreement> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_station_agreement";
        return new StepReaderBuilder<OriginOperationGasstationAgreement>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationAgreement.class).multithreaded(true).sortKeys("agreement_id").build();
    }

    @Bean("bwoilOperationGasstationAgreementMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationAgreement, BwoilOperationGasstationAgreement> processor() {
        return item -> {
            BwoilOperationGasstationAgreement target = new BwoilOperationGasstationAgreement();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getAgreementId());
            target.setCreateName(item.getCreatedName());
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            if (item.getModifyTime() == null || item.getModifyTime() == 0) {
                target.setModifyTime(null);
            } else {
                target.setModifyTime(new Date(Long.parseLong(item.getModifyTime().toString()) * 1000L));
            }
            if (item.getStartDate() == null || item.getStartDate() == 0) {
                target.setStartDate(null);
            } else {
                target.setStartDate(new Date(Long.parseLong(item.getStartDate().toString()) * 1000L));
            }
            if (item.getEndDate() == null || item.getEndDate() == 0) {
                target.setEndDate(null);
            } else {
                target.setEndDate(new Date(Long.parseLong(item.getEndDate().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationAgreementMigrationWriter")
    public ItemWriter<BwoilOperationGasstationAgreement> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_agreement (id, company_id, station_id, modify_name, create_name, status, modify_time, create_time, start_date, end_date) VALUES (:id, :companyId, :stationId, :modifyName, :createName, :status, :modifyTime, :createTime, :startDate, :endDate)";
        return new StepWriterBuilder<BwoilOperationGasstationAgreement>().dataSource(dataSource).sql(sqlStr).build();
    }
}
