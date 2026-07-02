package com.mytheresa.ui;

import java.util.List;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */

public class MenPage extends AuthPage {

    private final String MEN_PAGE_URI = "https://www.mytheresa.com/gb/en/men";

    private final List<String> EXPECTED_NAV_BUTTON_LABELS = List.of("NEW ARRIVALS", "DESIGNERS",
            "CLOTHING", "SHOES", "BAGS", "ACCESSORIES", "OCCASIONS", "VACATION SHOP", "SALE");

    public List<String> getExpectedNavButtonLabels() {
        return EXPECTED_NAV_BUTTON_LABELS;
    }

    public String getMEN_PAGE_URI() {
        return MEN_PAGE_URI;
    }
}
