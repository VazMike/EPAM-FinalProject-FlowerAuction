package com.viazovski.flowerauction.connection;

import com.viazovski.flowerauction.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * {@code ConnectionPool} is a storage for database connections.
 * The pool size is determined by configuration file property.
 * The connections are created only once at the very beginning
 * and destroyed at the very end.
 * It gives available connections away to clients needing it and
 * retrieves them when they are no longer needed.
 */
public class ConnectionPool {

    private static Logger logger = LogManager.getLogger();

    private static LockAction lockAction = new LockAction();

    /**
     * The wait time for a connection release.
     */
    private static final int WAIT_TIME = 2;

    /**
     * {@code Holder} class is a mechanism for implementing compact thread-safe
     * singleton. The first time {@link #getInstance()} is called the {@code Holder}
     * class gets initialized while JVM sets implicit class initialization locks.
     * This approach is 100% reliable and possess nice properties such as
     * lazy initialization and fast access.
     */
    private static class Holder {
        private static ConnectionPool instance = new ConnectionPool();
    }

    private List<ProxyConnection> connections = new ArrayList<>();

    private String url;

    private String user;

    private String password;

    private int maxSize;

    public static ConnectionPool getInstance() {
        return Holder.instance;
    }

    private ConnectionPool() {
        ConnectionConfig.registerMySqlDriver();
        ResourceBundle bundle = ConnectionConfig.loadConfigProperties();
        loadProperties(bundle);
    }

    /**
     * Gets connection if one is available, otherwise tries to get it in secondary way.
     *
     * @return connection.
     * @throws ConnectionException if connection wasn't acquired.
     */
    public Connection acquireConnection() throws ConnectionException {
        return acquireAvailableConnection().orElseGet(this::acquireConnectionIfUnavailable);
    }

    public void drain() throws ConnectionException {
        lockAction.apply(this::drainUnsafe);
        ConnectionConfig.deregisterMySqlDriver();
    }

    private Optional<Connection> acquireAvailableConnection() {
        return lockAction.get(this::acquireAvailableConnectionUnsafe);
    }

    private Connection acquireConnectionIfUnavailable() {
        return lockAction.get(this::acquireConnectionIfUnavailableUnsafe);
    }

    private ProxyConnection createConnection() throws SQLException {
        return lockAction.sqlGet(this::createConnectionUnsafe);
    }

    /**
     * Tries to remove unhealthy connections and if upper limit on connection pool size
     * isn't reached a new {@link Connection} to database is created.
     *
     * @return connection.
     * @throws ConnectionException if connection wasn't acquired.
     */
    private Connection acquireConnectionIfUnavailableUnsafe() throws ConnectionException {
        connections.removeIf(c -> !isHealthy(c) && c.expire());
        if (connections.size() != maxSize) {
            try {
                return createConnection();
            } catch (SQLException e) {
                throw new ConnectionException("Unable to create new connection", e);
            }
        }
        try {
            TimeUnit.SECONDS.sleep(WAIT_TIME);
        } catch (InterruptedException ignored) {}
        return acquireAvailableConnection().orElseThrow(
                () -> new ConnectionException("Connection pool is full"));
    }

    /**
     * Finds any not in use connection.
     *
     * @return {@link Optional} describing the connection.
     */
    private Optional<Connection> acquireAvailableConnectionUnsafe() {
        return connections.stream()
                .filter(pc -> !pc.isInUse())
                .findAny()
                .map(pc -> pc.lease());
    }

    /**
     * Creates {@link Connection} and adds it to the pool.
     *
     * @return proxy connection
     * @throws SQLException if connection wasn't established.
     */
    private ProxyConnection createConnectionUnsafe() throws SQLException {
        var connection = DriverManager.getConnection(url, user, password);
        var proxyConnection = new ProxyConnection(connection);
        connections.add(proxyConnection.lease());
        return proxyConnection;
    }

    /**
     * Closes all connections and clears the pool.
     */
    private void drainUnsafe() {
        connections.forEach(pc -> pc.expire());
        connections.clear();
    }

    private boolean isHealthy(Connection connection) {
        try {
            return !connection.isClosed() && connection.getWarnings() == null;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Reads values from database configuration file and loads them in the class.
     *
     * @param bundle containing configuration file data.
     */
    private void loadProperties(ResourceBundle bundle) {
        try {
            url = bundle.getString("url");
            user = bundle.getString("user");
            password = bundle.getString("password");
            maxSize = Integer.parseInt(bundle.getString("size"));
        } catch (MissingResourceException e) {
            String msg = "Failed pool configuration";
            logger.fatal(msg);
            throw new RuntimeException(msg, e);
        }
    }
}
