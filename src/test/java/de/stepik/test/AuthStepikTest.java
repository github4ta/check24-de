package de.stepik.test;

import de.stepik.api.AuthStepikService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class AuthStepikTest {

    @Test
    public void checkStepikRegistration() {
        long timestamp = System.currentTimeMillis();
        String email = "test_user_" + timestamp + "@mail.ru";

        String body = """
                {
                    "user": {
                        "email": "%s",
                        "password": "Password123",
                        "first_name": "Иван",
                        "last_name": "Смирнов"
                    }
                }
                """.formatted(email);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Referer", "https://stepik.org/catalog?auth=registration");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");

        AuthStepikService service = new AuthStepikService();
        service.setHeaders(headers);
        service.setBody(body);
        service.doRequest();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(service.getStatusCode()).as("Status Code").isEqualTo(201);
        softAssertions.assertThat(service.getBody()).as("Response Body").contains("id");
        softAssertions.assertAll();
    }
}
