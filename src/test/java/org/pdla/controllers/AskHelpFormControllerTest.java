package org.pdla.controllers;


import org.pdla.models.Credentials;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AskHelpFormControllerTest {

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