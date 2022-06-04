package com.outside;

import io.ebean.DatabaseFactory;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.dbmigration.DbMigration;
import io.ebean.migration.MigrationConfig;
import io.ebean.migration.MigrationRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    String username;
    String password;
    String url;

    public void verifyState() {
        Map<String, String> env = System.getenv();
        boolean ignoreErr = env.containsKey("OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS") && env.get("OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS").equalsIgnoreCase("true");

        if (ignoreErr) {
            return;
        }

        DbMigration dbMigration = DbMigration.create();
        dbMigration.setPlatform(Platform.POSTGRES);

        String newVersion = this.generateUpdates(dbMigration);
        newVersion = this.generateDrops(dbMigration, newVersion);

        if (newVersion != null) {
            throw new DatabaseMigrationException(newVersion);
        } else {
            this.runMigration();
        }
        this.refreshConnection();
    }

    public void refreshConnection() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername(this.username);
        dataSourceConfig.setPassword(this.password);
        dataSourceConfig.setUrl(this.url);

        DatabaseConfig config2 = new DatabaseConfig();
        config2.setDataSourceConfig(dataSourceConfig);

        DatabaseFactory.create(config2);
    }

    private String generateDrops(DbMigration dbMigration, String newVersion) {
        List<String> pendingDrops = dbMigration.getPendingDrops();
        if (!pendingDrops.isEmpty()) {
            System.setProperty("ddl.migration.pendingDropsFor", pendingDrops.get(0));
            try {
                return dbMigration.generateMigration();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newVersion;
    }

    private String generateUpdates(DbMigration dbMigration) {
        try {
            return dbMigration.generateMigration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void runMigration() {
        MigrationConfig config = new MigrationConfig();
        config.setDbUsername(this.username);
        config.setDbPassword(this.password);
        config.setDbSchema("public");
        config.setDbUrl(this.url);

        MigrationRunner runner = new MigrationRunner(config);
        runner.run();
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
