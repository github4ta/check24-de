import { browser } from '@wdio/globals'

/**
* main page object containing all methods, selectors and functionality
* that is shared across all page objects
*/
export default class BasePage {
    /**
    * Opens a sub page of the page
    * @param path path of the sub page (e.g. /path/to/page.html)
    */
    // open(path) {
    //     return browser.url("https://www.check24.de/");
    // }
    async open(path) {
        await browser.url("https://www.check24.de/");
        await browser.maximizeWindow();
    }

    closeCookies() {
        const cookieConsentButton = $("//div[contains(@class, 'c24-cookie-consent-notice-buttons')]//a[@class='c24-cookie-consent-button']");
        cookieConsentButton.click()
    }

    async getText(locator) {
        const element = await $(locator);
        return await element.getText();
    }

    async getRangePrice(locator) {
        const text = await this.getText(locator);
        const priceAsText = text.replace(/[^0-9]/g, "");
        return parseInt(priceAsText, 10);
    }

    async getTexts(locator) {
        const elements = await $$(locator);
        await browser.waitUntil(async () => (await elements.length) > 0, {
            timeout: 10000,
            timeoutMsg: 'Elements were not visible within 10s'
        });
        const texts = await elements.map(el => el.getText());
        return texts.map(text => text.trim()).filter(text => text !== "");
    }

}
