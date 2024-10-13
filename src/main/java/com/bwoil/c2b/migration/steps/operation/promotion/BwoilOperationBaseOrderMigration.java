package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationBaseOrder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationBaseOrder;
import org.apache.commons.lang3.StringUtils;
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

/**
 * @ClassName bwoilOperationBaseOrderMigration
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:59
 **/
@Configuration("bwoilOperationBaseOrderMigration")
public class BwoilOperationBaseOrderMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationBaseOrderMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationBaseOrderMigrationStep")
    public Step step(@Qualifier("baseOrderMigrationReader") ItemReader<OriginOperationBaseOrder> reader,
                     @Qualifier("baseOrderMigrationProcessor") ItemProcessor<OriginOperationBaseOrder, BwoilOperationBaseOrder> processor,
                     @Qualifier("baseOrderMigrationWriter") ItemWriter<BwoilOperationBaseOrder> writer) {
        return new StepBuilder<OriginOperationBaseOrder, BwoilOperationBaseOrder>().factory(stepBuilderFactory).name("运营服务-基础订单表-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("baseOrderMigrationReader")
    public ItemReader<OriginOperationBaseOrder> reader(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = " SELECT o.*,d.parent_id channel_department_parent_id,t.card_type " +
                " FROM bwoil_order o left join ( SELECT order_id,card_type FROM bwoil_order_cards ) t ON o.id = t.order_id " +
                " LEFT JOIN bwoil_operation_promotion_channel_department d ON o.extra_channel_id = d.id ";
        return new StepReaderBuilder<OriginOperationBaseOrder>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationBaseOrder.class).multithreaded(true).sortKeys("o.id").build();
    }

    @Bean("baseOrderMigrationProcessor")
    public ItemProcessor<OriginOperationBaseOrder, BwoilOperationBaseOrder> processor() {
        return item -> {
            BwoilOperationBaseOrder target = new BwoilOperationBaseOrder();
            BeanUtils.copyProperties(item, target);
            if (target.getTerm() == null) {
                target.setTerm(0);
            }
            target.setChannelDepartmentId(item.getExtraChannelId());
            return target;
        };
    }

    @Bean("baseOrderMigrationWriter")
    public ItemWriter<BwoilOperationBaseOrder> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = " INSERT INTO bwoil_operation_base_order  " +
                "(" +
                " order_id,member_id,mobile,product_id,product_name,region_id,region_name,oil_id,oil_name,oil_price,order_status,pay_status " +
                ",pay_way,pay_time,is_protect,cost_protect,total_cnt,term,total_amount,total_litre,real_pay_amount,cpns_money " +
                ",goil_money,reduce_money,discount_money,redpacket_money,cps_ids,channel_id,ad_source,mark_type,mark_text " +
                ",pay_no,order_refer_no,refer_notice,source,devinfo,linepayment,ip,channel_department_id,channel_department_parent_id,product_category" +
                ") " +
                "VALUES " +
                "( " +
                " :id,:memberId,:mobile,:productId,:productName,:regionId,:regionName,:oilId,:oilName,:oilPrice,:orderStatus,:payStatus" +
                ",:payWay,:payTime,:isProtect,:costProtect,:totalCnt,:term,:totalAmount,:totalLitre,:realPayAmount,:cpnsMoney" +
                ",:goilMoney,:reduceMoney,:discountMoney,:redpacketMoney,:cpsIds,:channelId,:adSource,:markType,:markText " +
                ",:payNo,:orderReferNo,:referNotice,:source,:devinfo,:linepayment,:ip,:channelDepartmentId,:channelDepartmentParentId,:productCategory " +
                ")";
        return new StepWriterBuilder<BwoilOperationBaseOrder>().dataSource(dataSource).sql(sqlStr).build();
    }
}
