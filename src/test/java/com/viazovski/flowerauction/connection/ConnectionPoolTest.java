package com.viazovski.flowerauction.connection;

import com.viazovski.flowerauction.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

@Test(groups = "connection-pool", dependsOnGroups = "connection-config")
public class ConnectionPoolTest {

    private static Logger logger = LogManager.getLogger();

    public void testGetConnection() {
        int amount = 10;
        List<Connection> connections = Arrays.asList(new Connection[amount]);
        connections.replaceAll(c -> ConnectionPool.getInstance().acquireConnection());
        logger.info("Test passed: Connections are working");
    }

    @Test(dependsOnMethods = "testGetConnection")
    public void testGetConnectionNegative() {
        try {
            int amount = 100;
            List<Connection> connections = Arrays.asList(new Connection[amount]);
            connections.replaceAll(c -> ConnectionPool.getInstance().acquireConnection());
        } catch (ConnectionException e) {
            logger.info("Test passed: Pool size limit has been exceeded");
        }
    }

    @Test(dependsOnMethods = "testGetConnectionNegative")
    public void testDrain() {
        ConnectionPool.getInstance().drain();
        logger.info("Test passed: Pool is drained successfully");
    }
}