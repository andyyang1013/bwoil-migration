package com.bwoil.c2b.migration.core.util;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    private static final ConcurrentHashMap<String, String> stepReaderSqlMapper = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, String> stepWriterSqlTemplateMapper = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Date> stepStartTimeMapper = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Boolean> completeJobMapper = new ConcurrentHashMap<>();

    public static void cacheStepSql(String name, String readerSql, String writerSqlTemplate) {
        if (stepReaderSqlMapper.containsKey(name) || stepWriterSqlTemplateMapper.containsKey(name)) {
            throw new RuntimeException(String.format("重复的作业步骤名称[%s]", name));
        }
        stepReaderSqlMapper.put(name, readerSql);
        stepWriterSqlTemplateMapper.put(name, writerSqlTemplate);
    }

    public static void cacheStepStartTime(String name, Date date) {
        stepStartTimeMapper.put(name, date);
    }

    public static String getStepReaderSql(String name) {
        return stepReaderSqlMapper.get(name);
    }

    public static String getStepWriterSqlTemplate(String name) {
        return stepWriterSqlTemplateMapper.get(name);
    }

    public static Date getStepStartTime(String name) {
        return stepStartTimeMapper.get(name);
    }

    public static void cacheCompleteJob(String jobName, boolean b) {
        completeJobMapper.put(jobName, b);
    }

    public static Boolean getCompleteJob(String jobName) {
        return completeJobMapper.get(jobName);
    }
}
