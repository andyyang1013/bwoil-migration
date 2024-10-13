package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationCashInfo;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationCashInfo;
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

@Configuration("bwoilOperationGasstationCashInfoMigration")
public class BwoilOperationGasstationCashInfoMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationCashInfoMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationCashInfoMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationCashInfo, BwoilOperationGasstationCashInfo>().factory(stepBuilderFactory).name("运营服务-加油站加油的扣款日志CashInfo记录-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationCashInfoMigrationReader")
    public ItemReader<OriginOperationGasstationCashInfo> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_gasstation_cash_info";
        return new StepReaderBuilder<OriginOperationGasstationCashInfo>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationCashInfo.class).multithreaded(true).sortKeys("trade_id").build();
    }

    @Bean("bwoilOperationGasstationCashInfoMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationCashInfo, BwoilOperationGasstationCashInfo> processor() {
        return item -> {
            BwoilOperationGasstationCashInfo target = new BwoilOperationGasstationCashInfo();
            BeanUtils.copyProperties(item, target);
            //转日期
            if (item.getCreateTime() == null || item.getCreateTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreateTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationCashInfoMigrationWriter")
    public ItemWriter<BwoilOperationGasstationCashInfo> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_cash_info " +
                "(trade_id, gas_cash_bn, invoice_bn, actual_litre, actual_price, actual_amount, member_id, user_id, operator_id, operator_name, oil_name, gun_num, station_id, source, order_type, create_time) VALUES " +
                "(:tradeId, :gasCashBn, :invoiceBn, :actualLitre, :actualPrice, :actualAmount, :memberId, :userId, :operatorId, :operatorName, :oilName, :gunNum, :stationId, :source, :orderType, :createTime)";
        return new StepWriterBuilder<BwoilOperationGasstationCashInfo>().dataSource(dataSource).sql(sqlStr).build();
    }
}
