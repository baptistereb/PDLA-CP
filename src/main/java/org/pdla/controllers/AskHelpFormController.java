package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

public class AskHelpFormController {
    public AskHelpFormController() {
    }

    public boolean sendRequest(String description) {
        //TODO Check if data is well formated
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.createMission(description, UserManagement.getMyID(),"need_help");
        return true;
    }
}
