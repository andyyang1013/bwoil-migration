package com.bwoil.c2b.migration.steps.order;

import java.util.Date;

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
import com.bwoil.c2b.migration.steps.order.entity.CardSeqEntity;

@Configuration("bwoilOrderCardSeqMigration")
public class BwoilOrderCardSeqMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCardSeqMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCardSeqMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CardSeqEntity, CardSeqEntity>().factory(stepBuilderFactory).name("订单服务-卡序列表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCardSeqMigrationReader")
    public ItemReader<CardSeqEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select substr(card_bn,1,10) as  card_type, max(card_bn) max_card_bn from sdb_ycy_cards where length(card_bn)=18 group by substr(card_bn,1,10)";

        return new StepReaderBuilder<CardSeqEntity>().dataSource(dataSource).sql(sqlStr).mappedClass(CardSeqEntity.class).build();
    }

    @Bean("bwoilOrderCardSeqMigrationProcessor")
    public ItemProcessor<CardSeqEntity, CardSeqEntity> processor() {
        return item -> {
        	CardSeqEntity target = new CardSeqEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderCardSeqMigrationWriter")
    public ItemWriter<CardSeqEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_card_seq (card_type,card_seq,create_time) "
                + "VALUES (:cardType, :cardSeq, :createTime)";
        return new StepWriterBuilder<CardSeqEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(CardSeqEntity data) {
    	String seq = data.getMaxCardBn().substring(10);
    	data.setCardSeq(Integer.parseInt(seq)+10);
    	data.setCreateTime(new Date());
    }
}
