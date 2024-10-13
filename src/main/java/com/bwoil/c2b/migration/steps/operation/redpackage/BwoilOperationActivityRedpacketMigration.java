package com.bwoil.c2b.migration.steps.operation.redpackage;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.redpackage.pojo.origin.OriginOperationActivityRedpacket;
import com.bwoil.c2b.migration.steps.operation.redpackage.pojo.target.BwoilOperationActivityRedpacket;
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

@Configuration("bwoilOperationActivityRedpacketMigration")
public class BwoilOperationActivityRedpacketMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationActivityRedpacketMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationActivityRedpacketMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationActivityRedpacket, BwoilOperationActivityRedpacket>().factory(stepBuilderFactory).name("运营服务-分享红包活动-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationActivityRedpacketMigrationReader")
    public ItemReader<OriginOperationActivityRedpacket> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_activity_redpacket ";
        return new StepReaderBuilder<OriginOperationActivityRedpacket>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationActivityRedpacket.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilOperationActivityRedpacketMigrationProcessor")
    public ItemProcessor<OriginOperationActivityRedpacket, BwoilOperationActivityRedpacket> processor() {
        return item -> {
            BwoilOperationActivityRedpacket target = new BwoilOperationActivityRedpacket();
            target.setId(item.getId());
            target.setOrderId(item.getOrderId() == null ? null : item.getOrderId().toString());
            target.setMemberId(item.getMemberId());
            target.setMobile(item.getMobile());
            target.setCpnsDesc(item.getCpnsDesc());
            target.setCpnsInfo(item.getCpnsInfo());
            target.setName(item.getName());
            target.setAmount(item.getAmount());
            target.setGetTime(TimeStampUtil.getTimestampWithNull(item.getGetTime()));
            target.setAccountTime(TimeStampUtil.getTimestampWithNull(item.getAccountTime()));
            target.setDeadTime(TimeStampUtil.getTimestampWithNull(item.getDeadTime()));
            target.setRegTime(TimeStampUtil.getTimestampWithNull(item.getRegTime()));
            target.setIsAuth(item.getIsAuth());
            target.setOpenid(item.getOpenid());
            target.setWeixinNc(item.getWeixinNc());
            target.setWeixinImg(item.getWeixinImg());
            target.setSendStatus(item.getSendStatus());
            return target;
        };
    }

    @Bean("bwoilOperationActivityRedpacketMigrationWriter")
    public ItemWriter<BwoilOperationActivityRedpacket> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_activity_redpacket (id, order_id, member_id, mobile, cpns_desc, cpns_info, name, amount, get_time, account_time, dead_time, reg_time, is_auth, openid, weixin_nc, weixin_img, send_status) VALUES (:id, :orderId, :memberId, :mobile, :cpnsDesc, :cpnsInfo, :name, :amount, :getTime, :accountTime, :deadTime, :regTime, :isAuth, :openid, :weixinNc, :weixinImg, :sendStatus)";
        return new StepWriterBuilder<BwoilOperationActivityRedpacket>().dataSource(dataSource).sql(sqlStr).build();
    }
}
