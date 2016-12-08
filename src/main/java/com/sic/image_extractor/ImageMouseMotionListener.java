package com.sic.image_extractor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by seehait on 12/8/16.
 */
public class ImageMouseMotionListener implements MouseMotionListener {
    private Window parent;

    public ImageMouseMotionListener(Window parent) {
        this.parent = parent;
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        parent.setMouseX(e.getX());
        parent.setMouseY(e.getY());
    }
}
