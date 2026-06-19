package com.mytheresa;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {

    private final String LOGIN_URL = "https://www.mytheresa.com/int/en/account/login";
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";
    private String emailInput = "//input[@name='email']";
    private String passwordInput = "//input[@name='password']";
    private String emailError = "//div[@id='email__error']";
    private String passwordError = "//div[@class='forminput__content__error' and @id='password__error']";
    private String saveChangesButton = "//div[@class='modal__wrapper__footer__buttons']/div";
    private String loginButton = "//div[@class='button']";
    String validEmail = "test@test.com";
    String validPassword = "Password123";


    @Test
    public void emptyEmail(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get(LOGIN_URL);
        acceptCookies(driver);
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(emailInput)).click();
        driver.findElement(By.xpath(passwordInput)).sendKeys(validPassword);
        driver.findElement(By.xpath(loginButton)).click();

        Assertions.assertEquals("Required field", driver.findElement(By.xpath(emailError)).getText());

        driver.quit();
    }

    @Test
    public void emptyPassword(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get(LOGIN_URL);
        acceptCookies(driver);
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(emailInput)).sendKeys(validEmail);
        driver.findElement(By.xpath(loginButton)).click();

        Assertions.assertEquals("Required field", driver.findElement(By.xpath(passwordError)).getText());

        driver.quit();
    }

    private void acceptCookies(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(shadowHostLocator)))
                .getShadowRoot()
                .findElement(By.cssSelector(acceptCookiesButton))
                .click();
    }
}
