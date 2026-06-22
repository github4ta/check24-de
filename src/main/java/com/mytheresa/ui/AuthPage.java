package com.mytheresa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */
public class AuthPage extends BaseMytheresaPage {

    private static final Logger log = LoggerFactory.getLogger(AuthPage.class);

    // =============== URLs ===============
    private final String LOGIN_URL = "https://www.mytheresa.com/int/en/account/login";
    private String registrationPageURI = "https://www.mytheresa.com/int/en/account/registration";

    // =============== Cookie consent ===============
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";

    // =============== Login locators ===============
    private String emailInput = "//input[@name='email']";
    private String passwordInput = "//input[@name='password']";
    private String emailError = "//div[@id='email__error']";
    private String passwordError = "//div[@class='forminput__content__error' and @id='password__error']";
    private String loginButton = "//div[@class='button']";
    private String logInButton = "//*[@class = 'button__text'  and text()= 'Log in' ]";
    private String getLogInButtonAlternative = "//div[@class='button' and @role = 'button']";

    // =============== Registration locators ===============
    private String registerButton = "//div[@aria-label = 'Register' and @class = 'button']";
    private String saveChangesButton = "//div[@class='modal__wrapper__footer__buttons']/div";
    private String firstnameInputField = "//input[@name = 'firstName']";
    private String lastnameInputField = "//input[@name = 'lastName']";
    private String passwordRulesConteinText = "//li[@class = 'password-rules__list__item']";
    private String vheckBoxLabelText = "//span[@class = 'checkbox__label__text']";
    private String chrckBoxInput = "//input[@name='subscribedToNewsletter']";
    private String sexDropdown = "//div[@class = 'dropdown__select__content']";

    // =============== Country selection ===============
    private String modalWrapperSaveButton = "//div[@aria-label= 'Save Changes' and @class = 'button']";
    private String modalWrapperCountrySelectionButton = "//div[@class ='languageselector__country']";
    private String searchCountryInput = "//div[@class='countrylist__search']//input";
    private String countryFrance = "//div[@data-id='FR']";
    private String countryUnitedKingdom = "//div[@data-id='GB']";
    private String countryUnitedStates = "//div[@data-id='US']";
    private String countryGermany = "//div[@data-id='DE']";
    private String countryByDataId = "//div[@data-id='%s']";

    // =============== Credentials ===============
    String validEmail = "test@test.com";
    String validPassword = "Password123";

    // ======================= Getters =======================

    public String getLOGIN_URL() {
        return LOGIN_URL;
    }

    public String getRegistrationPageURI() {
        return registrationPageURI;
    }

    public By getShadowHostLocator() {
        return By.cssSelector(shadowHostLocator);
    }

    public By getAcceptCookiesButton() {
        return By.cssSelector(acceptCookiesButton);
    }

    public By getEmailInput() {
        return By.xpath(emailInput);
    }

    public By getPasswordInput() {
        return By.xpath(passwordInput);
    }

    public By getEmailError() {
        return By.xpath(emailError);
    }

    public By getPasswordError() {
        return By.xpath(passwordError);
    }

    public By getLoginButton() {
        return By.xpath(loginButton);
    }

    public By getLogInButton() {
        return By.xpath(logInButton);
    }

    public By getGetLogInButtonAlternative() {
        return By.xpath(getLogInButtonAlternative);
    }

    public By getRegisterButton() {
        return By.xpath(registerButton);
    }

    public By getSaveChangesButton() {
        return By.xpath(saveChangesButton);
    }

    public By getFirstnameInputField() {
        return By.xpath(firstnameInputField);
    }

    public By getLastnameInputField() {
        return By.xpath(lastnameInputField);
    }

    public By getpasswordRulesConteinText() {
        return By.xpath(passwordRulesConteinText);
    }

    public By getCheckBoxLabelText() {
        return By.xpath(vheckBoxLabelText);
    }

    public By getChrckBoxInput() {
        return By.xpath(chrckBoxInput);
    }

    public By getSexDropdown() {
        return By.xpath(sexDropdown);
    }

    public By getModalWrapperSaveButton() {
        return By.xpath(modalWrapperSaveButton);
    }

    public By getModalWrapperCountrySelectionButton() {
        return By.xpath(modalWrapperCountrySelectionButton);
    }

    public By getSearchCountryInput() {
        return By.xpath(searchCountryInput);
    }

    public By getCountryFrance() {
        return By.xpath(countryFrance);
    }

    public By getCountryUnitedKingdom() {
        return By.xpath(countryUnitedKingdom);
    }

    public String getValidEmail() {
        return validEmail;
    }

    public String getValidPassword() {
        return validPassword;
    }

    // ======================= Actions =======================

    public void acceptCookies(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(getShadowHostLocator()))
                .getShadowRoot()
                .findElement(getAcceptCookiesButton())
                .click();
    }

    public WebDriver initDriver() throws InterruptedException {
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