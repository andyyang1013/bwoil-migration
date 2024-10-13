package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class BwoilPayBehaviorMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "支付服务-支付行为-历史数据迁移";
    }
}