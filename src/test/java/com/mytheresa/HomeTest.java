package com.mytheresa;

import com.mytheresa.ui.HomePage;
import com.mytheresa.ui.driver.MyDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomeTest {

    @Test
    public void testHomePage() {
        HomePage homePage = new HomePage();
        homePage.open();

        Assertions.assertEquals("copyright © 2006-2026 mytheresa.com", homePage.getCopyrightText());
    }

    @AfterEach
    public void tearDown() {
        MyDriver.quitDriver();
    }
}
