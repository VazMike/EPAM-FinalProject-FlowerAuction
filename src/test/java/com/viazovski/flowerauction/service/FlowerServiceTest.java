package com.viazovski.flowerauction.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowerServiceTest {

    private static Logger logger = LogManager.getLogger();

    private FlowerService service = new FlowerService();

    @Test(groups = {"service", "validation"})
    public void testPositiveValidateFlowerForm() {
        var name = "Flower";
        int value = 5;
        var isValid = service.validateFlowerForm(name, value).isEmpty();
        Assert.assertTrue(isValid);
        logger.info("Positive flower validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeValueNotInRangeValidateFlowerForm() {
        var name = "Flower";
        int value = -5;
        var isValid = service.validateFlowerForm(name, value).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative value not in range flower validation test passed");
    }
}