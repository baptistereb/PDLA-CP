package org.pdla.views;

import javax.swing.*;
import java.awt.*;

public class MasterView {
    private int width;
    private int height;
    private JFrame frame = new JFrame("Default");

    public MasterView( int width, int height, String title) {
        this.width = width;
        this.height = height;
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
        if(window.equals("login")) {
            LoginForm loginForm = new LoginForm(this.frame);
        } else if(window.equals("signup")) {
            SignupForm signupForm = new SignupForm(this.frame);
        }
    }
}
