package com.bwoil.c2b.migration.steps.order;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.order.entity.AssetEntity;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration("bwoilOrderAssetMigration")
public class BwoilOrderAssetMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOrderAssetMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOrderAssetMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<AssetEntity, AssetEntity>().factory(stepBuilderFactory).name("订单服务-资产表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOrderAssetMigrationReader")
    public ItemReader<AssetEntity> reader(@Qualifier("originDataSource") DataSource dataSource) {
        StringBuffer sql = new StringBuffer("select ");
        sql.append("sm.member_id, sm.advance, sm.advance_freeze as freeze_advance, sa.recharge, sa.red_envelope, sa.freeze_recharge, sa.freeze_red_envelope,")
                .append("from_unixtime(sm.lastmodify) last_update_time, ")
                .append("from_unixtime(sm.regtime) create_time ")
                .append("from sdb_b2c_members sm ")
                .append("left join sdb_b2c_member_sub_advance sa on sm.member_id=sa.member_id");

        return new StepReaderBuilder<AssetEntity>().dataSource(dataSource).sql(sql.toString()).mappedClass(AssetEntity.class).multithreaded(true).sortKeys("sm.member_id").build();
    }

    @Bean("bwoilOrderAssetMigrationProcessor")
    public ItemProcessor<AssetEntity, AssetEntity> processor() {
        return item -> {
            AssetEntity target = new AssetEntity();
            BeanUtils.copyProperties(item,target);
            return target;
        };
    }

    @Bean("bwoilOrderAssetMigrationWriter")
    public ItemWriter<AssetEntity> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_order_asset (member_id, asset_type, cur_advance, advance, freeze_advance, last_advance, create_time, last_update_time) VALUES (:memberId, :assetType, :curAdvance, :advance, :freezeAdvance, :lastAdvance, :createTime, :lastUpdateTime)";
        return new StepWriterBuilder<AssetEntity>().dataSource(dataSource).sql(sqlStr).reprocessing(
                targetList -> {
                    List<AssetEntity> processedTargetList = new ArrayList<>();
                    for (AssetEntity item : targetList) {
                        BigDecimal recharge = item.getRecharge() == null ? BigDecimal.ZERO : item.getRecharge();
                        BigDecimal freezeRecharge = item.getFreezeRecharge() == null ? BigDecimal.ZERO : item.getFreezeRecharge();
                        BigDecimal rd = item.getRedEnvelope() == null ? BigDecimal.ZERO : item.getRedEnvelope();
                        BigDecimal freezeRd = item.getFreezeRedEnvelope() == null ? BigDecimal.ZERO : item.getFreezeRedEnvelope();

                        BigDecimal lcAsset = item.getAdvance().subtract(recharge.add(rd));
                        BigDecimal freezeLc = item.getFreezeAdvance().subtract(freezeRecharge.add(freezeRd));

                        AssetEntity rechage = new AssetEntity();
                        rechage.setMemberId(item.getMemberId());
                        rechage.setAssetType("CZ");
                        rechage.setCurAdvance(recharge);
                        rechage.setAdvance(recharge.subtract(freezeRecharge));
                        rechage.setFreezeAdvance(freezeRecharge);
                        rechage.setLastAdvance(recharge);
                        rechage.setCreateTime(item.getCreateTime());
                        rechage.setLastUpdateTime(item.getLastUpdateTime());

                        AssetEntity redpakage = new AssetEntity();
                        redpakage.setMemberId(item.getMemberId());
                        redpakage.setAssetType("RP");
                        redpakage.setCurAdvance(rd);
                        redpakage.setAdvance(rd.subtract(freezeRd));
                        redpakage.setFreezeAdvance(freezeRd);
                        redpakage.setLastAdvance(rd);
                        redpakage.setCreateTime(item.getCreateTime());
                        redpakage.setLastUpdateTime(item.getLastUpdateTime());


                        AssetEntity lc = new AssetEntity();
                        lc.setMemberId(item.getMemberId());
                        lc.setAssetType("LC");
                        lc.setCurAdvance(lcAsset);
                        lc.setAdvance(lcAsset.subtract(freezeLc));
                        lc.setFreezeAdvance(freezeLc);
                        lc.setLastAdvance(lcAsset);
                        lc.setCreateTime(item.getCreateTime());
                        lc.setLastUpdateTime(item.getLastUpdateTime());

                        processedTargetList.add(redpakage);
                        processedTargetList.add(rechage);
                        processedTargetList.add(lc);
                    }
                    return processedTargetList;
                }
        ).build();
    }
}
