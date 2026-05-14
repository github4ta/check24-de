package de.check24.api.user;

import de.check24.api.BaseService;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthProcessStartService extends BaseService {
    private final String URL_USER_AUTH_PROCESS_START = BASE_API_URL + "/user/existsextended/";

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void doRequest() {
        response = given()
                .headers(getDefaultHeaders())
                .body(body)
        .when()
                .post(URL_USER_AUTH_PROCESS_START);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getBody() {
        return response.getBody().asPrettyString();
    }

    public Map<String, String> getHeaders() {
        return null;
    }

    public String getMessage() {
        return response.getBody().jsonPath().getString("message");
    }
}
