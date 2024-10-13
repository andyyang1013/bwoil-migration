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

@Configuration("bwoilScanRefuelFlowMigration")
public class BwoilScanRefuelFlowMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilScanRefuelFlowMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilScanRefuelFlowMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<TransFlowEntity, TransFlowEntity>().factory(stepBuilderFactory).name("订单服务-我的列表-扫码加油-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilScanRefuelFlowMigrationReader")
    public ItemReader<TransFlowEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sql = new StringBuffer("select ");
        sql.append("order_id as reference_id,member_id,pay_money as amount,'扫码加油' as flow_desc,'扫码加油' as remark,created as end_time,created as  start_time")
                .append(" from sdb_station_fuel_order ");

        return new StepReaderBuilder<TransFlowEntity>().dataSource(dataSource).sql(sql.toString()).mappedClass(TransFlowEntity.class).build();
    }

    @Bean("bwoilScanRefuelFlowMigrationProcessor")
    public ItemProcessor<TransFlowEntity, TransFlowEntity> processor() {
        return item -> {
        	TransFlowEntity target = new TransFlowEntity();
            BeanUtils.copyProperties(item,target);
            target.setTradeType("scan_refuel");
            target.setFlowStatus("0");
            target.setRemark(target.getFlowDesc());
            target.setCreateTime(TimeStampUtil.convertDate(target.getStartTime()));
            target.setLastUpdateTime(TimeStampUtil.convertDate(target.getEndTime()));
            return target;
        };
    }


    @Bean("bwoilScanRefuelFlowMigrationWriter")
    public ItemWriter<TransFlowEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_trans_flow (reference_id,trade_type,member_id,amount,flow_status,flow_desc,remark,create_time,last_update_time) "+
      "VALUES (:referenceId,:tradeType,:memberId,:amount,:flowStatus,:flowDesc,:remark,:createTime,:lastUpdateTime)";
        return new StepWriterBuilder<TransFlowEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
