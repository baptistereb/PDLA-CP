package org.pdla.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.pdla.models.ConnectionManagement;
import org.pdla.models.Credentials;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import static org.junit.jupiter.api.Assertions.*;


class FeedVolunteerControllerTest {

    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
        SignupFormController signupFormController = new SignupFormController();
        LoginFormController loginFormController = new LoginFormController();
        signupFormController.Signup("test", "test", "volunteer");
        loginFormController.Login("test", "test");
    }

    @org.junit.jupiter.api.Test
    void joinMission() {
        FeedVolunteerController feedvolunteercontroller = new FeedVolunteerController();
        MissionManagement.createMission("test", UserManagement.getMyID(), "help");
        feedvolunteercontroller.joinMission(Integer.toString(MissionManagement.getLastCreatedMissionId()));
        assertTrue(ConnectionManagement.isUserInMission(MissionManagement.getLastCreatedMissionId(), UserManagement.getMyID()));
        ConnectionManagement.DeleteConnection(ConnectionManagement.getLastCreatedConnection());
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId());
    }

    @org.junit.jupiter.api.Test
    void terminateMission() {
        MissionManagement missionManagement = new MissionManagement();
        FeedVolunteerController feedvolunteercontroller = new FeedVolunteerController();
        MissionManagement.createMission("test", UserManagement.getMyID(), "help");
        feedvolunteercontroller.joinMission(Integer.toString(MissionManagement.getLastCreatedMissionId()));
        FeedUserController.terminateMission(MissionManagement.getLastCreatedMissionId());
        assertEquals("realized", missionManagement.getMissionState(MissionManagement.getLastCreatedMissionId()));
        ConnectionManagement.DeleteConnection(ConnectionManagement.getLastCreatedConnection());
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId());
    }
}