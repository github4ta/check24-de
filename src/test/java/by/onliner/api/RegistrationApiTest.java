package by.onliner.api;

import by.onliner.api.registration.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationApiTest {
    RegistrationService registrationService = new RegistrationService();
    String contentType = "application/json";
    String message = "Validation failed";
    String passwordLengthError = "Пароль должен быть от 8 до 64 символов";

    @Test
    @DisplayName("Registration with password less than 8 symbols")
    public void testShortPassword() {
        String email = "test@test.test";
        String password = "1111";
        String repeatPassword = "1111";

        registrationService.setBody(email, password, repeatPassword);
        registrationService.setContentType(contentType);

        registrationService.doRequest();

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals(message, registrationService.getMessage());
        Assertions.assertEquals(passwordLengthError, registrationService.getErrorsPassword());
    }
}
