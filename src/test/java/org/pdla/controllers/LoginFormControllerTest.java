package org.pdla.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.pdla.models.Credentials;
import org.pdla.models.UserManagement;
import org.pdla.views.SignupForm;

import static org.junit.jupiter.api.Assertions.*;

class LoginFormControllerTest
{
    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
    }

    @org.junit.jupiter.api.Test
    void login()
    {
        LoginFormController loginFormController = new LoginFormController();
        SignupFormController Signup = new SignupFormController();
        Signup.Signup("test2", "test", "user");
        assertTrue(loginFormController.Login("test2", "test"));
        assertFalse(loginFormController.Login("", ""));
        assertFalse(loginFormController.Login("test", ""));
        assertFalse(loginFormController.Login("", "test"));
        UserManagement.DeleteUser("test2");
    }

}