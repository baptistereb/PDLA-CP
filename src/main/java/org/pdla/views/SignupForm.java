package org.pdla.views;
import org.pdla.controllers.SignupFormController;

import javax.swing.*;
import java.awt.*;

public class SignupForm {
    private JFrame frame;

    public SignupForm(JFrame frame) {
        this.frame = frame;
        createForm();
    }

    private void createForm() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(200, 30));

        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 30));

        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(200, 30));

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(200, 30));
        comboBox.addItem("user");
        comboBox.addItem("volunteer");
        comboBox.addItem("moderator");

        panel.add(username);
        panel.add(password);
        panel.add(comboBox);
        panel.add(signupButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        signupButton.addActionListener(e -> {
            SignupFormController signupFormController = new SignupFormController();
            signupFormController.Signup(
                    username.getText(),
                    new String(password.getPassword()), // Récupérer le mot de passe depuis JPasswordField
                    comboBox.getSelectedItem().toString()
            );
        });
    }
    }

