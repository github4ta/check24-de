package de.check24.ui.util;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShadowWaits {
    public static WebElement waitForElementInShadow(WebDriver driver, By hostSelector, By elementSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(d -> {
            try {
                WebElement host = d.findElement(hostSelector);
                SearchContext shadowRoot = host.getShadowRoot();
                WebElement element = shadowRoot.findElement(elementSelector);

                return (element.isDisplayed() && element.isEnabled()) ? element : null;
            } catch (Exception e) {
                return null;
            }
        });
    }
}
