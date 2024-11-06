package org.pdla.views;
import org.pdla.controllers.LoginFormController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Feed {
    private JFrame frame;
    private MasterView masterView;

    public Feed(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createPage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(200, 30));

        panel.add(logoutButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        logoutButton.addActionListener(e -> {masterView.loadWindow("signup");});
    }
}
