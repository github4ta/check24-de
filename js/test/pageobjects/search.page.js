import { $, $$ } from '@wdio/globals'
import BasePage from './base.page.js'

class SearchPage extends BasePage {
    #SELECTORS = {
        TITLE_HOTEL: "//a[@title='Hotel']",
        INPUT_DESTINATION: "//input[@name='destinationQueryInput' or @data-test-id-qa='destination-suggestion-input']",
        SUGGESTION_ITEM: "//div[@data-test-id-qa='destination-suggestion']",
        DATE_INPUT: "//div[@data-test-id-qa='date-range-picker-input']",
        TODAY_BTN: "//button[contains(@aria-label, 'Today')]",
        SUBMIT_BTN: "//button[@data-test-id-qa='submit']",
        INTEL_FILTER: "//textarea[contains(@placeholder, 'Ich suche')]",
        HOTEL_CARDS: "//div[contains(@class, 'hotel-result-card')]"
    };

    get titleHotel() { return $(this.#SELECTORS.TITLE_HOTEL); }
    get destinationInput() { return $(this.#SELECTORS.INPUT_DESTINATION); }
    get suggestionItem() { return $(this.#SELECTORS.SUGGESTION_ITEM); }
    get dateInput() { return $(this.#SELECTORS.DATE_INPUT); }
    get todayBtn() { return $(this.#SELECTORS.TODAY_BTN); }
    get submitBtn() { return $(this.#SELECTORS.SUBMIT_BTN); }
    get filterInput() { return $(this.#SELECTORS.INTEL_FILTER); }
    get hotelCards() { return $$(this.#SELECTORS.HOTEL_CARDS); }

    
    async searchHotels(city) {
        await this.titleHotel.click();
        await this.destinationInput.setValue(city);
        await this.suggestionItem.waitForDisplayed();
        await this.suggestionItem.click();
        await this.dateInput.click();
        await this.todayBtn.click();
        await this.submitBtn.click();
    }

    async removeBanner() {
        const portal = await $('#portalContainer');
        if (await portal.isExisting()) {
            await browser.execute(el => el.remove(), portal);
            console.log(">>> [LOG]: Banner removed");
        }
    }

   async applyIntelligentFilter(keyword) {
        const field = await this.filterInput;
        await field.waitForExist({ timeout: 10000 });
        await field.scrollIntoView({ block: 'center' });
        await field.click();
        
        // Вводим текст через JS, чтобы React/Vue его точно подхватили
        await browser.execute((el, val) => {
            el.value = val;
            el.dispatchEvent(new Event('input', { bubbles: true }));
            el.dispatchEvent(new Event('change', { bubbles: true }));
        }, field, keyword);

        await browser.keys([' ', 'Backspace', 'Enter']);
        await browser.pause(4000); 
    }

    async getAllHotelTexts() {
        console.log(">>> [LOG]: Начинаем сбор текстов карточек...");
    
        const firstCard = await $(this.#SELECTORS.HOTEL_CARDS);
        await firstCard.waitForDisplayed({ 
            timeout: 15000, 
            timeoutMsg: 'Отели не появились после фильтрации' 
        });

        const cards = await this.hotelCards;
        console.log(`>>> [LOG]: Найдено отелей: ${cards.length}`);

        const texts = await Promise.all(cards.map(async (card) => {
            try {
                const rawText = await card.getText();
                return rawText.replace(/\n/g, ' ').replace(/\s+/g, ' ').trim();
            } catch (e) {
                return null;
            }
        }));

        return texts.filter(text => text !== null && text.length > 0);
    }

    async verifyAllResultsContain(keyword) {
        const descriptions = await this.getAllHotelTexts();
        const lowerKeyword = keyword.toLowerCase();
        for (const desc of descriptions) {
            await expect(desc.toLowerCase()).toContain(lowerKeyword);
        }
    }
}

export default new SearchPage();
