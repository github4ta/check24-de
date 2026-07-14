package com.mytheresa.api;

import com.mytheresa.api.user.HomeService;
import com.mytheresa.api.user.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomeTest {

    @Test
    public void testHome() {
        HomeService homeService = new HomeService();
        homeService.doRequest();

        Assertions.assertEquals(200, homeService.getStatusCode());
    }

    @Test
    public void testLogin1() {
        LoginService loginService = new LoginService();
        loginService.doRequest();

        Assertions.assertEquals(422, loginService.getStatusCode());
        Assertions.assertEquals("Укажите ник или e-mail", loginService.getEmailErrorMessage());
        Assertions.assertEquals("Validation failed", loginService.getErrorMessage());
    }
}
