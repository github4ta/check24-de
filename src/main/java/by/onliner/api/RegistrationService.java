package by.onliner.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationService {
    private Response response;
    private final String REGISTRATION_URL = "https://profile.onliner.by/sdapi/user.api/registration";
    private String contentType = "application/json";
    private String body1 = """
            {"email":"test@test.test",
            "password":"1111",
            "repeat_password":"1111"}
            """;
    private String body2 = """
            {"email":"test",
            "password":"11111111",
            "repeat_password":"11111111"}
            """;

    private String body3 = """
            {"email":"test@test.test",
            "password":"11111111",
            "repeat_password":"22222222"}
            """;

    public void shortPassword() {
        clearResponse();
        response = given()
                .header("Content-Type", contentType)
                .body(body1)
                .when()
                .post(REGISTRATION_URL);
    }

    public void incorrectEmail() {
        clearResponse();
        response = given()
                .header("Content-Type", contentType)
                .body(body2)
                .when()
                .post(REGISTRATION_URL);
    }

    public void incorrectPasswordRepeat() {
        clearResponse();
        response = given()
                .header("Content-Type", contentType)
                .body(body3)
                .when()
                .post(REGISTRATION_URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getMessage() {
        return response.path("message");
    }

    public String getPasswordErrorMessage() {
        return response.path("errors.password[0]");
    }

    public String getEmailErrorMessage() {
        return response.path("errors.email[0]");
    }

    public String getRepeatPasswordErrorMessage() {
        return response.path("errors.repeat_password[0]");
    }

    public void clearResponse() {
        this.response = null;
    }
}
