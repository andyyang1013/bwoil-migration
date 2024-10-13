package com.bwoil.c2b.migration.steps.order;

import javax.sql.DataSource;

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

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RechargeEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

@Configuration("bwoilOrderRechargeMigration")
public class BwoilOrderRechargeMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRechargeMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRechargeMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RechargeEntity, RechargeEntity>().factory(stepBuilderFactory).name("订单服务-充值表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRechargeMigrationReader")
    public ItemReader<RechargeEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("sr.log_id,sr.member_id,pam.login_account as  mobile,sm.shop_member_bn as member_bn,sm.name member_name,sr.trade_bn,")
        .append("sr.payment_id as pay_bn,sr.mtime  as to_acct_time,sr.paymethod as pay_way,")
        .append("null as pay_channel,sr.payment_id as pay_no,sr.order_id as order_refer_no,sr.import_money as recharge_amount,sr.member_advance as account_remain,")
        .append("'1' as recharge_status,'0' as status,sr.mtime as create_time,sr.mtime as last_update_time ")
        .append("from sdb_b2c_member_advance sr ")
        .append(" left join sdb_b2c_members sm on sm.member_id=sr.member_id")
        .append(" left join sdb_pam_members pam on pam.member_id=sr.member_id and pam.login_type='mobile' ")
        .append(" where sr.member_id!=0 and sr.trade_type='recharge'");
        return new StepReaderBuilder<RechargeEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RechargeEntity.class).multithreaded(true).sortKeys("sr.log_id").build();
    }

    @Bean("bwoilOrderRechargeMigrationProcessor")
    public ItemProcessor<RechargeEntity, RechargeEntity> processor() {
        return item -> {
            RechargeEntity target = new RechargeEntity();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getLogId());
            convert(target);
            target.setToAcctTime(TimeStampUtil.convertDate(target.getToAcctTime()));
            target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
            target.setLastUpdateTime(TimeStampUtil.convertDate(target.getLastUpdateTime()));
            return target;
        };
    }

    @Bean("bwoilOrderRechargeMigrationWriter")
    public ItemWriter<RechargeEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_recharge (id,member_id,mobile,member_bn,member_name,trade_bn,pay_bn,to_acct_time,pay_way,pay_channel,pay_no,order_refer_no,recharge_amount,account_remain,recharge_status,status,create_time,last_update_time)"
                + " VALUES (:id, :memberId, :mobile, :memberBn, :memberName, :tradeBn, :payBn, :toAcctTime, :payWay, :payChannel, :payNo, :orderReferNo, :rechargeAmount, :accountRemain, :rechargeStatus, :status, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RechargeEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RechargeEntity target) {

        if (StringUtils.isEmpty(target.getMemberName())) {
            target.setMemberName("0");
        }
        if (StringUtils.isEmpty(target.getPayChannel())) {
            target.setPayChannel("0");
        }
    }
}
