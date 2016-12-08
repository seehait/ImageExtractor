package com.sic.image_extractor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by seehait on 12/8/16.
 */
public class ImagePanel extends JPanel {
    private Window parent;

    public ImagePanel(Window parent) {
        this.parent = parent;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        if (parent.getImage() != null) {
            BufferedImage image = parent.getImage();
            g.drawImage(image, 0, 0, Color.black, null);
        }

        g.setColor(Color.red);
        int boundary = Window.CAPTURE_SIZE / 2;
        g.drawRect(parent.getMouseX() - boundary, parent.getMouseY() - boundary, Window.CAPTURE_SIZE, Window.CAPTURE_SIZE);
    }
}
