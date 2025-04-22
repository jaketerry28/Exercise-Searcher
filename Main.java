// File: Main.java

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class Main {

    // Scanner object for user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create an instance of ExerciseParser to parse the JSON data
        ExerciseParser parser = new ExerciseParser();
        ArrayList<Exercise> exercises = parser.parseExercise();

        if (exercises == null) {
            System.out.println("Failed to parse exercises.");
            return;
        } // end if

        // Print the number of exercises parsed
        System.out.println("Parsed " + exercises.size() + " exercises.");

        // Create an instance of OverviewGUI to display the exercise overview
        OverviewGUI overview = new OverviewGUI();
    } // end main
    
} // end class Main

