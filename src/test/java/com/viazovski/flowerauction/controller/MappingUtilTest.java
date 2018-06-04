package com.viazovski.flowerauction.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Optional;

import static com.viazovski.flowerauction.controller.MappingUtil.mapToCommand;
import static com.viazovski.flowerauction.controller.MappingUtil.mapToJsp;

@Test(groups = "mapping-util")
public class MappingUtilTest {

    private static Logger logger = LogManager.getLogger();

    public void testMapToCommand() {
        var annotation = MappingUtil.class.getAnnotation(RequestMethods.class);
        String[] posts = annotation.posts();
        Arrays.stream(posts).forEach(url -> mapToCommand(url));
        logger.info("All POST-requests are mapped to Commands successfully");
    }

    public void testMapToJsp() {
        var annotation = MappingUtil.class.getAnnotation(RequestMethods.class);
        String[] gets = annotation.gets();
        Optional<String> nullJsp = Arrays.stream(gets)
                .filter(url -> mapToJsp(url) == null)
                .findAny();
        Assert.assertFalse(nullJsp.isPresent());
        logger.info("All GET-requests are mapped to JSPs successfully");
    }
}
