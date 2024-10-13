package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.CardTradeEntity;
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

@Configuration("bwoilOrderCardTradeMigration")
public class BwoilOrderCardTradeMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCardTradeMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCardTradeMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CardTradeEntity, CardTradeEntity>().factory(stepBuilderFactory).name("订单服务-卡交易表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCardTradeMigrationReader")
    public ItemReader<CardTradeEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select trade_id as id,trade_bn,card_bn,product_name,member_id,station_id,");
        sqlStr.append("trade_type,trade_sub_type,audit_status,trade_nums,price,pay,trade_price,income,cost,cpns_money,goil_money,discount,reduce_money,profit,pure_profit,fixed_profit,float_profit,commision as fee,to_member_id,total_cash_amount,total_buyback_amount,")
                .append("total_transfer_amount,remain_amount,total_cash_litre,total_buyback_litre,total_transfer_litre,remain_litre,give_nums,remark,invoice_attachment,")
                .append("FROM_UNIXTIME(trade_start_time) as trade_start_time,trade_start_time as start_time,")
                .append("FROM_UNIXTIME(trade_end_time) as trade_end_time,trade_end_time as end_time,")
                .append("FROM_UNIXTIME(lastmodify) as last_update_time, lastmodify as update_time")
                .append(" from sdb_ycy_card_trade");
        return new StepReaderBuilder<CardTradeEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(CardTradeEntity.class).multithreaded(true).sortKeys("trade_bn").build();
    }

    @Bean("bwoilOrderCardTradeMigrationProcessor")
    public ItemProcessor<CardTradeEntity, CardTradeEntity> processor() {
        return item -> {
            CardTradeEntity target = new CardTradeEntity();
            BeanUtils.copyProperties(item, target);
            if (target.getStartTime().equals("0")) {
                target.setStartTime("0000-00-00 00:00:00");
            } else {
                target.setStartTime(DateFormatUtils.format(target.getTradeStartTime(), "yyyy-MM-dd HH:mm:ss"));
            }
            if (target.getEndTime().equals("0")) {
                target.setEndTime("0000-00-00 00:00:00");
            } else {
                target.setEndTime(DateFormatUtils.format(target.getTradeEndTime(), "yyyy-MM-dd HH:mm:ss"));
            }
            if (target.getUpdateTime().equals("0")) {
                target.setUpdateTime("0000-00-00 00:00:00");
            } else {
                target.setUpdateTime(DateFormatUtils.format(target.getLastUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            }
            return target;
        };
    }

    @Bean("bwoilOrderCardTradeMigrationWriter")
    public ItemWriter<CardTradeEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_card_trade (id,trade_bn,card_bn,product_name,member_id,station_id,trade_type,trade_sub_type,audit_status,trade_nums,price,pay,trade_price,income,cost,cpns_money,goil_money,discount,reduce_money,profit,pure_profit,fixed_profit,float_profit,fee,to_member_id,total_cash_amount,total_buyback_amount,total_transfer_amount,remain_amount,total_cash_litre,total_buyback_litre,total_transfer_litre,remain_litre,give_nums,remark,invoice_attachment,trade_start_time,trade_end_time,last_update_time)"
                + " VALUES (:id, :tradeBn, :cardBn, :productName, :memberId, :stationId, :tradeType, :tradeSubType, :auditStatus, :tradeNums, :price, :pay, :tradePrice, :income, :cost, :cpnsMoney, :goilMoney, :discount, :reduceMoney, :profit, :pureProfit, :fixedProfit, :floatProfit, :fee, :toMemberId, :totalCashAmount, :totalBuybackAmount, :totalTransferAmount, :remainAmount, :totalCashLitre, :totalBuybackLitre, :totalTransferLitre, :remainLitre, :giveNums, :remark, :invoiceAttachment, :startTime, :endTime, :updateTime)";
        return new StepWriterBuilder<CardTradeEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
