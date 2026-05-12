package de.check24.tests.api.user;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthprocessStartTest {

    @Test
    public void checkAuthprocessStartTest() {
        given()
                .header("User-Agent", "Mozilla")
                .body("")
        .when()
                .get("https://www.check24.de/")
        .then()
                .statusCode(300);
    }
}
