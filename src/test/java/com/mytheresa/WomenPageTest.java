package com.mytheresa;

import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WomenPageTest {
    private final String WOMEN_PAGE_URI = "https://www.mytheresa.com/int/en/women";
    private String shadowHostLocator = "#usercentrics-cmp-ui";
    private String acceptCookiesButton = "#accept";
    private String categories = "//span[@class='nav__item__link__label']";

    @Test
    @Name("UI-TC-020: Verify category titles on the Women's page")
    public void verifyCategoryTitles() {
        WebDriver driver = initDriver();
        acceptCookies(driver);

        List<String> expectedTitles = List.of("new arrivals", "designers", "clothing", "shoes", "bags", "accessories", "jewelry", "vacation shop", "sale");
        List<String> actualTitles = getTitles(categories, driver);

        Assertions.assertEquals(expectedTitles, actualTitles);
    }

    private List<String> getTitles(String categories, WebDriver driver) {
        List<WebElement> listOfElements = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(categories)));
        List<String> listOfTitles = new ArrayList<>();
        for (WebElement element : listOfElements) {
            listOfTitles.add(element.getText().toLowerCase());
        }
        return listOfTitles;
    }

    private WebDriver initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(WOMEN_PAGE_URI);
        return driver;
    }

    private void acceptCookies(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(shadowHostLocator)))
                .getShadowRoot()
                .findElement(By.cssSelector(acceptCookiesButton))
                .click();
    }
}
