package com.bwoil.c2b.migration.core.listener;

import com.bwoil.c2b.migration.core.util.CacheUtil;
import com.bwoil.c2b.migration.core.util.CsvUtil;
import com.bwoil.c2b.migration.core.util.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class StepRunListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(StepRunListener.class);

    private final String name;

    public StepRunListener(String name) {
        this.name = name;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        CacheUtil.cacheStepStartTime(name, stepExecution.getStartTime());
        log.info(CacheUtil.getStepReaderSql(name));
        log.info(CacheUtil.getStepWriterSqlTemplate(name));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Result result = new Result();
        result.readCount = stepExecution.getReadCount();
        result.readSql = CacheUtil.getStepReaderSql(name);
        result.writeCount = stepExecution.getWriteCount();
        result.writeSql = CacheUtil.getStepWriterSqlTemplate(name);
        // step完成时的日志打印
        printResult(stepExecution);
        try {
            CsvUtil.saveResult(name, result);
        } catch (IOException e) {
            log.error("保存结果出错", e);
        }
        return null;
    }

    /**
     * step完成时的日志打印
     *
     * @param stepExecution step执行信息
     */
    private void printResult(StepExecution stepExecution) {
        log.info("step [{}] finished!!!", name);
        // 执行时长
        Date startTime = stepExecution.getStartTime();
        Date endTime = stepExecution.getEndTime();
        if (endTime == null) {
            endTime = new Date();
        }
        long duration = endTime.getTime() - startTime.getTime();
        log.info("step [{}] 执行时长: {}ms", name, duration);
        // 更新的表
        String insertTable = SqlUtil.getTable(CacheUtil.getStepWriterSqlTemplate(stepExecution.getStepName()));
        log.info("step [{}] 更新的表: {}", name, insertTable);
        // 影响的数据条数
        int writeCount = stepExecution.getWriteCount();
        log.info("step [{}] 影响的数据条数: {}", name, writeCount);
        // 失败的数据条数(总条数[R,P,W])
        int readSkipCount = stepExecution.getReadSkipCount();
        int processSkipCount = stepExecution.getProcessSkipCount();
        int writeSkipCount = stepExecution.getWriteSkipCount();
        int totalSkipCount = readSkipCount + processSkipCount + writeSkipCount;
        if (totalSkipCount > 0) {
            log.error("step [{}] 失败的数据条数(总条数={}[R={},P={},W={}]): ", name, totalSkipCount, readSkipCount, processSkipCount, writeSkipCount);
        } else {
            log.info("step [{}] 全部数据执行成功", name);
        }
        // 失败原因
        List<Throwable> failureExceptions = stepExecution.getFailureExceptions();
        if (failureExceptions != null && failureExceptions.size() > 0) {
            for (Throwable t : failureExceptions) {
                log.error("step [{}] 失败原因: ", name, t);
            }
        }
    }

    private class Result {
        private int readCount;
        private String readSql;
        private int writeCount;
        private String writeSql;

        public int getReadCount() {
            return readCount;
        }

        public String getReadSql() {
            return readSql;
        }

        public int getWriteCount() {
            return writeCount;
        }

        public String getWriteSql() {
            return writeSql;
        }
    }
}
