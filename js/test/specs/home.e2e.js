import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'

describe('Home page', () => {
    it.skip('should display activities on home page', async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await expect(HomePage.activities).toHaveText("Aktivitäten");
    });

    it.skip('should display placeholder in search field', async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });
})
