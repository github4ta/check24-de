package org.company.novysh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InnowiseMainPage {
    @FindBy(xpath = "//div[@class='elementor-widget-container']/p")
    private WebElement sloganText;

    public InnowiseMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSloganText() {
        return sloganText;
    }

}
