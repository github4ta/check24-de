package de.check24.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BaseService {

    protected final String BASE_API_URL = "https://accounts.check24.com/login/api";
    protected Map<String, String> headers;
    protected String body;
    protected Response response;

    protected Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }
}
