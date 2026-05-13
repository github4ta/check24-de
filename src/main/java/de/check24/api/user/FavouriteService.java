package de.check24.api.user;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class FavouriteService {
    private final String URL_FAVOURITE = "https://www.check24.de/ajax/user/wishlist/count/";
    private Map<String, String> cookies;
    private Map<String, String> formData;

    private Response response;

    public FavouriteService setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }

    public FavouriteService setFormData(Map<String, String> formData) {
        this.formData = formData;
        return this;
    }

    public void setFavouriteCount() {
        response = given()
                .cookies(cookies)
                .formParams(formData)
                .when()
                .post(URL_FAVOURITE);
    }

    public void getFavouriteCount() {
        response = given()
                .cookies(cookies)
                .when()
                .get(URL_FAVOURITE);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public boolean hasKey(String key) {
        return response.jsonPath().get(key) != null;
    }


}
