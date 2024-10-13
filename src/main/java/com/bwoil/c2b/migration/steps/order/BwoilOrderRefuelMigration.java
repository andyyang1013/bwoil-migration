package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RefuelOrderEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

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

@Configuration("bwoilOrderRefuelMigration")
public class BwoilOrderRefuelMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRefuelMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRefuelMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RefuelOrderEntity, RefuelOrderEntity>().factory(stepBuilderFactory).name("订单服务-加油表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRefuelMigrationReader")
    public ItemReader<RefuelOrderEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("order_id,order_amount,order_cash,proid,num,charge_type,provider_type,")
                .append("card_bn,card_tel,card_holder,sporder_id,member_id,card_trade_bn,")
                .append("ycy_card_bn,fuel_status,disabled,retry,memo,0 as status,")
                .append("createtime as create_time ")
                .append(" from sdb_b2c_refuel_order");

        return new StepReaderBuilder<RefuelOrderEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RefuelOrderEntity.class).multithreaded(true).sortKeys("order_id").build();
    }

    @Bean("bwoilOrderRefuelMigrationProcessor")
    public ItemProcessor<RefuelOrderEntity, RefuelOrderEntity> processor() {
        return item -> {
            RefuelOrderEntity target = new RefuelOrderEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderRefuelMigrationWriter")
    public ItemWriter<RefuelOrderEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_refuel (id,order_amount,order_cash,proid,num,charge_type,provider_type,card_bn,card_tel,card_holder,sporder_id,member_id,card_trade_bn,ycy_card_bn,fuel_status,disabled,retry,memo,status,create_time,last_update_time)"
                + " VALUES (:orderId, :orderAmount, :orderCash, :proid, :num, :chargeType, :providerType, :cardBn, :cardTel, :cardHolder, :sporderId, :memberId, :cardTradeBn, :ycyCardBn, :fuelStatus, :disabled, :retry, :memo, :status, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RefuelOrderEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RefuelOrderEntity target) {
    	target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
        target.setLastUpdateTime(target.getCreateTime());
        target.setDisabled("TRUE".equalsIgnoreCase(target.getDisabled()) ? "Y" : "N");
        target.setRetry("TRUE".equalsIgnoreCase(target.getRetry()) ? "Y" : "N");
    }
}
