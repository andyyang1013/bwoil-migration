ALTER TABLE bwoil_base_paymanager ADD UNIQUE INDEX `unique_index_pay_way` (`pay_way`);

ALTER TABLE bwoil_member ADD INDEX `index_register_time_index` (`register_time`);
ALTER TABLE bwoil_member ADD INDEX `index_iden_code` (`iden_code`);

ALTER TABLE `bwoil_member_msg`
DROP INDEX `index_name`, ADD INDEX `index_member_msg_receiver` (`receiver`, `msg_type`, `send_time`), ADD INDEX `index_member_msg_msg_id` (`msg_id`);

ALTER TABLE bwoil_member_bank ADD INDEX `index_bank_no` (`bank_no`);

ALTER TABLE bwoil_operation_gasstation_refuel_record ADD INDEX index_status(STATUS);
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD INDEX index_member_id(member_id);
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD INDEX index_shift_id(shift_id);
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD INDEX index_effective(effective); 
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD INDEX index_station_id(station_id); 
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD INDEX index_createtime(createtime);

ALTER TABLE bwoil_operation_gasstation_refuel_order ADD INDEX index_createtime(create_time); 
ALTER TABLE bwoil_operation_gasstation_refuel_order ADD INDEX index_station_id(station_id); 
ALTER TABLE bwoil_operation_gasstation_refuel_order ADD INDEX index_order_id(order_id);

ALTER TABLE bwoil_operation_gasstation_staff_shift ADD INDEX index_staff_id(staff_id); 
ALTER TABLE bwoil_operation_gasstation_staff_shift ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_staff ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_sale_oil_price ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_oil ADD INDEX index_oil_id(oil_id);
ALTER TABLE bwoil_operation_gasstation_oil ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_oil_discount ADD INDEX index_agreement_id(agreement_id);
ALTER TABLE bwoil_operation_gasstation_oil_discount ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_manual_paybn ADD INDEX index_pay_bn(pay_bn);
ALTER TABLE bwoil_operation_gasstation_manual_paybn ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_manualbill_order ADD INDEX index_account_no(account_no);
ALTER TABLE bwoil_operation_gasstation_manualbill_order ADD INDEX index_order_id(order_id);

ALTER TABLE bwoil_operation_gasstation_manualbill ADD INDEX index_account_no(account_no);
ALTER TABLE bwoil_operation_gasstation_manualbill ADD INDEX index_pay_bn(pay_bn);
ALTER TABLE bwoil_operation_gasstation_manualbill ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_gun ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_coupons ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_refuel_coupon_rule ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_agreement ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_activity ADD INDEX index_station_id(station_id);

ALTER TABLE bwoil_operation_gasstation_activity_item ADD INDEX index_activity_id(activity_id);

ALTER TABLE bwoil_operation_promotion_orderlist ADD INDEX index_create_time(create_time);
ALTER TABLE bwoil_operation_promotion_orderlist ADD INDEX index_prom_member_id(prom_member_id);
ALTER TABLE bwoil_operation_promotion_orderlist ADD INDEX index_promotion_shop_bn(promotion_shop_bn);
ALTER TABLE bwoil_operation_promotion_orderlist ADD INDEX index_buyer_mobile(buyer_mobile);
ALTER TABLE bwoil_operation_promotion_orderlist ADD INDEX index_operation_promotion_orderlist_promotion_member_id(prom_member_id);

ALTER TABLE bwoil_operation_promotion_relationship ADD INDEX index_buyer_member_id(buyer_member_id);
ALTER TABLE bwoil_operation_promotion_relationship ADD INDEX index_channel_id(channel_id);
ALTER TABLE bwoil_operation_promotion_relationship ADD INDEX index_operation_relationship_promotion_id(promotion_member_id);

ALTER TABLE bwoil_operation_promotion_report ADD INDEX index_report_content_time(report_content_time);

ALTER TABLE bwoil_order_channel_orders ADD INDEX index_redeem_code(redeem_code);
ALTER TABLE bwoil_order_channel_orders ADD INDEX index_channel_order_id(channel_order_id);

ALTER TABLE `bwoil_operation_cashcoupons` ADD INDEX `cpns_order_id`(`cpns_order_id`) USING BTREE;
ALTER TABLE `bwoil_operation_cashcoupons` ADD INDEX `cpns_member_id`(`cpns_member_id`) USING BTREE;

ALTER TABLE `bwoil_operation_cashcoupon_activity` ADD INDEX `cpn_end_date`(`cpn_end_date`) USING BTREE;

ALTER TABLE bwoil_operation_base_order ADD INDEX index_operation_base_order_union1(channel_department_parent_id,channel_department_id,pay_time,product_category);
ALTER TABLE bwoil_operation_base_order ADD INDEX index_operation_base_order_member_id(member_id);

ALTER TABLE bwoil_operation_base_member ADD INDEX index_operation_base_member_union1(channel_department_parent_id,channel_department_id,buyer_register_time);
ALTER TABLE bwoil_operation_base_member ADD UNIQUE INDEX index_operation_base_member_member_id(buyer_member_id);

ALTER TABLE bwoil_operation_promotion_share ADD UNIQUE INDEX index_operation_promotion_share_buyer_member_id(buyer_member_id,share_type);
ALTER TABLE bwoil_operation_promotion_share ADD INDEX index_operation_promotion_share_promotion_member_id(promotion_member_id);


ALTER TABLE `bwoil_order_trans_flow` ADD INDEX index_reference_id (`reference_id`);

ALTER TABLE `bwoil_order_redeem` ADD INDEX index_trade_bn (`trade_bn`);

ALTER TABLE `bwoil_order_cash_flow` ADD INDEX index_member_id (`member_id`);
ALTER TABLE `bwoil_order_cash_flow` ADD INDEX index_order_id (`order_id`);
ALTER TABLE `bwoil_order_cash_flow` ADD INDEX index_is_freeze (`is_freeze`);
ALTER TABLE `bwoil_order_cash_flow` ADD INDEX index_account_type (`account_type`);
ALTER TABLE `bwoil_order_cash_flow` ADD INDEX index_payment_id (`payment_id`);

ALTER TABLE `bwoil_order_cards` ADD INDEX index_card_type (`card_type`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_create_time (`create_time`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_force_sale_date (`force_sale_date`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_card_status (`card_status`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_order_id (`order_id`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_card_bn (`card_bn`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_trans_status (`trans_status`);
ALTER TABLE `bwoil_order_cards` ADD INDEX index_recently_sale_date (`recently_sale_date`); 
ALTER TABLE `bwoil_order_cards` ADD INDEX index_member_id (`member_id`); 
ALTER TABLE `bwoil_order_cards` ADD INDEX index_card_name (`card_name`); 
ALTER TABLE `bwoil_order_cards` ADD INDEX index_card_id (`card_id`); 
ALTER TABLE `bwoil_order_cards` ADD INDEX index_status (`status`);

ALTER TABLE `bwoil_order_redeem_plan` ADD INDEX index_card_bn (`card_bn`); 
ALTER TABLE `bwoil_order_redeem_plan` ADD INDEX index_member_id (`member_id`); 
ALTER TABLE `bwoil_order_redeem_plan` ADD INDEX index_sale_date (`sale_date`); 
ALTER TABLE `bwoil_order_redeem_plan` ADD INDEX index_sale_flag (`sale_flag`);

ALTER TABLE `bwoil_order_asset` ADD INDEX index_asset_type (`asset_type`);