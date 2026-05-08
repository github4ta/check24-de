import { browser } from '@wdio/globals'

/**
* main page object containing all methods, selectors and functionality
* that is shared across all page objects
*/
export default class BasePage {
    async open(path) {
        await browser.url("https://www.check24.de/");
        await browser.maximizeWindow();
    }

    closeCookies() {
        const cookieConsentButton = $("//div[contains(@class, 'c24-cookie-consent-notice-buttons')]//a[@class='c24-cookie-consent-button']");
        cookieConsentButton.click()
    }

    fill(locator, value) {
       $(locator).setValue(value);
    }

    async click(locator) {
        await $(locator).click();
    }

    async waitAndClick(locator) {
        const element = await $(locator);
        await element.waitForClickable({timeout: 15000});
        await element.click();
    }

    async getText(locator) {
        return await element.getText();
    }

    async getElementText(locator) {
        return await $(locator).getText()
    }

    async getRangePrice(locator) {
        const text = await this.getText(locator);
        const priceAsText = text.replace(/[^0-9]/g, "");
        return parseInt(priceAsText, 10);
    }

    async getElementList(locator) {
        await $(locator).waitForExist({timeout: 10000});
        return await $$(locator);
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

    async getQuantityOfElements(locator) {
            const elements = await $$(locator);
            return elements.length;
    }
}
