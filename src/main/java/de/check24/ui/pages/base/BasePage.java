package de.check24.ui.pages.base;

import de.check24.ui.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.check24.ui.driver.Driver.*;

public class BasePage {
    protected static final Logger log = LoggerFactory.getLogger(BasePage.class);
    protected final String BASE_URL = "https://www.check24.de/";
    private final String COOKIE_CONSENT_BUTTON = "//div[contains(@class, 'c24-cookie-consent-notice-buttons')]//a[@class='c24-cookie-consent-button']";
    private final String LABEL_ANMELDEN = "//a[contains(@class, 'c24-customer-hover-wrapper') and contains(@class, 'c24-login-opener')]";

    public void navigateToHomePage() {
        navigateTo(BASE_URL);
    }

    public void clickCookieConsentButton() {
        click(COOKIE_CONSENT_BUTTON);
    }

    public void clickAnmelden() {
        click(LABEL_ANMELDEN);
    }

    public String getCurrentUrl() {
        return getUrl();
    }
}
