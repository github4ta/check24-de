package com.mytheresa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */
public class BaseMytheresaPage {

    private final String basePage = "https://www.mytheresa.com/";

    private final By navButtonsLocator = By.xpath("//span[contains(@class, 'nav__item__link')]");

    public String getBasePage() {
        return basePage;
    }
    public By getNavButtonsLocator() {
        return navButtonsLocator;
    }

    public List<String> getNavButtonLabels(WebDriver driver) {

        List<WebElement> navButtonElementList = driver.findElements(getNavButtonsLocator());
        List<String> navButtonsListText = new ArrayList<>();

        for (WebElement navButtonElement : navButtonElementList) {
            navButtonsListText.add(navButtonElement.getText());
        }
        return navButtonsListText;
    }

}
