// File: DetailsGUI.java

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
    // Create labels for the images
    private JLabel img1Lbl = new JLabel("", SwingConstants.CENTER);
    private JLabel arrowLbl = new JLabel(" ==>  ", SwingConstants.CENTER); 
    private JLabel img2Lbl = new JLabel("" , SwingConstants.CENTER);    
     
    public DetailsGUI(Exercise exercise) {
         
        setTitle("Exercise Details");
        setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container pnlMain = this.getContentPane(); 
        
        // 2 rows, 1 column
        pnlMain.setLayout(new GridLayout(2,1)); 
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

        // Load images from URLs
        try {
            
            URL img1URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(0));
            URL img2URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(1));

            // Read images from URLs into BufferedImage
            img1 = ImageIO.read(img1URL);
            img2 = ImageIO.read(img2URL);

            // Create ImageIcons from the buffered images
            ImageIcon icon1 = new ImageIcon(img1);
            ImageIcon icon2 = new ImageIcon(img2);
            
            // Set the icons to the labels
            img1Lbl.setIcon(icon1);
            img2Lbl.setIcon(icon2);

        } catch (Exception e) {
            System.out.println("Error loading images: " + e.getMessage());
        } // end try-catch
    
        // Create a panel for the images
        JPanel pnlImg = new JPanel();
        // 1 row, 3 columns
        pnlImg.setLayout(new GridLayout(1, 3));
        pnlImg.setBorder(BorderFactory.createTitledBorder("Example Images"));

        // To handle window resizing with images
        ResizeImage resizeImg = new ResizeImage(img1Lbl, img2Lbl, img1, img2);
        pnlImg.addComponentListener(resizeImg);

        // Add the images to the image panel
        pnlImg.add(img1Lbl);
        pnlImg.add(arrowLbl);
        pnlImg.add(img2Lbl);

        // Add to the main panel
        pnlMain.add(dataScrollPane);
        pnlMain.add(pnlImg);
        
        this.setVisible(true);
    } // end constructor

} // end class def
