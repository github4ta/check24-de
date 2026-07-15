package by.onliner.api.registration;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationService {
    private Response response;
    private final String REGISTRATION_URL = "https://profile.onliner.by/sdapi/user.api/registration";
    private Body body = new Body();
    private Headers headers = new Headers();

    public RegistrationService setBody(String email, String password, String repeatPassword) {
        body.put("email", email);
        body.put("password", password);
        body.put("repeat_password", repeatPassword);
        return this;
    }

    public RegistrationService setContentType(String contentType){
        headers.put("Content-Type", contentType);
        return this;
    }

    public void doRequest() {
        clearResponse();
        response = given()
                .headers(headers.getHeaders())
                .body(body.getBody())
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

    public void clearResponse() {
        this.response = null;
    }
}
