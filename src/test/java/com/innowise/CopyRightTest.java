package com.innowise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopyRightTest {
    private String MAIN_PAGE_URL = "https://innowise.com/";
    private String copyRight = "//p[@class='copyright-text']";
    private String copyRightText = "© 2007-2026 Innowise. All Rights Reserved.";

    @Test
    @DisplayName("UI-TC-001: Verify Copyright text at home page")
    public void VerifyCopyrightTextTest() {
        WebDriver driver = initDriver();

        Assertions.assertTrue(driver.findElement(By.xpath(copyRight)).getText().contains(copyRightText));

        tearDown(driver);
    }

    private WebDriver initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(MAIN_PAGE_URL);
        return driver;
    }

    private void tearDown(WebDriver driver) {
        driver.quit();
    }
}
