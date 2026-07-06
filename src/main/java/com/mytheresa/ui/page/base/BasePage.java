package com.mytheresa.ui.page.base;

import com.mytheresa.ui.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */
public abstract class BasePage {
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";

    private final String BASE_URl = "https://www.mytheresa.com/";

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = Driver.getDriver();
    }

    public BasePage(int seconds) {
        this.driver = Driver.getDriver();
        this.wait = Driver.getWait(seconds);
    }

    public void open() {
        driver.get(BASE_URl);
    }

    public void acceptCookies() {
        try {
            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(getShadowHostLocator()))
                    .getShadowRoot()
                    .findElement(getAcceptCookiesButton())
                    .click();
        } catch (Exception e) {
            System.out.println("No cookies popup found");
        }
    }

    public By getShadowHostLocator() {
        return By.cssSelector(shadowHostLocator);
    }

    public By getAcceptCookiesButton() {
        return By.cssSelector(acceptCookiesButton);
    }
}
