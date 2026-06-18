package de.check24.tests.ui;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    private static final Faker faker = new Faker();

    private String mytheresaLoginUrl = "https://www.mytheresa.com/int/en/account/login";
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";
    private String saveChangesButton = "//div[@class='modal__wrapper__footer__buttons']/div";
    private String alreadyRegisteredText = "//div[@class='login__signin__title']";
    private String emailInput = "//input[@name='email']";
    private String passwordInput = "//input[@name='password']";
    private String loginButton = "//div[@class='form__submit']/div";
    private String wrongCredentialsAlert = "//div[@class = 'notification__content']";
    private String registerButton = "//a[@class='button button--alternative']";
    private String salutationDropDown = "//div[@class='form__element form__element--salutation']/div";
    private String salutationFirstChoice = "//div[@class='dropdown__item']";
    private String firstNameInput = "//input[@name='firstName']";
    private String lastNameInput = "//input[@name='lastName']";
    private String subscribeToNewLettersCheckBox = "//input[@name='subscribedToNewsletter']";
    private String closeRegistrationButton = "//div[@class='form__submit']/div";


    //Successful login with valid data
    //TC‑LOG‑001
    @Test
    public void validLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get(mytheresaLoginUrl);

        findShadowRootElement(driver, shadowHostLocator, acceptCookiesButton).click();
        driver.findElement(By.xpath(saveChangesButton)).click();

        Assertions.assertEquals("Already registered?", driver.findElement(By.xpath(alreadyRegisteredText)).getText());

        driver.findElement(By.xpath(emailInput)).sendKeys("mytheresat@gmail.com");
        driver.findElement(By.xpath(passwordInput)).sendKeys("userPass123");
        driver.findElement(By.xpath(loginButton)).click();

        Assertions.assertEquals("My Account Dashboard | Mytheresa", driver.getTitle());

        driver.quit();
    }


    //Login with invalid password
    //TC‑LOG‑002
    @Test
    public void wrongPasswordTest() {
        WebDriver driver = new ChromeDriver();
        driver.get(mytheresaLoginUrl);

        findShadowRootElement(driver, shadowHostLocator, acceptCookiesButton).click();
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(emailInput)).sendKeys("mytheresat@gmail.com");
        driver.findElement(By.xpath(passwordInput)).sendKeys("userPass456");
        driver.findElement(By.xpath(loginButton)).click();

        Assertions.assertEquals("The credentials you have inserted are not correct", driver.findElement(By.xpath(wrongCredentialsAlert)).getText());

        driver.quit();
    }

    //Successful registration
    //TC‑REG‑001
    @Test
    public void registrationTest(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(mytheresaLoginUrl);

        findShadowRootElement(driver, shadowHostLocator, acceptCookiesButton).click();
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(registerButton)).click();
        Assertions.assertEquals("Registration", driver.getTitle());

        driver.findElement(By.xpath(emailInput)).sendKeys(generateRandomEmail());
        driver.findElement(By.xpath(passwordInput)).sendKeys("Password123");
        driver.findElement(By.xpath(salutationDropDown)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(salutationFirstChoice))));
        driver.findElement(By.xpath(salutationFirstChoice)).click();
        driver.findElement(By.xpath(firstNameInput)).sendKeys("FirstName");
        driver.findElement(By.xpath(lastNameInput)).sendKeys("LastName");
        driver.findElement(By.xpath(subscribeToNewLettersCheckBox)).click();
        driver.findElement(By.xpath(closeRegistrationButton)).click();

        Assertions.assertEquals("My Account Dashboard | Mytheresa", driver.getTitle());

        driver.quit();
    }


    private WebElement findShadowRootElement(WebDriver driver, String shadowHostLocator, String elementToFind){
        return  new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(shadowHostLocator)))
                .getShadowRoot()
                .findElement(By.cssSelector(elementToFind));
    }

    private String generateRandomEmail (){
        return "aQa" + faker.internet().emailAddress();
    }
}