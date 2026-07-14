package com.mytheresa.api.user;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HomeService {
    private Response response;

    private String body = "{}";

    public void doRequest() {
        response = given()
                .when()
                .get("https://www.mytheresa.com/");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}
