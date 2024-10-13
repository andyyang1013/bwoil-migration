package com.bwoil.c2b.migration.steps.operation.wap;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.wap.pojo.origin.OriginOperationWapActivity;
import com.bwoil.c2b.migration.steps.operation.wap.pojo.target.BwoilOperationWapActivity;
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

@Configuration("bwoilOperationWapActivityMigration")
public class BwoilOperationWapActivityMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationWapActivityMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationWapActivityMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationWapActivity, BwoilOperationWapActivity>().factory(stepBuilderFactory).name("运营服务-移动端活动配置-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationWapActivityMigrationReader")
    public ItemReader<OriginOperationWapActivity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_wap_activity";
        return new StepReaderBuilder<OriginOperationWapActivity>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationWapActivity.class).multithreaded(true).sortKeys("activity_id").build();
    }

    @Bean("bwoilOperationWapActivityMigrationProcessor")
    public ItemProcessor<OriginOperationWapActivity, BwoilOperationWapActivity> processor() {
        return item -> {
            BwoilOperationWapActivity target = new BwoilOperationWapActivity();
            BeanUtils.copyProperties(item, target);
            target.setActivityArticleContent(item.getActivityContext());
            target.setActivityArticleTitle(item.getActivityContitle());
            target.setMShow(item.getMsiteShow());
            target.setActivityEtime(new Date(item.getActivityEtime() * 1000));
            target.setActivityStime(new Date(item.getActivityStime() * 1000));
            target.setAddTime(new Date(item.getAddTime() * 1000));
            target.setEditTime(new Date(item.getEditTime() * 1000));
            if (target.getActivityEtime().compareTo(new Date()) > 0) {
                target.setIsPub("1");
            } else {
                target.setIsPub("0");
            }
            target.setStatus("0");
            target.setActivityId(item.getActivityId());
            return target;
        };
    }

    @Bean("bwoilOperationWapActivityMigrationWriter")
    public ItemWriter<BwoilOperationWapActivity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_wap_activity " +
                "(activity_id, activity_title, activity_buttontext, activity_image, activity_hyperlink, m_show, activity_attend, is_pub, status, activity_stime, activity_etime, add_time, edit_time,activity_type,activity_article_title,activity_article_content,type_bn) " +
                "VALUES (:activityId, :activityTitle, :activityButtontext, :activityImage, :activityHyperlink, :mShow, :activityAttend, :isPub, :status, :activityStime, " +
                ":activityEtime, :addTime, :editTime,:activityType,:activityArticleTitle,:activityArticleContent,:typeBn)";
        return new StepWriterBuilder<BwoilOperationWapActivity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
