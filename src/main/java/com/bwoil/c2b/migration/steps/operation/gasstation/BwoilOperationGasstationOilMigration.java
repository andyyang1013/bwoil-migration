package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationOil;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationOil;
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

@Configuration("bwoilOperationGasstationOilMigration")
public class BwoilOperationGasstationOilMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationOilMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationOilMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationOil, BwoilOperationGasstationOil>().factory(stepBuilderFactory).name("运营服务-加油站油品stationOil的信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationOilMigrationReader")
    public ItemReader<OriginOperationGasstationOil> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from  sdb_ycy_station_oil";
        return new StepReaderBuilder<OriginOperationGasstationOil>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationOil.class).multithreaded(true).sortKeys("station_oil_id").build();
    }

    @Bean("bwoilOperationGasstationOilMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationOil, BwoilOperationGasstationOil> processor() {
        return item -> {
            BwoilOperationGasstationOil target = new BwoilOperationGasstationOil();
            BeanUtils.copyProperties(item, target);
            target.setCreateName(item.getCreatedName());
            target.setId(item.getStationOilId());
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
            return target;
        };
    }

    @Bean("bwoilOperationGasstationOilMigrationWriter")
    public ItemWriter<BwoilOperationGasstationOil> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_oil (id, company_id, station_id, oil_id, modify_name, create_name, status, modify_time, create_time) VALUES (:id, :companyId, :stationId, :oilId, :modifyName, :createName, :status, :modifyTime, :createTime)";
        return new StepWriterBuilder<BwoilOperationGasstationOil>().dataSource(dataSource).sql(sqlStr).build();
    }
}
