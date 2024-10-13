package com.bwoil.c2b.migration.steps.operation.cashcoupon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin.OriginOperationCommandActivity;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.req.CommandCouponsInfoReq;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target.BwoilOperationCommandActivity;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration("bwoilOperationCommandActivityMigration")
public class BwoilOperationCommandActivityMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCommandActivityMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCommandActivityMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationCommandActivity, BwoilOperationCommandActivity>().factory(stepBuilderFactory).name("运营服务-口令活动列表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationCommandActivityMigrationReader")
    public ItemReader<OriginOperationCommandActivity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_command_activity";
        return new StepReaderBuilder<OriginOperationCommandActivity>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCommandActivity.class).multithreaded(true).sortKeys("activity_id").build();
    }

    @Bean("bwoilOperationCommandActivityMigrationProcessor")
    public ItemProcessor<OriginOperationCommandActivity, BwoilOperationCommandActivity> processor() {
        return item -> {
            BwoilOperationCommandActivity target = new BwoilOperationCommandActivity();
            JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getActivityCoupons()));
            Set<String> info = resultJson.keySet();
            List<CommandCouponsInfoReq> commandCouponsInfoReqList = new ArrayList<>();
            for (String key : info) {
                CommandCouponsInfoReq req = new CommandCouponsInfoReq();
                Integer value = resultJson.getInteger(key);
                req.setCouponActivityId(Integer.parseInt(key));
                req.setNum(value);
                commandCouponsInfoReqList.add(req);
            }
            target.setActivityId(item.getActivityId());//活动ID
            target.setActivityBn(item.getActivityBn());//活动编号
            target.setActivityName(item.getActivityName());//活动名称
            target.setActivityDesc(item.getActivityDesc());//活动说明
            target.setActivityWord(item.getActivityWord());//领券口令
            target.setActivityTotalNum(item.getActivityTotalNum());//领取总量
            target.setActivityJoinNum(item.getActivityJoinNum());//已领取总量
            target.setActivityCoupons(JSON.toJSONString(commandCouponsInfoReqList));//赠送现金券
            target.setActivityStatus(item.getActivityStatus());//活动状态
            target.setActivityStartDate(TimeStampUtil.getTimestampWithNull(item.getActivityStartDate()));//开始时间
            target.setActivityEndDate(TimeStampUtil.getTimestampWithNull(item.getActivityEndDate()));//结束时间
            target.setCreateName(item.getCreateName());//创建人
            target.setCreateTime(TimeStampUtil.getTimestampWithNull(item.getCreateTime()));//创建时间
            target.setModifyName(item.getModifyName());//最后修改人
            target.setUpdateTime(TimeStampUtil.getTimestampWithNull(item.getLastmodify()));//最后更新时间
            return target;
        };
    }

    @Bean("bwoilOperationCommandActivityMigrationWriter")
    public ItemWriter<BwoilOperationCommandActivity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_command_activity (activity_id, activity_bn, activity_name, activity_desc, activity_word, activity_total_num, activity_join_num, activity_coupons, " +
                "activity_status, activity_start_date, activity_end_date, create_name, create_time, modify_name, update_time) " +
                "VALUES (:activityId, :activityBn, :activityName, :activityDesc, :activityWord, :activityTotalNum, :activityJoinNum, :activityCoupons, " +
                ":activityStatus, :activityStartDate, :activityEndDate, :createName, :createTime, :modifyName, :updateTime)";
        return new StepWriterBuilder<BwoilOperationCommandActivity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
