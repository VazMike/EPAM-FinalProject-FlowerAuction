package com.viazovski.flowerauction.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class AuctionServiceTest {

    private static Logger logger = LogManager.getLogger();

    private AuctionService service = new AuctionService();

    @Test(groups = {"service", "validation"})
    public void testPositiveValidateAuctionForm() {
        var name = "auction";
        var time = System.currentTimeMillis() + 10_000_000;
        var timestamp = new Timestamp(time);
        var isValid = service.validateAuctionForm(name, timestamp).isEmpty();
        Assert.assertTrue(isValid);
        logger.info("Positive auction validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeTimePassedValidateAuctionForm() {
        var name = "auction";
        var time = System.currentTimeMillis() - 10_000_000;
        var timestamp = new Timestamp(time);
        var isValid = service.validateAuctionForm(name, timestamp).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative time passed auction validation test passed");
    }
}