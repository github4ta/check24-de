package com.arbbot.api.symbols;

import com.arbbot.api.RequestData;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SymbolController {

    private static final Logger log = LoggerFactory.getLogger(SymbolController.class);
    String adminLogin = "1234";
    String adminPassword = "1234";
    String symbolsUrl = "Http://52.194.254.164:8080/api/symbols";
    String blacklistUrl = symbolsUrl + "/blacklist";
    private String url = symbolsUrl;
    private Method method = Method.GET;

    private RequestData requestData = defaultRequestData();

    private RequestData defaultRequestData() {
        return new RequestData(
                Map.of(
                        "accept", "application/json",
                        "Content-Type", "application/json"
                ),
                null
        );
    }

    public Response getResponse() {
        return given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .header("accept", "application/json")
                .when()
                .get(symbolsUrl);
    }

    public SymbolController setRequestData(RequestData requestData) {
        this.requestData = requestData;
        return this;
    }

    public Response doRequest() {
        RequestSpecification request = given()
                .auth().preemptive().basic(adminLogin, adminPassword)
                .headers(requestData.getHeaders());

        if (requestData.getBody() != null) {
            request.body(requestData.getBody());
        }

        return switch (method) {
            case GET -> request.get(url);
            case POST -> request.post(url);
            case DELETE -> request.delete(url);
            default -> throw new IllegalArgumentException(
                    "Unsupported HTTP method: " + method
            );
        };
    }
    public SymbolController setUrl(String url) {
        this.url = url;
        return this;
    }
    public SymbolController setBlackListUrl() {
        this.url = blacklistUrl;
        return this;
    }

    public SymbolController setMethod(Method method) {
        this.method = method;
        return this;
    }
}
