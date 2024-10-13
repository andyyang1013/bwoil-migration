package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstationStaffShift;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstationStaffShift;
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

@Configuration("bwoilOperationGasstationStaffShiftMigration")
public class BwoilOperationGasstationStaffShiftMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationStaffShiftMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationStaffShiftMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationStaffShift, BwoilOperationGasstationStaffShift>().factory(stepBuilderFactory).name("运营服务-加油站StaffShift职员班次表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationStaffShiftMigrationReader")
    public ItemReader<OriginOperationGasstationStaffShift> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_station_work_shift";
        return new StepReaderBuilder<OriginOperationGasstationStaffShift>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstationStaffShift.class).multithreaded(true).sortKeys("shift_id").build();
    }

    @Bean("bwoilOperationGasstationStaffShiftMigrationProcessor")
    public ItemProcessor<OriginOperationGasstationStaffShift, BwoilOperationGasstationStaffShift> processor() {
        return item -> {
            BwoilOperationGasstationStaffShift target = new BwoilOperationGasstationStaffShift();
            BeanUtils.copyProperties(item, target);
            if (item.getStartTime() == null || item.getStartTime() == 0) {
                target.setStartTime(null);
            } else {
                target.setStartTime(new Date(Long.parseLong(item.getStartTime().toString()) * 1000L));
            }
            if (item.getEndTime() == null || item.getEndTime() == 0) {
                target.setEndTime(null);
            } else {
                target.setEndTime(new Date(Long.parseLong(item.getEndTime().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationStaffShiftMigrationWriter")
    public ItemWriter<BwoilOperationGasstationStaffShift> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_staff_shift (shift_id, station_id, staff_id, start_time, end_time, settlement_id, total_money, total_count) VALUES (:shiftId, :stationId, :staffId, :startTime, :endTime, :settlementId, :totalMoney, :totalCount)";
        return new StepWriterBuilder<BwoilOperationGasstationStaffShift>().dataSource(dataSource).sql(sqlStr).build();
    }
}
