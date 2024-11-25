package org.pdla.views;

import org.pdla.controllers.FeedUserController;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FeedUser {
    private JFrame frame;
    private MasterView masterView;

    public FeedUser(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        MissionManagement missionManagement = new MissionManagement();
        List<List<String>> posts = missionManagement.getMissionsbystate("valid");

        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));

        for (List<String> post : posts) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BorderLayout());
            postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Name : " + UserManagement.getPseudo(Integer.parseInt(post.get(2))) + " ; type : "+ post.get(4));
            JTextArea postText = new JTextArea(post.get(1));
            postText.setLineWrap(true);
            postText.setWrapStyleWord(true);
            postText.setEditable(false);

            JPanel actionPanel = new JPanel();
            JButton acceptButton = new JButton("Join");
            FeedUserController feedUserController = new FeedUserController();
            acceptButton.addActionListener(e -> {
                // keskispass quand on clique sur accepter
                feedUserController.joinMission(post.get(0));
                masterView.loadWindow("feed_user");
            });
            actionPanel.add(acceptButton);

            postPanel.add(nameLabel, BorderLayout.NORTH);
            postPanel.add(new JScrollPane(postText), BorderLayout.CENTER);
            postPanel.add(actionPanel, BorderLayout.SOUTH);

            feedPanel.add(postPanel);
            feedPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement entre les posts
        }

        // Ajouter le feedPanel dans un JScrollPane pour la barre de défilement
        JScrollPane scrollPane = new JScrollPane(feedPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel logoutPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(200, 30));
        logoutButton.addActionListener(e -> masterView.loadWindow("login"));
        logoutPanel.add(logoutButton);


        JButton askButton = new JButton("Ask some help");
        askButton.setPreferredSize(new Dimension(200, 30));
        askButton.addActionListener(e -> masterView.loadWindow("ask_help"));
        logoutPanel.add(askButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(logoutPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}