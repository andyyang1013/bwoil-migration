package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationActivity;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationActivity;
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
import java.util.*;

@Configuration("bwoilOperationGasstationActivityMigration")
public class BwoilOperationGasstationActivityMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationActivityMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationActivityMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationActivity, BwoilOperationGasstationActivity>().factory(stepBuilderFactory).name("运营服务-加油站活动相关信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationActivityMigrationReader")
    public ItemReader<OriginOperationGasstationActivity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_station_activity";
        return new StepReaderBuilder<OriginOperationGasstationActivity>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationActivity.class).multithreaded(true).sortKeys("activity_id").build();
    }

    @Bean("bwoilOperationGasstationActivityMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationActivity, BwoilOperationGasstationActivity> processor() {
        return item -> {
            BwoilOperationGasstationActivity target = new BwoilOperationGasstationActivity();
            BeanUtils.copyProperties(item, target);
            target.setCreateName(item.getCreatedName());
            target.setId(item.getActivityId());
            //转换json信息
            JSONObject json = new JSONObject();
            String s = "channel";
            int i = 1;
            JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getChannelId()));
            if (resultJson.size() != 0) {
                Set<String> it = resultJson.keySet();
                for (String key : it) {
                    String mid = resultJson.getString(key);
                    if (mid != null) {
                        json.put(s + i, Integer.valueOf(mid));
                        i++;
                    }
                }
            }
            target.setChannelId(json.toJSONString());
            //转日期
            if (item.getModifyTime() == null || item.getModifyTime() == 0) {
                target.setModifyTime(null);
            } else {
                target.setModifyTime(new Date(Long.parseLong(item.getModifyTime().toString()) * 1000L));
            }
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            if (item.getStartDate() == null || item.getStartDate() == 0) {
                target.setStartDate(null);
            } else {
                target.setStartDate(new Date(Long.parseLong(item.getStartDate().toString()) * 1000L));
            }
            if (item.getEndDate() == null || item.getEndDate() == 0) {
                target.setEndDate(null);
            } else {
                target.setEndDate(new Date(Long.parseLong(item.getEndDate().toString()) * 1000L));
            }
            //转换小时json
            JSONObject resultJson2 = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getStartHour()));
            Set<String> it2 = resultJson2.keySet();
            Calendar c2 = Calendar.getInstance();
            List<Integer> list2 = new ArrayList<>();
            for (String key : it2) {
                String mid = resultJson2.getString(key);
                if (mid != null) {
                    list2.add(Integer.valueOf(mid));
                }
            }
            c2.set(target.getStartDate().getYear(), target.getStartDate().getMonth(), target.getStartDate().getDay(),
                    list2.get(0), list2.get(1), 00);
            //c2.add(target.getStartDate().getMonth(),-1);
            target.setStartHour(c2.getTime());
            //结束小时
            JSONObject resultJson3 = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getEndHour()));
            Set<String> it3 = resultJson3.keySet();
            Calendar c3 = Calendar.getInstance();
            List<Integer> list3 = new ArrayList<>();
            for (String key : it3) {
                String mid = resultJson3.getString(key);
                if (mid != null) {
                    list3.add(Integer.valueOf(mid));
                }
            }
            c3.set(target.getEndDate().getYear(), target.getEndDate().getMonth(), target.getEndDate().getDay(),
                    list3.get(0), list3.get(1), 00);
            //c3.add(target.getEndDate().getMonth(),-1);
            target.setEndHour(c3.getTime());
            return target;
        };
    }

    @Bean("bwoilOperationGasstationActivityMigrationWriter")
    public ItemWriter<BwoilOperationGasstationActivity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_activity " +
                "(id, activity_name, station_id, channel_id, modify_name, create_name, status, modify_time, create_time, start_date, end_date, start_hour, end_hour) VALUES" +
                "(:id, :activityName, :stationId, :channelId, :modifyName, :createName, :status, :modifyTime, :createTime, :startDate, :endDate, :startHour, :endHour)";
        return new StepWriterBuilder<BwoilOperationGasstationActivity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
