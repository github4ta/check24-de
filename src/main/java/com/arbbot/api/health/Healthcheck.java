package com.arbbot.api.health;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class Healthcheck {

    private static final Logger log = LoggerFactory.getLogger(Healthcheck.class);
    String adminLogin = "1234";
    String adminPassword = "1234";
    URI healthCheckUri;

    public Healthcheck() throws URISyntaxException {
        healthCheckUri = new URI("http://52.194.254.164:8080/api/health");
        log.info(healthCheckUri.toString());
    }

    public Response getResponse() {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .when()
                .get(healthCheckUri);
    }

    public int getStatusCode() {
        return getResponse().statusCode();
    }
}
