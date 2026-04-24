package de.check24.ui.pages.search;

import de.check24.ui.driver.Driver;
import de.check24.ui.pages.base.BasePage;

public class SearchPage extends BasePage {
    private final String SPLASH_SCREEN_BUTTON_CLOSE = "//div[@id='splashScreenContainer']//div[contains(@class, 'close')]";
    private final String DESTINATION_INPUT = "//input[@data-test-id-qa='destination-suggestion-input']";
    private final String DESTINATION_SUGGESTION_ITEM = "//div[@data-test-id-qa='destination-suggestion']";
    private final String DATA_RANGE_PICKER_INPUT = "//div[@data-test-id-qa='date-range-picker-input']";
    private final String DATA_TODAY_BUTTON = "//button[contains(@aria-label, 'Today')]";
    private final String SUCHEN_SUBMIT_BUTTON = "//button[@data-test-id-qa='submit']";
    private final String RESULT_LIST_CONTENT_CONTAINER = "//div[@data-test-id-qa='results-list-content-container']/div";

    public void clickSplashScreenButtonClose() {
        Driver.click(SPLASH_SCREEN_BUTTON_CLOSE);
    }

    public void setDestinationInput(String destination) {
        Driver.fill(DESTINATION_INPUT, destination);
    }

    public void clickDateRangePickerInput() {
        Driver.click(DATA_RANGE_PICKER_INPUT);
    }

    public void clickFirstDestinationSuggestionItem() {
        Driver.waitAndClick(DESTINATION_SUGGESTION_ITEM);
    }

    public void clickDataTodayButton() {
        Driver.waitAndClick(DATA_TODAY_BUTTON);
    }

    public void clickSuchenSubmitButton() {
        Driver.waitAndClick(SUCHEN_SUBMIT_BUTTON);
    }

    public int getContainers() {
        return Driver.getQuantityOfElements(RESULT_LIST_CONTENT_CONTAINER);
    }

}
