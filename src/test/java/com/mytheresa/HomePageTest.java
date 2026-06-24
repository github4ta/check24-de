package com.mytheresa;

import com.mytheresa.ui.BaseMytheresaPage;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageTest extends BaseMytheresaPage {

    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";
    private String termsOfUseButton = "//div[@class='footer__columns__column'][3]/*/*[1]";
    private String privacyPolicyButton = "//div[@class='footer__columns__column'][3]/*/*[2]";
    private String imprintButton = "//div[@class='footer__columns__column'][3]/*/*[3]";

    @Test
    @Name("UI-TC-011: Check homepage sections' names: Terms of Use, Privacy Policy, Imprint")
    public void checkSectionsNamesTest() {
        WebDriver driver = initDriver();
        Actions actions = new Actions(driver);
        acceptCookies(driver);

        actions.scrollToElement(driver.findElement(getTermsOfUseButton())).perform();
        Assertions.assertEquals("Terms of Use", driver.findElement(getTermsOfUseButton()).getText());

        actions.scrollToElement(driver.findElement(getPrivacyPolicyButton())).perform();
        Assertions.assertEquals("Privacy Policy", driver.findElement(getPrivacyPolicyButton()).getText());

        actions.scrollToElement(driver.findElement(getImprintButton())).perform();
        Assertions.assertEquals("Imprint", driver.findElement(getImprintButton()).getText());

        driver.quit();
    }

    private void acceptCookies(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(getShadowHostLocator())).getShadowRoot().findElement(getAcceptCookiesButton()).click();
    }

    private WebDriver initDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(getBasePage());
        return driver;
    }

    public By getShadowHostLocator() {
        return By.cssSelector(shadowHostLocator);
    }

    public By getAcceptCookiesButton() {
        return By.cssSelector(acceptCookiesButton);
    }

    public By getTermsOfUseButton(){
        return By.xpath(termsOfUseButton);
    }

    public By getPrivacyPolicyButton(){
        return By.xpath(privacyPolicyButton);
    }

    public By getImprintButton(){
        return By.xpath(imprintButton);
    }
}
