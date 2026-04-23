package de.check24.tests.ui;

import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.login.LoginPage;
import de.check24.ui.pages.login.LoginText;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Comprehensive tests for Check24 HomePage functionality
 */
@Epic("Check24 Homepage")
@Feature("Homepage Verification")
public class HomeTest extends BaseUITest {
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    void setupHomePage() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
    }

    @Test
    @DisplayName("VM001-Verification of copyright text: «© 2026 CHECK24 Vergleichsportal GmbH München»")
    void testVM001() {
        assertThat(homePage.isCopyrightFooterTextCorrect())
                .withFailMessage("Copyright footer text is incorrect")
                .isTrue();
    }

    @Test
    @DisplayName("SE003 - Search by section is working correctly")
    public void testSE003() {
        homePage.clickSectionTurkey();

        assertThat(homePage.getUrl())
                .withFailMessage("Search by section does not working correctly")
                .contains("Türkei");
    }

    @Test
    @DisplayName("SE001 - Search bar works correctly")
    public void testSE001() {
        homePage.clickToSearchFieldInHeader();
        homePage.fillInputInSearchHeader("Warsaw");
        homePage.submitSearchByEnter();

        assertThat(homePage.getTextAfterSearchWarsaw())
                .withFailMessage("Search text is not found")
                .contains("warschau");
    }

    @Test
    @DisplayName("VS005 - The logo links to the homepage")
    public void testVS005() {
        homePage.clickLinkFerienwohnung();
        homePage.clickLogoCheckInHeader();

        assertThat(homePage.isHomepageUrl())
                .withFailMessage("Logo does not link to the homepage")
                .isTrue();
    }

    @Test
    @DisplayName("VS003 - Personal account button «Anmelden» is visible")
    public void testVS003() {
        assertThat(homePage.isPersonalAccountButtonDisplayed())
                .withFailMessage("Personal account button is not visible")
                .isTrue();
    }

    @Test
    public void testVS004() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickFacebookButton();
        String actual = homePage.getFacebookPageUrl();
        Assertions.assertEquals("https://www.facebook.com/CHECK24de/?locale=de_DE", actual);
    }

    @Test
    public void testAU003() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickLoginIcon();
        homePage.enterEmail();
        homePage.clickForgotPassword();

        Assertions.assertTrue(driver.getCurrentUrl().contains("passwort-vergessen"),
                "URL не содержит 'passwort-vergessen'. Текущий URL: " + driver.getCurrentUrl());
    }

    @Test
    public void testVM004() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickAGBlink();
        String AGBUrl = driver.getCurrentUrl();
        assertTrue(AGBUrl.contains("https://hotel.check24.de/agb"));
    }

    @Test
    void testVS007() {
        final String expectedPlaceholder = "Wohin?";

        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        assertThat(homePage.getSearchHotelInputPlaceholder())
                .withFailMessage("The placeholder does not match " + expectedPlaceholder)
                .isEqualTo(expectedPlaceholder);
    }

    @Test
    public void testSE002() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickSearchBar();
        homePage.sendKeysSearchBar();

        String actual = homePage.getParisHotelCurrentUrl();
        Assertions.assertTrue(
                actual.contains("https://www.check24.de/suche/?q=paris"),
                "Unexpected URL: " + actual);
    }

    @Test
    @DisplayName("VM005 - The link to «AGB» clickable")
    void testVM005() {
        assertThat(homePage.isAGBLinkClickable())
                .withFailMessage("AGB link is not clickable")
                .isTrue();
    }

    @Test
    @DisplayName("VS006 - Verify Social Media links visibility and functionality (Facebook, Instagram, YouTube, TikTok)")
    void testVS006() {
        assertThat(homePage.getSocialIconCount())
                .withFailMessage("The number of icons does not meet the requirements")
                .isEqualTo(4);
    }

    @Test
    @DisplayName("SR001-Sorting function based on stars amount in descending order is working correctly")
    public void testSR001() {
        homePage.clickToSearchFieldInHeaderUsingActions();
        homePage.fillInputInSearchHeaderUsingActions("paris");
        homePage.submitSearchByEnter();
        homePage.clickToSearchParisHotelsButton();
        homePage.clickOnPopupWindowCross();
        homePage.clickOnSortingField();
        homePage.clickOnSortingByPopularityInDescendingOrder();

        assertThat(homePage.isSortingByPopularityInDescendingOrderFilterWorking())
                .withFailMessage("Popularity sorting filter is working incorrectly")
                .isTrue();
    }

    @Test
    @DisplayName("SR002-The price sorting function on the Paris hotels list page works correctly.")
    @Description("Verify, that hotels sort by price in ascending order ")
    public void testSR002() {
        homePage.clickToSearchFieldInHeaderUsingActions();
        homePage.fillInputInSearchHeaderUsingActions("paris");
        homePage.submitSearchByEnter();
        homePage.clickToSearchParisHotelsButton();
        homePage.clickOnPopupWindowCross();
        homePage.clickOnSortingField();
        homePage.selectSortingByPriceAscending();

        assertThat(homePage.isSortingByPriceAscendingFilterWorking())
                .withFailMessage("Price sorting is NOT working correctly (ascending order expected)")
                .isTrue();
    }

    @Test
    @DisplayName("VS010 -Validation of Hotel button functionality")
    public void testVS010() {
        homePage.clickHotelButton();

        assertThat(homePage.isHotelPageUrl())
                .withFailMessage("Hotel button does not link to the hotelpage")
                .isTrue();
    }

    @Test
    public void testVS011() {
        homePage.navigateToHomePage();
        homePage.clickReiseButton();
        String reiseUrl = driver.getCurrentUrl();
        assertTrue(reiseUrl.contains("https://urlaub.check24.de/"));
    }

    @Test
    public void testVM007() {
        homePage.navigateToHomePage();
        homePage.clickReiseButton();
        homePage.openBeliebteSportsSection();
        homePage.clickFunchalSpot();

        String pageTitle = driver.getTitle();
        assertThat(pageTitle)
                .as("Заголовок страницы должен содержать упоминание Мадейры или Фуншала")
                .containsIgnoringCase("Madeira");

        assertThat(driver.getCurrentUrl())
                .contains("madeira");
    }

    @Test
    public void testVM003() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickDatenschutzLink();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl)
                .as("URL после клика на Datenschutz должен быть корректным")
                .contains("datenschutz");
    }

    @Test
    public void testVM002() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickImpressumLink();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl)
                .as("URL после клика на Impressum должен быть корректным")
                .contains("impressum");
    }

    @Test
    public void testVM006() {
        final String expectedTitle = "Wanderorte für deine nächste Sportreise";

        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickWander();

        assertThat(homePage.getTitleWander())
                .withFailMessage("Wander is not clickable")
                .isEqualTo(expectedTitle);
    }

    @Test
    void testVS013() {
        final String expectedTitle = "Hotel buchen bei\nDeutschlands größtem Reiseportal";

        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickSearchBar();
        homePage.sendKeysSearchBar();
        homePage.clickSearchButton();
        System.out.println("title: " + homePage.getTitleParisHotels());

        assertThat(homePage.getTitleParisHotels())
                .withFailMessage("The title does not match " + expectedTitle)
                .isEqualTo(expectedTitle);
    }

    @Test
    void testVS014() {
        final String expectedUrl = "https://accounts.check24.com/login";

        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickLoginIcon();

        assertThat(homePage.getUrlAuthorisationPage())
                .withFailMessage("The url does not contains " + expectedUrl)
                .contains(expectedUrl);
    }

    @Test
    public void testFL002() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickToSearchFieldInHeaderUsingActions();
        homePage.fillInputInSearchHeaderUsingActions("paris");
        homePage.submitSearchByEnter();
        homePage.clickToSearchParisHotelsButton();
        homePage.clickOnPopupWindowCross();

        // toggle.click toggle.click();
        // assert(status isChanged)

        assertThat(homePage.isEntfernungFestlegenToggled())
                .withFailMessage("Toggle is not clickable")
                .isTrue();
    }

    @Test
    @DisplayName("Verify 'Suchen' button visibility and functionality")
    public void testVS012() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickSearchBar();
        homePage.sendKeysSearchBar();
        homePage.clickSearchButton();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl)
                .withFailMessage("Search did not redirect to results page. Current URL:" + currentUrl)
                .containsIgnoringCase("paris");
        log.info("Search button works correctly. Redirected to: {}", currentUrl);
    }

    @Test
    @Description("Successful logout")
    @Test
    public void testAU002() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickLoginIcon();
        loginPage.enterEmailLoginWithParameters(LoginText.EMAIL_FOR_LOGIN);
        loginPage.clickEmailButton();
        loginPage.enterPasswordWithParameters(LoginText.PASSWORD_FOR_LOGIN);
        loginPage.clickPasswordButton();
        homePage.clickProfileIconInHeader();
        homePage.clickLogoutLink();

        assertThat(homePage.isCurrentPageContainLinkToLoginPage())
                .isTrue();
    }

    @Test
    public void testSE004() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.сlickSectionPopularDestinationsForVacationRentals();

        assertThat(homePage.getUrl())
                .contains("Istrien");
    }
}
