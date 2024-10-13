package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.ChannelOrdersEntity;
import com.bwoil.c2b.migration.steps.order.target.ChannelOrdersEntityTarg;
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

@Configuration("bwoilChannelOrdersMigration")
public class BwoilChannelOrdersMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilChannelOrdersMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilChannelOrdersMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<ChannelOrdersEntityTarg,  ChannelOrdersEntity>().factory(stepBuilderFactory).name("订单服务-京东关系表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilChannelOrdersMigrationReader")
    public ItemReader< ChannelOrdersEntityTarg> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_channel_orders";
        return new StepReaderBuilder< ChannelOrdersEntityTarg>().dataSource(dataSource).sql(sqlStr).mappedClass( ChannelOrdersEntityTarg.class).build();
    }

    @Bean("bwoilChannelOrdersMigrationProcessor")
    public ItemProcessor<ChannelOrdersEntityTarg,  ChannelOrdersEntity> processor() {
        return item -> {
             ChannelOrdersEntity target = new  ChannelOrdersEntity();
            BeanUtils.copyProperties(item, target);
            if (item.getCreatetime() == null || item.getCreatetime() == 0) {
                target.setCreatetime(null);
            } else {
                target.setCreatetime(new Date(Long.parseLong(item.getCreatetime().toString()) * 1000L));
            }
            if (item.getLastModified() == null || item.getLastModified() == 0) {
                target.setLastModified(null);
            } else {
                target.setLastModified(new Date(Long.parseLong(item.getLastModified().toString()) * 1000L));
            }
            target.setCardStatus(item.getStatus());
            target.setStatus(0);
            return target;
        };
    }

    @Bean("bwoilChannelOrdersMigrationWriter")
    public ItemWriter< ChannelOrdersEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_channel_orders " +
                "(order_id,channel_order_id,channel_parent_order_id,channel_product_id,card_bn,member_id,redeem_code,buy_account,login_account,product_name,price,total_period,total_amount,final_amount,refuel_channel,createtime,last_modified,card_status,pay_status,status) VALUES " +
                "(:orderId, :channelOrderId, :channelParentOrderId, :channelProductId, :cardBn, :memberId, :redeemCode, :buyAccount, :loginAccount, :productName, :price, :totalPeriod, :totalAmount, :finalAmount, :refuelChannel, :createtime, :lastModified, :cardStatus, :payStatus, :status)";
        return new StepWriterBuilder< ChannelOrdersEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
