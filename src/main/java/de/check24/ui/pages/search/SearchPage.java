package de.check24.ui.pages.search;

import org.openqa.selenium.WebDriver;
import static de.check24.ui.pages.search.SearchLocator.*;
import static de.check24.ui.pages.search.SearchText.*;

public class SearchPage {
    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFakeResultMessageCorrected() {
        return driver.findElement(FAKE_RESULT_HEADLINE).getText().contains(FAKE_RESULT_TEXT);
    }
}
