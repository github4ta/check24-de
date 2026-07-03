package com.mytheresa.ui;

import com.mytheresa.ui.base.BaseTest;
import com.mytheresa.ui.page.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {

    @Test
    public void testCopyright() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.acceptCookies();

        Assertions.assertEquals("copyright © 2006-2026 mytheresa.com", homePage.getCopyrightText());
    }
}
