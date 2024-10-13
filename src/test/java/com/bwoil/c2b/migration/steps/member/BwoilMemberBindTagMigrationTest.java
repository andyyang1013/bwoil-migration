package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.steps.BaseTest;

import static org.junit.Assert.*;

public class BwoilMemberBindTagMigrationTest extends BaseTest {

    @Override
    public String getStepName() {
        return "会员服务-绑定第三方平台（微信）-历史数据迁移";
    }
}