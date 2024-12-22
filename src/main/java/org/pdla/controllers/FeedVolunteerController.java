package org.pdla.controllers;

import org.pdla.models.ConnectionManagement;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import java.util.List;

public class FeedVolunteerController {
    public static List<String> userJoined(int postId, String type) {
        return ConnectionManagement.getUserConnectedToMission(postId, type);
    }

    public void joinMission(String mission_id) {
        ConnectionManagement.joinMission(Integer.parseInt(mission_id), UserManagement.getMyID(), "help");
    }

    public static boolean isItYourMission(int mission_creator_id) {
        return mission_creator_id==UserManagement.getMyID();
    }

    public static void terminateMission(int mission_id) {
        MissionManagement.terminateMission(mission_id);
    }
}
