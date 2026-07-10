package com.mytheresa.api;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ArbApiTest {

    @Test
    public void CheckHealthTest() {

        given()
                .auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .when()
                .get("http://52.194.254.164:8080/api/health")
                .then()
                .statusCode(200)
                .body("status", equalTo("ok"), "version", equalTo("1.0.0"), "symbols", equalTo(1))
                .log().body()
        ;
    }

    @Test
    public void CheckCrudOperationsForSymbolsBlacklitTest() {

        String sym = "XRPUSDT";
        String body = """
                {
                    "symbol": "XRPUSDT"
                }
                """;

        given()
                .auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .contentType("application/json")
                .body(body)
                .when()
                .post("http://52.194.254.164:8080/api/symbols/blacklist")
                .then()
                .statusCode(200);

        given().
                auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .contentType("application/json")
                .when()
                .get("http://52.194.254.164:8080/api/symbols")
                .then()
                .statusCode(200)
                .body("blacklist.userList", hasItem("XRPUSDT"));

        given()
                .auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .contentType("application/json")
                .when()
                .delete("http://52.194.254.164:8080/api/symbols/blacklist/"+sym)
                .then()
                .body("blacklist.userList", not(hasItem("XRPUSDT")))
                .statusCode(200);
    }
}
