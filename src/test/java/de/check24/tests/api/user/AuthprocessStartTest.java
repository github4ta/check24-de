package de.check24.tests.api.user;

import de.check24.api.user.AuthProcessStartService;
import de.check24.api.util.FileReader;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class AuthprocessStartTest {

    @Test
    public void checkAuthprocessStartTest() {
        AuthProcessStartService service = new AuthProcessStartService();
        service.setBody(FileReader.readFile("default-body.txt", "api", "user", "auth"));

        service.doRequest();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(
                        service.getStatusCode())
                .isEqualTo(200);
        softAssertions.assertThat(
                        service.getBody())
                .isEqualTo(FileReader.readFile("response-body.json", "api", "user", "auth"));
        softAssertions.assertThat(
                        service.getMessage())
                .isEqualTo("Invalid CSRF token");
        softAssertions.assertAll();
    }
}
