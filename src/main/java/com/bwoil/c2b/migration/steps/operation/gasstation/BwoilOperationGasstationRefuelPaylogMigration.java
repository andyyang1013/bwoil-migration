package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationRefuelPaylog;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationRefuelPaylog;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.PaylogValue;
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
import java.util.Date;

@Configuration("bwoilOperationGasstationRefuelPaylogMigration")
public class BwoilOperationGasstationRefuelPaylogMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationRefuelPaylogMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationRefuelPaylogMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationRefuelPaylog, BwoilOperationGasstationRefuelPaylog>().factory(stepBuilderFactory).name("运营服务-加油站加油付款记录RefuelPaylog表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationRefuelPaylogMigrationReader")
    public ItemReader<OriginOperationGasstationRefuelPaylog> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_refuel_order_log where log_id not in(642813,643521,643677,644058,644082,644394,645960,648522,654561,655392,662094,687066,692358,701832,894882)";
        return new StepReaderBuilder<OriginOperationGasstationRefuelPaylog>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationRefuelPaylog.class).multithreaded(true).sortKeys("log_id").build();
    }

    @Bean("bwoilOperationGasstationRefuelPaylogMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationRefuelPaylog, BwoilOperationGasstationRefuelPaylog> processor() {
        return item -> {
            BwoilOperationGasstationRefuelPaylog target = new BwoilOperationGasstationRefuelPaylog();
            BeanUtils.copyProperties(item, target);
            if (item.getOperatorTime() == null || item.getOperatorTime() == 0) {
                target.setOperatorTime(null);
            } else {
                target.setOperatorTime(new Date(Long.parseLong(item.getOperatorTime().toString()) * 1000L));
            }
            //json转换
            String phpString = PhpSeriailizeUtil.getPhpString(item.getLogData());
            PaylogValue paylog = null;
            if (phpString.contains("orderAmount")) {
                paylog = JSONObject.parseObject(phpString, PaylogValue.class);
                target.setLogData(JSONObject.toJSONString(paylog));
            } else {
                JSONObject resultJson2 = JSON.parseObject(PhpSeriailizeUtil.getPhpString(phpString.replace("\\", "")));
                paylog = JSONObject.parseObject(resultJson2.toString(), PaylogValue.class);
                target.setLogData(JSONObject.toJSONString(paylog));
                String s = "";
                if (paylog.getStationBn() == null && paylog.getOrderNo() == null && paylog.getMemberNo() == null) {
                    if (resultJson2.size() != 0) {
                        if ((String) resultJson2.get("status") != null || !((String) resultJson2.get("status")).equals("")) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("status", (String) resultJson2.get("status"));
                            s = jsonObject.toJSONString();
                        }
                        target.setLogData(s);
                    } else {
                        target.setLogData("{}");
                    }
                }
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationRefuelPaylogMigrationWriter")
    public ItemWriter<BwoilOperationGasstationRefuelPaylog> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_refuel_paylog " +
                "(log_id, order_id, log_memo, log_behavior, log_data, log_notice, operator_time) VALUES " +
                "(:logId, :orderId, :logMemo, :logBehavior, :logData, :logNotice, :operatorTime)";
        return new StepWriterBuilder<BwoilOperationGasstationRefuelPaylog>().dataSource(dataSource).sql(sqlStr).build();
    }
}
