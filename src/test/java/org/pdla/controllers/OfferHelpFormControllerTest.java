package org.pdla.controllers;


import org.junit.jupiter.api.BeforeAll;
import org.pdla.models.Credentials;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OfferHelpFormControllerTest {

    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
    }

    @org.junit.jupiter.api.Test
    void sendRequest() {
        UserManagement userManagement = new UserManagement();
        userManagement.CreateUser("AAA", "AAA", "user");
        userManagement.Login("AAA", "AAA"); // pour set le uuid sinon on peut pas envoyer de request

        OfferHelpFormController offerHelpFormController = new OfferHelpFormController();
        assertTrue(offerHelpFormController.sendOffer("AAAAAAA"));
        MissionManagement.deleteMission(MissionManagement.getID("AAAAAAA"));
    }
}