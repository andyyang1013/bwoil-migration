package com.bwoil.c2b.migration.steps.operation.idfa;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.idfa.pojo.origin.OriginOperationIdfa;
import com.bwoil.c2b.migration.steps.operation.idfa.pojo.target.BwoilOperationIdfa;
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

@Configuration("bwoilOperationIdfaMigration")
public class BwoilOperationIdfaMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationIdfaMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationIdfaMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationIdfa, BwoilOperationIdfa>().factory(stepBuilderFactory).name("运营服务-IDFA排重表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationIdfaMigrationReader")
    public ItemReader<OriginOperationIdfa> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_promotion_openapi";
        return new StepReaderBuilder<OriginOperationIdfa>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationIdfa.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilOperationIdfaMigrationProcessor")
    public ItemProcessor<OriginOperationIdfa, BwoilOperationIdfa> processor() {
        return item -> {
            BwoilOperationIdfa target = new BwoilOperationIdfa();
            String isvalid = item.getIsvalid();
            if (isvalid.equals("true")) {
                target.setStatus(0);
            } else if (isvalid.equals("false")) {
                target.setStatus(-1);
            }
            target.setCreateTime(new Date(item.getCreateTime() * 1000));
            target.setName(item.getAppName());
            BeanUtils.copyProperties(item, target);
            return target;
        };
    }

    @Bean("bwoilOperationIdfaMigrationWriter")
    public ItemWriter<BwoilOperationIdfa> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_idfa (id, name, app_id, app_secret, create_time, status) VALUES (:id, :name, :appId, :appSecret, :createTime, :status)";
        return new StepWriterBuilder<BwoilOperationIdfa>().dataSource(dataSource).sql(sqlStr).build();
    }
}
