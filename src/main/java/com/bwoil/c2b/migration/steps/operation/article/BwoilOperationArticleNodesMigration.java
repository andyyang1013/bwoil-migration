package com.bwoil.c2b.migration.steps.operation.article;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.operation.article.pojo.origin.OriginOperationArticleNodes;
import com.bwoil.c2b.migration.steps.operation.article.pojo.target.BwoilOperationArticleNodes;
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

@Configuration("bwoilOperationArticleNodesMigration")
public class BwoilOperationArticleNodesMigration {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilOperationArticleNodesMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilOperationArticleNodesMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginOperationArticleNodes, BwoilOperationArticleNodes>().factory(stepBuilderFactory).name("运营服务-文章节点-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilOperationArticleNodesMigrationReader")
    public ItemReader<OriginOperationArticleNodes> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_content_article_nodes";
        return new StepReaderBuilder<OriginOperationArticleNodes>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginOperationArticleNodes.class).multithreaded(true).sortKeys("node_id").build();
    }

    @Bean("bwoilOperationArticleNodesMigrationProcessor")
    public ItemProcessor<OriginOperationArticleNodes, BwoilOperationArticleNodes> processor() {
        return item -> {
            BwoilOperationArticleNodes target = new BwoilOperationArticleNodes();
            BeanUtils.copyProperties(item, target);
            target.setNodeId(item.getNodeId());
            target.setParentId(item.getParentId());

            if (item.getHasChildren().equals("true")) {
                target.setHasChildren("1");
            } else {
                target.setHasChildren("0");
            }
            if (item.getIfpub().equals("true")) {
                target.setIfpub("1");
            } else {
                target.setIfpub("0");
            }
            if (item.getHomepage().equals("true")) {
                target.setHomepage("1");
            } else {
                target.setHomepage("0");
            }
            target.setUptime(new Date(item.getUptime() * 1000));
            target.setCreateTime(new Date(item.getUptime() * 1000));
            if (item.getDisabled().equals("false")) {
                target.setStatus("0");
            } else {
                target.setStatus("-1");
            }
            return target;
        };
    }

    @Bean("bwoilOperationArticleNodesMigrationWriter")
    public ItemWriter<BwoilOperationArticleNodes> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_operation_article_nodes (node_id, parent_id, node_depth, node_name, node_pagename, node_path, has_children, seo_title, seo_description, seo_keywords, ifpub, ordernum, homepage, content, status, uptime, create_time) VALUES (:nodeId, :parentId, :nodeDepth, :nodeName, :nodePagename, :nodePath, :hasChildren, :seoTitle, :seoDescription, :seoKeywords, :ifpub, :ordernum, :homepage, :content, :status, :uptime, :createTime)";
        return new StepWriterBuilder<BwoilOperationArticleNodes>().dataSource(dataSource).sql(sqlStr).build();
    }
}
