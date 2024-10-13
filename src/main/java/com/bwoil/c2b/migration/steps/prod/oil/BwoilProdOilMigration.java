package com.bwoil.c2b.migration.steps.prod.oil;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.prod.oil.origin.OriginProdOil;
import com.bwoil.c2b.migration.steps.prod.oil.target.BwoilProdOil;
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
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;

@Configuration("bwoilProdOilMigration")
public class BwoilProdOilMigration {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilProdOilMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilProdOilMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginProdOil, BwoilProdOil>().factory(stepBuilderFactory).name("产品服务-油品-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilProdOilMigrationReader")
    public ItemReader<OriginProdOil> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_oil";
        return new StepReaderBuilder<OriginProdOil>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginProdOil.class).build();
    }


    @Bean("bwoilProdOilMigrationProcessor")
    public ItemProcessor<OriginProdOil, BwoilProdOil> processor() {
        return item -> {
            BwoilProdOil target = new BwoilProdOil();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getOilId());
            target.setStatus("0");
            if (!ObjectUtils.isEmpty(item.getIsStation())) {
                if ("true".equals(item.getIsStation())) {
                    target.setIsStation("Y");
                }
                if ("false".equals(item.getIsStation())) {
                    target.setIsStation("N");
                }
            } else {
                target.setIsStation("Y");
            }
            return target;
        };
    }

    @Bean("bwoilProdOilMigrationWriter")
    public ItemWriter<BwoilProdOil> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_prod_oil (id, oil_bn, oil_name, is_station)" +
                " VALUES (:id, :oilBn, :oilName, :isStation)";

        return new StepWriterBuilder<BwoilProdOil>().dataSource(dataSource).sql(sqlStr).build();
    }

}
