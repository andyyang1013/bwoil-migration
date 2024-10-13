package com.bwoil.c2b.migration.steps.operation.refuel;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.refuel.pojo.origin.OriginOperationRefuelProducts;
import com.bwoil.c2b.migration.steps.operation.refuel.pojo.target.BwoilOperationRefuelProducts;
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

@Configuration("bwoilOperationRefuelProductsMigration")
public class BwoilOperationRefuelProductsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationRefuelProductsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationRefuelProductsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationRefuelProducts, BwoilOperationRefuelProducts>().factory(stepBuilderFactory).name("运营服务-加油卡代充产品-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationRefuelProductsMigrationReader")
    public ItemReader<OriginOperationRefuelProducts> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_refuel_products";
        return new StepReaderBuilder<OriginOperationRefuelProducts>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationRefuelProducts.class).multithreaded(true).sortKeys("product_id").build();
    }

    @Bean("bwoilOperationRefuelProductsMigrationProcessor")
    public ItemProcessor<OriginOperationRefuelProducts, BwoilOperationRefuelProducts> processor() {
        return item -> {
            BwoilOperationRefuelProducts target = new BwoilOperationRefuelProducts();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getProductId());
            if (item.getDisabled().equals("true")) {
                target.setStatus("-1");
            } else {
                target.setStatus("0");
            }
            target.setCreatetime(new Date(item.getCreatetime() * 1000));
            return target;
        };
    }

    @Bean("bwoilOperationRefuelProductsMigrationWriter")
    public ItemWriter<BwoilOperationRefuelProducts> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_refuel_products (id, proid, amount, price_sale, price, num, charge_type, provider_type, createtime, status) " +
                "VALUES (:id, :proid, :amount, :priceSale, :price, :num, :chargeType, :providerType, :createtime, :status)";
        return new StepWriterBuilder<BwoilOperationRefuelProducts>().dataSource(dataSource).sql(sqlStr).build();
    }
}
