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
    private JLabel img1Lbl = new JLabel("", SwingConstants.CENTER);
    private JLabel arrowLbl = new JLabel(" ==>  ", SwingConstants.CENTER); 
    private JLabel img2Lbl = new JLabel("" , SwingConstants.CENTER);
    private Exercise exercise;   
     
    public DetailsGUI(Exercise exercise) {
        init(exercise);
    } // end constructor

    public void init(Exercise exercise) {
        this.exercise = exercise; // Set the exercise object
         
        setTitle("Exercise Details");
        setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container pnlMain = this.getContentPane(); 
        
        // 2 rows, 1 column
        pnlMain.setLayout(new GridLayout(2,1)); 
        pnlMain.setBackground(Color.LIGHT_GRAY);

        // Creat a JTextArea for the exercise data
        JTextArea exerciseData = new JTextArea();
        exerciseData.setLineWrap(true);
        exerciseData.setWrapStyleWord(true);
        exerciseData.setEditable(false);
        exerciseData.setText(
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


        // Create a scroll pane for the data panel
        JScrollPane dataScrollPane = new JScrollPane(exerciseData);
        dataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dataScrollPane.getVerticalScrollBar().setUnitIncrement(8);

        // Set the images using the method
        setImages();
    
        // Create a 1 row, 2 col panel for the images
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new GridLayout(1, 3));
        imgPanel.setBorder(BorderFactory.createTitledBorder("Example Images"));

        // To handle window resizing with images
        ResizeImage resizeImg = new ResizeImage(img1Lbl, img2Lbl, img1, img2);
        imgPanel.addComponentListener(resizeImg);

        // Add the images to the image panel
        imgPanel.add(img1Lbl);
        imgPanel.add(arrowLbl);
        imgPanel.add(img2Lbl);

        // Add to the main panel
        pnlMain.add(dataScrollPane);
        pnlMain.add(imgPanel);
        
        this.setVisible(true);
    } // end init

    public void setImages(){

        // Load and set images from URLs
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
    }
} // end class def
