package com.sic.image_extractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by seehait on 12/8/16.
 */
public class Window extends JFrame {
    private JButton exportButton;
    private JLabel exportDirectoryLabel;
    private JPanel ioPanel;
    private JButton importButton;
    private BufferedImage image;
    private ImagePanel imagePanel;

    private int mouseX;
    private int mouseY;

    private File exportDirectory;

    public static final int CAPTURE_SIZE = 16;

    /**
     * Constructs an Instance.
     */
    public Window() {
        super("Image Extractor");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.ioPanel = new JPanel();
        this.ioPanel.setLayout(new FlowLayout());
        this.getContentPane().add(this.ioPanel, BorderLayout.NORTH);

        this.importButton = new JButton("Select Image");
        this.importButton.addActionListener(new ImportListener(this));
        this.ioPanel.add(importButton);

        this.exportButton = new JButton("Select Export Directory");
        this.exportButton.addActionListener(new ExportListener(this));
        this.ioPanel.add(exportButton);

        this.exportDirectoryLabel = new JLabel("Export Directory: ");
        this.ioPanel.add(this.exportDirectoryLabel);

        this.imagePanel = new ImagePanel(this);
        imagePanel.setPreferredSize(new Dimension(800, 600));
        imagePanel.repaint();
        this.getContentPane().add(imagePanel, BorderLayout.CENTER);

        this.imagePanel.addMouseListener(new ImageMouseListener(this));
        this.imagePanel.addMouseMotionListener(new ImageMouseMotionListener(this));

        this.pack();
    }

    public void capture() {
        int boundary = Window.CAPTURE_SIZE / 2;

        if (this.getImage() != null && this.getExportDirectory() != null && this.getMouseX() >= boundary && this.getMouseY() >= boundary && this.getMouseX() <= this.getImage().getWidth() - boundary && this.getMouseY() <= this.getImage().getHeight() - boundary) {
            BufferedImage subImage = this.image.getSubimage(this.getMouseX() - boundary, this.getMouseY() - boundary, Window.CAPTURE_SIZE, Window.CAPTURE_SIZE);

            try {
                File outputFile = File.createTempFile("sic", ".jpg", this.exportDirectory);
                ImageIO.write(subImage, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        this.imagePanel.repaint();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.imagePanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.imagePanel.repaint();
        this.pack();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
        this.imagePanel.repaint();
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
        this.imagePanel.repaint();
    }

    public File getExportDirectory() {
        return exportDirectory;
    }

    public void setExportDirectory(File exportDirectory) {
        this.exportDirectory = exportDirectory;
        this.exportDirectoryLabel.setText("Export Directory: " + this.getExportDirectory().getPath());
    }
}
