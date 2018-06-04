package com.viazovski.flowerauction.connection;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.mysql.jdbc.Driver;
import com.viazovski.flowerauction.exception.ConnectionException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * {@code ConnectionConfig} is util class containing helper methods
 * to set up a connection with a database.
 */
class ConnectionConfig {

    private static final String FILENAME = "pool-config";

    static ResourceBundle loadConfigProperties() {
        return ResourceBundle.getBundle(FILENAME);
    }

    /**
     * Sets MySQL driver to work with a database.
     *
     * @throws ConnectionException if MySQL Driver can't be instantiated
     * or registered by DriverManager.
     */
    static void registerMySqlDriver() {
        Driver driver;
        try {
            driver = new Driver();
        } catch (SQLException e) {
            throw new ConnectionException("Unable to create MySQL driver", e);
        }
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new ConnectionException("Unable to register MySQL driver", e);
        }
    }

    /**
     * Releases all acquired drivers and deregisters them.
     *
     * @throws ConnectionException if MySQL driver can't be deregistered.
     */
    static void deregisterMySqlDriver() {
        try {
            Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                DriverManager.deregisterDriver(drivers.nextElement());
            }
            AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (SQLException e) {
            throw new ConnectionException("Unable to deregister driver", e);
        }
    }
}
