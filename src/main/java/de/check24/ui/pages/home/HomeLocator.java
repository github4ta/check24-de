package de.check24.ui.pages.home;

import org.openqa.selenium.By;

public class HomeLocator {
    // сюда помещаем описание веб элементов, например
    public static final By LOGIN_CHECK_IN_HEADER = By.xpath("//a[@class='c24-logo']");
    static final By PROFILE_ICON_IN_HEADER = By.xpath("//span[@class='c24-customer-icon c24-customer-icon-li c24-icon']\n");
    static final By LOGOUT_LINK = By.xpath("//a[@title='abmelden' and text()='abmelden']\n");
    static final By FACEBOOK_BUTTON = By.xpath("//div[@class='c24-facebook-logo']");
    static final By LOGIN_IСON = By.xpath("//div[@class='c24-customer-icon c24-customer-icon-lo c24-icon']");
    static final By AGB_LINK = By.xpath("//a[@title='AGB']");
    static final By SEARCH_BAR = By.xpath("//input[@id='c24-search-header']");
    static final By SEARCH_HOTEL_INPUT= By.xpath("//input[@id='id-search-form-destination']");

}
