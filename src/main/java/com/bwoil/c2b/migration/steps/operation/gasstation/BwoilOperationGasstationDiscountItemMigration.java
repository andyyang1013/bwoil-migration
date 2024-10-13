package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationDiscountItem;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationDiscountItem;
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

@Configuration("bwoilOperationGasstationDiscountItemMigration")
public class BwoilOperationGasstationDiscountItemMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationDiscountItemMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationDiscountItemMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationDiscountItem, BwoilOperationGasstationDiscountItem>().factory(stepBuilderFactory).name("运营服务-加油站品牌公司折扣规则DiscountItem的明细-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationDiscountItemMigrationReader")
    public ItemReader<OriginOperationGasstationDiscountItem> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_station_discount_rule_item";
        return new StepReaderBuilder<OriginOperationGasstationDiscountItem>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationDiscountItem.class).multithreaded(true).sortKeys("station_discount_rule_item_id").build();
    }

    @Bean("bwoilOperationGasstationDiscountItemMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationDiscountItem, BwoilOperationGasstationDiscountItem> processor() {
        return item -> {
            BwoilOperationGasstationDiscountItem target = new BwoilOperationGasstationDiscountItem();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getStationDiscountRuleItemId());
            target.setDiscountId(item.getStationDiscountRuleId());
            return target;
        };
    }

    @Bean("bwoilOperationGasstationDiscountItemMigrationWriter")
    public ItemWriter<BwoilOperationGasstationDiscountItem> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_discount_item (id, discount_id, oil_id, litre_limit, discount_money) VALUES (:id, :discountId, :oilId, :litreLimit, :discountMoney)";
        return new StepWriterBuilder<BwoilOperationGasstationDiscountItem>().dataSource(dataSource).sql(sqlStr).build();
    }
}
