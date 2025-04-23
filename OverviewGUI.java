// File: OverviewGUI.java

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    JTable exerciseTable;
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

        // Create an instance of ExerciseParser to store the JSON in exercises
        parser = new ExerciseParser();
        exercises = parser.parseExercise();

        // Check if the exercises were parsed successfully
        if (exercises == null) {
            System.out.println("Failed to parse exercises.");
            return;
        } // end if

        // Create a table model to hold the exercise data
        createJTable();

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(exerciseTable);    
        pnlMain.add(scrollPane, BorderLayout.CENTER);
        pnlMain.add(viewDetailsButton, BorderLayout.SOUTH); 

        // Add an action listener to the button
        viewDetailsButton.addActionListener(this);

        setVisible(true);

    } // end init

    public void createJTable(){

        // Create a column names for table
        String[] columnNames = {"Name", "Primary Muscles", "Equipment"};

        // Create a table model to hold the exercise data
        DefaultTableModel table = new DefaultTableModel(columnNames, 0);

        // Create a JTable to display the exercise data
        exerciseTable = new JTable(table);

        // Set the table to be non-editable
        exerciseTable.setDefaultEditor(Object.class, null);

        // Fill table
        for (Exercise exercise : exercises) {
            Object[] row = new Object[3];
            row[0] = exercise.getName();
            row[1] = String.join(",", exercise.getPrimaryMuscles());
            row[2] = exercise.getEquipment();
            
            table.addRow(row);
        } // end for
    } // end createJTable

    public void actionPerformed(ActionEvent e){
        Object theButton = e.getSource(); // Get the source of the event
        if (theButton == viewDetailsButton) {
            int row = exerciseTable.getSelectedRow();
            if (row != -1){
                Exercise selectedExercise = exercises.get(row);
                // Create an instance of DetailsGUI to display the exercise details
                DetailsGUI detailsGUI = new DetailsGUI(selectedExercise);
            } // end if
        } // end if
    } // end actionPerformed


} // end class