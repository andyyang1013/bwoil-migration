package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.origin.OriginOperationGasstationImages;
import com.bwoil.c2b.migration.steps.other.pojo.target.BwoilOperationGasstationImages;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration("bwoilOperationGasstationImagesMigration")
public class BwoilOperationGasstationImagesMigration {


    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationImagesMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationImagesStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstationImages, BwoilOperationGasstationImages>().factory(stepBuilderFactory).name("基础服务-加油站的图片表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationImagesReader")
    public ItemReader<OriginOperationGasstationImages> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select * from sdb_gasstation_images";
        return new StepReaderBuilder<OriginOperationGasstationImages>().dataSource(dataSource).sql(sql).mappedClass(OriginOperationGasstationImages.class).multithreaded(true).sortKeys("img_id").build();
    }

    @Bean(name = "bwoilOperationGasstationImagesProcessor")
    public ItemProcessor<OriginOperationGasstationImages, BwoilOperationGasstationImages> processor() {
        return item -> {
            BwoilOperationGasstationImages target = new BwoilOperationGasstationImages();
            target.setId(item.getImgId());
            target.setStationId(item.getStationId());
            target.setName(item.getName());
            target.setImage(item.getImage());
            target.setEquipmentSn(item.getEquipmentSn());
            target.setImageStatus(item.getStatus());
            target.setSort(item.getSort());
            target.setStartTime(TimeStampUtil.getTimestampWithDefault(item.getStartTime()));
            target.setEndTime(TimeStampUtil.getTimestampWithDefault(item.getEndTime()));
            target.setCreateTime(TimeStampUtil.getTimestampWithDefault(item.getCreateTime()));
            target.setLastUpdateTime(TimeStampUtil.now());
            target.setStatus("0");
            return target;
        };
    }

    @Bean(name = "bwoilOperationGasstationImagesWriter")
    public ItemWriter<BwoilOperationGasstationImages> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation_images (id, station_id, name, image, equipment_sn, image_status, sort, start_time, end_time, status, create_time, last_update_time) VALUES (:id, :stationId, :name, :image, :equipmentSn, :imageStatus, :sort, :startTime, :endTime, :status, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<BwoilOperationGasstationImages>().dataSource(dataSource).sql(sqlStr).build();
    }
}
