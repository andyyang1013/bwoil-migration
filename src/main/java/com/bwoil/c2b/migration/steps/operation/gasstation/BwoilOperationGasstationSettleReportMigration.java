package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationSettleReport;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationSettleReport;
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

@Configuration("bwoilOperationGasstationSettleReportMigration")
public class BwoilOperationGasstationSettleReportMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationSettleReportMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationSettleReportMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationSettleReport, BwoilOperationGasstationSettleReport>().factory(stepBuilderFactory).name("运营服务-加油站结算SettleReport报表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationSettleReportMigrationReader")
    public ItemReader<OriginOperationGasstationSettleReport> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_station_settlement";
        return new StepReaderBuilder<OriginOperationGasstationSettleReport>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationSettleReport.class).multithreaded(true).sortKeys("settlement_id").build();
    }

    @Bean("bwoilOperationGasstationSettleReportMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationSettleReport, BwoilOperationGasstationSettleReport> processor() {
        return item -> {
            BwoilOperationGasstationSettleReport target = new BwoilOperationGasstationSettleReport();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getSettlementId());
            target.setReportName(item.getName());
            if (item.getCreated() == null || item.getCreated() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreated().toString()) * 1000L));
            }
            if (item.getStartTime() == null || item.getStartTime() == 0) {
                target.setStartTime(null);
            } else {
                target.setStartTime(new Date(Long.parseLong(item.getStartTime().toString()) * 1000L));
            }
            if (item.getEndTime() == null || item.getEndTime() == 0) {
                target.setEndTime(null);
            } else {
                target.setEndTime(new Date(Long.parseLong(item.getEndTime().toString()) * 1000L));
            }
            if (item.getPayTime() == null || item.getPayTime() == 0) {
                target.setPayTime(null);
            } else {
                target.setPayTime(new Date(Long.parseLong(item.getPayTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationSettleReportMigrationWriter")
    public ItemWriter<BwoilOperationGasstationSettleReport> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_settle_report (id, station_id, report_name, settlement_bn, staff_count, amount, bonus, create_time, start_time, end_time, pay_time, status) VALUES (:id, :stationId, :reportName, :settlementBn, :staffCount, :amount, :bonus, :createTime, :startTime, :endTime, :payTime, :status)";
        return new StepWriterBuilder<BwoilOperationGasstationSettleReport>().dataSource(dataSource).sql(sqlStr).build();
    }
}
