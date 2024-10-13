package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.OrderLogEntity;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
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

@Configuration("bwoilOrderLogMigration")
public class BwoilOrderLogMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderLogMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderLogMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OrderLogEntity, OrderLogEntity>().factory(stepBuilderFactory).name("订单服务-订单日志表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderLogMigrationReader")
    public ItemReader<OrderLogEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("select ")
                .append("log_id,rel_id,op_id,op_name,bill_type,behavior,result,log_text,addon,")
                .append("FROM_UNIXTIME(alttime)  as create_time")
                .append(" from sdb_b2c_order_log");
        return new StepReaderBuilder<OrderLogEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(OrderLogEntity.class).multithreaded(true).sortKeys("log_id").build();
    }

    @Bean("bwoilOrderLogMigrationProcessor")
    public ItemProcessor<OrderLogEntity, OrderLogEntity> processor() {
        return item -> {
            OrderLogEntity target = new OrderLogEntity();
            BeanUtils.copyProperties(item, target);
            target.setResult("SUCCESS".equals(target.getResult()) ? "Y" : "N");
            if (StringUtils.isNotEmpty(target.getLogText())) {
                target.setLogText(PhpSeriailizeUtil.getPhpString(target.getLogText()));
            }
            if (StringUtils.isNotEmpty(target.getAddon())) {
                target.setAddon(PhpSeriailizeUtil.getPhpString(target.getAddon()));
            }
            target.setLastUpdateTime(target.getCreateTime());
            return target;
        };
    }

    @Bean("bwoilOrderLogMigrationWriter")
    public ItemWriter<OrderLogEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_log (id,rel_id,op_id,op_name,bill_type,behavior,result,log_text,addon,create_time,last_update_time)"
                + " VALUES ( :logId, :relId, :opId, :opName, :billType, :behavior, :result, :logText, :addon, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<OrderLogEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
