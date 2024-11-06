package org.pdla.controllers;

import org.pdla.models.ConnectionManagement;
import org.pdla.models.UserManagement;

import javax.swing.*;

public class SignupFormController {
    public SignupFormController() {
    }

    public boolean Signup(String username, String password, String user_type) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        if (username.isEmpty() || password.isEmpty() || user_type.isEmpty()) {
            return false;
        }
        if(userManagement.UserExists(username)){
            return false;
        }
        userManagement.CreateUser(username, password, user_type);
        return true;
    }
}
