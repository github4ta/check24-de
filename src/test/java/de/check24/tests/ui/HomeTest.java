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

    @BeforeEach
    void setupHomePage() {
        homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
    }

    //  @Test
    //@Story("Copyright Verification")
    // @DisplayName("Copyright text is present")
    // @Description("Verify that the copyright text '© 2026 CHECK24 Vergleichsportal GmbH München' is displayed")
    // void testCopyrightPresence() {
    // When
    //    homePage.navigateToHomePage();

    // Then
    //    boolean hasExactCopyright = homePage.isCopyrightTextPresent();
    //    boolean hasCopyright2026 = homePage.isCopyright2026Present();

    //   assertThat(hasExactCopyright || hasCopyright2026)
    //           .withFailMessage("Copyright text '© 2026 CHECK24 Vergleichsportal GmbH München' not found on homepage")
    //          .isTrue();

    //  if (hasExactCopyright) {
    //   log.info("Exact copyright text found");
    //  } else if (hasCopyright2026) log.info("Copyright with 2026 and CHECK24 found");
    //   }

    //Additional check - get actual copyright text
    //  String copyrightText = homePage.getCopyrightText(String);
    //  if (!copyrightText.isEmpty()) {
    //     log.info("Found copyright text: {}", GET_COPYRIGHT_TEXT);
    //   }

    @Test
    @Story("Copyright Verification")
    @DisplayName("Verification of copyright text: «© 2026 CHECK24 Vergleichsportal GmbH München»")
    @Description("Verify that the copyright text '© 2026 CHECK24 Vergleichsportal GmbH München' is displayed")
    void testVM001() {
        assertThat(homePage.getCopyrightText())
                .as("Проверка текста копирайта в футере")
                .isNotBlank()
                .contains("2026")
                .contains("CHECK24 Vergleichsportal GmbH München");
    }

    @Test
    @Story("Search Functionality")
    @DisplayName("Search by section is working correctly")
    @Description("Verify that search by section Angesagte Reiseziele functionality provides correct list of hotels")
    public void testSE003() {
        homePage.clickSectionTurkey();

        assertThat(homePage.getUrl())
                .contains("Türkei");
    }

    @Test
    @Description("Verify search functionality using the keyword 'Warsaw'")
    public void testSE001() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickToSearchFieldInHeader();
        homePage.fillInputInSearchHeader("Warsaw");
        homePage.submitSearchByEnter();

        String actualText = homePage.getTextAfterSearchWarsaw();
        Assertions.assertEquals("warschau", actualText);
    }

    @Test
    public void testVS005() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        homePage.clickLinkFerienwohnung();
        homePage.clickLogoCheckInHeader();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.check24.de/";
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testVS003() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        Assertions.assertTrue(homePage.isPersonalAccountButtonDisplayed(), "Button should be visible");
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
    void testVM005() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        assertThat(homePage.isAGBLinkClickable())
                .withFailMessage("AGB link is not clickable")
                .isTrue();
    }

    @Test
    void testVS006() {
        homePage.navigateToHomePage();

        assertThat(homePage.getSocialIconCount())
                .withFailMessage("The number of icons does not meet the requirements")
                .isEqualTo(4);
    }

    @Test
    public void testSR001() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickToSearchFieldInHeaderUsingActions();
        homePage.fillInputInSearchHeaderUsingActions("paris");
        homePage.submitSearchByEnter();
        homePage.clickToSearchParisHotelsButton();
        homePage.clickOnPopupWindowCross();
        homePage.clickOnSortingField();
        homePage.clickOnSortingByPopularityInDescendingOrder();

        assertThat(homePage.checkIfSortingByPopularityInDescendingOrderIsWorking())
                .withFailMessage("Popularity sorting filter is working incorrectly")
                .isTrue();
    }

    @Test
    @DisplayName("SR002-The price sorting function on the Paris hotels list page works correctly.")
    @Description("Verify, that hotels sort by price in ascending order ")
    public void testSR002() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickToSearchFieldInHeaderUsingActions();
        homePage.fillInputInSearchHeaderUsingActions("paris");
        homePage.submitSearchByEnter();
        homePage.clickToSearchParisHotelsButton();
        homePage.clickOnPopupWindowCross();
        homePage.clickOnSortingField();
        homePage.selectSortingByPriceAscending();

        assertThat(homePage.checkIsSortingByPriceAscending())
                .withFailMessage("Price sorting is NOT working correctly (ascending order expected)")
                .isTrue();
    }

    @Test
    public void testVS010() {
        homePage.navigateToHomePage();

        homePage.clickHotelButton();
        String hotelUrl = driver.getCurrentUrl();
        assertTrue(hotelUrl.contains("https://hotel.check24.de/"));
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

    @Description("Successful logout")
    public void testAU002() {
        LoginPage loginPage = new LoginPage(driver);
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
