package com.bwoil.c2b.migration.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;


public class PhpSeriailizeUtil {

    public static String getPhpString(String value) {
        PHPSerialize p = new PHPSerialize();
        PHPValue c = new PHPValue(null);
        c = p.unserialize(value);
        String jsonString = JSONObject.toJSONString(c.toObject());
        return jsonString;
    }

    public static Map getPhpMap(String value) {
        PHPSerialize ps = new PHPSerialize();
        return ps.unserialize(value).toMapObject();
    }

    public static JSONObject unserializePhpString(String value) {
        PHPSerialize p = new PHPSerialize();
        PHPValue c = new PHPValue(null);
        c = p.unserialize(value);
        return JSON.parseObject(JSONObject.toJSONString(c.toObject()));
    }

	/*public static void main(String[] args) {
		// String value =
		// "a:3:{s:21:\"spec_private_value_id\";a:3:{i:6;s:12:\"144981927214\";i:9;s:12:\"144981927221\";i:10;s:11:\"14498192721\";}s:10:\"spec_value\";a:3:{i:6;s:5:\"50升\";i:9;s:12:\"Ⅳ汽油90#\";i:10;s:16:\"北京/北京市\";}s:13:\"spec_value_id\";a:3:{i:6;s:2:\"14\";i:9;s:2:\"21\";i:10;s:1:\"1\";}}";
		// String value =
		// "a:1:{s:35:\"b2c_promotion_solutions_card_tocash\";a:18:{s:16:\"one_time_payment\";s:1:\"0\";s:22:\"custom_purchase_amount\";s:1:\"0\";s:7:\"buy_num\";s:1:\"0\";s:11:\"buy_num_min\";s:1:\"0\";s:11:\"buy_num_max\";s:1:\"0\";s:18:\"recommended_amount\";s:1:\"0\";s:15:\"cash_start_unit\";s:1:\"0\";s:11:\"giftpercent\";s:1:\"0\";s:11:\"cashperiods\";s:1:\"1\";s:9:\"perperiod\";s:1:\"1\";s:13:\"perperiodtype\";s:1:\"0\";s:7:\"cashnum\";s:1:\"1\";s:13:\"cashstartdays\";s:1:\"0\";s:14:\"validityperiod\";s:1:\"1\";s:14:\"allowbuybacked\";s:1:\"0\";s:16:\"buybackedpercent\";s:1:\"0\";s:13:\"allowtransfer\";s:1:\"0\";s:15:\"transferpercent\";s:1:\"0\";}}";
		String value = "a:1:{s:35:\"b2c_promotion_solutions_card_tocash\";a:24:{s:10:\"allow_cash\";s:1:\"1\";s:12:\"allow_refuel\";s:1:\"0\";s:16:\"one_time_payment\";s:1:\"0\";s:22:\"custom_purchase_amount\";s:1:\"0\";s:7:\"buy_num\";s:1:\"0\";s:11:\"buy_num_min\";s:1:\"0\";s:11:\"buy_num_max\";s:1:\"0\";s:18:\"recommended_amount\";s:1:\"0\";s:15:\"cash_start_unit\";s:1:\"0\";s:11:\"giftpercent\";a:3:{i:0;s:2:\"10\";i:1;s:2:\"20\";i:2;s:2:\"20\";}s:11:\"end_of_gift\";s:1:\"0\";s:11:\"cashperiods\";s:1:\"1\";s:9:\"perperiod\";s:1:\"1\";s:13:\"perperiodtype\";s:1:\"0\";s:7:\"cashnum\";s:1:\"0\";s:13:\"cashstartdays\";s:1:\"0\";s:14:\"validityperiod\";s:1:\"0\";s:14:\"allowbuybacked\";s:1:\"0\";s:16:\"buybackedpercent\";s:1:\"0\";s:13:\"allowtransfer\";s:1:\"0\";s:15:\"transferpercent\";s:1:\"0\";s:10:\"is_default\";s:1:\"1\";s:11:\"cash_limits\";s:1:\"6\";s:10:\"cash_infos\";a:3:{i:3;a:16:{s:11:\"cashperiods\";i:1;s:15:\"floatingPercent\";s:1:\"5\";s:14:\"riseFeePercent\";s:1:\"2\";s:11:\"buy_num_min\";s:4:\"2000\";s:7:\"buy_num\";s:4:\"1000\";s:11:\"buy_num_max\";s:6:\"400000\";s:14:\"validityperiod\";s:2:\"12\";s:9:\"cashlimit\";s:1:\"3\";s:22:\"custom_purchase_amount\";i:1;s:10:\"is_default\";i:0;s:11:\"giftpercent\";s:2:\"10\";s:16:\"one_time_payment\";i:1;s:9:\"perperiod\";s:1:\"3\";s:13:\"perperiodtype\";i:1;s:13:\"cashstartdays\";s:1:\"3\";s:15:\"cash_start_unit\";i:1;}i:6;a:16:{s:11:\"cashperiods\";i:1;s:15:\"floatingPercent\";s:2:\"10\";s:14:\"riseFeePercent\";s:1:\"5\";s:11:\"buy_num_min\";s:4:\"1000\";s:7:\"buy_num\";s:4:\"1000\";s:11:\"buy_num_max\";s:6:\"100000\";s:14:\"validityperiod\";s:2:\"12\";s:9:\"cashlimit\";s:1:\"6\";s:22:\"custom_purchase_amount\";i:1;s:10:\"is_default\";i:1;s:11:\"giftpercent\";s:2:\"20\";s:16:\"one_time_payment\";i:1;s:9:\"perperiod\";s:1:\"6\";s:13:\"perperiodtype\";i:1;s:13:\"cashstartdays\";s:1:\"6\";s:15:\"cash_start_unit\";i:1;}i:12;a:16:{s:11:\"cashperiods\";i:1;s:15:\"floatingPercent\";s:2:\"10\";s:14:\"riseFeePercent\";s:1:\"5\";s:11:\"buy_num_min\";s:4:\"2000\";s:7:\"buy_num\";s:4:\"2000\";s:11:\"buy_num_max\";s:6:\"200000\";s:14:\"validityperiod\";s:2:\"18\";s:9:\"cashlimit\";s:2:\"12\";s:22:\"custom_purchase_amount\";i:1;s:10:\"is_default\";i:0;s:11:\"giftpercent\";s:2:\"20\";s:16:\"one_time_payment\";i:1;s:9:\"perperiod\";s:2:\"12\";s:13:\"perperiodtype\";i:1;s:13:\"cashstartdays\";s:2:\"12\";s:15:\"cash_start_unit\";i:1;}}}}";
		JSONObject resultJson = JSON.parseObject(getPhpString(value));
		System.out.println(resultJson.toJSONString());
	}*/

}
