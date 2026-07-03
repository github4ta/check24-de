package com.mytheresa.ui.base;

import com.mytheresa.ui.driver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @AfterEach
    public void tearDownDriver() {
        Driver.quitDriver();
        log.info("Driver closed");
    }
}
