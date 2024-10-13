package com.bwoil.c2b.migration.steps.operation.oil;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.oil.pojo.origin.OriginOperationReportOilPckTran;
import com.bwoil.c2b.migration.steps.operation.oil.pojo.target.BwoilOperationReportOilPckTran;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
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

@Configuration("bwoilOperationReportOilPckTranMigration")
public class BwoilOperationReportOilPckTranMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationReportOilPckTranMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationReportOilPckTranMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationReportOilPckTran, BwoilOperationReportOilPckTran>().factory(stepBuilderFactory).name("运营服务-油箱交易报表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationReportOilPckTranMigrationReader")
    public ItemReader<OriginOperationReportOilPckTran> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "SELECT \n" +
                "t.goil_trade_id,\n" +
                "t.goils_name,\n" +
                "t.goil_trade_type,\n" +
                "t.goil_trade_nums,\n" +
                "t.goil_trade_give,\n" +
                "t.goil_trade_price,\n" +
                "t.order_id,\n" +
                "m.login_account,\n" +
                "t.goil_createtime,\n" +
                "t.goil_trade_bn,\n" +
                "t.lastmodify\n" +
                "FROM sdb_b2c_goil_trade t \n" +
                "LEFT JOIN (SELECT mm.login_account,mm.member_id FROM sdb_pam_members mm WHERE mm.login_type = 'mobile') m \n" +
                "ON t.member_id = m.member_id";
        return new StepReaderBuilder<OriginOperationReportOilPckTran>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationReportOilPckTran.class).build();
    }

    @Bean("bwoilOperationReportOilPckTranMigrationProcessor")
    public ItemProcessor<OriginOperationReportOilPckTran, BwoilOperationReportOilPckTran> processor() {
        return item -> {
            BwoilOperationReportOilPckTran target = new BwoilOperationReportOilPckTran();
            target.setId(item.getGoilTradeId());// ID
            target.setActivityName(item.getGoilsName());//活动名称
            //交易类型
            if ("pay".equals(item.getGoilTradeType())){
                // 订单抵扣
                target.setTranType("1003");
            }
            if ("deduct".equals(item.getGoilTradeType())){
                // 到期扣除
                target.setTranType("1004");
            }
            if ("givi".equals(item.getGoilTradeType())){
                // 赠送
                target.setTranType("1001");
            }
            target.setTranQuantity(item.getGoilTradeNums());//交易数量（L）
            target.setOilBoxMass(item.getGoilTradeGive());//交易后油箱总量（L）
            target.setTranOilPrice(item.getGoilTradePrice());//交易油价
            target.setTranOilPriceTotal(calculateTotal(item.getGoilTradePrice(), item.getGoilTradeNums()));//交易油量总额
            target.setOrderBn(item.getOrderId());//订单号
            target.setMemberAccount(item.getLoginAccount());//会员账号
            target.setMemberMobile(item.getLoginAccount());//会员手机号
            target.setTranTime(TimeStampUtil.getTimestampWithNull(item.getGoilCreatetime()));//交易时间
            target.setTranBn(item.getGoilTradeBn());//交易编号
            target.setCreateTime(TimeStampUtil.getTimestampWithNull(item.getLastmodify()));//创建时间
            target.setUpdateTime(TimeStampUtil.getTimestampWithNull(item.getLastmodify()));//更新时间
            return target;
        };
    }

    /**
     * 计算交易总金额
     *
     * @param goilTradePrice 交易油价
     * @param goilTradeNums  交易数量
     * @return 交易总金额
     */
    private Double calculateTotal(Double goilTradePrice, Double goilTradeNums) {
        if (goilTradePrice == null || goilTradeNums == null) {
            return null;
        }
        return goilTradePrice * goilTradeNums;
    }

    @Bean("bwoilOperationReportOilPckTranMigrationWriter")
    public ItemWriter<BwoilOperationReportOilPckTran> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_report_oil_pck_tran (id, activity_name, tran_type, tran_quantity, oil_box_mass, tran_oil_price, tran_oil_price_total, order_bn, member_mobile ,member_account, tran_time, tran_bn, oil_remark, create_time, update_time) VALUES (:id, :activityName, :tranType, :tranQuantity, :oilBoxMass, :tranOilPrice, :tranOilPriceTotal, :orderBn, :memberMobile ,:memberAccount, :tranTime, :tranBn, :oilRemark, :createTime, :updateTime)";
        return new StepWriterBuilder<BwoilOperationReportOilPckTran>().dataSource(dataSource).sql(sqlStr).build();
    }
}
