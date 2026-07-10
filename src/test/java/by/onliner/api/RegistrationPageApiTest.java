package by.onliner.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationPageApiTest {
    String url = "https://profile.onliner.by/sdapi/user.api/registration";
    String contentType = "application/json";

    @Test
    @DisplayName("Registration with password less than 8 symbols")
    public void testShortPassword() {
        String body = """
                {"email":"test@test.test",
                "password":"1111",
                "repeat_password":"1111"}
                """;

        given()
                .header("Content-Type", contentType)
                .body(body)
        .when()
                .post(url)
        .then()
                .statusCode(422)
                .body("errors.password[0]", equalTo("Пароль должен быть от 8 до 64 символов"));
    }

    @Test
    @DisplayName("Registration with incorrect email form")
    public void testIncorrectEmail() {
        String body = """
                {"email":"test",
                "password":"11111111",
                "repeat_password":"11111111"}
                """;

        given()
                .header("Content-Type", contentType)
                .body(body)
        .when()
                .post(url)
        .then()
                .statusCode(422)
                .body("errors.email[0]", equalTo("Некорректный e-mail"));
    }

    @Test
    @DisplayName("Registration with incorrect password repeat")
    public void testIncorrectPasswordRepeat() {
        String body = """
                {"email":"test@test.test",
                "password":"11111111",
                "repeat_password":"22222222"}
                """;

        given()
                .header("Content-Type", contentType)
                .body(body)
        .when()
                .post(url)
        .then()
                .statusCode(422)
                .body("errors.repeat_password[0]", equalTo("Пароли не совпадают"));
    }
}
