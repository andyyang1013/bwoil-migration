package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberMsg;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMemberMsg;
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

@Configuration("bwoilMemberMsgMigration")
public class BwoilMemberMsgMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberMsgMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberMsgMigrationStep")
    public Step importMemberMsgStep(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberMsg, BwoilMemberMsg>().factory(stepBuilderFactory).name("会员服务-会员消息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberMsgMigrationReader")
    public ItemReader<OriginMemberMsg> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT comment_id,msg_type,title msg_title,`comment` msg_content,`time` send_time," +
                "to_id receiver ,author_id sender FROM sdb_b2c_member_comments where title is not null and time is not null";
        return new StepReaderBuilder<OriginMemberMsg>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberMsg.class).multithreaded(true).sortKeys("comment_id").build();
    }

    @Bean("bwoilMemberMsgMigrationProcessor")
    public ItemProcessor<OriginMemberMsg, BwoilMemberMsg> processor() {
        return item -> {
            BwoilMemberMsg target = new BwoilMemberMsg();
            BeanUtils.copyProperties(item, target);
            target.setMsgId(item.getCommentId());
            long timestamp = item.getSendTime() * 1000L;
            target.setSendTime(new Date(timestamp));

            if (item.getMsgType() == null) {
                target.setMsgType("2000");
            } else if ("1".equals(item.getMsgType())) {
                target.setMsgType("1000");
            } else if ("2".equals(item.getMsgType())) {
                target.setMsgType("2000");
            } else if ("3".equals(item.getMsgType())) {
                target.setMsgType("3000");
            } else if ("4".equals(item.getMsgType())) {
                target.setMsgType("4000");
            }

            return target;
        };
    }

    @Bean("bwoilMemberMsgMigrationWriter")
    public ItemWriter<BwoilMemberMsg> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_member_msg ( `msg_id`, `sender`, `receiver`, `send_time`, `receive_time`, `msg_title`, `msg_content`, `msg_type`, `create_time`, `update_time`) " +
                "VALUES (:msgId,:sender,:receiver,:sendTime,:receiveTime,:msgTitle,:msgContent,:msgType,:createTime,:updateTime)";
        return new StepWriterBuilder<BwoilMemberMsg>().dataSource(dataSource).sql(sqlStr).build();
    }
}
