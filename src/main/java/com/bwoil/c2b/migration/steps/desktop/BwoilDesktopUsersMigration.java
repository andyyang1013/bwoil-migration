package com.bwoil.c2b.migration.steps.desktop;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.desktop.pojo.origin.OriginDesktopUsers;
import com.bwoil.c2b.migration.steps.desktop.pojo.target.BwoilDesktopUsers;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
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

//@Configuration("bwoilDesktopUsersMigration")
public class BwoilDesktopUsersMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilDesktopUsersMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilDesktopUsersMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginDesktopUsers, BwoilDesktopUsers>().factory(stepBuilderFactory).name("中台-用户-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilDesktopUsersMigrationReader")
    public ItemReader<OriginDesktopUsers> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT u.user_id AS uid,\n" +
                "a.login_name AS account,\n" +
                "'96e79218965eb72c92a549dd5a330112' AS password,\n" +
                "u.name AS name,\n" +
                "u.`super` AS is_super,\n" +
                "u.logincount AS login_count,\n" +
                "u.lastlogin AS last_login_time,\n" +
                "u.disabled AS disabled,\n" +
                "'1' AS op_no,\n" +
                "'old_system_manager' AS memo,\n" +
                "'' AS avatar,\n" +
                "a.disabled AS delete_status\n" +
                " FROM sdb_desktop_users u LEFT JOIN sdb_pam_account a \n" +
                "ON u.user_id=a.account_id;";
        return new StepReaderBuilder<OriginDesktopUsers>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginDesktopUsers.class).build();
    }

    @Bean("bwoilDesktopUsersMigrationProcessor")
    public ItemProcessor<OriginDesktopUsers, BwoilDesktopUsers> processor() {
        return item -> {
            BwoilDesktopUsers target = new BwoilDesktopUsers();
            BeanUtils.copyProperties(item, target);
            if (item.getDeleteStatus() == null) {
                target.setDeleteStatus(0L);
            }
            target.setCreateTime(TimeStampUtil.now());
            target.setUpdateTime(TimeStampUtil.now());
            return target;
        };
    }

    @Bean("bwoilDesktopUsersMigrationWriter")
    public ItemWriter<BwoilDesktopUsers> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_desktop_users (uid, account, password,name,is_super,login_count,last_login_time,disabled,op_no,memo,avatar,delete_status,create_time,update_time) " +
                "VALUES (:uid, :account, :password,:name,:isSuper,:loginCount,:lastLoginTime,:disabled,:opNo,:memo,:avatar,:deleteStatus,:createTime,:updateTime)";
        return new StepWriterBuilder<BwoilDesktopUsers>().dataSource(dataSource).sql(sqlStr).build();
    }
}
