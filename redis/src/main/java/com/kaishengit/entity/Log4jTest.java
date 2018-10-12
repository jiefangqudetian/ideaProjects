package com.kaishengit.entity;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

    private Logger logger = Logger.getLogger(Log4jTest.class);


    @Test
    public void log(){

        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");


    }
}
