package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.CardPlanUpdateEntity;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration("bwoilOrderPlanUpdateMigration")
public class BwoilOrderPlanUpdateMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderPlanUpdateMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderPlanUpdateMigrationStep")
    public Step step(@Qualifier("targetDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CardPlanUpdateEntity, CardPlanUpdateEntity>().factory(stepBuilderFactory).name("订单服务-兑付计划数据更新处理-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderPlanUpdateMigrationReader")
    public ItemReader<CardPlanUpdateEntity> reader(@Qualifier("targetDataSource") DataSource dataSource) {
        return new StepReaderBuilder<CardPlanUpdateEntity>().dataSource(dataSource).sql("select card_bn,trans_status from bwoil_order_cards").mappedClass(CardPlanUpdateEntity.class).multithreaded(true).sortKeys("card_bn").build();
    }

    @Bean("bwoilOrderPlanUpdateMigrationProcessor")
    public ItemProcessor<CardPlanUpdateEntity, CardPlanUpdateEntity> processor() {
        return item -> {
            CardPlanUpdateEntity target = new CardPlanUpdateEntity();
            BeanUtils.copyProperties(item, target);
            return target;
        };
    }

    @Bean("bwoilOrderPlanUpdateMigrationWriter")
    public ItemWriter<CardPlanUpdateEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String curDate = format.format(new Date());
        String sqlStr = "update bwoil_order_redeem_plan set cash_remain=:cashRemain, sale_flag=:saleFlag where id=:id";
        return new StepWriterBuilder<CardPlanUpdateEntity>().dataSource(dataSource).sql(sqlStr).reprocessing(
                targetList -> {
                    List<CardPlanUpdateEntity> processedTargetList = new ArrayList<>();
                    for (CardPlanUpdateEntity item : targetList) {
                        String sql = "select sale_date,cash_amount,cash_remain,cash_total, sale_flag, id, term from bwoil_order_redeem_plan where card_bn='" + item.getCardBn() + "' and sale_flag!='Y' order by term desc";
                        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            ResultSet plan = preparedStatement.executeQuery();
                            int tempTerm = 0;
                            while (plan.next()) {
                                String saleDate = plan.getString(1);
                                String saleFlag = plan.getString(5);
                                int id = plan.getInt(6);
                                int term = plan.getInt(7);
                                if((tempTerm!=0 && term < tempTerm) || item.getTransStatus().equals("2")){
                                    CardPlanUpdateEntity newItem = new CardPlanUpdateEntity();
                                    newItem.setId(id);
                                    newItem.setCashRemain(BigDecimal.ZERO);
                                    newItem.setSaleFlag("Y");
                                    processedTargetList.add(newItem);
                                    continue;
                                }
                                if ("N".equals(saleFlag) && curDate.compareTo(saleDate) >= 0) {
                                    tempTerm = term;
                                }else{
                                    continue;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return processedTargetList;
                }
        ).build();
    }
}
