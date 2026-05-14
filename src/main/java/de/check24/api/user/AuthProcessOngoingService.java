package de.check24.api.user;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthProcessOngoingService {
    private final String URL_USER_AUTH_PROCESS_ONGOING = "https://accounts.check24.com/login/api/user/authprocessstart/";
    private Map<String, String> headers;
    private String body;
    private Response response;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void doRequest() {
        response = given()
                .headers(headers)
                .body(body)
                .when()
                .post(URL_USER_AUTH_PROCESS_ONGOING);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getBody() {
        return response.getBody().asPrettyString();
    }

    public Map<String, String> getHeaders() {
        return null;
    }

    public String getMessage() {
        return response.getBody().jsonPath().getString("message");
    }

    public boolean getPrepared() {
        return response.getBody().jsonPath().getBoolean("prepared");
    }

    public String getAuthProcessType() {
        return response.getBody().jsonPath().getString("data.authprocess_type");
    }
}