package com.mytheresa.suits;


import com.mytheresa.AuthPageTest;
import com.mytheresa.LoginPageTest;
import com.mytheresa.RegistrationPageTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        AuthPageTest.class,
        LoginPageTest.class,
        RegistrationPageTest.class
})
public class UI {
}
