package com.mytheresa.ui.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyDriver {
    private static WebDriver driver;

    private MyDriver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // public click on element
    public static void click(String locator) {
        getDriver().findElement(By.xpath(locator)).click();
    }

    // public sendKeys into element
    public static void fill(String locator, String value) {
        getDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    // public getText from element
    public static String getText(String locator) {
        return getDriver().findElement(By.xpath(locator)).getText();
    }

    public static void open(String url) {
        getDriver().get(url);
    }
}
