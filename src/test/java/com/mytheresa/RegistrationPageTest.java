package com.mytheresa;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RegistrationPageTest {

    private static final Logger log = LoggerFactory.getLogger(RegistrationPageTest.class);

    private String registrationPageURI = "https://www.mytheresa.com/int/en/account/registration";

    private String emailInputField = "//input[@name ='email']";
    private String passwordInputField = "//input[@name='password' and @type = 'password']";
    private String modalWrapperSaveButton = "//div[@aria-label= 'Save Changes' and @class = 'button']";
    private String firstnameInputField = "//input[@name = 'firstName']";
    private String lastnameInputField = "//input[@name = 'lastName']";
    private String vheckBoxLabelText = "//span[@class = 'checkbox__label__text']";

    private String passwordRulesConteinText = "//li[@class = 'password-rules__list__item']";
    private String chrckBoxInput = "//input[@name='subscribedToNewsletter']";

    private String registerButton = "//div[@aria-label = 'Register' and @class = 'button']";

    private String sexDropdown = "//div[@class = 'dropdown__select__content']";

    @Test
    public void registrationPageElementsExistTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        log.info("Opening page: {}", registrationPageURI);
        driver.get(registrationPageURI);
        Thread.sleep(900);

        log.info("Closing modal save button");
        driver.findElement(By.xpath(modalWrapperSaveButton)).click();

        log.info("Checking email field is displayed");
        Assertions.assertTrue(driver.findElement(By.xpath(emailInputField)).isDisplayed());
        log.info("Typing email");
        driver.findElement(By.xpath(emailInputField)).sendKeys("email");
        Assertions.assertTrue(driver.findElement(By.xpath(emailInputField)).getAttribute("value").equals("email"));
        log.info("Email field OK");

        log.info("Checking password field is displayed");
        Assertions.assertTrue(driver.findElement(By.xpath(passwordInputField)).isDisplayed());
        log.info("Typing password");
        driver.findElement(By.xpath(passwordInputField)).sendKeys("password");
        Assertions.assertTrue(driver.findElement(By.xpath(passwordInputField)).getAttribute("value").equals("password"));
        log.info("Password field OK");

        log.info("Clearing email and password fields");
        driver.findElement(By.xpath(emailInputField)).sendKeys(Keys.CONTROL, "a");
        driver.findElement(By.xpath(emailInputField)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(passwordInputField)).sendKeys(Keys.CONTROL, "a");
        driver.findElement(By.xpath(passwordInputField)).sendKeys(Keys.DELETE);

        driver.navigate().refresh();
        log.info("Page refreshed");

        List<WebElement> list = driver.findElements(By.xpath(passwordRulesConteinText));
        log.info("Password rules count: {}", list.size());
        for (WebElement item : list) {
            log.info("Password rule: [{}]", item.getText());
        }

        log.info("Checking firstname field is empty");
        Assertions.assertTrue(driver.findElement(By.xpath(firstnameInputField)).getAttribute("value").isEmpty());
        driver.findElement(By.xpath(firstnameInputField)).sendKeys(new Faker().name().firstName());
        Assertions.assertTrue(!driver.findElement(By.xpath(firstnameInputField)).getAttribute("value").isEmpty());
        log.info("Firstname field OK");

        Thread.sleep(500);
        log.info("Checking lastname field is empty");
        Assertions.assertTrue(driver.findElement(By.xpath(lastnameInputField)).getAttribute("value").isEmpty());
        driver.findElement(By.xpath(lastnameInputField)).sendKeys(new Faker().name().lastName());
        Assertions.assertTrue(!driver.findElement(By.xpath(lastnameInputField)).getAttribute("value").isEmpty());
        log.info("Lastname field OK");

        log.info("Checking checkbox is not selected by default");
        Assertions.assertTrue(!driver.findElement(By.xpath(chrckBoxInput)).isSelected(), "check box selected");
        log.info("Clicking checkbox");
        driver.findElement(By.xpath(chrckBoxInput)).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElement(By.xpath(chrckBoxInput)).isSelected(), "check box is not selected");
        log.info("Checkbox is selected");
        Assertions.assertTrue(driver.findElement(By.xpath(vheckBoxLabelText)).getText().contains("I agree to receive"));
        log.info("Checkbox label text OK");

        Assertions.assertTrue(driver.findElement(By.xpath(registerButton)).getText().equals("Register"));
        log.info("Register button text OK");

        log.info("Checking sex dropdown is displayed and enabled");
        Assertions.assertTrue(driver.findElement(By.xpath(sexDropdown)).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath(sexDropdown)).isEnabled());
        log.info("Sex dropdown OK");

        driver.quit();
    }

}