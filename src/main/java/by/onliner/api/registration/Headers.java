package by.onliner.api.registration;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, String> headers = new HashMap<>();

    public Headers() {
        this.headers.put("Content-Type", "application/json");
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
