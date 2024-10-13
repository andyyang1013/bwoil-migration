package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationCompany;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationCompany;
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

@Configuration("bwoilOperationGasstationCompanyMigration")
public class BwoilOperationGasstationCompanyMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationCompanyMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationCompanyMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationCompany, BwoilOperationGasstationCompany>().factory(stepBuilderFactory).name("运营服务-加油站品牌公司Company相关信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationCompanyMigrationReader")
    public ItemReader<OriginOperationGasstationCompany> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_gasstation_company";
        return new StepReaderBuilder<OriginOperationGasstationCompany>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationCompany.class).multithreaded(true).sortKeys("company_id").build();
    }

    @Bean("bwoilOperationGasstationCompanyMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationCompany, BwoilOperationGasstationCompany> processor() {
        return item -> {
            BwoilOperationGasstationCompany target = new BwoilOperationGasstationCompany();
            BeanUtils.copyProperties(item, target);
            if (item.getCreateTime() == null || item.getCreateTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreateTime().toString()) * 1000L));
            }
            if (item.getLastmodify() == null || item.getLastmodify() == 0) {
                target.setLastmodify(null);
            } else {
                target.setLastmodify(new Date(Long.parseLong(item.getLastmodify().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationCompanyMigrationWriter")
    public ItemWriter<BwoilOperationGasstationCompany> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_company (company_id, company_name, company_contact, company_tel, company_dess, create_time, lastmodify, status) VALUES (:companyId, :companyName, :companyContact, :companyTel, :companyDess, :createTime, :lastmodify, :status)";
        return new StepWriterBuilder<BwoilOperationGasstationCompany>().dataSource(dataSource).sql(sqlStr).build();
    }
}
