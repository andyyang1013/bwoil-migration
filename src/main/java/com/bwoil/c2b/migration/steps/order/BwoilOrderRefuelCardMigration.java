package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RefuelCardEntity;
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
import java.math.BigDecimal;

@Configuration("bwoilOrderRefuelCardMigration")
public class BwoilOrderRefuelCardMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRefuelCardMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRefuelCardMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RefuelCardEntity, RefuelCardEntity>().factory(stepBuilderFactory).name("订单服务-加油卡表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRefuelCardMigrationReader")
    public ItemReader<RefuelCardEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("member_id, card_no,")
                .append("DATE_FORMAT(FROM_UNIXTIME(bind_card_time),'%Y-%m-%d %H:%i:%s') as bind_time")
                .append(" from sdb_b2c_members where length(card_no)>5");

        return new StepReaderBuilder<RefuelCardEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RefuelCardEntity.class).multithreaded(true).sortKeys("member_id").build();
    }

    @Bean("bwoilOrderRefuelCardMigrationProcessor")
    public ItemProcessor<RefuelCardEntity, RefuelCardEntity> processor() {
        return item -> {
            convert(item);
            return item;
        };
    }

    @Bean("bwoilOrderRefuelCardMigrationWriter")
    public ItemWriter<RefuelCardEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_refuel_card (member_id,card_no,amount,rech_amount,unrech_amount,status,bind_time,create_time,last_update_time)"
                + " VALUES (:memberId, :cardNo, :amount, :rechAmount, :unrechAmount, :status, :bindTime, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RefuelCardEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RefuelCardEntity data) {
        data.setCreateTime(data.getBindTime());
        data.setLastUpdateTime(data.getBindTime());
        data.setAmount(BigDecimal.ZERO);
        data.setRechAmount(BigDecimal.ZERO);
        data.setUnrechAmount(BigDecimal.ZERO);
        data.setStatus("0");
    }
}
