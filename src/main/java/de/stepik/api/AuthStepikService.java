package de.stepik.api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AuthStepikService {
    private Map<String, String> headers = new HashMap<>();
    private String body;
    private int statusCode;
    private String responseBody;
    private Map<String, String> allCookies;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private void initializeSession() {
        Response initResponse = given()
                .header("User-Agent", headers != null ? headers.getOrDefault("User-Agent", "Mozilla/5.0") : "Mozilla/5.0")
                .get("https://stepik.org/catalog");

        this.allCookies = initResponse.getCookies();
    }

    public void doRequest() {
        initializeSession();
        String csrftoken = allCookies.get("csrftoken");

        Response response = given()
                .cookies(allCookies)
                .headers(headers)
                .header("X-CSRFToken", csrftoken)
                .body(body)
                .when()
                .post("https://stepik.org/api/users")
                .then()
                .extract()
                .response();
        extractResponseData(response);
    }

    private void extractResponseData(Response response) {
        this.statusCode = response.getStatusCode();
        this.responseBody = response.asString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return responseBody;
    }
}
