package org.pdla.views;
import org.pdla.controllers.LoginFormController;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JFrame frame;

    public LoginForm(JFrame frame) {
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

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 30));

        JLabel logged_label = new JLabel("Vous êtes connecté");
        JLabel failed_label = new JLabel("Identifiant ou mot de passe incorrecte");

        panel.add(username);
        panel.add(password);
        panel.add(loginButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            panel.remove(logged_label);
            panel.remove(failed_label);

            // actualiser la vue
            panel.revalidate();
            panel.repaint();
            frame.setVisible(true);

            LoginFormController loginFormController = new LoginFormController();
            boolean loged = loginFormController.Login(
                username.getText(),
                new String(password.getPassword())
            );

            if(loged) {
                // on lance une autre fenetre
                frame.dispose();                      // on ferme cette fenêtre du coup
            } else {
                panel.add(failed_label);
                frame.setVisible(true);
            }
        });
    }
}
