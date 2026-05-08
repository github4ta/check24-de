import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'
import SearchPage from '../pageobjects/search.page.js'

describe('Search page', () => {

    beforeEach(async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await HomePage.clickQuickChipsLinkHotel();

        await SearchPage.clickSplashScreenButtonClose();
    });

    it.skip('should display placeholder in search field', async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });

    it('should verify that all prices are within the selected range (SP111)', async () => {
        await SearchPage.setDestinationInput("Berlin");
        await SearchPage.clickFirstDestinationSuggestionItem();
        await SearchPage.clickDateRangePickerInput();
        await SearchPage.clickDataTodayButton();
        await SearchPage.clickSuchenSubmitButton();
        await SearchPage.clickCloseIcon();
        await SearchPage.scrollBudgetSliderToCenter();
        await SearchPage.scrollScreenToTheEnd(); 

        const minRangePrice = await SearchPage.getMinRangePrice();
        const maxRangePrice = await SearchPage.getMaxRangePrice();
        const prices = await SearchPage.getPrices();
        
        prices.forEach(price => {
            expect(price).toBeGreaterThanOrEqual(minRangePrice);
            expect(price).toBeLessThanOrEqual(maxRangePrice);
        });
    });

    
})
