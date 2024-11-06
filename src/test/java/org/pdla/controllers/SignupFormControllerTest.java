package org.pdla.controllers;

import org.pdla.models.UserManagement;

import static org.junit.jupiter.api.Assertions.*;

class SignupFormControllerTest {

    @org.junit.jupiter.api.Test
    void signup() {
        SignupFormController signupFormController = new SignupFormController();
        assertTrue(signupFormController.Signup("test1", "test1", "user"));
        assertFalse(signupFormController.Signup("test1", "test1", "user"));
        UserManagement userManagement = new UserManagement();
        userManagement.DeleteUser("test1");
        assertFalse(signupFormController.Signup("", "", ""));
        assertFalse(signupFormController.Signup("test", "", ""));
        assertFalse(signupFormController.Signup("", "test", ""));
        assertFalse(signupFormController.Signup("", "", "user"));

    }

}