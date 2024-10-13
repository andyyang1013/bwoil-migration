package com.bwoil.c2b.migration.steps.operation.auth;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.auth.pojo.origin.OriginOperationAuth;
import com.bwoil.c2b.migration.steps.operation.auth.pojo.target.BwoilOperationAuth;
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

@Configuration("bwoilOperationAuthMigration")
public class BwoilOperationAuthMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationAuthMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationAuthMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationAuth, BwoilOperationAuth>().factory(stepBuilderFactory).name("运营服务-app版本-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationAuthMigrationReader")
    public ItemReader<OriginOperationAuth> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_wap_auth";
        return new StepReaderBuilder<OriginOperationAuth>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationAuth.class).multithreaded(true).sortKeys("auth_id").build();
    }

    @Bean("bwoilOperationAuthMigrationProcessor")
    public ItemProcessor<OriginOperationAuth, BwoilOperationAuth> processor() {
        return item -> {
            BwoilOperationAuth target = new BwoilOperationAuth();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getAuthId());
            target.setCreateTime(new Date(item.getAddTime() * 1000));
            target.setUpdateTime(new Date());
            target.setStatus("0");
            return target;
        };
    }

    @Bean("bwoilOperationAuthMigrationWriter")
    public ItemWriter<BwoilOperationAuth> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_auth (id, plat, version, auth, description, create_time, update_time, status) " +
                "VALUES (:id, :plat, :version, :auth, :description, :createTime, :updateTime, :status)";
        return new StepWriterBuilder<BwoilOperationAuth>().dataSource(dataSource).sql(sqlStr).build();
    }
}
