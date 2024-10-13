package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.CardUpdateEntity;
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

@Configuration("bwoilOrderCardUpdateMigration")
public class BwoilOrderCardUpdateMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCardUpdateMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCardUpdateMigrationStep")
    public Step step(@Qualifier("targetDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CardUpdateEntity, CardUpdateEntity>().factory(stepBuilderFactory).name("订单服务-卡数据更新处理-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCardUpdateMigrationReader")
    public ItemReader<CardUpdateEntity> reader(@Qualifier("targetDataSource") DataSource dataSource) {

        return new StepReaderBuilder<CardUpdateEntity>().dataSource(dataSource).sql("select card_bn,order_id,total_amount from bwoil_order_cards").mappedClass(CardUpdateEntity.class).multithreaded(true).sortKeys("card_bn").build();
    }

    @Bean("bwoilOrderCardUpdateMigrationProcessor")
    public ItemProcessor<CardUpdateEntity, CardUpdateEntity> processor() {
        return item -> {
            CardUpdateEntity target = new CardUpdateEntity();
            BeanUtils.copyProperties(item, target);
            return target;
        };
    }

    @Bean("bwoilOrderCardUpdateMigrationWriter")
    public ItemWriter<CardUpdateEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String curDate = format.format(new Date());
        String sqlStr = "update bwoil_order_cards set trans_status=:transStatus, recently_sale_date=:recentlySaleDate,can_sale_amount=:canSaleAmount where card_bn=:cardBn";
        return new StepWriterBuilder<CardUpdateEntity>().dataSource(dataSource).sql(sqlStr).reprocessing(
                targetList -> {
                    List<CardUpdateEntity> processedTargetList = new ArrayList<>();
                    for (CardUpdateEntity item : targetList) {
                        String sql = "select sale_date,cash_amount,cash_remain,cash_total, sale_flag from bwoil_order_redeem_plan where card_bn='" + item.getCardBn() + "' order by term desc";
                        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            ResultSet plan = preparedStatement.executeQuery();
                            BigDecimal canSale = BigDecimal.ZERO;
                            BigDecimal remain = BigDecimal.ZERO;
                            boolean redeemAll = true;
                            String tempDate = "";
                            while (plan.next()) {
                                String saleDate = plan.getString(1);
                                BigDecimal cashAmount = plan.getBigDecimal(2);
                                BigDecimal cashRemain = plan.getBigDecimal(3);
                                String saleFlag = plan.getString(5);
                                remain = remain.add(cashAmount);
                                if("Y".equals(saleFlag) && BigDecimal.ZERO.compareTo(item.getTotalAmount()) == 0){
                                    tempDate = saleDate;
                                    break;
                                }

                                if("Y".equals(saleFlag) && BigDecimal.ZERO.compareTo(item.getTotalAmount()) != 0){
                                    break;
                                }

                                if("N".equals(saleFlag)){
                                    redeemAll = false;
                                }

                                if("N".equals(saleFlag) && curDate.compareTo(saleDate) <= 0){
                                    tempDate = saleDate;
                                }

                                if ("N".equals(saleFlag) && curDate.compareTo(saleDate) >= 0) {
                                    canSale = cashRemain;
                                    tempDate = saleDate;
                                    break;
                                }
                            }
                            DateFormat fm = new SimpleDateFormat("yyyyMMdd");
                            CardUpdateEntity newItem = new CardUpdateEntity();
                            newItem.setCardBn(item.getCardBn());
                            newItem.setCanSaleAmount(canSale);
                            try {
                                newItem.setRecentlySaleDate(fm.parse(tempDate));
                            } catch (Exception ex) {
                                newItem.setRecentlySaleDate(null);
                            }
                            if (redeemAll || item.getTotalAmount().compareTo(BigDecimal.ZERO)<=0) {
                                newItem.setTransStatus("2");
                            } else {
                                newItem.setTransStatus(BigDecimal.ZERO.compareTo(canSale) >= 0 ? "0" : "1");
                            }
                            processedTargetList.add(newItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return processedTargetList;
                }
        ).build();
    }
}
