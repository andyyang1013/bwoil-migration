package com.bwoil.c2b.migration.core.builder;

import com.bwoil.c2b.migration.core.util.SqlUtil;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

public class StepReaderBuilder<T> {
    private Reader reader;

    public StepReaderBuilder() {
        this.reader = new Reader();
    }

    public StepReaderBuilder<T> dataSource(DataSource dataSource) {
        reader.dataSource = dataSource;
        return this;
    }

    public StepReaderBuilder<T> sql(String sqlStr) {
        reader.sqlStr = sqlStr;
        return this;
    }

    public StepReaderBuilder<T> mappedClass(Class<T> mappedClass) {
        reader.mappedClass = mappedClass;
        return this;
    }

    public StepReaderBuilder<T> selectClause(String selectClause) {
        reader.selectClause = selectClause;
        return this;
    }

    public StepReaderBuilder<T> fromClause(String fromClause) {
        reader.fromClause = fromClause;
        return this;
    }

    public StepReaderBuilder<T> whereClause(String whereClause) {
        reader.whereClause = whereClause;
        return this;
    }

    public StepReaderBuilder<T> groupClause(String groupClause) {
        reader.groupClause = groupClause;
        return this;
    }

    public StepReaderBuilder<T> multithreaded(boolean multithreaded) {
        reader.multithreaded = multithreaded;
        return this;
    }

    public ItemReader<T> build() {
        if (reader.multithreaded) {
            return new JdbcPagingItemReaderBuilder<T>()
                    .saveState(false)
                    .dataSource(reader.dataSource)
                    .queryProvider(getQueryProvider(reader.sqlStr))
                    .pageSize(1000)
                    .rowMapper(new BeanPropertyRowMapper<>(reader.mappedClass))
                    .build();
        }
        return new JdbcCursorItemReaderBuilder<T>()
                .saveState(false)
                .dataSource(reader.dataSource)
                .sql(reader.sqlStr)
                .fetchSize(1000)
                .beanRowMapper(reader.mappedClass)
                .build();
    }

    private MySqlPagingQueryProvider getQueryProvider(String sqlStr) {
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause(reader.selectClause == null ? SqlUtil.getStructure(sqlStr).getSelectClause() : reader.selectClause);
        queryProvider.setFromClause(reader.fromClause == null ? SqlUtil.getStructure(sqlStr).getFromClause() : reader.fromClause);
        queryProvider.setWhereClause(reader.whereClause == null ? SqlUtil.getStructure(sqlStr).getWhereClause() : reader.whereClause);
        queryProvider.setGroupClause(reader.groupClause == null ? SqlUtil.getStructure(sqlStr).getGroupClause() : reader.groupClause);
        queryProvider.setSortKeys(reader.sortKeys);
        return queryProvider;
    }

    public StepReaderBuilder<T> sortKeys(String sortKeys) {
        reader.sortKeys = Collections.singletonMap(sortKeys, Order.ASCENDING);
        return this;
    }

    private class Reader {
        private DataSource dataSource;
        private Class<T> mappedClass;
        private String sqlStr;

        private boolean multithreaded;

        private String selectClause;
        private String fromClause;
        private String whereClause;
        private String groupClause;
        private Map<String, Order> sortKeys;
    }
}
