package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

public class OfferHelpFormController {
    public OfferHelpFormController() {
    }

    public boolean sendOffer(String description) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        MissionManagement missionManagement = new MissionManagement();
        MissionManagement.createMission(description, userManagement.getMyID(),"help");
        return true;
    }
}
