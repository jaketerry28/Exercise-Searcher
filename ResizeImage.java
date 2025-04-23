// ResizeImage.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class ResizeImage extends ComponentAdapter {
    private JLabel img1lbl;
    private JLabel img2lbl;
    private Image img1;
    private Image img2;

    public ResizeImage(JLabel img1lbl, JLabel img2lbl, Image img1, Image img2) {
        this.img1lbl = img1lbl;
        this.img2lbl = img2lbl;
        this.img1 = img1;
        this.img2 = img2;
    } // end constructor

    private void resizeImages() {
        int width1 = img1lbl.getWidth();
        int height1 = img1lbl.getHeight();
        int width2 = img2lbl.getWidth();
        int height2 = img2lbl.getHeight();

        Image scaledImage1 = scaleImage(img1, width1, height1);
        Image scaledImage2 = scaleImage(img2, width2, height2);

        img1lbl.setIcon(new ImageIcon(scaledImage1));
        img2lbl.setIcon(new ImageIcon(scaledImage2));
    } // end resizeImages

    private Image scaleImage(Image originalImage, int maxWidth, int maxHeight) {
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;
        double ratio = Math.min(widthRatio, heightRatio); // pick the smaller ratio

        int newWidth = (int) (originalWidth * ratio); // convert double to int
        int newHeight = (int) (originalHeight * ratio); // convert double to int

        Image newImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return newImage;
    } // end scaleImage

    // When the window is resized call resizeImages
    @Override
    public void componentResized(ComponentEvent e) {
        resizeImages();
    } // end componentResized

} // end class ResizeImage