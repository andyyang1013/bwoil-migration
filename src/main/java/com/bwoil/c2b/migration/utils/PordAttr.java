package com.bwoil.c2b.migration.utils;

public class PordAttr {
	
	public static String convertProdAttr(String typeBn) {
		if("fixed_amount".equals(typeBn)
				|| "fixed_amount_copy".equals(typeBn) 
				|| "amount".equals(typeBn) 
				|| "amount_refuel".equals(typeBn) 
				|| "cloud_float_amount".equals(typeBn) 
				|| "cloud_fixed_amount".equals(typeBn) 
				|| "fixed_amount_current".equals(typeBn) 
				|| "fixed_amount_term_1".equals(typeBn) 
				|| "fixed_amount_term_2".equals(typeBn) 
				|| "fixed_amount_term_3".equals(typeBn) 
				|| "amount_auto_refuel_exper".equals(typeBn) 
				||"wx_amount_auto_refuel_exper".equals(typeBn) 
				|| "amount_auto_refuel".equals(typeBn) 
				|| "amount_auto_entity".equals(typeBn) 
				|| "amount_refuel_activity".equals(typeBn) 
				||"amount_auto_direct".equals(typeBn) 
				|| "amount_auto_bos".equals(typeBn) 
				||"amount_auto_safeness".equals(typeBn) 
				||"amount_refuel_nation".equals(typeBn) 
				|| "amount_auto_bd".equals(typeBn) 
				||"amount_refuel_cloud".equals(typeBn) 
				||"fixed_amount_exper_v2".equals(typeBn) 
				||"fixed_amount_v2".equals(typeBn) 
				|| "amount_refuel_cloud_v1".equals(typeBn) 
				||"amount_auto_2019v1".equals(typeBn) 
				||"amount_auto_2019v2".equals(typeBn) 
				) {
			return "amount";
		}
		
		return "litre";
	}
	
}
