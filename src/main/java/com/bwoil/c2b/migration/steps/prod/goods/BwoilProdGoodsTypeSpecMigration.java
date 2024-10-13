package com.bwoil.c2b.migration.steps.prod.goods;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.prod.goods.origin.OriginProdGoodsTypeSpec;
import com.bwoil.c2b.migration.steps.prod.goods.target.BwoilProdGoodsTypeSpec;
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

// 更新产品类型规格
@Configuration("bwoilProdGoodsTypeSpecMigration")
public class BwoilProdGoodsTypeSpecMigration {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilProdGoodsTypeSpecMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilProdGoodsTypeSpecMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginProdGoodsTypeSpec, BwoilProdGoodsTypeSpec>().factory(stepBuilderFactory).name("产品服务-产品类型规格更新-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilProdGoodsTypeSpecMigrationReader")
    public ItemReader<OriginProdGoodsTypeSpec> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "\tSELECT type_id ,CONCAT('[',GROUP_CONCAT(spec_id ORDER BY spec_id SEPARATOR ','),']')  AS spec_ids\n" +
                "\tFROM (\n" +
                "\n" +
                "\t\t# 根据类型id 排序查规格\n" +
                "\t\tSELECT spec_id, type_id\n" +
                "\t\tFROM sdb_b2c_goods_type_spec \n" +
                "\t\tWHERE type_id \n" +
                "\t\tIN (SELECT type_id FROM sdb_b2c_goods_type) \n" +
                "\t\tORDER BY type_id\n" +
                "\n" +
                "\t)a\n" +
                "\tWHERE type_id\n" +
                "\tGROUP BY type_id";
        return new StepReaderBuilder<OriginProdGoodsTypeSpec>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginProdGoodsTypeSpec.class).build();
    }


    @Bean("bwoilProdGoodsTypeSpecMigrationProcessor")
    public ItemProcessor<OriginProdGoodsTypeSpec, BwoilProdGoodsTypeSpec> processor() {
        return item -> {
            BwoilProdGoodsTypeSpec target = new BwoilProdGoodsTypeSpec();
            BeanUtils.copyProperties(item, target);

            target.setId(item.getTypeId());
            target.setSpecIds(item.getSpecIds());
            return target;
        };
    }

    @Bean("bwoilProdGoodsTypeSpecMigrationWriter")
    public ItemWriter<BwoilProdGoodsTypeSpec> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "UPDATE bwoil_prod_goods_type t SET t.spec_ids = :specIds WHERE t.id = :id ;";
        return new StepWriterBuilder<BwoilProdGoodsTypeSpec>().dataSource(dataSource).sql(sqlStr).build();
    }

}
