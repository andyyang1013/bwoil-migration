package com.bwoil.c2b.migration.steps.flow;

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
import com.bwoil.c2b.migration.steps.flow.entity.TransFlowEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

@Configuration("bwoilCashFlowMigration")
public class BwoilCashFlowMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilCashFlowMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilCashFlowMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<TransFlowEntity, TransFlowEntity>().factory(stepBuilderFactory).name("订单服务-我的列表-兑付-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilCashFlowMigrationReader")
    public ItemReader<TransFlowEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sql = new StringBuffer("select ");
        sql.append("trade_bn as reference_id,member_id,income as amount,audit_status as flow_status,")
                .append("'兑付' as flow_desc,product_name as remark,trade_start_time as start_time,trade_end_time as end_time ")
                .append(" from sdb_ycy_card_trade where trade_type='cash'");

        return new StepReaderBuilder<TransFlowEntity>().dataSource(dataSource).sql(sql.toString()).mappedClass(TransFlowEntity.class).build();
    }

    @Bean("bwoilCashFlowMigrationProcessor")
    public ItemProcessor<TransFlowEntity, TransFlowEntity> processor() {
        return item -> {
        	TransFlowEntity target = new TransFlowEntity();
            BeanUtils.copyProperties(item,target);
            target.setTradeType("cash");
            target.setFlowStatus(convertStatsus(item.getFlowStatus()));
            target.setCreateTime(TimeStampUtil.convertDate(target.getStartTime()));
            target.setLastUpdateTime(TimeStampUtil.convertDate(target.getEndTime()));
            return target;
        };
    }


    @Bean("bwoilCashFlowMigrationWriter")
    public ItemWriter<TransFlowEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_trans_flow (reference_id,trade_type,member_id,amount,flow_status,flow_desc,remark,create_time,last_update_time) "+
      "VALUES (:referenceId,:tradeType,:memberId,:amount,:flowStatus,:flowDesc,:remark,:createTime,:lastUpdateTime)";
        return new StepWriterBuilder<TransFlowEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
    
    private String convertStatsus(String flowStatus) {
    	if("0".equals(flowStatus))
    	{
    		return "3";
    	}
    	if("1".equals(flowStatus))
    	{
    		return "0";
    	}
    	if("2".equals(flowStatus))
    	{
    		return "1";
    	}

    	return flowStatus;
    }
}
