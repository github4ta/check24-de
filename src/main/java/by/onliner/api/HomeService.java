package by.onliner.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HomeService {
    private Response response;
    private final String HOME_URL = "https://www.onliner.by/";

    public void doRequest(){
        clearResponse();
        response = given()
                .when()
                .get(HOME_URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public void clearResponse() {
        this.response = null;
    }
}
