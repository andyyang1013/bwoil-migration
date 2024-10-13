package com.bwoil.c2b.migration.steps.prod.spec;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.prod.spec.origin.OriginProdSpec;
import com.bwoil.c2b.migration.steps.prod.spec.target.BwoilProdSpec;
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

@Configuration("bwoilProdSpecMigration")
public class BwoilProdSpecMigration {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilProdSpecMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilProdSpecMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginProdSpec, BwoilProdSpec>().factory(stepBuilderFactory).name("产品服务-规格表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilProdSpecMigrationReader")
    public ItemReader<OriginProdSpec> reader(@Qualifier("originDataSource") DataSource dataSource) {
        // 只导入油品和地区的规格
        String sqlStr = "select * from sdb_b2c_specification WHERE spec_attr IN ('2','3')";
        return new StepReaderBuilder<OriginProdSpec>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginProdSpec.class).build();
    }


    @Bean("bwoilProdSpecMigrationProcessor")
    public ItemProcessor<OriginProdSpec, BwoilProdSpec> processor() {
        return item -> {
            BwoilProdSpec target = new BwoilProdSpec();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getSpecId());
            target.setStatus("0");
            if (!ObjectUtils.isEmpty(item.getDisabled())) {
                if ("true".equals(item.getDisabled())) {
                    target.setDisabled("Y");
                }
                if ("false".equals(item.getDisabled())) {
                    target.setDisabled("N");
                }
            } else {
                target.setDisabled("Y");
            }
            return target;
        };
    }

    @Bean("bwoilProdSpecMigrationWriter")
    public ItemWriter<BwoilProdSpec> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_prod_spec (id,  spec_name,  p_order,  spec_attr,  disabled,  status)" +
                " VALUES (:id, :specName, :pOrder, :specAttr, :disabled, :status)";
        return new StepWriterBuilder<BwoilProdSpec>().dataSource(dataSource).sql(sqlStr).build();
    }

}
