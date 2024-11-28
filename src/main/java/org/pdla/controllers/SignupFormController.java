package org.pdla.controllers;

import org.pdla.models.UserManagement;

public class SignupFormController {
    public SignupFormController() {
    }

    public boolean Signup(String username, String password, String user_type) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        if (username.isEmpty() || password.isEmpty() || user_type.isEmpty()) {
            System.out.println("Empty fields");
            return false;
        }
        if(userManagement.UserExists(username)){
            System.out.println("User already exists");
            return false;
        }
        userManagement.CreateUser(username, password, user_type);
        System.out.println("User created successfully");
        return true;
    }
}
