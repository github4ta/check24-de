package by.onliner.api.registration;

import java.util.HashMap;
import java.util.Map;

public class Body {
    private final Map<String, String> body = new HashMap<>();

    public void put(String name, String value) {
        this.body.put(name, value);
    }

    public Map<String, String> getBody(){
        return this.body;
    }
}
