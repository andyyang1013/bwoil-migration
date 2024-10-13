package com.bwoil.c2b.migration.steps.operation.redcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.origin.OriginOperationRedcouponActivityOrder;
import com.bwoil.c2b.migration.steps.operation.redcoupon.pojo.target.BwoilOperationRedcouponActivityOrder;
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
import java.util.Date;

@Configuration("bwoilOperationRedcouponActivityOrderMigration")
public class BwoilOperationRedcouponActivityOrderMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationRedcouponActivityOrderMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationRedcouponActivityOrderMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationRedcouponActivityOrder, BwoilOperationRedcouponActivityOrder>().factory(stepBuilderFactory).name("运营服务-满减订单-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationRedcouponActivityOrderMigrationReader")
    public ItemReader<OriginOperationRedcouponActivityOrder> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT rco.id, rco.cpn_bn AS cpn_bn, rc.cpn_name AS cpn_name, rco.order_id AS order_id, pm.member_id AS memberId, pm.login_account AS login_account, o. NAME AS goodName, o.createtime AS orderCreateTime, o.total_amount AS total_amount, o.reduce_money AS reduce_money, o.goil_money AS goil_money, o.cpns_money AS cpns_money, o.discount AS discountMoney FROM sdb_b2c_redcoupon_activity_order AS rco JOIN sdb_b2c_redcoupon_activity AS rc ON rco.cpn_bn = rc.cpn_bn JOIN sdb_b2c_orders AS o ON rco.order_id = o.order_id JOIN sdb_pam_members AS pm ON pm.member_id = o.member_id WHERE o.pay_status != '0' AND login_type = 'mobile'";
        return new StepReaderBuilder<OriginOperationRedcouponActivityOrder>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationRedcouponActivityOrder.class).multithreaded(true).sortKeys("rco.id").build();
    }

    @Bean("bwoilOperationRedcouponActivityOrderMigrationProcessor")
    public ItemProcessor<OriginOperationRedcouponActivityOrder, BwoilOperationRedcouponActivityOrder> processor() {
        return item -> {
            BwoilOperationRedcouponActivityOrder target = new BwoilOperationRedcouponActivityOrder();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getId());
            target.setMobile(item.getLoginAccount());
            target.setOrderCreateTime(new Date(item.getOrderCreateTime() * 1000));
            target.setCreateTime(new Date(item.getOrderCreateTime() * 1000));
            BigDecimal temp = target.getTotalAmount().subtract(target.getCpnsMoney()).subtract(target.getDiscountMoney()).subtract(target.getGoilMoney()).subtract(target.getReduceMoney());
            target.setRealPayAmount(temp);
            target.setStatus("0");
            target.setOrderStatus(1);
            target.setLastmodify(new Date());
            return target;
        };
    }

    @Bean("bwoilOperationRedcouponActivityOrderMigrationWriter")
    public ItemWriter<BwoilOperationRedcouponActivityOrder> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_redcoupon_activity_order " +
                "(id, cpn_bn, cpn_name, order_id, mobile, order_create_time, good_name, total_amount, real_pay_amount, cpns_money, goil_money, reduce_money, discount_money, status, create_time, lastmodify,member_id,order_status)" +
                " VALUES (:id, :cpnBn, :cpnName, :orderId, :mobile, :orderCreateTime, :goodName, :totalAmount, :realPayAmount,:cpnsMoney, :goilMoney, :reduceMoney, :discountMoney, :status, :createTime, :lastmodify,:memberId,:orderStatus)";
        return new StepWriterBuilder<BwoilOperationRedcouponActivityOrder>().dataSource(dataSource).sql(sqlStr).build();
    }
}
