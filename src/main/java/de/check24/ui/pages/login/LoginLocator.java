package de.check24.ui.pages.login;

import org.openqa.selenium.By;

class LoginLocator {
    static By SHADOW_HOST_LOGIN = By.cssSelector("unified-login");
    static By EMAIL_INPUT = By.cssSelector("input#cl_login");
    static By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    static By EMAIL_ERROR_MESSAGE = By.cssSelector(".c24-uli-input-info > div:last-child");
    static By HOST_SHADOW = By.cssSelector("unified-login");
    static By EMAIL_BUTTON = By.cssSelector("button.c24-uli-button");
    static By PASSWORD_INPUT = By.cssSelector("#cl_pw_login");
    static By PASSWORD_BUTTON = By.cssSelector("#c24-uli-pw-btn");
    static By PASSWORD_ERROR_MESSAGE = By.cssSelector(".c24-uli-error-pw");
}
