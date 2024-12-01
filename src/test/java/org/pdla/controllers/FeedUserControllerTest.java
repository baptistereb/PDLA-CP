package org.pdla.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.pdla.models.ConnectionManagement;
import org.pdla.models.Credentials;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import static org.junit.jupiter.api.Assertions.*;


class FeedUserControllerTest {

    @BeforeAll
    static void setup()
    {
        Credentials.SetTestCredentials();
        SignupFormController signupFormController = new SignupFormController();
        LoginFormController loginFormController = new LoginFormController();
        signupFormController.Signup("test", "test", "moderator");
        loginFormController.Login("test", "test");
    }

    @org.junit.jupiter.api.Test
    void userJoined() {

    }

    @org.junit.jupiter.api.Test
    void joinMission() {
       FeedUserController feedusercontroller = new FeedUserController();
        MissionManagement.createMission("test", UserManagement.getMyID(), "need_help");
        feedusercontroller.joinMission(Integer.toString(MissionManagement.getLastCreatedMissionId()));
        assertTrue(MissionManagement.isUserInMission(MissionManagement.getLastCreatedMissionId(), UserManagement.getMyID()));
        ConnectionManagement.DeleteConnection(ConnectionManagement.getLastCreatedConnection());
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId());
    }

    @org.junit.jupiter.api.Test
    void terminateMission() {
        MissionManagement missionManagement = new MissionManagement();
        FeedUserController feedusercontroller = new FeedUserController();
        MissionManagement.createMission("test", UserManagement.getMyID(), "need_help");
        feedusercontroller.joinMission(Integer.toString(MissionManagement.getLastCreatedMissionId()));
        FeedUserController.terminateMission(MissionManagement.getLastCreatedMissionId());
        assertEquals("realized", missionManagement.getMissionState(MissionManagement.getLastCreatedMissionId()));
        ConnectionManagement.DeleteConnection(ConnectionManagement.getLastCreatedConnection());
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId());
    }
}