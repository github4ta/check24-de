package com.mytheresa;

import com.mytheresa.ui.AuthPage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.time.Duration;

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

        try {
            log.info("Opening country selection modal");
            driver.findElement(getModalWrapperCountrySelectionButton()).click();
            Thread.sleep(1000);

            log.info("Clicking search field");
            driver.findElement(getSearchCountryInput()).click();
            Thread.sleep(300);

            log.info("Searching for France");
            driver.findElement(getSearchCountryInput()).sendKeys("France");
            Thread.sleep(500);

            log.info("Selecting France from results");
            driver.findElement(getCountryFrance()).click();
            Thread.sleep(500);

            log.info("Closing modal save button");
            driver.findElement(getModalWrapperSaveButton()).click();

        } catch (Exception exception) {
            log.info("Country selection modal failed. {}", exception.getMessage());
        }

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

    public void passUserPreferences(WebDriver driver) {

        try {
            log.info("Opening country selection modal");
            driver.findElement(getModalWrapperCountrySelectionButton()).click();
            Thread.sleep(1000);

            log.info("Clicking search field");
            driver.findElement(getSearchCountryInput()).click();
            Thread.sleep(300);

            log.info("Searching for GB");
            driver.findElement(getSearchCountryInput()).sendKeys("United");
            Thread.sleep(500);

            log.info("Selecting GB from results");
            driver.findElement(getCountryUnitedKingdom()).click();
            Thread.sleep(500);

            log.info("Closing modal save button");
            driver.findElement(getModalWrapperSaveButton()).click();

        } catch (Exception exception) {
            log.info("Country selection modal failed. {}", exception.getMessage());
        }
    }

}
