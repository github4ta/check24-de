package by.onliner.api.registration;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, String> headers = new HashMap<>();

    public void put(String header, String value){
        this.headers.put(header, value);
    }

    public Map<String, String> getHeaders(){
        return this.headers;
    }
}
