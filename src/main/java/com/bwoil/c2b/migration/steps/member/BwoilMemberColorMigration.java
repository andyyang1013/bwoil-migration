package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberColor;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMemberColor;
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

@Configuration("bwoilMemberColorMigration")
public class BwoilMemberColorMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberColorMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberColorMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberColor, BwoilMemberColor>().factory(stepBuilderFactory).name("会员服务-彩生活会员-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberColorMigrationReader")
    public ItemReader<OriginMemberColor> reader(@Qualifier("originDataSource") DataSource dataSource) {

        String sql = "select id, member_id, openid, mobile, refuelChannel, FROM_UNIXTIME(lastmodify) register_time FROM sdb_b2c_member_color  order by id asc ";
//              +  " where a.member_id='3010545' order by a.member_id asc ";
        return new StepReaderBuilder<OriginMemberColor>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberColor.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilMemberColorMigrationProcessor")
    public ItemProcessor<OriginMemberColor, BwoilMemberColor> processor() {
        return item -> {
            BwoilMemberColor target = new BwoilMemberColor();
            BeanUtils.copyProperties(item, target);

            return target;
        };
    }

    @Bean("bwoilMemberColorMigrationWriter")
    public ItemWriter<BwoilMemberColor> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_member_color (id, member_id, openid, mobile, refuelChannel, register_time, status) VALUES (:id,:memberId,:openid,:mobile,:refuelchannel,:registerTime,:status)";
        return new StepWriterBuilder<BwoilMemberColor>().dataSource(dataSource).sql(sqlStr).build();
    }
}
