package de.check24.tests.ui;

import de.check24.ui.pages.search.SearchPage;
import org.junit.jupiter.api.BeforeEach;

public class SearchTest extends BaseUITest {
    private SearchPage searchPage;

    @BeforeEach
    void setupHomePage() {
        searchPage = new SearchPage(driver);
        log.info("SearchPage instance created");
    }
}
