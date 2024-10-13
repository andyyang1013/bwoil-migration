package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.steps.BaseTest;

public class BwoilOrderCardCycleMigrationTest extends BaseTest{

	@Override
	public String getStepName() {
		return "订单服务-小票兑付、扫码加油卡交易表-历史数据迁移";
	}

}
