package com.bwoil.c2b.migration.steps.operation.cashcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin.OriginOperationCashcouponRule;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target.BwoilOperationCashcouponRule;
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
import java.util.ArrayList;
import java.util.List;

@Configuration("bwoilOperationCashcouponRuleMigration")
public class BwoilOperationCashcouponRuleMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCashcouponRuleMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCashcouponRuleMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationCashcouponRule, BwoilOperationCashcouponRule>().factory(stepBuilderFactory).name("运营服务-现金券活动规则-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationCashcouponRuleMigrationReader")
    public ItemReader<OriginOperationCashcouponRule> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_cashcoupon_rule";
        return new StepReaderBuilder<OriginOperationCashcouponRule>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCashcouponRule.class).multithreaded(true).sortKeys("rule_id").build();
    }

    @Bean("bwoilOperationCashcouponRuleMigrationProcessor")
    public ItemProcessor<OriginOperationCashcouponRule, BwoilOperationCashcouponRule> processor() {
        return item -> {
            BwoilOperationCashcouponRule target = new BwoilOperationCashcouponRule();
            setLengthAndUnit(item, target);
            BeanUtils.copyProperties(item, target);
            return target;
        };
    }

    @Bean("bwoilOperationCashcouponRuleMigrationWriter")
    public ItemWriter<BwoilOperationCashcouponRule> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_cashcoupon_rule (cpn_id, rule_type_bn, rule_order_money, rule_periods, rule_periods_length, rule_periods_unit) VALUES (:cpnId, :ruleTypeBn, :ruleOrderMoney, :rulePeriods, :rulePeriodsLength, :rulePeriodsUnit)";
//        return new StepWriterBuilder<BwoilOperationCashcouponRule>().dataSource(dataSource).sql(sqlStr).build();
        return new StepWriterBuilder<BwoilOperationCashcouponRule>().dataSource(dataSource).sql(sqlStr).reprocessing(
                targetList -> {
                    List<BwoilOperationCashcouponRule> processedTargetList = new ArrayList<>(targetList);
                    for (BwoilOperationCashcouponRule temp : targetList) {
                        for (int i = temp.getRulePeriods() + 1; i <= 12; i++) {
                            BwoilOperationCashcouponRule anotherTarget = new BwoilOperationCashcouponRule();
                            BeanUtils.copyProperties(temp, anotherTarget);
                            anotherTarget.setRulePeriods(i);
                            anotherTarget.setRuleId(null);
                            processedTargetList.add(anotherTarget);
                        }
                    }
                    return processedTargetList;
                }
        ).build();
    }


    private void setLengthAndUnit(OriginOperationCashcouponRule origin, BwoilOperationCashcouponRule target) {
        if (origin.getRuleTypeBn().equals("amount_refuel_cloud")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_up_v1")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_down_v1")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_refuel")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_amount_v2")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_auto_bd")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_auto_bos")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_auto_refuel")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_auto_refuel_exp")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("litre_refuel")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_amount_term_3")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_month")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_markup")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_term_exper")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_amount")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_amount_copy")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_amount_current")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_auto_refuel_exp")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_refuel_nation")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_exper_v1")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_refuel_nation_v1")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("feast_day")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_refuel_activity")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_amount_exper_v2")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("amount_refuel_webank")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else if (origin.getRuleTypeBn().equals("fixed_litre_exper")) {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        } else {
            target.setRulePeriodsLength(1);
            target.setRulePeriodsUnit(1);
        }


    }
}
