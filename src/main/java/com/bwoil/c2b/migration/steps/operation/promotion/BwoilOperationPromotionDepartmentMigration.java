package com.bwoil.c2b.migration.steps.operation.promotion;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.origin.OriginOperationChannelDepartment;
import com.bwoil.c2b.migration.steps.operation.promotion.pojo.target.BwoilOperationChannelDepartment;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName BwoilOperationChannelDepartmentMigration
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:59
 **/
@Configuration("bwoilOperationChannelDepartmentMigration")
public class BwoilOperationPromotionDepartmentMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationPromotionDepartmentMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationChannelDepartmentMigrationStep")
    public Step step(@Qualifier("channelDepartmentMigrationReader") ItemReader<OriginOperationChannelDepartment> reader,
                     @Qualifier("channelDepartmentMigrationProcessor") ItemProcessor<OriginOperationChannelDepartment, BwoilOperationChannelDepartment> processor,
                     @Qualifier("channelDepartmentMigrationWriter") ItemWriter<BwoilOperationChannelDepartment> writer) {
        return new StepBuilder<OriginOperationChannelDepartment, BwoilOperationChannelDepartment>().factory(stepBuilderFactory).name("运营服务-分销账户部门关系-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("channelDepartmentMigrationReader")
    public ItemReader<OriginOperationChannelDepartment> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = " SELECT * from sdb_promotion_department";
        return new StepReaderBuilder<OriginOperationChannelDepartment>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationChannelDepartment.class).multithreaded(true).sortKeys("department_id").build();
    }

    @Bean("channelDepartmentMigrationProcessor")
    public ItemProcessor<OriginOperationChannelDepartment, BwoilOperationChannelDepartment> processor() {
        return item -> {
            BwoilOperationChannelDepartment target = new BwoilOperationChannelDepartment();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getDepartmentId() == null ? 0 : item.getDepartmentId());
            target.setPayMethod(item.getPayAppId() == null ? "" : item.getPayAppId());
            target.setRegisterSource(item.getSource() == null ? "" : item.getSource());
            target.setType(target.getType() == null ? 1 : target.getType());
            target.setParentId(target.getParentId() == null ? 0 : target.getParentId());
            target.setCreateTime(TimeStampUtil.getTimestampWithNull(item.getCreateTime()));
            target.setUptime(TimeStampUtil.getTimestampWithNull(item.getUptime()));
            return target;
        };
    }

    @Bean("channelDepartmentMigrationWriter")
    public ItemWriter<BwoilOperationChannelDepartment> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_promotion_channel_department " +
                "(id,parent_id,node_name,type,register_source,pay_method,create_time,uptime)" +
                " VALUES" +
                " (:id,:parentId,:nodeName,:type,:registerSource,:payMethod,:createTime,:uptime)";
        return new StepWriterBuilder<BwoilOperationChannelDepartment>().dataSource(dataSource).sql(sqlStr).build();
    }
}
