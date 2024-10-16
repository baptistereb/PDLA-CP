package org.pdla.controllers;

import org.pdla.models.ConnectionManagement;
import org.pdla.models.UserManagement;

public class SignupFormController {
    public SignupFormController() {
    }

    public void Signup(String username, String password, String user_type) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        userManagement.CreateUser(username, password, user_type);
    }
}
