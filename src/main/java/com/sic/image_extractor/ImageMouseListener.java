package com.sic.image_extractor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by seehait on 12/8/16.
 */
public class ImageMouseListener implements MouseListener {
    private Window parent;

    public ImageMouseListener(Window parent) {
        this.parent = parent;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        parent.capture();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
