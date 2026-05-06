import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'
import SearchPage from '../pageobjects/search.page.js'

describe('Search page', () => {

    it.skip('should display placeholder in search field', async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });

    
})
