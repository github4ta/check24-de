package com.arbbot.api.health;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class Healthcheck {

    private static final Logger log = LoggerFactory.getLogger(Healthcheck.class);
    String adminLogin = "1234";
    String adminPassword = "1234";
    String healthCheckUrl = "http://52.194.254.164:8080/api/health";

    public Response getResponse() {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .when()
                .get(healthCheckUrl);
    }
}
