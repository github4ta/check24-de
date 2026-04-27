package de.check24.tests.ui;

import de.check24.tests.ui.base.BaseUITest;
import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.search.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        List<String> descriptions = searchPage.getAllHotelDescriptions();
        int count = descriptions.size();
        System.out.println("Anzahl der gefundenen Hotels: " + count);

        for (String description : descriptions) {
            String cleanDescription = description.replace("\n", " ").replaceAll("\\s+", " ");

            Assertions.assertTrue(
                    cleanDescription.toLowerCase().contains("dachterrasse"),
                    "Keyword 'Dachterrasse' not found! Full text was: " + cleanDescription
            );
        }
    }
}
