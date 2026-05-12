import { $ } from '@wdio/globals'
import BasePage from './base.page.js'

class SearchPage extends BasePage {

    #DESTINATION_INPUT = "//input[@data-test-id-qa='destination-suggestion-input']";
    #DESTINATION_SUGGESTION_ITEM = "//div[@data-test-id-qa='destination-suggestion']";
    #DATA_RANGE_PICKER_INPUT = "//div[@data-test-id-qa='date-range-picker-input']";
    #DATA_TODAY_BUTTON = "//button[contains(@aria-label, 'Today')]";
    #SUCHEN_SUBMIT_BUTTON = "//button[@data-test-id-qa='submit']";
    #RESULT_LIST_CONTENT_CONTAINER = "//div[@data-test-id-qa='results-list-content-container']/div";
    #DISTANCE_MAX_5_KM = "//div[contains(@data-label,'5 km')]";
    #CLOSE_ICON = "//div[contains(@class, loyaltyLoginTeaserOverLay)]//div[contains(@class, 'closeIconContainer')]"
    #RESULTS_LIST_DISTANCE_HINT = "//span[@data-test-id-qa='results-list-distance-hint']";

    get destinationInput() {
        return $(this.#DESTINATION_INPUT);
    }

    async setDestinationInput(destination) {
        await this.destinationInput.setValue(destination);
    }

    async clickCloseIcon() {
        await this.waitAndClick(this.#CLOSE_ICON);
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

    async getContainers() {
        await $(this.#RESULT_LIST_CONTENT_CONTAINER).waitForExist({ timeout: 15000 });
        return await this.getQuantityOfElements(this.#RESULT_LIST_CONTENT_CONTAINER);
    }

    async selectDistanceMax5km() {
        await this.waitAndClick(this.#DISTANCE_MAX_5_KM)
    }

    async isDistanceLessOrEqualTo(distanceInMeters) {
        await this.getElementList(this.#RESULTS_LIST_DISTANCE_HINT);
        const distances = await this.getResultsListDistance();
        for (const distance of distances) {
            if (distance > distanceInMeters) {
                return false;
            }
        }
        return true;
    }

    async getResultsListDistance() {
        const listAsString = await this.getResultsDistanceHint();
        const listAsNumbers = [];
            for (const item of listAsString) {
                const number = await this.getDistanceInMeters(item);
                listAsNumbers.push(number);
            }
        return listAsNumbers;
    }

    async getResultsDistanceHint() {
        return await this.getElementText(this.#RESULT_LIST_CONTENT_CONTAINER);
    }

    async getDistanceInMeters(distanceHint) {
        if (distanceHint == null || distanceHint.trim() === '') {
            return 0;
        }
        let distanceHintText = distanceHint.toLowerCase().replace(",", ".");
        let distanceHintNumber = distanceHintText.replaceAll("[^0-9.]", "");
        let distanceHintDouble = parseFloat(distanceHintNumber);
        if(isNaN(distanceHintDouble)) {
            return 0;
        }
        if(distanceHintText.includes("km")) {
            return Math.round(distanceHintDouble * 1000);
        } else if (distanceHintText.includes("m")) {
            return Math.round(distanceHintDouble);
        }
        return 0;
    }
}

export default new SearchPage();
