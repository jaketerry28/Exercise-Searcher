// ResizeImage.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class ResizeImage extends ComponentAdapter {
    private JLabel img1Lbl;
    private JLabel img2Lbl;
    private Image img1;
    private Image img2;

    public ResizeImage(JLabel img1Lbl, JLabel img2Lbl, Image img1, Image img2) {
        this.img1Lbl = img1Lbl;
        this.img2Lbl = img2Lbl;
        this.img1 = img1;
        this.img2 = img2;
    } // end constructor

    // When the window is resized call resizeImages
    @Override
    public void componentResized(ComponentEvent e) {
        resize();
    } // end componentResized

    public void resize() {
        int labelWidth1 = img1Lbl.getWidth();
        int labelHeigth1 = img1Lbl.getHeight();
        int labelWidth2 = img2Lbl.getWidth();
        int labelHeight2 = img2Lbl.getHeight();

        Image scaledImage1 = scaleImage(img1, labelWidth1, labelHeigth1);
        Image scaledImage2 = scaleImage(img2, labelWidth2, labelHeight2);

        ImageIcon icon1 = new ImageIcon(scaledImage1);
        ImageIcon icon2 = new ImageIcon(scaledImage2);

        img1Lbl.setIcon(icon1);
        img2Lbl.setIcon(icon2);
    } // end resizeImages

    public Image scaleImage(Image originalImage, int labelWidth, int labelHeigth) {
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        double widthRatio = (double) labelWidth / originalWidth;
        double heightRatio = (double) labelHeigth / originalHeight;
        double ratio = Math.min(widthRatio, heightRatio); // pick the smaller ratio

        int newWidth = (int) (originalWidth * ratio); // convert double to int
        int newHeight = (int) (originalHeight * ratio); // convert double to int

        Image newImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return newImage;
    } // end scaleImage

} // end class ResizeImage