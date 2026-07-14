package by.onliner.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationApiTest {
    RegistrationService registrationService = new RegistrationService();

    @Test
    @DisplayName("Registration with password less than 8 symbols")
    public void testShortPassword() {
        registrationService.doRequestWithShortPassword();

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals("Validation failed", registrationService.getMessage());
        Assertions.assertEquals("Пароль должен быть от 8 до 64 символов", registrationService.getErrorsPassword());
    }

    @Test
    @DisplayName("Registration with incorrect email form")
    public void testIncorrectEmail() {
        registrationService.doRequestWithIncorrectEmail();

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals("Validation failed", registrationService.getMessage());
        Assertions.assertEquals("Некорректный e-mail", registrationService.getErrorsEmail());
    }

    @Test
    @DisplayName("Registration with incorrect password repeat")
    public void testIncorrectPasswordRepeat() {
        registrationService.doRequestWithIncorrectPasswordRepeat();

        Assertions.assertEquals(422, registrationService.getStatusCode());
        Assertions.assertEquals("Validation failed", registrationService.getMessage());
        Assertions.assertEquals("Пароли не совпадают", registrationService.getErrorsRepeatPassword());
    }
}
