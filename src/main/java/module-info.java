module com.viazovski.flowerauction {
    requires java.sql;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
	requires javax.servlet.api;
	requires jsp.api;
	requires guava;
    requires gson;
    requires commons.lang;
    requires mysql.connector.java;
    requires annotations;

    exports com.viazovski.flowerauction.command;
    exports com.viazovski.flowerauction.connection;
    exports com.viazovski.flowerauction.controller;
    exports com.viazovski.flowerauction.exception;
    exports com.viazovski.flowerauction.model;
    exports com.viazovski.flowerauction.repository;
    exports com.viazovski.flowerauction.service;
    exports com.viazovski.flowerauction.specification;
    exports com.viazovski.flowerauction.tag;
    exports com.viazovski.flowerauction.validationmessage;
}