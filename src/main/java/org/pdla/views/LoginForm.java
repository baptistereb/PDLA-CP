package org.pdla.views;
import org.pdla.controllers.LoginFormController;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JFrame frame;
    private MasterView masterView;

    public LoginForm(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createForm() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("CONNECTION", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 0);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(200, 30));

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 30));

        JButton signupButton = new JButton("go to signup page");
        signupButton.setPreferredSize(new Dimension(200, 30));

        JLabel failedLabel = new JLabel("Identifiant ou mot de passe incorrecte");
        failedLabel.setForeground(Color.RED);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
        constraints.gridy = 1;
        panel.add(username, constraints);
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);
        constraints.gridy = 3;
        panel.add(password, constraints);
        constraints.gridy = 4;
        panel.add(loginButton, constraints);
        constraints.gridy = 5;
        panel.add(signupButton, constraints);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        frame.setVisible(true);

        signupButton.addActionListener(e -> masterView.loadWindow("signup"));

        loginButton.addActionListener(e -> {
            panel.remove(failedLabel);
            panel.revalidate();
            panel.repaint();

            LoginFormController loginFormController = new LoginFormController();
            boolean logged = loginFormController.Login(
                    username.getText(),
                    new String(password.getPassword())
            );

            if (logged) {
                masterView.loadWindow("feed");
            } else {
                constraints.gridy = 6;
                panel.add(failedLabel, constraints);
                frame.setVisible(true);
            }
        });
    }
}
