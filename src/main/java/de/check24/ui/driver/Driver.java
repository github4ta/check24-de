package de.check24.ui.driver;

import de.check24.config.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigLoader.get("browser.type", "chrome");
            driver = DriverFactory.createDriver(browserType);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            DriverFactory.quitDriver(driver);
            driver = null;
        }
    }

    public static void navigateTo(String url) {
        getDriver().get(url);
    }

    public static void click(String locator) {
        getDriver().findElement(By.xpath(locator)).click();
    }

    public static void fill(String locator, String value) {
        getDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public static String getText(String locator) {
        return getDriver().findElement(By.xpath(locator)).getText();
    }

    public static void waitAndClick(String locator) {
        // можно дописать логику ожидания при необходимости
        getDriver().findElement(By.xpath(locator)).click();
    }

    public static void waitAndFill(String locator, String value) {
        // можно дописать логику ожидания при необходимости
        getDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public static String waitAndGetText(String locator) {
        // можно дописать логику ожидания при необходимости
        return getDriver().findElement(By.xpath(locator)).getText();
    }

    // далее можно дописывать любые необходимые методы (инструменты) для взаимодействия с браузером,
    // вкладками, страницей и элементами


}
