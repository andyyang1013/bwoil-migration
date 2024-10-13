package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationRefuelRecord;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationRefuelRecord;
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

@Configuration("bwoilOperationGasstationRefuelRecordMigration")
public class BwoilOperationGasstationRefuelRecordMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationRefuelRecordMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationRefuelRecordMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationRefuelRecord, BwoilOperationGasstationRefuelRecord>().factory(stepBuilderFactory).name("运营服务-加油站扫码RefuelRecord加油记录表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationRefuelRecordMigrationReader")
    public ItemReader<OriginOperationGasstationRefuelRecord> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT ord.*,sta.station_name station_name, mem.shop_member_bn shop_bn, staf.true_name cashier_name FROM sdb_ycy_refuel_orders ord LEFT JOIN sdb_b2c_gas_station sta ON ord.station_id=sta.station_id LEFT JOIN sdb_b2c_members mem ON ord.member_id=mem.member_id LEFT JOIN sdb_station_work_shift s on ord.shift_id = s.shift_id LEFT JOIN sdb_station_staff staf ON s.staff_id = staf.staff_id";
        return new StepReaderBuilder<OriginOperationGasstationRefuelRecord>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationRefuelRecord.class).multithreaded(true).sortKeys("ord.order_id").build();
    }

    @Bean("bwoilOperationGasstationRefuelRecordMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationRefuelRecord, BwoilOperationGasstationRefuelRecord> processor() {
        return item -> {
            BwoilOperationGasstationRefuelRecord target = new BwoilOperationGasstationRefuelRecord();
            BeanUtils.copyProperties(item, target);
            target.setRecordId(String.valueOf(item.getOrderId()));
            if (item.getCreatetime() == null || item.getCreatetime() == 0) {
                target.setCreatetime(null);
            } else {
                target.setCreatetime(new Date(Long.parseLong(item.getCreatetime().toString()) * 1000L));
            }
            if (item.getExpired() == null || item.getExpired() == 0) {
                target.setExpired(null);
            } else {
                target.setExpired(new Date(Long.parseLong(item.getExpired().toString()) * 1000L));
            }
            if (item.getConfirm() == null || item.getConfirm() == 0) {
                target.setConfirm(null);
            } else {
                target.setConfirm(new Date(Long.parseLong(item.getConfirm().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationRefuelRecordMigrationWriter")
    public ItemWriter<BwoilOperationGasstationRefuelRecord> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_refuel_record (record_id, member_id, shop_bn, pay_bn, total_amount, paid_subscribers, advence_amount, refuel_amount, discount_amount, cpns_money, agreements_money, activity_money, oil_price, oil_total, status, station_id, createtime, expired, mobilephone, confirm, confirm_code, operator_id, operator_name, gun, oil_name, oilers_id, oilers_name, shift_id, ip, tax_company, source, payment_id, paymethod, effective, paid_discount, weixin_discount, clearing_type, clearing_status, clearing_error_msg, discount_text, bank_pay_num, allow_cprint, is_cprint, fee_pay_method, agreement_pay_method, fees_amount, clearing_amount, refuel_type, station_name, cashier_name, pay_status) VALUES (:recordId, :memberId, :shopBn, :payBn, :totalAmount, :paidSubscribers, :advenceAmount, :refuelAmount, :discountAmount, :cpnsMoney, :agreementsMoney, :activityMoney, :oilPrice, :oilTotal, :status, :stationId, :createtime, :expired, :mobilephone, :confirm, :confirmCode, :operatorId, :operatorName, :gun, :oilName, :oilersId, :oilersName, :shiftId, :ip, :taxCompany, :source, :paymentId, :paymethod, :effective, :paidDiscount, :weixinDiscount, :clearingType, :clearingStatus, :clearingErrorMsg, :discountText, :bankPayNum, :allowCprint, :isCprint, :feePayMethod, :agreementPayMethod, :feesAmount, :clearingAmount, :refuelType, :stationName, :cashierName, :payStatus)";
        return new StepWriterBuilder<BwoilOperationGasstationRefuelRecord>().dataSource(dataSource).sql(sqlStr).build();
    }
}
