package de.check24.tests.ui;

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

public class RegistrationTest {

    private static final Faker faker = new Faker();

    private final String LOGIN_URL = "https://www.mytheresa.com/int/en/account/login";
    private String invalidEmail = "mytheresatgmailcom";
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";
    private String saveChangesButton = "//div[@class='modal__wrapper__footer__buttons']/div";
    private String emailInput = "//input[@name='email']";
    private String passwordInput = "//input[@name='password']";
    private String registerButton = "//a[@class='button button--alternative']";
    private String salutationDropDown = "//div[@class='form__element form__element--salutation']/div";
    private String salutationFirstChoice = "//div[@class='dropdown__item']";
    private String firstNameInput = "//input[@name='firstName']";
    private String lastNameInput = "//input[@name='lastName']";
    private String submitRegistrationButton = "//div[@class='form__submit']/div";
    private String emailError = "//div[@id='email__error']";
    private String passwordError = "//li[@class='password-rules__list__item password-rules__list__item--error']";
    private String firstName = "FirstName";
    private String lastName = "LastName";


    @Test
    public void invalidEmailAddress(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get(LOGIN_URL);

        findShadowRootElement(driver, shadowHostLocator, acceptCookiesButton).click();
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(registerButton)).click();
        Assertions.assertEquals("Registration", driver.getTitle());

        driver.findElement(By.xpath(emailInput)).sendKeys(invalidEmail);
        driver.findElement(By.xpath(submitRegistrationButton)).click();

        Assertions.assertEquals("Invalid email address", driver.findElement(By.xpath(emailError)).getText());

        driver.quit();
    }

    @Test
    public void emptyEmail(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(LOGIN_URL);

        findShadowRootElement(driver, shadowHostLocator, acceptCookiesButton).click();
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(registerButton)).click();
        Assertions.assertEquals("Registration", driver.getTitle());

        inputRegistrationData("","Password123", firstName, lastName, wait, driver);

        Assertions.assertEquals("Required field", driver.findElement(By.xpath(emailError)).getText());

        driver.quit();
    }

    @Test
    public void passwordWithoutNumbers(){
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(LOGIN_URL);

        findShadowRootElement(driver, shadowHostLocator, acceptCookiesButton).click();
        driver.findElement(By.xpath(saveChangesButton)).click();

        driver.findElement(By.xpath(registerButton)).click();
        Assertions.assertEquals("Registration", driver.getTitle());

        inputRegistrationData(generateRandomEmail(),"Password", firstName, lastName, wait, driver);

        Assertions.assertEquals("At least one number", driver.findElement(By.xpath(passwordError)).getText());

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

    private void inputRegistrationData (String email, String password, String firstName, String lastName, WebDriverWait wait, WebDriver driver){
        driver.findElement(By.xpath(emailInput)).sendKeys(email);
        driver.findElement(By.xpath(passwordInput)).sendKeys(password);
        driver.findElement(By.xpath(salutationDropDown)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(salutationFirstChoice))));
        driver.findElement(By.xpath(salutationFirstChoice)).click();
        driver.findElement(By.xpath(firstNameInput)).sendKeys(firstName);
        driver.findElement(By.xpath(lastNameInput)).sendKeys(lastName);
        driver.findElement(By.xpath(submitRegistrationButton)).click();
    }
}
