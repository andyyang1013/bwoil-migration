package com.bwoil.c2b.migration;

import com.bwoil.c2b.migration.core.MigrationExecutor;
import com.bwoil.c2b.migration.core.monitor.MigrationMonitor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BwoilMigrationApplication implements ApplicationRunner {

    private final MigrationExecutor migrationExecutor;

    private final MigrationMonitor migrationMonitor;

    public BwoilMigrationApplication(MigrationExecutor migrationExecutor, MigrationMonitor migrationMonitor) {
        this.migrationExecutor = migrationExecutor;
        this.migrationMonitor = migrationMonitor;
    }

    public static void main(String[] args) {
        SpringApplication.run(BwoilMigrationApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        migrationExecutor.exe();
        migrationMonitor.run();
    }
}
