package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.origin.OriginMemberGasstationMember;
import com.bwoil.c2b.migration.steps.other.pojo.target.BwoilMemberGasstationMember;
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

@Configuration("bwoilMemberGasstationMemberMigration")
public class BwoilMemberGasstationMemberMigration {


    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberGasstationMemberMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberGasstationMemberStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberGasstationMember, BwoilMemberGasstationMember>().factory(stepBuilderFactory).name("会员服务-加油站会员绑定-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberGasstationMemberReader")
    public ItemReader<OriginMemberGasstationMember> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select * from sdb_gasstation_member";
        return new StepReaderBuilder<OriginMemberGasstationMember>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberGasstationMember.class).multithreaded(true).sortKeys("member_id").build();
    }

    @Bean(name = "bwoilMemberGasstationMemberProcessor")
    public ItemProcessor<OriginMemberGasstationMember, BwoilMemberGasstationMember> processor() {
        return item -> {
            BwoilMemberGasstationMember target = new BwoilMemberGasstationMember();
            target.setId(item.getMemberId());
            target.setWeixinNc(item.getWeixinNc());
            target.setWeixinImg(item.getWeixinImg());
            target.setOpenid(item.getOpenid());
            target.setMiniOpenid(item.getMiniOpenid());
            target.setCreateTime(TimeStampUtil.getTimestampWithDefault(item.getCreateTime()));
            target.setLastUpdateTime(TimeStampUtil.getTimestampWithDefault(item.getLastmodify()));
            return target;
        };
    }

    @Bean(name = "bwoilMemberGasstationMemberWriter")
    public ItemWriter<BwoilMemberGasstationMember> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_member_gasstation_member (id, weixin_nc, weixin_img, openid, mini_openid, create_time, last_update_time) VALUES (:id, :weixinNc, :weixinImg, :openid, :miniOpenid, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<BwoilMemberGasstationMember>().dataSource(dataSource).sql(sqlStr).build();
    }
}
