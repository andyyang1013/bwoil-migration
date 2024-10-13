package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberBindTag;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMemberBindTag;
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

/**
 * 绑定第三方平台（微信）
 *
 * @author yangda
 */
@Configuration("bwoilMemberBindTagMigration")
public class BwoilMemberBindTagMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberBindTagMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberBindTagMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberBindTag, BwoilMemberBindTag>().factory(stepBuilderFactory).name("会员服务-绑定第三方平台（微信）-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberBindTagMigrationReader")
    public ItemReader<OriginMemberBindTag> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT id, tag_type, open_id, tag_name, member_id, disabled, createtime " +
                " FROM sdb_pam_bind_tag ";
        return new StepReaderBuilder<OriginMemberBindTag>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberBindTag.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilMemberBindTagMigrationWriter")
    public ItemWriter<BwoilMemberBindTag> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_member_bind_tag(id, tag_type, tag_id, tag_name, member_id, disabled, create_time) " +
                " VALUES (:id, :tagType, :tagId, :tagName, :memberId, :disabled, :createTime) ;";
        return new StepWriterBuilder<BwoilMemberBindTag>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean("bwoilMemberBindTagMigrationProcessor")
    public ItemProcessor<OriginMemberBindTag, BwoilMemberBindTag> processor() {
        return item -> {
            BwoilMemberBindTag target = new BwoilMemberBindTag();
            BeanUtils.copyProperties(item, target);
            target.setCreateTime(new Date(item.getCreatetime() * 1000L));
            target.setTagId(item.getOpenId());
            target.setMemberId(Integer.valueOf(item.getMemberId()));
            return target;
        };
    }
}
