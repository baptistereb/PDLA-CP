package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

public class FeedUserController {
    public void joinMission(String mission_id) {
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.joinMission(Integer.parseInt(mission_id), UserManagement.getMyID(), "need_help");
    }
}
