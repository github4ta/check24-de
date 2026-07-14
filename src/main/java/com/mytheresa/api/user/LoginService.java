package com.mytheresa.api.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginService {
    private Response response;

    private final String LOGIN = "https://profile.onliner.by/sdapi/user.api/login";
    private String body = """
            {
                "login": "",
                "password": "password"
            }
            """;

    public void doRequest1() {
        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(LOGIN);
    }

    public void doRequest2() {
        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(LOGIN);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getEmailErrorMessage() {
        return response.path("errors.login[0]");
    }

    public String getErrorMessage() {
        return response.path("message");
    }
}
