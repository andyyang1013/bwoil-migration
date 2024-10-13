package com.bwoil.c2b.migration.steps.operation.oil;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.oil.pojo.origin.OriginOperationOilbox;
import com.bwoil.c2b.migration.steps.operation.oil.pojo.target.BwoilOperationOilbox;
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

@Configuration("bwoilOperationOilboxMigration")
public class BwoilOperationOilboxMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationOilboxMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationOilboxMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationOilbox, BwoilOperationOilbox>().factory(stepBuilderFactory).name("运营服务-我的油箱表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationOilboxMigrationReader")
    public ItemReader<OriginOperationOilbox> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT member_id, goil_give, goil_freeze FROM sdb_b2c_members WHERE goil_give != 0";
        return new StepReaderBuilder<OriginOperationOilbox>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationOilbox.class).multithreaded(true).sortKeys("member_id").build();
    }

    @Bean("bwoilOperationOilboxMigrationProcessor")
    public ItemProcessor<OriginOperationOilbox, BwoilOperationOilbox> processor() {
        return item -> {
            BwoilOperationOilbox target = new BwoilOperationOilbox();
            target.setMemberId(item.getMemberId());
            target.setOilMass(item.getGoilGive());
            target.setOilFrozen(item.getGoilFreeze());
            target.setStatus("0");
            return target;
        };
    }

    @Bean("bwoilOperationOilboxMigrationWriter")
    public ItemWriter<BwoilOperationOilbox> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_oilbox (member_id, oil_mass, oil_frozen, status) VALUES (:memberId, :oilMass, :oilFrozen, :status)";
        return new StepWriterBuilder<BwoilOperationOilbox>().dataSource(dataSource).sql(sqlStr).build();
    }
}
