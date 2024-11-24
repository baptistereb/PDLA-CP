package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

public class AskHelpFormController {
    public AskHelpFormController() {
    }

    public boolean sendRequest(String description) {
        UserManagement userManagement = new UserManagement();
        //TODO Check if data is well formated
        MissionManagement missionManagement = new MissionManagement();
        MissionManagement.createMission(description, userManagement.getMyID(),"need_help");
        return true;
    }
}
