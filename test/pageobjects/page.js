import { browser, $ } from '@wdio/globals'

export default class Page {
   
    open(path) {
        return browser.url(`https://www.check24.de/${path}`)
    }

    get cookieConsentButton() { 
        return $('//a[contains(@class, "c24-cookie-consent-button")]'); 
    }

    async clickCookieConsentButton() {
        if (await this.cookieConsentButton.isDisplayed()) {
            await this.cookieConsentButton.click();
        }
    }
}
