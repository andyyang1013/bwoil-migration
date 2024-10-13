package com.bwoil.c2b.migration.steps.operation.promotion;

import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationPromotionOrderlist;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationPromotionOrderlist;
import com.bwoil.c2b.migration.utils.PHPSerialize;
import com.bwoil.c2b.migration.utils.PHPValue;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
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

@Configuration("bwoilOperationPromotionOrderlistMigration")
public class BwoilOperationPromotionOrderlistMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationPromotionOrderlistMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationPromotionOrderlistMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationPromotionOrderlist, BwoilOperationPromotionOrderlist>().factory(stepBuilderFactory).name("运营服务-分销订单PromotionOrderlist表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationPromotionOrderlistMigrationReader")
    public ItemReader<OriginOperationPromotionOrderlist> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr =
                "SELECT " +
                        " pro.order_id, " +
                        " c.description, " +
                        " c.channel_id, " +
                        " c.channel_type, " +
                        " c.channel_name, " +
                        " pro.source, " +
                        " pro.promotion_profit, " +
                        " item.promotion_rate, " +
                        " ords.payed real_pay_amount, " +
                        " pro.order_bn,  " +
                        " item.name product_name, " +
                        " item.type_bn product_type, " +
                        " item.addon, " +
                        " ords.total_amount total_amount, " +
                        " pro.settlement_status pay_status, " +
                        " mem2.shop_member_bn promotion_shop_bn, " +
                        " mem.shop_member_bn buyer_shop_bn, " +
                        " pam2.login_account promotion_mobile, " +
                        " mem.regtime register_time, " +
                        " pro.lastmodify, " +
                        " pro.create_time, " +
                        " mem2.true_name promotion_member, " +
                        " mem.true_name buyer_member, " +
                        " item.nums produce_number, " +
                        " mem.member_id member_id, " +
                        " mem2.member_id prom_member_id, " +
                        " mem.realname_auth realname_auth, " +
                        " pam.login_account buyer_mobile, " +
                        " ords.createtime pay_time, " +
                        " ords.createtime order_time, " +
                        " pro.report_id, " +
                        " info.create_time real_time, " +
                        " item.action_solution " +
                        " FROM " +
                        " sdb_promotion_order pro " +
                        " LEFT JOIN (SELECT * FROM sdb_b2c_order_items GROUP BY order_id) item ON pro.order_bn = item.order_id " +
                        " LEFT JOIN sdb_promotion_channel c ON c.member_id = pro.promotion_member_id  " +
                        " LEFT JOIN sdb_b2c_orders ords ON ords.order_id = pro.order_bn " +
                        " LEFT JOIN sdb_b2c_members mem ON mem.member_id = pro.buyer_member_id " +
                        " LEFT JOIN sdb_pam_members pam ON pam.member_id = pro.buyer_member_id AND pam.login_type='mobile' " +
                        " LEFT JOIN sdb_b2c_members mem2 ON mem2.member_id = pro.promotion_member_id " +
                        " LEFT JOIN sdb_pam_members pam2 ON pam2.member_id = pro.promotion_member_id AND pam2.login_type='mobile' " +
                        " LEFT JOIN sdb_b2c_member_identity_info info ON pro.buyer_member_id = info.member_id ";
        return new StepReaderBuilder<OriginOperationPromotionOrderlist>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationPromotionOrderlist.class).multithreaded(true).sortKeys("pro.order_id").build();
    }

    @Bean("bwoilOperationPromotionOrderlistMigrationProcessor")
    public ItemProcessor<OriginOperationPromotionOrderlist, BwoilOperationPromotionOrderlist> processor() {
        return item -> {
            BwoilOperationPromotionOrderlist target = new BwoilOperationPromotionOrderlist();
            target.setDescription(item.getDescription() == null?"":item.getDescription());
            target.setChannelId(item.getChannelId() == null?0L:item.getChannelId());
            target.setChannelType(item.getChannelType() == null ?"":item.getChannelType());
            target.setChannelName(item.getChannelName() == null ?"":item.getChannelName());
            target.setSource(item.getSource() == null?"":item.getSource());
            target.setShareMount(item.getPromotionProfit());
            target.setRealPayAmount(item.getRealPayAmount());
            target.setOrderId(item.getOrderBn() == null?"":item.getOrderBn());
            target.setProductName(item.getProductName() == null?"":item.getProductName());
            target.setProductType(item.getProductType()==null?"":item.getProductType());
            target.setAddon(getJsonObjOfAddonData(item.getAddon()));
            target.setTotalAmount(item.getTotalAmount());
            target.setPayStatus(item.getPayStatus());
            target.setPromotionShopBn(item.getPromotionShopBn() ==null?"":item.getPromotionShopBn());
            target.setBuyerShopBn(item.getBuyerShopBn() == null?"":item.getBuyerShopBn());
            target.setPromotionMobile(item.getPromotionMobile() == null?"":item.getPromotionMobile());
            target.setRegisterTime(TimeStampUtil.getTimestampWithDefault(item.getRegisterTime()));
            target.setModifierTime(TimeStampUtil.getTimestampWithDefault(item.getLastmodify()));
            target.setCreateTime(TimeStampUtil.getTimestampWithDefault(item.getCreateTime()));
            target.setPromotionMember(item.getPromotionMember() == null?"":item.getPromotionMember());
            target.setBuyerMember(item.getBuyerMember() == null ?"":item.getBuyerMember());
            target.setProduceNumber(item.getProduceNumber() == null?0:item.getProduceNumber());
            target.setProducePeriod(getProducePeriodData(item.getActionSolution()));
            target.setProduceFreezing(getProduceFreezingData(item.getActionSolution()));
            target.setMemberId(item.getMemberId() == null?0:item.getMemberId());
            target.setPromMemberId(item.getPromMemberId() == null?0:item.getPromMemberId());
            if (item.getRealnameAuth() != null && "true".equals(item.getRealnameAuth())) {
                target.setRealnameAuth("1");
            }else {
                target.setRealnameAuth("0");
            }
            target.setBuyerMobile(item.getBuyerMobile()==null?"":item.getBuyerMobile());
            target.setPayTime(TimeStampUtil.getTimestampWithDefault(item.getPayTime()));
            target.setOrderTime(TimeStampUtil.getTimestampWithDefault(item.getOrderTime()));
            target.setBuyerMemberId(item.getMemberId() == null?0:item.getMemberId());
            target.setReportId(item.getReportId() == null?0:item.getReportId());
            target.setShareProportion(item.getPromotionRate() ==null?0:item.getPromotionRate());
            target.setRealTime(TimeStampUtil.getTimestampWithDefault(item.getRealTime()));
            return target;
        };
    }

    @Bean("bwoilOperationPromotionOrderlistMigrationWriter")
    public ItemWriter<BwoilOperationPromotionOrderlist> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_orderlist " +
                "(id, description, channel_id, channel_type, channel_name, source, share_mount, real_pay_amount, order_id, product_name, product_type, addon, total_amount, pay_status, promotion_shop_bn, buyer_shop_bn, promotion_mobile, register_time, modifier_time, create_time, promotion_member, buyer_member, produce_number, produce_period, produce_freezing, member_id, prom_member_id, realname_auth, buyer_mobile, pay_time, order_time, buyer_member_id, report_id,share_proportion,real_time) VALUES " +
                "(:id, :description, :channelId, :channelType, :channelName, :source, :shareMount, :realPayAmount, :orderId, :productName, :productType, :addon, :totalAmount, :payStatus, :promotionShopBn, :buyerShopBn, :promotionMobile, :registerTime, :modifierTime, :createTime, :promotionMember, :buyerMember, :produceNumber, :producePeriod, :produceFreezing, :memberId, :promMemberId, :realnameAuth, :buyerMobile, :payTime, :orderTime, :buyerMemberId, :reportId,:shareProportion,:realTime)";
        return new StepWriterBuilder<BwoilOperationPromotionOrderlist>().dataSource(dataSource).sql(sqlStr).build();
    }

    private Long getProducePeriodData(String data){
        Long value = 0L;
        JSONObject object2 = getJsonObjOfProductData(data);
        if(null != object2){
            String cashperiods = object2.getString("cashperiods");
            if(StringUtils.isNotBlank(cashperiods)){
                value = (long)object2.getIntValue("cashperiods");
            }
        }
        return value;
    }

    private Long getProduceFreezingData(String data){
        Long value = 0L;
        JSONObject object2 = getJsonObjOfProductData(data);
        if(object2 != null){
            value = (long)object2.getIntValue("cashstartdays");
        }
        return value;
    }

    private JSONObject getJsonObjOfProductData(String data){
        JSONObject object2 = null;
        if(StringUtils.isNotBlank(data)){
            PHPSerialize p = new PHPSerialize();
            PHPValue c = new PHPValue(null);
            c = p.unserialize(data);
            PHPValue c2 = p.unserialize(c.toString());
            JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(c2.toObject()));
            object2 = object.getJSONObject("b2c_promotion_solutions_card_tocash");
        }
        return object2;
    }

    private String getJsonObjOfAddonData(String data){
        String ad = "";
        if(StringUtils.isNotBlank(data)){
            String ttStr = PhpSeriailizeUtil.getPhpString(data);
            if(StringUtils.isNotBlank(ttStr)){
                JSONObject t0 = JSONObject.parseObject(ttStr);
                if(null != t0){
                    JSONObject productObj = t0.getJSONObject("product_attr");
                    if(null != productObj){
                        JSONObject obj7 = productObj.getJSONObject("7");
                        JSONObject obj6 = productObj.getJSONObject("6");
                        if(obj7 != null ){
                            ad = obj7.getString("value");
                        }
                        if(obj7 == null && obj6 != null ){
                            ad = obj6.getString("value");
                        }
                    }
                }
            }
        }
        return ad;
    }
}
