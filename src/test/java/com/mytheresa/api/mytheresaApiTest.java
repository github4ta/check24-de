package com.mytheresa.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class mytheresaApiTest {

    @Test
    void loginRequestReturns200() {
        String body = """
                {
                  "query": "query XUserToken($anonymousCartId: String, $anonymousWishlistId: String, $email: String!, $password: String!) { xUserToken(anonymousCartId: $anonymousCartId, anonymousWishlistId: $anonymousWishlistId, email: $email, password: $password) { cartId wishlistId customerData { email firstName lastName } } }",
                  "variables": {
                    "anonymousCartId": null,
                    "anonymousWishlistId": null,
                    "email": "zbefap@chitthi.in",
                    "password": "dWCrTb6_NP7YsiT"
                  }
                }
                """;

        given()
                .contentType("application/json")
                .accept("*/*")
                .body(body)
        .when()
                .post("https://www.mytheresa.com/api")
        .then()
                .statusCode(200);
    }
}
