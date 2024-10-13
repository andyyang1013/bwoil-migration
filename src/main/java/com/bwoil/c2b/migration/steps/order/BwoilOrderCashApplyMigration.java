package com.bwoil.c2b.migration.steps.order;

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
import com.bwoil.c2b.migration.steps.order.entity.CashApplyEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

@Configuration("bwoilOrderCashApplyMigration")
public class BwoilOrderCashApplyMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCashApplyMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCashApplyMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CashApplyEntity, CashApplyEntity>().factory(stepBuilderFactory).name("订单服务-提现申请表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCashApplyMigrationReader")
    public ItemReader<CashApplyEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("select sr.cash_out_id,sr.cash_bn,sr.member_id, c.true_name as member_name,")
                .append("pm.login_account as mobile,bm.shop_member_bn as member_bn,")
                .append("null as bank_no,")
                .append("sr.available_balance,sr.cash_amount, sr.cash_out_recharge_charge cash_fee,sr.cash_account_id,")
                .append("sr.cash_status,sr.cash_received,sr.plat,sr.devinfo,sr.audit_operator_id,sr.audit_operator_name,")
                .append("sr.audit_ip,sr.remittance_status,sr.remark,sr.auto_recon_status,sr.auto_remark,sr.auto_type,")
                .append("sr.cash_pay_type,sr.cash_pay_channel,sr.cash_pay_proposer,sr.cash_pay_audit_status,")
                .append("sr.cash_pay_auditor,sr.cash_pay_remark,sr.account_status,sr.account_time,")
                .append("sr.recharge_available_balance,sr.red_envelope_available_balance,")
                .append("sr.audit_time atime,")
                .append("sr.cash_pay_audit_time au_time,")
                .append("sr.cash_finish_time finish_time,")
                .append("sr.cash_start_time start_time,")
                .append("sr.lastmodify update_time")
                .append(" from sdb_b2c_cash_out sr ")
                .append(" left join sdb_pam_members pm on pm.member_id=sr.member_id and pm.login_type='mobile'")
                .append(" left join sdb_b2c_members bm on bm.member_id=sr.member_id")
                .append(" left join (select member_id,true_name,iden_number_encrypt from sdb_b2c_member_identity_info where audit_status='1' GROUP BY member_id ORDER BY iden_id DESC) c on sr.member_id = c.member_id ");
        ;

        return new StepReaderBuilder<CashApplyEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(CashApplyEntity.class).multithreaded(true).sortKeys("sr.cash_out_id").build();
    }

    @Bean("bwoilOrderCashApplyMigrationProcessor")
    public ItemProcessor<CashApplyEntity, CashApplyEntity> processor() {
        return item -> {
            CashApplyEntity target = new CashApplyEntity();
            BeanUtils.copyProperties(item, target);
            target.setCashReceived("FALSE".equalsIgnoreCase(target.getCashReceived()) ? "N" : "Y");
            target.setRemittanceStatus("0".equalsIgnoreCase(target.getRemittanceStatus()) ? "N" : "Y");

            if (target.getMobile() == null || target.getMobile().length() > 15) {
                target.setMobile("0");
            }
            target.setAtime(TimeStampUtil.convertDate(target.getAtime()));
            target.setAuTime(TimeStampUtil.convertDate(target.getAuTime()));
            target.setFinishTime(TimeStampUtil.convertDate(target.getFinishTime()));
            target.setStartTime(TimeStampUtil.convertDate(target.getStartTime()));
            target.setUpdateTime(TimeStampUtil.convertDate(target.getUpdateTime()));
            target.setAccountTime(TimeStampUtil.convertDate(target.getAccountTime()));
            return target;
        };
    }

    @Bean("bwoilOrderCashApplyMigrationWriter")
    public ItemWriter<CashApplyEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_cash_apply (member_name,cash_out_id,cash_bn,member_id,mobile,member_bn,bank_no,available_balance,cash_amount,cash_fee,cash_account_id,cash_status,cash_received,plat,devinfo,audit_operator_id,audit_operator_name,audit_time,audit_ip,cash_finish_time,remittance_status,remark,auto_recon_status,auto_remark,auto_type,cash_pay_type,cash_pay_channel,cash_pay_proposer,cash_pay_audit_time,cash_pay_audit_status,cash_pay_auditor,cash_pay_remark,account_status,account_time,recharge_available_balance,red_envelope_available_balance,create_time,last_update_time) "
                + "VALUES (:memberName,:cashOutId, :cashBn, :memberId, :mobile, :memberBn, :bankNo, :availableBalance, :cashAmount, :cashFee, :cashAccountId, :cashStatus, :cashReceived, :plat, :devinfo, :auditOperatorId, :auditOperatorName, :atime, :auditIp, :finishTime, :remittanceStatus, :remark, :autoReconStatus, :autoRemark, :autoType, :cashPayType, :cashPayChannel, :cashPayProposer, :auTime, :cashPayAuditStatus, :cashPayAuditor, :cashPayRemark, :accountStatus, :accountTime, :rechargeAvailableBalance, :redEnvelopeAvailableBalance, :startTime, :updateTime)";
        return new StepWriterBuilder<CashApplyEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
