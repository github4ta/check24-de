package com.mytheresa.ui.page;

import com.mytheresa.ui.page.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthPage extends BasePage {

    public AuthPage() {
        super();
    }

    private final String loginPageUrl = "https://www.mytheresa.com/int/en/account/login";

    private final WebElement emailInput = driver.findElement(By.xpath("//input[@name ='email']"));
    private final WebElement passwordInput = driver.findElement(By.xpath("//input[@name ='password']"));
    private final WebElement emailErrorText = driver.findElement(By.xpath("//div[@id ='email__error']"));
    private final WebElement passwordErrorText = driver.findElement(By.xpath("//div[@id='password__error']"));
    private final WebElement loginButton = driver.findElement(By.xpath("//div[@class='button']"));
    private final WebElement alreadyRegisteredText = driver.findElement(By.xpath("//div[@class='login__signin__title']"));

    public AuthPage goToLoginPage(){
        driver.get(loginPageUrl);
        return this;
    }

    public AuthPage setEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }
    public AuthPage setPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public String getAlreadyRegisteredText(){
        return wait.until(ExpectedConditions.visibilityOf(alreadyRegisteredText)).getText();
    }

    public AuthPage clickToLoginButton(){
        loginButton.click();
        return this;
    }

    public AuthPage clickToEmailField(){
        emailInput.click();
        return this;
    }

    public AuthPage clickToPasswordField(){
        passwordInput.click();
        return this;
    }

    public String getEmailErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOf(emailErrorText)).getText();
    }

    public String getPasswordErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOf(passwordErrorText)).getText();
    }
}
