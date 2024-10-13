package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationOilPriceRule;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationOilPriceRule;
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
import java.util.Set;

@Configuration("bwoilOperationGasstationOilPriceRuleMigration")
public class BwoilOperationGasstationOilPriceRuleMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationOilPriceRuleMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationOilPriceRuleMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationOilPriceRule, BwoilOperationGasstationOilPriceRule>().factory(stepBuilderFactory).name("运营服务-加油站品牌公司价格规则OilPriceRule信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationOilPriceRuleMigrationReader")
    public ItemReader<OriginOperationGasstationOilPriceRule> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_gasstation_goil_price";
        return new StepReaderBuilder<OriginOperationGasstationOilPriceRule>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationOilPriceRule.class).multithreaded(true).sortKeys("goil_price_id").build();
    }

    @Bean("bwoilOperationGasstationOilPriceRuleMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationOilPriceRule, BwoilOperationGasstationOilPriceRule> processor() {
        return item -> {
            BwoilOperationGasstationOilPriceRule target = new BwoilOperationGasstationOilPriceRule();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getGoilPriceId());
            if (item.getOperatorTime() == null || item.getOperatorTime() == 0) {
                target.setOperatorTime(null);
            } else {
                target.setOperatorTime(new Date(Long.parseLong(item.getOperatorTime().toString()) * 1000L));
            }
            if (item.getAuditTime() == null || item.getAuditTime() == 0) {
                target.setAuditTime(null);
            } else {
                target.setAuditTime(new Date(Long.parseLong(item.getAuditTime().toString()) * 1000L));
            }
            if (item.getLastmodify() == null || item.getLastmodify() == 0) {
                target.setLastmodify(null);
            } else {
                target.setLastmodify(new Date(Long.parseLong(item.getLastmodify().toString()) * 1000L));
            }
            //转换json
            JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getGoilSalePrices()));
            JSONObject json = new JSONObject();
            if (resultJson.size() != 0) {
                Set<String> it = resultJson.keySet();
                for (String key : it) {
                    JSONObject q1 = resultJson.getJSONObject(key);
                    json.put(q1.getString("oil_name"), q1.getString("sale_price"));
                }
            }
            target.setGoilSalePrices(json.toJSONString());
            return target;
        };
    }

    @Bean("bwoilOperationGasstationOilPriceRuleMigrationWriter")
    public ItemWriter<BwoilOperationGasstationOilPriceRule> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_oil_price_rule (id, operator_time, operator_id, operator_name, audit_id, audit_name, audit_time, goil_date, goil_sale_prices, status, lastmodify, company_id) VALUES (:id, :operatorTime, :operatorId, :operatorName, :auditId, :auditName, :auditTime, :goilDate, :goilSalePrices, :status, :lastmodify, :companyId)";
        return new StepWriterBuilder<BwoilOperationGasstationOilPriceRule>().dataSource(dataSource).sql(sqlStr).build();
    }
}
