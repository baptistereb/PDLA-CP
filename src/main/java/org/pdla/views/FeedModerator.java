package org.pdla.views;

import org.pdla.controllers.FeedModeratorController;
import org.pdla.models.MissionManagement;
import org.pdla.models.UserManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FeedModerator {
    private JFrame frame;
    private MasterView masterView;

    public FeedModerator(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        MissionManagement missionManagement = new MissionManagement();
        List<List<String>> posts = missionManagement.getMissionsbystate("waiting");

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
            JButton acceptButton = new JButton("Accept");
            FeedModeratorController feedModeratorController = new FeedModeratorController();
            acceptButton.addActionListener(e -> {
                // keskispass quand on clique sur accepter
                feedModeratorController.validateMission(post.get(0));
                masterView.loadWindow("feed_moderator");
            });
            actionPanel.add(acceptButton);

            JTextField actionText = new JTextField();
            actionText.setPreferredSize(new Dimension(200, 30));
            actionPanel.add(actionText);

            JButton refuseButton = new JButton("Refuse");
            refuseButton.addActionListener(e -> {
                // keskispass quand on clique sur refuser
                feedModeratorController.refuseMission(post.get(0), actionText.getText());
                masterView.loadWindow("feed_moderator");
            });
            actionPanel.add(refuseButton);

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

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(logoutPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}