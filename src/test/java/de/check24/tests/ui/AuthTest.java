package de.check24.tests.ui;

import de.check24.tests.ui.base.BaseUITest;
import de.check24.ui.pages.auth.AuthPage;
import de.check24.ui.pages.home.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest extends BaseUITest {
    private HomePage homePage;
    private AuthPage authPage;

    @BeforeEach
    public void openSiteAndAcceptCookie() {
        homePage = new HomePage();
        homePage.navigateToHomePage();
        homePage.clickCookieConsentButton();
        authPage = new AuthPage();
    }

    @Test
    @DisplayName("LP102 - The ‘zurück’ link is opened to the previous page.")
    void testLP102() {
        homePage.clickAnmelden();
        authPage.clickZuruck();

        assertThat(
                homePage.getCurrentUrl())
                .isEqualTo("https://www.check24.de/");
    }
}
