package com.arbbot.api.healthcheck;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class Healthcheck {

    private static final Logger log = LoggerFactory.getLogger(Healthcheck.class);
    String adminLogin ="1234";
    String adminPassword ="1234";

    URI healthCheckUri = new URI("http://52.194.254.164:8080/api/health");

    Response response = given()
            .auth().preemptive().basic(adminLogin,adminPassword)
            .when()
            .get(healthCheckUri);

    public int getStatusCode(Response response) {
        return getResponse(response).statusCode();
    }

    public  Response getResponse(Response response) {
        return response;
    }

    public Healthcheck() throws MalformedURLException, URISyntaxException {
                    log.info(healthCheckUri.toString() + "Is invalid or something");
    };
}
