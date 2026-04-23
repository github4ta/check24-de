package de.check24.ui.pages.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.check24.ui.driver.Driver.click;
import static de.check24.ui.driver.Driver.navigateTo;

public class BasePage {
    protected static final Logger log = LoggerFactory.getLogger(BasePage.class);

    protected final String BASE_URL = "https://www.check24.de/";

    private final String COOKIE_CONSENT_BUTTON = "//div[contains(@class, 'c24-cookie-consent-notice-buttons')]//a[@class='c24-cookie-consent-button']";

    public void navigateToHomePage() {
        navigateTo(BASE_URL);
    }

    public void clickCookieConsentButton() {
        click(COOKIE_CONSENT_BUTTON);
    }
}
