package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.HolidayCardEntity;
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

@Configuration("bwoilOrderHdCardMigration")
public class BwoilOrderHdCardMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderHdCardMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderHdCardMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<HolidayCardEntity, HolidayCardEntity>().factory(stepBuilderFactory).name("订单服务-节日卡表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderHdCardMigrationReader")
    public ItemReader<HolidayCardEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer origSql = new StringBuffer();
        origSql.append("select ")
                .append(" card_bn, card_secret as card_crypt, product_id, '0' as product_name, region_name,")
                .append(" region_id, oil_name, oil_id, order_id, bind_member_id as  member_id, cardholder as  mobile, ")
                .append(" bind_time, amount, cashperiods as term, 'M' as term_unit, card_status as bind_status, buyer_member_id as buy_user, ")
                .append(" '0' as status,  create_time")
                .append(" from sdb_ycy_card_actual");

        return new StepReaderBuilder<HolidayCardEntity>().dataSource(dataSource).sql(origSql.toString()).mappedClass(HolidayCardEntity.class).multithreaded(true).sortKeys("card_bn").build();
    }

    @Bean("bwoilOrderHdCardMigrationProcessor")
    public ItemProcessor<HolidayCardEntity, HolidayCardEntity> processor() {
        return item -> {
            HolidayCardEntity target = new HolidayCardEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderHdCardMigrationWriter")
    public ItemWriter<HolidayCardEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("INSERT INTO bwoil_order_hd_card ");
        sqlStr.append("(card_bn,card_crypt,product_id,product_name,region_name,region_id,oil_name,oil_id,order_id,member_id,mobile,bind_time,amount,term,term_unit,bind_status,buy_user,status,create_time,last_update_time)")
                .append("VALUES")
                .append("(:cardBn, :cardCrypt, :productId, :productName, :regionName, :regionId, :oilName, :oilId, :orderId, :memberId, :mobile, :bindTime, :amount, :term, :termUnit, :bindStatus, :buyUser, :status, :createTime, :lastUpdateTime)");

        return new StepWriterBuilder<HolidayCardEntity>().dataSource(dataSource).sql(sqlStr.toString()).build();
    }

    private void convert(HolidayCardEntity target) {
        target.setStatus("0");
        target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
        target.setBindTime(TimeStampUtil.convertDate(target.getBindTime()));
        if (StringUtils.isEmpty(target.getBindTime()) || target.getBindTime().equals("0000-00-00 00:00:00")) {
            target.setBindStatus("N");
            target.setLastUpdateTime(target.getCreateTime());
        } else {
            target.setLastUpdateTime(target.getBindTime());
            target.setBindStatus("Y");
        }

    }
}
