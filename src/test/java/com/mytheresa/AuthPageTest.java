package com.mytheresa;

import com.mytheresa.ui.AuthPage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */
public class AuthPageTest  extends AuthPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPageTest.class);

    @Test
    @Name("UI-TC-002: Validation error when submitting empty Email and valid Password")
    public void emptyEmail() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get(getLOGIN_URL());
        acceptCookies(driver);
        driver.findElement(getSaveChangesButton()).click();

        driver.findElement(getEmailInput()).click();
        driver.findElement(getPasswordInput()).sendKeys(getValidPassword());
        driver.findElement(getLoginButton()).click();

        Assertions.assertEquals("Required field", driver.findElement(getEmailError()).getText());

        driver.quit();
    }

    @Test
    @Name("UI-TC-003: Validation error when submitting valid Email and empty Password")
    public void emptyPassword() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get(getLOGIN_URL());
        acceptCookies(driver);
        driver.findElement(getSaveChangesButton()).click();

        driver.findElement(getEmailInput()).sendKeys(getValidEmail());
        driver.findElement(getLoginButton()).click();

        Assertions.assertEquals("Required field", driver.findElement(getPasswordError()).getText());

        driver.quit();
    }

    @Test
    @Name("UI-TC-001: Validation error when submitting an empty login form")
    void emptyLoginFieldsTest() throws InterruptedException {

        WebDriver driver = initDriver();
        acceptCookies(driver);
        passUserPreferences(driver);

        log.info("Clearing email field");
        driver.findElement(getEmailInput()).sendKeys("");
        Thread.sleep(800);
        log.info("Verifying password field is displayed");
        Assertions.assertTrue(driver.findElement(getPasswordInput()).isDisplayed());
        log.info("Clearing password field");
        driver.findElement(getPasswordInput()).sendKeys("");
        log.info("Clicking Log in button");
        driver.findElement(getLogInButton()).click();
        log.info("Verifying email error is displayed");
        Assertions.assertTrue(driver.findElement(getEmailError()).isDisplayed());
        log.info("Verifying password error is displayed");
        Assertions.assertTrue(driver.findElement(getPasswordError()).isDisplayed());
        log.info("Verifying email error text");
        Assertions.assertTrue(driver.findElement(getEmailError()).getText().equals("Required field"));
        log.info("Verifying password error text");
        Assertions.assertTrue(driver.findElement(getPasswordError()).getText().equals("Required field"));
        log.info("Empty fields verification passed");

        driver.quit();
    }

    @Test
    @Name("UI-TC-004: Successful login with valid credentials")
    public void verifyValidLoginAndPasswordSignInTest() throws InterruptedException {
        WebDriver driver = initDriver();
        acceptCookies(driver);
        passUserPreferences(driver);

        String password = "dWCrTb6_NP7YsiT";
        String email = "zbefap@chitthi.in";

        log.info("Typing valid mail");
        driver.findElement(getEmailInput()).sendKeys(email);
        Thread.sleep(new SecureRandom().nextInt(800));

        log.info("Typing password");
        driver.findElement(getPasswordInput()).sendKeys(password);
        Thread.sleep(new SecureRandom().nextInt(800));

        try {
            Assertions.assertFalse(driver.findElement(getEmailError()).isDisplayed(), "Email message displayed");
        } catch (NoSuchElementException e){
            Assertions.assertTrue(e.getMessage().contains("no such element"),"Another Error");
            log.info("Verifying email error is not displayed");
        }
        try {
            Assertions.assertFalse(driver.findElement(getPasswordError()).isDisplayed(), "Password message displayed");
        }catch (NoSuchElementException e){
            Assertions.assertTrue(e.getMessage().contains("no such element"),"Another Error");
            log.info("Verifying password error is not displayed");
        }

        log.info("Clicking Log in button");
        driver.findElement(getGetLogInButtonAlternative()).click();

        log.info("Login submitted");

        driver.quit();
    }
}
