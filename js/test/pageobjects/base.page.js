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
}
