package com.bwoil.c2b.migration.steps.operation.friendlylink;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.friendlylink.pojo.origin.OriginOperationFriendlyLink;
import com.bwoil.c2b.migration.steps.operation.friendlylink.pojo.target.BwoilOperationFriendlyLink;
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

@Configuration("bwoilOperationFriendlyLinkMigration")
public class BwoilOperationFriendlyLinkMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationFriendlyLinkMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationFriendlyLinkMigrationStep")
    public Step step(@Qualifier("friendlyLinkMigrationReader") ItemReader<OriginOperationFriendlyLink> reader,
                     @Qualifier("friendlyLinkMigrationProcessor") ItemProcessor<OriginOperationFriendlyLink, BwoilOperationFriendlyLink> processor,
                     @Qualifier("friendlyLinkMigrationWriter") ItemWriter<BwoilOperationFriendlyLink> writer) {
        return new StepBuilder<OriginOperationFriendlyLink, BwoilOperationFriendlyLink>().factory(stepBuilderFactory).name("运营服务-友情链接-历史数据迁移")
                .reader(reader).processor(processor).writer(writer)
                .build();
    }

    @Bean("friendlyLinkMigrationReader")
    public ItemReader<OriginOperationFriendlyLink> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_site_link";
        return new StepReaderBuilder<OriginOperationFriendlyLink>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationFriendlyLink.class).multithreaded(true).sortKeys("link_id").build();
    }

    @Bean("friendlyLinkMigrationProcessor")
    public ItemProcessor<OriginOperationFriendlyLink, BwoilOperationFriendlyLink> processor() {
        return item -> {
            BwoilOperationFriendlyLink target = new BwoilOperationFriendlyLink();
            target.setLinkName(item.getLink_name());
            target.setLinkAddr(item.getHref());
            target.setLinkImage(item.getImage_url());
            target.setSortNum(item.getOrderlist());
            if (null != item.getHidden() && item.getHidden().equals("true")) {
                target.setIsHidden(1);
            } else {
                target.setIsHidden(0);
            }
            return target;
        };
    }

    @Bean("friendlyLinkMigrationWriter")
    public ItemWriter<BwoilOperationFriendlyLink> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_friendly_link (" +
                "link_name," +
                "link_addr," +
                "link_image," +
                "sort_num," +
                "is_hidden )" +
                "VALUES" +
                "(:linkName ,:linkAddr ,:linkImage ,:sortNum ,:isHidden) ";
        return new StepWriterBuilder<BwoilOperationFriendlyLink>().dataSource(dataSource).sql(sqlStr).build();
    }
}
