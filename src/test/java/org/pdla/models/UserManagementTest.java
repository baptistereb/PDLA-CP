package org.pdla.models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class UserManagementTest {

    private UserManagement userManagement;

    @BeforeAll
    static void setup() {
        Credentials.SetTestCredentials();
    }

    @BeforeEach
    void init() {
        userManagement = new UserManagement();
    }

    @org.junit.jupiter.api.Test
    void createUser() {
        assertDoesNotThrow(() -> userManagement.CreateUser("AAA", "AAA", "volunteer"));
        assertDoesNotThrow(() -> userManagement.CreateUser("BBB", "BBB", "user"));
        assertDoesNotThrow(() -> userManagement.CreateUser("CCC", "CCC", "moderator"));
        UserManagement.DeleteUser("AAA");
        UserManagement.DeleteUser("BBB");
        UserManagement.DeleteUser("CCC");
    }

    @org.junit.jupiter.api.Test
    void login() {
        userManagement.CreateUser("BBBBB", "a_really_secure_password", "volunteer");

        assertTrue(userManagement.Login("BBBBB", "a_really_secure_password"));
        assertFalse(userManagement.Login("AAA", "tracs2024"));
        assertFalse(userManagement.Login("oula", "a_really_secure_password"));

        UserManagement.DeleteUser("BBBBB");
    }

    @org.junit.jupiter.api.Test
    void userExists() {
        userManagement.CreateUser("AAA", "AAA", "volunteer");

        assertTrue(userManagement.UserExists("AAA"));

        UserManagement.DeleteUser("AAA");
    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
        userManagement.CreateUser("AAA", "AAA", "volunteer");

        assertDoesNotThrow(() -> UserManagement.DeleteUser("AAA"));

        assertFalse(userManagement.UserExists("AAA"));
    }

    @org.junit.jupiter.api.Test
    void getUserType() {
        userManagement.CreateUser("AAA", "AAA", "volunteer");
        userManagement.CreateUser("BBB", "BBB", "user");
        userManagement.CreateUser("CCC", "CCC", "moderator");

        assertEquals("volunteer", userManagement.getUserType("AAA"));
        assertEquals("user", userManagement.getUserType("BBB"));
        assertEquals("moderator", userManagement.getUserType("CCC"));

        UserManagement.DeleteUser("AAA");
        UserManagement.DeleteUser("BBB");
        UserManagement.DeleteUser("CCC");
    }
}
