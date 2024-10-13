package com.bwoil.c2b.migration.steps.operation.cashcoupon;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.origin.OriginOperationCashcoupons;
import com.bwoil.c2b.migration.steps.operation.cashcoupon.pojo.target.BwoilOperationCashcoupons;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
import org.apache.commons.lang3.StringUtils;
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

@Configuration("bwoilOperationCashcouponsMigration")
public class BwoilOperationCashcouponsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationCashcouponsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationCashcouponsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationCashcoupons, BwoilOperationCashcoupons>().factory(stepBuilderFactory).name("运营服务-用户优惠券表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationCashcouponsMigrationReader")
    public ItemReader<OriginOperationCashcoupons> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select a.*, r.total_amount, r.payed, (SELECT count(DISTINCT(rule_type_bn)) FROM sdb_b2c_cashcoupon_rule where cpn_id = a.cpn_id) as max_rule_type_num, (SELECT min(rule_periods) FROM sdb_b2c_cashcoupon_rule where cpn_id = a.cpn_id) as min_rule_periods_num from sdb_b2c_cashcoupons as a LEFT JOIN sdb_b2c_orders r ON a.cpns_order_id = r.order_id";
        return new StepReaderBuilder<OriginOperationCashcoupons>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationCashcoupons.class).multithreaded(true).sortKeys("a.cpns_id").build();
    }

    @Bean("bwoilOperationCashcouponsMigrationProcessor")
    public ItemProcessor<OriginOperationCashcoupons, BwoilOperationCashcoupons> processor() {
        return item -> {
            BwoilOperationCashcoupons target = new BwoilOperationCashcoupons();
            target.setCpnsId(item.getCpnsId());//现金券ID
            target.setCpnsBn(item.getCpnsBn());//现金券编号
            target.setCpnId(item.getCpnId());//现金券活动ID
            target.setCpnName(item.getCpnName());//现金券活动名称
            target.setCpnsMoney(item.getCpnsMoney());//现金券面额
            target.setCpnsMemberId(item.getCpnsMemberId());//会员id
            target.setCpnsMoblie(item.getCpnsMoblie());//手机号码
            target.setCpnsStartDate(item.getCpnsStartDate());//现金券使用开始时间
            target.setCpnsEndDate(item.getCpnsEndDate());//现金券使用结束时间
            target.setCpnsSource(item.getCpnsSource());//现金券来源
            target.setCpnsPcode(item.getCpnsPcode());//推广码
            target.setCpnsMemo(item.getCpnsMemo());//备注
            target.setCpnsGiveMode(item.getCpnsGiveMode());//赠送方式
            target.setCpnsBindDate(item.getCpnsBindDate());//绑定时间
            target.setCpnsBindStatus(item.getCpnsBindStatus());//绑定状态
            target.setCpnsRules(item.getCpnsRules());//现金券使用规则
            target.setCpnsStatus(item.getCpnsStatus());//现金券状态
            target.setCpnsDate(item.getCpnsDate());//现金券使用时间
            target.setCpnsOrderId(item.getCpnsOrderId());//现金券使用订单ID
            target.setCpnsMaxCardNum(StringUtils.isBlank(item.getCpnsMaxCardNum()) ? "0" : item.getCpnsMaxCardNum());//适用最大卡数量
            if ("true".equals(item.getIsNotice())) {
                target.setIsNotice("1");
            }
            if ("false".equals(item.getIsNotice())) {
                target.setIsNotice("0");
            }
            target.setCpnsType(item.getCpnsType());//优惠券类型
            target.setRecommendedMemberId(item.getRecommendedMemberId());//被推荐人账号
            target.setCreateTime(TimeStampUtil.getTimestampWithNull(item.getCreateTime()));//创建时间
            target.setLastUpdateTime(TimeStampUtil.getTimestampWithNull(item.getLastmodify()));//最后更新时间
            target.setMaxRuleTypeNum(StringUtils.isBlank(item.getMaxRuleTypeNum()) ? "0" : item.getMaxRuleTypeNum());//该优惠活动支持最大的产品类型数
            target.setMinRulePeriodsNum(StringUtils.isBlank(item.getMinRulePeriodsNum()) ? "0" : item.getMinRulePeriodsNum());//该优惠活动支持最小的产品期数
            target.setStatus("0");
            target.setGoodsName("");
            if (StringUtils.isBlank(item.getTotalAmount())) {
                target.setOrderPrice("0");
            }
            if (StringUtils.isNotBlank(item.getTotalAmount())) {
                target.setOrderPrice(item.getTotalAmount());
            }
            if (StringUtils.isBlank(item.getPayed())) {
                target.setPayPrice("0");
            }
            if (StringUtils.isNotBlank(item.getPayed())) {
                target.setPayPrice(item.getPayed());
            }
            return target;
        };
    }

    @Bean("bwoilOperationCashcouponsMigrationWriter")
    public ItemWriter<BwoilOperationCashcoupons> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_cashcoupons (cpns_id, cpns_bn, cpn_id, cpn_name, cpns_money, cpns_member_id, cpns_moblie, cpns_start_date, cpns_end_date, cpns_source, cpns_pcode, cpns_memo, cpns_give_mode, cpns_bind_date, cpns_bind_status, cpns_rules, cpns_status, cpns_date, cpns_order_id, cpns_max_card_num, is_notice, cpns_type, recommended_member_id, create_time, last_update_time, status, max_rule_type_num, min_rule_periods_num, goods_name, order_price, pay_price) VALUES (:cpnsId, :cpnsBn, :cpnId, :cpnName, :cpnsMoney, :cpnsMemberId, :cpnsMoblie, :cpnsStartDate, :cpnsEndDate, :cpnsSource, :cpnsPcode, :cpnsMemo, :cpnsGiveMode, :cpnsBindDate, :cpnsBindStatus, :cpnsRules, :cpnsStatus, :cpnsDate, :cpnsOrderId, :cpnsMaxCardNum, :isNotice, :cpnsType, :recommendedMemberId, :createTime, :lastUpdateTime, :status, :maxRuleTypeNum, :minRulePeriodsNum, :goodsName, :orderPrice, :payPrice)";
        return new StepWriterBuilder<BwoilOperationCashcoupons>().dataSource(dataSource).sql(sqlStr).build();
    }
}
