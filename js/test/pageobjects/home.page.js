import { $ } from '@wdio/globals'
import BasePage from './base.page.js'

class HomePage extends BasePage {

    #ACTIVITIES = "//div[@class='c24-activities-label c24-header-icon-label']";
    #HEADER_SEARCH = "//input[@id='c24-search-header']";

    get headerSearch () {
        return $(this.#HEADER_SEARCH);
    }

    get btnSubmit() {
        return $('button[type="submit"]');
    }

    get activities() {
        return $(this.#ACTIVITIES);
    }
}

export default new HomePage();
