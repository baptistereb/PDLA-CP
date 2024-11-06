package org.pdla.controllers;

import org.pdla.models.UserManagement;
import org.pdla.views.SignupForm;

import static org.junit.jupiter.api.Assertions.*;

class LoginFormControllerTest
{
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
        UserManagement userManagement = new UserManagement();
        userManagement.DeleteUser("test2");
    }

}