package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.pay.pojo.origin.OriginPaymentBehavior;
import com.bwoil.c2b.migration.steps.pay.pojo.target.PaymentBehaviorEntity;
import com.bwoil.c2b.migration.utils.Channel;
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

@Configuration("bwoilPayBehaviorMigration")
public class BwoilPayBehaviorMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilPayBehaviorMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilPayBehaviorMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginPaymentBehavior, PaymentBehaviorEntity>().factory(stepBuilderFactory).name("支付服务-支付行为-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilPayBehaviorMigrationReader")
    public ItemReader<OriginPaymentBehavior> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT `record_id`, `member_id`, `pay_account`, `payment_id`, `pay_type`, `pay_app_id` channel, `source`, `money`, `is_quick_pay` quick_pay, `bank_bn`, FROM_UNIXTIME(`t_payed`) pay_time " +
                "FROM sdb_ectools_payment_behavior";
        return new StepReaderBuilder<OriginPaymentBehavior>().dataSource(dataSource).sql(sql).mappedClass(OriginPaymentBehavior.class).multithreaded(true).sortKeys("record_id").build();
    }

    @Bean("bwoilPayBehaviorMigrationWriter")
    public ItemWriter<PaymentBehaviorEntity> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_payment_behavior (`record_id`, `member_id`, `pay_account`, `payment_id`, `pay_type`, `channel`, `source`, `money`, `is_quick_pay`, `bank_bn`, `pay_time`) " +
                "VALUES (:recordId,:memberId,:payAccount,:paymentId,:payType,:channel,:source,:money,:quickPay,:bankBn,:payTime)";
        return new StepWriterBuilder<PaymentBehaviorEntity>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean("bwoilPayBehaviorMigrationProcessor")
    public ItemProcessor<OriginPaymentBehavior, PaymentBehaviorEntity> processor() {
        return item -> {
            PaymentBehaviorEntity target = new PaymentBehaviorEntity();
            BeanUtils.copyProperties(item, target);
            switch (item.getChannel()) {
                case "deposit":
                    target.setChannel(Channel.BALANCE.name());
                    break;
                case "weixinapppay":
                    target.setChannel(Channel.WX_APP.name());
                    break;
                case "webankapppay":
                    target.setChannel(Channel.WEBANK_APP.name());
                    break;
                case "yeepay":
                    target.setChannel(Channel.YEEPAY.name());
                    break;
                case "unionapppay":
                    target.setChannel(Channel.UNIONPAY_APP.name());
                    break;
                case "unionpay":
                    target.setChannel(Channel.UNIONPAY.name());
                    break;
                case "weixinpay":
                    target.setChannel(Channel.WX_WAP.name());
                    break;
                case "fuyouapppay":
                    target.setChannel(Channel.FUYOU_PC.name());
                    break;
                case "weixinwappay":
                    target.setChannel(Channel.WX_WAP.name());
                    break;
                case "colourlife":
                    target.setChannel(Channel.COLOR_PC.name());
                    break;
                default:
                    break;
            }
            if (target.getChannel().length() > 20) {
                target.setChannel(target.getChannel().substring(0, 19));
            }

            return target;
        };
    }
}
