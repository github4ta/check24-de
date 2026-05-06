const { browser } = require('@wdio/globals')

/**
* main page object containing all methods, selectors and functionality
* that is shared across all page objects
*/
module.exports = class Page {
    /**
    * Opens a sub page of the page
    * @param path path of the sub page (e.g. /path/to/page.html)
    */
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
