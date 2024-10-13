package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationSaleOilPrice;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationSaleOilPrice;
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

@Configuration("bwoilOperationGasstationSaleOilPriceMigration")
public class BwoilOperationGasstationSaleOilPriceMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationSaleOilPriceMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationSaleOilPriceMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationSaleOilPrice, BwoilOperationGasstationSaleOilPrice>().factory(stepBuilderFactory).name("运营服务-加油站油品SaleOilPrice挂牌价信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationSaleOilPriceMigrationReader")
    public ItemReader<OriginOperationGasstationSaleOilPrice> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ycy_oil_price";
        return new StepReaderBuilder<OriginOperationGasstationSaleOilPrice>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationSaleOilPrice.class).multithreaded(true).sortKeys("oil_price_id").build();
    }

    @Bean("bwoilOperationGasstationSaleOilPriceMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationSaleOilPrice, BwoilOperationGasstationSaleOilPrice> processor() {
        return item -> {
            BwoilOperationGasstationSaleOilPrice target = new BwoilOperationGasstationSaleOilPrice();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getOilPriceId());
            target.setDiscount(new BigDecimal(0));
            target.setSaleOilPrice(item.getOilPrice());
            target.setCreateName(item.getCreatedName());
            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreatedTime().toString()) * 1000L));
            }
            if (item.getModifyTime() == null || item.getModifyTime() == 0) {
                target.setModifyTime(null);
            } else {
                target.setModifyTime(new Date(Long.parseLong(item.getModifyTime().toString()) * 1000L));
            }
            if (item.getStartDate() == null || item.getStartDate() == 0) {
                target.setStartDate(null);
            } else {
                target.setStartDate(new Date(Long.parseLong(item.getStartDate().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationSaleOilPriceMigrationWriter")
    public ItemWriter<BwoilOperationGasstationSaleOilPrice> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_sale_oil_price (id, company_id, station_id, oil_id, sale_oil_price, discount, adjust_times, price_range, modify_name, create_name, status, start_date, modify_time, create_time) VALUES " +
                "(:id, :companyId, :stationId, :oilId, :saleOilPrice, :discount, :adjustTimes, :priceRange, :modifyName, :createName, :status, :startDate, :modifyTime, :createTime)";
        return new StepWriterBuilder<BwoilOperationGasstationSaleOilPrice>().dataSource(dataSource).sql(sqlStr).build();
    }
}
