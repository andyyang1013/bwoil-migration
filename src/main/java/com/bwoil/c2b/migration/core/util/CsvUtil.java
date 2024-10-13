package com.bwoil.c2b.migration.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtil {

    private static void writeHeader(CSVPrinter csvPrinter, Object item, Throwable... t) throws IOException {
        List<String> values = new ArrayList<>();
        if (item != null) {
            // 插入表头
            new ObjectMapper().valueToTree(item).fieldNames().forEachRemaining(values::add);
        }
        if (t != null) {
            values.add("exception");
        }
        csvPrinter.printRecord(values);
    }

    private static void writeRecord(CSVPrinter csvPrinter, Object item, Throwable... t) throws IOException {
        List<String> values = new ArrayList<>();
        if (item != null) {
            new ObjectMapper().valueToTree(item).fields().forEachRemaining(field -> values.add(field.getValue().asText()));
        }
        if (t != null) {
            values.add(Arrays.toString(t));
        }
        csvPrinter.printRecord(values);
    }

    private static boolean createFile(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return true;
        }
        return false;
    }

    private static void writeIntoCsv(Path path, Object item, Throwable... t) throws IOException {
        boolean isNew = createFile(path);
        StandardOpenOption openOption = isNew ? StandardOpenOption.CREATE : StandardOpenOption.APPEND;
        try (
                BufferedWriter bufferedWriter = Files.newBufferedWriter(path, openOption);
                CSVPrinter csvPrinter = CSVFormat.EXCEL.withAutoFlush(true).print(bufferedWriter)
        ) {
            if (isNew) {
                writeHeader(csvPrinter, item, t);
            }
            writeRecord(csvPrinter, item, t);
        }
    }

    private static void writeIntoCsvBatch(Path path, List items, Throwable... t) throws IOException {
        boolean isNew = createFile(path);
        StandardOpenOption openOption = isNew ? StandardOpenOption.CREATE : StandardOpenOption.APPEND;
        try (
                BufferedWriter bufferedWriter = Files.newBufferedWriter(path, openOption);
                CSVPrinter csvPrinter = CSVFormat.EXCEL.withAutoFlush(true).print(bufferedWriter)
        ) {
            // 插入表头
            if (isNew) {
                writeHeader(csvPrinter, items.get(0), t);
            }
            // 插入数据行
            for (Object item : items) {
                writeRecord(csvPrinter, item, t);
            }
            csvPrinter.flush();
        }
    }

    public static void saveReadError(String name, Throwable t) throws IOException {
        writeIntoCsv(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "readError.csv"), null, t);
    }

    public static void saveWriteError(String name, Object item, Throwable t) throws IOException {
        writeIntoCsv(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "writeError.csv"), item, t);
    }

    public static void saveWriteError(String name, List items, Throwable t) throws IOException {
        writeIntoCsvBatch(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "writeError.csv"), items, t);
    }

    public static void saveProcessError(String name, Object item, Throwable t) throws IOException {
        writeIntoCsv(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "processError.csv"), item, t);
    }

    public static void saveWriteSuccess(String name, List items) throws IOException {
//        writeIntoCsvBatch(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "writeSuccess.csv"), items);
    }

    public static void saveReadSuccess(String name, Object item) throws IOException {
//        writeIntoCsv(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "readSuccess.csv"), item);
    }

    public static void saveResult(String name, Object item) throws IOException {
        writeIntoCsv(Paths.get("数据迁移结果", name, new SimpleDateFormat("yyyyMMddHHmmss").format(CacheUtil.getStepStartTime(name)), "result.csv"), item);
    }

    public static void saveReport(Object item) throws IOException {
        writeIntoCsv(Paths.get("数据迁移结果", "report.csv"), item);
    }
}
