import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'
import SearchPage from '../pageobjects/search.page.js'

describe('Search page', () => {

    beforeEach(async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await HomePage.clickQuickChipsLinkHotel();
    });

    it.skip('should display placeholder in search field', async () => {
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });

    it('verify Entfernung Zentrum filter', async () => {
        await SearchPage.setDestinationInput("Köln");
        await expect(SearchPage.destinationInput).toHaveValue("Köln");
        await SearchPage.clickFirstDestinationSuggestionItem();
        await SearchPage.clickDateRangePickerInput();
        await SearchPage.clickDataTodayButton();
        await SearchPage.clickSuchenSubmitButton();
    })
})