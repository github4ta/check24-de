package com.arbbot.api.symbols;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class SymbolController {

    private static final Logger log = LoggerFactory.getLogger(SymbolController.class);
    String adminLogin = "1234";
    String adminPassword = "1234";
    URI symbolsUri;
    URI blacklistUri;

    public SymbolController() throws URISyntaxException {
        symbolsUri = new URI("http://52.194.254.164:8080/api/symbols");
        blacklistUri = new URI(symbolsUri + "/blacklist");
        log.info(symbolsUri.toString());
    }

    public Response getResponse() {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .header("accept", "application/json")
                .when()
                .get(symbolsUri);
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
                .post(blacklistUri);
    }

    public Response deleteFromBlacklist(String symbol) {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .header("accept", "application/json")
                .contentType("application/json")
                .when()
                .delete(blacklistUri + "/" + symbol);
    }

    public int getStatusCode() {
        return getResponse().statusCode();
    }
}
