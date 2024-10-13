package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.OilCardCycleEntity;
import org.apache.commons.lang3.time.DateUtils;
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

@Configuration("bwoilOrderCardCycleMigration")
public class BwoilOrderCardCycleMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCardCycleMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCardCycleMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OilCardCycleEntity, OilCardCycleEntity>().factory(stepBuilderFactory).name("订单服务-小票兑付、扫码加油卡交易表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCardCycleMigrationReader")
    public ItemReader<OilCardCycleEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select card_bn,member_id,cycle_amount,cycle_day,cycle_cash_remain,FROM_UNIXTIME(last_cash_time) last_cash_time, last_cash_time cash_time from sdb_ycy_card_cash_cycle";
        return new StepReaderBuilder<OilCardCycleEntity>().dataSource(dataSource).sql(sqlStr).mappedClass(OilCardCycleEntity.class).multithreaded(true).sortKeys("card_bn").build();
    }

    @Bean("bwoilOrderCardCycleMigrationProcessor")
    public ItemProcessor<OilCardCycleEntity, OilCardCycleEntity> processor() {
        return item -> {
            OilCardCycleEntity target = new OilCardCycleEntity();
            BeanUtils.copyProperties(item, target);
            if (0 == item.getCashTime()) {
                target.setLastCashTime(DateUtils.parseDate("20150101", "yyyyMMdd"));
            }
            return target;
        };
    }

    @Bean("bwoilOrderCardCycleMigrationWriter")
    public ItemWriter<OilCardCycleEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_card_cycle (card_bn,member_id,cycle_amount,cycle_day,cycle_cash_remain,last_cash_time) VALUES (:cardBn, :memberId, :cycleAmount, :cycleDay, :cycleCashRemain, :lastCashTime)";
        return new StepWriterBuilder<OilCardCycleEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
