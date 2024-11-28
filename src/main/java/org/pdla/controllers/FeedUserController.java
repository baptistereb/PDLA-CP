package org.pdla.controllers;

import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import java.util.Arrays;
import java.util.List;

public class FeedUserController {
    public static List<String> userJoined(int postId) {
        return MissionManagement.getUserConnectedToMission(postId);
    }

    public void joinMission(String mission_id) {
        MissionManagement missionManagement = new MissionManagement();
        missionManagement.joinMission(Integer.parseInt(mission_id), UserManagement.getMyID(), "need_help");
    }
}
