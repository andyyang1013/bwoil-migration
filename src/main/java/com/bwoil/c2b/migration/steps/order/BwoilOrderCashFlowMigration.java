package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.CashFlowEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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

@Configuration("bwoilOrderCashFlowMigration")
public class BwoilOrderCashFlowMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCashFlowMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCashFlowMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CashFlowEntity, CashFlowEntity>().factory(stepBuilderFactory).name("订单服务-资金流水表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCashFlowMigrationReader")
    public ItemReader<CashFlowEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("log_id,member_id,op_id,money,message,payment_id,order_id,paymethod,memo,trade_bn,")
                 .append("card_bn,trade_type,log_type,import_money,explode_money,member_advance,valid_advance,is_freeze,disabled,account_type,") 
                 .append("0 as fee_amt,")
                .append("0 as fee_rate,")
                .append("mtime")
                .append(" from sdb_b2c_member_advance ");
        return new StepReaderBuilder<CashFlowEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(CashFlowEntity.class).multithreaded(true).sortKeys("log_id").build();
    }

    @Bean("bwoilOrderCashFlowMigrationProcessor")
    public ItemProcessor<CashFlowEntity, CashFlowEntity> processor() {
        return item -> {
            CashFlowEntity target = new CashFlowEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderCashFlowMigrationWriter")
    public ItemWriter<CashFlowEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_cash_flow (log_id,member_id,op_id,money,message,payment_id,order_id,paymethod,memo,trade_bn,card_bn,trade_type,log_type,import_money,explode_money,member_advance,valid_advance,fee_amt,fee_rate,is_freeze,disabled,account_type,create_time,last_update_time)"
                + "VALUES (:logId, :memberId, :opId, :money, :message, :paymentId, :orderId, :paymethod, :memo, :tradeBn, :cardBn, :tradeType, :logType, :importMoney, :explodeMoney, :memberAdvance, :validAdvance, :feeAmt, :feeRate, :isFreeze, :disabled, :accountType, :mtime, :mtime)";
        return new StepWriterBuilder<CashFlowEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(CashFlowEntity target) {
        target.setIsFreeze("FALSE".equalsIgnoreCase(target.getIsFreeze()) ? "N" : "Y");
        target.setDisabled("FALSE".equalsIgnoreCase(target.getDisabled()) ? "N" : "Y");
        
        target.setMtime(TimeStampUtil.convertDate(target.getMtime()));
    }
    
}
