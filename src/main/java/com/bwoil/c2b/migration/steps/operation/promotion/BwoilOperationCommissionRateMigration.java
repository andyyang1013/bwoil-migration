package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationCommissionRate;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationCommissionRate;
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

/**
 * @ClassName BwoilOperationCommissionRateMigration
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:59
 **/
@Configuration("bwoilOperationCommissionRateMigration")
public class BwoilOperationCommissionRateMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCommissionRateMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCommissionRateMigrationStep")
    public Step step(@Qualifier("commissionRateMigrationReader") ItemReader<OriginOperationCommissionRate> reader,
                     @Qualifier("commissionRateMigrationProcessor") ItemProcessor<OriginOperationCommissionRate, BwoilOperationCommissionRate> processor,
                     @Qualifier("commissionRateMigrationWriter") ItemWriter<BwoilOperationCommissionRate> writer) {
        return new StepBuilder<OriginOperationCommissionRate, BwoilOperationCommissionRate>().factory(stepBuilderFactory).name("运营服务-佣金比例-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("commissionRateMigrationReader")
    public ItemReader<OriginOperationCommissionRate> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = " SELECT " +
                " t.type_bn, " +
                " r.goods_spec, " +
                " r.goods_rate, " +
                " a.NAME login_name " +
                " FROM " +
                " sdb_promotion_goods_rate r " +
                " LEFT JOIN sdb_b2c_goods_type t ON r.goods_type_id = t.type_id " +
                " LEFT JOIN sdb_desktop_users a ON r.operator_id = a.user_id  ";
        return new StepReaderBuilder<OriginOperationCommissionRate>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCommissionRate.class).build();
    }

    @Bean("commissionRateMigrationProcessor")
    public ItemProcessor<OriginOperationCommissionRate, BwoilOperationCommissionRate> processor() {
        return item -> {
            BwoilOperationCommissionRate target = new BwoilOperationCommissionRate();
            target.setGoodsTypeBn(item.getType_bn());
            target.setGoodsRate(item.getGoods_rate());
            target.setGoodsAmount(item.getGoods_spec() == null ? 0 : Math.round(Double.parseDouble(item.getGoods_spec())));
            target.setOperatorName(item.getLogin_name());
            return target;
        };
    }

    @Bean("commissionRateMigrationWriter")
    public ItemWriter<BwoilOperationCommissionRate> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_commission_rate " +
                "(goods_type_bn,goods_amount,goods_rate,operator_name)" +
                " VALUES" +
                " (:goodsTypeBn,:goodsAmount,:goodsRate,:operatorName)";
        return new StepWriterBuilder<BwoilOperationCommissionRate>().dataSource(dataSource).sql(sqlStr).build();
    }
}
