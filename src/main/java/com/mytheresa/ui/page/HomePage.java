package com.mytheresa.ui.page;

import com.mytheresa.ui.page.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String COPYRIGHT = "//p[@dir='ltr']";
    private final String ICON_USER = "//div[@class='headerdesktop']//a[@href='/euro/en/account/overview']";

    public HomePage() {
        super();
    }

    public String getCopyrightText() {
        return driver.findElement(By.xpath(COPYRIGHT)).getText();
    }

    public void clickIconUser() {
        driver.findElement(By.xpath(ICON_USER)).click();
    }
}
