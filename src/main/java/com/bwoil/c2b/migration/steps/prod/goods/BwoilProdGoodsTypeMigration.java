package com.bwoil.c2b.migration.steps.prod.goods;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.prod.goods.origin.OriginProdGoodsType;
import com.bwoil.c2b.migration.steps.prod.goods.target.BwoilProdGoodsType;
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
import java.math.BigDecimal;

@Configuration("bwoilProdGoodsTypeMigration")
public class BwoilProdGoodsTypeMigration {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilProdGoodsTypeMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilProdGoodsTypeMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginProdGoodsType, BwoilProdGoodsType>().factory(stepBuilderFactory).name("产品服务-产品类型-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilProdGoodsTypeMigrationReader")
    public ItemReader<OriginProdGoodsType> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_goods_type";
        return new StepReaderBuilder<OriginProdGoodsType>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginProdGoodsType.class).build();
    }


    @Bean("bwoilProdGoodsTypeMigrationProcessor")
    public ItemProcessor<OriginProdGoodsType, BwoilProdGoodsType> processor() {
        return item -> {
            BwoilProdGoodsType target = new BwoilProdGoodsType();
            BeanUtils.copyProperties(item, target);

            target.setId(item.getTypeId());
            target.setAlias(item.getAlias());
            target.setTypeAttr(item.getType());
            target.setMaxDiscount(item.getDiscountMax());

            if (!ObjectUtils.isEmpty(item.getDailyLimit())) {
                target.setDayLimitAmout(BigDecimal.valueOf(Long.valueOf(item.getDailyLimit())));
            }
            if (!ObjectUtils.isEmpty(item.getYearLimit())) {
                target.setYearLimitAmout(BigDecimal.valueOf(Long.valueOf(item.getYearLimit())));
            }
            if (!ObjectUtils.isEmpty(item.getHoldLimit())) {
                target.setMaxAmount(BigDecimal.valueOf(Long.valueOf(item.getHoldLimit())));
            }

            target.setIosDesc(item.getDescapp());
            target.setAndroidDesc(item.getDescAndroid());
            target.setTypeDesc(item.getDescapp());
            target.setDetailDesc(item.getpShortDesc());
            target.setSaleDesc(item.getpHonourDesc());
            target.setpOrder(item.getSort());
            target.setAppCatalog(item.getApptypeList());

            if (!ObjectUtils.isEmpty(item.getDisabled())) {
                if ("true".equals(item.getDisabled())) {
                    target.setStatus("-1");
                }
                if ("false".equals(item.getDisabled())) {
                    target.setStatus("0");
                }
            } else {
                target.setStatus("-1");
            }
            return target;
        };
    }

    @Bean("bwoilProdGoodsTypeMigrationWriter")
    public ItemWriter<BwoilProdGoodsType> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_prod_goods_type" +
                " (id, name, type_bn, alias,type_attr, max_discount, day_limit_amout,year_limit_amout,max_amount," +
                " ios_desc, android_desc,  type_desc, detail_desc, sale_desc, keyword_image, label," +
                " p_order, remarks, app_catalog, status)" +
                " VALUES (:id, :name, :typeBn, :alias, :typeAttr, :maxDiscount, :dayLimitAmout, :yearLimitAmout, :maxAmount," +
                " :iosDesc, :androidDesc, :typeDesc, :detailDesc, :saleDesc, :keywordImage, :label, " +
                " :pOrder, :remarks, :appCatalog, :status)";

        return new StepWriterBuilder<BwoilProdGoodsType>().dataSource(dataSource).sql(sqlStr).build();
    }

}
