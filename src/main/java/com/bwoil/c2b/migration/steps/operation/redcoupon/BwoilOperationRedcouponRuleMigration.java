package com.bwoil.c2b.migration.steps.operation.redcoupon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.origin.OriginOperationRedcouponRule;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.target.BwoilOperationRedcouponRule;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.tempClass.RedCouponMoneyReq;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
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
import java.util.Date;
import java.util.List;

@Configuration("bwoilOperationRedcouponRuleMigration")
public class BwoilOperationRedcouponRuleMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationRedcouponRuleMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationRedcouponRuleMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationRedcouponRule, BwoilOperationRedcouponRule>().factory(stepBuilderFactory).name("运营服务-满减规则-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationRedcouponRuleMigrationReader")
    public ItemReader<OriginOperationRedcouponRule> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_redcoupon_rule ";
        return new StepReaderBuilder<OriginOperationRedcouponRule>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationRedcouponRule.class).multithreaded(true).sortKeys("rule_id").build();
    }

    @Bean("bwoilOperationRedcouponRuleMigrationProcessor")
    public ItemProcessor<OriginOperationRedcouponRule, BwoilOperationRedcouponRule> processor() {
        return item -> {
            BwoilOperationRedcouponRule target = new BwoilOperationRedcouponRule();
            BeanUtils.copyProperties(item, target);
            target.setCreateTime(new Date());
            target.setLastmodify(new Date());
            target.setStatus("0");
            JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getRuleDetail()));
            List<RedCouponMoneyReq> goodTypeReqs = new ArrayList<>();
            for (int i = 0; i < resultJson.size(); i++) {
                JSONObject tEmp = resultJson.getJSONObject(String.valueOf(i));
                RedCouponMoneyReq req = new RedCouponMoneyReq();
                req.setRuleMoney(tEmp.getBigDecimal("reduce_money"));
                req.setRuleOrderMoney(tEmp.getBigDecimal("rule_order_money"));
                goodTypeReqs.add(req);
            }
            target.setRuleDetail(JSON.toJSONString(goodTypeReqs));
            return target;
        };
    }

    @Bean("bwoilOperationRedcouponRuleMigrationWriter")
    public ItemWriter<BwoilOperationRedcouponRule> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_redcoupon_rule (rule_id, cpn_id, rule_type_bn, rule_detail, rule_periods, status, create_time, lastmodify) VALUES (:ruleId, :cpnId, :ruleTypeBn, :ruleDetail, :rulePeriods, :status, :createTime, :lastmodify)";
        return new StepWriterBuilder<BwoilOperationRedcouponRule>().dataSource(dataSource).sql(sqlStr).build();
    }
}
