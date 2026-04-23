package de.check24.ui.pages.home;

import org.openqa.selenium.By;

public class HomeLocator {
    // сюда помещаем описание веб элементов, например
    static final By LOGIN_CHECK_IN_HEADER = By.xpath("//a[@class='c24-logo']");
    static final By PROFILE_ICON_IN_HEADER = By.xpath("//span[@class='c24-customer-icon c24-customer-icon-li c24-icon']\n");
    static final By LOGOUT_LINK = By.xpath("//a[@title='abmelden' and text()='abmelden']\n");
    static final By COPYRIGHT_FOOTER = By.xpath("//div[@class='c24-footer-company-line']");
    static final By SECTION_TURKEY = By.xpath("//*[@id=\"c24trendingLocations\"]/div/a[2]/div/div[2]/div[1]");
    static final By SEARCH_IN_HEADER = By.xpath("//input[@id='c24-search-header']");
    static final By LABEL_WARSCHAU = By.xpath("//span[text()='warschau']");
    static final By LINK_FERIENWOHNUNG = By.xpath("//div[text()='Ferienwohnung buchen']");
    static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//a[@class='c24-customer-hover-wrapper c24-login-opener']");
    static final By AGB_LINK = By.xpath("//a[@title='AGB']");
    static final By SOCIAL_ICON = By.xpath("//a[@class='c24-footer-icon']");

}
