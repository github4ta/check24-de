package de.check24.ui.pages.search;

import de.check24.ui.driver.Driver;
import de.check24.ui.pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

import static de.check24.ui.driver.Driver.getTexts;
import static de.check24.ui.driver.Driver.waitAndClick;

public class SearchPage extends BasePage {
    private final int CENTOS_IN_EURO = 100;

    private final String SPLASH_SCREEN_BUTTON_CLOSE = "//div[@id='splashScreenContainer']//div[contains(@class, 'close')]";
    private final String CLOSE_ICON = "//div[contains(@class, 'loyaltyLoginTeaserOverlay')]//div[contains(@class, 'closeIconContainer')]";
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
    private final String HOTEL_RESULT_LOCATION = "//div[contains(@class, 'hotelResultLocation')]";
    private final String RESULTS_LIST_DISTANCE_HINT = "//span[@data-test-id-qa='results-list-distance-hint']";

    public void clickSplashScreenButtonClose() {
        try {
            Driver.waitAndClick(SPLASH_SCREEN_BUTTON_CLOSE);
        } catch (Exception e) {
            log.info("Splash screen is not displayed.");
        }
    }

    public void setDestinationInput(String destination) {
        log.info("Destination input '{}' is displayed.", destination);
        Driver.fill(DESTINATION_INPUT, destination);
    }

    public void clickDateRangePickerInput() {
        Driver.click(DATA_RANGE_PICKER_INPUT);
        log.info("Date range picker input is clicked.");
    }

    public void clickFirstDestinationSuggestionItem() {
        waitAndClick(DESTINATION_SUGGESTION_ITEM);
        log.info("First destination suggestion item is clicked.");
    }

    public void clickDataTodayButton() {
        waitAndClick(DATA_TODAY_BUTTON);
        log.info("Data Today button is clicked.");
    }

    public void clickSuchenSubmitButton() {
        waitAndClick(SUCHEN_SUBMIT_BUTTON);
        log.info("Suchen submitted button is clicked.");
    }

    public void clickCloseIcon() {
        try {
            waitAndClick(CLOSE_ICON);
            log.info("Close icon is clicked.");
        } catch (Exception e) {
            log.info("Loyalty login teaser overlay is not displayed.");
        }
    }

    public void setIntelligentFilter(String value) {
        log.info("Intelligent filter '{}' is displayed.", value);
        Driver.waitAndClearAndFillAndPressEnter(INTELLIGENT_FILTER_INPUT, value);
    }

    public boolean isHotelDescriptionsContain(String text) {
        log.info("Hotel description '{}' to check.", text);
        List<String> descriptions = Driver.getTexts(SHORT_SUMMARIES_CONTAINER);
        log.info("Hotel descriptions '{}' is displayed.", descriptions);
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
                .map(this::parsePriceToInt)
                .toList();
    }

    private int parsePriceToInt(String text) {
        String digitsOnly = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(digitsOnly);
    }

    public List<Integer> getPrices() {
        List<String> textListPrices = Driver.getTexts(RESULT_LIST_PRICE);
        return parsePrice(textListPrices);
    }

    public int getMinRangePrice() {
        return Driver.getRangePrice(MIN_PRICE_RANGE) * CENTOS_IN_EURO;
    }

    public int getMaxRangePrice() {
        return Driver.getRangePrice(MAX_PRICE_RANGE) * CENTOS_IN_EURO;
    }

    public List<String> getHotelResultLocations() {
        return Driver.getTexts(HOTEL_RESULT_LOCATION);
    }

    public List<Integer> getResultsListDistance() {
        return Driver.getTexts(RESULTS_LIST_DISTANCE_HINT).stream().map(distanceHint -> parseDistance(distanceHint)).toList();
    }

    private int parseDistance(String text) {
        String cleanValue = text.replaceAll("[^0-9,.]", "").replace(",", ".");
        double distance = Double.parseDouble(cleanValue);
        if (text.toLowerCase().contains("km")) {
            return (int) (distance * 1000);
        }
        return (int) distance;
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
