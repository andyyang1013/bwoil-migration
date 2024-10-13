package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RedeemEntity;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

import org.apache.commons.lang3.StringUtils;
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

@Configuration("bwoilOrderRedeemMigration")
public class BwoilOrderRedeemMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRedeemMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRedeemMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RedeemEntity, RedeemEntity>().factory(stepBuilderFactory).name("订单服务-兑付表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRedeemMigrationReader")
    public ItemReader<RedeemEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("trade_id as id,trade_bn,card_bn,product_name,0 as card_type,")
                .append("member_id,0 as member_name,0 as mobile,null as buy_time,cost as buy_money,")
                .append("0 as buy_liter,price as buy_price,'customer' as trade_type,trade_nums as sale_amount,income as sale_money,")
                .append("trade_nums as sale_liter,trade_price as oil_price,cpns_money,profit as profit_oil,fixed_profit as discount_money,")
                .append("0 as reduce_money,0 as profit_money,0 as profit_liter,fixed_profit as fixed_interet,")
                .append("float_profit as float_interet,commision as fee,0 as status,")
                .append("trade_start_time as create_time,")
                .append("lastmodify  as last_update_time")
                .append(" from sdb_ycy_card_trade where trade_type in ('cash','cash_code','cash_recharge','cash_invoice')");

        return new StepReaderBuilder<RedeemEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RedeemEntity.class).multithreaded(true).sortKeys("trade_bn").build();
    }

    @Bean("bwoilOrderRedeemMigrationProcessor")
    public ItemProcessor<RedeemEntity, RedeemEntity> processor() {
        return item -> {
            RedeemEntity target = new RedeemEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderRedeemMigrationWriter")
    public ItemWriter<RedeemEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_redeem (id,trade_bn,card_bn,product_name,card_type,member_id,member_name,mobile,buy_time,buy_money,buy_liter,buy_price,trade_type,sale_amount,sale_money,sale_liter,oil_price,cpns_money,profit_oil,discount_money,reduce_money,profit_money,profit_liter,fixed_interet,float_interet,fee,status,create_time,last_update_time) "
                + "VALUES (:id, :tradeBn, :cardBn, :productName, :cardType, :memberId, :memberName, :mobile, :buyTime, :buyMoney, :buyLiter, :buyPrice, :tradeType, :saleAmount, :saleMoney, :saleLiter, :oilPrice, :cpnsMoney, :profitOil, :discountMoney, :reduceMoney, :profitMoney, :profitLiter, :fixedInteret, :floatInteret, :fee, :status, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RedeemEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RedeemEntity target) {

        if (StringUtils.isEmpty(target.getMemberName())) {
            target.setMemberName("0");
        }
        if (StringUtils.isEmpty(target.getCardType())) {
            target.setCardType("0");
        }
        if (StringUtils.isEmpty(target.getMobile())) {
            target.setMobile("0");
        }
        if (target.getBuyMoney() == null) {
            target.setBuyMoney(BigDecimal.ZERO);
        }
        if (target.getBuyLiter() == null) {
            target.setBuyLiter(BigDecimal.ZERO);
        }
        if (target.getBuyPrice() == null) {
            target.setBuyPrice(BigDecimal.ZERO);
        }
        if (target.getSaleMoney() == null) {
            target.setSaleAmount(BigDecimal.ZERO);
        }
        if (target.getSaleLiter() == null) {
            target.setSaleLiter(BigDecimal.ZERO);
        }
        if (target.getSaleAmount() == null) {
            target.setSaleAmount(BigDecimal.ZERO);
        }
        target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
        target.setLastUpdateTime(TimeStampUtil.convertDate(target.getLastUpdateTime()));
        target.setBuyTime(TimeStampUtil.convertDate(target.getBuyTime()));
    }
}
