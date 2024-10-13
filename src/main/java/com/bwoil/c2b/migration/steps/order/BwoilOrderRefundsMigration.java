package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RefundEntity;
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

@Configuration("bwoilOrderRefundsMigration")
public class BwoilOrderRefundsMigration {


    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRefundsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRefundsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RefundEntity, RefundEntity>().factory(stepBuilderFactory).name("订单服务-退款列表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRefundsMigrationReader")
    public ItemReader<RefundEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("sr.refund_id,sr.orderid as order_id,sr.cardbn as card_bn,sr.money,sr.member_id,")
                .append("pam.login_account as mobile,bm.shop_member_bn as member_bn,sr.status as refund_status,")
                .append("sr.auditer,sr.consenter,sr.reasion,sr.audit_memo,sr.confirm_memo,")
                .append("DATE_FORMAT(FROM_UNIXTIME(sr.applytime),'%Y%m%d')  as apply_time, ")
                .append("DATE_FORMAT(FROM_UNIXTIME(sr.audittime),'%Y-%m-%d %H:%i:%s')  as audit_time, ")
                .append("DATE_FORMAT(FROM_UNIXTIME(sr.refundtime),'%Y-%m-%d %H:%i:%s')  as refund_time, ")
                .append("DATE_FORMAT(FROM_UNIXTIME(sr.applytime),'%Y-%m-%d %H:%i:%s')  as create_time, ")
                .append("DATE_FORMAT(FROM_UNIXTIME(sr.refundtime),'%Y-%m-%d %H:%i:%s')  as last_update_time ")
                .append(" from sdb_ectools_instalment_refunds sr ")
                .append(" left join sdb_b2c_members bm on bm.member_id=sr.member_id")
                .append(" left join sdb_pam_members pam on pam.member_id=sr.member_id and pam.login_type='mobile' ");

        return new StepReaderBuilder<RefundEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RefundEntity.class).multithreaded(true).sortKeys("sr.refund_id").build();
    }

    @Bean("bwoilOrderRefundsMigrationProcessor")
    public ItemProcessor<RefundEntity, RefundEntity> processor() {
        return item -> {
            RefundEntity target = new RefundEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderRefundsMigrationWriter")
    public ItemWriter<RefundEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_refunds (refund_id,order_id,card_bn,money,member_id,mobile,member_bn,refund_status,apply_time,audit_time,refund_time,auditer,consenter,reasion,audit_memo,confirm_memo,create_time,last_update_time)"
                + " VALUES (:refundId, :orderId, :cardBn, :money, :memberId, :mobile, :memberBn, :refundStatus, :applyTime, :auditTime, :refundTime, :auditer, :consenter, :reasion, :auditMemo, :confirmMemo, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RefundEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RefundEntity target) {
        if ("1970-01-01 08:00:00".equals(target.getAuditTime())) {
            target.setAuditTime("0000-00-00 00:00:00");
        }
        if ("1970-01-01 08:00:00".equals(target.getRefundTime())) {
            target.setRefundTime("0000-00-00 00:00:00");
        }

        if ("1970-01-01 08:00:00".equals(target.getCreateTime())) {
            target.setCreateTime("0000-00-00 00:00:00");
        }
        target.setLastUpdateTime(target.getCreateTime());
    }
}
