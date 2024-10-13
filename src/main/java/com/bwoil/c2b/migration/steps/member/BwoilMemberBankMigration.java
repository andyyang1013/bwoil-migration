package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberBank;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMemberBank;
import com.bwoil.c2b.migration.utils.AES;
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

@Configuration("bwoilMemberBankMigration")
public class BwoilMemberBankMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberBankMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberBankMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberBank, BwoilMemberBank>().factory(stepBuilderFactory).name("会员服务-银行账号-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberBankMigrationReader")
    public ItemReader<OriginMemberBank> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT a.cash_account_id bank_id,a.member_id,a.bank_bn,a.bank_name,a.bank_account_encrypt bank_no,a.is_default def_flag,a.create_time,a.disabled `status`," +
                "case when (b.member_id) is not null then 'baofu' else 'guanghui' end as channel_code ," +
                "case when (b.member_id) is not null then 1 else 0 end as quick_pay, " +
                "b.biz_protocol_no bind_id,b.pay_protocol_no protocol_no, " +
                " CASE WHEN   (b.biz_protocol_no) IS NOT NULL AND (b.pay_protocol_no) IS NOT NULL THEN 2 " +
                " WHEN (b.biz_protocol_no) IS NOT NULL AND (b.pay_protocol_no) IS NULL THEN 1 " +
                " WHEN (b.biz_protocol_no) IS NULL AND (b.pay_protocol_no) IS NOT  NULL THEN 2 " +
                " ELSE 2 END AS baofu_type " +
                "FROM sdb_b2c_cash_account a left join sdb_b2c_member_hnapay b on a.member_id=b.member_id AND a.bank_bn=b.bank_no and  b.verified=true order by a.member_id asc ";
//              +  " where a.member_id='3010545' order by a.member_id asc ";

        return new StepReaderBuilder<OriginMemberBank>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberBank.class).multithreaded(true).sortKeys("a.member_id").build();
    }

    @Bean("bwoilMemberBankMigrationWriter")
    public ItemWriter<BwoilMemberBank> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_member_bank (bank_id, member_id, bank_name, bank_region, bank_sub_name, bank_bn, bank_no, bind_id, protocol_no, baofu_type, channel_code, def_flag, quick_pay, create_time, last_update_time, status) " +
                "VALUES (:bankId,:memberId,:bankName,:bankRegion,:bankSubName,:bankBn,:bankNo,:bindId,:protocolNo,:baofuType,:channelCode,:defFlag,:quickPay,:createTime,:lastUpdateTime,:status)";
        return new StepWriterBuilder<BwoilMemberBank>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean("bwoilMemberBankMigrationProcessor")
    public ItemProcessor<OriginMemberBank, BwoilMemberBank> processor() {
        return item -> {
            BwoilMemberBank target = new BwoilMemberBank();
            BeanUtils.copyProperties(item, target);
            target.setBankNo(AES.encrypt(AES.decrypt(item.getBankNo())));

            target.setDefFlag("true".equals(item.getDefFlag()) ? "1" : "0");
            target.setStatus("false".equals(item.getStatus()) ? "0" : "-1");

            return target;
        };
    }
}
