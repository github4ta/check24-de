import { $ } from '@wdio/globals'
import BasePage from './base.page.js'

class SearchPage extends BasePage {

    #OPTION_MAX_5_KM = "//div[contains(@data-label,'5 km')]";
    #RESULTS_LIST_DISTANCE_HINT = "//span[@data-test-id-qa='results-list-distance-hint']";
    #DESTINATION_INPUT = "//input[@data-test-id-qa='destination-suggestion-input']";
    #DESTINATION_SUGGESTION_ITEM = "//div[@data-test-id-qa='destination-suggestion']";
    #DATA_RANGE_PICKER_INPUT = "//div[@data-test-id-qa='date-range-picker-input']";
    #DATA_TODAY_BUTTON = "//button[contains(@aria-label, 'Today')]";
    #SUCHEN_SUBMIT_BUTTON = "//button[@data-test-id-qa='submit']";

    get destinationInput() {
        return $(this.#DESTINATION_INPUT);
    }

    async setDestinationInput(destination) {
        await this.destinationInput.setValue(destination);
    }

    async clickFirstDestinationSuggestionItem() {
        await this.waitAndClick(this.#DESTINATION_SUGGESTION_ITEM);
    }

    async clickDateRangePickerInput() {
        await this.click(this.#DATA_RANGE_PICKER_INPUT);
    }

    async clickDataTodayButton() {
        await this.waitAndClick(this.#DATA_TODAY_BUTTON);
    }

    async clickSuchenSubmitButton() {
        await this.waitAndClick(this.#SUCHEN_SUBMIT_BUTTON);
    }
}

export default new SearchPage();
