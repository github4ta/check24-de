package de.check24.ui.pages.login;

import org.openqa.selenium.By;

class LoginLocator {
    static By SHADOW_HOST_LOGIN = By.cssSelector("unified-login");
    static By EMAIL_INPUT = By.cssSelector("input#cl_login");
    static By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    static By EMAIL_ERROR_MESSAGE = By.cssSelector(".c24-uli-input-info > div:last-child");
}
