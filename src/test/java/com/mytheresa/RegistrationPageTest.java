package com.mytheresa;

import com.mytheresa.ui.AuthPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RegistrationPageTest  extends AuthPage {

    private static final Logger log = LoggerFactory.getLogger(RegistrationPageTest.class);

    @Test
    public void registrationPageElementsExistTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        log.info("Opening page: {}", getRegistrationPageURI());
        driver.get(getRegistrationPageURI());
        Thread.sleep(900);

        log.info("Closing modal save button");
        driver.findElement(getModalWrapperSaveButton()).click();

        log.info("Checking email field is displayed");
        Assertions.assertTrue(driver.findElement(getEmailInput()).isDisplayed());
        log.info("Typing email");
        driver.findElement(getEmailInput()).sendKeys("email");
        Assertions.assertTrue(driver.findElement(getEmailInput()).getAttribute("value").equals("email"));
        log.info("Email field OK");

        log.info("Checking password field is displayed");
        Assertions.assertTrue(driver.findElement(getPasswordInput()).isDisplayed());
        log.info("Typing password");
        driver.findElement(getPasswordInput()).sendKeys("password");
        Assertions.assertTrue(driver.findElement(getPasswordInput()).getAttribute("value").equals("password"));
        log.info("Password field OK");

        log.info("Clearing email and password fields");
        driver.findElement(getEmailInput()).sendKeys(Keys.CONTROL, "a");
        driver.findElement(getEmailInput()).sendKeys(Keys.DELETE);
        driver.findElement(getPasswordInput()).sendKeys(Keys.CONTROL, "a");
        driver.findElement(getPasswordInput()).sendKeys(Keys.DELETE);

        driver.navigate().refresh();
        log.info("Page refreshed");

        List<WebElement> list = driver.findElements(getpasswordRulesConteinText());
        log.info("Password rules count: {}", list.size());
        for (WebElement item : list) {
            log.info("Password rule: [{}]", item.getText());
        }

        log.info("Checking firstname field is empty");
        Assertions.assertTrue(driver.findElement(getFirstnameInputField()).getAttribute("value").isEmpty());
        driver.findElement(getFirstnameInputField()).sendKeys(new Faker().name().firstName());
        Assertions.assertTrue(!driver.findElement(getFirstnameInputField()).getAttribute("value").isEmpty());
        log.info("Firstname field OK");

        Thread.sleep(500);
        log.info("Checking lastname field is empty");
        Assertions.assertTrue(driver.findElement(getLastnameInputField()).getAttribute("value").isEmpty());
        driver.findElement(getLastnameInputField()).sendKeys(new Faker().name().lastName());
        Assertions.assertTrue(!driver.findElement(getLastnameInputField()).getAttribute("value").isEmpty());
        log.info("Lastname field OK");

        log.info("Checking checkbox is not selected by default");
        Assertions.assertTrue(!driver.findElement(getChrckBoxInput()).isSelected(), "check box selected");
        log.info("Clicking checkbox");
        driver.findElement(getChrckBoxInput()).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElement(getChrckBoxInput()).isSelected(), "check box is not selected");
        log.info("Checkbox is selected");
        Assertions.assertTrue(driver.findElement(getCheckBoxLabelText()).getText().contains("I agree to receive"));
        log.info("Checkbox label text OK");

        Assertions.assertTrue(driver.findElement(getRegisterButton()).getText().equals("Register"));
        log.info("Register button text OK");

        log.info("Checking sex dropdown is displayed and enabled");
        Assertions.assertTrue(driver.findElement(getSexDropdown()).isDisplayed());
        Assertions.assertTrue(driver.findElement(getSexDropdown()).isEnabled());
        log.info("Sex dropdown OK");

        driver.quit();
    }

}