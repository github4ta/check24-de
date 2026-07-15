package by.onliner.api.registration;

import java.util.HashMap;
import java.util.Map;

public class Body {
    private final Map<String, String> body = new HashMap<>();

    public Body setBody(String email, String password, String repeatPassword) {
        body.put("email", email);
        body.put("password", password);
        body.put("repeat_password", repeatPassword);
        return this;
    }

    public Map<String, String> getBody() {
        return this.body;
    }
}
