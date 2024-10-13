package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.origin.OriginBaseSetting;
import com.bwoil.c2b.migration.steps.other.pojo.target.BwoilBaseSetting;
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

@Configuration("bwoilBaseSettingMigration")
public class BwoilBaseSettingMigration {


    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilBaseSettingMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilBaseSettingMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginBaseSetting, BwoilBaseSetting>().factory(stepBuilderFactory).name("基础服务-基础配置表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilBaseSettingMigrationReader")
    public ItemReader<OriginBaseSetting> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select * from sdb_base_setting";
        return new StepReaderBuilder<OriginBaseSetting>().dataSource(dataSource).sql(sql).mappedClass(OriginBaseSetting.class).multithreaded(true).sortKeys("app").build();
    }

    @Bean(name = "bwoilBaseSettingMigrationProcessor")
    public ItemProcessor<OriginBaseSetting, BwoilBaseSetting> processor() {
        return item -> {
            BwoilBaseSetting target = new BwoilBaseSetting();
            target.setcApp(item.getApp());
            target.setcKey(item.getKey());
            target.setcValue(item.getValue());
            return target;
        };
    }

    @Bean(name = "bwoilBaseSettingMigrationWriter")
    public ItemWriter<BwoilBaseSetting> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_base_setting (`app`, `key`, `value`) VALUES (:cApp, :cKey, :cValue)";
        return new StepWriterBuilder<BwoilBaseSetting>().dataSource(dataSource).sql(sqlStr).build();
    }
}
