package com.bwoil.c2b.migration.steps.order;

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
import com.bwoil.c2b.migration.steps.order.entity.OrderItemsEntity;
import com.bwoil.c2b.migration.utils.PhpSeriailizeUtil;
import com.bwoil.c2b.migration.utils.PordAttr;

@Configuration("bwoilOrderItemsMigration")
public class BwoilOrderItemsMigration {

    private final StepBuilderFactory stepBuilderFactory;
    
    private static int once = 0;

    @Autowired
    public BwoilOrderItemsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderItemsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OrderItemsEntity, OrderItemsEntity>().factory(stepBuilderFactory).name("订单服务-订单快照表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderItemsMigrationReader")
    public ItemReader<OrderItemsEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sqlStr = new StringBuffer("select ");
        sqlStr.append("item_id,order_id,product_id,bn as product_bn,name as product_name,product_attr,type_id,type_bn as type_bn,")
                .append("0 as type_alisa,")
                .append("0 as rule_detail_id,")
                .append("action_solution,addon,item_type,card_type ")
                .append(" from sdb_b2c_order_items");
        return new StepReaderBuilder<OrderItemsEntity>().dataSource(dataSource).sql(sqlStr.toString()).mappedClass(OrderItemsEntity.class).multithreaded(true).sortKeys("item_id").build();
    }

    @Bean("bwoilOrderItemsMigrationProcessor")
    public ItemProcessor<OrderItemsEntity, OrderItemsEntity> processor() {
        return item -> {
            OrderItemsEntity target = new OrderItemsEntity();
            BeanUtils.copyProperties(item, target);
            convert(target);
            return target;
        };
    }

    @Bean("bwoilOrderItemsMigrationWriter")
    public ItemWriter<OrderItemsEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_items (id,order_id,product_id,product_bn,product_name,product_attr,type_id,type_bn,type_alisa,rule_detail_id,action_solution,addon,item_type,card_type) "
                + "VALUES (:itemId, :orderId, :productId, :productBn, :productName, :productAttr, :typeId, :typeBn, :typeAlisa, :ruleDetailId, :actionSolution, :addon, :itemType, :cardType)";
        return new StepWriterBuilder<OrderItemsEntity>().dataSource(dataSource).sql(sqlStr).build();
    }

    private void convert(OrderItemsEntity data) {
        if (StringUtils.isNotEmpty(data.getActionSolution())) {
        	try {
               //TODO ansys(data);	

                String fac = PhpSeriailizeUtil.getPhpString(data.getActionSolution());
                data.setActionSolution(PhpSeriailizeUtil.getPhpString(fac));
        	}catch(Exception e) {
        		once++;
        		if(once==1) {
        			e.printStackTrace();
        		}
        	}
        }

        if (StringUtils.isNotEmpty(data.getAddon())) {
            data.setAddon(PhpSeriailizeUtil.getPhpString(data.getAddon()));
        }
        data.setProductAttr(convertProdAttr(data.getTypeBn()));        	
    }
    
    private String  convertProdAttr(String typeBn) {

        if("amount_refuel_nation_v1".equals(typeBn)
        		||"fixed_litre_up_v1".equals(typeBn)
        		||"fixed_litre_down_v1".equals(typeBn)
        		||"fixed_litre_exper_v1".equals(typeBn)
        		) {
        	return "amount";
        }else {
        	return PordAttr.convertProdAttr(typeBn);        	
        }
    }
    
    private static void ansys(OrderItemsEntity data) {
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
    	JSONObject newSpon = new JSONObject();
    	newSpon.put("perTermUnit", "".equals("1" .equals(target.getString("perperiodtype")) ? "M" :"D"));
    	String cashlimit = target.getString("cashlimit");
    	String cashperiods = target.getString("cashperiods");
    	if(StringUtils.isEmpty(cashlimit)) {
    		newSpon.put("saleTerm", cashlimit);
    	}else {
    		newSpon.put("saleTerm", cashperiods);
    	}
    	newSpon.put("fixedProfitRate", target.getString("giftpercent"));
    	
    	data.setActionSolution(newSpon.toJSONString());
	}
}
