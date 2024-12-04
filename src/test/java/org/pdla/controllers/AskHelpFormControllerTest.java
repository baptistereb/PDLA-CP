package org.pdla.controllers;


import org.junit.jupiter.api.BeforeAll;
import org.pdla.models.Credentials;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AskHelpFormControllerTest {

    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
    }

    @org.junit.jupiter.api.Test
    void sendRequest() {
        AskHelpFormController askHelpFormController = new AskHelpFormController();
        assertTrue(askHelpFormController.sendRequest("description"));

    }
}