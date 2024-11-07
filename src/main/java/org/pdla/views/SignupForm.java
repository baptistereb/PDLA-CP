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

        JLabel titleLabel = new JLabel("INSCRIPTION", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 0); // Marges entre les composants

        JLabel usernameLabel = new JLabel("Username:");
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(200, 30));

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 30));

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(200, 30));
        comboBox.addItem("user");
        comboBox.addItem("volunteer");
        comboBox.addItem("moderator");

        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(200, 30));

        JButton loginButton = new JButton("go to login page");
        loginButton.setPreferredSize(new Dimension(200, 30));

        JLabel successLabel = new JLabel("User created successfully");
        JLabel failLabel = new JLabel("Failed to create user");
        successLabel.setForeground(Color.GREEN);
        failLabel.setForeground(Color.RED);

        // Ajouter les contraintes en gros
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
        panel.add(comboBox, constraints);
        constraints.gridy = 5;
        panel.add(signupButton, constraints);
        constraints.gridy = 6;
        panel.add(loginButton, constraints);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        frame.setVisible(true);

        loginButton.addActionListener(e -> masterView.loadWindow("login"));

        signupButton.addActionListener(e -> {
            panel.remove(successLabel);
            panel.remove(failLabel);
            panel.revalidate();
            panel.repaint();

            SignupFormController signupFormController = new SignupFormController();
            boolean state = signupFormController.Signup(
                    username.getText(),
                    new String(password.getPassword()),
                    comboBox.getSelectedItem().toString()
            );

            // Affichage du message de succès ou d'échec
            constraints.gridy = 5;
            if (state) {
                panel.add(successLabel, constraints);
            } else {
                panel.add(failLabel, constraints);
            }
            frame.setVisible(true);
        });
    }
}
