package de.check24.ui.pages.home;

import de.check24.ui.driver.Driver;
import de.check24.ui.pages.base.BasePage;

import static de.check24.ui.driver.Driver.getText;
import static de.check24.ui.driver.Driver.isElementDisplayedWithWait;

public class HomePage extends BasePage {
    private final String FOOTER_COMPANY_LINE = "//div[@class='c24-footer-company-line']";
    public static final String LABEL_AMNELDEN = "//div[@class='c24-customer c24-customer-guest c24-header-hover c24-header-icon']";
    public static final String LABEL_AKTIVITATEN = "//a[@class='c24-activities-icon c24-header-hover']";
    public static final String LABEL_CHAT = "//a[@class='c24-contact-content clearfix']";
    public static final String LABEL_NOTIFICATION = "//div[@data-layer-class='c24-notification-layer-hover']";
    private final String QUICK_CHIPS_LINK_HOTEL = "//a[@data-identifier='hotel']";
    private final String SEARCH_HEADER = "//input[@id='c24-search-header']";

    public String getFooterCompanyLine() {
        return getText(FOOTER_COMPANY_LINE);
    }

    public boolean isLabelAnmeldenDisplayed() {
        return isElementDisplayedWithWait(LABEL_AMNELDEN, 5);
    }

    public boolean isLabelAktivitätenDisplayed() {
        return isElementDisplayedWithWait(LABEL_AKTIVITATEN, 5);
    }

    public boolean isLabelChatDisplayed() {
        return isElementDisplayedWithWait(LABEL_CHAT, 5);
    }

    public void clickQuickChipsLinkHotel() {
        Driver.click(QUICK_CHIPS_LINK_HOTEL);
    }

    public String getTextPlaceholderForSearchHeader() {
        return Driver.getElementAttribute(SEARCH_HEADER, "placeholder");
    }

    public String getHeaderIconText(String locator) {
        return getText(locator);
    }

    public boolean isVisibleForParamTest(String locator) {
        return isElementDisplayedWithWait(locator, 5);
    }
}
