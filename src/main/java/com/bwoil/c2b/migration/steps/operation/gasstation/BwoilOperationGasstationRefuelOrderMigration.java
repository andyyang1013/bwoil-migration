package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationRefuelOrder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationRefuelOrder;
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

@Configuration("bwoilOperationGasstationRefuelOrderMigration")
public class BwoilOperationGasstationRefuelOrderMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationRefuelOrderMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationRefuelOrderMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationRefuelOrder, BwoilOperationGasstationRefuelOrder>().factory(stepBuilderFactory).name("运营服务-加油站扫码RefuelOrder加油记录表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationRefuelOrderMigrationReader")
    public ItemReader<OriginOperationGasstationRefuelOrder> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from  sdb_station_fuel_order";
        return new StepReaderBuilder<OriginOperationGasstationRefuelOrder>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationRefuelOrder.class).multithreaded(true).sortKeys("fuel_order_id").build();
    }

    @Bean("bwoilOperationGasstationRefuelOrderMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationRefuelOrder, BwoilOperationGasstationRefuelOrder> processor() {
        return item -> {
            BwoilOperationGasstationRefuelOrder target = new BwoilOperationGasstationRefuelOrder();
            BeanUtils.copyProperties(item, target);
            if (item.getCreated() == null || item.getCreated() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreated().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationRefuelOrderMigrationWriter")
    public ItemWriter<BwoilOperationGasstationRefuelOrder> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_refuel_order " +
                "(fuel_order_id, order_id, shift_id, station_id, staff_id, yougong_id, payment_id, member_id, gun_num, pay_money, invoice_number, agreements_money, oil_number, activity_money, oil_total, oil_price, sms_code, create_time, settlement, print_status, bonus, source, type, paid_subscribers, discount_amount, cpns_money, paid_discount, weixin_discount, clearing_type, accounting_status, discount_text, agreement_pay_method, fee_pay_method) VALUES " +
                "(:fuelOrderId, :orderId, :shiftId, :stationId, :staffId, :yougongId, :paymentId, :memberId, :gunNum, :payMoney, :invoiceNumber, :agreementsMoney, :oilNumber, :activityMoney, :oilTotal, :oilPrice, :smsCode, :createTime, :settlement, :printStatus, :bonus, :source, :type, :paidSubscribers, :discountAmount, :cpnsMoney, :paidDiscount, :weixinDiscount, :clearingType, :accountingStatus, :discountText, :agreementPayMethod, :feePayMethod)";
        return new StepWriterBuilder<BwoilOperationGasstationRefuelOrder>().dataSource(dataSource).sql(sqlStr).build();
    }
}
