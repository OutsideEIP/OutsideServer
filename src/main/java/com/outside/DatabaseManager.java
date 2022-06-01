package com.outside;

import io.ebean.DatabaseFactory;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.dbmigration.DbMigration;
import io.ebean.migration.MigrationConfig;
import io.ebean.migration.MigrationRunner;

import java.io.IOException;

public class DatabaseManager {

    String username;
    String password;
    String url;

    public void refreshConnection() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername(this.username);
        dataSourceConfig.setPassword(this.password);
        dataSourceConfig.setUrl(this.url);

        DatabaseConfig config2 = new DatabaseConfig();
        config2.setDataSourceConfig(dataSourceConfig);

        DatabaseFactory.create(config2);
    }

    public void verifyState() {
        DbMigration dbMigration = DbMigration.create();
        dbMigration.setPlatform(Platform.POSTGRES);

        String newVersion = null;
        try {
            newVersion = dbMigration.generateMigration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (newVersion != null) {
            throw new DatabaseMigrationException(newVersion);
        }

        MigrationConfig config = new MigrationConfig();
        config.setDbUsername(this.username);
        config.setDbPassword(this.password);
        config.setDbSchema("public");
        config.setDbUrl(this.url);

        MigrationRunner runner = new MigrationRunner(config);
        runner.run();

        this.refreshConnection();
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
