package de.check24.tests.ui;

import de.check24.ui.pages.login.LoginLocator;
import de.check24.ui.pages.login.LoginPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

public class LoginTest extends BaseUITest{
    private LoginPage loginPage;

    @BeforeEach
    void setupHomePage() {
        loginPage = new LoginPage(driver);
        log.info("LoginPage instance created");
    }

    @Test
    public void AU001() {
        loginPage.navigateToLoginPage();
        loginPage.enterEmailLogin();
        loginPage.clickEmailButton();
        loginPage.enterPassword();
        loginPage.clickPasswordButton();
    }


    @AfterEach
    void cleanup() {
        try {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } catch (Exception e) {
            log.warn("Could not reset window size: {}", e.getMessage());
        }
    }
}
