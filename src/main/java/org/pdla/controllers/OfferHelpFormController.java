package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

public class OfferHelpFormController {
    public OfferHelpFormController() {
    }

    public boolean sendOffer(String description) {
        //TODO Check if data is well formated
        MissionManagement.createMission(description, UserManagement.getMyID(),"help");
        return true;
    }
}
