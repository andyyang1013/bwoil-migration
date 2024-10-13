package com.bwoil.c2b.migration.steps.operation.oil;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.oil.pojo.origin.OriginOperationOilActivity;
import com.bwoil.c2b.migration.steps.operation.oil.pojo.target.BwoilOperationOilActivity;
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

@Configuration("bwoilOperationOilActivityMigration")
public class BwoilOperationOilActivityMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationOilActivityMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationOilActivityMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationOilActivity, BwoilOperationOilActivity>().factory(stepBuilderFactory).name("运营服务-送油活动表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationOilActivityMigrationReader")
    public ItemReader<OriginOperationOilActivity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_goil_activity";
        return new StepReaderBuilder<OriginOperationOilActivity>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationOilActivity.class).multithreaded(true).sortKeys("goils_id").build();
    }

    @Bean("bwoilOperationOilActivityMigrationProcessor")
    public ItemProcessor<OriginOperationOilActivity, BwoilOperationOilActivity> processor() {
        return item -> {
            BwoilOperationOilActivity target = new BwoilOperationOilActivity();
            // 活动ID
            target.setId(item.getGoilsId());
            // 活动编号
            target.setActivityBn(item.getGoilsBn());
            // 送油活动类型
            switch (item.getGoilsType()) {
                // 送油活动
                case "goil":
                    target.setActivityType(1);
                    break;
                // 专题活动
                case "special":
                    target.setActivityType(2);
                    break;
                // 抽奖活动
                case "draw":
                    target.setActivityType(3);
                    break;
                // 司机活动
                case "drivers":
                    target.setActivityType(4);
                    break;
                // 旅游小姐活动
                case "goddess":
                    target.setActivityType(5);
                    break;
            }
            // 活动名称
            target.setActivityName(item.getGoilsName());
            // 油品地区ID
            target.setOilRegionId(item.getGoilsRegionId());
            // 油品地区名称
            target.setOilRegionName(item.getGoilsRegionName());
            // 油品ID
            target.setOilSkuId(item.getGoilsOilId());
            // 油品名称
            target.setOilName(item.getGoilsOilName());
            // 活动赠送类型
            switch (item.getGoilsGiveType()) {
                // 固定升数
                case "1":
                    target.setOilGiftQuantityType(1);
                    break;
                // 随机升数
                case "2":
                    target.setOilGiftQuantityType(2);
                    break;
            }
            // 活动赠送油量
            target.setOilQuantityPerGift(item.getGoilsGive());
            // 数量限制
            if (item.getGoilsLimitGive() == 0) {
                // 不限制
                target.setOilTotalNumLimit(0);
            }
            if (item.getGoilsLimitGive() > 0) {
                // 限制
                target.setOilTotalNumLimit(1);
                // 送油的个数总量
                target.setOilTotalNum(item.getGoilsLimitGive());
            }
            // 活动已赠送总量
            target.setGoilsSumGive(item.getGoilsSumGive());
            // 是否启用交互语
            switch (item.getGoilsGiveIstips()) {
                // 不启用
                case "0":
                    target.setGoilsGiveIstips(0);
                    break;
                // 启用
                case "1":
                    target.setGoilsGiveIstips(1);
                    target.setActivityInterlanguage(item.getGoilsGiveTips());
                    break;
            }
            // 规则描述
            target.setActivityRuleDesc(item.getGoilsDesc());
            // 状态
            switch (item.getGoilsStatus()) {
                // 启用
                case "1":
                    target.setActivityEnable(1);
                    break;
                // 禁用
                case "0":
                    target.setActivityEnable(0);
                    break;
            }
            // 使用期限

            // 活动日期
            target.setActivityBeginTime(TimeStampUtil.getTimestampWithDefault(item.getGoilsStartDate()));
            target.setActivityEndTime(TimeStampUtil.getTimestampWithDefault(item.getGoilsEndDate()));
            // 创建时间
            target.setCreateTime(TimeStampUtil.getTimestampWithDefault(item.getCreateTime()));
            // 更新时间
            target.setUpdateTime(TimeStampUtil.getTimestampWithDefault(item.getLastmodify()));

            // 提油值
            target.setOilQuantityPerGift(item.getGoilsGive());
            // 初始值（新用户）
            target.setGoilsGiveInitNew(item.getGoilsGiveInitNew());
            // 初始值（老用户）
            target.setGoilsGiveInitOld(item.getGoilsGiveInitOld());
            // 加油值（新用户）
            target.setGoilsGiveLikeNew(item.getGoilsGiveLikeNew());
            // 加油值（老用户）
            target.setGoilsGiveLike(item.getGoilsGiveLike());

            // 分享标题
            target.setGoilsShareTitle(item.getGoilsShareTitle());
            // 分享图片
            target.setGoilsShareImage(item.getGoilsShareImage());
            // 分享描述
            target.setGoilsShareDesc(item.getGoilsShareDesc());
            // 页面标题1
            target.setGoilsTitle(item.getGoilsTitle());
            // 页面标题2
            target.setGoilsSubtitle(item.getGoilsSubtitle());
            // 允许老用户点赞
            switch (item.getNewLike()) {
                case "true":
                    target.setNewLike(1);
                    break;
                case "false":
                    target.setNewLike(0);
                    break;
            }
            // 允许老用户参与
            switch (item.getOldJoin()) {
                case "true":
                    target.setOldJoin(1);
                    break;
                case "false":
                    target.setOldJoin(0);
                    break;
            }

            return target;
        };
    }

    @Bean("bwoilOperationOilActivityMigrationWriter")
    public ItemWriter<BwoilOperationOilActivity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_oil_activity (id, activity_name, activity_type, activity_bn, oil_region_id, oil_region_name, oil_sku_id, oil_name, oil_quantity_per_gift, oil_gift_quantity_type, oil_total_num, goils_sum_give, goils_sum_num_join, oil_total_num_limit, goils_sum_num_like, goils_give_istips, activity_interlanguage, activity_rule_desc, activity_enable, goils_give_init_new, goils_give_init_old, goils_give_like, goils_give_like_new, oil_valid_days, activity_begin_time, activity_end_time, goils_share_title, goils_share_desc, goils_share_image, new_like, old_join, goils_title, goils_subtitle, create_time, update_time, status) VALUES (:id, :activityName, :activityType, :activityBn, :oilRegionId, :oilRegionName, :oilSkuId, :oilName, :oilQuantityPerGift, :oilGiftQuantityType, :oilTotalNum, :goilsSumGive, :goilsSumNumJoin, :oilTotalNumLimit, :goilsSumNumLike, :goilsGiveIstips, :activityInterlanguage, :activityRuleDesc, :activityEnable, :goilsGiveInitNew, :goilsGiveInitOld, :goilsGiveLike, :goilsGiveLikeNew, :oilValidDays, :activityBeginTime, :activityEndTime, :goilsShareTitle, :goilsShareDesc, :goilsShareImage, :newLike, :oldJoin, :goilsTitle, :goilsSubtitle, :createTime, :updateTime, :status)";
        return new StepWriterBuilder<BwoilOperationOilActivity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
