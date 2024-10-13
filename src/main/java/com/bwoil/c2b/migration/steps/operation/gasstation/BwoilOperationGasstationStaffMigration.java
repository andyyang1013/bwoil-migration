package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationStaff;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationStaff;
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
import java.util.Date;

@Configuration("bwoilOperationGasstationStaffMigration")
public class BwoilOperationGasstationStaffMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationStaffMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationStaffMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationStaff, BwoilOperationGasstationStaff>().factory(stepBuilderFactory).name("运营服务-加油站stationStaff职员表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationStaffMigrationReader")
    public ItemReader<OriginOperationGasstationStaff> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_station_staff";
        return new StepReaderBuilder<OriginOperationGasstationStaff>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationStaff.class).multithreaded(true).sortKeys("staff_id").build();
    }

    @Bean("bwoilOperationGasstationStaffMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationStaff, BwoilOperationGasstationStaff> processor() {
        return item -> {
            BwoilOperationGasstationStaff target = new BwoilOperationGasstationStaff();
            BeanUtils.copyProperties(item, target);
            if (item.getCreated() == null || item.getCreated() == 0) {
                target.setCreated(null);
            } else {
                target.setCreated(new Date(Long.parseLong(item.getCreated().toString()) * 1000L));
            }
            if (item.getLastmodify() == null || item.getLastmodify() == 0) {
                target.setLastmodify(null);
            } else {
                target.setLastmodify(new Date(Long.parseLong(item.getLastmodify().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationStaffMigrationWriter")
    public ItemWriter<BwoilOperationGasstationStaff> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_staff (staff_id, station_id, parent_member_id, user_name, mobile, password, true_name, type_id, status, lastmodify, created, pwd_error) VALUES (:staffId, :stationId, :parentMemberId, :userName, :mobile, :password, :trueName, :typeId, :status, :lastmodify, :created, :pwdError)";
        return new StepWriterBuilder<BwoilOperationGasstationStaff>().dataSource(dataSource).sql(sqlStr).build();
    }
}
