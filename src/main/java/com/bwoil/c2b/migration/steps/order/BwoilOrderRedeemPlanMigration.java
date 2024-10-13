package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.RedeemPlanEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Configuration("bwoilOrderRedeemPlanMigration")
public class BwoilOrderRedeemPlanMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderRedeemPlanMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderRedeemPlanMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<RedeemPlanEntity, RedeemPlanEntity>().factory(stepBuilderFactory).name("订单服务-兑付计划表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderRedeemPlanMigrationReader")
    public ItemReader<RedeemPlanEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("pl.cash_time_id, pl.card_bn,pl.member_id,")
                .append("FROM_UNIXTIME(pl.cash_start, '%Y%m%d') as sale_date,")
                .append("pl.period as term, pl.cash_limit as cash_amount, pl.cash_remain,pl.cash_total,")
                .append("DATE_FORMAT(FROM_UNIXTIME(cd.create_time),'%Y-%m-%d %H:%i:%s') as create_time ")
                .append("from  sdb_ycy_card_cash_time pl left join sdb_ycy_cards cd on pl.card_bn=cd.card_bn ");

        return new StepReaderBuilder<RedeemPlanEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(RedeemPlanEntity.class).multithreaded(true).sortKeys("pl.cash_time_id").build();
    }

    @Bean("bwoilOrderRedeemPlanMigrationProcessor")
    public ItemProcessor<RedeemPlanEntity, RedeemPlanEntity> processor() {
        return item -> {
            convert(item);
            return item;
        };
    }

    @Bean("bwoilOrderRedeemPlanMigrationWriter")
    public ItemWriter<RedeemPlanEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_redeem_plan (id, card_bn, member_id, sale_date, term, cash_amount, cash_remain, cash_total, force_sale, sale_flag, status, create_time, last_update_time) VALUES (:cashTimeId, :cardBn, :memberId, :saleDate, :term, :cashAmount, :cashRemain, :cashTotal, :forceSale, :saleFlag, :status, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<RedeemPlanEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(RedeemPlanEntity target) {
        target.setLastUpdateTime(target.getCreateTime());
        target.setStatus("0");
        target.setForceSale("N");
        BigDecimal cashRemain = target.getCashRemain();
        if (BigDecimal.ZERO.compareTo(cashRemain) >= 0) {
            target.setSaleFlag("Y");
        } else {
            target.setSaleFlag("N");
        }
    }
}
