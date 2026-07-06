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

    private final WebElement registerButton = driver.findElement(By.xpath("//div[@class ='button__text' and text()='Register']"));
    private final WebElement singUpText = driver.findElement(By.xpath("//div[text()='Sign up']"));
    private final WebElement emailInput = driver.findElement(By.xpath("//input[@name ='email']"));
    private final WebElement passwordInput = driver.findElement(By.xpath("//input[@name ='password']"));
    private final WebElement newsletterCheckBox = driver.findElement(By.xpath("//input[@class = 'checkbox__input' and @name ='subscribedToNewsletter']"));
    private final WebElement salutationMenu = driver.findElement(By.xpath("//div[@class = 'dropdown__select__content']"));
    private final WebElement firstNameInput = driver.findElement(By.xpath("//input[@name = 'firstName']"));
    private final WebElement lastNameInput = driver.findElement(By.xpath("//input[@name = 'lastName']"));
    private final WebElement emailErrorText = driver.findElement(By.xpath("//div[@id ='email__error']"));
    private final WebElement passwordErrorText = driver.findElement(By.xpath("//div[@id='password__error']"));
    private final WebElement loginButton = driver.findElement(By.xpath("//div[@class='button']"));
    private final WebElement alreadyRegisteredText = driver.findElement(By.xpath("//div[@class='login__signin__title']"));
    private final WebElement salutationErrorText = driver.findElement(By.xpath("//div[@id ='salutation__error']"));
    private final WebElement firstNameErrorText = driver.findElement(By.xpath("//div[@id ='firstName__error']"));
    private final WebElement lastNameErrorText = driver.findElement(By.xpath("//div[@id ='lastName__error']"));

    public AuthPage goToLoginPage() {
        driver.get(loginPageUrl);
        return new AuthPage();
    }

    public String getAlreadyRegisteredText() {
        return wait.until(ExpectedConditions.visibilityOf(alreadyRegisteredText)).getText();
    }

    public String getSingUpText() {
        return singUpText.getText();
    }

    public AuthPage clickToEmail() {
        emailInput.click();
        return this;
    }

    public AuthPage clickToPassword() {
        passwordInput.click();
        return this;
    }

    public AuthPage clickToLoginButton() {
        loginButton.click();
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

    public AuthPage setFirstName(String text) {
        firstNameInput.sendKeys(text);
        return this;
    }

    public AuthPage setLastNameInput(String text) {
        lastNameInput.sendKeys(text);
        return this;
    }

    public AuthPage registerButtonClick() {
        registerButton.click();
        return this;
    }

    public AuthPage salutationMenuClick(String text) {
        salutationMenu.click();
        return this;
    }

    public AuthPage newsletterCheckBoxClick() {
        newsletterCheckBox.click();
        return this;
    }

    public String getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emailErrorText)).getText();
    }

    public String getPasswordErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(passwordErrorText)).getText();
    }

    public String getSalutationErrorText() {
        return salutationErrorText.getText();
    }

    public String getFirstNameErrorText() {
        return firstNameErrorText.getText();
    }

    public String getLastNameErrorText() {
        return lastNameErrorText.getText();
    }
}
