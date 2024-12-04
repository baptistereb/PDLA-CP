package org.pdla.views;

import org.pdla.controllers.AskHelpFormController;
import org.pdla.controllers.OfferHelpFormController;

import javax.swing.*;
import java.awt.*;

public class OfferHelpForm {
    private JFrame frame;
    private MasterView masterView;

    public OfferHelpForm(JFrame frame, MasterView mv) {
        this.frame = frame;
        this.masterView = mv;
    }

    public void createForm() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Offer some help", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 0); // Marges entre les composants

        JLabel asklabel = new JLabel("Describe your Offer:");
        JTextField offer = new JTextField();
        offer.setPreferredSize(new Dimension(200, 30));

        JLabel successLabel = new JLabel("Offer created successfully");
        JLabel failLabel = new JLabel("Failed to create the offer");
        successLabel.setForeground(Color.GREEN);
        failLabel.setForeground(Color.RED);


        JButton sendbutton = new JButton("Send your offer");
        sendbutton.setPreferredSize(new Dimension(200, 30));

        JButton backbutton = new JButton("go back");
        backbutton.setPreferredSize(new Dimension(200, 30));

        // Ajouter les contraintes en gros
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(asklabel, constraints);
        constraints.gridy = 1;
        panel.add(offer, constraints);
        constraints.gridy = 2;
        panel.add(sendbutton, constraints);
        constraints.gridy = 3;
        panel.add(backbutton, constraints);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        frame.setVisible(true);

        backbutton.addActionListener(e -> masterView.loadWindow("feed_volunteer"));

        sendbutton.addActionListener(e -> {
            panel.remove(successLabel);
            panel.remove(failLabel);
            panel.revalidate();
            panel.repaint();

            OfferHelpFormController offerHelpFormController = new OfferHelpFormController();
            boolean state = offerHelpFormController.sendOffer(
                    offer.getText()
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
