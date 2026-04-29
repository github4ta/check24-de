package de.check24.ui.pages.search;

import de.check24.ui.driver.Driver;
import de.check24.ui.pages.base.BasePage;

import java.util.List;

import static de.check24.ui.driver.Driver.waitAndClick;

public class SearchPage extends BasePage {
    private final String SPLASH_SCREEN_BUTTON_CLOSE = "//div[@id='splashScreenContainer']//div[contains(@class, 'close')]";
    private final String DESTINATION_INPUT = "//input[@data-test-id-qa='destination-suggestion-input']";
    private final String DESTINATION_SUGGESTION_ITEM = "//div[@data-test-id-qa='destination-suggestion']";
    private final String DATA_RANGE_PICKER_INPUT = "//div[@data-test-id-qa='date-range-picker-input']";
    private final String DATA_TODAY_BUTTON = "//button[contains(@aria-label, 'Today')]";
    private final String SUCHEN_SUBMIT_BUTTON = "//button[@data-test-id-qa='submit']";
    private final String RESULT_LIST_CONTENT_CONTAINER = "//div[@data-test-id-qa='results-list-content-container']/div";
    private final String INTELLIGENT_FILTER_INPUT = "//textarea[@class='a27f8c739-textArea']";
    private final String HOTEL_NAME = "//span[@data-test-id-qa='hotel-name']";
    private final String OPTION_MAX_3_KM = "//div[contains(@data-label,'3 km')]";
    private final String FILTER_TEMPLATE = "//section[@data-test-id-qa=\"filter-section-wrapper\" and contains(., '%s')]";
    private final String FILTER_OPTION_TEMPLATE = "//div[@data-label=\"%s\"]";
    private final String MEHR_ANZEIGEN_LINK = "//a[text()='mehr anzeigen']";
    private final String SHORT_SUMMARIES_CONTAINER = "//div[contains(@class, 'shortSummariesContainer')]";

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
        waitAndClick(DESTINATION_SUGGESTION_ITEM);
    }

    public void clickDataTodayButton() {
        waitAndClick(DATA_TODAY_BUTTON);
    }

    public void clickSuchenSubmitButton() {
        waitAndClick(SUCHEN_SUBMIT_BUTTON);
    }

    public int getContainers() {
        return Driver.getQuantityOfElements(RESULT_LIST_CONTENT_CONTAINER);
    }

    public void setIntelligentFilter(String value) {
        Driver.waitAndClearAndFillAndPressEnter(INTELLIGENT_FILTER_INPUT, value);
    }

    public boolean isHotelDescriptionsContain(String text) {
        List<String> descriptions = Driver.getTexts(SHORT_SUMMARIES_CONTAINER);
        for (String description : descriptions) {
            if (!description.toLowerCase().contains(text.toLowerCase())) return false;
        }
        return true;
    }

    private void clickMehrAnzeigenForFilter(String filter) {
        Driver.waitAndClick(String.format(FILTER_TEMPLATE + MEHR_ANZEIGEN_LINK, filter));
    }

    private void setFilterOption(String filter, String option) {
        Driver.click(String.format(FILTER_TEMPLATE + FILTER_OPTION_TEMPLATE, filter, option));
    }

    public void setFilterOptionWithMoreLink(String filter, String option) {
        clickMehrAnzeigenForFilter(filter);
        setFilterOption(filter, option);
    }

    public boolean isHotelNamesContain(String text) {
        List<String> names = Driver.getTexts(HOTEL_NAME);
        for (String name : names) {
            if (!name.contains(text)) return false;
        }
        return true;
    }

    public void selectOptionMax3km() {
        waitAndClick(OPTION_MAX_3_KM);
    }
}
