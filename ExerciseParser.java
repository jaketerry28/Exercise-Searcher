// ExerciseParser.java

import java.io.*;
import java.util.*;
import java.net.URL;
import com.google.gson.*;



public class ExerciseParser{

    // testing a single JSON file
    private static final String exampleURL = "https://raw.githubusercontent.com/yuhonas/free-exercise-db/refs/heads/main/exercises/Dumbbell_Alternate_Bicep_Curl.json";

    public static void main(String[] args) {
        ExerciseParser parser = new ExerciseParser();
        Exercise testExercise = parser.parseExercise();

        if (testExercise != null) {
            System.out.println("\nLoaded Exercises: ");
            testExercise.displayExerciseInfo();
        } else {
            System.out.println("Failed to load exercises");
        } // end if
    } // end main

    public Exercise parseExercise(){
        try {
            URL url = new URL(exampleURL);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            Gson gson = new Gson();
            Exercise exercise = gson.fromJson(reader, Exercise.class);
            reader.close();
            return exercise;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } // end try-catch
    }

    
} // end class