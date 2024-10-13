package com.bwoil.c2b.migration.steps.operation.wap;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.wap.pojo.origin.OriginOperationWapAds;
import com.bwoil.c2b.migration.steps.operation.wap.pojo.target.BwoilOperationWapAds;
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

@Configuration("bwoilOperationWapAdsMigration")
public class BwoilOperationWapAdsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationWapAdsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationWapAdsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationWapAds, BwoilOperationWapAds>().factory(stepBuilderFactory).name("运营服务-三端广告-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationWapAdsMigrationReader")
    public ItemReader<OriginOperationWapAds> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_wap_ads";
        return new StepReaderBuilder<OriginOperationWapAds>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationWapAds.class).multithreaded(true).sortKeys("ad_id").build();
    }

    @Bean("bwoilOperationWapAdsMigrationProcessor")
    public ItemProcessor<OriginOperationWapAds, BwoilOperationWapAds> processor() {
        return item -> {
            BwoilOperationWapAds target = new BwoilOperationWapAds();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getAdId());
            if (item.getStatus().equals("true")) {
                target.setStatus("0");
            } else {
                target.setStatus("-1");
            }
            if (item.getShare().equals("true")) {
                target.setShare(1);
            } else {
                target.setShare(0);
            }
            target.setShareTitle(item.getSharetitle());
            target.setAddTime(new Date(item.getAddTime() * 1000));
            target.setEditTime(new Date());
            return target;
        };
    }

    @Bean("bwoilOperationWapAdsMigrationWriter")
    public ItemWriter<BwoilOperationWapAds> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_wap_ads (id, image_url, image_hyperlink, ad_title, ad_type, ad_jumpusage, instruction, sort, type_bn, edit_time, add_time, status, share, share_banner, share_title, share_content) VALUES (:id, :imageUrl, :imageHyperlink, :adTitle, :adType, :adJumpusage, :instruction, :sort, :typeBn, :editTime, :addTime, :status, :share, :shareBanner, :shareTitle, :shareContent)";
        return new StepWriterBuilder<BwoilOperationWapAds>().dataSource(dataSource).sql(sqlStr).build();
    }
}
