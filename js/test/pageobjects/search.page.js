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

    async clickSplashScreenButtonClose() {
        try {
            const btn = await $(this.#SPLASH_SCREEN_BUTTON_CLOSE);
            await btn.waitForClickable({ timeout: 5000 });
            await btn.click();
        } catch (error) {
            console.log("Splash screen is not displayed.");
        }
    }

    async setDestinationInput(destination) {
        console.log(`Destination input '${destination}' is displayed.`);
        await $(this.#DESTINATION_INPUT).setValue(destination);
    }

    async clickFirstDestinationSuggestionItem() {
        const element = await $(this.#DESTINATION_SUGGESTION_ITEM);
        await element.waitForClickable();
        await element.click();
        console.log("First destination suggestion item is clicked.");
    }

    async clickDateRangePickerInput() {
        await $(this.#DATA_RANGE_PICKER_INPUT).click();
        console.log("Date range picker input is clicked.");
    }

    async clickDataTodayButton() {
        const btn = await $(this.#DATA_TODAY_BUTTON);
        await btn.waitForClickable();
        await btn.click();
        console.log("Data Today button is clicked.");
    }

    async clickSuchenSubmitButton() {
        const btn = await $(this.#SUCHEN_SUBMIT_BUTTON);
        await btn.waitForClickable();
        await btn.click();
        console.log("Suchen submitted button is clicked");
    }

    async clickCloseIcon() {
        try {
            const icon = await $(this.#CLOSE_ICON);
            await icon.waitForClickable({ timeout: 7000 });
            await icon.click();
            console.log("Close icon is clicked.");
        } catch (error) {
            console.log("Loyalty login teaser overlay is not displayed.");
        }
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

    async getPrices() {
        const textListPrices = await this.getTexts(this.#RESULT_LIST_PRICE);
        return this.#parsePrice(textListPrices);
    }

    #parsePrice(list) {
        return list.map(text => this.#parsePriceToInt(text));
    }

    #parsePriceToInt(text) {
        const digitsOnly = text.replace(/[^0-9]/g, "");
        return parseInt(digitsOnly, 10);
    }
}

export default new SearchPage();
