package org.pdla.views;

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

        List<List<String>> posts = Arrays.asList(
                Arrays.asList("User", "Voici le texte de la première publication."),
                Arrays.asList("Claire Martin", "Texte de la seconde publication, avec plus de détails."),
                Arrays.asList("Claire Martin", "Texte de la seconde publication, avec plus de détails."),
                Arrays.asList("Claire Martin", "Texte de la seconde publication, avec plus de détails."),
                Arrays.asList("Claire Martin", "Texte de la seconde publication, avec plus de détails."),
                Arrays.asList("Claire Martin", "Texte de la seconde publication, avec plus de détails."),
                Arrays.asList("Claire Martin", "Texte de la seconde publication, avec plus de détails."),
                Arrays.asList("Paul Durand", "Encore une autre publication à afficher dans le feed.")
        );

        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));

        for (List<String> post : posts) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BorderLayout());
            postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Nom : " + post.get(0));
            JTextArea postText = new JTextArea(post.get(1));
            postText.setLineWrap(true);
            postText.setWrapStyleWord(true);
            postText.setEditable(false);

            JButton joinButton = new JButton("Rejoindre");
            joinButton.addActionListener(e -> {
                // keskispass quand on clique sur rejoindre
            });

            postPanel.add(nameLabel, BorderLayout.NORTH);
            postPanel.add(new JScrollPane(postText), BorderLayout.CENTER);
            postPanel.add(joinButton, BorderLayout.SOUTH);

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