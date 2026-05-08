import { expect } from '@wdio/globals';
import HomePage from '../pageobjects/home.page.js';
import SearchPage from '../pageobjects/search.page.js';

describe('Search page', () => {
    const CITY = "Bremen";
    const KEYWORD = "Dachterrasse";
    const SEARCH_PLACEHOLDER = "Suchen oder fragen";

    beforeEach(async () => {
        await HomePage.open();
        await HomePage.closeCookies();
    });

    it('SP115 - Search hotels with intelligent filter', async () => {
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", SEARCH_PLACEHOLDER);
        await SearchPage.searchHotels(CITY); 
        await SearchPage.filterInput.waitForDisplayed({ timeout: 10000 });
        await SearchPage.removeBanner();
        await SearchPage.applyIntelligentFilter(KEYWORD);
        const descriptions = await SearchPage.getAllHotelTexts();
        await SearchPage.verifyAllResultsContain(KEYWORD);
    });
});
