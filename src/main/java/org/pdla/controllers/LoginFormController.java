package org.pdla.controllers;

import org.pdla.models.UserManagement;

public class LoginFormController {
    public LoginFormController() {

    }

    public boolean Login(String username, String password) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        System.out.println("Login attempt for user: " + username);
        System.out.println("Password: " + password);
        return userManagement.Login(username, password);
    }

    public String getUserType(String username) {
        UserManagement userManagement = new UserManagement();
        return userManagement.getUserType(username);
    }
}
