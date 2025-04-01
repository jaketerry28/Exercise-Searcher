import com.google.gson.Gson;
import java.io.*;
import java.util.*;

class ExerciseList extends List<Customer> {};

public class Converter {
    Exercise exercise;

    public static void main(String[] args) {
        Gson gson = new Gson();
        FileReader reader = new fileReader("exercises.json");

        try (reader){
            // JSON to Java object
            Exercise exercises = gson.fromJson(reader);

            // print the exercises
            System.out.println(exercises.getInfo());
        } catch (IOException e) {
            e.printStackTrace();
        } // end try-catch
    } // end main

