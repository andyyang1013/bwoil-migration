package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationActivityItem;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationActivityItem;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Configuration("bwoilOperationGasstationActivityItemMigration")
public class BwoilOperationGasstationActivityItemMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationActivityItemMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationActivityItemMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationActivityItem, BwoilOperationGasstationActivityItem>().factory(stepBuilderFactory).name("运营服务-加油站活动的明细表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationActivityItemMigrationReader")
    public ItemReader<OriginOperationGasstationActivityItem> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_oil_activity";
        return new StepReaderBuilder<OriginOperationGasstationActivityItem>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationActivityItem.class).multithreaded(true).sortKeys("oil_activity_id").build();
    }

    @Bean("bwoilOperationGasstationActivityItemMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationActivityItem, BwoilOperationGasstationActivityItem> processor() {
        return item -> {
            BwoilOperationGasstationActivityItem target = new BwoilOperationGasstationActivityItem();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getOilActivityId());
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            //json转换
            if (item.getDiscountId() == 2) {
                JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getData()));
                JSONObject json = new JSONObject();
                List<String> list = new ArrayList<>();
                if (resultJson.size() != 0) {
                    Set<String> it = resultJson.keySet();
                    for (String key : it) {
                        String s = (String) resultJson.get(key);
                        list.add(s);
                    }
                }
                json.put(list.get(1), list.get(0));
                target.setData(json.toJSONString());
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationActivityItemMigrationWriter")
    public ItemWriter<BwoilOperationGasstationActivityItem> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_activity_item (id, activity_id, discount_id, oil_id, data, create_time) VALUES (:id, :activityId, :discountId, :oilId, :data, :createTime)";
        return new StepWriterBuilder<BwoilOperationGasstationActivityItem>().dataSource(dataSource).sql(sqlStr).build();
    }
}
