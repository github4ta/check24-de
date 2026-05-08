import { $ } from '@wdio/globals'
import BasePage from './base.page.js'

class SearchPage extends BasePage {
    #CENTS_IN_EURO = 100;

    #SPLASH_SCREEN_BUTTON_CLOSE = "//div[@id='splashScreenContainer']//div[contains(@class, 'close')]";
    #DESTINATION_INPUT = "//input[@data-test-id-qa='destination-suggestion-input']";
    #DESTINATION_SUGGESTION_ITEM = "//div[@data-test-id-qa='destination-suggestion']";
    #DATA_RANGE_PICKER_INPUT = "//div[@data-test-id-qa='date-range-picker-input']";
    #DATA_TODAY_BUTTON = "//button[contains(@aria-label, 'Today')]";
    #SUCHEN_SUBMIT_BUTTON = "//button[@data-test-id-qa='submit']";
    #CLOSE_ICON = "//div[contains(@class, 'loyaltyLoginTeaserOverlay')]//div[contains(@class, 'closeIconContainer')]";
    #IHR_BUDGET_SLIDER = "//div[contains(@class, '-rail')]";
    #RESULT_LIST_PRICE = "//div[@data-test-id-qa='results-list-price']"
    #MIN_PRICE_RANGE = "(//div[@role='slider' and @data-label='min']//span)[2]";
    #MAX_PRICE_RANGE = "(//div[@role='slider' and @data-label='max']//span)[2]";
    #DISTANCE_MAX_5_KM = "//div[contains(@data-label,'5 km')]";
    #CLOSE_ICON = "//div[contains(@class, loyaltyLoginTeaserOverLay)]//div[contains(@class, 'closeIconContainer')]"
    #RESULTS_LIST_DISTANCE_HINT = "//span[@data-test-id-qa='results-list-distance-hint']";

    async clickSplashScreenButtonClose() {
        try {
            const btn = await $(this.#SPLASH_SCREEN_BUTTON_CLOSE);
            await btn.waitForClickable({ timeout: 5000 });
            await btn.click();
        } catch (error) {
            console.log("Splash screen is not displayed.");
        }
    }

    get destinationInput() {
        return $(this.#DESTINATION_INPUT);
    }

    async setDestinationInput(destination) {
        await this.destinationInput.setValue(destination);
    }

    async clickCloseIcon() {
        await this.waitAndClick(this.#CLOSE_ICON);
        console.log(`Destination input '${destination}' is displayed.`);
        await $(this.#DESTINATION_INPUT).setValue(destination);
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

    async selectDistanceMax5km() {
        await this.waitAndClick(this.#DISTANCE_MAX_5_KM)
    }

    async isDistanceLessOrEqualTo(distanceInMeters) {
        await this.getElementList(this.#RESULTS_LIST_DISTANCE_HINT);
        const distances = await getResultsListDistance();
        for (const distance of distances) {
            if (distance > distanceInMeters) {
                return false;
            }
        }
        return true;
    }

    async scrollBudgetSliderToCenter() {
        const slider = await $(this.#IHR_BUDGET_SLIDER);
        await slider.waitForExist({ timeout: 10000 });
        await slider.dragAndDrop({ x: 10, y: 0 });
    }

    async scrollScreenToTheEnd() {
        await browser.execute(() => {
            window.scrollTo(0, document.body.scrollHeight);
        });
        const element = await $(this.#RESULT_LIST_PRICE);
        await element.waitForExist({ timeout: 10000 });
        console.log("Scrolled to the end and element is present.");
    }

    async getMinRangePrice() {
        const price = await this.getRangePrice(this.#MIN_PRICE_RANGE);
        return price * this.#CENTS_IN_EURO;
    }


    async getMaxRangePrice() {
        const price = await this.getRangePrice(this.#MAX_PRICE_RANGE);
        return price * this.#CENTS_IN_EURO;
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

    async getPrices() {
        const textListPrices = await this.getTexts(this.#RESULT_LIST_PRICE);
        return this.#parsePrice(textListPrices);
    }

    async getResultsDistanceHint() {
        return await this.getElementText(this.#RESULTS_LIST_DISTANCE_HINT);
    }

    #parsePrice(list) {
        return list.map(text => this.#parsePriceToInt(text));
    }

    async getDistanceInMeters(distanceHint) {
        if (distanceHint == null || distanceHint.trim().isEmpty()) {
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

    #parsePriceToInt(text) {
        const digitsOnly = text.replace(/[^0-9]/g, "");
        return parseInt(digitsOnly, 10);
    }
}

export default new SearchPage();
