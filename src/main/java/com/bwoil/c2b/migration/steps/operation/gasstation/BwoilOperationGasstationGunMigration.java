package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationGun;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationGun;
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

@Configuration("bwoilOperationGasstationGunMigration")
public class BwoilOperationGasstationGunMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationGunMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationGunMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationGun, BwoilOperationGasstationGun>().factory(stepBuilderFactory).name("运营服务-加油站油枪号Gun对应表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationGunMigrationReader")
    public ItemReader<OriginOperationGasstationGun> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_station_fuel_gun";
        return new StepReaderBuilder<OriginOperationGasstationGun>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationGun.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilOperationGasstationGunMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationGun, BwoilOperationGasstationGun> processor() {
        return item -> {
            BwoilOperationGasstationGun target = new BwoilOperationGasstationGun();
            BeanUtils.copyProperties(item, target);
            if (item.getUpdateTime() == null || item.getUpdateTime() == 0) {
                target.setUpdateTime(null);
            } else {
                target.setUpdateTime(new Date(Long.parseLong(item.getUpdateTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationGunMigrationWriter")
    public ItemWriter<BwoilOperationGasstationGun> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_gun (id, station_id, oil_id, gun_num, update_time) VALUES (:id, :stationId, :oilId, :gunNum, :updateTime)";
        return new StepWriterBuilder<BwoilOperationGasstationGun>().dataSource(dataSource).sql(sqlStr).build();
    }
}
