import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'

describe('Home page', () => {
    it('should display activities on home page', async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await expect(HomePage.activities).toHaveText("Aktivitäten");
    });
});  