/**
 * @fileoverview Тест-сьют для проверки главной страницы портала Check24.
 * @module Check24HomeTest
 */

import { expect } from '@wdio/globals'
import HomePage from '../pageobjects/home.page.js'

/**
     * @test
     * @description Проверка отображения лейбла авторизации после обхода cookie-баннера.
     */
    
describe('Check24 Home Page Test', () => {
    it('should verify login label is displayed', async () => {
        await HomePage.open();
        await HomePage.clickCookieConsentButton();

        const isDisplayed = await HomePage.isLabelAnmeldenDisplayed();
        await expect(isDisplayed).toBe(true);
        
    });
});