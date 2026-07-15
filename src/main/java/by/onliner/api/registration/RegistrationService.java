package by.onliner.api.registration;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegistrationService {
    private Response response;
    private final String REGISTRATION_URL = "https://profile.onliner.by/sdapi/user.api/registration";

    public void doRequest(Map<String, String> body, Map<String, String> headers) {
        clearResponse();
        response = given()
                .headers(headers)
                .body(body)
                .when()
                .post(REGISTRATION_URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getMessage() {
        return response.path("message");
    }

    public String getErrorsPassword() {
        return response.path("errors.password[0]");
    }

    public String getErrorsEmail() {
        return response.path("errors.email[0]");
    }

    public String getErrorsRepeatPassword() {
        return response.path("errors.repeat_password[0]");
    }

    public void clearResponse() {
        this.response = null;
    }
}
