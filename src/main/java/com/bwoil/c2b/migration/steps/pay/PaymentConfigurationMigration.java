package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.pay.pojo.origin.OriginPayment;
import com.bwoil.c2b.migration.steps.pay.pojo.target.PaymentEntity;
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
import java.math.BigDecimal;

@Configuration
public class PaymentConfigurationMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PaymentConfigurationMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean(name = "bwoilPaymentMigrationStep")
    public Step paymentMigrationStep(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginPayment, PaymentEntity>().factory(stepBuilderFactory).name("支付服务-支付单记录-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilPaymentMigrationReader")
    public ItemReader<OriginPayment> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT t.payment_id,t.member_id,t.account payedAccount,t.trade_no third_trade_no,t.`status` trade_status,t.pay_account account,t.bank pay_channel_name," +
                "t.pay_app_id pay_channel,t.paycost pay_fee,t.disabled `status` ,t.currency,t.ip,t.return_url,FROM_UNIXTIME(t.t_payed) pay_time, FROM_UNIXTIME(t.t_begin) create_time, t.money amount,t.thirdparty_account, " +
                "o.`order_id` order_no," +
                "case when(o.is_mixed) is null then false else o.is_mixed end as combine_order " +
                "FROM sdb_ectools_payments t  LEFT JOIN (SELECT trade_no,order_id,is_mixed FROM sdb_b2c_orders WHERE trade_no IS NOT NULL AND trade_no!='' ) o ON t.payment_id = o.trade_no ";
        return new StepReaderBuilder<OriginPayment>().dataSource(dataSource).sql(sql).mappedClass(OriginPayment.class).multithreaded(true).sortKeys("t.payment_id").build();
    }

    @Bean(name = "bwoilPaymentMigrationProcessor")
    public ItemProcessor<OriginPayment, PaymentEntity> processor() {
        return item -> {
            PaymentEntity target = new PaymentEntity();
            BeanUtils.copyProperties(item, target);
            Double v = item.getAmount() * 100;
            target.setAmount(v.intValue());

            if (item.getPayFee() != null) {
                target.setPayFee(item.getPayFee().multiply(new BigDecimal(100)).intValue());
            }

            target.setStatus("true".equals(item.getStatus()) ? "-1" : "0");

            switch (item.getPayChannel()) {
                case "deposit":
                    target.setPayChannel(Channel.BALANCE.name());
                    break;
                case "bwoilpay":
                    target.setPayChannel(Channel.GUANGHUI.name());
                    break;
                case "bwoilwappay":
                    target.setPayChannel(Channel.GUANGHUI.name());
                    break;

                case "alipay":
                    target.setPayChannel(Channel.ALI.name());
                    break;
                case "aliapppay":
                    target.setPayChannel(Channel.ALI.name());
                    break;
                case "offline":
                    target.setPayChannel(Channel.OFFLINE.name());
                    break;
                case "online":
                    target.setPayChannel(Channel.ONLINE.name());
                    break;
                case "online-payment":
                    target.setPayChannel(Channel.ONLINE.name());
                    break;
                case "hnapay":
                    target.setPayChannel(Channel.BAOFU.name());
                    break;
                case "cbcpay":
                    target.setPayChannel(Channel.CCB_PC.name());
                    break;
                case "cbcwappay":
                    target.setPayChannel(Channel.CCB_PC.name());
                    break;
                case "cbcapppay":
                    target.setPayChannel(Channel.CCB_PC.name());
                    break;
                case "ibankinginner":
                    target.setPayChannel(Channel.JD.name());
                    break;
                case "jdpay":
                    target.setPayChannel(Channel.JD.name());
                    break;
                case "jdshoppay":
                    target.setPayChannel(Channel.JD.name());
                    break;
                case "weixinapppay":
                    target.setPayChannel(Channel.WX_APP.name());
                    break;
                case "wxdirectwappay":
                    target.setPayChannel(Channel.WX_WAP.name());
                    break;
                case "wxdirectapppay":
                    target.setPayChannel(Channel.WX_APP.name());
                    break;
                case "wxgaswappay":
                    target.setPayChannel(Channel.WX_APP.name());
                    break;
                case "webankapppay":
                    target.setPayChannel(Channel.WEBANK_APP.name());
                    break;
                case "yeepay":
                    target.setPayChannel(Channel.YEEPAY.name());
                    break;
                case "yeeapppay":
                    target.setPayChannel(Channel.YEEPAY.name());
                    break;
                case "yeempay":
                    target.setPayChannel(Channel.YEEPAY.name());
                    break;
                case "unionapppay":
                    target.setPayChannel(Channel.UNIONPAY_APP.name());
                    break;
                case "uniondebitapppay":
                    target.setPayChannel(Channel.UNIONPAY.name());
                    break;
                case "uniondebitappp":
                    target.setPayChannel(Channel.UNIONPAY.name());
                    break;
                case "upacp":
                    target.setPayChannel(Channel.UNIONPAY.name());
                    break;
                case "upacpdebit":
                    target.setPayChannel(Channel.UNIONPAY.name());
                    break;
                case "unionpay":
                    target.setPayChannel(Channel.UNIONPAY.name());
                    break;
                case "weixinpay":
                    target.setPayChannel(Channel.WX_WAP.name());
                    break;
                case "fuyouapppay":
                    target.setPayChannel(Channel.FUYOU_PC.name());
                    break;
                case "fuyoupay":
                    target.setPayChannel(Channel.FUYOU_PC.name());
                    break;
                case "weixinwappay":
                    target.setPayChannel(Channel.WX_WAP.name());
                    break;
                case "colourlife":
                    target.setPayChannel(Channel.COLOR_PC.name());
                    break;
                case "wxpayface":
                    target.setPayChannel(Channel.WX_FACE.name());
                    break;
                case "bospay":
                    target.setPayChannel(Channel.SHANGHAI_APP.name());
                    break;
                case "czywappay":
                    target.setPayChannel(Channel.COLOR_PC.name());
                    break;
                default:
                    break;
            }
            if (target.getPayChannel().length() > 20) {
                target.setPayChannel(target.getPayChannel().substring(0, 19));
            }
            switch (item.getTradeStatus()) {
                case "succ":
                    target.setTradeStatus("SUCCESS");
                    break;
                case "failed":
                    target.setTradeStatus("FAIL");
                    break;
                case "cancel":
                    target.setTradeStatus("CANCEL");
                    break;
                case "invalid":
                    target.setTradeStatus("ERROR");
                    break;
                case "progress":
                    target.setTradeStatus("PROCESSING");
                    break;
                case "timeout":
                    target.setTradeStatus("TIMEOUT");
                    break;
                case "ready":
                    target.setTradeStatus("WAIT");
                    break;
            }

            target.setPayType("order");
            target.setCombineOrder("true".equals(item.getCombineOrder()) ? true : false);
            target.setChargeOrder(true);
            target.setNotifyOrder(true);
            target.setSendERP(true);
            return target;
        };
    }

    @Bean(name = "bwoilPaymentMigrationWriter")
    public ItemWriter<PaymentEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_payments (order_no, trade_status, trade_error_msg, amount, member_id, pay_type, pay_channel, pay_channel_name, subject, payed_account, payed_bank, account, currency, pay_fee, ip, trade_no, return_url, thirdparty_account, pay_time, create_time, last_update_time,`status`) " +
                "VALUES (:orderNo,:tradeStatus,:tradeErrorMsg,:amount,:memberId,:payType,:payChannel,:payChannelName,:subject,:payedAccount,:payedBank,:account,:currency,:payFee,:ip,:tradeNo,:returnUrl,:thirdpartyAccount,:payTime,:createTime,:lastUpdateTime,:status)";
        return new StepWriterBuilder<PaymentEntity>().dataSource(dataSource).sql(sqlStr).build();
    }
}
