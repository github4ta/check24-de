package de.check24.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for creating WebDriver instances
 */
public class DriverFactory {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    /**
     * Create WebDriver for specified browser
     */
    public static WebDriver createDriver(String browserType) {
        log.info("Creating WebDriver for browser: {}", browserType);

        return switch (browserType.toLowerCase()) {
            case "chrome" -> createChromeDriver();
            case "firefox" -> createFirefoxDriver();
            default -> {
                log.warn("Unknown browser type: {}, using Chrome by default", browserType);
                yield createChromeDriver();
            }
        };
    }

    /**
     * Create Chrome WebDriver
     */
    private static WebDriver createChromeDriver() {
        log.info("Initializing ChromeDriver...");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver
     */
    private static WebDriver createFirefoxDriver() {
        log.info("Initializing FirefoxDriver...");
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);

        return new FirefoxDriver(options);
    }

    /**
     * Close WebDriver
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            try {
                log.info("Closing WebDriver...");
                driver.quit();
            } catch (Exception e) {
                log.error("Error closing driver: {}", e.getMessage());
            }
        }
    }
}
