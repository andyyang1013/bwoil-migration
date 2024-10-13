package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class BwoilOperationGasstationRemittanceLogMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "运营服务-加油站打款回调日志表-历史数据迁移";
    }
}