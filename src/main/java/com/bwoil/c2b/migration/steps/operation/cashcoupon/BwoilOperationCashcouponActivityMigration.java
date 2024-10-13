package com.bwoil.c2b.migration.steps.operation.cashcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin.OriginOperationCashcouponActivity;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target.BwoilOperationCashcouponActivity;
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
import java.math.BigDecimal;
import java.util.Date;

@Configuration("bwoilOperationCashcouponActivityMigration")
public class BwoilOperationCashcouponActivityMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCashcouponActivityMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCashcouponActivityMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationCashcouponActivity, BwoilOperationCashcouponActivity>().factory(stepBuilderFactory).name("运营服务-现金券活动表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationCashcouponActivityMigrationReader")
    public ItemReader<OriginOperationCashcouponActivity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select *,(SELECT count(DISTINCT(rule_type_bn)) FROM sdb_b2c_cashcoupon_rule where cpn_id = a.cpn_id) as max_rule_type_num, (SELECT min(rule_periods) FROM sdb_b2c_cashcoupon_rule where cpn_id = a.cpn_id) as min_rule_periods_num  from sdb_b2c_cashcoupon_activity as a ";
        return new StepReaderBuilder<OriginOperationCashcouponActivity>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCashcouponActivity.class).multithreaded(true).sortKeys("cpn_id").build();
    }

    @Bean("bwoilOperationCashcouponActivityMigrationProcessor")
    public ItemProcessor<OriginOperationCashcouponActivity, BwoilOperationCashcouponActivity> processor() {
        return item -> {
            BwoilOperationCashcouponActivity target = new BwoilOperationCashcouponActivity();
            BeanUtils.copyProperties(item, target);

            if (item.getCpnReceiveNum().equals(0)) {
                target.setIsCpnReceive(0);
            } else {
                target.setIsCpnReceive(1);
            }

            if (item.getCpnReceiveMnum().equals(0)) {
                target.setIsCpnReceiveM(0);
            } else {
                target.setIsCpnReceiveM(1);
            }

            if (item.getCpnStatus().equals("0")) {
                target.setCpnStatus(0);
            } else {
                target.setCpnStatus(1);
            }
            if (item.getIsDeleted().equals("0")) {
                target.setStatus(0);
            } else {
                target.setStatus(1);
            }

            if (item.getMaxRuleTypeNum() == null) {
                target.setMaxRuleTypeNum(0);
            } else {
                target.setMaxRuleTypeNum(item.getMaxRuleTypeNum());
            }

            if (item.getMinRulePeriodsNum() == null) {
                target.setMinRulePeriodsNum(0);
            } else {
                target.setMinRulePeriodsNum(item.getMinRulePeriodsNum());
            }

            target.setCpnType(1);
            target.setCreateTime(new Date(item.getCreateTime() * 1000));
            target.setUpdateTime(new Date(item.getLastmodify() * 1000));
            target.setIsUseCash(1);
            target.setIsUseOil(1);
            target.setPayOrderNum(0);
            target.setPayOrderMoney(BigDecimal.ZERO);
            target.setPayDiscountMoney(BigDecimal.ZERO);
            target.setPayOrderNum(item.getCpnUseSumnum());
            return target;
        };
    }

    @Bean("bwoilOperationCashcouponActivityMigrationWriter")
    public ItemWriter<BwoilOperationCashcouponActivity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_cashcoupon_activity (cpn_id, cpn_bn, cpn_name, cpn_money, cpn_receive_num, is_cpn_receive, cpn_receive_mnum, is_cpn_receive_m, cpn_start_date, cpn_end_date, valid_days, cpn_desc, cpn_status, status, cpn_type, create_time, remark, update_time, is_use_cash, is_use_oil, pay_order_num, pay_order_money, pay_discount_money, max_rule_type_num, min_rule_periods_num) VALUES (:cpnId, :cpnBn, :cpnName, :cpnMoney, :cpnReceiveNum, :isCpnReceive, :cpnReceiveMnum, :isCpnReceiveM, :cpnStartDate, :cpnEndDate, :validDays, :cpnDesc, :cpnStatus, :status, :cpnType, :createTime, :remark, :updateTime, :isUseCash, :isUseOil, :payOrderNum, :payOrderMoney, :payDiscountMoney, :maxRuleTypeNum, :minRulePeriodsNum)";
        return new StepWriterBuilder<BwoilOperationCashcouponActivity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
