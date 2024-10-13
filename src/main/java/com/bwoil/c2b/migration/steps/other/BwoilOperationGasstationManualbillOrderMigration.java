package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.origin.OriginOperationGasstationManualbillOrder;
import com.bwoil.c2b.migration.steps.other.pojo.target.BwoilOperationGasstationManualbillOrder;
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

@Configuration("bwoilOperationGasstationManualbillOrderMigration")
public class BwoilOperationGasstationManualbillOrderMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationManualbillOrderMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationManualbillOrderStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationManualbillOrder, BwoilOperationGasstationManualbillOrder>().factory(stepBuilderFactory).name("运营服务-加油站打款成功后记录-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationManualbillOrderReader")
    public ItemReader<OriginOperationGasstationManualbillOrder> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select * from sdb_ycy_refuel_manualbill_order";
        return new StepReaderBuilder<OriginOperationGasstationManualbillOrder>().dataSource(dataSource).sql(sql).mappedClass(OriginOperationGasstationManualbillOrder.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean(name = "bwoilOperationGasstationManualbillOrderProcessor")
    public ItemProcessor<OriginOperationGasstationManualbillOrder, BwoilOperationGasstationManualbillOrder> processor() {
        return item -> {
            BwoilOperationGasstationManualbillOrder target = new BwoilOperationGasstationManualbillOrder();
            target.setId(item.getId());
            target.setOrderId(item.getOrderId());
            target.setAccountNo(item.getAccountNo());
            target.setPayBn(item.getPayBn());
            target.setPayType(item.getPayType());
            target.setTotalAmount(item.getTotalAmount());
            target.setRefundAmount(item.getRefundAmount());
            target.setStatus(item.getStatus());
            target.setMessage(item.getMessage());
            target.setCompleteTime(TimeStampUtil.getTimestampWithDefault(item.getCompleteTime()));
            target.setCreatedTime(TimeStampUtil.getTimestampWithDefault(item.getCreatedTime()));
            return target;
        };
    }

    @Bean(name = "bwoilOperationGasstationManualbillOrderWriter")
    public ItemWriter<BwoilOperationGasstationManualbillOrder> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_manualbill_order (id, order_id, account_no, pay_bn, pay_type, total_amount, refundAmount, status, message, complete_time, created_time) VALUES (:id, :orderId, :accountNo, :payBn, :payType, :totalAmount, :refundAmount, :status, :message, :completeTime, :createdTime)";
        return new StepWriterBuilder<BwoilOperationGasstationManualbillOrder>().dataSource(dataSource).sql(sqlStr).build();
    }
}
