package de.check24.ui.driver;

import de.check24.config.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
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

    public static boolean isElementDisplayedWithWait(String locator, int seconds) {
        return getWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)))
                .isDisplayed();
    }

    // далее можно дописывать любые необходимые методы (инструменты) для взаимодействия с браузером,
    // вкладками, страницей и элементами


}
