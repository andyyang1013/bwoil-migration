package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.ScanRefuelEntity;
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

@Configuration("bwoilOrderScanRefuelMigration")
public class BwoilOrderScanRefuelMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderScanRefuelMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderScanRefuelMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<ScanRefuelEntity, ScanRefuelEntity>().factory(stepBuilderFactory).name("订单服务-扫码加油表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderScanRefuelMigrationReader")
    public ItemReader<ScanRefuelEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("fuel_order_id,member_id,null as mobile,null as member_bn")
                .append(",order_id,null as pay_bn,pay_money as fuel_amount,paid_subscribers as real_pay_amount")
                .append(",0 as protocol_amount,0 as profit_amount,0 as settle_fee_amount")
                .append(",0 as pay_card_amount,0 as pay_lc_amount,0 as profit_acitive_amount")
                .append(",0 as settle_amount,null as pay_status,null as expire_time,null as valid_time")
                .append(",station_id as refuel_station_id,null as refuel_station,null as oil_name,gun_num as oil_gan_no")
                .append(",agreement_pay_method as settle_way,weixin_discount as wechat_rate,paid_discount as channel_rate,staff_id as waiter,oil_price")
                .append(",oil_total as refule_liter,sms_code as vcode,so.type as refuel_type,null as is_sucess,null as pay_way,null as ad_source")
                .append(",accounting_status as order_status,0 as status,")
                .append("DATE_FORMAT(FROM_UNIXTIME(created),'%Y-%m-%d %H:%i:%s')  as create_time ")
                .append(" from sdb_station_fuel_order so");

        return new StepReaderBuilder<ScanRefuelEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(ScanRefuelEntity.class).multithreaded(true).sortKeys("fuel_order_id").build();
    }

    @Bean("bwoilOrderScanRefuelMigrationProcessor")
    public ItemProcessor<ScanRefuelEntity, ScanRefuelEntity> processor() {
        return item -> {
            ScanRefuelEntity target = new ScanRefuelEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderScanRefuelMigrationWriter")
    public ItemWriter<ScanRefuelEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_scan_refuel (id,member_id,mobile,member_bn,order_id,pay_bn,fuel_amount,real_pay_amount,protocol_amount,profit_amount,settle_fee_amount,pay_card_amount,pay_lc_amount,profit_acitive_amount,settle_amount,pay_status,expire_time,valid_time,refuel_station_id,refuel_station,oil_name,oil_gan_no,settle_way,wechat_rate,channel_rate,waiter,oil_price,refule_liter,vcode,refuel_type,is_sucess,pay_way,ad_source,order_status,status,create_time,last_update_time)"
                + " VALUES (:fuelOrderId, :memberId, :mobile, :memberBn, :orderId, :payBn, :fuelAmount, :realPayAmount, :protocolAmount, :profitAmount, :settleFeeAmount, :payCardAmount, :payLcAmount, :profitAcitiveAmount, :settleAmount, :payStatus, :expireTime, :validTime, :refuelStationId, :refuelStation, :oilName, :oilGanNo, :settleWay, :wechatRate, :channelRate, :waiter, :oilPrice, :refuleLiter, :vcode, :refuelType, :isSucess, :payWay, :adSource, :orderStatus, :status, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<ScanRefuelEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(ScanRefuelEntity target) {
        if ("1970-01-01 08:00:00".equals(target.getExpireTime())) {
            target.setExpireTime("0000-00-00 00:00:00");
        }

        if ("1970-01-01 08:00:00".equals(target.getValidTime())) {
            target.setValidTime("0000-00-00 00:00:00");
        }

        if ("1970-01-01 08:00:00".equals(target.getCreateTime())) {
            target.setCreateTime("0000-00-00 00:00:00");
        }

        if ("1970-01-01 08:00:00".equals(target.getLastUpdateTime())) {
            target.setLastUpdateTime("0000-00-00 00:00:00");
        }

        if (target.getMobile() == null) {
            target.setMobile("0");
        }
        if (target.getOilPrice() == null) {
            target.setOilPrice(BigDecimal.ZERO);
        }
        if (target.getRefuleLiter() == null) {
            target.setRefuleLiter(BigDecimal.ZERO);
        }
    }
}
