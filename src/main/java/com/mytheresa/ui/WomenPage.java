package com.mytheresa.ui;

import java.util.List;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */

public class WomenPage  extends AuthPage{

    private final String WOMEN_PAGE_URI = "https://www.mytheresa.com/gb/en/women";

    private final List<String> EXPECTED_NAV_BUTTON_LABELS = List.of("NEW ARRIVALS", "DESIGNERS",
            "CLOTHING", "SHOES", "BAGS", "ACCESSORIES", "JEWELRY", "VACATION SHOP", "SALE");

    public List<String> getExpectedNavButtonLabels() {
        return EXPECTED_NAV_BUTTON_LABELS;
    }

    public String getWomenPageUri() {
        return WOMEN_PAGE_URI;
    }
}
