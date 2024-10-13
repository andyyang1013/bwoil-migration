package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RedeemTicketEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

import org.apache.commons.lang3.StringUtils;
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

@Configuration("bwoilOrderRedeemTicketMigration")
public class BwoilOrderRedeemTicketMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRedeemTicketMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRedeemTicketMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RedeemTicketEntity, RedeemTicketEntity>().factory(stepBuilderFactory).name("订单服务-小票兑付表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRedeemTicketMigrationReader")
    public ItemReader<RedeemTicketEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("select ")
                .append("sr.fuel_id,sr.fuel_type as auth_type,sr.refuel_orderid as redeem_bn,sr.member_id,bm.shop_member_bn as member_bn,sr.total_cash_amount as amount,")
                .append("sr.invoice_code as tickt_code,sr.invoice_number as tickt_no,")
                .append("sr.invoice_company as tickt_company,sr.invoice_date as tickt_date,sr.invoice_iden as tax_iden,sr.invoice_attachment as attachment_image,")
                .append("sr.audit_status as audit_status,0 as operator_id,null as audit_ip,sr.remark as audit_remark,")
                .append("fuel_start_time as create_time, ")
                .append("fuel_end_time  as last_update_time")
                .append(" from sdb_ycy_card_fuel sr")
                .append(" left join sdb_b2c_members bm on bm.member_id=sr.member_id");

        return new StepReaderBuilder<RedeemTicketEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RedeemTicketEntity.class).multithreaded(true).sortKeys("fuel_id").build();
    }

    @Bean("bwoilOrderRedeemTicketMigrationProcessor")
    public ItemProcessor<RedeemTicketEntity, RedeemTicketEntity> processor() {
        return item -> {
            RedeemTicketEntity target = new RedeemTicketEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderRedeemTicketMigrationWriter")
    public ItemWriter<RedeemTicketEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_redeem_ticket (id,auth_type,redeem_bn,member_id,member_bn,amount,tickt_code,tickt_no,tickt_company,tickt_date,tax_iden,attachment_image,audit_status,operator_id,audit_time,audit_ip,audit_remark,create_time,last_update_time) "
                + "VALUES (:fuelId, :authType, :redeemBn, :memberId, :memberBn, :amount, :ticktCode, :ticktNo, :ticktCompany, :ticktDate, :taxIden, :attachmentImage, :auditStatus, :operatorId, :auditTime, :auditIp, :auditRemark, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RedeemTicketEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RedeemTicketEntity target) {

    	target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
    	target.setLastUpdateTime(TimeStampUtil.convertDate(target.getLastUpdateTime()));

        if (StringUtils.isNotEmpty(target.getTicktDate())) {
            target.setTicktDate(target.getTicktDate().replaceAll("-", ""));
        }
        if(!"0".equals(target.getAuditStatus())) {
        	target.setAuditTime(target.getLastUpdateTime());        	
        }
        if (target.getMemberBn() == null) {
            target.setMemberBn("0");
        }
        if (target.getTicktCode() == null) {
            target.setTicktCode("0");
        }
        if (target.getTicktNo() == null) {
            target.setTicktNo("0");
        }
        if (target.getTicktCompany() == null) {
            target.setTicktCompany("0");
        }
        if (target.getTaxIden() == null) {
            target.setTaxIden("0");
        }
    }
}
