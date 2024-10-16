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

        SignupForm signupForm = new SignupForm(this.frame);
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
}
