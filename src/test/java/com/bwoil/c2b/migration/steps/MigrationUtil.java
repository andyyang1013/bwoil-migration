package com.bwoil.c2b.migration.steps;

import com.bwoil.c2b.migration.core.MigrationJob;
import com.bwoil.c2b.migration.core.util.CacheUtil;
import com.bwoil.c2b.migration.core.util.SqlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigrationUtil {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("targetDataSource")
    private DataSource dataSource;

    /**
     * 数据迁移jobs目录
     */
    private static final String JOB_PATH_NAME = "F:\\git\\bwoil-migration\\jobs";

    /**
     * 目标数据库schema
     */
    private static final String TARGET_DATA_SOURCE_SCHEMA = "bwoil_refactor_migration";

    /**
     * 启动脚本
     */
    private static final String START_SH = "nohup java -jar bwoil-migration.jar --spring.profiles.active=pro --migration.job-config-path=.%s > nohup%s &";

    @Test
    public void test() {
        System.out.println("=====================参数=====================");
        System.out.println("数据迁移jobs目录: " + JOB_PATH_NAME);
        System.out.println("目标数据库schema: " + TARGET_DATA_SOURCE_SCHEMA);
        System.out.println("启动脚本: " + START_SH);
        checkMigrationJobs();
        tablesMigration();
        tablesExcludeMigration();
        generateStartSh();
    }

    private void generateStartSh() {
        System.out.println("=====================获取启动脚本=====================");
        Set<String> jobsPathSet = new HashSet<>();
        List<File> files = (List<File>) FileUtils.listFiles(new File(JOB_PATH_NAME), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        files.forEach(file -> {
            String absolutePath = file.getAbsolutePath();
            int beginIndex = absolutePath.indexOf("\\jobs\\");
            int endIndex = absolutePath.lastIndexOf("\\");
            String jobsPath = absolutePath.substring(beginIndex, endIndex + 1).replaceAll("\\\\", "/");
            jobsPathSet.add(jobsPath);
        });
        jobsPathSet.stream().sorted().forEach(s -> {
            String logName = s.replaceAll("/", "_").substring(0, s.length() - 1);
            System.out.println(String.format(START_SH, s, logName));
            System.out.println("sleep 10");
        });
    }

    /**
     * 获取历史数据迁移的表
     */
    private void tablesMigration() {
        System.out.println("=====================获取历史数据迁移的表=====================");
        Set<String> tableSet = getMigrationTables();
        tableSet.stream().sorted().forEach(s -> System.out.println("truncate table " + s + ";"));
    }

    /**
     * 获取非历史数据迁移的表
     */
    private void tablesExcludeMigration() {
        System.out.println("=====================获取非历史数据迁移的表=====================");
        Set<String> allTables = getAllTables();
        Set<String> migrationTables = getMigrationTables();
        allTables.removeAll(migrationTables);
        allTables.stream().sorted().forEach(System.out::println);
    }

    /**
     * 校验迁移jobs
     */
    private void checkMigrationJobs() {
        System.out.println("=====================校验迁移jobs=====================");
        AtomicBoolean invalid = new AtomicBoolean(true);
        Map<String, Integer> stepCount = new HashMap<>();
        List<MigrationJob> migrationJobs = getMigrationJobs();
        migrationJobs.forEach(migrationJob -> {
            migrationJob.getSteps().forEach(migrationStep -> {
                boolean flag = stepCount.containsKey(migrationStep.getStep());
                if (flag) {
                    Integer count = stepCount.get(migrationStep.getStep());
                    stepCount.put(migrationStep.getStep(), ++count);
                } else {
                    stepCount.put(migrationStep.getStep(), 1);
                }
            });
        });
        stepCount.forEach((s, c) -> {
            if (c > 1) {
                invalid.set(false);
                System.err.println(s + " : " + c);
            }
        });
        if (invalid.get()) {
            System.out.println("校验通过");
        } else {
            System.err.println("校验不通过");
        }
    }

    private Set<String> getMigrationTables() {
        Set<String> migrationTables = new HashSet<>();
        List<MigrationJob> list = getMigrationJobs();
        list.forEach(migrationJob -> migrationJob.getSteps().forEach(migrationStep -> {
            Step step = (Step) applicationContext.getBean(migrationStep.getStep());
            String table = SqlUtil.getTable(CacheUtil.getStepWriterSqlTemplate(step.getName()));
            migrationTables.add(table);
        }));
        return migrationTables;
    }

    private List<MigrationJob> getMigrationJobs() {
        List<MigrationJob> list = new ArrayList<>();
        List<File> files = (List<File>) FileUtils.listFiles(new File(JOB_PATH_NAME), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        files.forEach(file -> {
            try {
                list.add(new ObjectMapper().readValue(file, MigrationJob.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    private Set<String> getAllTables() {
        Set<String> allTables = new HashSet<>();
        String sql = "SELECT table_name FROM information_schema.TABLES WHERE table_schema = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, TARGET_DATA_SOURCE_SCHEMA);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String table = resultSet.getString(1);
                allTables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTables;
    }

    public static void main(String[] args) {
//        errorFiles();
        incrementalScript();
    }

    /**
     * 增量脚本
     */
    private static void incrementalScript() {
        List<File> files = (List<File>) FileUtils.listFiles(new File("F:\\svn\\develop\\重构\\数据库迁移\\增量"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        files.stream().sorted().forEach(file -> {
            try {
                String data = FileUtils.readFileToString(file, Charset.defaultCharset());
                System.out.println(file.getAbsolutePath() + "\t" + data);
                FileUtils.write(new File("F:\\zengliang.sql"), "=====================" + file.getAbsolutePath() + "=====================\n",Charset.defaultCharset(),true);
                FileUtils.write(new File("F:\\zengliang.sql"), data, Charset.defaultCharset(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 错误日志检查
     */
    private static void errorFiles() {
        List<String> filePathList = new ArrayList<>();

        filePathList.add("F:\\svn\\develop\\重构\\数据库迁移\\迁移结果日志\\20190626\\migration-result-2.7-0626");
        filePathList.add("F:\\svn\\develop\\重构\\数据库迁移\\迁移结果日志\\20190626\\migration-result-2.8-0626");
        filePathList.add("F:\\svn\\develop\\重构\\数据库迁移\\迁移结果日志\\20190626\\migration-result-2.9-0626");

        filePathList.add("F:\\svn\\develop\\重构\\数据库迁移\\迁移结果日志\\20190624\\migration-result-0624-7");
        filePathList.add("F:\\svn\\develop\\重构\\数据库迁移\\迁移结果日志\\20190624\\migration-result-0624-8");
        filePathList.add("F:\\svn\\develop\\重构\\数据库迁移\\迁移结果日志\\20190624\\migration-result-0624-9");

        filePathList.forEach(s -> {
            System.out.println("=====================" + s + "=====================");
            List<File> files = (List<File>) FileUtils.listFiles(new File(s), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            files.forEach(file -> {
                if (file.getName().contains("Error")) {
                    System.out.println(file.getAbsolutePath());
                }
            });
        });
    }


}
