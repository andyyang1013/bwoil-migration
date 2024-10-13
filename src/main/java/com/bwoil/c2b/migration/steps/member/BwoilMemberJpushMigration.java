package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberJpushBinds;
import com.bwoil.c2b.migration.steps.member.pojo.target.MemberJpushBindsEntity;
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

@Configuration("bwoilMemberJpushMigration")
public class BwoilMemberJpushMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberJpushMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberJpushMigrationStep")
    public Step importStep(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberJpushBinds, MemberJpushBindsEntity>().factory(stepBuilderFactory).name("会员服务-Jpush绑定-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberJpushMigrationReader")
    public ItemReader<OriginMemberJpushBinds> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT `id`, `member_id`, `shop_member_bn` shop_bn, `platform`, FROM_UNIXTIME(`register_time`) create_time, FROM_UNIXTIME(`last_access_time`) last_update_time, " +
                "`access_times`, `tag`, `alias` mobile, `registration_id`, `memo` FROM sdb_jpush_member_device_binding GROUP BY registration_id";
        return new StepReaderBuilder<OriginMemberJpushBinds>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberJpushBinds.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilMemberJpushMigrationProcessor")
    public ItemProcessor<OriginMemberJpushBinds, MemberJpushBindsEntity> processor() {
        return item -> {
            if ("100d8559094b5d4adf0".equals(item.getRegistrationId())) {
                return null;
            }
            if ("120c83f7602df69ff9a".equals(item.getRegistrationId())) {
                return null;
            }
            MemberJpushBindsEntity target = new MemberJpushBindsEntity();
            BeanUtils.copyProperties(item, target);
            return target;
        };
    }

    @Bean("bwoilMemberJpushMigrationWriter")
    public ItemWriter<MemberJpushBindsEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_member_jpush_device_binds ( `id`, `member_id`, `shop_bn`, `mobile`, `registration_id`, `platform`, `access_times`, `tag`, `memo`, `create_time`, `last_update_time`, `status`) " +
                "VALUES (:id,:memberId,:shopBn,:mobile,:registrationId,:platform,:accessTimes,:tag,:memo,:createTime,:lastUpdateTime,:status)";
        return new StepWriterBuilder<MemberJpushBindsEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}

