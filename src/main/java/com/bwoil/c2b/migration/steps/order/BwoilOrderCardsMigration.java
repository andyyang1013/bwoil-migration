package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.OilCardEntity;
import com.bwoil.c2b.migration.utils.PordAttr;
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
import java.math.BigDecimal;

@Configuration("bwoilOrderCardsMigration")
public class BwoilOrderCardsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCardsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCardsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OilCardEntity, OilCardEntity>().factory(stepBuilderFactory).name("订单服务-卡表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCardsMigrationReader")
    public ItemReader<OilCardEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("sc.card_id,sp.order_bn as order_id, sc.card_bn,sc.card_name,sc.member_id,")
                .append("sc.card_type,sc.product_attr as prod_attr,sc.region_name,sc.region_id,sc.oil_name,sc.oil_id,sc.buy_price as oil_price,")
                .append("sc.card_status,sc.trade_status as trans_status,sc.remain_amount as total_amount,0 as freeze_amount,sc.cpns_money,")
                .append("0 as can_sale_amount,total_cash_amount as sale_amount,payout as buy_amount,sc.cost as pay_amout,")
                .append("goil_money as oil_deduction,sc.reduce_money,sc.give_percent as profit_discount,sc.limit_distribute_unit as term_unit,")
                .append("((payout-discount)/payout*10) as rule_discount,sc.annualized as income_rate,sc.floatingPercent as float_rate,sc.managementPercent as sale_rate,")
                .append("1 as card_cnt,sc.limit_distribute_time as peroid,cash_deadline as term,null as unlock_date,expired as force_sale_date ,0 as status,remain_litre ,total_cash_litre,")
                .append("create_time as create_time,lastmodify as last_update_time ")
                .append(" from sdb_ycy_cards sc left join sdb_ycy_card_product sp on sc.card_bn=sp.card_bn and sc.member_id=sp.member_id");

        return new StepReaderBuilder<OilCardEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(OilCardEntity.class).multithreaded(true).sortKeys("sc.card_id").build();
    }

    @Bean("bwoilOrderCardsMigrationProcessor")
    public ItemProcessor<OilCardEntity, OilCardEntity> processor() {
        return item -> {
            OilCardEntity target = new OilCardEntity();
            BeanUtils.copyProperties(item, target);
            // TODO: 可能是坏卡？
            if (item.getBuyAmount() == null){
                target.setBuyAmount(BigDecimal.ZERO);
            }
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderCardsMigrationWriter")
    public ItemWriter<OilCardEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_cards (card_id,order_id,card_bn,card_name,member_id,card_type,prod_attr,region_name,region_id,oil_name,oil_id,oil_price,card_status,trans_status,total_amount,freeze_amount,can_sale_amount,recently_sale_date,sale_amount,buy_amount,pay_amout,cpns_money,cpns_liter,oil_deduction,reduce_money,profit_discount,rule_discount,income_rate,float_rate,sale_rate,card_cnt,peroid,term,unlock_date,force_sale_date,status,term_unit,create_time,last_update_time) "
                + "VALUES (:cardId, :orderId, :cardBn, :cardName, :memberId, :cardType, :prodAttr, :regionName, :regionId, :oilName, :oilId, :oilPrice, :cardStatus, :transStatus, :totalAmount, :freezeAmount, :canSaleAmount, :recentlySaleDate, :saleAmount, :buyAmount, :payAmout, :cpnsMoney, :cpnsLiter, :oilDeduction, :reduceMoney, :profitDiscount, :ruleDiscount, :incomeRate, :floatRate, :saleRate, :cardCnt, :peroid, :term, :unlockDate, :forceSaleDate, :status, :termUnit, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<OilCardEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(OilCardEntity data) {
        data.setTransStatus(getTransStatus(data));
        data.setCardStatus(getCardStatus(data.getCardStatus()));
        data.setTermUnit(getTermUnit(data.getTermUnit()));
        if (data.getCpnsMoney() == null) {
            data.setCpnsMoney(BigDecimal.ZERO);
        }
        if (data.getCardCnt() == null) {
            data.setCardCnt(1);
        }
        if (data.getPeroid() == null) {
            data.setPeroid(1);
        }
        if (data.getCpnsLiter() == null) {
            data.setCpnsLiter(BigDecimal.ZERO);
        }
        if (data.getOrderId() == null) {
            data.setOrderId("0");
        }
        if (data.getPayAmout() == null) {
            data.setPayAmout(BigDecimal.ZERO);
        }
        data.setCreateTime(TimeStampUtil.convertDate(data.getCreateTime()));
        data.setLastUpdateTime(TimeStampUtil.convertDate(data.getLastUpdateTime()));
        data.setForceSaleDate(TimeStampUtil.convertDate(data.getForceSaleDate()).substring(0,10).replace("-", ""));
        String cardType = data.getCardType();

        data.setProdAttr(PordAttr.convertProdAttr(cardType));
        if("litre".equals(data.getProdAttr())) {
            data.setTotalAmount(data.getRemainLitre());
            data.setSaleAmount(data.getTotalCashLitre());	
        }
    }

    private String getTransStatus(OilCardEntity data) {
        String origTransStatus = data.getTransStatus();
        String cardStatus = data.getCardStatus();
        String cardType = data.getCardType();
        if("invalid".equals(cardStatus)
            && ("amount_auto_2019v1".equals(cardType)
            || "amount_auto_2019v2".equals(cardType))){
            return "3";
        }
        if("invalid".equals(cardStatus)){
            data.setStatus("-1");
        }
        if ("buybacked".equals(origTransStatus)) {
            return "4";
        }
        if ("cashing".equals(origTransStatus)) {
            return "1";
        }
        if ("ready".equals(origTransStatus)) {
            return "0";
        }
        if ("cashed".equals(origTransStatus)) {
            return "2";
        }
        return "0";
    }

    private String getCardStatus(String origStatus) {
        if ("freeze".equals(origStatus)) {
            return "0";
        }

        return "1";
    }

    private String getTermUnit(String termUnit) {
        if ("month".equals(termUnit)) {
            return "M";
        }

        return "D";
    }
    
}
