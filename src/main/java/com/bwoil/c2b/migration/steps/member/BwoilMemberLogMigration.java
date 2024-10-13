package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberLog;
import com.bwoil.c2b.migration.steps.member.pojo.target.MemberLogEntity;
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

@Configuration("bwoilMemberLogMigration")
public class BwoilMemberLogMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberLogMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberLogMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberLog, MemberLogEntity>().factory(stepBuilderFactory).name("会员服务-操作记录-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean
    public ItemReader<OriginMemberLog> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT member_log_id, member_id, member_account, object_type, ip, in_ip, mac, terminal, log_memo, `status`, operator_id, operator_name, log_data, FROM_UNIXTIME(operator_time) create_time" +
                " FROM sdb_b2c_member_log where operator_time > UNIX_TIMESTAMP('2019-01-01')";

        return new StepReaderBuilder<OriginMemberLog>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberLog.class).multithreaded(true).sortKeys("member_log_id").build();
    }

    @Bean
    public ItemWriter<MemberLogEntity> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_member_log (`log_id`, `member_id`, `member_account`, `object_type`,  `ip`, `in_ip`, `mac`, `terminal`, `log_memo`, `status`, `operator_id`, `operator_name`, `log_data`, `create_time`)" +
                " VALUES (:logId, :memberId, :memberAccount, :objectType, :ip, :inIp, :mac, :terminal, :logMemo, :status, :operatorId, :operatorName, :logData, :createTime)";
        return new StepWriterBuilder<MemberLogEntity>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean
    public ItemProcessor<OriginMemberLog, MemberLogEntity> processor() {
        return item -> {
            MemberLogEntity target = new MemberLogEntity();
            BeanUtils.copyProperties(item, target);
            target.setLogId(item.getMemberLogId());
            return target;
        };
    }
}
