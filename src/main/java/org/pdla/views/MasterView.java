package org.pdla.views;

import javax.swing.*;
import java.awt.*;

public class MasterView {
    private int width;
    private int height;
    private JFrame frame = new JFrame("Default");
    private LoginForm loginForm;
    private SignupForm signupForm;
    private FeedVolunteer feed_volunteer;
    private FeedUser feed_user;
    private FeedModerator feed_moderator;
    private AskHelpForm ask_help_moderator;


    public MasterView( int width, int height, String title) {
        this.width = width;
        this.height = height;

        loginForm = new LoginForm(this.frame, this);
        signupForm = new SignupForm(this.frame, this);
        feed_volunteer = new FeedVolunteer(this.frame, this);
        feed_user = new FeedUser(this.frame, this);
        feed_moderator = new FeedModerator(this.frame, this);
        ask_help_moderator = new AskHelpForm(this.frame, this);

        createWindow();
        setTitle(title);

        loadWindow("login");
    }

    private void createWindow() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(this.width, this.height));
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void setTitle(String name) {
        this.frame.setTitle(name);
    }

    public void loadWindow(String window) {
        frame.getContentPane().removeAll(); // Supprime tout le contenu de la fenêtre
        frame.revalidate();                 // Redessine la fenêtre pour appliquer les changements
        frame.repaint();                    // Rafraîchit l'affichage de la fenêtre
        if(window.equals("login")) {
            loginForm.createForm();
        } else if(window.equals("signup")) {
            signupForm.createForm();
        } else if(window.equals("feed_volunteer")) {
            feed_volunteer.createPage();
        } else if(window.equals("feed_user")) {
            feed_user.createPage();
        } else if(window.equals("feed_moderator")) {
            feed_moderator.createPage();
        } else if(window.equals("ask_help")) {
            ask_help_moderator.createForm();
        }
    }
}
