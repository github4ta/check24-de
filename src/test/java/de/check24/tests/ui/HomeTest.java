package de.check24.tests.ui;

import de.check24.tests.ui.base.BaseUITest;
import de.check24.ui.pages.home.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static de.check24.ui.pages.home.HomePage.*;
import static org.assertj.core.api.Assertions.*;

public class HomeTest extends BaseUITest {
    private HomePage homePage;

    @BeforeEach
    public void openSiteAndAcceptCookie() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        homePage.clickCookieConsentButton();
    }

    @Test
    @DisplayName("Homepage loads successfully")
    void testHomepageOpen() {
        assertThat(
                homePage.getFooterCompanyLine())
                .isEqualTo("© 2026 CHECK24 Vergleichsportal GmbH München");
    }

    @Test
    @DisplayName("Homepage loads successfully")
    void testHomepageOpen2() {
        assertThat(
                homePage.getFooterCompanyLine())
                .isEqualTo("© 2026 CHECK24 Vergleichsportal GmbH München");
    }

    @Test
    @DisplayName("HP102, HP104, HP105 - Labels 'Aktivitäten', 'Chat', 'Anmelden' are displayed")
    void testHP102_HP104_HP105() {
        assertThat(homePage.isLabelAktivitätenDisplayed())
                .as("Aktivitäten label should be displayed").isTrue();
        assertThat(homePage.isLabelAnmeldenDisplayed())
                .as("Anmelden label should be displayed").isTrue();
        assertThat(homePage.isLabelChatDisplayed())
                .as("Chat label should be displayed").isTrue();
    }

    @Test
    @DisplayName("HP101 - Placeholder in search input is displayed")
    void testHP101() {
        assertThat(
                homePage.isPlaceholderValueEqualsToExpected())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {LABEL_AMNELDEN, LABEL_CHAT, LABEL_NOTIFICATION, LABEL_AKTIVITATEN})
    @DisplayName("PR102 - header icons are visible")
    void testPR102(String locator) {
        assertThat(
                homePage.isVisibleForParamTest(locator))
                .withFailMessage("Element " + "\"" + homePage.getHeaderIconText(locator) + "\"" + " is not visible")
                .isTrue();
    }
}