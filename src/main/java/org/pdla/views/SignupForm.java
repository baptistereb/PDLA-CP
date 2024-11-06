package org.pdla.views;

import org.pdla.controllers.SignupFormController;
import javax.swing.*;
import java.awt.*;

public class SignupForm {
    private JFrame frame;
    private MasterView masterView;

    public SignupForm(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createForm() {
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

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 30));

        panel.add(username);
        panel.add(password);
        panel.add(comboBox);
        panel.add(signupButton);
        panel.add(loginButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        JLabel success_label = new JLabel("User created successfully");
        JLabel fail_label = new JLabel("Failed to create user");

        loginButton.addActionListener(e -> {masterView.loadWindow("login");});

        signupButton.addActionListener(e -> {
            panel.remove(success_label);
            panel.remove(fail_label);
            panel.revalidate();
            panel.repaint();
            frame.setVisible(true);

            SignupFormController signupFormController = new SignupFormController();
            boolean state =signupFormController.Signup(
                    username.getText(),
                    new String(password.getPassword()),
                    comboBox.getSelectedItem().toString()
            );
            if (state) {
                panel.add(success_label);
                frame.setVisible(true);
            }
            else{
                panel.add(fail_label);
                frame.setVisible(true);
            }
        });
    }
}
