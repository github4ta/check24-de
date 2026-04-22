package de.check24.tests.ui;

import de.check24.ui.pages.home.HomePage;
import de.check24.ui.pages.login.LoginPage;
import org.junit.jupiter.api.*;
import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import static de.check24.ui.pages.login.LoginText.EXPECTED_PASSWORD_ERROR_MESSAGE;
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

    @Test
    @DisplayName("AU006 - When click on the info icon, a text with a hint appears on the login page")
    @Description("Verify that info icon on login page is working correctly")
    void testAU006() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickLoginIcon();
        loginPage.clickInfoIcon();

        assertThat(loginPage.isActualInfoTextEqualsToExpected())
                .withFailMessage("Actual info text is different from expected")
                .isTrue();
    }

    @DisplayName("AU-005: Negative Login - Incorrect Password")
    @Test
    public void testAU005() {
        homePage.navigateToHomePage();
        homePage.clickCookieAcceptButton();
        homePage.clickLoginIcon();
        loginPage.enterEmailLogin();
        loginPage.clickEmailButton();
        loginPage.enterInvalidPassword();
        loginPage.clickPasswordButton();
        String actualError = loginPage.getErrorPasswordText();
        assertThat(actualError)
                .as("Checking the error message when the password is incorrect")
                .isNotNull()
                .isEqualTo(EXPECTED_PASSWORD_ERROR_MESSAGE);
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
