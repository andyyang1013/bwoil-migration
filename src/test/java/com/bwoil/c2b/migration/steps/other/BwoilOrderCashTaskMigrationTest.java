package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class BwoilOrderCashTaskMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "订单服务-打款任务表-历史数据迁移";
    }
}