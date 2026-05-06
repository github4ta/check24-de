import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page';

describe('Home page', () => {
    it('should login with valid credentials', async () => {
        await LoginPage.open()

        await LoginPage.login('tomsmith', 'SuperSecretPassword!');
        await expect(SecurePage.flashAlert);
        await expect(SecurePage.flashAlert).toHaveText(
            expect.stringContaining('You logged into a secure area!'));
    });

    it('should display placeholder in search field', async () => {
        await HomePage.open();
        await HomePage.closeCookies();
        await expect(HomePage.headerSearch).toHaveAttr("placeholder", "Suchen oder fragen");
    });

    // ...
})