// File: OverviewGUI.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class OverviewGUI extends JFrame implements ActionListener {

    // Create a instance variables
    JButton viewDetailsButton = new JButton("View Details");
    JList<String> exerciseList;
    ExerciseParser parser;
    ArrayList<Exercise> exercises;

    public static void main(String[] args) {
        new OverviewGUI();
    } // end main

    public OverviewGUI(){
        init();
    } // end constructor

    public void init(){
        // Set the title and size of the window
        setTitle("Exercise Overview");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pnlMain = this.getContentPane();

        // Set the layout manager for the main panel
        pnlMain.setLayout(new BorderLayout());
        pnlMain.setBackground(Color.LIGHT_GRAY);

        // Create an instance of ExerciseParser to parse the JSON data
        this.parser = new ExerciseParser();
        // Parse the JSON data and get the list of exercises
        this.exercises = parser.parseExercise();

        // Check if the exercises were parsed successfully
        if (exercises == null) {
            System.out.println("Failed to parse exercises.");
            return;
        } // end if

        // Create a list to display exercise names
        DefaultListModel<String> exerciseNames = new DefaultListModel<>();
        for (Exercise exercise : exercises) {
        exerciseNames.addElement(exercise.getName());
        } // end for

        // Create a JList to display the exercise names
        exerciseList = new JList<>(exerciseNames);

        // Allow only one selection at a time
        exerciseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a scroll pane for the list
        JScrollPane scrollPane = new JScrollPane(exerciseList);    

        pnlMain.add(scrollPane, BorderLayout.CENTER);
        pnlMain.add(viewDetailsButton, BorderLayout.SOUTH); 

        // Add an action listener to the button
        viewDetailsButton.addActionListener(this);

        setVisible(true);

    } // end init

    public void actionPerformed(ActionEvent e){
        Object theButton = e.getSource(); // Get the source of the event
        if (theButton == viewDetailsButton) {
            int index = exerciseList.getSelectedIndex();
            if (index != -1){
                Exercise selectedExercise = exercises.get(index);
                // Create an instance of DetailsGUI to display the exercise details
                DetailsGUI detailsGUI = new DetailsGUI(selectedExercise);
            } // end if
        } // end if
    }
} // end class