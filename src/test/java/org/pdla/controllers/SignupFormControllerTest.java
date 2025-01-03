package org.pdla.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.pdla.models.Credentials;
import org.pdla.models.UserManagement;

import static org.junit.jupiter.api.Assertions.*;

class SignupFormControllerTest {

    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
    }


    @org.junit.jupiter.api.Test
    void signup() {
        SignupFormController signupFormController = new SignupFormController();
        assertTrue(signupFormController.Signup("test1", "test1", "user"));
        assertFalse(signupFormController.Signup("test1", "test1", "user"));
        UserManagement.DeleteUser("test1");
        assertFalse(signupFormController.Signup("", "", ""));
        assertFalse(signupFormController.Signup("test", "", ""));
        assertFalse(signupFormController.Signup("", "test", ""));
        assertFalse(signupFormController.Signup("", "", "user"));

    }

}