package ch.comparis;

import de.check24.tests.ui.base.BaseUITest;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class AuthTest  {

    SecureRandom rand = new SecureRandom();
    Faker faker = new Faker();

    private static final Logger log = LoggerFactory.getLogger(AuthTest.class);

    private String acceptCookiesButton = "//a[@class ='cmpboxbtn cmpboxbtnyes cmptxt_btn_yes']";
    private String loginButton = "//a[@class='css-je6r1p']";
    private String loginPageTitle = "//h1";
    private String emailInputField = "//input[@id = 'Input_Email']";
    private String passwordInputField = "//input[@id = 'Input_Password']";
        private String anmeldenButton = "//button[@name= 'Input.Button']";

    private String errorEmailMessage = "//span[@class = 'info field-validation-error' and @data-valmsg-for ='Input.Email']";
    private String errorPasswordMessage = "//span[@class = 'info field-validation-error' and @data-valmsg-for ='Input.Password']";
    private String errorSummaryMassage = "//*[@class='validation-summary-errors']";


    @Test
    public void LoginWelcomeTest() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://www.comparis.ch/");
        log.info("We on {}", driver.getCurrentUrl());
        Thread.sleep(rand.nextInt(500));
        try {
            driver.findElement(By.xpath(acceptCookiesButton)).click();
            log.info("Cookies accepted");
        } catch (RuntimeException e) {
            log.warn("No Cookies message",e.getMessage());
        }

        log.info("Login button {} clicked", loginButton);
        Thread.sleep(rand.nextInt(500));
        driver.findElement(By.xpath(loginButton)).click();
        Assertions.assertTrue(driver.findElement(By.xpath(loginPageTitle)).getText().equals("Willkommen zurück. Melden Sie sich jetzt für Ihr Benutzerkonto an."));
        log.info("Welcome text exist");

        driver.quit();
    }

    @Test
            public void EmptyLoginTest() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.comparis.ch/");

        log.info("We on {}", driver.getCurrentUrl());
        Thread.sleep(rand.nextInt(500));
        try {
            driver.findElement(By.xpath(acceptCookiesButton)).click();
            log.info("Cookies accepted");
        } catch (RuntimeException e) {
            log.warn("No Cookies message",e.getMessage());
        }


        driver.findElement(By.xpath(loginButton)).click();
        log.info("Login button {} clicked", loginButton);

        driver.findElement(By.xpath(anmeldenButton)).click();
        log.info("Anmelden button {} clicked", anmeldenButton);

        Assertions.assertEquals("Bitte geben Sie Ihre E-Mail-Adresse ein.",driver.findElement(By.xpath(errorEmailMessage)).getText());
        Assertions.assertEquals("Bitte geben Sie Ihr Passwort ein.",driver.findElement(By.xpath(errorPasswordMessage)).getText());
        log.info("Page verifying empty fields right");

        driver.quit();

    }

    @Test
    public void VerofyValidEmailAndEmailTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver =new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.comparis.ch/");
        Thread.sleep(rand.nextInt(500));
        log.info("We on {}", driver.getCurrentUrl());
        try {
            driver.findElement(By.xpath(acceptCookiesButton)).click();
            log.info("Cookies accepted");
        } catch (RuntimeException e) {
            log.warn("No Cookies message",e.getMessage());
        }
        Thread.sleep(rand.nextInt(500));
        driver.findElement(By.xpath(loginButton)).click();
        log.info("Login button {} clicked", loginButton);

        int randomNumber = rand.nextInt(9999);

        driver.findElement(By.xpath(emailInputField)).sendKeys(faker.name().fullName().replace(" ","")+randomNumber+"@gmail.com");
        Thread.sleep(rand.nextInt(500));
        driver.findElement(By.xpath(passwordInputField)).sendKeys("1234");
        Thread.sleep(rand.nextInt(500));
        driver.findElement(By.xpath(anmeldenButton)).click();
        Assertions.assertEquals("E-Mail-Adresse unbekannt und/oder Passwort ungültig",driver.findElement(By.xpath(errorSummaryMassage)).getText(),
                "wrong error message");
        log.info("Page verifying invalid password right");

        driver.quit();

    }
}
