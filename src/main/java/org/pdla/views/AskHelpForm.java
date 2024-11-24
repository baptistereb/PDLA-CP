package org.pdla.views;

import org.pdla.controllers.AskHelpFormController;

import javax.swing.*;
import java.awt.*;

public class AskHelpForm {
    private JFrame frame;
    private MasterView masterView;

    public AskHelpForm(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createForm() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Ask some help", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 0); // Marges entre les composants

        JLabel asklabel = new JLabel("Describe your request:");
        JTextField ask = new JTextField();
        ask.setPreferredSize(new Dimension(200, 30));

        JLabel successLabel = new JLabel("Request created successfully");
        JLabel failLabel = new JLabel("Failed to create the request");
        successLabel.setForeground(Color.GREEN);
        failLabel.setForeground(Color.RED);


        JButton sendbutton = new JButton("Send your request");
        sendbutton.setPreferredSize(new Dimension(200, 30));

        JButton backbutton = new JButton("go back");
        backbutton.setPreferredSize(new Dimension(200, 30));

        // Ajouter les contraintes en gros
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(asklabel, constraints);
        constraints.gridy = 1;
        panel.add(ask, constraints);
        constraints.gridy = 2;
        panel.add(sendbutton, constraints);
        constraints.gridy = 3;
        panel.add(backbutton, constraints);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        frame.setVisible(true);

        backbutton.addActionListener(e -> masterView.loadWindow("feed_user"));

        sendbutton.addActionListener(e -> {
            panel.remove(successLabel);
            panel.remove(failLabel);
            panel.revalidate();
            panel.repaint();

            AskHelpFormController askHelpFormController = new AskHelpFormController();
            boolean state = askHelpFormController.sendRequest(
                    3, //some user_id
                    ask.getText()
            );

            // Affichage du message de succès ou d'échec
            constraints.gridy = 4;
            if (state) {
                panel.add(successLabel, constraints);
            } else {
                panel.add(failLabel, constraints);
            }
            frame.setVisible(true);
        });
    }
}
