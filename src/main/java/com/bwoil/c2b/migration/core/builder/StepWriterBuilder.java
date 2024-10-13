package com.bwoil.c2b.migration.core.builder;

import com.bwoil.c2b.migration.core.ProcessingInterface;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class StepWriterBuilder<T> {
    private Writer writer;

    public StepWriterBuilder() {
        this.writer = new Writer();
    }

    public StepWriterBuilder<T> dataSource(DataSource dataSource) {
        writer.dataSource = dataSource;
        return this;
    }

    public StepWriterBuilder<T> sql(String sqlStr) {
        writer.sql = sqlStr;
        return this;
    }

    public StepWriterBuilder<T> reprocessing(ProcessingInterface<T> processingInterface) {
        writer.processingInterface = processingInterface;
        return this;
    }

    public ItemWriter<T> build() {
        if (writer.processingInterface != null) {
            return items -> {
                List<T> data = new ArrayList<>(items);
                List<T> processedData = writer.processingInterface.process(data);
                SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(processedData);
                new NamedParameterJdbcTemplate(writer.dataSource).batchUpdate(writer.sql, batchArgs);
            };
        }
        return new JdbcBatchItemWriterBuilder<T>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(writer.sql)
                .dataSource(writer.dataSource)
                .build();
    }

    private class Writer {

        private ProcessingInterface<T> processingInterface;
        private String sql;
        private DataSource dataSource;

    }
}
