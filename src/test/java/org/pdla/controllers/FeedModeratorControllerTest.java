package org.pdla.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.pdla.models.Credentials;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import static org.junit.jupiter.api.Assertions.*;

class FeedModeratorControllerTest {


    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
        SignupFormController signupFormController = new SignupFormController();
        LoginFormController loginFormController = new LoginFormController();
        signupFormController.Signup("test", "test", "moderator");
        loginFormController.Login("test", "test");
    }

    @AfterAll
    static void clear() {
        UserManagement.DeleteUser("test");
    }

    @org.junit.jupiter.api.Test
    void validateMission() {
       FeedModeratorController feedModeratorController = new FeedModeratorController();
        MissionManagement.createMission("test", UserManagement.getMyID(), "need_help");
        feedModeratorController.validateMission(MissionManagement.getLastCreatedMissionId());

        assertEquals("valid", MissionManagement.getMissionState(MissionManagement.getLastCreatedMissionId()));
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId());
    }

    @org.junit.jupiter.api.Test
    void refuseMission() {
        FeedModeratorController feedModeratorController = new FeedModeratorController();
        MissionManagement.createMission("test", UserManagement.getMyID(), "need_help");
        feedModeratorController.refuseMission(MissionManagement.getLastCreatedMissionId(), "test");

        assertEquals("refused", MissionManagement.getMissionState(MissionManagement.getLastCreatedMissionId()));
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId());
    }
}