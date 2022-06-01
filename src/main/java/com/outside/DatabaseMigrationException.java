package com.outside;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMigrationException extends RuntimeException {

    public static final Logger LOGGER = Logger.getLogger("Database");

    public DatabaseMigrationException(String newVersion) {
        LOGGER.log(Level.INFO, "----------------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "Database was not up to date, a script has been generated for migration to v" + newVersion);
        LOGGER.log(Level.INFO, "You can simply restart this application to apply migration");
        LOGGER.log(Level.INFO, "Exiting with code 1");
        LOGGER.log(Level.INFO, "----------------------------------------------------------------------------------------------------");
        System.exit(1);
    }
}
