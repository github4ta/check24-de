package com.mytheresa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MenPage extends AuthPage {

    private final String MEN_PAGE_URI = "https://www.mytheresa.com/gb/en/men";
    private final By navButtonsLocator = By.xpath("//span[contains(@class, 'nav__item__link')]");

    private final List<String> EXPECTED_NAV_BUTTON_LABELS = List.of("NEW ARRIVALS", "DESIGNERS",
            "CLOTHING", "SHOES", "BAGS", "ACCESSORIES", "OCCASIONS", "VACATION SHOP", "SALE");

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

    public String getMEN_PAGE_URI() {
        return MEN_PAGE_URI;
    }

    public By getNavButtonsLocator() {
        return navButtonsLocator;
    }

}
