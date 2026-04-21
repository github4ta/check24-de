package de.check24.ui.pages.login;

import de.check24.ui.util.ShadowWaits;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static de.check24.ui.pages.login.LoginLocator.*;
import static de.check24.ui.pages.login.LoginText.INVALID_PASSWORD;
import static de.check24.ui.util.ShadowWaits.waitForElementInShadow;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String GET_URL_LOGIN_PAGE = "https://accounts.check24.com/login?callback=https%3A%2F%2Fwww" +
            ".check24.de%2F&api_product=check24_lp&loc=de_DE&deviceoutput=desktop&ls=1&context_key=default";

    private static final String EMAIL_FOR_LOGIN = "logob86924@sskaid.com";
    private static final String PASSWORD_FOR_LOGIN = "qwerty@443";


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void enterInvalidPassword() {
        WebElement inputPassword = waitForElementInShadow(driver, HOST_SHADOW, PASSWORD_INPUT);
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(INVALID_PASSWORD);
    }

    public String getErrorPasswordText() {
        WebElement errorElement = ShadowWaits.waitForElementInShadow(driver, HOST_SHADOW, PASSWORD_ERROR_MESSAGE);
        return errorElement.getText().trim();
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

    private SearchContext getSearchContext() {
        return driver.findElement(LoginLocator.SHADOW_HOST_LOGIN).getShadowRoot();
    }

    public void setEmail(String email) {
        WebElement input = wait.until(d -> {
                    try {
                        WebElement el = getSearchContext().findElement(EMAIL_INPUT);
                        return (el.isDisplayed() && el.isEnabled()) ? el : null;
                    } catch (Exception e) {
                        return null;
                    }
                }
        );

        if (input != null) {
            input.click();
            input.clear();
            input.sendKeys(email);
        }
    }

    public void clickSubmitButton() {
        WebElement btn = wait.until(d -> {
                    try {
                        return getSearchContext().findElement(LoginLocator.SUBMIT_BUTTON);
                    } catch (Exception e) {
                        return null;
                    }
                }
        );

        if (btn != null) btn.click();
    }

    public boolean isExpectedErrorMessage() {
        WebElement msg = wait.until(d -> {
                    try {
                        return getSearchContext().findElement(LoginLocator.EMAIL_ERROR_MESSAGE);
                    } catch (Exception e) {
                        return null;
                    }
                }
        );

        if (msg == null) return false;

        return msg.getText().equals(LoginText.EMAIL_ERROR_MESSAGE);
    }
}
