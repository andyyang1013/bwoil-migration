package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationRefuelCouponRule;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationRefuelCouponRule;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.RuleEntity;
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
import java.util.*;

@Configuration("bwoilOperationGasstationRefuelCouponRuleMigration")
public class BwoilOperationGasstationRefuelCouponRuleMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationRefuelCouponRuleMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationRefuelCouponRuleMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationRefuelCouponRule, BwoilOperationGasstationRefuelCouponRule>().factory(stepBuilderFactory).name("运营服务-加油站的赠券规则-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationRefuelCouponRuleMigrationReader")
    public ItemReader<OriginOperationGasstationRefuelCouponRule> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from  sdb_ycy_refuel_couponrule";
        return new StepReaderBuilder<OriginOperationGasstationRefuelCouponRule>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationRefuelCouponRule.class).multithreaded(true).sortKeys("rule_id").build();
    }

    @Bean("bwoilOperationGasstationRefuelCouponRuleMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationRefuelCouponRule, BwoilOperationGasstationRefuelCouponRule> processor() {
        return item -> {
            BwoilOperationGasstationRefuelCouponRule target = new BwoilOperationGasstationRefuelCouponRule();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getRuleId());
            if (item.getStartTime() == null || item.getStartTime() == 0) {
                target.setStartTime(null);
            } else {
                target.setStartTime(new Date(Long.parseLong(item.getStartTime().toString()) * 1000L));
            }
            if (item.getEndTime() == null || item.getEndTime() == 0) {
                target.setEndTime(null);
            } else {
                target.setEndTime(new Date(Long.parseLong(item.getEndTime().toString()) * 1000L));
            }
            if (item.getModifyTime() == null || item.getModifyTime() == 0) {
                target.setModifyTime(null);
            } else {
                target.setModifyTime(new Date(Long.parseLong(item.getModifyTime().toString()) * 1000L));
            }
            if (item.getCreateTime() == null || item.getCreateTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreateTime().toString()) * 1000L));
            }
            //JSON转
            List<RuleEntity> list = new ArrayList<>();
            Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(item.getCouponList());
            Set<Map.Entry<String, Object>> entries = parse.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                RuleEntity entity = JSONObject.parseObject(entry.getValue().toString(), RuleEntity.class);
                entity.setCouponId(Integer.valueOf(entry.getKey()));
                list.add(entity);
            }
            target.setCouponList(JSONObject.toJSONString(list));
            return target;
        };
    }

    @Bean("bwoilOperationGasstationRefuelCouponRuleMigrationWriter")
    public ItemWriter<BwoilOperationGasstationRefuelCouponRule> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_refuel_coupon_rule (id, station_id, station_name, coupon_list, start_time, end_time, modify_time, modify_username, create_time, status) VALUES(:id, :stationId, :stationName, :couponList, :startTime, :endTime, :modifyTime, :modifyUsername, :createTime, :status)";
        return new StepWriterBuilder<BwoilOperationGasstationRefuelCouponRule>().dataSource(dataSource).sql(sqlStr).build();
    }
}
