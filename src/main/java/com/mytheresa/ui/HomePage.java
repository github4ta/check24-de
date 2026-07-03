package com.mytheresa.ui;

import com.mytheresa.ui.driver.MyDriver;

public class HomePage {
    private final String HOME_PAGE_URL = "https://www.mytheresa.com/";
    private final String COPYRIGHT = "//p[@dir='ltr']";

    public HomePage() {
    }

    public void open() {
        MyDriver.open(HOME_PAGE_URL);
    }

    public String getCopyrightText() {
        return MyDriver.getText(COPYRIGHT);
    }
}
