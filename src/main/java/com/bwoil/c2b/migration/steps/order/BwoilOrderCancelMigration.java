package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.OrderCancelEntity;
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

@Configuration("bwoilOrderCancelMigration")
public class BwoilOrderCancelMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCancelMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCancelMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OrderCancelEntity, OrderCancelEntity>().factory(stepBuilderFactory).name("订单服务-订单取消表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCancelMigrationReader")
    public ItemReader<OrderCancelEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select  order_id, reason_type , reason_desc,");
        sqlStr.append("FROM_UNIXTIME(cancel_time) cancel_time")
                .append(" from sdb_b2c_order_cancel_reason");

        return new StepReaderBuilder<OrderCancelEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(OrderCancelEntity.class).multithreaded(true).sortKeys("order_id").build();
    }

    @Bean("bwoilOrderCancelMigrationProcessor")
    public ItemProcessor<OrderCancelEntity, OrderCancelEntity> processor() {
        return item -> {
            return item;
        };
    }

    @Bean("bwoilOrderCancelMigrationWriter")
    public ItemWriter<OrderCancelEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_cancel (order_id, reason_type, reason_desc, cancel_time) VALUES (:orderId, :reasonType, :reasonDesc, :cancelTime)";
        return new StepWriterBuilder<OrderCancelEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
