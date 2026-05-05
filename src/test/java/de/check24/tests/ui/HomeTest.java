package de.check24.tests.ui;

import de.check24.tests.ui.base.BaseUITest;
import de.check24.ui.pages.home.HomePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static de.check24.ui.pages.home.HomePage.LABEL_AMNELDEN;
import static de.check24.ui.pages.home.HomePage.LABEL_CHAT;
import static de.check24.ui.pages.home.HomePage.LABEL_NOTIFICATION;
import static de.check24.ui.pages.home.HomePage.LABEL_AKTIVITATEN;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("HP102, HP104, HP105 - Labels 'Aktivitäten', 'Chat', 'Anmelden' are displayed")
    void testHP102_HP104_HP105() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(
                        homePage.isLabelAktivitätenDisplayed())
                .as("Aktivitäten label should be displayed")
                .isTrue();
        softAssertions.assertThat(
                        homePage.isLabelAnmeldenDisplayed())
                .as("Anmelden label should be displayed")
                .isTrue();
        softAssertions.assertThat(
                        homePage.isLabelChatDisplayed())
                .as("Chat label should be displayed")
                .isTrue();
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("HP101 - Placeholder in search input is displayed")
    void testHP101() {
        assertThat(
                homePage.getTextPlaceholderForSearchHeader())
                .isEqualTo("Suchen oder fragen");
    }

    @ParameterizedTest
    @ValueSource(strings = {LABEL_AMNELDEN, LABEL_CHAT, LABEL_NOTIFICATION, LABEL_AKTIVITATEN})
    @DisplayName("PR102 - header icons are visible")
    void testPR102(String locator) {
        assertThat(
                homePage.isVisibleForParamTest(locator))
                .withFailMessage("Element '%S' is not visible", homePage.getHeaderIconText(locator))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "Activities, //div[@data-layer-class='c24-activities-layer-hover']",
            "Notification, //div[@data-layer-class='c24-notification-layer-hover']",
            "Chat, //div[@class='c24-contact c24-header-hover c24-header-icon clearfix']",
            "Customer, //div[@class='c24-customer c24-customer-guest c24-header-hover c24-header-icon']"})
    @DisplayName("PR101 - The header icons are visible")
    void testPR101(String element, String locator) {
        assertThat(
                homePage.isVisibleForParamTest(locator))
                .withFailMessage("Element '%s' is not visible", element)
                .isTrue();
    }
}
