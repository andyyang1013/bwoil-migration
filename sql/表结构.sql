/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

DROP TABLE IF EXISTS `bwoil_base_cardbin`;
CREATE TABLE IF NOT EXISTS `bwoil_base_cardbin` (
  `bin_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bank_name` varchar(32) NOT NULL COMMENT '银行名称',
  `bin_prefix` varchar(64) NOT NULL COMMENT 'CardBin前缀',
  `card_type_name` varchar(32) NOT NULL DEFAULT '0',
  `card_type` enum('1','2') NOT NULL DEFAULT '1',
  `short_no` varchar(32) NOT NULL DEFAULT '0',
  `gshort_no` varchar(32) NOT NULL DEFAULT '',
  `ushort_no` varchar(32) NOT NULL DEFAULT '' COMMENT '银联银行简称',
  PRIMARY KEY (`bin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_conf`;
CREATE TABLE IF NOT EXISTS `bwoil_base_conf` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `desc` varchar(100) DEFAULT NULL,
  `mtype` varchar(50) NOT NULL COMMENT '类型',
  `mkey` varchar(50) NOT NULL COMMENT '键',
  `val` varchar(4500) DEFAULT NULL,
  `status` char(2) DEFAULT '0' COMMENT '状态(0:正常 1:停用 -1:删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_dictionary`;
CREATE TABLE IF NOT EXISTS `bwoil_base_dictionary` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `data_desc` varchar(2000) DEFAULT NULL COMMENT '描述',
  `data_type` varchar(50) DEFAULT NULL COMMENT '字典类型key值',
  `data_key` varchar(50) NOT NULL COMMENT '字典的key值',
  `data_value` varchar(50) NOT NULL COMMENT '字典的value值',
  `sort` int(10) DEFAULT '0' COMMENT '排序(升序)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_data_type_key` (`data_type`,`data_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_files`;
CREATE TABLE IF NOT EXISTS `bwoil_base_files` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_path` varchar(200) NOT NULL COMMENT '文件路径',
  `file_type` int(2) NOT NULL COMMENT '文件类别，0临时文件/1长期有效文件',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_holiday_manage`;
CREATE TABLE IF NOT EXISTS `bwoil_base_holiday_manage` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `day_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '日期类型 0:工作日，1：周末，2：节假日',
  `real_date` varchar(20) DEFAULT NULL COMMENT '真实日期，格式:yyyyMMdd',
  `real_date_year` varchar(255) NOT NULL COMMENT '真实日期的年份',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节假日管理表';

DROP TABLE IF EXISTS `bwoil_base_image`;
CREATE TABLE IF NOT EXISTS `bwoil_base_image` (
  `image_id` char(32) NOT NULL COMMENT '图片ID',
  `url` varchar(200) NOT NULL COMMENT '网址',
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';

DROP TABLE IF EXISTS `bwoil_base_importexport_task`;
CREATE TABLE IF NOT EXISTS `bwoil_base_importexport_task` (
  `task_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` mediumint(8) NOT NULL DEFAULT '1' COMMENT '操作人',
  `user_name` varchar(100) DEFAULT 'admin' COMMENT '??????????',
  `name` varchar(255) NOT NULL COMMENT '任务名称',
  `message` varchar(255) DEFAULT NULL COMMENT '备注',
  `filetype` varchar(20) DEFAULT NULL COMMENT '文件类型',
  `create_date` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `complete_date` int(10) unsigned DEFAULT NULL COMMENT '完成时间',
  `type` enum('export','import') DEFAULT NULL COMMENT '任务类型',
  `status` enum('0','1','2','3','4','5','6','7','8') DEFAULT '0' COMMENT '任务状态',
  `is_display` enum('0','1') DEFAULT '0' COMMENT '是否显示',
  `file_key` varchar(255) DEFAULT NULL COMMENT '存储文件名称',
  PRIMARY KEY (`task_id`),
  KEY `ind_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_jpush`;
CREATE TABLE IF NOT EXISTS `bwoil_base_jpush` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `msg_id` varchar(75) DEFAULT NULL COMMENT '消息ID',
  `msg_type` varchar(25) DEFAULT NULL COMMENT '消息分类',
  `platform` varchar(20) NOT NULL COMMENT '平台',
  `audience` text COMMENT '推送设备对象(json)',
  `push_type` varchar(15) NOT NULL DEFAULT '0' COMMENT '推送类型',
  `alert` varchar(1024) DEFAULT NULL COMMENT '通知内容',
  `url` varchar(255) DEFAULT NULL COMMENT '活动链接',
  `title` varchar(64) DEFAULT NULL COMMENT '通知标题',
  `type_bn` varchar(64) DEFAULT NULL COMMENT '关联商品分类',
  `is_getreport` char(1) DEFAULT '0' COMMENT '是否已获取消息报告',
  `report` varchar(1024) DEFAULT NULL COMMENT '消息报告',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_log_email`;
CREATE TABLE IF NOT EXISTS `bwoil_base_log_email` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP',
  `busi_code` varchar(20) DEFAULT NULL COMMENT '业务类型',
  `busi_name` varchar(100) DEFAULT NULL COMMENT '业务名称',
  `tmpl_type` varchar(20) DEFAULT NULL COMMENT '模板类型',
  `tmpl_name` varchar(100) NOT NULL COMMENT '模板名称',
  `content` longtext COMMENT '发送内容',
  `sendto` varchar(50) NOT NULL COMMENT '接收方',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `is_success` char(1) DEFAULT NULL COMMENT '是否发生成功(1:失败 0: 成功)',
  `tip_content` varchar(255) DEFAULT NULL COMMENT '成功或失败提示',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_log_sms`;
CREATE TABLE IF NOT EXISTS `bwoil_base_log_sms` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP',
  `busi_code` varchar(30) DEFAULT NULL COMMENT '业务编码',
  `busi_name` varchar(100) DEFAULT NULL,
  `tmpl_name` varchar(100) NOT NULL COMMENT '模板名称',
  `content` varchar(1000) DEFAULT NULL COMMENT '发送内容',
  `sendto` varchar(15) NOT NULL COMMENT '接收方',
  `is_success` char(1) DEFAULT NULL COMMENT '是否发生成功(0: 成功 1:失败)',
  `tip_content` varchar(255) DEFAULT NULL COMMENT '成功或失败提示',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_paymanager`;
CREATE TABLE IF NOT EXISTS `bwoil_base_paymanager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pay_way` varchar(64) DEFAULT NULL COMMENT '支付方式',
  `status` char(1) DEFAULT '0' COMMENT '状态(1:停用 0:启用)',
  `platform_type` varchar(16) DEFAULT NULL COMMENT '支持平台类型（标准版，触屏版，移动版）',
  `pay_name` varchar(32) DEFAULT NULL COMMENT '支付方式名称',
  `sort` int(5) NOT NULL DEFAULT '999' COMMENT '排序',
  `val` longtext COMMENT '配置参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS `bwoil_base_quickpay`;
CREATE TABLE IF NOT EXISTS `bwoil_base_quickpay` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '银行名称',
  `card_type` char(1) DEFAULT NULL COMMENT '卡种类',
  `bank_bn` varchar(16) DEFAULT NULL COMMENT '银行标识',
  `big_logo_id` varchar(50) DEFAULT NULL COMMENT '银行大LOGO',
  `small_logo_id` varchar(50) DEFAULT NULL COMMENT '银行小LOGO',
  `limit_each_enable` char(1) DEFAULT NULL COMMENT '每笔限制开关',
  `limit_each_amount` decimal(20,3) DEFAULT NULL COMMENT '每笔限制金额',
  `limit_day_enable` char(1) DEFAULT NULL COMMENT '单日限制开关',
  `limit_day_amount` decimal(20,3) DEFAULT NULL COMMENT '单日限制金额',
  `limit_month_enable` char(1) DEFAULT NULL COMMENT '每月限制开关',
  `limit_month_amount` decimal(20,3) DEFAULT NULL COMMENT '每月限制金额',
  `open_online_pay` char(1) DEFAULT NULL COMMENT '开通银联在线支付开关',
  `baofu_type` char(1) DEFAULT NULL COMMENT '宝付协议类型(1.普通认证2.协议认证)',
  `bank_order` int(9) DEFAULT NULL COMMENT '银行排序',
  `status` varchar(2) DEFAULT NULL COMMENT '状态(-1:删除 0:正常)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `is_used` char(1) DEFAULT '' COMMENT '是否开启配置 1开启 0停用',
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `card_type` (`card_type`,`bank_bn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快捷支付表';

DROP TABLE IF EXISTS `bwoil_base_setting`;
CREATE TABLE IF NOT EXISTS `bwoil_base_setting` (
  `app` varchar(50) NOT NULL DEFAULT '' COMMENT 'app名',
  `key` varchar(255) NOT NULL DEFAULT '' COMMENT 'setting键值',
  `value` longtext COMMENT 'setting存储值',
  PRIMARY KEY (`app`,`key`),
  KEY `idx_key` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_syn_job`;
CREATE TABLE IF NOT EXISTS `bwoil_base_syn_job` (
  `syn_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '同步主键',
  `type` char(2) NOT NULL COMMENT '同步类型(详情查枚举)',
  `method` varchar(20) NOT NULL COMMENT 'RPC调用方法',
  `content` text NOT NULL COMMENT '同步内容',
  `handle_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '同步处理时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `is_handle` char(1) NOT NULL DEFAULT '1' COMMENT '0:处理 1:未处理',
  PRIMARY KEY (`syn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_tpl`;
CREATE TABLE IF NOT EXISTS `bwoil_base_tpl` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tmpl_type` varchar(50) NOT NULL COMMENT '类型',
  `sub_type` varchar(15) NOT NULL COMMENT '子类型',
  `tmpl_name` varchar(100) NOT NULL COMMENT '模板名称',
  `tmpl_content` longtext COMMENT '模板内容',
  `tmpl_variables` longtext COMMENT '模板变量定义',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` char(1) DEFAULT '1' COMMENT '状态(-1:删除 0:停用 1:正常 )',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tmpl_type` (`tmpl_type`,`sub_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_base_unionpay`;
CREATE TABLE IF NOT EXISTS `bwoil_base_unionpay` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '银行名称',
  `card_type` char(1) DEFAULT NULL COMMENT '卡种类',
  `bank_bn` varchar(16) DEFAULT NULL COMMENT '银行标识',
  `big_logo_id` varchar(50) DEFAULT NULL COMMENT '银行大LOGO',
  `small_logo_id` varchar(50) DEFAULT NULL COMMENT '银行小LOGO',
  `limit_each_enable` char(1) DEFAULT NULL COMMENT '每笔限制开关',
  `limit_each_amount` decimal(20,3) DEFAULT NULL COMMENT '每笔限制金额',
  `limit_day_enable` char(1) DEFAULT NULL COMMENT '单日限制开关',
  `limit_day_amount` decimal(20,3) DEFAULT NULL COMMENT '单日限制金额',
  `limit_month_enable` char(1) DEFAULT NULL COMMENT '每月限制开关',
  `limit_month_amount` decimal(20,3) DEFAULT NULL COMMENT '每月限制金额',
  `open_online_pay` char(1) DEFAULT NULL COMMENT '开通银联在线支付开关',
  `is_used` char(1) DEFAULT NULL COMMENT '是否启用该配置',
  `bank_order` int(9) DEFAULT NULL COMMENT '银行排序',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态(-1:停用 0:启用)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  PRIMARY KEY (`bank_id`) USING BTREE,
  UNIQUE KEY `card_type` (`card_type`,`bank_bn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='组合支付表';

DROP TABLE IF EXISTS `bwoil_base_wechatpay`;
CREATE TABLE IF NOT EXISTS `bwoil_base_wechatpay` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '银行名称',
  `card_type` char(1) DEFAULT NULL COMMENT '卡种类',
  `bank_bn` varchar(16) DEFAULT NULL COMMENT '银行标识',
  `big_logo_id` varchar(50) DEFAULT NULL COMMENT '银行大LOGO',
  `small_logo_id` varchar(50) DEFAULT NULL COMMENT '银行小LOGO',
  `limit_each_enable` char(1) DEFAULT NULL COMMENT '每笔限制开关',
  `limit_each_amount` decimal(20,3) DEFAULT NULL COMMENT '每笔限制金额',
  `limit_day_enable` char(1) DEFAULT NULL COMMENT '单日限制开关',
  `limit_day_amount` decimal(20,3) DEFAULT NULL COMMENT '单日限制金额',
  `limit_month_enable` char(1) DEFAULT NULL COMMENT '每月限制开关',
  `limit_month_amount` decimal(20,3) DEFAULT NULL COMMENT '每月限制金额',
  `open_online_pay` char(1) DEFAULT NULL COMMENT '开通银联在线支付开关',
  `bank_order` int(9) DEFAULT NULL COMMENT '银行排序',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态(-1:停用 0:启用)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `is_used` char(1) DEFAULT NULL COMMENT '是否启用该配置',
  PRIMARY KEY (`bank_id`) USING BTREE,
  UNIQUE KEY `card_type` (`card_type`,`bank_bn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='微信支付表';

DROP TABLE IF EXISTS `bwoil_member`;
CREATE TABLE IF NOT EXISTS `bwoil_member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `register_mobile` varchar(15) NOT NULL COMMENT '注册手机号',
  `country_code` varchar(10) DEFAULT NULL COMMENT '手机归属国家代码',
  `image_id` varchar(255) DEFAULT NULL COMMENT '用户头像id',
  `realname` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `sex` char(1) DEFAULT NULL COMMENT '性别(1:男2:女3:未知)',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `wechat_opid` varchar(100) DEFAULT NULL COMMENT '微信openid',
  `realname_auth` char(2) DEFAULT '0' COMMENT '实名认证标识(0：未实名 1:已实名)',
  `auth_channel` varchar(50) DEFAULT NULL COMMENT '实名认证渠道',
  `auth_time` datetime DEFAULT NULL COMMENT 'ʵ??ʱ??',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `trade_pwd` varchar(255) DEFAULT NULL COMMENT '交易密码',
  `webank_flag` varchar(20) DEFAULT '0' COMMENT '是否开通微众帐户(0:未开通 1:开通 2:等待人工审核)',
  `yun_acct_flag` varchar(100) DEFAULT '0' COMMENT '是否开通云油账号(0:未开通 1:开通)',
  `iden_code` varchar(150) DEFAULT NULL COMMENT '身份证',
  `shop_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `pcode` varchar(20) DEFAULT NULL COMMENT '推荐人',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `source` varchar(50) DEFAULT NULL COMMENT '平台来源,如: web,app',
  `market_source` varchar(50) DEFAULT NULL COMMENT 'app应用市场来源',
  `ad_source` varchar(50) DEFAULT NULL COMMENT '广告来源',
  `activity_source` varchar(50) DEFAULT NULL COMMENT '活动来源',
  `extra_channel_id` int(10) unsigned DEFAULT '0' COMMENT '推广extra渠道ID',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` char(1) DEFAULT '0' COMMENT '用户状态(-1 删除 0:正常 1:冻结 2：注销)',
  `yun_acct_time` date DEFAULT NULL COMMENT '开通云油账号时间',
  PRIMARY KEY (`member_id`),
  KEY `index_name` (`mobile`,`register_time`,`pcode`,`wechat_opid`),
  KEY `member_pcode_index` (`pcode`),
  KEY `member_shopbn_index` (`shop_bn`),
  KEY `index_register_time_index` (`register_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员账号';

DROP TABLE IF EXISTS `bwoil_member_bank`;
CREATE TABLE IF NOT EXISTS `bwoil_member_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `bank_name` varchar(64) NOT NULL COMMENT '银行名称',
  `bank_region` varchar(255) DEFAULT NULL COMMENT '支行地区',
  `bank_sub_name` varchar(150) DEFAULT NULL COMMENT '支行名称',
  `bank_bn` varchar(10) DEFAULT NULL COMMENT '银行编号(使用银联的标准)',
  `bank_no` varchar(200) NOT NULL COMMENT '银行卡号',
  `bind_id` varchar(40) DEFAULT NULL COMMENT '宝付绑定ID(1实名认证)',
  `protocol_no` varchar(40) DEFAULT NULL COMMENT '宝付签约唯一码(2协议认证)',
  `baofu_type` char(1) DEFAULT NULL COMMENT '宝付认证类型1实名认证2协议认证',
  `channel_code` varchar(20) DEFAULT NULL COMMENT '绑定渠道',
  `def_flag` char(1) NOT NULL DEFAULT '0' COMMENT '默认提现卡号(0:否 1:是)',
  `quick_pay` char(2) DEFAULT NULL COMMENT '是否支持快捷 0:不支持 1:支持',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '状态(-1:删除 0:正常 1:预绑定)',
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行账号';

DROP TABLE IF EXISTS `bwoil_member_bind_tag`;
CREATE TABLE IF NOT EXISTS `bwoil_member_bind_tag` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tag_type` varchar(32) NOT NULL DEFAULT 'weixin' COMMENT '绑定平台',
  `tag_id` varchar(100) DEFAULT NULL COMMENT '绑定平台唯一ID',
  `tag_name` varchar(100) DEFAULT NULL COMMENT '绑定平台的昵称',
  `member_id` int(11) NOT NULL COMMENT '绑定会员',
  `disabled` varchar(5) DEFAULT 'false',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绑定第三方平台';

DROP TABLE IF EXISTS `bwoil_member_color`;
CREATE TABLE IF NOT EXISTS `bwoil_member_color` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` int(15) NOT NULL COMMENT '会员id',
  `openid` varchar(255) DEFAULT NULL COMMENT '第三方会员标识',
  `mobile` varchar(15) DEFAULT NULL COMMENT '第三方手机号',
  `refuelChannel` varchar(15) NOT NULL COMMENT '渠道',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `status` char(1) DEFAULT '0' COMMENT '用户状态( -1 删除 1:冻结 0:正常 2：注销)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='彩生活会员';

DROP TABLE IF EXISTS `bwoil_member_feedback`;
CREATE TABLE IF NOT EXISTS `bwoil_member_feedback` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int(10) NOT NULL COMMENT '获赠人ID',
  `moblie` varchar(30) DEFAULT NULL COMMENT '用户名',
  `images` longtext COMMENT '反馈图片',
  `content` longtext COMMENT '反馈内容',
  `admin_content` longtext COMMENT '处理意见',
  `create_time` int(10) unsigned NOT NULL COMMENT '反馈时间',
  `operation_time` int(10) unsigned DEFAULT NULL COMMENT '处理时间',
  `status` enum('0','1','-1') DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `ind_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈';

DROP TABLE IF EXISTS `bwoil_member_gasstation_member`;
CREATE TABLE IF NOT EXISTS `bwoil_member_gasstation_member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '会员用户名',
  `weixin_nc` varchar(200) DEFAULT '' COMMENT '参与人微信账号昵称',
  `weixin_img` varchar(200) DEFAULT '' COMMENT '微信头像',
  `openid` varchar(100) DEFAULT '' COMMENT '微信公众号用户openid',
  `mini_openid` varchar(100) DEFAULT '' COMMENT '小程序openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ind_member_id` (`id`),
  KEY `ind_openid` (`openid`),
  KEY `ind_mini_openid` (`mini_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_member_jpush_device_binds`;
CREATE TABLE IF NOT EXISTS `bwoil_member_jpush_device_binds` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int(8) DEFAULT '0' COMMENT '会员账号',
  `shop_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '推送手机号',
  `registration_id` varchar(64) NOT NULL COMMENT '推送注册ID',
  `platform` varchar(20) DEFAULT NULL COMMENT '平台',
  `access_times` int(10) DEFAULT '0' COMMENT '访问次数',
  `tag` varchar(32) DEFAULT NULL COMMENT '推送标识',
  `memo` varchar(32) DEFAULT NULL COMMENT '其它',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` char(1) DEFAULT '0' COMMENT '状态(-1:删除 0:正常)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ind_registration_id` (`registration_id`),
  KEY `ind_member_id` (`member_id`) USING BTREE,
  KEY `ind_bind_shop_bn` (`shop_bn`) USING BTREE,
  KEY `ind_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Jpush绑定';

DROP TABLE IF EXISTS `bwoil_member_log`;
CREATE TABLE IF NOT EXISTS `bwoil_member_log` (
  `log_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  `member_account` varchar(50) DEFAULT NULL COMMENT '会员账号',
  `object_type` varchar(20) NOT NULL COMMENT '日志类型 login(登录）、info(会员信息）、identity（实名认证）、trade_pwd（交易密码）、login_pwd（登录密码）、register（注册）',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP',
  `in_ip` varchar(20) DEFAULT NULL COMMENT '内网IP',
  `mac` varchar(100) DEFAULT NULL COMMENT 'MAC地址',
  `terminal` varchar(100) DEFAULT NULL COMMENT '终端类型',
  `log_memo` varchar(200) NOT NULL COMMENT '日志内容',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(32) DEFAULT NULL COMMENT '操作人姓名',
  `log_data` longtext COMMENT '日志数据',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` char(1) NOT NULL COMMENT '状态:0：成功，1：失败',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录';

DROP TABLE IF EXISTS `bwoil_member_msg`;
CREATE TABLE IF NOT EXISTS `bwoil_member_msg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `msg_id` varchar(50) NOT NULL COMMENT '消息ID',
  `sender` bigint(20) unsigned NOT NULL COMMENT '发送者用户ID',
  `receiver` bigint(20) unsigned NOT NULL COMMENT '接收者用户ID',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `receive_time` timestamp NULL DEFAULT NULL COMMENT '接收时间',
  `msg_title` varchar(1000) NOT NULL COMMENT '标题',
  `msg_content` text NOT NULL COMMENT '内容',
  `msg_type` varchar(50) NOT NULL COMMENT '类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_member_msg_receiver` (`receiver`,`msg_type`,`send_time`),
  KEY `index_member_msg_msg_id` (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员消息表';

DROP TABLE IF EXISTS `bwoil_member_msg_log`;
CREATE TABLE IF NOT EXISTS `bwoil_member_msg_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(50) NOT NULL COMMENT '消息ID',
  `msg_type` varchar(50) NOT NULL COMMENT '消息类型',
  `sender` bigint(20) unsigned NOT NULL COMMENT '发送者',
  `receiver` bigint(20) unsigned NOT NULL COMMENT '接收者',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `read_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '读取时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_name` (`msg_id`,`receiver`,`msg_type`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员消息日志表';

DROP TABLE IF EXISTS `bwoil_member_real_name_log`;
CREATE TABLE IF NOT EXISTS `bwoil_member_real_name_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int(10) unsigned DEFAULT NULL COMMENT '修改会员ID',
  `merchant_name` varchar(50) NOT NULL COMMENT '渠道名称',
  `resource_content` varchar(1000) DEFAULT NULL COMMENT '信息',
  `back_code` varchar(50) DEFAULT NULL COMMENT '返回码',
  `bcak_info` varchar(1000) DEFAULT NULL COMMENT '返回原因',
  `auth_type` int(2) NOT NULL COMMENT '认证类别，1实名认证/2银行卡查询/3绑定银行卡/4解绑银行卡',
  `auth_succ` int(2) NOT NULL COMMENT '认证成功标识，1成功/0失败',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员实名认证日志';

DROP TABLE IF EXISTS `bwoil_member_real_name_stat`;
CREATE TABLE IF NOT EXISTS `bwoil_member_real_name_stat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `merchant_id` varchar(50) NOT NULL COMMENT '商户ID',
  `auth_type` tinyint(1) NOT NULL COMMENT '认证类别，1实名认证/2银行卡查询',
  `succ_num` int(11) NOT NULL DEFAULT '0' COMMENT '成功次数',
  `fail_num` int(11) NOT NULL DEFAULT '0' COMMENT '失败次数',
  `total_num` int(11) NOT NULL DEFAULT '0' COMMENT '总次数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员实名认证统计表';

DROP TABLE IF EXISTS `bwoil_member_shanghai_bos_bills`;
CREATE TABLE IF NOT EXISTS `bwoil_member_shanghai_bos_bills` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `member_id` int(11) unsigned DEFAULT NULL COMMENT '会员ID',
  `login_account` varchar(50) NOT NULL COMMENT '手机号码',
  `true_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `card_no` varchar(100) DEFAULT NULL COMMENT '联名卡卡号',
  `is_discount` varchar(5) DEFAULT '0' COMMENT '是否可折扣',
  `bill_type` varchar(5) DEFAULT '2' COMMENT '卡类型',
  `bill_firstmonth` varchar(5) DEFAULT '1' COMMENT '首次绑定',
  `bill_month` int(2) DEFAULT NULL COMMENT '帐单月份',
  `bill_ecif` varchar(20) DEFAULT '' COMMENT 'ECIF客户号',
  `bill_source` varchar(5) DEFAULT '1' COMMENT '数据来源',
  `last_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ind_member_id` (`member_id`),
  KEY `ind_login_account` (`login_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上海银行联名卡帐单表';

DROP TABLE IF EXISTS `bwoil_member_shanghai_sign`;
CREATE TABLE IF NOT EXISTS `bwoil_member_shanghai_sign` (
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `name` varchar(50) DEFAULT NULL COMMENT '上海银行开户人',
  `identify_id` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `card_no` varchar(50) DEFAULT NULL COMMENT '上海银行卡号',
  `card_type` varchar(10) DEFAULT NULL COMMENT '签约银行卡类型',
  `sign_code` varchar(50) DEFAULT NULL COMMENT '快捷支付协议号',
  `acct_lv` varchar(10) DEFAULT NULL COMMENT '账户等级',
  `sign_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签约日期',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改日期',
  UNIQUE KEY `index_member_id` (`member_id`) USING BTREE,
  UNIQUE KEY `index_card_no` (`card_no`) USING BTREE,
  UNIQUE KEY `index_sign_code` (`sign_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上海银行开户信息';

DROP TABLE IF EXISTS `bwoil_operation_activity_redpacket`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_activity_redpacket` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_id` varchar(32) NOT NULL COMMENT '分享订单ID',
  `member_id` int(10) DEFAULT NULL COMMENT '会员ID',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `cpns_desc` varchar(255) DEFAULT NULL COMMENT '红包描述',
  `cpns_info` varchar(255) NOT NULL COMMENT '优惠券信息',
  `name` varchar(255) NOT NULL COMMENT '红包名称',
  `amount` decimal(20,3) NOT NULL COMMENT '红包面额(元)',
  `get_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '红包领取时间',
  `account_time` timestamp NULL DEFAULT NULL COMMENT '券到帐时间',
  `dead_time` timestamp NULL DEFAULT NULL COMMENT '红包有效截止日期',
  `reg_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `is_auth` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否授权: 0(未授权) 1(已授权)',
  `openid` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `weixin_nc` varchar(200) DEFAULT '' COMMENT '微信账号昵称',
  `weixin_img` varchar(200) DEFAULT '' COMMENT '微信头像',
  `send_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '发放状态: 0(未发放) 1(已发放)',
  PRIMARY KEY (`id`),
  KEY `ind_member_id` (`member_id`),
  KEY `ind_openid` (`openid`),
  KEY `ind_get_time` (`get_time`),
  KEY `ind_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包分享领取列表';

DROP TABLE IF EXISTS `bwoil_operation_article_indexs`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_article_indexs` (
  `article_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(200) NOT NULL COMMENT '文章标题',
  `platform` varchar(10) NOT NULL DEFAULT 'pc' COMMENT '客户端(pc,wap)',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '文章类型(1普通文章,2单独页,3自定义页面) ',
  `node_id` mediumint(8) unsigned NOT NULL COMMENT '节点',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `content` longtext COMMENT '文章内容',
  `content_img` varchar(255) DEFAULT '""' COMMENT '文章配图',
  `seo_title` varchar(100) DEFAULT NULL COMMENT 'SEO标题',
  `seo_description` mediumtext COMMENT 'SEO简介',
  `seo_keywords` varchar(200) DEFAULT NULL COMMENT 'SEO关键字',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（无需精确到秒）',
  `uptime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（精确到秒）',
  `ifpub` char(1) NOT NULL DEFAULT '0' COMMENT '发布(1,0)',
  `pubTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `pv` int(10) unsigned DEFAULT '0' COMMENT 'pageview',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '(-1,0)',
  PRIMARY KEY (`article_id`),
  KEY `ind_node_id` (`node_id`),
  KEY `ind_ifpub` (`ifpub`),
  KEY `ind_pv` (`pv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章内容';

DROP TABLE IF EXISTS `bwoil_operation_article_nodes`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_article_nodes` (
  `node_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `parent_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '父节点',
  `node_depth` tinyint(1) NOT NULL DEFAULT '0' COMMENT '节点深度',
  `node_name` varchar(50) NOT NULL DEFAULT '' COMMENT '节点名称',
  `node_pagename` varchar(50) DEFAULT NULL COMMENT '节点页面名',
  `node_path` varchar(200) DEFAULT NULL COMMENT '节点路径',
  `has_children` char(1) DEFAULT '0' COMMENT '是否有孩子节点0无1有',
  `seo_title` varchar(100) DEFAULT NULL COMMENT 'SEO标题',
  `seo_description` mediumtext COMMENT 'SEO简介',
  `seo_keywords` varchar(200) DEFAULT NULL COMMENT 'SEO关键字',
  `ifpub` char(1) NOT NULL DEFAULT '0' COMMENT '发布 0 不发 0 发布',
  `ordernum` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `homepage` char(1) DEFAULT '0' COMMENT '主页',
  `content` longtext COMMENT '文章内容',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '-1删除 0正常',
  `uptime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`node_id`),
  KEY `ind_ordernum` (`ordernum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章节点';

DROP TABLE IF EXISTS `bwoil_operation_auth`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_auth` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `plat` varchar(50) DEFAULT NULL COMMENT '应用市场平台',
  `version` varchar(20) NOT NULL COMMENT '版本号v',
  `auth` varchar(100) NOT NULL COMMENT '版本md5值',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` char(2) DEFAULT '0' COMMENT '状态值(-1 删除 0 正常)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app版本';

DROP TABLE IF EXISTS `bwoil_operation_auth_dict`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_auth_dict` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `plat` varchar(50) DEFAULT NULL COMMENT '应用市场平台',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` char(2) DEFAULT '0' COMMENT '状态值(-1 删除 0 正常)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `plat` (`plat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app使用市场数据';

DROP TABLE IF EXISTS `bwoil_operation_base_member`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_base_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_member_id` int(11) NOT NULL COMMENT '被推荐人的id',
  `buyer_member_name` varchar(20) DEFAULT NULL COMMENT '被推荐人名称',
  `buyer_shop_bn` varchar(255) DEFAULT NULL COMMENT '被推广会员编号',
  `buyer_mobile` varchar(255) DEFAULT NULL COMMENT '被推广账号',
  `buyer_register_time` timestamp NULL DEFAULT NULL COMMENT '被推广会员注册时间',
  `buyer_source` varchar(255) DEFAULT NULL COMMENT '被推广人注册来源',
  `buyer_is_truename` int(1) DEFAULT '0' COMMENT '是否实名 0：未实名，1：已实名',
  `buyer_auth_time` datetime DEFAULT NULL COMMENT '认证时间',
  `promotion_member_id` int(11) DEFAULT NULL COMMENT '推荐人的id',
  `promotion_member_name` varchar(20) DEFAULT NULL COMMENT '推荐人名称',
  `promotion_shop_bn` varchar(255) DEFAULT NULL COMMENT '推广会员编号',
  `promotion_mobile` varchar(255) DEFAULT NULL COMMENT '推广账号',
  `promotion_register_time` timestamp NULL DEFAULT NULL COMMENT '推广会员注册时间',
  `channel_department_id` int(11) DEFAULT '0' COMMENT '部门id',
  `channel_department_parent_id` int(11) DEFAULT '0' COMMENT '部门父类id',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营会员基础表';

DROP TABLE IF EXISTS `bwoil_operation_base_order`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_base_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) DEFAULT NULL COMMENT '订单号',
  `member_id` mediumint(8) unsigned DEFAULT NULL COMMENT '会员用户名',
  `mobile` varchar(15) DEFAULT NULL COMMENT '会员手机号',
  `product_id` mediumint(8) NOT NULL COMMENT '货品ID',
  `product_name` varchar(200) DEFAULT NULL COMMENT '货品名称',
  `region_id` int(10) DEFAULT NULL COMMENT '地区ID',
  `region_name` varchar(32) DEFAULT NULL COMMENT '地区名称',
  `oil_id` mediumint(8) DEFAULT NULL COMMENT '油品ID',
  `oil_name` varchar(20) DEFAULT NULL COMMENT '油品名称',
  `oil_price` decimal(20,2) DEFAULT NULL COMMENT '最近一条油品价格',
  `order_status` varchar(15) NOT NULL DEFAULT 'active' COMMENT '订单状态:active,dead,finish',
  `pay_status` char(1) NOT NULL DEFAULT '0' COMMENT '付款状态:0,1,2,3,4,5 ',
  `pay_way` varchar(15) NOT NULL DEFAULT '0' COMMENT '支付方式',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `is_protect` char(1) NOT NULL DEFAULT 'N' COMMENT '是否还有保价费:Y,N',
  `cost_protect` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '保价费',
  `total_cnt` int(8) DEFAULT NULL COMMENT '购买张数',
  `term` int(8) NOT NULL COMMENT '期数',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `total_litre` decimal(20,2) DEFAULT '0.00' COMMENT '升数',
  `real_pay_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单实际支付金额',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单现金券减免',
  `goil_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单油箱抵用金额',
  `reduce_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单满减金额',
  `discount_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单折扣金额',
  `redpacket_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '红包折扣金额',
  `cps_ids` varchar(1000) DEFAULT NULL COMMENT '优惠券id array[]',
  `channel_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '推广渠道ID',
  `ad_source` varchar(100) DEFAULT NULL COMMENT '广告来源',
  `mark_type` varchar(2) NOT NULL DEFAULT 'b1' COMMENT '订单备注图标',
  `mark_text` varchar(2000) DEFAULT NULL COMMENT '订单备注',
  `pay_no` varchar(32) DEFAULT NULL COMMENT '支付单号',
  `order_refer_no` varchar(32) DEFAULT NULL COMMENT '第三方订单号',
  `refer_notice` char(1) DEFAULT 'N' COMMENT '是否通知第三方:Y,N',
  `source` varchar(10) DEFAULT 'pc' COMMENT '平台来源:pc,app,wap,weixin',
  `devinfo` varchar(100) DEFAULT NULL COMMENT '设备信息',
  `linepayment` varchar(20) DEFAULT NULL COMMENT '线下交易号',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `channel_department_id` int(11) DEFAULT '0' COMMENT '部门id',
  `channel_department_parent_id` int(11) DEFAULT '0' COMMENT '部门父类id',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `product_category` varchar(100) DEFAULT NULL COMMENT '商品类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营订单基础表';

DROP TABLE IF EXISTS `bwoil_operation_brand_endorsement`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_brand_endorsement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `home_title` varchar(255) DEFAULT NULL COMMENT '首页标题',
  `main_title` varchar(255) DEFAULT NULL COMMENT '主标题',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `list_title` varchar(255) DEFAULT NULL COMMENT '列表标题',
  `picture_path` varchar(255) DEFAULT NULL COMMENT '缩略图(id)',
  `point` varchar(255) DEFAULT NULL COMMENT '埋点',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序列',
  `content` varchar(10000) DEFAULT NULL COMMENT '富文本编辑器中的具体内容',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '-1删除, 0正常',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌背书相关';

DROP TABLE IF EXISTS `bwoil_operation_cashcoupons`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_cashcoupons` (
  `cpns_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '现金券ID',
  `cpns_bn` varchar(50) NOT NULL COMMENT '现金券编号',
  `cpn_id` int(11) NOT NULL COMMENT '现金券活动ID',
  `cpn_name` varchar(200) NOT NULL COMMENT '现金券活动名称',
  `cpns_money` decimal(10,2) NOT NULL COMMENT '现金券面额',
  `cpns_member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `cpns_moblie` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `cpns_start_date` int(10) unsigned NOT NULL COMMENT '现金券使用开始时间',
  `cpns_end_date` int(10) unsigned NOT NULL COMMENT '现金券使用结束时间',
  `cpns_source` varchar(200) DEFAULT NULL COMMENT '现金券来源',
  `cpns_pcode` varchar(200) DEFAULT NULL COMMENT '推广码',
  `cpns_memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `cpns_give_mode` varchar(20) NOT NULL COMMENT '赠送方式',
  `cpns_bind_date` int(10) unsigned DEFAULT NULL COMMENT '绑定时间',
  `cpns_bind_status` tinyint(4) DEFAULT NULL COMMENT '绑定状态(0:未绑定 1:已绑定)',
  `cpns_rules` varchar(2000) DEFAULT NULL COMMENT '现金券使用规则',
  `cpns_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '现金券状态(0: 1: 2: 3:) 0=未使用 1=已使用 2=已过期 3=已作废',
  `cpns_date` int(10) unsigned DEFAULT NULL COMMENT '现金券使用时间',
  `cpns_order_id` varchar(20) DEFAULT NULL COMMENT '现金券使用订单ID',
  `cpns_max_card_num` int(10) NOT NULL DEFAULT '0' COMMENT '适用最大卡数量',
  `is_notice` tinyint(4) NOT NULL DEFAULT '0' COMMENT '过期是否已通知(0:未通知 1:已通知)',
  `cpns_type` varchar(15) NOT NULL COMMENT '优惠券类型',
  `recommended_member_id` int(11) DEFAULT NULL COMMENT '被推荐人账号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0:正常 -1:删除 )',
  `max_rule_type_num` int(10) NOT NULL DEFAULT '0' COMMENT '该优惠活动支持最大的产品类型数',
  `min_rule_periods_num` int(10) NOT NULL DEFAULT '0' COMMENT '该优惠活动支持最小的产品期数',
  `goods_name` varchar(255) NOT NULL DEFAULT '' COMMENT '购买的商品名称',
  `order_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `pay_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '实付金额',
  `term` varchar(20) DEFAULT NULL COMMENT '购买对应产品的期限',
  PRIMARY KEY (`cpns_id`),
  KEY `cpns_bn` (`cpns_bn`) USING BTREE,
  KEY `cpns_moblie` (`cpns_moblie`) USING BTREE,
  KEY `cpns_give_mode` (`cpns_give_mode`) USING BTREE,
  KEY `cpns_status` (`cpns_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户优惠券表';

DROP TABLE IF EXISTS `bwoil_operation_cashcoupon_activity`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_cashcoupon_activity` (
  `cpn_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cpn_bn` varchar(50) NOT NULL COMMENT '现金券活动编号',
  `cpn_name` varchar(200) NOT NULL COMMENT '现金券活动名称',
  `cpn_money` decimal(20,2) NOT NULL COMMENT '面额',
  `cpn_receive_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大领取数量 每人最大领取数量',
  `is_cpn_receive` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '设置每个用户能领取券的数量 0=不限制 1=限制',
  `cpn_receive_mnum` int(10) NOT NULL DEFAULT '0' COMMENT '可领取数量',
  `is_cpn_receive_m` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '设置该券能领取的总数量 0=不限制 1=限制',
  `cpn_start_date` int(10) unsigned DEFAULT NULL COMMENT '现金券使用开始时间',
  `cpn_end_date` int(10) unsigned DEFAULT NULL COMMENT '现金券使用结束时间',
  `valid_days` int(4) DEFAULT '0' COMMENT '有效天数',
  `cpn_desc` varchar(1000) DEFAULT NULL COMMENT '现金券活动描述 规则描述',
  `cpn_status` tinyint(1) NOT NULL COMMENT '优惠券状态 0停用 1启用',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 -1删除  0正常',
  `cpn_type` tinyint(1) NOT NULL COMMENT '优惠券类型 1=现金券 2=满减券',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_use_cash` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0不可叠加现金券 1可叠加现金券',
  `is_use_oil` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0不可叠加油包 1可叠加油包',
  `pay_order_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '以支付订单数',
  `pay_order_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '产生销售总量',
  `pay_discount_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '优惠券累计优惠金额',
  `max_rule_type_num` int(10) NOT NULL DEFAULT '0' COMMENT '该优惠活动支持最大的产品类型数',
  `min_rule_periods_num` int(10) NOT NULL DEFAULT '0' COMMENT '该优惠活动支持最小的产品期数',
  `cpn_receive_memnum` int(10) DEFAULT '0' COMMENT '会员已领取数量',
  `cpn_receive_sumnum` int(10) DEFAULT '0' COMMENT '已领取数量',
  `cpn_build_sumnum` int(10) DEFAULT '0' COMMENT '生成兑换码数量',
  `cpn_use_sumnum` int(10) DEFAULT '0' COMMENT '已使用数量',
  PRIMARY KEY (`cpn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='现金券活动表';

DROP TABLE IF EXISTS `bwoil_operation_cashcoupon_queue`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_cashcoupon_queue` (
  `queue_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '现金券赠送队列id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `queue_cpnname` varchar(2000) NOT NULL DEFAULT '' COMMENT '现金券活动名称',
  `queue_mobile` varchar(2000) DEFAULT '' COMMENT '未成功手机号码',
  `queue_type` tinyint(1) NOT NULL COMMENT '类型 1=会员 2=非会员',
  `queue_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0提交 1=执行中 2=已完成',
  `send_type` tinyint(1) NOT NULL COMMENT '发券类型 1=指定用户 2=所有注册用户 3=批量导入用户 4=指定条件用户',
  `send_mobile` varchar(2000) DEFAULT '' COMMENT '指定送券的手机号',
  `coupons` varchar(200) NOT NULL DEFAULT '' COMMENT '优惠券列表 json格式',
  PRIMARY KEY (`queue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赠送现金券消息记录表';

DROP TABLE IF EXISTS `bwoil_operation_cashcoupon_rule`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_cashcoupon_rule` (
  `rule_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '现金券活动规则ID',
  `cpn_id` int(10) NOT NULL COMMENT '现金券活动ID',
  `rule_type_bn` varchar(32) NOT NULL COMMENT '类型编号 对应商品类型表的类型',
  `rule_order_money` decimal(20,2) unsigned NOT NULL COMMENT '现金券活动规则订单金额',
  `rule_periods` int(10) unsigned NOT NULL COMMENT '期数',
  `rule_periods_length` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '期长',
  `rule_periods_unit` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '期长单位 1=天 2=月',
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='现金券活动规则';

DROP TABLE IF EXISTS `bwoil_operation_channel_coupon`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_channel_coupon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_bn` varchar(50) NOT NULL COMMENT '活动编号',
  `activity_name` varchar(30) NOT NULL COMMENT '活动名称',
  `new_first_cpns` varchar(255) NOT NULL COMMENT '新用户第一批送券',
  `starttime` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `endtime` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `object` int(2) NOT NULL DEFAULT '2' COMMENT '送券对象 0:新老用户 1:老用户 2:新用户',
  `is_enable` int(2) NOT NULL DEFAULT '0' COMMENT '是否启用',
  `disable_tip` varchar(80) DEFAULT '' COMMENT '活动禁用提示语',
  `not_suit_tip` varchar(80) DEFAULT '' COMMENT '不符合送券对象提示语',
  `not_enough_tip` varchar(80) DEFAULT '' COMMENT '优惠券已抢完提示语',
  `first_coupon_tip` varchar(80) DEFAULT '' COMMENT '领到第一批优惠券提示语',
  `had_get_tip` varchar(80) DEFAULT '' COMMENT '已领过券提示语',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 -1 删除 0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS `bwoil_operation_channel_deposit`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_channel_deposit` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `channel_name` varchar(50) NOT NULL COMMENT '渠道类型',
  `advance_source` varchar(200) NOT NULL COMMENT '预付款来源即pcode',
  `advance_district` varchar(200) NOT NULL COMMENT '代充金额区间（多个用 | 隔开，单位万）',
  `cpnc_discount` varchar(200) DEFAULT NULL COMMENT '中石油卡折扣（多个用 | 隔开）',
  `sinopec_discount` varchar(200) DEFAULT NULL COMMENT '中石化卡折扣（多个用 | 隔开）',
  `sum_amount` decimal(10,2) DEFAULT '0.00' COMMENT '总的订单总额',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '状态 0 正常 -1 删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道预付款扣款配置表';

DROP TABLE IF EXISTS `bwoil_operation_command_activity`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_command_activity` (
  `activity_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `activity_bn` varchar(50) NOT NULL COMMENT '活动编号',
  `activity_name` varchar(200) NOT NULL COMMENT '活动名称',
  `activity_desc` varchar(500) DEFAULT NULL COMMENT '活动说明',
  `activity_word` varchar(200) NOT NULL COMMENT '领券口令',
  `activity_total_num` int(10) NOT NULL DEFAULT '0' COMMENT '领取总量',
  `activity_join_num` int(10) DEFAULT '0' COMMENT '已领取总量',
  `activity_coupons` varchar(500) NOT NULL COMMENT '赠送现金券',
  `activity_status` int(11) NOT NULL DEFAULT '0' COMMENT '活动状态',
  `activity_start_date` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `activity_end_date` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0=正常 -1=删除',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='口令优惠券活动表';

DROP TABLE IF EXISTS `bwoil_operation_command_activity_join`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_command_activity_join` (
  `join_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '参与ID',
  `activity_id` int(10) NOT NULL COMMENT '参与活动ID',
  `member_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '会员账号',
  `give_coupon_ids` varchar(260) NOT NULL COMMENT '赠送优惠券编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`join_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领取口令优惠券活动表';

DROP TABLE IF EXISTS `bwoil_operation_common_setting`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_common_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mkey` varchar(50) DEFAULT NULL COMMENT '取值key',
  `val` longtext COMMENT '值',
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` char(2) DEFAULT '0' COMMENT '-1删除 0正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`mkey`) COMMENT '索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用配置';

DROP TABLE IF EXISTS `bwoil_operation_feedback`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_feedback` (
  `feedback_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `member_id` int(10) NOT NULL COMMENT '获赠人ID',
  `mobile` varchar(30) DEFAULT NULL,
  `images` longtext COMMENT '反馈图片',
  `content` longtext COMMENT '反馈内容',
  `admin_content` longtext COMMENT '处理意见',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间',
  `opt_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '处理时间',
  `status` char(2) DEFAULT '0' COMMENT '状态(''-1''删除,''0''未处理,''1''已处理)',
  PRIMARY KEY (`feedback_id`),
  KEY `ind_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服';

DROP TABLE IF EXISTS `bwoil_operation_friendly_link`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_friendly_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `link_name` varchar(50) NOT NULL COMMENT '链接名称',
  `link_addr` varchar(1000) NOT NULL COMMENT '链接地址',
  `link_image` varchar(100) DEFAULT NULL COMMENT '链接图片',
  `sort_num` int(3) DEFAULT '0' COMMENT '排序号',
  `is_hidden` int(1) DEFAULT '0' COMMENT '是否隐藏(0 不隐藏 1 隐藏) ',
  `status` int(1) DEFAULT '0' COMMENT '状态 0 正常 -1 删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation` (
  `station_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '加油站ID',
  `station_bn` varchar(20) NOT NULL COMMENT '加油站ERP编号',
  `station_name` varchar(64) NOT NULL COMMENT '加油站名称',
  `area` varchar(255) DEFAULT NULL COMMENT '加油站所在地区',
  `addr` varchar(128) DEFAULT NULL COMMENT '加油站地址',
  `contact` varchar(32) DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `phone` varchar(32) DEFAULT NULL COMMENT '固定电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `device_version` varchar(255) DEFAULT NULL COMMENT '设备SN版本',
  `device_sn` varchar(255) DEFAULT NULL COMMENT '设备SN编号码',
  `device_updatetime` varchar(255) DEFAULT NULL COMMENT '设备SN更新时间',
  `device_status` varchar(255) DEFAULT NULL COMMENT '设备SN强制升级标志',
  `printer_sn` varchar(100) DEFAULT '' COMMENT '加油站打印机编号',
  `gas_key` varchar(100) DEFAULT '' COMMENT '加油站打印机的key值',
  `allow_cprint` varchar(1) DEFAULT '1' COMMENT '是否允许云端打印：1:允许，2:不允许',
  `allow_pay_station` varchar(1) DEFAULT '1' COMMENT '是否开通直付功能：1:开通默认，2:不开通',
  `weixin_discount` decimal(10,2) DEFAULT '0.00' COMMENT '微信折扣',
  `status` enum('false','true') DEFAULT 'true' COMMENT '是否启用加油 状态true启用, false不启用',
  `quotaAmountPerDay` decimal(10,2) DEFAULT '0.00' COMMENT '每日打款限额',
  `image_id` varchar(255) DEFAULT NULL,
  `member_id` int(8) unsigned DEFAULT NULL COMMENT '会员ID',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '开户银行',
  `bank_area` varchar(150) DEFAULT NULL COMMENT '开户支行',
  `bank_no` varchar(50) DEFAULT NULL COMMENT '网点编号',
  `true_name` varchar(52) DEFAULT NULL COMMENT '账户名称',
  `bank_account` varchar(32) DEFAULT NULL COMMENT '银行帐号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `lastmodify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_parent` int(1) DEFAULT '0' COMMENT '是否有子加油站, 0没有, 1有',
  `child_station` varchar(255) DEFAULT '' COMMENT '下属子油站',
  `parent_id` int(1) DEFAULT '0' COMMENT '父级加油站ID',
  `feeRate` varchar(10) DEFAULT '0' COMMENT '手续费率',
  `remittanceType` enum('REALTIME','FULFILL_TIME') DEFAULT 'REALTIME' COMMENT '结算打款周期: 默认实时结算REALTIME, 每天结算FULFILL_TIME',
  `fee_pay_method` varchar(1) DEFAULT '1' COMMENT '手续费-结算方式  默认1每笔结算, 2线下结算',
  `agreement_pay_method` varchar(1) DEFAULT '1' COMMENT '协议折扣-结算方式  默认1每笔结算, 2线下结算',
  `remittancePeriod` varchar(255) DEFAULT NULL COMMENT '每天结算打款时间',
  `longitude` decimal(10,5) DEFAULT '0.00000' COMMENT '经度坐标',
  `latitude` decimal(10,5) DEFAULT '0.00000' COMMENT '纬度坐标',
  `company_id` int(10) unsigned DEFAULT NULL COMMENT '品牌公司id',
  `company_name` varchar(200) DEFAULT NULL COMMENT '品牌公司名称',
  PRIMARY KEY (`station_id`),
  KEY `ind_email` (`email`) USING BTREE,
  KEY `ind_station_bn` (`station_bn`) USING BTREE,
  KEY `ind_station_name` (`station_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站的相关信息(对应明细gasstation_coupons表)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_activity`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `activity_name` varchar(64) NOT NULL DEFAULT '' COMMENT '活动名称',
  `station_id` int(5) NOT NULL COMMENT '加油站ID',
  `channel_id` varchar(500) NOT NULL COMMENT '分销渠道json信息 {channel: id}',
  `modify_name` varchar(64) NOT NULL DEFAULT '' COMMENT '维护人姓名',
  `create_name` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `status` varchar(5) NOT NULL DEFAULT '2' COMMENT '状态,  0无效, 默认2待审核, 1生效, 3审核不通过',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `start_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '生效时间',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `start_hour` time NOT NULL COMMENT '开始时间',
  `end_hour` time NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `ind_activity_name` (`activity_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站活动相关信息(对应activity_item)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_activity_item`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_activity_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `activity_id` int(8) unsigned NOT NULL COMMENT '活动ID',
  `discount_id` int(2) NOT NULL DEFAULT '1' COMMENT '折扣形式, 值1升数折扣, 2金额优惠折扣',
  `oil_id` int(8) unsigned NOT NULL COMMENT '油品ID',
  `data` varchar(255) NOT NULL COMMENT '输入的数据',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ind_activity_id` (`activity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站活动的明细表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_agreement`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_agreement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `company_id` int(8) unsigned NOT NULL COMMENT '油站公司ID',
  `station_id` int(8) unsigned NOT NULL COMMENT '加油站ID',
  `modify_name` varchar(64) NOT NULL DEFAULT '' COMMENT '维护人姓名',
  `create_name` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `status` varchar(5) NOT NULL DEFAULT '2' COMMENT '状态 无效0, 默认2待审, 1生效, 3不通过',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `start_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '生效时间',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `ind_station_id` (`station_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站协议相关信息(对应明细oil_discount表)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_cash_info`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_cash_info` (
  `trade_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '交易ID',
  `gas_cash_bn` varchar(50) NOT NULL COMMENT '加油扣款编号',
  `invoice_bn` varchar(64) DEFAULT NULL COMMENT '发票号',
  `actual_litre` decimal(20,5) DEFAULT NULL COMMENT '实际加油升数',
  `actual_price` decimal(20,3) DEFAULT NULL COMMENT '实际加油每升单价',
  `actual_amount` decimal(20,3) NOT NULL COMMENT '实际加油金额',
  `member_id` mediumint(8) unsigned NOT NULL COMMENT '会员ID',
  `user_id` mediumint(8) unsigned DEFAULT NULL COMMENT '操作人',
  `operator_id` int(8) DEFAULT NULL COMMENT '操作员ID',
  `operator_name` varchar(50) DEFAULT NULL COMMENT '操作员',
  `oil_name` varchar(20) DEFAULT NULL COMMENT '油品名称',
  `gun_num` varchar(20) DEFAULT NULL COMMENT '枪号',
  `station_id` mediumint(8) unsigned NOT NULL COMMENT '加油站ID',
  `source` enum('pc','app','wap','weixin','applet','face') NOT NULL DEFAULT 'pc' COMMENT '来源',
  `order_type` enum('scan','fuel') NOT NULL DEFAULT 'fuel' COMMENT '加油类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '扣款时间',
  PRIMARY KEY (`trade_id`),
  KEY `ind_gas_cash_bn` (`gas_cash_bn`) USING BTREE,
  KEY `ind_member_id` (`member_id`) USING BTREE,
  KEY `ind_station_id` (`station_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站加油的扣款日志记录';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_company`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_company` (
  `company_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '品牌公司ID',
  `company_name` varchar(120) NOT NULL COMMENT '品牌公司名称',
  `company_contact` varchar(32) DEFAULT NULL COMMENT '品牌公司联系人',
  `company_tel` varchar(20) DEFAULT NULL COMMENT '品牌公司联系电话',
  `company_dess` text COMMENT '提示备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `lastmodify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `status` varchar(2) DEFAULT NULL COMMENT '-1删除, 0正常',
  PRIMARY KEY (`company_id`),
  KEY `ind_company_name` (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站品牌公司相关信息';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_coupons`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_coupons` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `channel_id` int(10) unsigned DEFAULT '0' COMMENT '渠道ID (停用了)',
  `station_id` int(10) unsigned NOT NULL COMMENT '加油站ID',
  `coupon_rate` decimal(7,2) DEFAULT NULL COMMENT '优惠比例',
  `start_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `end_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `isvalid` enum('false','true') NOT NULL DEFAULT 'true' COMMENT '是否有效, false无效, 默认true有效  优惠时间后就无效(或删除)',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ind_coupon_channel` (`channel_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站的优惠配置';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_discount_item`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_discount_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `discount_id` int(8) unsigned NOT NULL COMMENT '定升加油卡折扣规则ID',
  `oil_id` int(8) unsigned NOT NULL COMMENT '油品ID',
  `litre_limit` int(8) unsigned NOT NULL COMMENT '充值升数条件',
  `discount_money` decimal(20,2) NOT NULL COMMENT '优惠金额',
  PRIMARY KEY (`id`),
  KEY `ind_station_discount_rule_id` (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站品牌公司折扣规则的明细';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_discount_rule`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_discount_rule` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `modify_name` varchar(64) DEFAULT '' COMMENT '审核人姓名',
  `company_id` mediumint(8) unsigned NOT NULL COMMENT '品牌公司ID',
  `created_name` varchar(64) NOT NULL DEFAULT '' COMMENT '申请人姓名',
  `status` varchar(5) NOT NULL DEFAULT '0' COMMENT '状态: -10失效, 默认0待审核, 10启用',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `effective_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '生效时间',
  PRIMARY KEY (`id`),
  KEY `ind_station_discount_rule_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站品牌公司折扣规则信息(对应明细discount_item表)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_gun`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_gun` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `station_id` int(8) NOT NULL DEFAULT '0' COMMENT '加油站id',
  `oil_id` int(5) NOT NULL DEFAULT '0' COMMENT '石油品名 id',
  `gun_num` int(5) NOT NULL DEFAULT '0' COMMENT '加油枪号',
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站油品枪号对应表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_images`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_images` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `station_id` int(10) unsigned NOT NULL COMMENT '加油站ID',
  `name` varchar(200) DEFAULT NULL COMMENT '宣传名称',
  `image` varchar(200) DEFAULT NULL COMMENT '宣传图片',
  `equipment_sn` varchar(200) NOT NULL COMMENT '上传设备',
  `image_status` char(1) NOT NULL DEFAULT '1' COMMENT '图片状态  0失效  1有效',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '图片排序',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `ind_station_id` (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站快捷加油图片配置表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_manualbill`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_manualbill` (
  `bill_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_no` varchar(30) NOT NULL COMMENT '单号',
  `pay_bn` varchar(30) DEFAULT '' COMMENT '打款单号',
  `financial_type` enum('0','1') NOT NULL COMMENT '财务类型 0应收, 1应付''',
  `pay_type` enum('REALTIME','FULFILL_QUOTA','FULFILL_TIME') DEFAULT 'REALTIME' COMMENT '打款类型',
  `costs_name` varchar(64) NOT NULL DEFAULT '' COMMENT '费用类型',
  `station_name` varchar(64) NOT NULL DEFAULT '' COMMENT '客户名称',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '开户行',
  `bank_account` varchar(32) NOT NULL DEFAULT '' COMMENT '收款帐号',
  `station_id` mediumint(8) unsigned NOT NULL COMMENT '加油站ID',
  `costs_introductions` varchar(255) NOT NULL DEFAULT '' COMMENT '费用类型说明',
  `status` enum('success','ready','audit','pending','failed','void') NOT NULL DEFAULT 'audit' COMMENT '状态',
  `message` varchar(2000) DEFAULT '' COMMENT '打款处理信息',
  `complete_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`bill_id`),
  KEY `ind_pay_bn` (`pay_bn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='清结算手工账务列表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_manualbill_order`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_manualbill_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_id` varchar(30) NOT NULL COMMENT '订单号',
  `account_no` varchar(30) NOT NULL COMMENT '扣款订单号',
  `pay_bn` varchar(30) NOT NULL COMMENT '人工调账打款编号',
  `pay_type` enum('REALTIME','FULFILL_QUOTA','FULFILL_TIME') NOT NULL COMMENT '打款类型',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '该笔订单扣款金额',
  `refundAmount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '扣款订单金额',
  `status` enum('success','failed') NOT NULL DEFAULT 'success' COMMENT '打款状态',
  `message` varchar(50) DEFAULT '' COMMENT '打款处理信息',
  `complete_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ind_account_no` (`account_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='打款列表(加油站打款后回调保存的)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_manualtype`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_manualtype` (
  `type_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `serialno` bigint(32) unsigned NOT NULL DEFAULT '1' COMMENT '序号',
  `financial_type` enum('0','1') NOT NULL COMMENT '财务类型 0应收, 1应付',
  `costs_name` varchar(64) NOT NULL DEFAULT '' COMMENT '费用类型',
  `costs_introductions` varchar(64) NOT NULL DEFAULT '' COMMENT '费用类型说明',
  `status` enum('0','1') NOT NULL DEFAULT '1' COMMENT '是否有效 0无效, 1有效',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`type_id`),
  KEY `ind_financial_type` (`financial_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='清结算打款类型设置表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_manual_paybn`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_manual_paybn` (
  `clearing_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pay_bn` varchar(30) NOT NULL COMMENT '打款编号',
  `pay_type` enum('REALTIME','FULFILL_QUOTA','FULFILL_TIME') NOT NULL COMMENT '打款类型',
  `station_name` varchar(64) NOT NULL DEFAULT '' COMMENT '加油站名称',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '打款金额',
  `bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '开户银行',
  `bank_area` varchar(150) NOT NULL DEFAULT '' COMMENT '开户支行',
  `bank_account` varchar(32) NOT NULL DEFAULT '' COMMENT '银行帐号',
  `station_id` mediumint(8) unsigned NOT NULL COMMENT '加油站ID',
  `status` enum('success','failed','again') NOT NULL DEFAULT 'success' COMMENT '打款状态',
  `message` varchar(50) DEFAULT '' COMMENT '打款处理信息',
  `complete_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '处理时间',
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `type` enum('0','1','2','3','4') NOT NULL DEFAULT '2' COMMENT '款项类型 0应收, 1应付, 2加油, 3混合款, 4 充值付款',
  `is_type` enum('0','1') NOT NULL DEFAULT '0' COMMENT '企业清结算方式, 0清结算付款, 1企业付款',
  PRIMARY KEY (`clearing_id`),
  KEY `ind_pay_bn` (`pay_bn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='清结算的打款列表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_oil`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_oil` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `company_id` int(8) unsigned NOT NULL COMMENT '油站公司ID',
  `station_id` int(8) unsigned NOT NULL COMMENT '加油站ID',
  `oil_id` int(8) unsigned NOT NULL COMMENT '油品ID',
  `modify_name` varchar(64) DEFAULT '' COMMENT '维护人姓名',
  `create_name` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `status` varchar(5) NOT NULL DEFAULT '1' COMMENT '状态, 0停用, 默认1启用',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ind_station_id` (`station_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站油品的信息';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_oil_discount`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_oil_discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `agreement_id` mediumint(8) unsigned NOT NULL COMMENT '合作协议ID',
  `discount_id` varchar(2) NOT NULL COMMENT '折扣形式, 值1升数折扣, 2金额优惠折扣',
  `station_id` mediumint(8) unsigned NOT NULL COMMENT '加油站ID',
  `oil_id` mediumint(8) unsigned NOT NULL COMMENT '油品ID',
  `data` decimal(20,2) NOT NULL COMMENT '输入的数据',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ind_agreement_id` (`agreement_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站协议信息的明细';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_oil_price_rule`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_oil_price_rule` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `operator_id` int(10) NOT NULL COMMENT '创建人ID',
  `operator_name` varchar(20) NOT NULL COMMENT '创建人姓名',
  `audit_id` int(10) DEFAULT NULL COMMENT '审核人ID',
  `audit_name` varchar(255) DEFAULT NULL COMMENT '审核人姓名',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `goil_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '油价生效日期',
  `goil_sale_prices` varchar(1000) NOT NULL COMMENT '油品具体销售价格 json格式的 {油品名称: 对应油价}',
  `status` varchar(3) NOT NULL DEFAULT '0' COMMENT '状态 默认0待审核, 1启用, 2失效',
  `lastmodify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `company_id` int(8) unsigned NOT NULL COMMENT '品牌公司ID',
  PRIMARY KEY (`id`),
  KEY `ind_goil_date` (`goil_date`),
  KEY `ind_lastmodify` (`lastmodify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站品牌公司价格规则信息';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_quickset`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_quickset` (
  `quick_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `station_id` int(10) unsigned NOT NULL COMMENT '????վID',
  `premium_rate` decimal(5,2) DEFAULT NULL COMMENT 'Ա??????????',
  `premium_max` decimal(7,2) DEFAULT '0.00' COMMENT '???ʷⶥ????',
  `status` enum('0','1') NOT NULL DEFAULT '1' COMMENT '״̬',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '?޸?ʱ??',
  PRIMARY KEY (`quick_id`),
  KEY `ind_station_id` (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???ݼ??͵?????';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_refuel_coupon_rule`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_refuel_coupon_rule` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '规则序号',
  `station_id` int(8) unsigned NOT NULL COMMENT '加油站id',
  `station_name` varchar(30) NOT NULL DEFAULT '' COMMENT '加油站名称',
  `coupon_list` varchar(5000) NOT NULL COMMENT '优惠券列表',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '可用起始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '可用结束时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `modify_username` varchar(20) DEFAULT NULL COMMENT '维护人姓名',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `status` enum('valid','invalid','audit') DEFAULT 'audit' COMMENT '状态, 默认audit待审核, ''invalid失效, ''valid启用',
  PRIMARY KEY (`id`),
  KEY `ind_created_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站的赠券规则';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_refuel_log`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_refuel_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID，自增主键',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `station_id` mediumint(8) unsigned NOT NULL COMMENT '加油站名称',
  `gun_id` int(11) NOT NULL COMMENT '油枪ID',
  `gun` varchar(50) DEFAULT NULL COMMENT '油枪号',
  `operator_id` int(11) DEFAULT NULL COMMENT '收银员ID',
  `log_data` varchar(600) DEFAULT NULL COMMENT '日志内容',
  `is_success` enum('true','false') NOT NULL COMMENT '是否成功',
  `refuel_type` enum('amount','litre') DEFAULT 'amount' COMMENT '加油类型',
  `refuel_order_id` varchar(35) DEFAULT '0' COMMENT '???Ͷ???id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ind_station_id` (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_operation_gasstation_refuel_order`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_refuel_order` (
  `fuel_order_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` varchar(20) NOT NULL,
  `shift_id` int(10) DEFAULT NULL COMMENT '班次id',
  `station_id` mediumint(8) NOT NULL COMMENT '加油站id',
  `staff_id` mediumint(8) NOT NULL COMMENT '收银员ID',
  `yougong_id` varchar(8) DEFAULT '0' COMMENT '油工ID',
  `payment_id` varchar(50) NOT NULL COMMENT '交易单号b2c_member_advance.payment_id',
  `member_id` bigint(20) NOT NULL COMMENT '加油会员ID',
  `gun_num` varchar(20) NOT NULL COMMENT '枪号',
  `pay_money` decimal(20,2) NOT NULL COMMENT '付款金额',
  `invoice_number` varchar(50) DEFAULT NULL COMMENT '加油小票号码',
  `agreements_money` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '油品协议价',
  `oil_number` varchar(20) DEFAULT NULL COMMENT '品名',
  `activity_money` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '油品活动价',
  `oil_total` decimal(20,2) DEFAULT '0.00' COMMENT '油量（升）',
  `oil_price` decimal(20,2) DEFAULT '0.00' COMMENT '油价（元/升）',
  `sms_code` varchar(10) DEFAULT NULL COMMENT '短信交易码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `settlement` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否已对账,创建结算报表即已对账1, 回滚草稿则为未对账0',
  `print_status` varchar(1) NOT NULL DEFAULT '0' COMMENT '小票打印状态，  0为打印失败 1为打印成功',
  `bonus` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '小票打印奖励金额',
  `source` varchar(10) DEFAULT 'pc' COMMENT '来源 ''wap'',''app'',''weixin'',''pc'',''applet'',''face''',
  `type` enum('scan','fuel') DEFAULT 'fuel' COMMENT 'scan=>扫码,fuel=>免单',
  `paid_subscribers` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '实收用户金额',
  `discount_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '直付券金额',
  `paid_discount` char(11) NOT NULL DEFAULT '10' COMMENT '渠道折扣',
  `weixin_discount` char(11) NOT NULL DEFAULT '10' COMMENT '微信直付折扣',
  `clearing_type` char(11) NOT NULL DEFAULT 'erp' COMMENT '结算方式',
  `accounting_status` varchar(1) NOT NULL DEFAULT '0' COMMENT '核算状态 已核算1, 未核算0',
  `discount_text` varchar(255) NOT NULL DEFAULT '' COMMENT '优惠内容',
  `agreement_pay_method` varchar(1) NOT NULL DEFAULT '1' COMMENT '协议金额结算方式, 默认1每笔结算, 2线下结算',
  `fee_pay_method` varchar(1) NOT NULL DEFAULT '1' COMMENT '手续费金额结算方式, 默认1每笔结算, 2线下结算',
  PRIMARY KEY (`fuel_order_id`),
  KEY `indx_shift_id` (`shift_id`) USING BTREE,
  KEY `indx_station_id` (`station_id`) USING BTREE,
  KEY `indx_payment_id` (`payment_id`) USING BTREE,
  KEY `indx_staff_id` (`staff_id`) USING BTREE,
  KEY `indx_created` (`create_time`) USING BTREE,
  KEY `indx_settlement` (`settlement`) USING BTREE,
  KEY `ind_payment_id` (`payment_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站加油订单表(所有订单)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_refuel_payfailed`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_refuel_payfailed` (
  `payfailed_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_id` varchar(30) NOT NULL COMMENT '订单号',
  `pay_bn` varchar(30) NOT NULL COMMENT '打款编号',
  `pay_type` enum('REALTIME','FULFILL_QUOTA','FULFILL_TIME') NOT NULL COMMENT '打款类型',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '打款金额',
  `status` enum('success','failed','again') NOT NULL DEFAULT 'success' COMMENT '打款状态',
  `message` varchar(50) DEFAULT '' COMMENT '打款处理信息',
  `complete_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`payfailed_id`),
  KEY `ind_pay_bn` (`pay_bn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_operation_gasstation_refuel_paylog`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_refuel_paylog` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID，自增主键',
  `order_id` varchar(32) NOT NULL COMMENT '扫码加油订单号',
  `log_memo` varchar(600) NOT NULL COMMENT '日志内容',
  `log_behavior` enum('order','sync') NOT NULL DEFAULT 'order' COMMENT '日志类型',
  `log_data` longtext COMMENT '日志数据',
  `log_notice` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '发送通知',
  `operator_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_id`),
  KEY `ind_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站打款给油站的日志记录';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_refuel_record`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_refuel_record` (
  `record_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '订单编号',
  `member_id` mediumint(8) unsigned DEFAULT NULL COMMENT '会员用户名',
  `shop_bn` varchar(255) DEFAULT NULL COMMENT '会员编号',
  `pay_bn` varchar(30) NOT NULL DEFAULT '0' COMMENT '打款编号',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '加油总金额',
  `paid_subscribers` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '实收用户金额',
  `advence_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '支付预存款金额',
  `refuel_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '支付加油卡金额',
  `discount_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '直付券支付',
  `agreements_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '协议金额',
  `activity_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '活动优惠金额',
  `oil_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油站挂牌价(元/升)',
  `oil_total` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '加油升数',
  `pay_status` varchar(20) NOT NULL DEFAULT 'unpay' COMMENT '快捷加油订单状态 unpay payed cancel',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '订单状态, 0已提交, 1已验证,2 已过期, 3已出票',
  `station_id` int(8) unsigned NOT NULL COMMENT '油站ID',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '下单时间',
  `expired` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `mobilephone` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `confirm` timestamp NULL DEFAULT NULL COMMENT '验证时间',
  `confirm_code` varchar(10) DEFAULT NULL COMMENT '验证码',
  `operator_id` int(11) DEFAULT NULL COMMENT '验证操作人ID',
  `operator_name` varchar(50) DEFAULT NULL COMMENT '验证操作人',
  `gun` varchar(50) DEFAULT NULL COMMENT '油枪号',
  `oil_name` varchar(50) DEFAULT NULL COMMENT '油品名称',
  `oilers_id` int(11) DEFAULT '0' COMMENT '加油员工ID',
  `oilers_name` varchar(50) DEFAULT NULL COMMENT '加油员工用户名',
  `shift_id` int(11) DEFAULT NULL COMMENT '下单当前的班次ID',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `tax_company` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `source` varchar(10) DEFAULT 'pc' COMMENT '来源',
  `payment_id` varchar(30) DEFAULT NULL COMMENT '小票显示单号',
  `paymethod` varchar(10) DEFAULT 'integrated' COMMENT '支付方式',
  `effective` varchar(2) DEFAULT '1' COMMENT '订单是否有效, 0无效订单, 默认1有效订单',
  `paid_discount` varchar(10) DEFAULT '10' COMMENT '渠道折扣',
  `weixin_discount` varchar(10) DEFAULT '10' COMMENT '微信折扣',
  `clearing_type` varchar(5) DEFAULT 'erp' COMMENT '结算方式, 默认bank银行即时转账, erp财务结算',
  `clearing_status` varchar(7) DEFAULT 'other' COMMENT '银行转账状态, 默认success支付成功, failed支付失败, 默认otherERP打款, pending处理中，ready准备中',
  `clearing_error_msg` varchar(1000) DEFAULT '' COMMENT '银行转账失败信息',
  `discount_text` varchar(255) DEFAULT '' COMMENT '优惠信息',
  `bank_pay_num` int(11) DEFAULT '0' COMMENT '银行转账重复次数',
  `allow_cprint` varchar(2) DEFAULT '1' COMMENT '是否需要打印小票, 默认1需要, 2不需要',
  `is_cprint` varchar(2) DEFAULT '0' COMMENT '是否已打印小票, 默认0否, 1已打印',
  `fee_pay_method` varchar(2) NOT NULL DEFAULT '0' COMMENT '手续费-结算方式  默认1每笔结算, 2线下结算',
  `agreement_pay_method` varchar(2) NOT NULL DEFAULT '0' COMMENT '协议折扣-结算方式  默认1每笔结算, 2线下结算',
  `fees_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '结算手续费',
  `clearing_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '结算金额',
  `refuel_type` varchar(10) DEFAULT 'amount' COMMENT '????????, litre????????, Ĭ??amount????????,quick ???ݼ???',
  `station_name` varchar(255) DEFAULT NULL COMMENT '加油站名称',
  `device_sn` varchar(255) DEFAULT NULL COMMENT '设备SN编号码',
  `cashier_name` varchar(255) DEFAULT NULL COMMENT '收银员用户名',
  `user_name` varchar(100) DEFAULT NULL COMMENT '?????û?????',
  PRIMARY KEY (`record_id`),
  KEY `ind_createtime` (`createtime`) USING BTREE,
  KEY `ind_member_id` (`member_id`) USING BTREE,
  KEY `ind_pay_bn` (`pay_bn`) USING BTREE,
  KEY `ind_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站扫码加油记录表(只保存扫码订单)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_remittance_log`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_remittance_log` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID，自增主键',
  `order_id` bigint(32) unsigned NOT NULL COMMENT '扫码加油订单号',
  `station_id` mediumint(8) unsigned NOT NULL COMMENT '加油站ID',
  `member_bn` varchar(50) NOT NULL COMMENT '会员编号',
  `status` enum('ready','pending','success','failed') NOT NULL DEFAULT 'ready' COMMENT '打款状态',
  `message` varchar(50) DEFAULT '' COMMENT '处理消息',
  `req_id` varchar(20) NOT NULL COMMENT '打款编号',
  `complete_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `ind_order_id` (`order_id`,`station_id`,`member_bn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站接收清结算系统打款回调日志(全纪录)';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_sale_oil_price`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_sale_oil_price` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `company_id` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '油站公司ID',
  `station_id` int(8) unsigned NOT NULL COMMENT '加油站ID',
  `oil_id` int(8) unsigned NOT NULL COMMENT '油品ID',
  `sale_oil_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油站挂牌价(元/升)',
  `discount` decimal(6,2) NOT NULL,
  `adjust_times` mediumint(8) unsigned DEFAULT '0' COMMENT '挂牌价一天内调整的次数(停用)',
  `price_range` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油价调整幅度(%)',
  `modify_name` varchar(64) NOT NULL DEFAULT '' COMMENT '维护人姓名',
  `create_name` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `status` varchar(3) NOT NULL DEFAULT '2' COMMENT '状态, 失效删除0, 默认待审核2, 审核ok启用1, 审核不通过3, 待提交4',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ind_station_id` (`station_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站油品挂牌价信息';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_settle_report`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_settle_report` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '结算报表id',
  `station_id` int(8) NOT NULL COMMENT '加油站id',
  `report_name` varchar(50) NOT NULL COMMENT '报表名称',
  `settlement_bn` varchar(30) NOT NULL DEFAULT '0' COMMENT '结算编码',
  `staff_count` int(4) NOT NULL DEFAULT '0' COMMENT '班次的总数',
  `amount` decimal(20,2) NOT NULL COMMENT '结算总金额',
  `bonus` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '小票打印奖励金额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '起始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `pay_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '付款时间,erp付款后记录',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '结算状态, 默认0草稿，1已提交，2已付款，3已确认',
  `shift_id` int(10) DEFAULT NULL COMMENT '班次Id',
  PRIMARY KEY (`id`),
  KEY `indx_created` (`create_time`) USING BTREE,
  KEY `indx_station_id` (`station_id`) USING BTREE,
  KEY `indx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站结算报表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_staff`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_staff` (
  `staff_id` int(8) NOT NULL AUTO_INCREMENT,
  `station_id` int(8) NOT NULL COMMENT '加油站id',
  `parent_member_id` int(8) NOT NULL COMMENT '油站管理员id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '登录名',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `true_name` varchar(50) NOT NULL COMMENT '真是姓名',
  `type_id` int(10) NOT NULL COMMENT '职位，1为收银员，2财务人员',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '用户状态1可用,0禁用',
  `lastmodify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `pwd_error` int(2) NOT NULL DEFAULT '0' COMMENT '输入密码错误次数，3次密码锁定',
  PRIMARY KEY (`staff_id`),
  KEY `indx_station_id` (`station_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站职员表';

DROP TABLE IF EXISTS `bwoil_operation_gasstation_staff_shift`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_gasstation_staff_shift` (
  `shift_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '班次id',
  `station_id` int(8) DEFAULT NULL COMMENT '加油站ID',
  `staff_id` int(8) DEFAULT NULL COMMENT '收银员id',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `settlement_id` int(10) DEFAULT '0' COMMENT '报表的ID',
  `total_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '结班总金额',
  `total_count` int(10) NOT NULL DEFAULT '0' COMMENT '结班总单数',
  PRIMARY KEY (`shift_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站职员班次表';

DROP TABLE IF EXISTS `bwoil_operation_home_find_setting`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_home_find_setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_type` tinyint(1) NOT NULL COMMENT '关联位置 1=会员服务 2=油价走势 3=云油服务 4=第三方服务',
  `platform_type` tinyint(1) NOT NULL COMMENT '关联展示平台 1=app 2=M站',
  `function_type` tinyint(1) NOT NULL COMMENT '关联功能\r\n1=链接\r\n2=精彩活动\r\n3=产品列表\r\n4=加油充值卡\r\n5=扫码加油\r\n6=兑换码\r\n7=油价分析\r\n8=油价走势\r\n9=加油站地图\r\n10=分享油包\r\n11=发票报销\r\n12=绑定节日卡',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `link_url` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `icon_id` varchar(100) DEFAULT NULL COMMENT 'iconId',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序 越小的越靠前',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_validate_identity` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否验证身份 0=不需要 1=需要',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态值-1删除 0正常',
  `hide_name` varchar(255) NOT NULL DEFAULT '' COMMENT '埋点名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发现页配置表';

DROP TABLE IF EXISTS `bwoil_operation_home_navigation`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_home_navigation` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `function_type` tinyint(2) NOT NULL COMMENT '关联功能 1=链接 2=精彩活动 3=产品列表 4=加油充值卡 5=扫码加油 6=兑换码 7=油价分析 8=油价走势 9=加油站地图 10=分享油包 11=发票报销 12=绑定节日卡',
  `img_url` varchar(255) NOT NULL DEFAULT '' COMMENT '导航栏图片地址',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `platform_type` tinyint(1) NOT NULL COMMENT '关联展示平台 1=app首页 2=M站首页',
  `link_url` varchar(255) NOT NULL COMMENT '链接',
  `icon_id` varchar(100) NOT NULL COMMENT 'icon id',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `is_release` tinyint(1) NOT NULL COMMENT '是否发布 0=否 1=是',
  `is_identity` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否身份验证 0=否 1=是',
  `hide_name` varchar(255) NOT NULL DEFAULT '' COMMENT '埋点名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态值-1删除 0正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页四宫格配置表';

DROP TABLE IF EXISTS `bwoil_operation_idfa`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_idfa` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '渠道名称',
  `app_id` varchar(255) NOT NULL DEFAULT '' COMMENT '应用id',
  `app_secret` varchar(255) NOT NULL COMMENT '应用密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态值-1删除 0正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='IDFA排重表';

DROP TABLE IF EXISTS `bwoil_operation_juhe_product`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_juhe_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `proid` varchar(10) DEFAULT NULL COMMENT '商品id',
  `price` decimal(8,2) DEFAULT NULL COMMENT '面额',
  `sale_price` decimal(8,3) DEFAULT NULL COMMENT '成本价',
  `charge_type` char(1) DEFAULT NULL COMMENT '加油卡类型(1中石化 2中石油 )',
  `provider_type` char(1) DEFAULT NULL COMMENT '充值接口类型(1聚合 2欧非)',
  `unlimit` char(1) DEFAULT '0' COMMENT '是否是任意充 0不是  1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聚合欧非中石油中石化协议价';

DROP TABLE IF EXISTS `bwoil_operation_member_insurance`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_member_insurance` (
  `insurance_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `member_id` mediumint(8) unsigned NOT NULL COMMENT '会员ID',
  `shop_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `customer_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `certificate_type` int(1) NOT NULL DEFAULT '0' COMMENT '证件类型,0:身份证,1护照',
  `certificate_bn` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `insurance_company` varchar(20) DEFAULT NULL COMMENT '投保公司',
  `product_code` varchar(128) DEFAULT NULL COMMENT '产品代码',
  `insurance_date` int(10) unsigned DEFAULT NULL COMMENT '投保日期',
  `insurance_bn` varchar(32) DEFAULT NULL COMMENT '保险单号',
  `from_time` int(10) unsigned DEFAULT NULL COMMENT '保险生效时间',
  `to_time` int(10) unsigned DEFAULT NULL COMMENT '保险失效时间',
  `period` int(3) unsigned DEFAULT NULL COMMENT '有效期',
  `insurance_money` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '保险金额',
  `insurance_limit` decimal(20,3) DEFAULT '0.000' COMMENT '免赔限额',
  `premium` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '保险费',
  `insurance_image` varchar(100) DEFAULT NULL COMMENT '电子保单图片',
  `insurance_status` int(1) NOT NULL DEFAULT '0' COMMENT '保障状态:0新建,1认证成功,2准备投保,3投保中,4投保成功,5即将到期,6投保失败',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` int(10) unsigned NOT NULL COMMENT '创建时间',
  `lastmodify` int(10) unsigned NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`insurance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_operation_member_insurance_strategy`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_member_insurance_strategy` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `amount_start` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '起始金额',
  `amount_end` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '截止金额',
  `product_code` varchar(20) NOT NULL COMMENT '产品代码',
  `insurance_company` varchar(20) NOT NULL COMMENT '保险公司名称',
  `insurance_amount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '保额',
  `insurance_cost` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '保费',
  `period` int(3) unsigned NOT NULL COMMENT '有效期',
  `is_export` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否导出，默认0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_operation_oilbox`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_oilbox` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员ID',
  `oil_mass` decimal(20,2) NOT NULL COMMENT '剩余油量',
  `oil_frozen` decimal(20,2) NOT NULL COMMENT '冻结油量',
  `status` tinyint(1) DEFAULT '0' COMMENT '0正常/-1删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的油箱表';

DROP TABLE IF EXISTS `bwoil_operation_oilbox_mass_change`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_oilbox_mass_change` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员ID',
  `oil_activity_id` int(11) DEFAULT NULL COMMENT '送油活动id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `type` varchar(50) NOT NULL COMMENT '收支分类',
  `quantity` decimal(20,2) NOT NULL COMMENT '收支油量',
  `gain_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '获取时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '0正常/-1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='油量收支表';

DROP TABLE IF EXISTS `bwoil_operation_oilbox_pck`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_oilbox_pck` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `quantity` decimal(20,2) unsigned NOT NULL COMMENT '油量',
  `oil_source` varchar(50) NOT NULL COMMENT '来源',
  `expireTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '过期时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '0正常/-1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='油包表';

DROP TABLE IF EXISTS `bwoil_operation_oilbox_pck_member`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_oilbox_pck_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员ID',
  `pck_id` int(11) unsigned NOT NULL COMMENT '油包ID',
  `gain_time` timestamp NULL DEFAULT NULL COMMENT '获取时间',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '0正常/-1删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id_pck_id` (`member_id`,`pck_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的油包表';

DROP TABLE IF EXISTS `bwoil_operation_oil_activity`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_oil_activity` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `activity_name` varchar(50) NOT NULL COMMENT '活动名称',
  `activity_type` int(11) NOT NULL COMMENT '活动类型',
  `activity_bn` varchar(50) NOT NULL COMMENT '活动编号',
  `oil_region_id` int(11) DEFAULT NULL COMMENT '油品地区ID',
  `oil_region_name` varchar(255) DEFAULT NULL COMMENT '地区名称',
  `oil_sku_id` int(11) DEFAULT NULL COMMENT '油品规格ID',
  `oil_name` varchar(255) DEFAULT NULL COMMENT '油品名称',
  `oil_quantity_per_gift` varchar(255) NOT NULL COMMENT '赠送油量（单位升,需大于0,支持2位小数, 随机升数时,用英文分号;隔开）',
  `oil_gift_quantity_type` tinyint(1) NOT NULL COMMENT '赠送油量类型（1/固定升数,2/随机升数）',
  `oil_total_num` int(11) NOT NULL DEFAULT '0' COMMENT '送油的个数总量',
  `goils_sum_give` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '活动已赠送总油量',
  `goils_sum_num_join` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '活动参与人数',
  `oil_total_num_limit` int(10) NOT NULL COMMENT '是否限制送油的个数总量(1/限制,0/不限制)',
  `goils_sum_num_like` int(10) DEFAULT '0' COMMENT '加油人数【为参与人点赞的人数】',
  `goils_give_istips` tinyint(1) DEFAULT NULL COMMENT '送油活动是否启用交互语',
  `activity_interlanguage` text COMMENT '交互语',
  `activity_rule_desc` text NOT NULL COMMENT '规则描述',
  `activity_enable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1/启用,0/禁用',
  `goils_give_init_new` varchar(255) NOT NULL DEFAULT '0' COMMENT '活动初始升数(新用户)',
  `goils_give_init_old` varchar(255) NOT NULL DEFAULT '0' COMMENT '活动初始升数(老用户)',
  `goils_give_like` varchar(255) NOT NULL DEFAULT '0' COMMENT '活动点赞加油升数',
  `goils_give_like_new` varchar(255) NOT NULL DEFAULT '0' COMMENT '活动点赞加油升数(新用户)',
  `oil_valid_days` int(11) DEFAULT NULL COMMENT '当前领取油量的有效天数',
  `activity_begin_time` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `activity_end_time` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `goils_share_title` varchar(200) DEFAULT '' COMMENT '分享标题',
  `goils_share_desc` text COMMENT '分享描述',
  `goils_share_image` varchar(255) DEFAULT '' COMMENT '分享图片',
  `new_like` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否允许老用户给好友加油 0 不允许 1允许',
  `old_join` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否允许老用户参与 1:允许; 0:不允许',
  `goils_title` varchar(200) DEFAULT '' COMMENT '页面标题1',
  `goils_subtitle` varchar(200) DEFAULT '' COMMENT '页面标题2',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常 -1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='送油活动表';

DROP TABLE IF EXISTS `bwoil_operation_order_result_manager`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_order_result_manager` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `button_title` varchar(50) NOT NULL COMMENT '按钮标题',
  `button_function` varchar(50) NOT NULL COMMENT '按钮功能,关联到按钮功能的key值',
  `image` varchar(50) DEFAULT NULL COMMENT '图片标识',
  `link_url` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `sort_num` int(3) DEFAULT '0' COMMENT '排序号，默认0',
  `associated_location` varchar(20) DEFAULT NULL COMMENT '关联位置,由前台定义',
  `associated_platform` varchar(20) DEFAULT NULL COMMENT '关联平台,由前台定义',
  `is_open` int(3) DEFAULT '0' COMMENT '是否开启:0 是 ,1 否 默认0',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常 默认0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单结果管理表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_channel`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_channel` (
  `channel_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '渠道ID',
  `channel_type` char(1) NOT NULL DEFAULT '1' COMMENT '渠道类型(1：个人，2：合作商，3：电销，4：地推， 5：加油站)',
  `department_parent_id` mediumint(8) DEFAULT '0' COMMENT '部门父类id',
  `department_type_id` mediumint(8) DEFAULT '0' COMMENT '部门类型id',
  `business_type` varchar(20) DEFAULT 'defaultype' COMMENT '第三方colourlife,defaultype',
  `member_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `channel_name` varchar(255) NOT NULL DEFAULT '' COMMENT '账户名称',
  `isvalid` char(1) NOT NULL DEFAULT '1' COMMENT '是否有效 0否,1是',
  `isfastreg` char(1) NOT NULL DEFAULT '0' COMMENT '是否快速注册 0否 ,1是',
  `account_desc` longtext COMMENT '账期提醒',
  `rate_desc` longtext COMMENT '收益规则',
  `promotion_desc` longtext COMMENT '分享内容',
  `description` longtext COMMENT '说明',
  `share_image` varchar(255) DEFAULT '' COMMENT '封面图片',
  `share_image_url` varchar(255) DEFAULT '' COMMENT '链接地址',
  `channel_base_info` text COMMENT '分销注册页成功页配置',
  `h5_button_setting` text COMMENT 'h5注册页配置',
  `h5_bottomnav_setting` text COMMENT 'H5落地页导航栏配置',
  `sales_oil_send` text COMMENT '送油',
  `sales_coupon_send` text COMMENT '送券配置',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` char(2) DEFAULT '0' COMMENT '状态值(-1 删除 0 正常)',
  PRIMARY KEY (`channel_id`),
  KEY `ind_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销渠道列表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_channel_department`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_channel_department` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '父节点',
  `node_name` varchar(50) NOT NULL DEFAULT '' COMMENT '部门栏目名称',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '数据来源:1分销账户,2注册来源,3支付方式',
  `register_source` varchar(50) NOT NULL DEFAULT '' COMMENT '注册来源',
  `pay_method` varchar(50) NOT NULL DEFAULT '' COMMENT '支付方式',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `uptime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销渠道部门';

DROP TABLE IF EXISTS `bwoil_operation_promotion_commission_rate`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_commission_rate` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_type_bn` varchar(50) NOT NULL COMMENT '商品类型bn',
  `goods_amount` bigint(10) DEFAULT '0' COMMENT '商品金额',
  `goods_rate` decimal(8,2) DEFAULT '0.00' COMMENT '佣金比例',
  `operator_name` varchar(50) DEFAULT NULL COMMENT '操作人名称',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常 默认0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品佣金比例表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_config`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_config` (
  `config_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `channel_id` int(20) unsigned NOT NULL COMMENT '渠道ID',
  `promotion_rate` decimal(7,2) DEFAULT NULL COMMENT '佣金比例',
  `duration` int(10) NOT NULL DEFAULT '0' COMMENT '收益时效/天',
  `revised_duration` int(10) NOT NULL DEFAULT '31' COMMENT '变更收益时效/天',
  `isvalid` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '是否有效',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道佣金配置表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_orderlist`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_orderlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道id',
  `channel_type` varchar(1) DEFAULT NULL COMMENT '渠道类型 1：个人，2：合作商，3：电销，4：地推， 5：加油站',
  `channel_name` varchar(255) DEFAULT NULL COMMENT '账户名称',
  `source` varchar(255) DEFAULT NULL COMMENT '来源',
  `share_mount` decimal(11,2) DEFAULT NULL COMMENT '佣金收益',
  `real_pay_amount` decimal(11,2) DEFAULT NULL COMMENT '订单实付金额',
  `order_id` varchar(255) DEFAULT NULL COMMENT '订单号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `product_type` varchar(200) DEFAULT NULL COMMENT '商品类型',
  `addon` varchar(255) DEFAULT NULL COMMENT '规格(升数/金额)',
  `total_amount` decimal(11,2) DEFAULT NULL COMMENT '订单金额',
  `pay_status` varchar(225) DEFAULT NULL COMMENT '打款状态(0：未打款，1：已打款，2：打款失败'')',
  `promotion_shop_bn` varchar(255) DEFAULT NULL COMMENT '推广会员编号',
  `buyer_shop_bn` varchar(255) DEFAULT NULL COMMENT '被推广会员编号',
  `promotion_mobile` varchar(255) DEFAULT NULL COMMENT '推广账号(手机)',
  `register_time` timestamp NULL DEFAULT NULL COMMENT '订单购买人账号的注册时间',
  `modifier_time` timestamp NULL DEFAULT NULL COMMENT '分销订单记录的最后操作时间',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订单的创建时间',
  `promotion_member` varchar(255) DEFAULT NULL COMMENT '推广人名称',
  `buyer_member` varchar(255) DEFAULT NULL COMMENT '被推广人名称',
  `produce_number` int(255) DEFAULT NULL COMMENT '产品数量',
  `produce_period` int(255) DEFAULT NULL COMMENT '产品期数',
  `produce_freezing` int(255) DEFAULT NULL COMMENT '产品冻结期',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `prom_member_id` int(11) DEFAULT NULL COMMENT '推荐人的id',
  `realname_auth` varchar(2) DEFAULT NULL COMMENT '实名认证标识(0：未实名 1:已实名)',
  `buyer_mobile` varchar(255) DEFAULT NULL COMMENT '被推广人(购买人的电话)',
  `pay_time` datetime DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `buyer_member_id` int(11) NOT NULL COMMENT '被推广人ID',
  `report_id` int(11) DEFAULT '0' COMMENT '结算报表ID',
  `share_proportion` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '佣金比例',
  `real_time` timestamp NULL DEFAULT NULL COMMENT '下单人的实名时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销订单表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_relationship`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_member_id` int(11) NOT NULL COMMENT '推荐人的id',
  `promotion_member_name` varchar(20) DEFAULT NULL COMMENT '推荐人名称',
  `buyer_member_id` int(11) NOT NULL COMMENT '被推荐人的id',
  `buyer_member_name` varchar(20) DEFAULT NULL COMMENT '被推荐人名称',
  `buyer_is_truename` int(1) DEFAULT '0' COMMENT '是否实名 0：未实名，1：已实名',
  `channel_id` int(11) NOT NULL COMMENT '对应的渠道id',
  `duration` int(11) NOT NULL COMMENT '收益时效',
  `duration_modify` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '到期时间',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `buyer_shop_bn` varchar(255) NOT NULL COMMENT '被推广会员编号',
  `buyer_mobile` varchar(255) DEFAULT NULL COMMENT '被推广账号',
  `buyer_register_time` timestamp NULL DEFAULT NULL COMMENT '被推广会员注册时间',
  `buyer_source` varchar(255) DEFAULT NULL COMMENT '被推广人注册来源',
  `promotion_shop_bn` varchar(255) DEFAULT NULL COMMENT '推广会员编号',
  `promotion_mobile` varchar(255) DEFAULT NULL COMMENT '推广账号',
  `promotion_register_time` timestamp NULL DEFAULT NULL COMMENT '推广会员注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐人分销关系表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_report`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_name` varchar(255) NOT NULL COMMENT '报表名称',
  `channel_name` varchar(255) NOT NULL COMMENT '推广人账户',
  `pay_status` int(1) NOT NULL DEFAULT '0' COMMENT '打款状态(0未打款, 1已打款)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报表创建日期',
  `report_content_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '报表内容日期',
  `share_mount` decimal(10,2) NOT NULL COMMENT '结算佣金',
  `real_pay_amount` decimal(10,2) NOT NULL COMMENT '购买实付总金额',
  `channel_type` varchar(255) NOT NULL COMMENT '渠道类型',
  `promotion_shop_bn` varchar(255) NOT NULL COMMENT '推广人编号',
  `member_id` int(11) NOT NULL COMMENT '推荐人id',
  `date_type` int(1) DEFAULT '0' COMMENT '时间类型，0为订单购买时间统计报表，1为用户注册时间统计',
  `total_amount` decimal(11,2) NOT NULL COMMENT '订单金额',
  `lastmodify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销报表列表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_sales`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_sales` (
  `sales_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `channel_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `market_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '活动模板ID',
  `sales_type` char(1) NOT NULL COMMENT '营销配置类型 1红包 2现金券 3送油',
  `status` char(2) DEFAULT '0' COMMENT '营销配置状态-1删除 0 正常',
  `sales_text` varchar(500) DEFAULT NULL COMMENT '营销配置方案明细',
  PRIMARY KEY (`sales_id`),
  KEY `ind_channel_id` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销渠道油券明细表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_share`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_share` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `buyer_member_id` mediumint(8) unsigned DEFAULT '0' COMMENT '被推广人ID',
  `buyer_shop_bn` varchar(50) DEFAULT '' COMMENT '被推广会员编号',
  `buyer_mobile` varchar(50) DEFAULT '' COMMENT '被推广账号',
  `promotion_member_id` mediumint(8) unsigned DEFAULT '0' COMMENT '推广人ID',
  `share_type` int(1) DEFAULT '2' COMMENT '佣金类型，：1：注册佣金，2：邀请好友返现',
  `profit` decimal(20,2) DEFAULT '0.00' COMMENT '佣金金额',
  `buyer_register_time` datetime DEFAULT NULL COMMENT '被推广会员注册时间 ',
  `buyer_source` varchar(50) DEFAULT '' COMMENT '被推广人注册来源',
  `buyer_is_truename` int(1) DEFAULT '0' COMMENT '是否实名 0：未实名，1：已实名',
  `buyer_auth_time` datetime DEFAULT NULL COMMENT '认证时间',
  `promotion_shop_bn` varchar(50) DEFAULT '' COMMENT '推广会员编号',
  `promotion_mobile` varchar(50) DEFAULT '' COMMENT '推广账号',
  `promotion_register_time` datetime DEFAULT NULL COMMENT '推广会员注册时间 ',
  `channel_id` bigint(20) unsigned DEFAULT '0' COMMENT '渠道ID',
  `achieve_status` int(1) DEFAULT '0' COMMENT '状态，：0：未达标，1：达标',
  `achieve_time` datetime DEFAULT NULL COMMENT '达标时间',
  `commission_status` int(1) DEFAULT '0' COMMENT '结算状态，：0：未结算，1：已结算',
  `commission_time` datetime DEFAULT NULL COMMENT '结算时间',
  `is_exception` int(1) DEFAULT '0' COMMENT '是否异常 0正常 1异常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请好友佣金表';

DROP TABLE IF EXISTS `bwoil_operation_promotion_statistics`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_promotion_statistics` (
  `statistics_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `pcode` varchar(50) NOT NULL DEFAULT '' COMMENT '推广码',
  `channel_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `url` varchar(500) DEFAULT '' COMMENT '访问地址',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`statistics_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广渠道统计表';

DROP TABLE IF EXISTS `bwoil_operation_redcoupon_activity`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_redcoupon_activity` (
  `cpn_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `cpn_bn` varchar(50) NOT NULL COMMENT '活动编号',
  `cpn_name` varchar(200) NOT NULL COMMENT '活动名称',
  `reduce_type` char(1) NOT NULL DEFAULT '1' COMMENT '优惠形式',
  `cpn_start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '使用开始时间',
  `cpn_end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '使用结束时间',
  `limit_num` varchar(250) DEFAULT NULL COMMENT '同一账户限制次数',
  `order_num` int(10) unsigned DEFAULT '0' COMMENT '已支付订单数',
  `money_sum` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '产生销售总额',
  `discount_sum` decimal(10,2) DEFAULT '0.00',
  `m_act_slogan` text COMMENT '移动端前台活动标语',
  `pc_act_slogan` text COMMENT 'PC端前台活动标语',
  `m_act_url` text COMMENT '移动端活动入口',
  `pc_act_url` text COMMENT 'PC端活动入口',
  `cpn_status` char(1) NOT NULL DEFAULT '1' COMMENT '方案状态 0关闭 1开启',
  `with_cashcoupon` char(1) NOT NULL DEFAULT '0' COMMENT '可同时使用现金券0 不可 1可',
  `with_goil` char(1) NOT NULL DEFAULT '0' COMMENT '可同时使用油箱抵扣 0 不可 1可',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '方案删除状态 0 正常 -1 删除',
  `remark` text COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`cpn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='满减活动配置';

DROP TABLE IF EXISTS `bwoil_operation_redcoupon_activity_order`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_redcoupon_activity_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cpn_bn` varchar(50) NOT NULL COMMENT '活动编号',
  `cpn_name` varchar(50) NOT NULL COMMENT '活动名称',
  `order_id` varchar(25) NOT NULL DEFAULT '0' COMMENT '订单号',
  `member_id` int(10) NOT NULL COMMENT '会员id',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `order_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `good_name` varchar(50) NOT NULL COMMENT '商品名字',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `real_pay_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单实际支付金额',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '现金券减免',
  `goil_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油箱抵用金额',
  `reduce_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '满减金额',
  `discount_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '商品本身打折金额',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '方案删除状态 0 正常 -1 删除',
  `order_status` int(10) NOT NULL DEFAULT '1' COMMENT '订单状态 1=未支付 2=已支付 3=已取消',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='满减订单';

DROP TABLE IF EXISTS `bwoil_operation_redcoupon_rule`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_redcoupon_rule` (
  `rule_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '满减活动规则ID',
  `cpn_id` int(10) NOT NULL COMMENT '满减活动ID',
  `rule_type_bn` varchar(50) DEFAULT NULL COMMENT '满减活动规则货品类型',
  `rule_detail` text COMMENT '规则详情',
  `rule_periods` int(10) DEFAULT '0' COMMENT '满多少期可使用',
  `status` char(2) NOT NULL DEFAULT '0' COMMENT '方案删除状态 0 正常 -1 删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='满减规则';

DROP TABLE IF EXISTS `bwoil_operation_redpacket_orders`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_redpacket_orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` varchar(30) DEFAULT '' COMMENT '会员编号',
  `order_bn` varchar(30) DEFAULT '' COMMENT '订单编号',
  `order_price` decimal(20,4) DEFAULT NULL COMMENT '订单金额',
  `product_category` varchar(30) DEFAULT '' COMMENT '产品类型',
  `lock_peroid` varchar(10) DEFAULT '' COMMENT '产品期限',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包订单表';

DROP TABLE IF EXISTS `bwoil_operation_refuel_products`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_refuel_products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `proid` int(10) DEFAULT NULL COMMENT '聚合产品id',
  `amount` decimal(19,2) NOT NULL DEFAULT '0.00' COMMENT '面值',
  `price_sale` decimal(19,2) NOT NULL DEFAULT '0.00' COMMENT '售价',
  `price` decimal(19,2) NOT NULL DEFAULT '0.00' COMMENT '成本',
  `num` int(10) DEFAULT '1' COMMENT '数量',
  `charge_type` char(1) NOT NULL DEFAULT '1' COMMENT '加油卡类型 （1:中石化、2:中石油；默认为1)',
  `provider_type` char(1) NOT NULL DEFAULT '1' COMMENT '服务商 （1:聚合、2:欧飞；默认为1)',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` char(2) DEFAULT '0' COMMENT '状态(-1删除 0 正常)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油卡代充产品';

DROP TABLE IF EXISTS `bwoil_operation_report_oil_pck_stat`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_report_oil_pck_stat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `activity_name` varchar(50) NOT NULL COMMENT '活动名称',
  `gift_oil_quantity` decimal(10,2) NOT NULL COMMENT '赠送油量',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `member_account` varchar(50) NOT NULL COMMENT '会员账号',
  `gift_oil_time` timestamp NULL DEFAULT NULL COMMENT '送油时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='送油统计报表';

DROP TABLE IF EXISTS `bwoil_operation_report_oil_pck_tran`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_report_oil_pck_tran` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `activity_name` varchar(50) DEFAULT '' COMMENT '活动名称',
  `tran_type` varchar(50) DEFAULT '' COMMENT '交易类型',
  `tran_quantity` decimal(20,2) DEFAULT '0.00' COMMENT '交易数量',
  `oil_box_mass` decimal(20,2) DEFAULT '0.00' COMMENT '交易后油箱总量',
  `tran_oil_price` decimal(20,2) DEFAULT '0.00' COMMENT '交易油价',
  `tran_oil_price_total` decimal(20,2) DEFAULT '0.00' COMMENT '交易油量总额',
  `order_bn` varchar(50) DEFAULT NULL COMMENT '订单号',
  `member_mobile` varchar(20) DEFAULT NULL COMMENT '会员手机号',
  `member_account` varchar(50) DEFAULT NULL COMMENT '会员账号',
  `tran_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `tran_bn` varchar(50) DEFAULT NULL COMMENT '交易编号',
  `oil_remark` varchar(500) DEFAULT '' COMMENT '扣减的油包信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='油箱交易报表';

DROP TABLE IF EXISTS `bwoil_operation_wap_activity`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_wap_activity` (
  `activity_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `activity_title` varchar(255) NOT NULL COMMENT '活动标题',
  `activity_buttontext` varchar(255) DEFAULT '' COMMENT '按钮文字',
  `activity_image` varchar(255) DEFAULT NULL COMMENT '活动图片的路径',
  `activity_type` char(2) DEFAULT '0' COMMENT '跳转类型(0活动页,1文章页)',
  `activity_hyperlink` varchar(255) DEFAULT NULL COMMENT '活动链接地址',
  `activity_article_title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `activity_article_content` varchar(255) DEFAULT NULL COMMENT '内容',
  `type_bn` varchar(255) DEFAULT NULL COMMENT '关联商品分类',
  `m_show` char(2) DEFAULT '0' COMMENT '关联平台0M站 1app  2全部平台',
  `activity_attend` int(10) NOT NULL DEFAULT '0' COMMENT '参与人数',
  `is_pub` char(1) DEFAULT '0' COMMENT '0不发 1发布',
  `status` char(2) DEFAULT '0' COMMENT '0正常 -1 删除',
  `activity_stime` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `activity_etime` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移动端活动配置';

DROP TABLE IF EXISTS `bwoil_operation_wap_ads`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_wap_ads` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '广告ID',
  `image_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `image_hyperlink` varchar(255) DEFAULT NULL COMMENT '图片的超链接地址',
  `ad_title` varchar(255) DEFAULT NULL COMMENT '轮播标题',
  `ad_type` varchar(255) DEFAULT 'home' COMMENT '广告类型:home首页,service加油服务',
  `ad_jumpusage` varchar(255) DEFAULT NULL COMMENT '跳转功能',
  `instruction` varchar(255) DEFAULT NULL COMMENT '说明',
  `sort` smallint(5) unsigned DEFAULT '0' COMMENT '排序',
  `type_bn` varchar(255) DEFAULT NULL COMMENT '关联商品分类',
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `status` char(2) DEFAULT '0' COMMENT '(-1:删除 0 不发布 1 发布)',
  `share` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可以分享 0否 1是',
  `share_banner` varchar(255) DEFAULT '' COMMENT '分享图标',
  `share_title` varchar(255) DEFAULT '' COMMENT '分享标题',
  `share_content` varchar(255) DEFAULT '' COMMENT '分享内容描述',
  PRIMARY KEY (`id`),
  KEY `idx_ad_type` (`ad_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三端广告';

DROP TABLE IF EXISTS `bwoil_operation_wap_topnews`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_wap_topnews` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '头条ID',
  `author` varchar(255) DEFAULT NULL COMMENT '头条发布者',
  `title` varchar(255) DEFAULT '' COMMENT '头条标题',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `context` text COMMENT '头条内容',
  `ison` char(1) DEFAULT '0' COMMENT '是否发布0 不发布 1 发布',
  `app_proid` varchar(100) DEFAULT '' COMMENT 'app商品分id',
  `status` char(2) DEFAULT '0' COMMENT '0正常 -1 删除',
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `msite_proid` varchar(100) DEFAULT '' COMMENT 'M站商品id',
  `base_qty` mediumint(8) unsigned DEFAULT '0' COMMENT '阅读量基础数',
  `read_qty` mediumint(8) unsigned DEFAULT '0' COMMENT '阅读量',
  `like_qty` mediumint(8) unsigned DEFAULT '0' COMMENT '点赞量',
  `sort` int(10) unsigned DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='头条（油价分析）';

DROP TABLE IF EXISTS `bwoil_operation_whiteblack_list`;
CREATE TABLE IF NOT EXISTS `bwoil_operation_whiteblack_list` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` int(1) NOT NULL COMMENT '类型：0 黑名单 1 白名单',
  `open_state` int(1) NOT NULL COMMENT '开启状态：0 关闭 1 开启，默认 0',
  `ips` varchar(21000) DEFAULT NULL COMMENT '多个ip用逗号隔开',
  `page_code` int(3) DEFAULT NULL COMMENT '页面编码',
  `is_crossdomain` int(1) DEFAULT '0' COMMENT '是否跨域 0 不开启 1 开启，默认0',
  `status` int(1) DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='黑白名单表';

DROP TABLE IF EXISTS `bwoil_order`;
CREATE TABLE IF NOT EXISTS `bwoil_order` (
  `id` varchar(20) NOT NULL COMMENT '订单号',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  `mobile` varchar(15) DEFAULT NULL COMMENT '会员手机号',
  `shop_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `product_id` mediumint(8) NOT NULL COMMENT '货品ID',
  `product_name` varchar(200) DEFAULT NULL COMMENT '货品名称',
  `region_id` int(10) DEFAULT NULL COMMENT '地区ID',
  `region_name` varchar(32) DEFAULT NULL COMMENT '地区名称',
  `oil_id` mediumint(8) DEFAULT NULL COMMENT '油品ID',
  `oil_name` varchar(20) DEFAULT NULL COMMENT '油品名称',
  `oil_price` decimal(20,2) DEFAULT NULL COMMENT '最近一条油品价格',
  `order_status` varchar(15) NOT NULL DEFAULT 'active' COMMENT '订单状态:active,dead,finish',
  `pay_status` char(1) NOT NULL DEFAULT '0' COMMENT '付款状态:0,1,2,3,4,5 ',
  `pay_way` varchar(15) NOT NULL DEFAULT '0' COMMENT '支付方式',
  `pay_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
  `is_protect` char(1) NOT NULL DEFAULT 'N' COMMENT '是否还有保价费:Y,N',
  `cost_protect` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '保价费',
  `total_cnt` int(8) NOT NULL COMMENT '购买张数',
  `term` int(8) NOT NULL COMMENT '期数',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `total_litre` decimal(20,2) DEFAULT '0.00' COMMENT '升数',
  `real_pay_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单实际支付金额',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单现金券减免',
  `goil_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单油箱抵用金额',
  `reduce_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单满减金额',
  `discount_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单折扣金额',
  `redpacket_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '红包折扣金额',
  `cps_ids` varchar(1000) DEFAULT NULL COMMENT '优惠券id array[]',
  `channel_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '推广渠道ID',
  `extra_channel_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '推广extra渠道ID',
  `ad_source` varchar(100) DEFAULT NULL COMMENT '广告来源',
  `mark_type` varchar(2) NOT NULL DEFAULT 'b1' COMMENT '订单备注图标',
  `mark_text` varchar(2000) DEFAULT NULL COMMENT '订单备注',
  `pay_no` varchar(32) DEFAULT NULL COMMENT '支付单号',
  `order_refer_no` varchar(32) DEFAULT NULL COMMENT '第三方订单号',
  `refer_notice` char(1) DEFAULT 'N' COMMENT '是否通知第三方:Y,N',
  `source` varchar(10) DEFAULT 'pc' COMMENT '平台来源:pc,app,wap,weixin',
  `devinfo` varchar(100) DEFAULT NULL COMMENT '设备信息',
  `linepayment` varchar(20) DEFAULT NULL COMMENT '线下交易号',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `buy_channel` varchar(20) DEFAULT NULL COMMENT '购买渠道',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP TABLE IF EXISTS `bwoil_order_asset`;
CREATE TABLE IF NOT EXISTS `bwoil_order_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资产id',
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `asset_type` varchar(4) NOT NULL COMMENT '资产类型 PROD: 产品、CZ: 充值 LC: 理财账户 RP: 红包',
  `cur_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '当前余额',
  `advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '当前可用余额',
  `freeze_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '冻结金额',
  `last_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '上次余额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产表';

DROP TABLE IF EXISTS `bwoil_order_asset_detail`;
CREATE TABLE IF NOT EXISTS `bwoil_order_asset_detail` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `member_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `op_id` mediumint(8) DEFAULT NULL COMMENT '操作员ID',
  `money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '出入金额',
  `message` varchar(255) DEFAULT NULL COMMENT '管理备注',
  `payment_id` varchar(50) DEFAULT NULL COMMENT '支付单号',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单号',
  `paymethod` varchar(100) DEFAULT NULL COMMENT '支付方式',
  `memo` longtext COMMENT '业务摘要',
  `trade_bn` varchar(20) DEFAULT NULL COMMENT '交易编号',
  `card_bn` bigint(20) DEFAULT NULL COMMENT '卡号',
  `trade_type` varchar(15) DEFAULT NULL COMMENT '交易类型 ',
  `log_type` varchar(28) DEFAULT NULL COMMENT '日志类型',
  `import_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '存入金额',
  `explode_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '支出金额',
  `valid_advance` decimal(20,2) DEFAULT '0.00' COMMENT '可用金额',
  `member_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '当前余额',
  `shop_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '商店余额',
  `is_freeze` char(1) DEFAULT 'N' COMMENT '是否是冻结金额(Y N)',
  `disabled` char(1) NOT NULL DEFAULT 'N' COMMENT '失效(Y N)',
  `account_type` varchar(15) NOT NULL DEFAULT 'finance' COMMENT '账户类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`log_id`),
  KEY `ind_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_cancel`;
CREATE TABLE IF NOT EXISTS `bwoil_order_cancel` (
  `order_id` varchar(20) NOT NULL COMMENT '订单号',
  `reason_type` char(1) DEFAULT '0' COMMENT '取消原因类型:0-7',
  `reason_desc` varchar(200) DEFAULT NULL COMMENT '订单备注',
  `cancel_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单取消表';

DROP TABLE IF EXISTS `bwoil_order_cards`;
CREATE TABLE IF NOT EXISTS `bwoil_order_cards` (
  `card_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '卡ID',
  `order_id` varchar(20) NOT NULL COMMENT '订单号',
  `card_bn` varchar(32) NOT NULL COMMENT '卡号',
  `card_name` varchar(200) NOT NULL COMMENT '名称',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `card_type` varchar(30) NOT NULL COMMENT '卡类型已中台为准',
  `prod_attr` varchar(15) DEFAULT NULL COMMENT '产品属性（litre: 储油产品、amount: 金额产品）',
  `region_name` varchar(100) DEFAULT NULL COMMENT '地区名称',
  `region_id` int(11) NOT NULL COMMENT '地区ID',
  `oil_name` varchar(50) DEFAULT NULL COMMENT '油品名称',
  `oil_id` int(11) NOT NULL COMMENT '油品ID',
  `oil_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油价',
  `sale_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '售出油价',
  `card_status` char(1) NOT NULL COMMENT '状态 0: 冻结 1：正常',
  `trans_status` char(1) NOT NULL COMMENT '交易状态 0: 储油中 1:可兑付 2：已兑付 3: 强制退款',
  `total_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '总额,金额或升',
  `freeze_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '冻结,金额或升',
  `can_sale_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '可兑付金额或升',
  `recently_sale_date` timestamp NULL DEFAULT NULL COMMENT '最近可兑换日期',
  `sale_amount` decimal(20,2) DEFAULT '0.00' COMMENT '已兑付,金额或升',
  `buy_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '购买时总金额',
  `pay_amout` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付金额',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '优惠券抵扣金额',
  `cpns_liter` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '优惠券抵扣升数',
  `oil_deduction` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油箱抵扣',
  `reduce_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '订单满减金额',
  `profit_discount` decimal(20,3) DEFAULT '0.000' COMMENT '赠送折扣',
  `rule_discount` decimal(20,2) DEFAULT NULL COMMENT '折扣',
  `income_rate` decimal(8,2) DEFAULT '0.00' COMMENT '年化收益率(%)',
  `float_rate` decimal(8,2) DEFAULT '0.00' COMMENT '浮动收益(%)',
  `sale_rate` decimal(8,2) DEFAULT '0.00' COMMENT '管理费收费比(%)',
  `card_cnt` int(10) NOT NULL DEFAULT '1' COMMENT '卡张数',
  `peroid` int(11) NOT NULL COMMENT '期限',
  `term` int(11) DEFAULT '1' COMMENT '期数',
  `term_unit` char(1) DEFAULT 'M' COMMENT '期数单位',
  `unlock_date` varchar(8) DEFAULT NULL COMMENT '锁定截止日期',
  `force_sale_date` varchar(8) DEFAULT NULL COMMENT '强制兑付日',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`card_id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_card_bn` (`card_bn`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_force_sale_date` (`force_sale_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡表';

DROP TABLE IF EXISTS `bwoil_order_card_cycle`;
CREATE TABLE IF NOT EXISTS `bwoil_order_card_cycle` (
  `card_bn` varchar(32) NOT NULL COMMENT '卡编号',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `cycle_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '发票报销/扫码加油限额(元)',
  `cycle_day` smallint(5) NOT NULL COMMENT '发票报销/扫码加油频率(天)',
  `cycle_cash_remain` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '本期剩余兑付升数/金额',
  `last_cash_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次兑付时间',
  PRIMARY KEY (`card_bn`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_card_bn` (`card_bn`),
  KEY `idx_last_cash_time` (`last_cash_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小票兑付、扫码加油卡交易表';

DROP TABLE IF EXISTS `bwoil_order_card_seq`;
CREATE TABLE IF NOT EXISTS `bwoil_order_card_seq` (
  `card_type` varchar(12) NOT NULL COMMENT '卡类型(类型别名+地区区号+油品编号左边两位+卡类型06虚拟卡/08实体卡)',
  `card_seq` int(8) NOT NULL COMMENT '卡号序列',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`card_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_card_trade`;
CREATE TABLE IF NOT EXISTS `bwoil_order_card_trade` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '交易ID，自增主键',
  `trade_bn` varchar(32) NOT NULL COMMENT '交易编号',
  `card_bn` varchar(32) NOT NULL COMMENT '储油通卡号',
  `product_name` varchar(60) DEFAULT NULL COMMENT '产品名称',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `station_id` int(11) DEFAULT '0' COMMENT '加油站ID',
  `trade_type` varchar(15) DEFAULT NULL COMMENT '交易类型',
  `trade_sub_type` varchar(50) NOT NULL COMMENT '类型',
  `audit_status` char(1) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `trade_nums` decimal(20,5) NOT NULL DEFAULT '0.00000' COMMENT '当前兑付申请的升数、金额',
  `price` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '即购买卡时平台卖出油价',
  `pay` decimal(20,3) DEFAULT '0.000' COMMENT '即购买交易数量所花费的钱',
  `trade_price` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '即兑付、回购时的平台买入价',
  `income` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '交易得到的收入，包含收益，不含手续费，每次交易完成后，需要更新卡表上的总收入',
  `cost` decimal(20,3) DEFAULT '0.000' COMMENT '用户成本',
  `cpns_money` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '每张卡优惠券抵扣',
  `goil_money` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '每张卡油箱抵扣',
  `discount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '每张卡折扣抵扣',
  `reduce_money` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '每张卡满减金额',
  `profit` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '此次交易收入-此次交易的成本（交易数量*购买单价），也即兑付得到的额外收益+赠送部分，回购交易为卡的总收益（所有交易的兑付收入之和，包含本次回购的 - 购买金额），付款生成卡时候的收益为0',
  `pure_profit` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '收益(不包含支付优惠)',
  `fixed_profit` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '固定收益',
  `float_profit` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '浮动收益',
  `fee` decimal(20,3) DEFAULT '0.000' COMMENT '手续费(元)',
  `to_member_id` int(11) DEFAULT '0' COMMENT '交易对方会员ID（转让业务时即转让方会员ID）',
  `total_cash_amount` decimal(20,3) DEFAULT '0.000' COMMENT '金额卡，卡表上的累计兑付金额 + 当前交易表上的兑付申请数量，每次交易成功需要更新到卡表上',
  `total_buyback_amount` decimal(20,3) DEFAULT '0.000' COMMENT '金额卡，卡表上的累计回购金额 + 当前交易表上的申请数量，每次交易成功需要更新到卡表上',
  `total_transfer_amount` decimal(20,3) DEFAULT '0.000' COMMENT '金额卡，卡表上的累计转让金额 + 当前交易表上的转让申请数量，每次交易成功需要更新到卡表上',
  `remain_amount` decimal(20,3) DEFAULT '0.000' COMMENT '金额卡，卡表上的剩余金额 - 当前交易的申请数量，每次交易成功需要更新到卡表上',
  `total_cash_litre` decimal(20,5) DEFAULT '0.00000' COMMENT '升数卡，卡表上的累计兑付升数 + 当前交易表上的兑付申请数量，每次交易成功需要更新到卡表上',
  `total_buyback_litre` decimal(10,5) DEFAULT '0.00000' COMMENT '升数卡，卡表上的累计回购升数 + 当前交易表上的回购申请数量，每次交易成功需要更新到卡表上',
  `total_transfer_litre` decimal(10,5) DEFAULT '0.00000' COMMENT '升数卡，卡表上的累计转让升数 + 当前交易表上的转让申请数量，每次交易成功需要更新到卡表上',
  `remain_litre` decimal(10,5) DEFAULT '0.00000' COMMENT '升数卡，卡表上的剩余升数 - 当前交易表上的申请数量，每次交易成功需要更新到卡表上',
  `give_nums` decimal(10,5) DEFAULT '0.00000' COMMENT '升数或金额',
  `remark` varchar(250) DEFAULT NULL COMMENT '交易备注',
  `invoice_attachment` varchar(32) DEFAULT NULL COMMENT '发票/小票附件',
  `trade_start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '交易开始时间',
  `trade_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '交易结束时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_ident_type` (`trade_bn`),
  KEY `ind_station_id` (`station_id`),
  KEY `ind_audit_status` (`audit_status`),
  KEY `ind_trade_end_time` (`trade_end_time`),
  KEY `ind_to_member_id` (`to_member_id`),
  KEY `ind_trade_type` (`trade_type`),
  KEY `ind_member_id` (`member_id`),
  KEY `ind_card_bn` (`card_bn`),
  KEY `ind_lastmodify` (`last_update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡交易表';

DROP TABLE IF EXISTS `bwoil_order_cash_apply`;
CREATE TABLE IF NOT EXISTS `bwoil_order_cash_apply` (
  `cash_out_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员提现ID',
  `cash_bn` varchar(20) DEFAULT NULL COMMENT '提现编号',
  `member_id` int(9) unsigned NOT NULL COMMENT '会员id',
  `member_name` varchar(250) DEFAULT NULL,
  `iden_no` varchar(20) DEFAULT NULL,
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `member_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `bank_no` varchar(20) DEFAULT NULL COMMENT '银行账户',
  `available_balance` decimal(20,2) DEFAULT '0.00' COMMENT '账户可用余额',
  `cash_amount` decimal(20,2) DEFAULT NULL COMMENT '提现金额',
  `cash_fee` decimal(20,2) DEFAULT NULL COMMENT '提现手续费',
  `cash_account_id` int(10) NOT NULL COMMENT '账户提现账号id',
  `cash_status` char(1) NOT NULL DEFAULT '0' COMMENT '提现状态:0 待审核,1 审核通过,2 审核未通过,3 已到账,4 出账失败,5 出账异常',
  `cash_received` char(1) NOT NULL DEFAULT 'N' COMMENT '提现到账:Y N',
  `plat` varchar(50) DEFAULT NULL COMMENT '平台',
  `devinfo` varchar(100) DEFAULT NULL COMMENT '设备信息',
  `audit_operator_id` int(10) DEFAULT NULL COMMENT '审核人ID',
  `audit_operator_name` varchar(32) DEFAULT NULL COMMENT '审核人姓名',
  `audit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `audit_ip` varchar(20) DEFAULT NULL COMMENT '审核IP',
  `cash_finish_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '提现完成时间，即到账时间',
  `remittance_status` char(1) NOT NULL DEFAULT 'N' COMMENT '打款队列状态:N:未入队列 ,Y: 已入队列',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `auto_recon_status` char(1) NOT NULL DEFAULT '1' COMMENT '自动对账状态0未走自动对账流程1待对账2处理中3对账成功4对账失败5对账异常',
  `auto_remark` text COMMENT '自动对账备注',
  `auto_type` char(1) NOT NULL DEFAULT '2' COMMENT '审核方式：0人工审核1自动对账审核2(无审批方式)',
  `cash_pay_type` char(1) DEFAULT '2' COMMENT '打款方式0,1,2',
  `cash_pay_channel` varchar(32) DEFAULT '' COMMENT '线上打款通道',
  `cash_pay_proposer` varchar(32) DEFAULT '' COMMENT '打款方式申请人',
  `cash_pay_audit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '打款复核时间',
  `cash_pay_audit_status` char(1) NOT NULL DEFAULT '0' COMMENT '复核状态0,1,2',
  `cash_pay_auditor` varchar(32) DEFAULT '' COMMENT '复核人',
  `cash_pay_remark` varchar(256) DEFAULT '' COMMENT '复核备注',
  `account_status` char(1) NOT NULL DEFAULT '0' COMMENT '对账状态0,1,2',
  `account_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '对账时间',
  `clear_bn` varchar(32) DEFAULT NULL COMMENT '清结算编号',
  `pay_bn` varchar(32) DEFAULT NULL COMMENT '打款编号',
  `recharge_available_balance` decimal(20,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '充值账户可用余额',
  `red_envelope_available_balance` decimal(20,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '红包账户可用余额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`cash_out_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现申请表';

DROP TABLE IF EXISTS `bwoil_order_cash_flow`;
CREATE TABLE IF NOT EXISTS `bwoil_order_cash_flow` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `member_id` int(11) DEFAULT NULL,
  `op_id` mediumint(8) DEFAULT NULL COMMENT '操作员ID',
  `money` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '出入金额',
  `message` varchar(255) DEFAULT NULL COMMENT '管理备注',
  `payment_id` varchar(50) DEFAULT NULL COMMENT '支付单号',
  `order_id` varchar(20) DEFAULT NULL COMMENT '订单号',
  `paymethod` varchar(100) DEFAULT NULL COMMENT '支付方式',
  `memo` varchar(2000) DEFAULT NULL COMMENT '业务摘要',
  `trade_bn` varchar(20) DEFAULT NULL COMMENT '交易编号',
  `card_bn` varchar(32) DEFAULT NULL COMMENT '卡号',
  `trade_type` varchar(15) DEFAULT NULL COMMENT '交易类型:cash,buyback,transfer,fuel,recharge',
  `log_type` varchar(28) DEFAULT NULL COMMENT '日志类型',
  `import_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '存入金额',
  `explode_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '支出金额',
  `member_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '当前余额',
  `valid_advance` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '添加之前余额',
  `fee_amt` decimal(20,2) DEFAULT '0.00' COMMENT '手续费',
  `fee_rate` decimal(20,2) DEFAULT '0.00' COMMENT '费率',
  `is_freeze` char(1) DEFAULT 'N' COMMENT '是否是冻结金额:Y N',
  `disabled` char(1) NOT NULL DEFAULT 'N' COMMENT '失效:Y N',
  `account_type` varchar(15) NOT NULL DEFAULT 'finance' COMMENT '账户类型finance,recharge,red_envelope',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金流水表';

DROP TABLE IF EXISTS `bwoil_order_cash_task`;
CREATE TABLE IF NOT EXISTS `bwoil_order_cash_task` (
  `task_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_bn` varchar(32) NOT NULL COMMENT '打款编号',
  `member_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '会员id',
  `member_account` varchar(50) NOT NULL COMMENT '会员账号',
  `member_bn` varchar(50) NOT NULL DEFAULT '' COMMENT '会员编号',
  `cash_bn` varchar(32) DEFAULT NULL COMMENT '提现编号',
  `cash_amount` decimal(20,2) DEFAULT '0.00' COMMENT '提现金额',
  `audit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审批时间',
  `task_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '打款任务创建时间',
  `remittance_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '打款时间',
  `status` varchar(15) NOT NULL DEFAULT 'pending' COMMENT '打款状态',
  `clear_bn` varchar(50) DEFAULT '' COMMENT '清结算流水号',
  `channel` varchar(32) DEFAULT NULL COMMENT '打款通道',
  `is_retry` char(1) NOT NULL DEFAULT 'N' COMMENT '重试状态 Y: 重试 N: 未重试',
  `re_remittance` varchar(50) DEFAULT NULL COMMENT '重新打款人',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `ind_task_bn` (`task_bn`) USING BTREE,
  UNIQUE KEY `ind_cash_bn` (`cash_bn`) USING BTREE,
  KEY `ind_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_cash_task_log`;
CREATE TABLE IF NOT EXISTS `bwoil_order_cash_task_log` (
  `log_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_id` int(10) NOT NULL COMMENT '打款id',
  `remittance_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '打款时间',
  `status` varchar(15) NOT NULL COMMENT '打款状态',
  `remark` text COMMENT '打款备注',
  `remittance` varchar(50) DEFAULT '系统' COMMENT '触发人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_channel_orders`;
CREATE TABLE IF NOT EXISTS `bwoil_order_channel_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单号',
  `channel_order_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '渠道订单号',
  `channel_parent_order_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '渠道总订单号',
  `channel_product_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '产品标识号',
  `card_bn` bigint(32) unsigned DEFAULT NULL COMMENT '油卡号',
  `member_id` int(10) unsigned DEFAULT NULL COMMENT '会员编号',
  `redeem_code` varchar(200) DEFAULT NULL COMMENT '兑换码',
  `buy_account` varchar(50) NOT NULL COMMENT '购买手机号',
  `login_account` varchar(50) DEFAULT NULL COMMENT '会员手机号',
  `product_name` varchar(200) DEFAULT NULL COMMENT '产品名称',
  `price` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '产品金额',
  `total_period` int(11) NOT NULL DEFAULT '0' COMMENT '期限',
  `total_amount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '总金额',
  `final_amount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '实付金额',
  `refuel_channel` varchar(20) DEFAULT NULL COMMENT '渠道名称 京东=''jdshop''',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `card_status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '卡状态 active待兑换, redeemed已兑换, 已锁定locked, 已失效canceled',
  `pay_status` int(2) NOT NULL DEFAULT '0' COMMENT '到账状态 0待收款, 1已收款, 2退款中, 3已退款',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '-1 删除  0正常',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ind_channel_product_id` (`channel_product_id`),
  KEY `ind_member_id` (`member_id`),
  KEY `ind_buy_account` (`buy_account`),
  KEY `ind_login_account` (`login_account`),
  KEY `ind_channel_order_id` (`channel_order_id`),
  KEY `ind_channel_parent_order_id` (`channel_parent_order_id`),
  KEY `ind_redeem_code` (`redeem_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_color_pay`;
CREATE TABLE IF NOT EXISTS `bwoil_order_color_pay` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `member_id` int(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `op_id` mediumint(8) DEFAULT NULL COMMENT '操作员ID',
  `money` decimal(10,2) NOT NULL COMMENT '金额',
  `discount` decimal(10,2) NOT NULL COMMENT '折扣',
  `message` varchar(255) DEFAULT NULL COMMENT '备注',
  `payment_id` varchar(50) DEFAULT NULL COMMENT '支付单号',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单号',
  `channel_name` varchar(15) DEFAULT 'colorlife' COMMENT '渠道名称',
  `ret_info` varchar(1024) DEFAULT NULL COMMENT '第三方支付信息',
  `pay_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态:0：失败，1：成功，2：作废',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='彩生活支付';

DROP TABLE IF EXISTS `bwoil_order_conf`;
CREATE TABLE IF NOT EXISTS `bwoil_order_conf` (
  `conf_key` varchar(15) NOT NULL COMMENT '配置key',
  `conf_data` varchar(2000) NOT NULL COMMENT '配置内容json',
  `operator_id` int(11) NOT NULL COMMENT '操作人id',
  `operator_name` varchar(255) NOT NULL COMMENT '操作人',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作人ip信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`conf_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_hd_card`;
CREATE TABLE IF NOT EXISTS `bwoil_order_hd_card` (
  `card_bn` varchar(32) NOT NULL COMMENT '卡号',
  `card_crypt` varchar(32) NOT NULL COMMENT '卡密',
  `product_id` mediumint(8) NOT NULL COMMENT '货品ID',
  `product_name` varchar(200) DEFAULT NULL COMMENT '货品名称',
  `region_name` varchar(100) DEFAULT NULL COMMENT '地区名称',
  `region_id` int(11) NOT NULL COMMENT '地区ID',
  `oil_name` varchar(50) DEFAULT NULL COMMENT '油品名称',
  `oil_id` int(11) NOT NULL COMMENT '油品ID',
  `order_id` varchar(20) DEFAULT NULL COMMENT '订单号',
  `member_id` int(11) DEFAULT NULL COMMENT '绑定会员ID',
  `mobile` varchar(15) DEFAULT NULL COMMENT '绑定手机号',
  `bind_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '绑定时间',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `term` int(11) NOT NULL COMMENT '期数',
  `term_unit` char(1) NOT NULL COMMENT '期限单位(D: 天 M: 月)',
  `bind_status` char(1) NOT NULL COMMENT '绑定状态(Y:已绑定, N:未绑定)',
  `buy_user` varchar(200) DEFAULT NULL COMMENT '购买用户',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`card_bn`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节日卡表';

DROP TABLE IF EXISTS `bwoil_order_hd_card_gen`;
CREATE TABLE IF NOT EXISTS `bwoil_order_hd_card_gen` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `buyUser` varchar(200) DEFAULT NULL COMMENT '购买用户',
  `generator_cnt` int(11) NOT NULL COMMENT '生成数量',
  `crypt_len` int(11) NOT NULL COMMENT '密钥长度',
  `crypt_types` varchar(2000) NOT NULL COMMENT '密钥类型json',
  `product_id` mediumint(8) NOT NULL COMMENT '货品ID',
  `product_name` varchar(200) DEFAULT NULL COMMENT '货品名称',
  `operator_id` int(11) NOT NULL COMMENT '操作人id',
  `operator_name` varchar(255) NOT NULL COMMENT '操作人',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作人ip信息',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_insurance`;
CREATE TABLE IF NOT EXISTS `bwoil_order_insurance` (
  `insurance_id` int(10) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `insurance_date` int(10) DEFAULT NULL,
  `insurance_bn` varchar(96) DEFAULT NULL,
  `customer_name` varchar(24) DEFAULT NULL,
  `mobile` varchar(48) DEFAULT NULL,
  `certificate_type` char(72) DEFAULT NULL,
  `certificate_bn` varchar(120) DEFAULT NULL,
  `insurance_company` varchar(180) DEFAULT NULL,
  `product_code` varchar(1152) DEFAULT NULL,
  `from_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `to_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `insurance_money` decimal(22,0) DEFAULT NULL,
  `insurance_limit` decimal(22,0) DEFAULT NULL,
  `premium` decimal(22,0) DEFAULT NULL,
  `insurance_image` varchar(192) DEFAULT NULL,
  `status` char(48) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastmodify` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_items`;
CREATE TABLE IF NOT EXISTS `bwoil_order_items` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
  `order_id` varchar(20) NOT NULL COMMENT '订单号',
  `product_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '货品ID',
  `product_bn` varchar(40) DEFAULT NULL COMMENT '货品编号',
  `product_name` varchar(200) DEFAULT NULL COMMENT '货品名称',
  `product_attr` varchar(15) DEFAULT NULL COMMENT '产品属性（litre: 储油产品、amount: 金额产品）',
  `type_id` mediumint(8) DEFAULT NULL COMMENT '商品类型ID',
  `type_bn` varchar(30) DEFAULT NULL COMMENT '类型编号',
  `type_alisa` varchar(15) DEFAULT NULL COMMENT '类型别名',
  `rule_detail_id` int(10) DEFAULT NULL COMMENT '规则明细id',
  `action_solution` varchar(2000) DEFAULT NULL COMMENT '兑付规则JSON',
  `addon` varchar(2000) DEFAULT NULL COMMENT '明细商品的规格属性JSON',
  `item_type` varchar(15) NOT NULL DEFAULT 'product' COMMENT '(兼容旧数据)明细商品类型product,pkg,gift,adjunct,transfer',
  `card_type` varchar(15) NOT NULL DEFAULT 'virtual' COMMENT '(兼容旧数据)商品形态:virtual,actual,transfer',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单快照表';

DROP TABLE IF EXISTS `bwoil_order_jd_snapshot`;
CREATE TABLE IF NOT EXISTS `bwoil_order_jd_snapshot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `redeem_code` varchar(255) DEFAULT NULL,
  `order_json` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='京东生成兑换码保存商品兑换信息快照表';

DROP TABLE IF EXISTS `bwoil_order_log`;
CREATE TABLE IF NOT EXISTS `bwoil_order_log` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '订单日志ID',
  `rel_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '关联对象ID',
  `op_id` int(11) DEFAULT NULL COMMENT '操作员ID',
  `op_name` varchar(100) DEFAULT NULL COMMENT '操作人名称',
  `bill_type` varchar(15) NOT NULL DEFAULT 'order' COMMENT '操作人员姓名',
  `behavior` varchar(15) NOT NULL DEFAULT 'payments' COMMENT '日志记录操作的行为',
  `result` char(1) NOT NULL COMMENT '操作结果 Y/N',
  `log_text` varchar(2000) DEFAULT NULL COMMENT '操作内容',
  `addon` varchar(2000) DEFAULT NULL COMMENT '序列化数据',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `ind_rel_id` (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单日志';

DROP TABLE IF EXISTS `bwoil_order_recharge`;
CREATE TABLE IF NOT EXISTS `bwoil_order_recharge` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `member_id` mediumint(8) unsigned DEFAULT NULL COMMENT '会员用户名',
  `mobile` varchar(15) NOT NULL COMMENT '会员手机号',
  `member_bn` varchar(32) DEFAULT NULL COMMENT '会员编号',
  `member_name` varchar(100) NOT NULL COMMENT '会员名称',
  `trade_bn` varchar(32) DEFAULT NULL COMMENT '交易编号',
  `pay_bn` varchar(32) DEFAULT NULL COMMENT '支付单号',
  `to_acct_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '到帐时间',
  `pay_way` varchar(50) NOT NULL DEFAULT '0' COMMENT '支付方式',
  `pay_channel` varchar(15) NOT NULL DEFAULT '0' COMMENT '收款渠道',
  `pay_no` varchar(32) DEFAULT NULL COMMENT '支付单号',
  `order_refer_no` varchar(32) DEFAULT NULL COMMENT '第三方订单号',
  `recharge_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '充值金额',
  `account_remain` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '充值到帐后余额',
  `recharge_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态: 0:支付中 1: 已到账 2：支付失败',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值表';

DROP TABLE IF EXISTS `bwoil_order_redeem`;
CREATE TABLE IF NOT EXISTS `bwoil_order_redeem` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '交易ID，自增主键',
  `trade_bn` bigint(20) DEFAULT NULL COMMENT '交易编号',
  `card_bn` varchar(32) NOT NULL COMMENT '储油通卡号',
  `product_name` varchar(60) DEFAULT NULL COMMENT '产品名称',
  `card_type` varchar(30) NOT NULL COMMENT '卡类型已中台为准',
  `member_id` int(11) NOT NULL COMMENT '会员Id',
  `member_name` varchar(255) DEFAULT NULL COMMENT '会员名称',
  `mobile` varchar(15) NOT NULL COMMENT '会员手机号',
  `buy_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '购买时间',
  `buy_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '购买金额',
  `buy_liter` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '购买升数',
  `buy_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '购买油价',
  `trade_type` varchar(15) DEFAULT NULL COMMENT '交易类型 schedual: 定时任务 customer: 客户主动 adm: 管理员强制赎回',
  `sale_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '兑付额度:金额产品对应金额，升数产品对应升数',
  `sale_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '兑付金额',
  `sale_liter` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '兑付升数',
  `oil_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '兑付油价',
  `cpns_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑现金券抵扣',
  `profit_oil` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑油箱抵扣',
  `discount_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑折扣金额',
  `reduce_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑满减金额',
  `profit_money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑赠送金额',
  `profit_liter` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑赠送升数',
  `fixed_interet` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑固定利息',
  `float_interet` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已兑浮动收益',
  `fee` decimal(20,2) DEFAULT '0.00' COMMENT '手续费(元)',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_card_bn` (`card_bn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑付表';

DROP TABLE IF EXISTS `bwoil_order_redeem_plan`;
CREATE TABLE IF NOT EXISTS `bwoil_order_redeem_plan` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `card_bn` varchar(32) NOT NULL COMMENT '卡编号',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `sale_date` varchar(8) NOT NULL COMMENT '兑付日期',
  `term` int(11) NOT NULL COMMENT '期别',
  `cash_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '兑付额度升数/金额',
  `cash_remain` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '本期剩余兑付升数/金额',
  `cash_total` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '本期累计兑付升数/金额',
  `force_sale` char(1) NOT NULL COMMENT '是否强制兑付:Y:是 N:否',
  `sale_flag` char(1) NOT NULL COMMENT '已兑付:Y:是 N:否',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_card_bn` (`card_bn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑付计划表';

DROP TABLE IF EXISTS `bwoil_order_redeem_ticket`;
CREATE TABLE IF NOT EXISTS `bwoil_order_redeem_ticket` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `auth_type` char(1) NOT NULL DEFAULT '1' COMMENT '0:自动审核 1:手工审核',
  `redeem_bn` varchar(32) NOT NULL COMMENT '兑付流水号',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `member_bn` varchar(32) NOT NULL COMMENT '会员编号',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '加油金额',
  `tickt_code` varchar(32) NOT NULL COMMENT '发票代码',
  `tickt_no` varchar(32) NOT NULL COMMENT '发票号码',
  `tickt_company` varchar(500) NOT NULL COMMENT '收款单位',
  `tickt_date` varchar(14) DEFAULT NULL COMMENT '发票日期',
  `tax_iden` varchar(500) NOT NULL COMMENT '纳税人识别码',
  `attachment_image` varchar(32) DEFAULT NULL COMMENT '发票/小票附件id',
  `audit_status` char(1) NOT NULL DEFAULT '0' COMMENT '审核状态0,1,2',
  `operator_id` int(11) DEFAULT NULL COMMENT '审核人',
  `audit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `audit_ip` varchar(20) DEFAULT NULL COMMENT '审核IP',
  `audit_remark` varchar(200) DEFAULT NULL COMMENT '审核备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小票兑付表';

DROP TABLE IF EXISTS `bwoil_order_refuel`;
CREATE TABLE IF NOT EXISTS `bwoil_order_refuel` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '代充交易编号',
  `order_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '加油总额',
  `order_cash` decimal(20,2) DEFAULT '0.00' COMMENT '购买单价',
  `proid` int(10) DEFAULT NULL COMMENT '产品id',
  `num` int(10) DEFAULT NULL COMMENT '数量',
  `charge_type` char(1) NOT NULL DEFAULT '1' COMMENT '加油卡类型 （1:中石化、2:中石油；默认为1)',
  `provider_type` char(1) NOT NULL DEFAULT '1' COMMENT '渠道类型 （1:聚合、2:欧飞；默认为1)',
  `card_bn` bigint(20) NOT NULL COMMENT '充值卡号',
  `card_tel` varchar(50) DEFAULT NULL COMMENT '持卡人手机号',
  `card_holder` varchar(50) DEFAULT NULL COMMENT '持卡人',
  `sporder_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '流水号',
  `member_id` int(20) DEFAULT NULL COMMENT '会员名称',
  `card_trade_bn` bigint(20) DEFAULT NULL COMMENT '交易编号',
  `ycy_card_bn` bigint(32) DEFAULT NULL COMMENT '储油卡卡号',
  `fuel_status` char(1) NOT NULL DEFAULT '0' COMMENT '充值状态:0充值中 1成功 2失败 9撤销',
  `disabled` char(1) DEFAULT 'N' COMMENT '无效 Y:是 N:否',
  `retry` char(1) DEFAULT 'N' COMMENT '是否再次尝试:Y:是 N:否',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '充值时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_refuel_card`;
CREATE TABLE IF NOT EXISTS `bwoil_order_refuel_card` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '加油卡id',
  `member_id` int(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `card_no` varchar(20) NOT NULL COMMENT '加油卡卡号',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `rech_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已充值总额',
  `unrech_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '待充值总额',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `bind_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '绑定时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '充值时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `IDX_MEMBER_ID` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油卡表';

DROP TABLE IF EXISTS `bwoil_order_refunds`;
CREATE TABLE IF NOT EXISTS `bwoil_order_refunds` (
  `refund_id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '退款单id',
  `order_id` varchar(32) NOT NULL COMMENT '订单号',
  `card_bn` varchar(32) DEFAULT NULL COMMENT '退款卡号',
  `money` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '退款金额',
  `member_id` int(11) DEFAULT NULL COMMENT '会员id',
  `mobile` varchar(15) DEFAULT NULL COMMENT '会员账户',
  `member_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `refund_status` varchar(10) NOT NULL DEFAULT 'ready' COMMENT '退款状态',
  `apply_time` varchar(8) NOT NULL COMMENT '申请时间',
  `audit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `refund_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '退款时间',
  `auditer` mediumint(8) DEFAULT NULL COMMENT '审核人',
  `consenter` mediumint(8) DEFAULT NULL COMMENT '退款人',
  `reasion` varchar(50) DEFAULT NULL COMMENT '退款原因',
  `audit_memo` varchar(50) DEFAULT NULL COMMENT '审核备注',
  `confirm_memo` varchar(50) DEFAULT NULL COMMENT '确认退款备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`refund_id`),
  KEY `idx_apply_time` (`apply_time`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_sale`;
CREATE TABLE IF NOT EXISTS `bwoil_order_sale` (
  `sale_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sale_name` varchar(12) DEFAULT NULL COMMENT '销售人员名称',
  `sale_mobile` varchar(12) NOT NULL COMMENT '销售人员手机号码',
  `member_mobile` varchar(12) NOT NULL COMMENT '购买人手机号码',
  `call_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '呼叫时间',
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_sale_isomerism`;
CREATE TABLE IF NOT EXISTS `bwoil_order_sale_isomerism` (
  `isomerism_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `sale_name` varchar(12) NOT NULL COMMENT '外呼人员名称',
  `order_id` varchar(20) NOT NULL COMMENT '订单号',
  `product_name` varchar(20) NOT NULL COMMENT '商品名称',
  `card_type` varchar(20) NOT NULL COMMENT '商品类型',
  `term` varchar(10) NOT NULL COMMENT '产品期数',
  `order_amount` decimal(12,2) NOT NULL COMMENT '订单金额',
  `real_pay` decimal(12,2) NOT NULL COMMENT '实付金额',
  `source` varchar(12) NOT NULL COMMENT '平台来源',
  `mobile` varchar(12) NOT NULL COMMENT '会员账号',
  `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '支付时间',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '会员注册时间',
  `channel_name` varchar(20) DEFAULT NULL COMMENT '分销账户名称',
  PRIMARY KEY (`isomerism_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_scan_refuel`;
CREATE TABLE IF NOT EXISTS `bwoil_order_scan_refuel` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int(11) NOT NULL COMMENT 'ID',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `member_bn` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `order_id` varchar(20) DEFAULT NULL COMMENT '订单号',
  `pay_bn` varchar(20) DEFAULT NULL COMMENT '打款编号',
  `fuel_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '加油总金额',
  `real_pay_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '实收金额',
  `protocol_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '协议金额',
  `profit_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `settle_fee_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '结算手续费',
  `pay_card_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '支付加油卡金额',
  `pay_lc_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '支付预存款金额',
  `profit_acitive_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '活动优惠金额',
  `settle_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '结算金额',
  `pay_status` varchar(20) DEFAULT NULL COMMENT '银行转账状态',
  `expire_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '过期时间',
  `valid_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '验证时间',
  `refuel_station_id` int(10) NOT NULL COMMENT '加油站id',
  `refuel_station` varchar(200) DEFAULT NULL COMMENT '加油站名称',
  `oil_name` varchar(200) DEFAULT NULL COMMENT '油品名称',
  `oil_gan_no` int(5) DEFAULT NULL COMMENT '油枪号',
  `settle_way` varchar(15) DEFAULT NULL COMMENT '结算方式',
  `wechat_rate` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '微信折扣',
  `channel_rate` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '渠道折扣',
  `waiter` varchar(200) DEFAULT NULL COMMENT '收银员名称',
  `oil_price` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '油站挂牌价',
  `refule_liter` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '加油升数',
  `vcode` varchar(200) DEFAULT NULL COMMENT '验证码',
  `refuel_type` varchar(200) DEFAULT NULL COMMENT '加油类型',
  `is_sucess` varchar(1) DEFAULT NULL COMMENT '订单是否成功',
  `pay_way` varchar(200) DEFAULT NULL COMMENT '支付方式',
  `ad_source` varchar(20) DEFAULT NULL COMMENT '订单来源',
  `order_status` varchar(15) DEFAULT NULL COMMENT '订单状态',
  `invoice` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_order_trans_flow`;
CREATE TABLE IF NOT EXISTS `bwoil_order_trans_flow` (
  `flow_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资金流水ID',
  `reference_id` varchar(30) NOT NULL COMMENT '关联表id',
  `trade_type` varchar(20) NOT NULL COMMENT '交易类型',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `flow_status` varchar(2) NOT NULL COMMENT '状态',
  `flow_desc` varchar(100) NOT NULL COMMENT '操作说明',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`flow_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_payments`;
CREATE TABLE IF NOT EXISTS `bwoil_payments` (
  `id` bigint(30) unsigned NOT NULL AUTO_INCREMENT COMMENT '支付单号',
  `order_no` varchar(32) DEFAULT '' COMMENT '订单号',
  `trade_status` varchar(20) NOT NULL DEFAULT 'WAIT' COMMENT '支付状态,参考TradeStatus',
  `trade_error_msg` varchar(50) DEFAULT NULL COMMENT '支付单错误信息',
  `amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '支付金额',
  `member_id` int(10) DEFAULT NULL COMMENT '会员用户ID',
  `pay_type` varchar(15) DEFAULT 'order' COMMENT '支付类型：order 订单 recharge 充值',
  `pay_channel` varchar(20) NOT NULL COMMENT '支付方式',
  `pay_channel_name` varchar(100) DEFAULT NULL COMMENT '支付方式名称',
  `subject` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `payed_account` varchar(50) DEFAULT NULL COMMENT '收款账号',
  `payed_bank` varchar(50) DEFAULT NULL COMMENT '收款银行',
  `account` varchar(50) DEFAULT NULL COMMENT '支付账户',
  `currency` varchar(10) DEFAULT NULL COMMENT '货币',
  `pay_fee` int(10) DEFAULT '0' COMMENT '支付网关费用',
  `ip` varchar(20) DEFAULT NULL COMMENT '支付IP',
  `extra` longtext COMMENT '额外参数',
  `trade_no` varchar(50) DEFAULT NULL COMMENT '支付单交易编号',
  `third_trade_no` varchar(50) DEFAULT NULL COMMENT '第三方支付单号',
  `return_url` varchar(100) DEFAULT NULL COMMENT '支付返回地址',
  `thirdparty_account` varchar(50) DEFAULT NULL COMMENT '第三方支付账户',
  `combine_order` tinyint(1) DEFAULT '0' COMMENT '是否组合支付订单',
  `charge_order` tinyint(1) DEFAULT '0' COMMENT '充值支付单是否充值成功',
  `notify_order` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已通知订单状态',
  `send_erp` tinyint(1) NOT NULL DEFAULT '0' COMMENT '同步ERP',
  `confirm_time` timestamp NULL DEFAULT NULL COMMENT '确认时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付完成时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` char(2) DEFAULT NULL COMMENT '状态： -1删除',
  PRIMARY KEY (`id`),
  KEY `ind_member_id` (`member_id`) USING BTREE,
  KEY `ind_order_no` (`order_no`) USING BTREE,
  KEY `ind_pay_time` (`pay_time`) USING BTREE,
  KEY `ind_trade_statue` (`trade_status`),
  KEY `ind_trade_no` (`trade_no`),
  KEY `ind_charge_order` (`charge_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付单记录';

DROP TABLE IF EXISTS `bwoil_payment_behavior`;
CREATE TABLE IF NOT EXISTS `bwoil_payment_behavior` (
  `record_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `member_id` varchar(20) NOT NULL DEFAULT '' COMMENT '会员用户名',
  `pay_account` varchar(50) NOT NULL DEFAULT '' COMMENT '支付账户',
  `payment_id` varchar(20) NOT NULL DEFAULT '' COMMENT '支付单号',
  `pay_type` varchar(15) NOT NULL DEFAULT 'online' COMMENT '֧?????? online,offline,deposit',
  `channel` varchar(20) NOT NULL COMMENT '支付方式名称',
  `source` varchar(10) DEFAULT 'pc' COMMENT '平台来源 pc,app,wap,weixin',
  `money` bigint(20) NOT NULL DEFAULT '0' COMMENT '支付金额',
  `is_quick_pay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否快捷支付',
  `bank_bn` varchar(32) NOT NULL DEFAULT '' COMMENT '银行简码',
  `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付完成时间',
  PRIMARY KEY (`record_id`),
  KEY `inx_member_id` (`member_id`),
  KEY `inx_pay_account` (`pay_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付行为记录';

DROP TABLE IF EXISTS `bwoil_pay_channel_config`;
CREATE TABLE IF NOT EXISTS `bwoil_pay_channel_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) DEFAULT NULL,
  `channel_code` varchar(50) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `status` int(11) NOT NULL,
  `pay_fee_rate` decimal(3,3) DEFAULT NULL,
  `pay_fee_min` decimal(3,2) DEFAULT NULL,
  `pay_fee_max` decimal(3,2) DEFAULT NULL,
  `auth_fee` decimal(3,2) DEFAULT NULL,
  `config` longtext,
  `merchant` varchar(50) NOT NULL COMMENT '商户号',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK3k8acq8ycdxyy4pt7ea1eem5v` (`appid`) USING BTREE,
  KEY `FK6ejvhy6tp4u6pcixht99wfqf8` (`channel_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS `bwoil_product`;
CREATE TABLE IF NOT EXISTS `bwoil_product` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '货品ID',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `bn` varchar(30) DEFAULT NULL COMMENT '货号',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '货品名称',
  `is_default` char(1) NOT NULL DEFAULT 'N' COMMENT '是否为默认货品 Y/N',
  `is_index` char(1) DEFAULT 'N' COMMENT '是否首页展示 Y/N',
  `is_new` char(1) DEFAULT 'N' COMMENT '是否为新手产品 Y/N',
  `marketable` char(1) NOT NULL DEFAULT 'N' COMMENT '上架 Y/N',
  `spec_value` varchar(500) NOT NULL COMMENT '规格信息json',
  `spec_desc` varchar(250) NOT NULL COMMENT '规格值',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ind_bn` (`bn`),
  KEY `idx_goods_id` (`goods_id`),
  KEY `idx_marketable` (`marketable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_area`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_area` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '区域序号',
  `local_name` varchar(50) NOT NULL DEFAULT '' COMMENT '地区名称',
  `pkg` varchar(20) NOT NULL DEFAULT '' COMMENT '地区包的类别, 中国/外国等. 中国大陆的编号目前为mainland',
  `p_region_id` int(10) DEFAULT NULL COMMENT '上一级地区的序号',
  `region_path` varchar(255) DEFAULT NULL COMMENT '序号层级排列结构',
  `region_grade` mediumint(8) DEFAULT NULL COMMENT '地区层级',
  `area_code` varchar(5) DEFAULT NULL COMMENT '区号',
  `ordernum` int(8) DEFAULT NULL COMMENT '排序',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_local_name` (`local_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_channel_products`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_channel_products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `product_id` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '产品ID',
  `channel_product_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '产品标识号',
  `name` varchar(200) DEFAULT NULL COMMENT '产品名称',
  `money` decimal(20,3) DEFAULT NULL COMMENT '金额',
  `term` varchar(100) DEFAULT NULL COMMENT '期限',
  `refuel_channel` varchar(20) DEFAULT NULL COMMENT '渠道  jdshop=京东',
  `availability` char(1) DEFAULT 'Y' COMMENT '有效 Y/N',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `rule_detail_id` int(10) DEFAULT NULL COMMENT '规则明细id',
  PRIMARY KEY (`id`),
  KEY `ind_channel_product_id` (`channel_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道产品兑换关系表';

DROP TABLE IF EXISTS `bwoil_prod_goods`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `public_cloud_attr` char(1) NOT NULL COMMENT '属性（1：大众储油；2：云储油）',
  `bn` varchar(200) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `type_id` mediumint(8) DEFAULT NULL COMMENT '商品类型id',
  `rule_id` int(10) DEFAULT NULL COMMENT '兑付规则id',
  `cat_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '分类',
  `marketable` char(1) NOT NULL DEFAULT 'N' COMMENT '上架 Y/N',
  `uptime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上架时间',
  `downtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下架时间',
  `auth_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `instructions` varchar(5000) DEFAULT NULL COMMENT '商品简介',
  `detail` varchar(5000) DEFAULT NULL COMMENT '产品详情',
  `cash_safe` varchar(5000) DEFAULT NULL COMMENT '资金安全说明',
  `importantNote` varchar(5000) DEFAULT NULL COMMENT '重要提示',
  `audit_status` char(1) NOT NULL DEFAULT '0' COMMENT '审核状态，：0：待审核，1：审核通过，2：审核不通过',
  `audit_remark` varchar(500) DEFAULT NULL COMMENT '审核意见',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_bn` (`bn`),
  KEY `idx_marketable` (`marketable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_goods_rule`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_goods_rule` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '规则id',
  `goods_type_id` int(10) NOT NULL COMMENT '商品类型id',
  `name` varchar(50) NOT NULL COMMENT '规则名称',
  `cat_type` varchar(2) NOT NULL COMMENT '规则分类 1 大众储油；2：云储油',
  `ios_desc` varchar(2000) DEFAULT NULL COMMENT 'ios规则描述',
  `android_desc` varchar(2000) DEFAULT NULL COMMENT 'android规则描述',
  `ios_example` varchar(2000) DEFAULT NULL COMMENT 'ios示例',
  `android_example` varchar(2000) DEFAULT NULL COMMENT 'android示例',
  `prod_attr` varchar(15) NOT NULL COMMENT '产品类型 litre: 金额产品、amount: 储油产品',
  `area_spec_ids` varchar(500) DEFAULT NULL COMMENT '地区规格(规格id数组)',
  `oil_spec_ids` varchar(500) DEFAULT NULL COMMENT '油品规格(规格id数组)',
  `liter_spec_ids` varchar(500) DEFAULT NULL COMMENT '升数规格(规格id数组)',
  `money_spec_ids` varchar(500) DEFAULT NULL COMMENT '金额规格(规格id数组)',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_goods_type_id` (`goods_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_goods_rule_detail`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_goods_rule_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '规则明细id',
  `rule_id` int(10) NOT NULL COMMENT '规则id',
  `detail` varchar(3000) NOT NULL COMMENT '规则明细json字符串',
  `enable` char(1) NOT NULL DEFAULT 'Y' COMMENT '启用 Y/N',
  `is_default` char(1) NOT NULL DEFAULT 'N' COMMENT '是否为默认规则 Y/N',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_rule_id` (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_goods_type`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_goods_type` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '类型序号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '类型名称',
  `type_bn` varchar(32) DEFAULT NULL COMMENT '类型编号',
  `alias` varchar(15) NOT NULL COMMENT '别名',
  `type_attr` varchar(15) DEFAULT NULL COMMENT '所属类型（litre:  储油产品、amount: 金额产品）',
  `max_discount` decimal(5,2) DEFAULT NULL COMMENT '最大折扣',
  `day_limit_amout` decimal(10,2) DEFAULT NULL COMMENT '单日限购量(金额/升数)',
  `year_limit_amout` decimal(10,2) DEFAULT NULL COMMENT '年限购量(金额/升数)',
  `max_amount` decimal(10,2) DEFAULT NULL COMMENT '最大持有量(金额/升数)',
  `spec_ids` varchar(100) DEFAULT NULL COMMENT '规格ID数组',
  `ios_desc` varchar(500) DEFAULT NULL COMMENT 'ios描述',
  `android_desc` varchar(500) DEFAULT NULL COMMENT 'andriod描述',
  `type_desc` varchar(500) DEFAULT NULL COMMENT '类型描述',
  `detail_desc` varchar(500) DEFAULT NULL COMMENT '商品详情描述',
  `m_desc` varchar(500) DEFAULT NULL COMMENT 'M站详情描述',
  `sale_desc` varchar(500) DEFAULT NULL COMMENT '兑付描述',
  `keyword_image` varchar(255) DEFAULT NULL COMMENT '特点描述图片',
  `label` varchar(255) DEFAULT NULL COMMENT '标签',
  `p_order` mediumint(8) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `pc_catalog` char(1) DEFAULT NULL COMMENT 'pc分类显示(0.全部 1.储油卡 2.加油卡 3.保值卡 4.汇油宝)',
  `app_catalog` char(1) DEFAULT NULL COMMENT 'app分类显示(0.全部 1.储油卡 2.加油卡 3.保值卡 4.汇油宝)',
  `m_catalog` char(1) DEFAULT NULL COMMENT 'm站分类显示(0.全部 1.储油卡 2.加油卡 3.保值卡 4.汇油宝)',
  `forbid_sale` char(1) NOT NULL DEFAULT 'N' COMMENT '是否禁售 Y/N',
  `forbid_sale_begin` time NOT NULL DEFAULT '00:00:00' COMMENT '禁售开始时间',
  `forbid_sale_end` time NOT NULL DEFAULT '00:00:00' COMMENT '禁售结束时间',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_home_show`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_home_show` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '产品设置ID',
  `thumbnail` varchar(255) NOT NULL COMMENT '缩略图',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `number_of_periods` int(10) DEFAULT NULL COMMENT '期数',
  `rule_detail_id` int(10) NOT NULL COMMENT '规则明细ID',
  `product_id` mediumint(8) NOT NULL COMMENT '关联货品ID',
  `prod_goods_id` bigint(20) NOT NULL COMMENT '关联商品ID',
  `show_app` char(1) NOT NULL DEFAULT '0' COMMENT 'app是否展示：0否 1是',
  `show_m` char(1) NOT NULL DEFAULT '0' COMMENT 'M站是否展示：0否 1是',
  `show_pc` char(1) NOT NULL DEFAULT '0' COMMENT 'pc是否展示：0否 1是',
  `category` varchar(2) DEFAULT NULL COMMENT '首页分类(1：新手体验卡，2：加油卡，3：储油增值，4：油品保值 ，5：固定回报 )',
  `level` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `enabled` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否启用：0否 1是',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_log`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `log_type` int(8) NOT NULL COMMENT '日志类型见ProdLogTypeEnums',
  `remark` varchar(2000) NOT NULL COMMENT '操作信息',
  `table_id` varchar(32) DEFAULT NULL COMMENT '表记录主键',
  `op_data` varchar(2000) NOT NULL COMMENT '请求数据信息json',
  `op_result` char(1) NOT NULL COMMENT '操作结果 Y/N',
  `operator_id` int(11) NOT NULL COMMENT '操作人id',
  `operator_name` varchar(255) NOT NULL COMMENT '操作人',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作人ip信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_log_type` (`log_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_oil`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_oil` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '油品ID',
  `oil_bn` varchar(20) NOT NULL COMMENT '油品编号',
  `oil_name` varchar(32) NOT NULL COMMENT '油品名称',
  `is_station` char(1) DEFAULT 'Y' COMMENT '加油站使用:Y N',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_oil_bn` (`oil_bn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_oil_price`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_oil_price` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '油价ID',
  `oil_date` varchar(8) NOT NULL COMMENT '油价日期',
  `region_id` int(10) NOT NULL COMMENT '地区ID',
  `region_name` varchar(255) NOT NULL COMMENT '地区名称',
  `oil_id` mediumint(8) NOT NULL COMMENT '油品ID',
  `oil_name` varchar(20) NOT NULL COMMENT '油品名称',
  `sale_price` decimal(8,2) NOT NULL COMMENT '平台卖出价(元/升)',
  `buy_price` decimal(8,2) NOT NULL COMMENT '平台买入价(元/升)',
  `audit_status` char(1) NOT NULL DEFAULT '0' COMMENT '审核状态 0: 待审核 1: 审核通过 2: 审核拒绝 ',
  `applyer_id` int(10) NOT NULL COMMENT '维护人ID',
  `applyer_name` varchar(20) NOT NULL COMMENT '维护人姓名',
  `auditor_id` int(10) DEFAULT NULL COMMENT '审核人id',
  `auditor_name` varchar(50) DEFAULT NULL COMMENT '审核人',
  `auditor_remark` varchar(500) DEFAULT NULL COMMENT '审核备注',
  `auth_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_oil_date` (`oil_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_spec`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_spec` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '规格id',
  `spec_name` varchar(50) NOT NULL DEFAULT '' COMMENT '规格名称',
  `p_order` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `spec_attr` char(1) NOT NULL DEFAULT '0' COMMENT '规格属性0 升数,1 金额,2 油品,3 地区',
  `disabled` char(1) NOT NULL DEFAULT 'N' COMMENT '是否可用Y/N',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bwoil_prod_spec_values`;
CREATE TABLE IF NOT EXISTS `bwoil_prod_spec_values` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '规格值ID',
  `spec_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '规格ID',
  `spec_attr` char(1) NOT NULL DEFAULT '0' COMMENT '规格属性0 升数,1 金额,2 油品,3 地区',
  `spec_value` varchar(100) NOT NULL DEFAULT '' COMMENT '规格值',
  `spec_name` varchar(500) NOT NULL DEFAULT '' COMMENT '规格值名称',
  `p_order` mediumint(8) NOT NULL DEFAULT '50' COMMENT '排序',
  `relation_id` int(10) DEFAULT NULL COMMENT '关联ID（油品或地区）',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态:-1:删除 0:正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_spec_attr` (`spec_attr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

ALTER TABLE bwoil_base_log_email
  CHANGE `busi_code` `busi_code` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '业务类型',
  CHANGE `tmpl_type` `tmpl_type` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci NULL COMMENT '模板类型',
  CHANGE `sendto` `sendto` VARCHAR(255) CHARSET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收方';
ALTER TABLE `bwoil_member_bank` ADD COLUMN `baofu_tag` VARCHAR(5) NULL COMMENT '宝付认证查询标识解决绑定关系问题' AFTER `baofu_type`;
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD COLUMN `pay_no` VARCHAR(255) DEFAULT '' COMMENT '支付单号(支付用)';
ALTER TABLE bwoil_operation_gasstation_refuel_record ADD COLUMN `order_refer_no` VARCHAR(255) DEFAULT '' COMMENT '第三方订单号(支付用)';
ALTER TABLE bwoil_operation_gasstation ADD COLUMN `type` INT(1) NOT NULL DEFAULT 1 COMMENT '加油站类型, 1已签约, 0未签约';
ALTER TABLE `bwoil_operation_gasstation_cash_info` CHANGE COLUMN `source` `source` ENUM('pc','app','wap','weixin','applet','face','scan') NOT NULL DEFAULT 'pc' COMMENT '来源' AFTER `station_id`;
ALTER TABLE bwoil_operation_gasstation_activity_item ADD COLUMN `release` INT(1) DEFAULT 1 COMMENT '是否发布字段, 默认1发布, 0不发布';
ALTER TABLE bwoil_order CHANGE pay_no `pay_no` VARCHAR(64) DEFAULT NULL COMMENT '支付单号';
ALTER TABLE bwoil_order CHANGE order_refer_no `order_refer_no` VARCHAR(64) DEFAULT NULL COMMENT '第三方订单号';
ALTER TABLE bwoil_order_refuel CHANGE card_bn `card_bn` VARCHAR(32) NOT NULL COMMENT '充值卡号';
ALTER TABLE bwoil_order_refuel CHANGE card_trade_bn `card_trade_bn` VARCHAR(50) DEFAULT NULL COMMENT '交易编号';
ALTER TABLE bwoil_order_refuel CHANGE ycy_card_bn `ycy_card_bn` VARCHAR(32) DEFAULT NULL COMMENT '储油卡卡号';
ALTER TABLE `bwoil_order_conf` ALTER `conf_key`
DROP DEFAULT; ALTER TABLE `bwoil_order_conf` CHANGE COLUMN `conf_key` `conf_key` VARCHAR(30) NOT NULL COMMENT '配置key' FIRST;