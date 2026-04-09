package de.check24.tests.ui;

import de.check24.config.ConfigLoader;
import de.check24.ui.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

/**
 * Demo test for checking UI and API integration
 */
public class DemoTest extends BaseUITest {
    private final Faker faker = new Faker();

    @BeforeEach
    void setupAPI() {
        // Setup API using configuration
        String apiUrl = ConfigLoader.get("api.base.url", "https://httpbin.org");
        RestAssured.baseURI = apiUrl;
        log.info("API baseURI set: {}", apiUrl);
    }

    @Test
    @DisplayName("Demo API + UI test")
    @Description("Get data via API and perform login in UI")
    void fullTestFlow() {
        log.info("=== Starting full test flow ===");
        String username = faker.name().username();
        log.info("Generated username: {}", username);

        // 1. API Step
        checkUserExists(username);

        // 2. UI Step
        loginViaUi(username, "password123");
        
        log.info("=== Test flow completed successfully ===");
    }

    @Step("Check data via API")
    private void checkUserExists(String username) {
        log.info("Executing GET request with parameter: {}", username);
        given()
                .queryParam("name", username)
                .when()
                .get("/get")
                .then()
                .statusCode(200);
        log.info("API check passed successfully");
    }

    @Step("Login to system via UI")
    private void loginViaUi(String user, String pass) {
        log.info("Navigating to login page...");
        driver.get("https://example.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, pass);
        log.info("Login completed for user: {}", user);
    }
}
