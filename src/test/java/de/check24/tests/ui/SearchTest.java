package de.check24.tests.ui;

import de.check24.tests.ui.base.BaseUITest;
import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.search.SearchPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends BaseUITest {
    private SearchPage searchPage;

    @BeforeEach
    public void beforeEach() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        homePage.clickCookieConsentButton();
        homePage.clickQuickChipsLinkHotel();

        searchPage = new SearchPage();
        searchPage.clickSplashScreenButtonClose();
    }

    @Test
    @DisplayName("SP115-Search city:'Bremen',filter Intelligente:'Dachterrasse'")
    public void testSP115() {
        searchPage.setDestinationInput("Bremen");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();

        searchPage.setIntelligentFilter("Dachterrasse");

        assertThat(searchPage.isHotelDescriptionsContain("Dachterrasse")).isTrue();
    }

    @Test
    @DisplayName("SP112 - The search results include 'motel'")
    public void testSP112() {
        searchPage.setDestinationInput("Hamburg");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();

        searchPage.setFilterOptionWithMoreLink("Unterkunftstyp", "Motel");

        assertThat(searchPage.isHotelNamesContain("Motel")).isTrue();
    }

    @Disabled
    @Test
    @DisplayName("SP113 - 'Entfernung Zentrum' Filter")
    public void testSP113() {
        searchPage.setDestinationInput("Köln");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();

        searchPage.selectOptionMax5km();

        final int DISTANCE_5_KM = 5_000;
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(searchPage.getHotelResultLocations()).allMatch(location -> location.contains("Köln"));
        softAssertions.assertThat(searchPage.getResultsListDistance()).allMatch(distance -> distance <= DISTANCE_5_KM);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("SP111-Search city: 'Berlin', filter Ihr Budget (pro Nacht)")
    void testSP111() {
        searchPage.setDestinationInput("Berlin");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();
        searchPage.scrollIhrBudgetSliderToCenter();
        searchPage.scrollScreen();

        int minRangePrice = searchPage.getMinRangePrice();
        int maxRangePrice = searchPage.getMaxRangePrice();
        assertThat(searchPage.getPrices()).allMatch(price -> (price >= minRangePrice && price <= maxRangePrice));
    }

    @Test
    @DisplayName("SP116 - The 'Gästebewertung' filter is working correctly")
    public void testSP116() {
        searchPage.setDestinationInput("Mallorca");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();
        searchPage.setFilterOption("Gästebewertung", "Hervorragend: 9+");

        assertThat(searchPage.isHotelRatingMoreThan(9)).isTrue();
    }
}
