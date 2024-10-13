package com.bwoil.c2b.migration.steps.base.image;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.base.image.pojo.origin.OriginBaseImage;
import com.bwoil.c2b.migration.steps.base.image.pojo.target.BwoilBaseImage;
import com.bwoil.c2b.migration.utils.TimeStampUtil;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.File;

@Configuration("bwoilBaseImageMigration")
public class BwoilBaseImageMigration {

    private final StepBuilderFactory stepBuilderFactory;

    private final FastFileStorageClient storageClient;

    @Value("${nfs-path}")
    private String nfsPath;

    @Autowired
    public BwoilBaseImageMigration(StepBuilderFactory stepBuilderFactory, FastFileStorageClient storageClient) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.storageClient = storageClient;
    }

    @Bean("bwoilBaseImageMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginBaseImage, BwoilBaseImage>().factory(stepBuilderFactory).name("基础服务-图片-历史数据迁移")
                .reader(reader(dataSource/*, start, limit*/)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilBaseImageMigrationReader")
    public ItemReader<OriginBaseImage> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sqlStr = "select * from sdb_image_image where from_unixtime(last_modified) > '2019-05-01'";
        return new StepReaderBuilder<OriginBaseImage>().dataSource(dataSource).sql(sqlStr).mappedClass(OriginBaseImage.class).multithreaded(true).sortKeys("image_id").build();
    }

    @Bean("bwoilBaseImageMigrationProcessor")
    public ItemProcessor<OriginBaseImage, BwoilBaseImage> processor() {
        return item -> {
            BwoilBaseImage target = new BwoilBaseImage();
            target.setImageId(item.getImageId());
            String url = item.getUrl();
            byte[] bytes = FileUtils.readFileToByteArray(new File(nfsPath + item.getUrl()));
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            StorePath storePath = storageClient.uploadFile(byteArrayInputStream, bytes.length, getExtension(url), null);
            byteArrayInputStream.close();
            target.setUrl(storePath.getPath());
            target.setLastModified(TimeStampUtil.getTimestampWithDefault(item.getLastModified()));
            return target;
        };
    }

    private String getExtension(String url) {
        int index = url.indexOf(".");
        if (index == -1) {
            return null;
        }
        return url.substring(index + 1);
    }

    @Bean("bwoilBaseImageMigrationWriter")
    public ItemWriter<BwoilBaseImage> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_base_image (image_id, url, last_modified) VALUES (:imageId, :url, :lastModified)";
        return new StepWriterBuilder<BwoilBaseImage>().dataSource(dataSource).sql(sqlStr).build();
    }
}
