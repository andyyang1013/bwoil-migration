package com.bwoil.c2b.migration.steps.other;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.other.pojo.origin.OriginOperationInsurance;
import com.bwoil.c2b.migration.steps.other.pojo.target.BwoilOperationInsurance;
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
 * @ClassName BwoilOperationInsuranceMigration
 * @Description TODO
 * @Author tanjian
 * @Date 2019/3/1 9:59
 **/
@Configuration("bwoilOperationInsuranceMigration")
public class BwoilOperationInsuranceMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationInsuranceMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationInsuranceMigrationStep")
    public Step step(@Qualifier("InsuranceMigrationReader") ItemReader<OriginOperationInsurance> reader,
                     @Qualifier("InsuranceMigrationProcessor") ItemProcessor<OriginOperationInsurance, BwoilOperationInsurance> processor,
                     @Qualifier("InsuranceMigrationWriter") ItemWriter<BwoilOperationInsurance> writer) {
        return new StepBuilder<OriginOperationInsurance, BwoilOperationInsurance>().factory(stepBuilderFactory).name("运营服务-资产保险-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("InsuranceMigrationReader")
    public ItemReader<OriginOperationInsurance> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "  " +
                "SELECT " +
                " i.*, " +
                " m.shop_member_bn shop_bn " +
                " FROM " +
                " ( " +
                "  SELECT " +
                "   a.* " +
                "  FROM " +
                "   sdb_b2c_member_insurance a, " +
                "   ( " +
                "    SELECT " +
                "     member_id, " +
                "     MAX(insurance_id) AS insurance_id " +
                "    FROM " +
                "     sdb_b2c_member_insurance " +
                "    GROUP BY " +
                "     member_id " +
                "   ) AS b " +
                "  WHERE " +
                "   a.insurance_id = b.insurance_id " +
                " ) i " +
                " LEFT JOIN sdb_b2c_members m ON i.member_id = m.member_id ";
        return new StepReaderBuilder<OriginOperationInsurance>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationInsurance.class).multithreaded(true).sortKeys("i.insurance_id").build();
    }

    @Bean("InsuranceMigrationProcessor")
    public ItemProcessor<OriginOperationInsurance, BwoilOperationInsurance> processor() {
        return item -> {
            BwoilOperationInsurance target = new BwoilOperationInsurance();
            BeanUtils.copyProperties(item, target);

            // 身份类型
            Integer certificateType = 0;
            if ("passport".equals(item.getCertificateType())) {
                certificateType = 1;
            }
            target.setCertificateType(certificateType);

            // 有效期
            target.setPeriod(30);

            // 投保状态
            Integer insuranceStatus = 1;
            if ("guarantee".equals(item.getStatus())) {
                insuranceStatus = 4;
            }
            target.setInsuranceStatus(insuranceStatus);
            return target;
        };
    }

    @Bean("InsuranceMigrationWriter")
    public ItemWriter<BwoilOperationInsurance> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_member_insurance " +
                " (insurance_id,member_id,shop_bn,customer_name,mobile,certificate_type,certificate_bn," +
                " insurance_company,product_code,insurance_date,insurance_bn,from_time,to_time,period," +
                " insurance_money,insurance_limit,premium,insurance_image,insurance_status,create_time,lastmodify)" +
                " VALUES" +
                " (:insuranceId,:memberId,:shopBn,:customerName,:mobile,:certificateType,:certificateBn," +
                " :insuranceCompany,:productCode,:insuranceDate,:insuranceBn,:fromTime,:toTime,:period," +
                " :insuranceMoney,:insuranceLimit,:premium,:insuranceImage,:insuranceStatus,:createTime,:lastmodify)";
        return new StepWriterBuilder<BwoilOperationInsurance>().dataSource(dataSource).sql(sqlStr).build();
    }
}
