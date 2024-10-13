package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationDiscountRule;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationDiscountRule;
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

@Configuration("bwoilOperationGasstationDiscountRuleMigration")
public class BwoilOperationGasstationDiscountRuleMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationDiscountRuleMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationDiscountRuleMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationDiscountRule, BwoilOperationGasstationDiscountRule>().factory(stepBuilderFactory).name("运营服务-加油站品牌公司折扣规则DiscountRule信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationDiscountRuleMigrationReader")
    public ItemReader<OriginOperationGasstationDiscountRule> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_station_discount_rule";
        return new StepReaderBuilder<OriginOperationGasstationDiscountRule>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationDiscountRule.class).multithreaded(true).sortKeys("station_discount_rule_id").build();
    }

    @Bean("bwoilOperationGasstationDiscountRuleMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationDiscountRule, BwoilOperationGasstationDiscountRule> processor() {
        return item -> {
            BwoilOperationGasstationDiscountRule target = new BwoilOperationGasstationDiscountRule();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getStationDiscountRuleId());
            if (item.getModifyTime() == null || item.getModifyTime() == 0) {
                target.setModifyTime(null);
            } else {
                target.setModifyTime(new Date(Long.parseLong(item.getModifyTime().toString()) * 1000L));
            }
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreatedTime(null);
            } else {
                target.setCreatedTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationDiscountRuleMigrationWriter")
    public ItemWriter<BwoilOperationGasstationDiscountRule> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_discount_rule (id, modify_name, company_id, created_name, status, modify_time, created_time, effective_time) VALUES (:id, :modifyName, :companyId, :createdName, :status, :modifyTime, :createdTime, :effectiveTime)";
        return new StepWriterBuilder<BwoilOperationGasstationDiscountRule>().dataSource(dataSource).sql(sqlStr).build();
    }
}
