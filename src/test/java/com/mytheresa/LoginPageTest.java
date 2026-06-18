package com.mytheresa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.List;

public class LoginPageTest {

    private static final Logger log = LoggerFactory.getLogger(LoginPageTest.class);
    private String loginURI = "https://www.mytheresa.com/int/en/account/login";

    private String emailInputField = "//input[@name ='email']";
    private String passwordInputField = "//input[@name='password' and @type = 'password']";
    private String loginSignInTitleCss = "//*[@class = 'login__signin__title']";
    private String logInButton = "//*[@class = 'button__text'  and text()= 'Log in' ]";

    private String registerButton = "//div[@class ='button__text' and text()='Register']";
    private String benefitItems = "//div[@class='usps__usp']";
    private String errorEmailMessage = "//div[@id = 'email__error' and @class = 'forminput__content__error' ]";
    private String errorPasswordMessage = "//div[@id = 'password__error' and @class = 'forminput__content__error' ]";

    private String modalWrapperSaveButton = "//div[@aria-label= 'Save Changes' and @class = 'button']";
    private String modalWrapperCountrySelectionButton = "//div[@class ='languageselector__country']";

    private String searchCountryInput = "//div[@class='countrylist__search']//input";
        private String countryFrance = "//div[@data-id='FR']";
        private String countryUnitedKingdom = "//div[@data-id='GB']";
        private String countryUnitedStates = "//div[@data-id='US']";
        private String countryGermany = "//div[@data-id='DE']";
        private String countryByDataId = "//div[@data-id='%s']";

    @Test
    void loginPageElementsExistTest() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(loginURI);
        log.info("We on {}", driver.getCurrentUrl());

        Thread.sleep(10000);

        log.info("Closing cookie consent via shadow DOM");
        SearchContext shadowRoot = driver.findElement(By.cssSelector("aside#usercentrics-cmp-ui")).getShadowRoot();
        shadowRoot.findElement(By.cssSelector("button#accept")).click();
        log.info("Cookie accept button clicked");
        try {
            log.info("Opening country selection modal");
            driver.findElement(By.xpath(modalWrapperCountrySelectionButton)).click();
            Thread.sleep(1000);

            log.info("Clicking search field");
            driver.findElement(By.xpath(searchCountryInput)).click();
            Thread.sleep(300);

            log.info("Searching for GB");
            driver.findElement(By.xpath(searchCountryInput)).sendKeys("United");
            Thread.sleep(500);

            log.info("Selecting GB from results");
            driver.findElement(By.xpath(countryUnitedKingdom)).click();
            Thread.sleep(500);

            log.info("Closing modal save button");
            driver.findElement(By.xpath(modalWrapperSaveButton)).click();

        } catch (Exception exception){
            log.info("Country selection modal failed. {}", exception.getMessage());
        }

        log.info("Verifying sign-in title exists");
        Assertions.assertTrue(driver.findElement(By.xpath(loginSignInTitleCss)).getText().contains("Already registered?"));
        log.info("Sign-in title verified");

        log.info("Verifying Log in button exists");
        Assertions.assertTrue(driver.findElement(By.xpath(logInButton)).getText().contains("Log in"));
        log.info("Log in button verified");

        log.info("Verifying Register button exists and enabled");
        Assertions.assertTrue(driver.findElement(By.xpath(registerButton)).getText().contains("Register"));
        Assertions.assertTrue(driver.findElement(By.xpath(registerButton)).isEnabled(), "Register button is not enabled");
        log.info("Register button verified");

        log.info("Verifying site benefits");
        List<WebElement> webElementList = driver.findElements(By.xpath(benefitItems));
        for (WebElement webElement : webElementList) {
            log.info("Site benefits: {}", webElement.getText());
        }
        log.info("Page verifying elements right");
    }

    @Test
    void emptyLoginFieldsTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(loginURI);
        log.info("We on {}", driver.getCurrentUrl());
        Thread.sleep(10000);

        log.info("Closing cookie consent via shadow DOM");
        SearchContext shadowRoot = driver.findElement(By.cssSelector("aside#usercentrics-cmp-ui")).getShadowRoot();
        shadowRoot.findElement(By.cssSelector("button#accept")).click();
        log.info("Cookie accept button clicked");
        try {
            log.info("Opening country selection modal");
            driver.findElement(By.xpath(modalWrapperCountrySelectionButton)).click();
            Thread.sleep(1000);

            log.info("Clicking search field");
            driver.findElement(By.xpath(searchCountryInput)).click();
            Thread.sleep(300);

            log.info("Searching for GB");
            driver.findElement(By.xpath(searchCountryInput)).sendKeys("United");
            Thread.sleep(500);

            log.info("Selecting GB from results");
            driver.findElement(By.xpath(countryUnitedKingdom)).click();
            Thread.sleep(500);

            log.info("Closing modal save button");
            driver.findElement(By.xpath(modalWrapperSaveButton)).click();

        } catch (Exception exception){
            log.info("Country selection modal failed. {}", exception.getMessage());
        }

        log.info("Clearing email field");
        driver.findElement(By.xpath(emailInputField)).sendKeys("");
        Thread.sleep(200);
        log.info("Verifying password field is displayed");
        Assertions.assertTrue(driver.findElement(By.xpath(passwordInputField)).isDisplayed());
        log.info("Clearing password field");
        driver.findElement(By.xpath(passwordInputField)).sendKeys("");
        log.info("Clicking Log in button");
        driver.findElement(By.xpath(logInButton)).click();
        log.info("Verifying email error is displayed");
        Assertions.assertTrue(driver.findElement(By.xpath(errorEmailMessage)).isDisplayed());
        log.info("Verifying password error is displayed");
        Assertions.assertTrue(driver.findElement(By.xpath(errorPasswordMessage)).isDisplayed());
        log.info("Verifying email error text");
        Assertions.assertTrue(driver.findElement(By.xpath(errorEmailMessage)).getText().equals("Required field"));
        log.info("Verifying password error text");
        Assertions.assertTrue(driver.findElement(By.xpath(errorPasswordMessage)).getText().equals("Required field"));
        log.info("Empty fields verification passed");

        driver.quit();
    }

    @Test
    public void verifyValidLoginAndPasswordSignInTest() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(loginURI);
        log.info("We on {}", driver.getCurrentUrl());
        Thread.sleep(10000);
        log.info("Closing cookie consent via shadow DOM");
        SearchContext shadowRoot = driver.findElement(By.cssSelector("aside#usercentrics-cmp-ui")).getShadowRoot();
        shadowRoot.findElement(By.cssSelector("button#accept")).click();
        log.info("Cookie accept button clicked");
        Thread.sleep(1000);

        try {
            log.info("Opening country selection modal");
            driver.findElement(By.xpath(modalWrapperCountrySelectionButton)).click();
            Thread.sleep(1000);

            log.info("Clicking search field");
            driver.findElement(By.xpath(searchCountryInput)).click();
            Thread.sleep(300);

            log.info("Searching for France");
            driver.findElement(By.xpath(searchCountryInput)).sendKeys("France");
            Thread.sleep(500);

            log.info("Selecting France from results");
            driver.findElement(By.xpath(countryFrance)).click();
            Thread.sleep(500);

            log.info("Closing modal save button");
            driver.findElement(By.xpath(modalWrapperSaveButton)).click();

        } catch (Exception exception){
            log.info("Country selection modal failed. {}", exception.getMessage());
        }

        // dWCrTb6_NP7YsiT
        // zbefap@chitthi.in
        String password = "dWCrTb6_NP7YsiT";
        String email = "zbefap@chitthi.in";

        log.info("Typing email");
        driver.findElement(By.xpath(emailInputField)).sendKeys(email);
        Thread.sleep(new SecureRandom().nextInt(800));

        log.info("Typing password");
        driver.findElement(By.xpath(passwordInputField)).sendKeys(password);

        log.info("Clicking Log in button");
        driver.findElement(By.xpath("//div[@class='button' and @role = 'button']")).click();
        
        log.info("Login submitted");
    }

}