package com.bwoil.c2b.migration.steps.operation.feedback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.feedback.pojo.origin.OriginOperationFeedback;
import com.bwoil.c2b.migration.steps.operation.feedback.pojo.target.BwoilOperationFeedback;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
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
import java.util.Set;

@Configuration("bwoilOperationFeedbackMigration")
public class BwoilOperationFeedbackMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationFeedbackMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationFeedbackMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationFeedback, BwoilOperationFeedback>().factory(stepBuilderFactory).name("运营服务-客服-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationFeedbackMigrationReader")
    public ItemReader<OriginOperationFeedback> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_feedback ";
        return new StepReaderBuilder<OriginOperationFeedback>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationFeedback.class).multithreaded(true).sortKeys("feedback_id").build();
    }

    @Bean("bwoilOperationFeedbackMigrationProcessor")
    public ItemProcessor<OriginOperationFeedback, BwoilOperationFeedback> processor() {
        return item -> {
            BwoilOperationFeedback target = new BwoilOperationFeedback();
            BeanUtils.copyProperties(item, target);
            target.setCreatetime(new Date(item.getCreatetime() * 1000));
            if (item.getOptTime() == null) {
                target.setOptTime(new Date());
            } else {
                target.setOptTime(new Date(item.getOptTime() * 1000));
            }
            target.setFeedbackId(item.getFeedbackId());
            target.setMemberId(item.getMemberId());
            JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getImages()));
            Set<String> it = resultJson.keySet();
            StringBuffer temp = new StringBuffer();
            for (String key : it) {
                String mid = resultJson.getString(key);
                if (mid != null) {
                    temp.append(mid).append("|"); //imgd_id 数组以竖线隔开
                }
            }
            target.setImages(temp.toString());
            target.setmobile(item.getMoblie());
            return target;
        };
    }

    @Bean("bwoilOperationFeedbackMigrationWriter")
    public ItemWriter<BwoilOperationFeedback> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_feedback (feedback_id, member_id, mobile, images, content, admin_content, createtime, opt_time, status)" +
                "VALUES (:feedbackId, :memberId, :mobile, :images, :content, :adminContent, :createtime, :optTime, :status)";
        return new StepWriterBuilder<BwoilOperationFeedback>().dataSource(dataSource).sql(sqlStr).build();
    }
}
