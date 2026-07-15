package by.onliner.api;

import by.onliner.api.registration.Body;
import by.onliner.api.registration.Headers;
import by.onliner.api.registration.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationApiTest {
    RegistrationService registrationService = new RegistrationService();
    Headers headers = new Headers();

    String message = "Validation failed";
    String passwordLengthError = "Пароль должен быть от 8 до 64 символов";
    String incorrectEmailError = "Некорректный e-mail";
    String incorrectPasswordRepeatError = "Пароли не совпадают";

    @Test
    @DisplayName("Registration with password less than 8 symbols")
    public void testShortPassword() {
        Body body = new Body().setBody("test@test.test", "1111", "1111");

        registrationService.doRequest(body.getBody(), headers.getHeaders());

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals(message, registrationService.getMessage());
        Assertions.assertEquals(passwordLengthError, registrationService.getErrorsPassword());
    }

    @Test
    @DisplayName("Registration with incorrect email form")
    public void testIncorrectEmail() {
        Body body = new Body().setBody("test", "111111111", "111111111");

        registrationService.doRequest(body.getBody(), headers.getHeaders());

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals(message, registrationService.getMessage());
        Assertions.assertEquals(incorrectEmailError, registrationService.getErrorsEmail());
    }

    @Test
    @DisplayName("Registration with incorrect password repeat")
    public void testIncorrectPasswordRepeat() {
        Body body = new Body().setBody("test@test.test", "111111111", "222222222");

        registrationService.doRequest(body.getBody(), headers.getHeaders());

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals(message, registrationService.getMessage());
        Assertions.assertEquals(incorrectPasswordRepeatError, registrationService.getErrorsRepeatPassword());
    }
}
