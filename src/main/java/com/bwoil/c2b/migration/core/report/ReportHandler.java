package com.bwoil.c2b.migration.core.report;

import com.bwoil.c2b.migration.core.util.CacheUtil;
import com.bwoil.c2b.migration.core.util.CsvUtil;
import com.bwoil.c2b.migration.core.util.SqlUtil;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportHandler {

    private static Record addRecord(StepExecution stepExecution) throws IOException {
        Record record = new Record();
        // 微服务
        record.setServiceName(stepExecution.getStepName().substring(0, stepExecution.getStepName().indexOf("-")));
        // 目标表
        record.setTargetTable(SqlUtil.getTable(CacheUtil.getStepWriterSqlTemplate(stepExecution.getStepName())));
        // 表描述
        record.setTargetTableComment(stepExecution.getStepName().substring(stepExecution.getStepName().indexOf("-") + 1, stepExecution.getStepName().lastIndexOf("-")));
        // 历史表
        try {
            record.setOriginTable(SqlUtil.getTable(CacheUtil.getStepReaderSql(stepExecution.getStepName())));
        } catch (Exception ex) {
            record.setOriginTable("sql parser not support");
        }
        // 查询SQL
        record.setSelectSql(CacheUtil.getStepReaderSql(stepExecution.getStepName()));
        // 写入SQL
        record.setInsertSql(CacheUtil.getStepWriterSqlTemplate(stepExecution.getStepName()));
        // 读取历史数据成功数量(条)
        record.setReadSuccessCount(stepExecution.getReadCount());
        // 写入历史数据成功数量(条)
        record.setWriteSuccessCount(stepExecution.getWriteCount());
        // 执行查询SQL失败数量(条)
        record.setReadFailCount(stepExecution.getReadSkipCount());
        // 执行数据转换失败数量(条)
        record.setProcessFailCount(stepExecution.getProcessSkipCount());
        // 执行写入SQL失败数量(条)
        record.setWriteFailCount(stepExecution.getWriteSkipCount());
        // 迁移开始时间
        record.setBeginTime(stepExecution.getStartTime());
        // 迁移结束时间
        record.setEndTime(stepExecution.getEndTime());
        // 执行时间(毫秒)
        record.setDuration(stepExecution.getEndTime().getTime() - stepExecution.getStartTime().getTime());
        // 执行速度(条/秒)
        record.setSpeed((long) (stepExecution.getReadCount() / ((stepExecution.getEndTime().getTime() - stepExecution.getStartTime().getTime()) * 1.00 / 1000)));
        CsvUtil.saveReport(record);
        return record;
    }

    public static List<Record> addRecords(JobExecution jobExecution) throws IOException {
        List<Record> list = new ArrayList<>();
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        for (StepExecution stepExecution : stepExecutions) {
            list.add(addRecord(stepExecution));
        }
        return list;
    }

}
