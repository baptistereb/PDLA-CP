package org.pdla.views;

import javax.swing.*;
import java.awt.*;

public class MasterView {
    private int width;
    private int height;
    private String title;

    public MasterView( int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(this.width, this.height));
        frame.pack();
        frame.setVisible(true);
    }
}
