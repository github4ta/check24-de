package com.mytheresa;

import com.mytheresa.ui.WomenPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */

public class WomenPageTest {
    private WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(WomenPage.class);
    WomenPage menPage = new WomenPage();

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(menPage.getWomenPageUri());
        Thread.sleep(500);
        menPage.acceptCookies(driver);
        menPage.passUserPreferences(driver);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("UI-TC-020: Verify Navigation button existence and text consistence in Women page")
    public void navButtonTextContainTextTest() {

        log.info("real     buttons text is {}", menPage.getNavButtonLabels(driver));
        log.info("expected buttons text is {}", menPage.getExpectedNavButtonLabels());

        Assertions.assertAll(
                () -> Assertions.assertEquals(menPage.getNavButtonLabels(driver).size(), menPage.getExpectedNavButtonLabels().size()),
                () -> Assertions.assertTrue(menPage.getNavButtonLabels(driver).containsAll(menPage.getExpectedNavButtonLabels())),
                () -> Assertions.assertTrue(menPage.getExpectedNavButtonLabels().containsAll(menPage.getNavButtonLabels(driver)))
        );
    }
}
