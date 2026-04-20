package de.check24.ui.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static de.check24.ui.util.ShadowWaits.waitForElementInShadow;

public class LoginPage {
    private final WebDriver driver;

    private static final String GET_URL_LOGIN_PAGE = "https://accounts.check24.com/login?callback=https%3A%2F%2Fwww" +
            ".check24.de%2F&api_product=check24_lp&loc=de_DE&deviceoutput=desktop&ls=1&context_key=default";

    private static final String EMAIL_FOR_LOGIN = "logob86924@sskaid.com";
    private static final String PASSWORD_FOR_LOGIN = "qwerty@443";
    private final By HOST_SHADOW = By.cssSelector("unified-login");
    private final By EMAIL_INPUT = By.cssSelector("input#cl_login");
    private final By EMAIL_BUTTON = By.cssSelector("button.c24-uli-button");
    private final By PASSWORD_INPUT = By.cssSelector("#cl_pw_login");
    private final By PASSWORD_BUTTON = By.cssSelector("#c24-uli-pw-btn");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        driver.get(GET_URL_LOGIN_PAGE);

    }
    public void enterEmailLogin() {
        WebElement inputEmail = waitForElementInShadow(driver, HOST_SHADOW, EMAIL_INPUT);
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(EMAIL_FOR_LOGIN);
    }

    public void enterEmailLoginWithParameters(String emailForLogin) {
        WebElement inputEmail = waitForElementInShadow(driver, HOST_SHADOW, EMAIL_INPUT);
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(emailForLogin);
    }

    public void clickEmailButton() {
        WebElement buttonEmail = waitForElementInShadow(driver, HOST_SHADOW, EMAIL_BUTTON);
        buttonEmail.click();
    }

    public void enterPassword() {
        WebElement inputPassword = waitForElementInShadow(driver, HOST_SHADOW, PASSWORD_INPUT);
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(PASSWORD_FOR_LOGIN);
    }

    public void enterPasswordWithParameters(String passwordForLogin) {
        WebElement inputPassword = waitForElementInShadow(driver, HOST_SHADOW, PASSWORD_INPUT);
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(passwordForLogin);
    }

    public void clickPasswordButton() {
        WebElement buttonPassword = waitForElementInShadow(driver, HOST_SHADOW, PASSWORD_BUTTON);
        buttonPassword.click();
    }
}
