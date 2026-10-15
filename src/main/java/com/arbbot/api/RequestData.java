package com.arbbot.api;

import java.util.Map;

public class RequestData {

    private final Map<String, String> headers;
    private final String body;

    public RequestData(Map<String, String> headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

}
