package org.company.chelei;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InnowiseMainPage {
    private final WebDriver driver;

    public InnowiseMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSloganText() {
        By slogan = By.xpath("//div[contains(@class, 'heroRightText')]//p");
        return driver.findElement(slogan).getText().strip();
    }
}
