package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.pay.pojo.origin.OriginQuickPay;
import com.bwoil.c2b.migration.steps.pay.pojo.target.QuickPayEntity;
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
import java.math.BigDecimal;

@Configuration("bwoilQuickPayMigration")
public class BwoilQuickPayMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilQuickPayMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilQuickPayMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginQuickPay, QuickPayEntity>().factory(stepBuilderFactory).name("支付服务-快捷支付配置-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilQuickPayMigrationReader")
    public ItemReader<OriginQuickPay> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select `set_id`, `bank_name`, `card_type`, `bank_bn`, `big_logo_path` big_logo_id, `small_logo_path` small_logo_id, `is_limit_each` limit_each_enable, `limit_each_amount`, `is_limit_day` limit_day_enable, `limit_day_amount`, `is_limit_month` limit_month_enable, `limit_month_amount`, `upacp_must_on` open_online_pay, `order` bank_order, `enable` is_used, FROM_UNIXTIME(`createtime`) create_time, FROM_UNIXTIME(`lastmodify`) last_update_time  " +
                "FROM sdb_ectools_settings_hnapay WHERE type='1'";

        return new StepReaderBuilder<OriginQuickPay>().dataSource(dataSource).sql(sql).mappedClass(OriginQuickPay.class).multithreaded(true).sortKeys("set_id").build();
    }


    @Bean("bwoilQuickPayMigrationWriter")
    public ItemWriter<QuickPayEntity> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_base_quickpay (`bank_id`, `bank_name`, `card_type`, `bank_bn`, `big_logo_id`, `small_logo_id`, `limit_each_enable`, `limit_each_amount`, `limit_day_enable`, `limit_day_amount`, `limit_month_enable`, `limit_month_amount`, `open_online_pay`, `baofu_type`, `bank_order`, `status`, `create_time`, `last_update_time`, `is_used`) " +
                "VALUES (:bankId,:bankName,:cardType,:bankBn,:bigLogoId,:smallLogoId,:limitEachEnable,:limitEachAmount,:limitDayEnable,:limitDayAmount,:limitMonthEnable,:limitMonthAmount,:openOnlinePay,:baofuType,:bankOrder,:status,:createTime,:lastUpdateTime,:isUsed)";
        return new StepWriterBuilder<QuickPayEntity>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean("bwoilQuickPayMigrationProcessor")
    public ItemProcessor<OriginQuickPay, QuickPayEntity> processor() {
        return item -> {
            QuickPayEntity target = new QuickPayEntity();
            BeanUtils.copyProperties(item, target);
            target.setBankId(item.getSetId());
            if ("true".equals(item.getLimitDayEnable())) {
                target.setLimitDayEnable("1");
            } else {
                target.setLimitDayEnable("0");
            }
            if ("true".equals(item.getLimitDayEnable())) {
                target.setLimitEachEnable("1");
            } else {
                target.setLimitEachEnable("0");
            }
            if ("true".equals(item.getLimitMonthEnable())) {
                target.setLimitMonthEnable("1");
            } else {
                target.setLimitMonthEnable("0");
            }
            if ("true".equals(item.getOpenOnlinePay())) {
                target.setOpenOnlinePay("1");
            } else {
                target.setOpenOnlinePay("0");
            }
            if (item.getLimitEachAmount() != null) {
                target.setLimitEachAmount(new BigDecimal(item.getLimitEachAmount()));
            }
            if (item.getLimitDayAmount() != null) {
                target.setLimitDayAmount(new BigDecimal(item.getLimitDayAmount()));
            }
            if (item.getLimitMonthAmount() != null) {
                target.setLimitMonthAmount(new BigDecimal(item.getLimitMonthAmount()));
            }
            if ("true".equals(item.getIsUsed())) {
                target.setIsUsed("1");
            } else {
                target.setIsUsed("0");
            }
            target.setStatus("0");
            return target;
        };
    }
}
