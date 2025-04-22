// File: DetailsGUI.java

// This program demonstrates the use of BoxLayout
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import java.io.*;
import javax.imageio.ImageIO;
 
public class DetailsGUI extends JFrame {

    private BufferedImage img1;
    private BufferedImage img2;
     
    public DetailsGUI(Exercise exercise) {
         
        setTitle("Exercise Details");
        setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container pnlMain = this.getContentPane(); 
        
        pnlMain.setLayout(new GridLayout(2,1)); // 2 row, 1 column
        pnlMain.setBackground(Color.LIGHT_GRAY);

        // Create a panel for the data
        JPanel pnlData = new JPanel();
        pnlData.setLayout(new BoxLayout(pnlData, BoxLayout.PAGE_AXIS));

        JTextArea txtData = new JTextArea();
        txtData.setLineWrap(true);
        txtData.setWrapStyleWord(true);
        txtData.setEditable(false);
        txtData.setText(
                "NAME: " + exercise.getName() + "\n\n" +
                "FORCE: " + exercise.getForce() + "\n\n" +
                "LEVEL: " + exercise.getLevel() + "\n\n" +
                "MECHANIC: " + exercise.getMechanic() + "\n\n" +
                "EQUIPMENT: " + exercise.getEquipment() + "\n\n" +
                "CATEGORY: " + exercise.getCategory() + "\n\n" +
                "PRIMARY MUSCLES: " + String.join(", ", exercise.getPrimaryMuscles()) + "\n\n" +
                "SECONDARY MUSCLES: " + String.join(", ", exercise.getSecondaryMuscles()) + "\n\n" +
                "INSTRUCTIONS: " + String.join(" ", exercise.getInstructions())
        );

        // Add the labels to the data panel
        pnlData.add(txtData);

        // Create a scroll pane for the data panel
        JScrollPane dataScrollPane = new JScrollPane(pnlData);
        dataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dataScrollPane.getVerticalScrollBar().setUnitIncrement(8);
        dataScrollPane.getHorizontalScrollBar().setUnitIncrement(8);

        // Create labels for the images
        JLabel img1Lbl = new JLabel("", SwingConstants.CENTER);
        JLabel arrowLbl = new JLabel(" ==>  ", SwingConstants.CENTER); 
        JLabel img2Lbl = new JLabel("" , SwingConstants.CENTER);


        try {
            // Load images from URLs
            URL img1URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(0));
            URL img2URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(1));

            // Read images from URLs into BufferedImage
            img1 = ImageIO.read(img1URL);
            img2 = ImageIO.read(img2URL);

            // Initial scaled images 
            Image scaledImg1 = img1.getScaledInstance(-1, -1, Image.SCALE_SMOOTH);
            Image scaledImg2 = img2.getScaledInstance(-1, -1, Image.SCALE_SMOOTH);

            // Create ImageIcons from the scaled images
            ImageIcon icon1 = new ImageIcon(scaledImg1);
            ImageIcon icon2 = new ImageIcon(scaledImg2);
            
            // Set the icons to the labels
            img1Lbl.setIcon(icon1);
            img2Lbl.setIcon(icon2);

        } catch (Exception e) {
            System.out.println("Error loading images: " + e.getMessage());
        } // end try-catch

        // Create a panel for the images
        JPanel pnlImg = new JPanel();
        pnlImg.setLayout(new GridLayout(1, 2)); // 1 row, 2 columns
        pnlImg.setBorder(BorderFactory.createTitledBorder("Example Images"));

        // Add listener for resizing events
        pnlImg.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int widht1 = img1Lbl.getWidth();
                int height1 = img1Lbl.getHeight();
                int widht2 = img2Lbl.getWidth();
                int height2 = img2Lbl.getHeight();

                Image scaledImage1 = scaleImage(img1, widht1, height1);
                Image scaledImage2 = scaleImage(img2, widht2, height2);
        
                img1Lbl.setIcon(new ImageIcon(scaledImage1));
                img2Lbl.setIcon(new ImageIcon(scaledImage2));

               System.out.println("Resizing images...");
            }
        });

        // Add the images to the image panel
        pnlImg.add(img1Lbl);
        pnlImg.add(arrowLbl);
        pnlImg.add(img2Lbl);

        // Add to the main panel
        pnlMain.add(dataScrollPane);
        pnlMain.add(pnlImg);
        
        this.setVisible(true);
;
    } // end constructor
    
    // Method to scale image and maintain aspect ratio
    private Image scaleImage(BufferedImage originalImage, int maxWidth, int maxHeight) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;
        double ratio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (originalWidth * ratio);
        int newHeight = (int) (originalHeight * ratio);

        return originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    } // end scaleImage

} // end class def
