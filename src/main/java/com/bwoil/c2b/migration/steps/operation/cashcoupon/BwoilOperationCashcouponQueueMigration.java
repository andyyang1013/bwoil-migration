package com.bwoil.c2b.migration.steps.operation.cashcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin.OriginOperationCashcouponQueue;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target.BwoilOperationCashcouponQueue;
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

@Configuration("bwoilOperationCashcouponQueueMigration")
public class BwoilOperationCashcouponQueueMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCashcouponQueueMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCashcouponQueueMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationCashcouponQueue, BwoilOperationCashcouponQueue>().factory(stepBuilderFactory).name("运营服务-赠送现金券消息记录表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationCashcouponQueueMigrationReader")
    public ItemReader<OriginOperationCashcouponQueue> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_cashcoupon_queue";
        return new StepReaderBuilder<OriginOperationCashcouponQueue>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCashcouponQueue.class).multithreaded(true).sortKeys("queue_id").build();
    }

    @Bean("bwoilOperationCashcouponQueueMigrationProcessor")
    public ItemProcessor<OriginOperationCashcouponQueue, BwoilOperationCashcouponQueue> processor() {
        return item -> {
            BwoilOperationCashcouponQueue target = new BwoilOperationCashcouponQueue();

            long createTime = (Long.parseLong(item.getQueueCreatetime().toString()) * 1000L);
            target.setCreateTime(new Date(createTime));

            long lastmodify = (Long.parseLong(item.getQueueLasttime().toString()) * 1000L);
            target.setUpdateTime(new Date(lastmodify));


            if (item.getQueueType().equals("1")) {
                target.setQueueType(1);
            } else if (item.getQueueType().equals("2")) {
                target.setQueueType(2);
            }

            if (item.getQueueStatus().equals("0")) {
                target.setQueueStatus(0);
            } else if (item.getQueueStatus().equals("1")) {
                target.setQueueStatus(1);
            } else if (item.getQueueStatus().equals("2")) {
                target.setQueueStatus(2);
            }

            BeanUtils.copyProperties(item, target);
            target.setSendType(1);
            target.setSendMobile("");
            target.setCoupons("");
            return target;
        };
    }

    @Bean("bwoilOperationCashcouponQueueMigrationWriter")
    public ItemWriter<BwoilOperationCashcouponQueue> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_cashcoupon_queue (queue_id, create_time, update_time, queue_cpnname, queue_mobile, queue_type, queue_status, send_type, send_mobile, coupons) VALUES (:queueId, :createTime, :updateTime, :queueCpnname, :queueMobile, :queueType, :queueStatus, :sendType, :sendMobile, :coupons)";
        return new StepWriterBuilder<BwoilOperationCashcouponQueue>().dataSource(dataSource).sql(sqlStr).build();
    }
}
