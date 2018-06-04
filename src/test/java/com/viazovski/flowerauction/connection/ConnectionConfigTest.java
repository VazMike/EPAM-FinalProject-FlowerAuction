package com.viazovski.flowerauction.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

@Test(groups = "connection-config")
public class ConnectionConfigTest {

    private static Logger logger = LogManager.getLogger();

    public void testLoadConfigProperties() {
        ResourceBundle bundle = ConnectionConfig.loadConfigProperties();
        Assert.assertTrue(bundle.containsKey("url") &&
                bundle.containsKey("user") &&
                bundle.containsKey("password") &&
                bundle.containsKey("size"));
        logger.info("Test passed: Properties exist & are well-formed");
    }

    public void testRegisterMySqlDriver() {
        ConnectionConfig.registerMySqlDriver();
        logger.info("Test passed: MySQL drivers are registered successfully");
    }
}
