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
    open (path) {
        return browser.url("https://www.check24.de/");
    }

    closeCookies () {
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

    async getElementText(locator) {
        return await $(locator).getText()
    }

    async getElementList(locator) {
        await $(locator).waitForExist({timeout: 10000});
        return await $$(locator);
    }

    async getQuantityOfElements(locator) {
        const elements = await $$(locator);
        return elements.length;
    }
}
