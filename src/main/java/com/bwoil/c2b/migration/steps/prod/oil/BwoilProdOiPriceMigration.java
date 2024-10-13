package com.bwoil.c2b.migration.steps.prod.oil;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.prod.oil.origin.OriginProdOilPrice;
import com.bwoil.c2b.migration.steps.prod.oil.target.BwoilProdOilPrice;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration("bwoilProdOilPriceMigration")
public class BwoilProdOiPriceMigration {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilProdOiPriceMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilProdOilPriceMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginProdOilPrice, BwoilProdOilPrice>().factory(stepBuilderFactory).name("产品服务-油价-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilProdOilPriceMigrationReader")
    public ItemReader<OriginProdOilPrice> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_oil_price";
        return new StepReaderBuilder<OriginProdOilPrice>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginProdOilPrice.class).build();
    }


    @Bean("bwoilProdOilPriceMigrationProcessor")
    public ItemProcessor<OriginProdOilPrice, BwoilProdOilPrice> processor() {
        return item -> {
            BwoilProdOilPrice target = new BwoilProdOilPrice();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getOilPriceId());

            String oilDate = new SimpleDateFormat("yyyyMMdd").format(item.getOilDate());

            target.setOilDate(oilDate);
            target.setApplyerId(item.getOperatorId());
            target.setApplyerName(item.getOperatorName());

            long dateTemp = (long) item.getLastmodify() * 1000;
            Date dateLastmodify = new Date(dateTemp);
            target.setAuthTime(dateLastmodify);

            target.setStatus("0");

            long dateTemp2 = (long) item.getOperatorTime() * 1000;
            Date dateOperatorTime = new Date(dateTemp2);
            target.setCreateTime(dateOperatorTime);

            target.setLastUpdateTime(dateLastmodify);

            return target;
        };
    }

    @Bean("bwoilProdOilPriceMigrationWriter")
    public ItemWriter<BwoilProdOilPrice> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_prod_oil_price (id,oil_date,region_id,region_name,oil_id,oil_name,sale_price,buy_price,audit_status," +
                "applyer_id,applyer_name," +
                "auditor_id,auditor_name,auditor_remark," +
                "auth_time,STATUS,create_time,last_update_time)" +
                " VALUES (:id, :oilDate, :regionId, :regionName, :oilId, :oilName, :salePrice, :buyPrice, :auditStatus," +
                ":applyerId, :applyerName," +
                ":auditorId, :auditorName, :auditorRemark," +
                ":authTime, :status, :createTime, :lastUpdateTime)";

        return new StepWriterBuilder<BwoilProdOilPrice>().dataSource(dataSource).sql(sqlStr).build();
    }

}
