package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class BwoilUnionPayMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "支付服务-组合支付配置-历史数据迁移";
    }
}