package de.check24.tests.ui;

import de.check24.ui.pages.login.LoginLocator;
import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.login.LoginPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseUITest{
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeEach
    void setupHomePage() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
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

    @DisplayName("AU004 - Validation of error message for invalid email during authorization.")
    @Test
    void testAU004() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickLoginIcon();

        loginPage.setEmail("a");
        loginPage.clickSubmitButton();

        assertThat(loginPage.isExpectedErrorMessage())
                .withFailMessage("An error massage doesn't match the expected error message")
                .isTrue();
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
