package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMemberBosBills;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMemberBosBills;
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

/**
 * 上海银行联名卡帐单表
 *
 * @author yangda
 */
@Configuration("bwoilMemberShanghaiBosBills")
public class BwoilMemberShanghaiBosBillsMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberShanghaiBosBillsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberShanghaiBosBillsStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMemberBosBills, BwoilMemberBosBills>().factory(stepBuilderFactory).name("会员服务-上海银行联名卡帐单表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberShanghaiBosBillsReader")
    public ItemReader<OriginMemberBosBills> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "SELECT   bills_id ,  member_id,  login_account,  true_name,  card_no,  bill_discount,  bill_type,  bill_firstmonth,  bill_month,  bill_ecif,  bill_source,  lastmodify  " +
                " FROM sdb_b2c_bos_bills ";
        return new StepReaderBuilder<OriginMemberBosBills>().dataSource(dataSource).sql(sql).mappedClass(OriginMemberBosBills.class).multithreaded(true).sortKeys("bills_id").build();
    }

    @Bean("bwoilMemberShanghaiBosBillsWriter")
    public ItemWriter<BwoilMemberBosBills> writer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        String sqlStr = "INSERT INTO bwoil_member_shanghai_bos_bills (id, member_id,  login_account, true_name, card_no,  is_discount, bill_type,  bill_firstmonth,  bill_month,  bill_ecif,  bill_source,  last_modify) " +
                " VALUES  (:id, :memberId, :loginAccount, :trueName, :cardNo, :isDiscount, :billType,  :billFirstmonth, :billMonth,  :billEcif,  :billSource,  :lastModify) ;";
        return new StepWriterBuilder<BwoilMemberBosBills>().dataSource(targetDataSource).sql(sqlStr).build();
    }

    @Bean("bwoilMemberShanghaiBosBillsProcessor")
    public ItemProcessor<OriginMemberBosBills, BwoilMemberBosBills> processor() {
        return item -> {
            BwoilMemberBosBills target = new BwoilMemberBosBills();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getBillsId());
            target.setLastModify(new Date(item.getLastmodify() * 1000L));
            target.setIsDiscount(item.getBillDiscount());
            return target;
        };
    }
}
