package de.check24.tests.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class SimpleTest {

    private static final Logger log = LoggerFactory.getLogger(SimpleTest.class);

    private static WebElement getElementInShadowRoot(WebDriver driver, String hostLocator, String cssSelector) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(NoSuchElementException.class)
                .until(d -> {
                    WebElement element = d.findElement(By.xpath(hostLocator))
                            .getShadowRoot()
                            .findElement(By.cssSelector(cssSelector));
                    return element.isDisplayed() ? element : null;
                });
    }

    private static String getElementInShadowRootText(WebDriver driver, String hostLocator, String cssSelector) {
       return   getElementInShadowRoot(driver,hostLocator, cssSelector).getText();
    }

    private String check24Url = "https://www.check24.de/";


    private String cookiesAcceptButton = "//*[text()='geht klar']";
    private String cookiesNotice = "//*[@class ='c24-cookie-consent-notice']";
    private String anmeldenButton  = "//*[text()='Anmelden']";
    private String welcomeText ="//*[text()='Willkommen']";

    private String hostLocator = "//unified-login";
    private String loginTitleCssLocatorText = "div.c24-uli-title[data-tid='page-title']";
    private String loginCssLocatorInputField = "input[data-tid='input-login']";
    private String weiterCssLocatorButton = "#c24-uli-login-btn";

    private String vornameInputField = "#cl_ul_firstname";
    private String nachnameInputField = "#cl_ul_lastname";
    private String passwortInputField = "#cl_pw_register";
    private String passwortConfirmInputField = "#cl_ul_pw_register_repeat";
    private String weiterCssLocatorButton2 = "#c24-uli-register-btn";


    @Test
    // TS-001
    //"Given I am logged out on https://www.check24.de
    //When I open the login via ""Anmelden"" (Login) and choose ""Kundenkonto erstellen"" (Create account)
    //And I enter the new email ""qa.aqa.newflow.2026@gmail.com"" and press ""weiter"" (Continue)
    //Then the ""Kundenkonto anlegen"" (Create account) form asks me for ""Vorname"" (First name), ""Nachname"" (Last name), ""Passwort"" (Password) and ""Passwort wiederholen"" (Repeat password)
    //And a ""weiter"" (Continue) button is available"
    public void VerifyingRegistrationPageTest() {

        WebDriver driver = new ChromeDriver();

        driver.get(check24Url);
        log.info("Navigating to "+driver.getTitle());

        Assertions.assertTrue(driver.findElement(By.xpath(cookiesNotice)).getText().contains("Mit dem Klick auf"),"There is no mit dem Klick auf");
        driver.findElement(By.xpath(cookiesAcceptButton)).click();
        log.info("Cookies accepted");

        driver.findElement(By.xpath(anmeldenButton)).click();
        String anmeldenText = getElementInShadowRootText(driver,hostLocator,loginTitleCssLocatorText);
        Assertions.assertEquals("Anmelden", anmeldenText,"Anmelden text dont match");

        WebElement loginInput = getElementInShadowRoot(driver,hostLocator, loginCssLocatorInputField);
        loginInput.sendKeys("qa.aqa.newflow.2026@gmail.com");
        Assertions.assertEquals("qa.aqa.newflow.2026@gmail.com", loginInput.getAttribute("value"));
        WebElement waiterButton = getElementInShadowRoot(driver,"//unified-login",weiterCssLocatorButton);
        waiterButton.click();
        log.info("New email entered and excepted");

        log.info("Verifying input placeholders ");
        Assertions.assertTrue(getElementInShadowRoot(driver,hostLocator,vornameInputField).getAttribute("placeholder").contains("Vorname"));
        Assertions.assertTrue(getElementInShadowRoot(driver,hostLocator,nachnameInputField).getAttribute("placeholder").contains("Nachname"));
        Assertions.assertTrue(getElementInShadowRoot(driver,hostLocator,passwortInputField).getAttribute("placeholder").contains("Passwort"));
        Assertions.assertTrue(getElementInShadowRoot(driver,hostLocator,passwortConfirmInputField).getAttribute("placeholder").contains("Passwort wiederholen"),
                "Passwort wiederholen placeholder dont match");
        Assertions.assertTrue(getElementInShadowRoot(driver,hostLocator,weiterCssLocatorButton2).isEnabled(),"weiter button is not enabled");
        log.info("Test {} passed","TS-001");

    }
}
