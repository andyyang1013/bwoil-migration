package com.bwoil.c2b.migration.steps.other;

import javax.sql.DataSource;

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

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.CashTaskEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

@Configuration("bwoilOrderCashTaskMigration")
public class BwoilOrderCashTaskMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCashTaskMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCashTaskMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CashTaskEntity, CashTaskEntity>().factory(stepBuilderFactory).name("订单服务-打款任务表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCashTaskMigrationReader")
    public ItemReader<CashTaskEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("select ")
                .append("task_id, task_bn, member_id, member_account, member_bn, cash_bn, cash_amount, audit_time, task_time, remittance_time, status, clear_bn, ")
                .append("channel, is_retry, reRemittance, remark, create_time, lastmodify as last_update_time ")
                .append(" from sdb_b2c_remittance_task");

        return new StepReaderBuilder<CashTaskEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(CashTaskEntity.class).multithreaded(true).sortKeys("task_id").build();
    }

    @Bean("bwoilOrderCashTaskMigrationProcessor")
    public ItemProcessor<CashTaskEntity, CashTaskEntity> processor() {
        return item -> {
            CashTaskEntity target = new CashTaskEntity();
            BeanUtils.copyProperties(item, target);
            target.setIsRetry("1".equals(target.getIsRetry()) ? "N" : "Y");

            target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
            target.setLastUpdateTime(TimeStampUtil.convertDate(target.getLastUpdateTime()));
            target.setAuditTime(TimeStampUtil.convertDate(target.getAuditTime()));
            target.setTaskTime(TimeStampUtil.convertDate(target.getTaskTime()));
            target.setRemittanceTime(TimeStampUtil.convertDate(target.getRemittanceTime()));
            return target;
        };
    }

    @Bean("bwoilOrderCashTaskMigrationWriter")
    public ItemWriter<CashTaskEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_cash_task (task_id, task_bn, member_id, member_account, member_bn, cash_bn, cash_amount, audit_time, task_time, remittance_time, status, clear_bn, channel, is_retry, re_remittance, remark, create_time, last_update_time) "
                + "VALUES (:taskId, :taskBn, :memberId, :memberAccount, :memberBn, :cashBn, :cashAmount, :auditTime, :taskTime, :remittanceTime, :status, :clearBn, :channel, :isRetry, :reRemittance, :remark, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<CashTaskEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}