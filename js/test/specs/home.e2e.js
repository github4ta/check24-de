import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'

describe('Home page', () => {

    beforeEach(async () => {
        await HomePage.open();
        await HomePage.closeCookies();
    });

    it('should display activities on home page', async () => {
        await expect(HomePage.activities).toHaveText("Aktivitäten");
    });

    it('should display placeholder in search field', async () => {
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });

    it('should verify login label is displayed', async () => {
        await expect(HomePage.anmeldenLabel).toBeDisplayed();
    });

    it('should display placeholder in search field', async () => {
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });
})
