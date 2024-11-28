package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

public class FeedModeratorController {
    public void validateMission(int s) {
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.validateMission(s);
    }

    public void refuseMission(int s, String reason) {
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.refuseMission(s, reason);
    }
}
