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
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration("bwoilOrderCardUpdate2Migration")
public class BwoilOrderCardUpdate2Migration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderCardUpdate2Migration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderCardUpdate2MigrationStep")
    public Step step(@Qualifier("targetDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<CardUpdateEntity, CardUpdateEntity>().factory(stepBuilderFactory).name("订单服务-卡数据更新处理-历史数据迁移2")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderCardUpdate2MigrationReader")
    public ItemReader<CardUpdateEntity> reader(@Qualifier("targetDataSource") DataSource dataSource) {

        return new StepReaderBuilder<CardUpdateEntity>().dataSource(dataSource).sql("select total_cnt,id,real_pay_amount from bwoil_order").mappedClass(CardUpdateEntity.class).multithreaded(true).sortKeys("id").build();
    }

    @Bean("bwoilOrderCardUpdate2MigrationProcessor")
    public ItemProcessor<CardUpdateEntity, CardUpdateEntity> processor() {
        return item -> {
            CardUpdateEntity target = new CardUpdateEntity();
            BeanUtils.copyProperties(item, target);
            return target;
        };
    }

    @Bean("bwoilOrderCardUpdate2MigrationWriter")
    public ItemWriter<CardUpdateEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String curDate = format.format(new Date());
        String sqlStr = "update bwoil_order_cards set pay_amout=:realPayAmount where card_bn=:cardBn";
        return new StepWriterBuilder<CardUpdateEntity>().dataSource(dataSource).sql(sqlStr).reprocessing(
                targetList -> {
                    List<CardUpdateEntity> processedTargetList = new ArrayList<>();
                    for (CardUpdateEntity item : targetList) {
                        String sql = "select card_bn,pay_amout from bwoil_order_cards where order_id='" + item.getId() + "'";
                        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            ResultSet plan = preparedStatement.executeQuery();
                            while (plan.next()) {
                                String cardBn = plan.getString(1);
                                BigDecimal payAmout = plan.getBigDecimal(2);
                                if(BigDecimal.ZERO.compareTo(payAmout) != 0){
                                    continue;
                                }
                                CardUpdateEntity newItem = new CardUpdateEntity();
                                BigDecimal realPayAmount = item.getRealPayAmount();
                                Integer totalCnt = item.getTotalCnt();
                                if(totalCnt == 0){
                                    continue;
                                }
                                BigDecimal realPay = realPayAmount.divide(new BigDecimal(totalCnt), 2, RoundingMode.HALF_UP);
                                newItem.setRealPayAmount(realPay);
                                newItem.setCardBn(cardBn);
                                processedTargetList.add(newItem);
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
