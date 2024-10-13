package com.bwoil.c2b.migration.core.builder;

import com.bwoil.c2b.migration.core.listener.*;
import com.bwoil.c2b.migration.core.util.CacheUtil;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.AbstractTaskletStepBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.core.task.TaskExecutor;

import java.lang.reflect.Field;

public class StepBuilder<O, T> {
    private Step step;

    public StepBuilder() {
        step = new Step();
    }

    public StepBuilder<O, T> name(String name) {
        step.name = name;
        return this;
    }

    public StepBuilder<O, T> reader(ItemReader<O> reader) {
        step.reader = reader;
        return this;
    }

    public StepBuilder<O, T> processor(ItemProcessor<O, T> processor) {
        step.processor = processor;
        return this;
    }

    public StepBuilder<O, T> writer(ItemWriter<T> writer) {
        step.writer = writer;
        return this;
    }

    public org.springframework.batch.core.Step build() {
        CacheUtil.cacheStepSql(step.name, getReaderSql(step.reader), getWriterSqlTemplate(step.name, step.writer));
        AbstractTaskletStepBuilder<SimpleStepBuilder<O, T>> stepBuilder = step.stepBuilderFactory.get(step.name)
                .<O, T>chunk(5000)
                .reader(step.reader)
                .processor(step.processor)
                .writer(step.writer)
                .listener(new WriteListener<>(step.name))
                .listener(new ReadListener<>(step.name))
                .listener(new ProcessListener<>(step.name))
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(Integer.MAX_VALUE)
                .listener(new ItemSkipListener<>(step.name))
                .listener(new StepRunListener(step.name))
                .listener(new StepChunkNotificationListener());
        if (step.taskExecutor != null) {
            stepBuilder = stepBuilder.taskExecutor(step.taskExecutor).throttleLimit(10);
        }
        return stepBuilder.build();
    }

    private String getReaderSql(ItemReader<O> reader) {
        if (reader instanceof JdbcCursorItemReader) {
            return ((JdbcCursorItemReader) reader).getSql();
        }
        if (reader instanceof JdbcPagingItemReader) {
            try {
                Field writerSql = reader.getClass().getDeclaredField("firstPageSql");
                writerSql.setAccessible(true);
                return (String) writerSql.get(reader);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                return null;
            }
        }
        return null;
    }

    private String getWriterSqlTemplate(String stepName, ItemWriter<T> writer) {
        if (stepName.equals("运营服务-现金券活动规则-历史数据迁移")) {
            return "INSERT INTO bwoil_operation_cashcoupon_rule (cpn_id, rule_type_bn, rule_order_money, rule_periods, rule_periods_length, rule_periods_unit) VALUES (:cpnId, :ruleTypeBn, :ruleOrderMoney, :rulePeriods, :rulePeriodsLength, :rulePeriodsUnit)";
        }
        if (stepName.equals("订单服务-资产表-历史数据迁移")) {
            return "INSERT INTO bwoil_order_asset (member_id, asset_type, cur_advance, advance, freeze_advance, last_advance, create_time, last_update_time) VALUES (:memberId, :assetType, :curAdvance, :advance, :freezeAdvance, :lastAdvance, :createTime, :lastUpdateTime)";
        }
        if (stepName.equals("订单服务-卡数据更新处理-历史数据迁移")) {
            return "update bwoil_order_cards set trans_status=:transStatus, recently_sale_date=:recentlySaleDate,can_sale_amount=:canSaleAmount where card_bn=:cardBn";
        }
        if (stepName.equals("订单服务-兑付计划数据更新处理-历史数据迁移")) {
            return "update bwoil_order_redeem_plan set cash_remain=:cashRemain, sale_flag=:saleFlag where id=:id";
        }
        if (stepName.equals("订单服务-我的列表-购买-历史数据迁移")){
            return "INSERT INTO bwoil_order_trans_flow (reference_id,trade_type,member_id,amount,flow_status,flow_desc,remark,create_time,last_update_time) "+
                    "VALUES (:referenceId,:tradeType,:memberId,:amount,:flowStatus,:flowDesc,:remark,:createTime,:lastUpdateTime)";
        }
        if (stepName.equals("订单服务-卡数据更新处理-历史数据迁移2")){
            return "update bwoil_order_cards set pay_amout=:realPayAmount where card_bn=:cardBn";
        }
        try {
            Field writerSql = writer.getClass().getDeclaredField("sql");
            writerSql.setAccessible(true);
            return (String) writerSql.get(writer);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return null;
        }
    }

    public StepBuilder<O, T> factory(StepBuilderFactory stepBuilderFactory) {
        step.stepBuilderFactory = stepBuilderFactory;
        return this;
    }

    public StepBuilder<O, T> taskExecutor(TaskExecutor taskExecutor) {
        step.taskExecutor = taskExecutor;
        return this;
    }

    private class Step {
        private TaskExecutor taskExecutor;
        private StepBuilderFactory stepBuilderFactory;
        private ItemWriter<T> writer;
        private ItemProcessor<O, T> processor;
        private ItemReader<O> reader;
        private String name;
    }
}
