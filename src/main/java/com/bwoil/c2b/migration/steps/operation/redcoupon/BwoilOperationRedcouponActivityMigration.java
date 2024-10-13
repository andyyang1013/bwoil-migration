package com.bwoil.c2b.migration.steps.operation.redcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.origin.OriginOperationRedcouponActivity;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.target.BwoilOperationRedcouponActivity;
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

@Configuration("bwoilOperationRedcouponActivityMigration")
public class BwoilOperationRedcouponActivityMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationRedcouponActivityMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationRedcouponActivityMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationRedcouponActivity, BwoilOperationRedcouponActivity>().factory(stepBuilderFactory).name("运营服务-满减活动配置-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationRedcouponActivityMigrationReader")
    public ItemReader<OriginOperationRedcouponActivity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_redcoupon_activity ";
        return new StepReaderBuilder<OriginOperationRedcouponActivity>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationRedcouponActivity.class).multithreaded(true).sortKeys("cpn_id").build();
    }

    @Bean("bwoilOperationRedcouponActivityMigrationProcessor")
    public ItemProcessor<OriginOperationRedcouponActivity, BwoilOperationRedcouponActivity> processor() {
        return item -> {
            BwoilOperationRedcouponActivity target = new BwoilOperationRedcouponActivity();
            BeanUtils.copyProperties(item, target);
            target.setCpnStartDate(new Date(item.getCpnStartDate() * 1000));
            target.setCpnEndDate(new Date(item.getCpnEndDate() * 1000));
            target.setCreateTime(new Date(item.getCreateTime() * 1000));
            target.setLastmodify(new Date(item.getLastmodify() * 1000));

            if (item.getIsDeleted().equals("1")) {
                target.setStatus("-1");
            } else {
                target.setStatus("0");
            }
            return target;
        };
    }

    @Bean("bwoilOperationRedcouponActivityMigrationWriter")
    public ItemWriter<BwoilOperationRedcouponActivity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_redcoupon_activity (cpn_id, cpn_bn, cpn_name, reduce_type, cpn_start_date, cpn_end_date, limit_num, order_num, money_sum, discount_sum, m_act_slogan, pc_act_slogan, m_act_url, pc_act_url, cpn_status, with_cashcoupon, with_goil, status, remark, create_time, lastmodify) VALUES (:cpnId, :cpnBn, :cpnName, :reduceType, :cpnStartDate, :cpnEndDate, :limitNum, :orderNum, :moneySum, :discountSum, :mActSlogan, :pcActSlogan, :mActUrl, :pcActUrl, :cpnStatus, :withCashcoupon, :withGoil, :status, :remark, :createTime, :lastmodify)";
        return new StepWriterBuilder<BwoilOperationRedcouponActivity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
