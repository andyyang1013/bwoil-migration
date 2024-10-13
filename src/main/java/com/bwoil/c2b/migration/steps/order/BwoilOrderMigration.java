package com.bwoil.c2b.migration.steps.order;

import java.math.BigDecimal;
import java.util.Map;

import javax.sql.DataSource;

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

import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.OrderEntity;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
import com.bwoil.c2b.migration.utils.TimeStampUtil;

@Configuration("bwoilOrderMigration")
public class BwoilOrderMigration {

    private final StepBuilderFactory stepBuilderFactory;
    
    private static int once = 0;

    @Autowired
    public BwoilOrderMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OrderEntity, OrderEntity>().factory(stepBuilderFactory).name("订单服务-订单表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderMigrationReader")
    public ItemReader<OrderEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("pay.t_payed as pay_time,oi.addon,od.refuelChannel as buy_channel, bm.shop_member_bn as shop_bn,od.order_id,od.member_id,pam.login_account as mobile,")
                .append("oi.product_id as product_id,oi.name as product_name,oi.region_id as region_id,oi.region_name as region_name,oi.oil_id as oil_id,oi.oil_price as oil_price,")
                .append("od.status as order_status,od.pay_status,od.payment as pay_way,")
                .append("od.is_protect,od.cost_protect,oi.nums as total_cnt,oi.total_period as term,od.total_amount,")
                .append("oi.litre_or_amount as total_litre,od.payed as real_pay_amount,od.cpns_money,od.goil_money,od.reduce_money,od.discount as discount_money,0 as redpacket_money,")
                .append("null as cps_ids,od.channel_id,od.extra_channel_id,od.ad_source,od.mark_type,od.mark_text,od.trade_no as pay_no,od.order_refer_no,od.refer_notice,")
                .append("od.source,od.devinfo,od.linepayment,od.ip,'0' as status,")
                .append("od.createtime as create_time,")
                .append("od.last_modified  as last_update_time ")
                .append(" from sdb_b2c_orders od ")
                .append(" left join (SELECT * from sdb_b2c_order_items t GROUP BY t.order_id) oi on oi.order_id=od.order_id")
                .append(" left join (select distinct b.rel_id,t_payed from sdb_ectools_payments a left join sdb_ectools_order_bills b on b.bill_id=a.payment_id and a.status='succ' group by  b.rel_id,t_payed) pay on pay.rel_id=od.order_id")
                .append(" left join sdb_b2c_members bm on bm.member_id=od.member_id")
                .append(" left join sdb_pam_members pam on pam.member_id=od.member_id and pam.login_type='mobile' ");
        return new StepReaderBuilder<OrderEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(OrderEntity.class).build();
    }

    @Bean("bwoilOrderMigrationProcessor")
    public ItemProcessor<OrderEntity, OrderEntity> processor() {
        return item -> {
            OrderEntity target = new OrderEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderMigrationWriter")
    public ItemWriter<OrderEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order (shop_bn,id,member_id,mobile,product_id,product_name,region_id,region_name,oil_id,oil_name,oil_price,order_status,pay_status,pay_way,pay_time,is_protect,cost_protect,total_cnt,term,total_amount,total_litre,real_pay_amount,cpns_money,goil_money,reduce_money,discount_money,redpacket_money,cps_ids,channel_id,extra_channel_id,ad_source,mark_type,mark_text,pay_no,order_refer_no,refer_notice,source,devinfo,linepayment,ip,status,create_time,last_update_time,buy_channel) "
                + "VALUES (:shopBn,:orderId, :memberId, :mobile, :productId, :productName, :regionId, :regionName, :oilId, :oilName, :oilPrice, :orderStatus, :payStatus, :payWay, :payTime, :isProtect, :costProtect, :totalCnt, :term, :totalAmount, :totalLitre, :realPayAmount, :cpnsMoney, :goilMoney, :reduceMoney, :discountMoney, :redpacketMoney, :cpsIds, :channelId, :extraChannelId, :adSource, :markType, :markText, :payNo, :orderReferNo, :referNotice, :source, :devinfo, :linepayment, :ip, :status, :createTime, :lastUpdateTime, :buyChannel)";
        return new StepWriterBuilder<OrderEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(OrderEntity target) {
        target.setIsProtect("FALSE".equalsIgnoreCase(target.getIsProtect()) ? "N" : "Y");
        target.setReferNotice("TRUE".equalsIgnoreCase(target.getReferNotice()) ? "Y" : "N");
        anysisProdAttr(target, target.getAddon());
        target.setCreateTime(TimeStampUtil.convertDate(target.getCreateTime()));
        if("0".equals(target.getLastUpdateTime())) {
        	target.setLastUpdateTime(target.getCreateTime());
        }else {
        	target.setLastUpdateTime(TimeStampUtil.convertDate(target.getLastUpdateTime()));	
        }
        target.setPayTime(TimeStampUtil.convertDate(target.getPayTime()));        



        if (target.getProductId() == null) {
            target.setProductId(0);
        }
        if (target.getOrderStatus() == null) {
            target.setOrderStatus("active");
        }
        if (target.getPayStatus() == null) {
            target.setPayStatus("0");
        }
        if (target.getPayWay() == null) {
            target.setPayWay("0");
        }
        if (target.getCostProtect() == null) {
            target.setCostProtect(BigDecimal.ZERO);
        }
        if (target.getTotalCnt() == null) {
            target.setTotalCnt(0);
        }
        if (target.getTerm() == null) {
            target.setTerm(0);
        }
        if (target.getTotalAmount() == null) {
            target.setTotalAmount(BigDecimal.ZERO);
        }
        if (target.getTotalLitre() == null) {
            target.setTotalLitre(BigDecimal.ZERO);
        }
        if (target.getRealPayAmount() == null) {
            target.setRealPayAmount(BigDecimal.ZERO);
        }
        if (target.getCpnsMoney() == null) {
            target.setCpnsMoney(BigDecimal.ZERO);
        }
        if (target.getGoilMoney() == null) {
            target.setGoilMoney(BigDecimal.ZERO);
        }
        if (target.getReduceMoney() == null) {
            target.setReduceMoney(BigDecimal.ZERO);
        }
        if (target.getDiscountMoney() == null) {
            target.setDiscountMoney(BigDecimal.ZERO);
        }
        if (target.getRedpacketMoney() == null) {
            target.setRedpacketMoney(BigDecimal.ZERO);
        }
        if (target.getChannelId() == null) {
            target.setChannelId(0);
        }
        if (target.getExtraChannelId() == null) {
            target.setExtraChannelId(0);
        }
        if (StringUtils.isEmpty(target.getMarkType())) {
            target.setMarkType("b1");
        }
        if (StringUtils.isNotEmpty(target.getMobile()) && target.getMobile().length() > 15) {
            target.setMobile("0");
        }
        //TODO ansys(target);
        target.setPayWay(convertPayWay(target.getPayWay()));
    }
    
    private String convertPayWay(String payWay) {
    	if("alipay".equals(payWay) || "aliapppay".equals(payWay)|| "uniondebitapppay".equals(payWay)){
    		return "ALIPAY";//支付宝
    	}
    	if("unionapppay".equals(payWay) || "upacpdebit".equals(payWay) || "upacp".equals(payWay) || "cbcwappay".equals(payWay) || "unionapppay".equals(payWay)|| "cbcpay".equals(payWay) 
    			|| "ibankinginner".equals(payWay) || "cbcapppay".equals(payWay) || "unionpay".equals(payWay) 
    			){
    		return "UNIONPAY";//中国银联
    	}
    	if("weixinapppay".equals(payWay) ||"weixinapppay".equals(payWay) ||"wxdirectapppay".equals(payWay)||"wxgaswappay".equals(payWay) || "weixinpay".equals(payWay)
    			|| "weixinwappay".equals(payWay) || "weixinwappay".equals(payWay) || "wxdirectwappay".equals(payWay)){
    		return "WX_APP";//微信支付
    	}
    	if("bwoilpay".equals(payWay) ){
    		return "GUANGHUI";//光汇支付
    	}
    	if("hnapay".equals(payWay) ||"bospay".equals(payWay)){
    		return "BAOFU";//宝付快捷支付
    	}
    	if("fuyoupay".equals(payWay)){
    		return "FUYOU_PC";//富友支付
    	}
    	if("deposit".equals(payWay)){
    		return "BALANCE";//预存款  
    	}
    	if("jdpay".equals(payWay) || "jdshoppay".equals(payWay)){
    		return "JD";//京东
    	}
    	if("yeepay".equals(payWay) || "yeeapppay".equals(payWay) || "yeempay".equals(payWay)) {
    		return "YEEPAY";//易宝支付  
    	}
    	if("bwoilwappay".equals(payWay)){
    		return "FASTPAY";//快捷支付 
    	}
    	if("offline".equals(payWay)){
    		return "OFFLINE";//线下支付
    	}
    	if("webankapppay".equals(payWay)){
    		return "WEBANK_APP";//微众支付 
    	}
    	if("czywappay".equals(payWay)){
    		return "COLOR_PC";//彩之云支付
    	}
    	if("wxpayface".equals(payWay)) {
    		return "WX_FACE";//微信刷脸支付
    	}
    	if("online-payment ".equals(payWay)) {
    		return "ONLINE";
    	}
    	
    	return "UNIONPAY";
    }
    
    private static void ansys(OrderEntity data) {
    	try {

        	if(data.getTerm() != 0) {
        		return;
        	}
        	if(StringUtils.isEmpty(data.getActionSolution())) {
        		return;
        	}
        	String str = PhpSeriailizeUtil.getPhpString(data.getActionSolution());
        	StringBuffer sb = new StringBuffer();
        	int need = 0;
        	int two = 0;
        	int split = 0;
        	for(char ch : str.toCharArray()) {
        		if(ch == '\\') {
        			continue;
        		}
        		if(ch =='{' || ch =='}') {
        			need = 0;
        			split = 0;
            		sb.append(ch);
            		continue;
        		}
        		if(two == 2) {
        			two = 0;
        			need = 0;
        			if(split == 4) {
        				split = 0;
        				sb.append(",");
        			}else {
        				sb.append(":");
        			}
        			
        			continue;
        		}
        		if(ch == ':') {
        			need++;
        			continue;
        		}
        		if(need != 2) {
        			continue;
        		}
        		if(ch == '"') {
        			two++;
        			split++;
        		}
        		sb.append(ch);
        	}
        	str = sb.toString();
        	str = str.replace(",}}", "}}");
        	JSONObject ob = JSONObject.parseObject(str, JSONObject.class);
        	if(ob == null) {
        		return;
        	}
        	JSONObject target = ob.getJSONObject(ob.getString("b2c_promotion_solutions_card_tocash"));
        	if(target == null) {
        		return;
        	}
        	String cashlimit = target.getString("cashlimit");
        	String cashperiods = target.getString("cashperiods");
        	if(StringUtils.isNotEmpty(cashlimit) && StringUtils.isNumeric(cashlimit)) {
        		data.setTerm(Integer.parseInt(cashlimit));
        	}else if(StringUtils.isNotEmpty(cashperiods) && StringUtils.isNumeric(cashlimit)){
        		data.setTerm(Integer.parseInt(cashperiods));
        	}
    	}catch(Exception e){
    		once++;
    		if(once==1) {
    			e.printStackTrace();
    		}
    	}
	}
    
    private static void anysisProdAttr(OrderEntity data, String addon) {

        if (StringUtils.isEmpty(addon)) {
        	return;
        }
    	addon = PhpSeriailizeUtil.getPhpString(addon);
        Map<String, Map<String, JSONObject>> mapType = JSONObject.parseObject(addon,Map.class);
        Map<String, JSONObject> ad = mapType.get("product_attr");
        for(String key : ad.keySet()) {
        	JSONObject d = ad.get(key);
        	String value = d.getString("value");
        	String label = d.getString("label");
        	if("油品".equals(label)) {
        		data.setOilName(value);
        		continue;
        	}
        	if("地区".equals(label)) {
        		data.setRegionName(value);
        		continue;
        	}
        }
    }
}
