import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'

describe('Check24 Home Page Test', () => {
    it('should verify login label is displayed', async () => {
        await HomePage.open();
        await HomePage.clickCookieConsentButton();

        const isDisplayed = await HomePage.isLabelAnmeldenDisplayed();
        await expect(isDisplayed).toBe(true);
        
    });
});