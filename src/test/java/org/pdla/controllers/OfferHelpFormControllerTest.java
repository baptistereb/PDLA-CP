package org.pdla.controllers;


import org.junit.jupiter.api.BeforeAll;
import org.pdla.models.Credentials;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OfferHelpFormControllerTest {

    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
    }

    @org.junit.jupiter.api.Test
    void sendRequest() {
        OfferHelpFormController offerHelpFormController = new OfferHelpFormController();
        assertTrue(offerHelpFormController.sendOffer("description"));
    }
}