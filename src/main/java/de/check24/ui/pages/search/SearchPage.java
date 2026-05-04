package de.check24.ui.pages.search;

import de.check24.ui.driver.Driver;
import de.check24.ui.pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

import static de.check24.ui.driver.Driver.getTexts;
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
    private final String OPTION_MAX_5_KM = "//div[contains(@data-label,'5 km')]";
    private final String FILTER_TEMPLATE = "//section[@data-test-id-qa=\"filter-section-wrapper\" and contains(., '%s')]";
    private final String FILTER_OPTION_TEMPLATE = "//div[@data-label=\"%s\"]";
    private final String MEHR_ANZEIGEN_LINK = "//a[text()='mehr anzeigen']";
    private final String SHORT_SUMMARIES_CONTAINER = "//div[contains(@class, 'shortSummariesContainer')]";
    private final String IHR_BUDGET_SLIDER = "//div[contains(@class, '-rail')]";
    private final String MIN_PRICE_RANGE = "(//div[@role='slider' and @data-label='min']//span)[2]";
    private final String MAX_PRICE_RANGE = "(//div[@role='slider' and @data-label='max']//span)[2]";
    private final String RESULT_LIST_PRICE = "//div[@data-test-id-qa='results-list-price']";
    private final String HOTEL_RATING = "//div[@data-test-id-qa='hotel-rating']";

    public void clickSplashScreenButtonClose() {
        Driver.waitAndClick(SPLASH_SCREEN_BUTTON_CLOSE);
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

    public void setFilterOption(String filter, String option) {
        Driver.waitAndClick(String.format(FILTER_TEMPLATE + FILTER_OPTION_TEMPLATE, filter, option));
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

    public void selectOptionMax5km() {
        waitAndClick(OPTION_MAX_5_KM);
    }

    public void scrollIhrBudgetSliderToCenter() {
        Driver.scrollSliderToCenter(IHR_BUDGET_SLIDER);
    }

    public void scrollScreen() {
        Driver.scrollScreenToTheEnd(RESULT_LIST_PRICE);
    }

    private String cleanText(String text) {
        String cleaned = text.trim();
        if (cleaned.contains(".") && cleaned.contains(",")) {
            cleaned = cleaned.replace(".", "");
        }
        cleaned = cleaned.replace(",", ".");
        return cleaned.replaceAll("[^0-9.]", "");
    }

    private int convertStringToInt(String s) {
        return (int) Math.round(Double.parseDouble(s));
    }
    private List<Integer> parsePrice(List<String> list) {
        return list.stream()
                .map(this::cleanText)
                .map(this::convertStringToInt)
                .toList();
    }

    private List<Integer> getPrices() {
        List<String> textListPrices = Driver.getTexts(RESULT_LIST_PRICE);
        return parsePrice(textListPrices);
    }

    private int getMinRangePrice() {
        return Driver.getRangePrice(MIN_PRICE_RANGE);
    }

    private int getMaxRangePrice() {
        return Driver.getRangePrice(MAX_PRICE_RANGE);
    }

    public boolean isPriceInChosenRange() {
        int minPrice = getMinRangePrice();
        int maxPrice = getMaxRangePrice();
        List<Integer> prices = getPrices();
        return prices.stream().allMatch(p -> p >= minPrice && p <= maxPrice);
    }

    private int simpleParseAndScale(String value, int decimalPlaces) {
        String numberStr = value.replaceAll("[^0-9.,]", "").replace(",", ".");
        return new BigDecimal(numberStr)
                .movePointRight(decimalPlaces)
                .intValue();
    }

    public boolean isHotelRatingMoreThan(int rating) {
        List<String> hotelRaitingList = getTexts(HOTEL_RATING);
        rating *= 10;
        for (String hotelRaiting : hotelRaitingList) {
            if (rating > simpleParseAndScale(hotelRaiting, 1)) return false;
        }
        return true;
    }
}
