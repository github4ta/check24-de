package de.check24.tests.ui.base;

import de.check24.ui.driver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseUITest {
    protected static final Logger log = LoggerFactory.getLogger(BaseUITest.class);

    @AfterEach
    public void tearDownDriver() {
        Driver.quitDriver();
        log.info("Driver closed");
    }
}
