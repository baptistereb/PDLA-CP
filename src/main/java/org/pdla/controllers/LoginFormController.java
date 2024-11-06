package org.pdla.controllers;

import org.pdla.models.UserManagement;

public class LoginFormController {
    public LoginFormController() {

    }

    public boolean Login(String username, String password) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        return userManagement.Login(username, password);
    }
}
