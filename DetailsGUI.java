// File: DetailsGUI.java

// This program demonstrates the use of BoxLayout
import java.awt.*;
import javax.swing.*;
import java.net.URL;
 
public class DetailsGUI extends JFrame {
     
    public DetailsGUI(Exercise exercise) {
         
        setTitle("Box Layout Example");
        setSize(600, 400);
        Container pnlMain = this.getContentPane(); 
        
        pnlMain.setLayout(new BorderLayout());
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

        // Create labels for the images
        JLabel img1Lbl = new JLabel();
        JLabel img2Lbl = new JLabel();

        try {
            URL img1URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(0));
            URL img2URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(1));
            ImageIcon img1 = new ImageIcon(img1URL);
            ImageIcon img2 = new ImageIcon(img2URL);
            img1Lbl.setIcon(img1);
            img2Lbl.setIcon(img2);

        } catch (Exception e) {
            System.out.println("Error loading images: " + e.getMessage());
        } // end try-catch

        // Create a panel for the images
        JPanel pnlImg = new JPanel();
        pnlImg.setLayout(new BoxLayout(pnlImg, BoxLayout.PAGE_AXIS));
        pnlImg.setBorder(BorderFactory.createTitledBorder("Images"));
        pnlImg.add(img1Lbl);
        pnlImg.add(Box.createVerticalStrut(5));
        pnlImg.add(img2Lbl);

        JScrollPane imgScrollPane = new JScrollPane(pnlImg);
        imgScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        imgScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        imgScrollPane.getVerticalScrollBar().setUnitIncrement(8);
        imgScrollPane.getHorizontalScrollBar().setUnitIncrement(8);
        
        
        // Create a split pane to hold the data and images
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlData, imgScrollPane);
        
        // Set minimum size for both components in the split pane
        pnlData.setMinimumSize(new Dimension(150, 0));  // Minimum width for pnlData
        imgScrollPane.setMinimumSize(new Dimension(150, 0));  // Minimum width for imgScrollPane
        splitPane.setDividerLocation(400); // Set initial divider location
        
        // Add the split pane to the main panel
        pnlMain.add(splitPane, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    } // end constructor
    
} // end class def