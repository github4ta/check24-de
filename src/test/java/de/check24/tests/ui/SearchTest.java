package de.check24.tests.ui;

import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.search.SearchPage;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

public class SearchTest extends BaseUITest {
    private SearchPage searchPage;
    private HomePage homePage;

    @BeforeEach
    void setupHomePage() {
        searchPage = new SearchPage(driver);
        log.info("SearchPage instance created");

        homePage = new HomePage(driver);
        log.info("SearchPage instance created");
    }

    @DisplayName("SE007 - Negative text search.")
    @Test
    void testSE007() {
        String searchNegativeText = "sadfghjkqwerytuizxcvbm,qweryuiosadfhlj";

        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.search(searchNegativeText);

        assertThat(searchPage.isFakeResultMessageCorrected())
                .withFailMessage("Fake result message is not correct.")
                .isTrue();
    }

    @AfterEach
    void cleanup() {
        // Reset window size for next test
        try {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } catch (Exception e) {
            log.warn("Could not reset window size: {}", e.getMessage());
        }
    }
}
