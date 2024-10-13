package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.origin.OriginOperationGasstationRemittanceLog;
import com.bwoil.c2b.migration.steps.other.pojo.target.BwoilOperationGasstationRemittanceLog;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration("bwoilOperationGasstationRemittanceLogMigration")
public class BwoilOperationGasstationRemittanceLogMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationRemittanceLogMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationRemittanceLogStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationRemittanceLog, BwoilOperationGasstationRemittanceLog>().factory(stepBuilderFactory).name("运营服务-加油站打款回调日志表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationRemittanceLogReader")
    public ItemReader<OriginOperationGasstationRemittanceLog> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select * from sdb_b2c_clear_remittance_log";
        return new StepReaderBuilder<OriginOperationGasstationRemittanceLog>().dataSource(dataSource).sql(sql).mappedClass(OriginOperationGasstationRemittanceLog.class).multithreaded(true).sortKeys("log_id").build();
    }

    @Bean(name = "bwoilOperationGasstationRemittanceLogProcessor")
    public ItemProcessor<OriginOperationGasstationRemittanceLog, BwoilOperationGasstationRemittanceLog> processor() {
        return item -> {
            BwoilOperationGasstationRemittanceLog target = new BwoilOperationGasstationRemittanceLog();
            target.setLogId(item.getLogId());
            target.setOrderId(item.getOrderId());
            target.setStationId(item.getStationId());
            target.setMemberBn(item.getMemberBn());
            target.setStatus(item.getStatus());
            target.setMessage(item.getMessage());
            target.setReqId(item.getReqId());
            target.setCompleteTime(TimeStampUtil.getTimestampWithDefault(item.getCompleteTime()));
            target.setCreatedTime(TimeStampUtil.getTimestampWithDefault(item.getCreatedTime()));
            return target;
        };
    }

    @Bean(name = "bwoilOperationGasstationRemittanceLogWriter")
    public ItemWriter<BwoilOperationGasstationRemittanceLog> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_remittance_log (log_id, order_id, station_id, member_bn, status, message, req_id, complete_time, created_time) VALUES (:logId, :orderId, :stationId, :memberBn, :status, :message, :reqId, :completeTime, :createdTime)";
        return new StepWriterBuilder<BwoilOperationGasstationRemittanceLog>().dataSource(dataSource).sql(sqlStr).build();
    }
}
