package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class BwoilWeChatMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "支付服务-微信支付配置-历史数据迁移";
    }
}