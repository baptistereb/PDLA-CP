package org.pdla.controllers;

import org.pdla.models.MissionManagement;

public class FeedModeratorController {
    public void validateMission(String s) {
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.validateMission(Integer.parseInt(s));
    }

    public void refuseMission(String s, String reason) {
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.refuseMission(Integer.parseInt(s), reason);
    }
}
