package com.outside;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMigrationException extends RuntimeException {

    public static final Logger LOGGER = Logger.getLogger("Database");

    public DatabaseMigrationException(String newVersion) {
        Map<String, String> env = System.getenv();
        boolean ignoreErr = env.get("OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS").equalsIgnoreCase("true");

        LOGGER.log(Level.INFO, "----------------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "Database was not up to date, a script has been generated for migration to v" + newVersion);
        LOGGER.log(Level.INFO, "You can simply restart this application to apply migration");
        if (!ignoreErr) {
            LOGGER.log(Level.INFO, "Exiting with code 0");
        } else {
            LOGGER.log(Level.INFO, "Ignoring, since OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS is set to true");
        }
        LOGGER.log(Level.INFO, "----------------------------------------------------------------------------------------------------");

        if (!ignoreErr) {
            System.exit(0);
        }
    }
}
