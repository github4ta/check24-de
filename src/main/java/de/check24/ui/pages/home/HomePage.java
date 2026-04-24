package de.check24.ui.pages.home;

import de.check24.ui.pages.base.BasePage;

import static de.check24.ui.driver.Driver.getText;
import static de.check24.ui.driver.Driver.isElementDisplayedWithWait;


public class HomePage extends BasePage {
    private final String FOOTER_COMPANY_LINE = "//div[@class='c24-footer-company-line']";
    private final String LABEL_AMNELDEN = "//div[@class='c24-customer-icon c24-customer-icon-lo c24-icon']";
    private final String LABEL_AKTIVITATEN = "//a[@class='c24-activities-icon c24-header-hover']";

    public String getFooterCompanyLine() {
        return getText(FOOTER_COMPANY_LINE);
    }

    public boolean isLabelAnmeldenDisplayed() {
        return isElementDisplayedWithWait(LABEL_AMNELDEN, 5);
    }

    public boolean isLabelAktivitätenDisplayed() {
        return isElementDisplayedWithWait(LABEL_AKTIVITATEN, 5);
    }
}
