package com.arbbot.api.symbols;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class SymbolController {

    private static final Logger log = LoggerFactory.getLogger(SymbolController.class);
    String adminLogin = "1234";
    String adminPassword = "1234";
    String symbolsUrl = "Http://52.194.254.164:8080/api/symbols";
    String blacklistUrl = symbolsUrl + "/blacklist";

    public Response getResponse() {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .header("accept", "application/json")
                .when()
                .get(symbolsUrl);
    }

    public Response addToBlacklist(String symbol) {
        String body = """
                {
                    "symbol": "%s"
                }
                """.formatted(symbol);

        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .header("accept", "application/json")
                .contentType("application/json")
                .body(body)
                .when()
                .post(blacklistUrl);
    }

    public Response deleteFromBlacklist(String symbol) {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .header("accept", "application/json")
                .contentType("application/json")
                .when()
                .delete(blacklistUrl + "/" + symbol);
    }
}
