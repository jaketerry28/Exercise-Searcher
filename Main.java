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
    public static void main(String[] args) {
        ExerciseParser parser = new ExerciseParser();
        ArrayList<Exercise> exercises = parser.parseExercise();
        DetailsGUI demo = new DetailsGUI(exercises.get(11));
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setVisible(true);
    } // end main
} // end class Main