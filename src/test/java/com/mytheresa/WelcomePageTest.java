package com.mytheresa;

import com.mytheresa.ui.AuthPage;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WelcomePageTest extends AuthPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPageTest.class);

    @Test
    @Name("UI-TC-005: Successful password masking")
    public void verifyPasswordMaskingAndUnmaskingTest() throws InterruptedException {
        WebDriver driver = initDriver();
        acceptCookies(driver);
        driver.findElement(getSaveChangesButton()).click();

        driver.findElement(getEmailInput()).click();
        driver.findElement(getPasswordInput()).sendKeys(getValidPassword());
        Assertions.assertEquals("password", driver.findElement(getPasswordInput()).getAttribute("type"));

        driver.findElement(getContentEyeIcon()).click();
        Assertions.assertEquals("text", driver.findElement(getPasswordInput()).getAttribute("type"));

        driver.quit();
    }

    private void acceptCookies(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(getShadowHostLocator()))
                .getShadowRoot()
                .findElement(getAcceptCookiesButton())
                .click();
    }

    private WebDriver initDriver() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(getLOGIN_URL());
        log.info("We on {}", driver.getCurrentUrl());
        Thread.sleep(1000);
        return driver;
    }
}
