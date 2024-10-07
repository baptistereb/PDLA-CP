package org.pdla;

import static org.junit.jupiter.api.Assertions.*;

class UserManagementTest {

    @org.junit.jupiter.api.Test
    void createUser() {
        UserManagement userManagement = new UserManagement();
        assertDoesNotThrow(() -> userManagement.CreateUser("AAA", "AAA", "volunteer"));
        assertDoesNotThrow(() -> userManagement.CreateUser("BBB", "BBB", "user"));
        assertDoesNotThrow(() -> userManagement.CreateUser("CCC", "CCC", "moderator"));
    }

    @org.junit.jupiter.api.Test
    void deleteUser(){
        UserManagement userManagement = new UserManagement();
        assertDoesNotThrow(() -> userManagement.DeleteUser("AAA"));
        assertDoesNotThrow(() -> userManagement.DeleteUser("BBB"));
        assertDoesNotThrow(() -> userManagement.DeleteUser("CCC"));
    }
}