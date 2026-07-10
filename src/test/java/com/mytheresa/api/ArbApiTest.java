package com.mytheresa.api;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ArbApiTest {

    String sym = "XRPUSDT";

    @Test
    @Order(1)
    public void CheckHealthTest() {

        given()
                .auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .when()
                .get("http://52.194.254.164:8080/api/health")
                .then()
                .statusCode(200)
                .log().body()
                .body("status", equalTo("ok"), "version", equalTo("1.0.0"), "symbols", equalTo(1));
    }

    @Test
    @Order(2)
    public void checkPostRequestForBlackListTest() {

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
    }

    @Test
    @Order(3)
    public void checkPresenceOfSymbolInBlacklistTest() {
        given().
                auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .contentType("application/json")
                .when()
                .get("http://52.194.254.164:8080/api/symbols")
                .then()
                .statusCode(200)
                .body("blacklist.userList", hasItem("XRPUSDT"));
    }

    @Test
    @Order(4)
    public void checkDeleteRequestForBlackListTest() {

        given()
                .auth().preemptive().basic("1234", "1234")
                .header("accept", "application/json")
                .contentType("application/json")
                .when()
                .delete("http://52.194.254.164:8080/api/symbols/blacklist/"+sym)
                .then()
                .body("blacklist.userList", not(hasItem(sym)))
                .statusCode(200);
    }
}
