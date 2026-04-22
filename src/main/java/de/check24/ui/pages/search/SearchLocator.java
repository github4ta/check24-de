package de.check24.ui.pages.search;

import org.openqa.selenium.By;

public class SearchLocator {
    public static final By COOKIE_ACCEPT_BUTTON = By.xpath("//*[@id='c24-html']/body/div[2]/div[1]/div[3]/a[2]");
    public static final By INPUT_DESTINATION = By.xpath("//*[@id='trsf-destination-input']");
    static final By FAKE_RESULT_HEADLINE = By.xpath("//div[@class='fakeresult_headline']");
}
