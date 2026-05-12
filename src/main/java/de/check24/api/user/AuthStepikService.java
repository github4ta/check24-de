package de.check24.api.user;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthStepikService {
    private Map<String, String> headers;
    private String body;
    private int statusCode;
    private String responseBody;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void doRequest() {
        Response initResponse = given()
                .header("User-Agent", "Mozilla/5.0")
                .get("https://stepik.org/catalog");

        String csrftoken = initResponse.getCookie("csrftoken");
        Map<String, String> allCookies = initResponse.getCookies();

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

        this.statusCode = response.getStatusCode();
        this.responseBody = response.getBody().asString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return responseBody;
    }
}
