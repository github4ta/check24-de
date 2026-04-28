package de.check24.tests.ui;

import de.check24.tests.ui.base.BaseUITest;
import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.search.SearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    public void searchTest() {
        searchPage.setDestinationInput("Lara");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();

        System.out.println("Anzahl der Container: " + searchPage.getContainers());
    }

    @Test
    @DisplayName("SP115-Search city:'Bremen',filter Intelligente:'Dachterrasse'")
    public void testSP115() {
        searchPage.setDestinationInput("Bremen");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();
        searchPage.fillIntelligentFilter("Dachterrasse");
        List<String> descriptions = searchPage.getAllHotelDescriptions("Dachterrasse");

        assertThat(descriptions)
                .as("Hotel descriptions list")
                .isNotEmpty()
                .allSatisfy(description -> {
                    String cleanDescription = description.replace("\n", " ").replaceAll("\\s+", " ");

                    assertThat(cleanDescription.toLowerCase())
                            .as("Checking keyword in hotel: " + cleanDescription)
                            .contains("dachterrasse");
                });
    }

    @Test
    @DisplayName("SP112 - The search results include 'motel'")
    public void testSP112() {
        searchPage.setDestinationInput("Hamburg");
        searchPage.clickFirstDestinationSuggestionItem();
        searchPage.clickDateRangePickerInput();
        searchPage.clickDataTodayButton();
        searchPage.clickSuchenSubmitButton();

        // задать (чек) для фильта значение (опцию)
        // searchPage.setFilterOption("Unterkunftstyp", "Motel");
        searchPage.setFilterOptions("Unterkunftstyp", "Motel");
        assertThat(
                searchPage.isHotelNamesContain("Motel"))
                .isTrue();

    }
}
