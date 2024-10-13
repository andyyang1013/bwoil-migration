package com.bwoil.c2b.migration.steps.operation.gasstation;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.origin.OriginOperationGasstation;
import com.bwoil.c2b.migration.steps.operation.gasstation.pojo.target.BwoilOperationGasstation;
import org.apache.commons.lang3.StringUtils;
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

@Configuration("bwoilOperationGasstationMigration")
public class BwoilOperationGasstationMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationGasstationMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationGasstationMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationGasstation, BwoilOperationGasstation>().factory(stepBuilderFactory).name("运营服务-加油站的station相关信息-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationGasstationMigrationReader")
    public ItemReader<OriginOperationGasstation> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_b2c_gas_station";
        return new StepReaderBuilder<OriginOperationGasstation>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationGasstation.class).multithreaded(true).sortKeys("station_id").build();
    }

    @Bean("bwoilOperationGasstationMigrationProcessor")
    public ItemProcessor<OriginOperationGasstation, BwoilOperationGasstation> processor() {
        return item -> {
            BwoilOperationGasstation target = new BwoilOperationGasstation();
            BeanUtils.copyProperties(item, target);
            target.setStatus(item.getIsOpen());
            if (StringUtils.isEmpty(item.getChildStation())){
                target.setChildStation("[0]");
            }
            //转日期
            if (item.getCreateTime() == null || item.getCreateTime() == 0) {
                target.setCreateTime(null);
            } else {
                target.setCreateTime(new Date(Long.parseLong(item.getCreateTime().toString()) * 1000L));
            }
            if (item.getLastmodify() == null || item.getLastmodify() == 0) {
                target.setLastmodify(null);
            } else {
                target.setLastmodify(new Date(Long.parseLong(item.getLastmodify().toString()) * 1000L));
            }
            return target;
        };
    }

    @Bean("bwoilOperationGasstationMigrationWriter")
    public ItemWriter<BwoilOperationGasstation> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_gasstation " +
                "(station_id, station_bn, station_name, area, addr, contact, mobile, phone, email, device_version, device_sn, device_updatetime, device_status, printer_sn, gas_key, allow_cprint, allow_pay_station, weixin_discount, status, quotaAmountPerDay, image_id, member_id, bank_name, bank_area, bank_no, true_name, bank_account, create_time, lastmodify, is_parent, child_station, parent_id, feeRate, remittanceType, fee_pay_method, agreement_pay_method, remittancePeriod, longitude, latitude, company_id, company_name) VALUES " +
                "(:stationId, :stationBn, :stationName, :area, :addr, :contact, :mobile, :phone, :email, :deviceVersion, :deviceSn, :deviceUpdatetime, :deviceStatus, :printerSn, :gasKey, :allowCprint, :allowPayStation, :weixinDiscount, :status, :quotaamountperday, :imageId, :memberId, :bankName, :bankArea, :bankNo, :trueName, :bankAccount, :createTime, :lastmodify, :isParent, :childStation, :parentId, :feerate, :remittancetype, :feePayMethod, :agreementPayMethod, :remittanceperiod, :longitude, :latitude, :companyId, :companyName)";
        return new StepWriterBuilder<BwoilOperationGasstation>().dataSource(dataSource).sql(sqlStr).build();
    }
}
