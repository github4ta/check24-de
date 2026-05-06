import { $ } from '@wdio/globals'
import Page from './page.js';

class HomePage extends Page {

    get footerCompanyLine() { 
        return $("//div[@class='c24-footer-company-line']"); 
    }

    get labelAnmelden() { 
        return $("//div[contains(@class, 'c24-customer-icon-lo')]"); 
    }

    open() {
        return super.open('');
    }
    
    async getFooterCompanyLineText() {
        await this.footerCompanyLine.waitForDisplayed();
        return await this.footerCompanyLine.getText();
    }

    async isLabelAnmeldenDisplayed() {
        return await this.labelAnmelden.isDisplayed();
    }
} 

export default new HomePage();