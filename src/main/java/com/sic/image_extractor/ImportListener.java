package com.sic.image_extractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by seehait on 12/8/16.
 */
public class ImportListener implements ActionListener {
    private Window parent;

    public ImportListener(Window parent) {
        super();
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                boolean isAccept = false;
                String fileName = f.getName();
                String fileExtension = "";

                if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                    fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

                    if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("png")) {
                        isAccept = true;
                    }
                }

                return isAccept;
            }

            @Override
            public String getDescription() {
                return "JPEG or PNG Image only";
            }
        });
        int returnValue = fileChooser.showOpenDialog(parent);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            BufferedImage image = null;
            try {
                image = ImageIO.read(selectedFile);
                parent.setImage(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
