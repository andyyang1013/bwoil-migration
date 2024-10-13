package com.bwoil.c2b.migration.steps.operation.wap;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.wap.pojo.origin.OriginOperationWapTopnews;
import com.bwoil.c2b.migration.steps.operation.wap.pojo.target.BwoilOperationWapTopnews;
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

@Configuration("bwoilOperationWapTopnewsMigration")
public class BwoilOperationWapTopnewsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationWapTopnewsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationWapTopnewsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationWapTopnews, BwoilOperationWapTopnews>().factory(stepBuilderFactory).name("运营服务-头条(油价分析)-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationWapTopnewsMigrationReader")
    public ItemReader<OriginOperationWapTopnews> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_wap_topnews";
        return new StepReaderBuilder<OriginOperationWapTopnews>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationWapTopnews.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilOperationWapTopnewsMigrationProcessor")
    public ItemProcessor<OriginOperationWapTopnews, BwoilOperationWapTopnews> processor() {
        return item -> {
            BwoilOperationWapTopnews target = new BwoilOperationWapTopnews();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getId());
            target.setAppProid(item.getTypeBn());
            target.setMsiteProid(item.getMsiteTypeBn());
            target.setAddTime(new Date(item.getAddTime() * 1000));
            target.setEditTime(new Date(item.getEditTime() * 1000));
            target.setStatus("0");
            target.setBaseQty(item.getBaseQty());
            target.setReadQty(item.getReadQty());
            target.setLikeQty(item.getLikeQty());
            return target;
        };
    }

    @Bean("bwoilOperationWapTopnewsMigrationWriter")
    public ItemWriter<BwoilOperationWapTopnews> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_wap_topnews (id, author, title, thumbnail, context, ison, app_proid, status, edit_time, add_time, msite_proid, base_qty, read_qty, like_qty, sort) " +
                "VALUES (:id, :author, :title, :thumbnail, :context, :ison, :appProid, :status, :editTime, :addTime, :msiteProid, :baseQty, :readQty, :likeQty, :sort)";
        return new StepWriterBuilder<BwoilOperationWapTopnews>().dataSource(dataSource).sql(sqlStr).build();
    }
}
