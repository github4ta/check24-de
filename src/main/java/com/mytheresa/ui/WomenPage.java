package com.mytheresa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WomenPage  extends AuthPage{
    AuthPage auth = new AuthPage();

    private static final Logger log = LoggerFactory.getLogger(WomenPage.class);

    private final String WOMEN_PAGE_URI = "https://www.mytheresa.com/gb/en/women";
    private final By navButtonsLocator = By.xpath("//span[contains(@class, 'nav__item__link')]");

    private final List<String> EXPECTED_NAV_BUTTON_LABELS = List.of("NEW ARRIVALS", "DESIGNERS",
            "CLOTHING", "SHOES", "BAGS", "ACCESSORIES", "JEWELRY", "VACATION SHOP", "SALE");


    public List<String> getNavButtonLabels(WebDriver driver) {

        List<WebElement> navButtonElementList = driver.findElements(getNavButtonsLocator());
        List<String> navButtonsListText = new ArrayList<>();

        for (WebElement navButtonElement : navButtonElementList) {
            navButtonsListText.add(navButtonElement.getText());
        }
        return navButtonsListText;
    }

    public List<String> getExpectedNavButtonLabels() {
        return EXPECTED_NAV_BUTTON_LABELS;
    }

    public String getWOMEN_PAGE_URI() {
        return WOMEN_PAGE_URI;
    }

    public By getNavButtonsLocator() {
        return navButtonsLocator;
    }

}
