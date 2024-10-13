package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.ColorPayEntity;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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

@Configuration("bwoilOrderColorPayMigration")
public class BwoilOrderColorPayMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderColorPayMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderColorPayMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<ColorPayEntity, ColorPayEntity>().factory(stepBuilderFactory).name("订单服务-彩生活支付表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderColorPayMigrationReader")
    public ItemReader<ColorPayEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_otherpay_payment";
        return new StepReaderBuilder<ColorPayEntity>().dataSource(dataSource).sql(sqlStr).mappedClass(ColorPayEntity.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilOrderColorPayMigrationProcessor")
    public ItemProcessor<ColorPayEntity, ColorPayEntity> processor() {
        return item -> {
            ColorPayEntity target = new ColorPayEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderColorPayMigrationWriter")
    public ItemWriter<ColorPayEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_color_pay (id, member_id, op_id, money, discount, message, payment_id, order_id, channel_name, ret_info, status, create_time, last_update_time) VALUES (id, :memberId, :opId, :money, :discount, :message, :paymentId, :orderId, :channelName, :retInfo, :status, :createTime, :lastmodify)";
        return new StepWriterBuilder<ColorPayEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(ColorPayEntity target) {

            target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
            target.setLastmodify(TimeStampUtil.convertDate(target.getLastmodify()));
        if (StringUtils.isNotEmpty(target.getRetInfo())) {
            target.setRetInfo(PhpSeriailizeUtil.getPhpString(target.getRetInfo()));
        }
    }
}
