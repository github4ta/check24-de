import { browser, expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'
import SearchPage from '../pageobjects/search.page.js'

describe('Search page', () => {

    beforeEach(async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await HomePage.clickQuickChipsLinkHotel();
    });

    // TODO при автозапуске фильтр расстояния не отображается, нужно разобраться почему
    it.skip('verify Entfernung Zentrum filter', async () => {
        await SearchPage.setDestinationInput("Köln");
        await expect(SearchPage.destinationInput).toHaveValue("Köln");
        await SearchPage.clickFirstDestinationSuggestionItem();
        await SearchPage.clickDateRangePickerInput();
        await SearchPage.clickDataTodayButton();
        await SearchPage.clickSuchenSubmitButton();
        await SearchPage.clickCloseIcon();

        await SearchPage.selectFilterOption("Ihre vorherigen Filter", "Weniger als 5 km");
        
        //const isLessOrEqual = await SearchPage.isDistanceLessOrEqualTo(5000);
        // expect(isLessOrEqual).toBe(true);
    });

    // TODO не работает, нужно разобраться почему.
    it.skip('should verify that all prices are within the selected range (SP111)', async () => {
        await SearchPage.setDestinationInput("Berlin");
        await SearchPage.clickFirstDestinationSuggestionItem();
        await SearchPage.clickDateRangePickerInput();
        await SearchPage.clickDataTodayButton();
        await SearchPage.clickSuchenSubmitButton();
        await SearchPage.clickCloseIcon();


        // await SearchPage.scrollBudgetSliderToCenter();
        // await SearchPage.scrollScreenToTheEnd();

        await browser.pause(5000); // Pause to allow any lazy-loaded content to load

        const minRangePrice = await SearchPage.getMinRangePrice();
        const maxRangePrice = await SearchPage.getMaxRangePrice();
        const prices = await SearchPage.getPrices();

        prices.forEach(price => {
            expect(price).toBeGreaterThanOrEqual(minRangePrice);
            expect(price).toBeLessThanOrEqual(maxRangePrice);
        });
    });

})
