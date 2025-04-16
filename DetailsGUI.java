// File: DetailsGUI.java

// This program demonstrates the use of BoxLayout
import java.awt.*;
import javax.swing.*;
import java.net.URL;
 
public class DetailsGUI extends JFrame {
     
    public DetailsGUI(Exercise exercise) {
         
        setTitle("Exercise Details");
        setSize(800, 600);
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

        // Create a scroll pane for the data panel
        JScrollPane dataScrollPane = new JScrollPane(pnlData);
        dataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dataScrollPane.getVerticalScrollBar().setUnitIncrement(8);
        dataScrollPane.getHorizontalScrollBar().setUnitIncrement(8);


        // Create labels for the images
        JLabel img1Lbl = new JLabel();
        JLabel img2Lbl = new JLabel();

        // Create a label for the arrow
        JLabel arrowLbl = new JLabel(new ImageIcon("images/arrow.png"));
        //JLabel arrowLbl = new JLabel("->", SwingConstants.CENTER);

        try {
            // Load images from URLs
            URL img1URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(0));
            URL img2URL = new URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/" + exercise.getImages().get(1));
            ImageIcon origImg1 = new ImageIcon(img1URL);
            ImageIcon origImg2 = new ImageIcon(img2URL);

            // Resize images to fit the labels
            Image scaledImg1 = origImg1.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
            Image scaledImg2 = origImg2.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
            ImageIcon img1 = new ImageIcon(scaledImg1);
            ImageIcon img2 = new ImageIcon(scaledImg2);
            
            // Set the icons to the labels
            img1Lbl.setIcon(img1);
            img2Lbl.setIcon(img2);

        } catch (Exception e) {
            System.out.println("Error loading images: " + e.getMessage());
        } // end try-catch

        // Create a panel for the images
        JPanel pnlImg = new JPanel();
        pnlImg.setLayout(new BoxLayout(pnlImg, BoxLayout.LINE_AXIS));
        pnlImg.setBorder(BorderFactory.createTitledBorder("Example Images"));
        pnlImg.add(img1Lbl);
        pnlImg.add(Box.createVerticalStrut(5));
        pnlImg.add(arrowLbl);
        pnlImg.add(Box.createVerticalStrut(5));
        pnlImg.add(img2Lbl);

        // Create a split pane to hold the data and images
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dataScrollPane, pnlImg);
        
        // Set minimum size for both components in the split pane
        pnlData.setMinimumSize(new Dimension(150, 0));  // Minimum width for pnlData
        dataScrollPane.setMinimumSize(new Dimension(150, 0));  // Minimum width for dataScrollPane
        
        // Add the split pane to the main panel
        pnlMain.add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(800); // Set initial divider location
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
;
    } // end constructor
    
} // end class def