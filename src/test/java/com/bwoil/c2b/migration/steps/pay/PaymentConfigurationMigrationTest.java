package com.bwoil.c2b.migration.steps.pay;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class PaymentConfigurationMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "支付服务-支付单记录-历史数据迁移";
    }
}