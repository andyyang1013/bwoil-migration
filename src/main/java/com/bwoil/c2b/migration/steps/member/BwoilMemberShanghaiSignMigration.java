package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberSign;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMemberSign;
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
 * 上海银行签约数据
 *
 * @author yangda
 */
@Configuration("bwoilMemberShanghaiSignMigration")
public class BwoilMemberShanghaiSignMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberShanghaiSignMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberShanghaiSignMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberSign, BwoilMemberSign>().factory(stepBuilderFactory).name("会员服务-上海银行签约数据-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberShanghaiSignMigrationReader")
    public ItemReader<OriginMemberSign> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT  member_id, NAME, card_number, card_no, card_code, card_type, acctLvl, auth_time, lastmodify " +
                "FROM sdb_b2c_member_exten";
        return new StepReaderBuilder<OriginMemberSign>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberSign.class).multithreaded(true).sortKeys("member_id").build();
    }

    @Bean("bwoilMemberShanghaiSignMigrationWriter")
    public ItemWriter<BwoilMemberSign> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_member_shanghai_sign(member_id, name, identify_id, card_no, card_type, sign_code, acct_lv,  sign_time, last_update_time) " +
                "VALUES  ( :memberId, :name, :identifyId,  :cardNo,  :cardType, :signCode,  :acctLv,  :signTime,   :lastUpdateTime) ;";
        return new StepWriterBuilder<BwoilMemberSign>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean("bwoilMemberShanghaiSignMigrationProcessor")
    public ItemProcessor<OriginMemberSign, BwoilMemberSign> processor() {
        return item -> {
            BwoilMemberSign target = new BwoilMemberSign();
            BeanUtils.copyProperties(item, target);
            target.setIdentifyId(item.getCardNumber());
            target.setSignCode(item.getCardCode());
            target.setAcctLv(item.getAcctLvl());
            target.setSignTime(new Date(item.getAuthTime() * 1000L));
            target.setLastUpdateTime(new Date(item.getLastmodify() * 1000L));
            return target;
        };
    }

}
