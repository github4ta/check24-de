import { $ } from '@wdio/globals'
import BasePage from './base.page.js'

class HomePage extends BasePage {

    #ACTIVITIES = "//div[@class='c24-activities-label c24-header-icon-label']";
    #HEADER_SEARCH = "//input[@id='c24-search-header']";
    #QUICK_CHIPS_LINK_HOTEL = "//a[@data-identifier='hotel']";
<<<<<<< HEAD
=======
    #ANMELDEN_LABEL = "//div[contains(@class, 'c24-customer-icon-lo')]";

    get anmeldenLabel() {
        return $(this.#ANMELDEN_LABEL);
    }
    
    get quickChipsLinkHotel() {
        return $(this.#QUICK_CHIPS_LINK_HOTEL);
    }

    async clickQuickChipsLinkHotel() {
        await this.quickChipsLinkHotel.click();
    }
>>>>>>> js

    get headerSearch () {
        return $(this.#HEADER_SEARCH);
    }

    get btnSubmit() {
        return $('button[type="submit"]');
    }

    get activities() {
        return $(this.#ACTIVITIES);
    }

    async clickQuickChipsLinkHotel() {
        await this.click(this.#QUICK_CHIPS_LINK_HOTEL);
    }
}

export default new HomePage();
