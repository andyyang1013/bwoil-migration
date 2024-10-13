package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationPromotionReport;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationPromotionReport;
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
import java.util.Date;

@Configuration("bwoilOperationPromotionReportMigration")
public class BwoilOperationPromotionReportMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationPromotionReportMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationPromotionReportMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationPromotionReport, BwoilOperationPromotionReport>().factory(stepBuilderFactory).name("运营服务-分销报表PromotionReport列表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationPromotionReportMigrationReader")
    public ItemReader<OriginOperationPromotionReport> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_promotion_settlement";
        return new StepReaderBuilder<OriginOperationPromotionReport>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationPromotionReport.class).multithreaded(true).sortKeys("settlement_id").build();
    }

    @Bean("bwoilOperationPromotionReportMigrationProcessor")
    public ItemProcessor<OriginOperationPromotionReport, BwoilOperationPromotionReport> processor() {
        return item -> {
            BwoilOperationPromotionReport target = new BwoilOperationPromotionReport();
            target.setReportName(item.getReport_name());
            target.setShareMount(item.getSettlement_money());
            target.setPayStatus(item.getSettlement_status() == null ? 0 : Integer.parseInt(item.getSettlement_status()));
            target.setChannelType(item.getChannel_type());
            target.setCreateTime(item.getReport_add_time() == null ? null : new Date(item.getReport_add_time() * 1000));
            target.setReportContentTime(item.getReport_time() == null ? null : new Date(item.getReport_time() * 1000));
            return target;
        };
    }

    @Bean("bwoilOperationPromotionReportMigrationWriter")
    public ItemWriter<BwoilOperationPromotionReport> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_report " +
                " (report_name,share_mount,pay_status,channel_type,create_time,report_content_time,channel_name,promotion_shop_bn,member_id,total_amount,real_pay_amount) " +
                " VALUES " +
                " (:reportName,:shareMount,:payStatus,:channelType,:createTime,:reportContentTime,'','',0,0,0)";
        return new StepWriterBuilder<BwoilOperationPromotionReport>().dataSource(dataSource).sql(sqlStr).build();
    }
}
