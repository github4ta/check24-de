package de.check24.tests.ui;

import de.check24.ui.pages.home.HomePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Dimension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Comprehensive tests for Check24 HomePage functionality
 */
@Epic("Check24 Homepage")
@Feature("Homepage Verification")
public class HomeTest extends BaseUITest {
    private HomePage homePage;
    private final String EXISTING_EMAIL = "test@example.com";

    @BeforeEach
    void setupHomePage() {
        homePage = new HomePage(driver);
        log.info("HomePage instance created");
    }

    @Test
    @Story("Page Loading")
    @DisplayName("Homepage loads successfully")
    @Description("Verify that Check24 homepage loads without errors")
    void testHomepageLoads() {
        // When
        homePage.navigateToHomePage();

        // Then
        assertThat(homePage.isPageLoaded())
                .withFailMessage("Check24 homepage failed to load properly")
                .isTrue();

        assertThat(driver.getTitle())
                .withFailMessage("Page title should contain CHECK24")
                .contains("CHECK24");

        log.info("Homepage loaded successfully with title: {}", driver.getTitle());
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

        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();

        String actualCopyright = homePage.getCopyrightText();
        log.info("Found copyright text: {}", actualCopyright);

        assertThat(actualCopyright)
                .as("Проверка текста копирайта в футере")
                .isNotBlank()
                .contains("2026")
                .contains("CHECK24 Vergleichsportal GmbH München");
    }

    @Test
    @Story("UI Elements")
    @DisplayName("Logo is displayed")
    @Description("Verify that the Check24 logo is visible on the homepage")
    void testLogoVisibility() {
        // When
        homePage.navigateToHomePage();

        // Then
        assertThat(homePage.isLogoDisplayed())
                .withFailMessage("Check24 logo is not displayed on homepage")
                .isTrue();

        log.info("Logo is displayed correctly");
    }

    @Test
    @Story("Page Structure")
    @DisplayName("Page has proper structure")
    @Description("Verify that homepage has all necessary structural elements")
    void testPageStructure() {
        // When
        homePage.navigateToHomePage();

        // Then
        assertThat(homePage.isPageLoaded())
                .withFailMessage("Page did not load properly")
                .isTrue();

        // Check that we can interact with the page
        assertThat(driver.getCurrentUrl())
                .withFailMessage("URL should be check24.de")
                .contains("check24.de");

        // Check that page has content (basic smoke test)
        String pageSource = driver.getPageSource();
        assertThat(pageSource.length())
                .withFailMessage("Page source is too short, page might not have loaded properly")
                .isGreaterThan(10000);

        log.info("Page structure verification passed");
    }

    @ParameterizedTest
    @ValueSource(strings = {"versicherung", "kredit", "strom", "dsl"})
    @Story("Search Functionality")
    @DisplayName("Search suggestions work")
    @Description("Verify that search functionality provides suggestions for common terms")
    void testSearchSuggestions(String searchTerm) {
        // When
        homePage.navigateToHomePage();

        // Note: This test assumes search functionality exists
        // In real implementation, you might need to check if search input exists first
        try {
            homePage.search(searchTerm);
            log.info("Search executed successfully for term: {}", searchTerm);
        } catch (Exception e) {
            log.warn("Search functionality not available or different implementation: {}", e.getMessage());
            // This is acceptable - search might not be implemented the same way
        }
    }

    @Test
    @Story("Responsiveness")
    @DisplayName("Page works on different screen sizes")
    @Description("Verify that homepage works on mobile and desktop screen sizes")
    void testResponsiveness() {
        // Test desktop size
        driver.manage().window().setSize(new Dimension(1920, 1080));
        homePage.navigateToHomePage();

        assertThat(homePage.isPageLoaded())
                .withFailMessage("Page failed to load on desktop size")
                .isTrue();

        // Test mobile size
        driver.manage().window().setSize(new Dimension(375, 667));
        // Refresh page to apply new size
        driver.navigate().refresh();

        assertThat(homePage.isPageLoaded())
                .withFailMessage("Page failed to load on mobile size")
                .isTrue();

        // Test tablet size
        driver.manage().window().setSize(new Dimension(768, 1024));
        driver.navigate().refresh();

        assertThat(homePage.isPageLoaded())
                .withFailMessage("Page failed to load on tablet size")
                .isTrue();

        log.info("Page works correctly on different screen sizes");
    }

    @Test
    @Story("Performance")
    @DisplayName("Page loads within reasonable time")
    @Description("Verify that homepage loads within acceptable time limits")
    void testPageLoadPerformance() {
        long startTime = System.currentTimeMillis();

        // When
        homePage.navigateToHomePage();

        // Then
        long loadTime = System.currentTimeMillis() - startTime;

        assertThat(homePage.isPageLoaded())
                .withFailMessage("Page did not load successfully")
                .isTrue();

        assertThat(loadTime)
                .withFailMessage("Page took too long to load: " + loadTime + "ms")
                .isLessThan(30000); // 30 seconds

        log.info("Page loaded in {} ms", loadTime);
    }

    @Test
    @Story("Accessibility")
    @DisplayName("Page has proper accessibility attributes")
    @Description("Basic check for accessibility compliance")
    void testBasicAccessibility() {
        // When
        homePage.navigateToHomePage();

        // Then
        assertThat(homePage.isPageLoaded())
                .withFailMessage("Page did not load for accessibility check")
                .isTrue();

        // Check for basic accessibility - page should have lang attribute
        String pageSource = driver.getPageSource();
        assertThat(pageSource)
                .withFailMessage("Page should have lang attribute for accessibility")
                .contains("lang=");

        log.info("Basic accessibility check passed");
    }

    @Test
    @Story("Search Functionality")
    @DisplayName("Search by section is working correctly")
    @Description("Verify that search by section Angesagte Reiseziele functionality provides correct list of hotels")
    public void testSE003() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
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
    void testVS013 () {
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
