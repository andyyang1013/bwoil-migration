package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationManualtpye;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationManualtype;
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

@Configuration("bwoilOperationGasstationManualtpyeMigration")
public class BwoilOperationGasstationManualtpyeMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationManualtpyeMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationManualtpyeMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationManualtpye, BwoilOperationGasstationManualtype>().factory(stepBuilderFactory).name("运营服务-加油站type的明细表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationManualtpyeMigrationReader")
    public ItemReader<OriginOperationGasstationManualtpye> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_refuel_manualtype";
        return new StepReaderBuilder<OriginOperationGasstationManualtpye>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationManualtpye.class).multithreaded(true).sortKeys("type_id").build();
    }

    @Bean("bwoilOperationGasstationManualtpyeMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationManualtpye, BwoilOperationGasstationManualtype> processor() {
        return item -> {
            BwoilOperationGasstationManualtype target = new BwoilOperationGasstationManualtype();
            BeanUtils.copyProperties(item, target);
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreatedTime(null);
            } else {
                target.setCreatedTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationManualtpyeMigrationWriter")
    public ItemWriter<BwoilOperationGasstationManualtype> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_manualtype " +
                "(type_id, serialno, financial_type, costs_name, costs_introductions, status, created_time) VALUES " +
                "(:typeId, :serialno, :financialType, :costsName, :costsIntroductions, :status, :createdTime)";
        return new StepWriterBuilder<BwoilOperationGasstationManualtype>().dataSource(dataSource).sql(sqlStr).build();
    }
}
