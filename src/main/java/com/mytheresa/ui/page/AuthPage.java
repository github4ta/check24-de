package com.mytheresa.ui.page;

import com.mytheresa.ui.page.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthPage extends BasePage {

    public AuthPage() {
        super();
    }

    private String LOGIN_URL = "https://www.mytheresa.com/int/en/account/login";
    private String ALREADY_REGISTERED_TEXT = "//div[@class='login__signin__title']";
    private String EMAIL_INPUT = "//input[@name ='email']";
    private String PASSWORD_INPUT = "//input[@name='password']";
    private String LOGIN = "//div[@class='button']";
    private String EMAIL_ERROR_MESSAGE = "//div[@id='email__error']";
    private String PASSWORD_ERROR_MESSAGE = "//div[@id='password__error']";

    public AuthPage goToLoginPage(){
        driver.get(LOGIN_URL);
        return this;
    }
    public String getAlreadyRegisteredText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALREADY_REGISTERED_TEXT))).getText();
    }

    public AuthPage clickToLoginButton(){
        driver.findElement(By.xpath(LOGIN)).click();
        return this;
    }

    public AuthPage inputEmail(String email){
        driver.findElement(By.xpath(EMAIL_INPUT)).sendKeys(email);
        return this;
    }

    public AuthPage inputPassword(String password){
        driver.findElement(By.xpath(PASSWORD_INPUT)).sendKeys(password);
        return this;
    }

    public AuthPage clickToEmailField(){
        driver.findElement(By.xpath(EMAIL_INPUT)).click();
        return this;
    }

    public AuthPage clickToPasswordField(){
        driver.findElement(By.xpath(PASSWORD_INPUT)).click();
        return this;
    }

    public String getEmailErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ERROR_MESSAGE))).getText();
    }

    public String getPasswordErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_ERROR_MESSAGE))).getText();
    }
}
