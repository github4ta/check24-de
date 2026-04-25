package de.check24.ui.pages.home;

import de.check24.ui.driver.Driver;
import de.check24.ui.pages.base.BasePage;

import static de.check24.ui.driver.Driver.*;
import static de.check24.ui.pages.home.HomeText.*;


public class HomePage extends BasePage {
    private final String FOOTER_COMPANY_LINE = "//div[@class='c24-footer-company-line']";
    private final String LABEL_AMNELDEN = "//div[@class='c24-customer-icon c24-customer-icon-lo c24-icon']";
    private final String LABEL_AKTIVITATEN = "//a[@class='c24-activities-icon c24-header-hover']";
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

    public void clickQuickChipsLinkHotel() {
        Driver.click(QUICK_CHIPS_LINK_HOTEL);
    }

    public boolean isPlaceholderValueEqualsToExpected() {
        return isAttributeValueEqualsToExpected(SEARCH_HEADER,"placeholder",SEARCH_HEADER_PLACEHOLDER_EXPECTED_TEXT);
    }
}
