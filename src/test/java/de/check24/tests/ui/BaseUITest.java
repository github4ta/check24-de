package de.check24.tests.ui;

import de.check24.config.ConfigLoader;
import de.check24.ui.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all UI tests
 */
public abstract class BaseUITest {
    protected static final Logger log = LoggerFactory.getLogger(BaseUITest.class);
    protected WebDriver driver;

    @BeforeEach
    void initDriver() {
        String browserType = ConfigLoader.get("browser.type", "chrome");
        driver = DriverFactory.createDriver(browserType);
        log.info("Driver initialized");
    }

    @AfterEach
    void tearDownDriver() {
        DriverFactory.quitDriver(driver);
        log.info("Driver closed");
    }
}
