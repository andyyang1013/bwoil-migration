package com.bwoil.c2b.migration.steps.operation.article;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.article.pojo.origin.OriginOperationArticleIndexs;
import com.bwoil.c2b.migration.steps.operation.article.pojo.target.BwoilOperationArticleIndexs;
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

@Configuration("bwoilOperationArticleIndexsMigration")
public class BwoilOperationArticleIndexsMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationArticleIndexsMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationArticleIndexsMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationArticleIndexs, BwoilOperationArticleIndexs>().factory(stepBuilderFactory).name("运营服务-文章内容-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationArticleIndexsMigrationReader")
    public ItemReader<OriginOperationArticleIndexs> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select i.article_id,i.title,i.platform,i.type,i.node_id,i.author,i.pubtime,i.uptime,i.ifpub,i.pv,i.disabled,b.content,b.seo_description,b.seo_title,b.seo_keywords " +
                "from sdb_content_article_indexs as i left join sdb_content_article_bodys as b on i.article_id = b.article_id ";
        return new StepReaderBuilder<OriginOperationArticleIndexs>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationArticleIndexs.class).multithreaded(true).sortKeys("i.article_id").build();
    }

    @Bean("bwoilOperationArticleIndexsMigrationProcessor")
    public ItemProcessor<OriginOperationArticleIndexs, BwoilOperationArticleIndexs> processor() {
        return item -> {
            BwoilOperationArticleIndexs target = new BwoilOperationArticleIndexs();
            BeanUtils.copyProperties(item, target);
            target.setNodeId(item.getNodeId());
            if (item.getUptime() != null) {
                target.setUptime(new Date(item.getUptime() * 1000));
            }
            if (item.getUptime() != null) {
                target.setCreateTime(new Date(item.getUptime() * 1000));
            }
            target.setPubTime(new Date(item.getPubtime() * 1000));
            if (item.getDisabled().equals("true")) {
                target.setStatus("-1");
            } else {
                target.setStatus("0");
            }
            if (item.getIfpub().equals("true")) {
                target.setIfpub("1");
            } else {
                target.setIfpub("0");
            }
            return target;
        };
    }

    @Bean("bwoilOperationArticleIndexsMigrationWriter")
    public ItemWriter<BwoilOperationArticleIndexs> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_article_indexs (article_id, title, platform, type, node_id, author, content, seo_title, seo_description, seo_keywords, create_time, uptime, ifpub, pubTime, pv, status) VALUES (:articleId, :title, :platform, :type, :nodeId, :author, :content, :seoTitle, :seoDescription, :seoKeywords, :createTime, :uptime, :ifpub, :pubTime, :pv, :status)";
        return new StepWriterBuilder<BwoilOperationArticleIndexs>().dataSource(dataSource).sql(sqlStr).build();
    }
}
