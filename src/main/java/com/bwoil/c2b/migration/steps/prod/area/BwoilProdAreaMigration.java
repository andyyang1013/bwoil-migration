package com.bwoil.c2b.migration.steps.prod.area;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.prod.area.origin.OriginProdArea;
import com.bwoil.c2b.migration.steps.prod.area.target.BwoilProdArea;
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
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;

@Configuration("bwoilProdAreaMigration")
public class BwoilProdAreaMigration {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilProdAreaMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilProdAreaMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginProdArea, BwoilProdArea>().factory(stepBuilderFactory).name("产品服务-地区的明细表-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilProdAreaMigrationReader")
    public ItemReader<OriginProdArea> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_ectools_regions";
        return new StepReaderBuilder<OriginProdArea>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginProdArea.class).build();
    }


    @Bean("bwoilProdAreaMigrationProcessor")
    public ItemProcessor<OriginProdArea, BwoilProdArea> processor() {
        return item -> {
            BwoilProdArea target = new BwoilProdArea();
            BeanUtils.copyProperties(item, target);
            target.setId(item.getRegionId());
            target.setPkg("mainland");
            if (!ObjectUtils.isEmpty(item.getDisabled())) {
                if ("true".equals(item.getDisabled())) {
                    target.setStatus("-1");
                }
                if ("false".equals(item.getDisabled())) {
                    target.setStatus("0");
                }
            } else {
                target.setStatus("-1");
            }

//            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
//                target.setCreateTime(null);
//            } else {
//                Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-02").getTime());
//                if (new Date(item.getCreatedTime() * 1000).compareTo(date) <= 0) {
//                    target.setCreateTime(date);
//                } else {
//                    target.setCreateTime(new Date(item.getCreatedTime() * 1000));
//                }
//            }

//            if (item.getCreatedTime() == null || item.getCreatedTime() == 0) {
//                target.setCreateTime(null);
//            } else {
//                Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-02").getTime());
//                if (new Date(item.getCreatedTime() * 1000).compareTo(date) <= 0) {
//                    target.setCreateTime(date);
//                } else {
//                    target.setCreateTime(new Date(item.getCreatedTime() * 1000));
//                }
//            }
            //json转换
//            if (item.getDiscountId() == 2) {
//                JSONObject resultJson = JSON.parseObject(PhpSeriailizeUtil.getPhpString(item.getData()));
//                JSONObject json = new JSONObject();
//                List<String> list = new ArrayList<>();
//                if (resultJson.size() != 0) {
//                    Set<String> it = resultJson.keySet();
//                    for (String key : it) {
//                        String s = (String) resultJson.get(key);
//                        list.add(s);
//                    }
//                }
//                json.put(list.get(1), list.get(0));
//                target.setData(json.toJSONString());
//            }
            return target;
        };
    }

    @Bean("bwoilProdAreaMigrationWriter")
    public ItemWriter<BwoilProdArea> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_prod_area (id, local_name, pkg, p_region_id, region_path, region_grade, area_code, ordernum, status)" +
                " VALUES (:id, :localName, :pkg, :pRegionId, :regionPath, :regionGrade, :areaCode, :ordernum, :status)";
        return new StepWriterBuilder<BwoilProdArea>().dataSource(dataSource).sql(sqlStr).build();
    }

}
