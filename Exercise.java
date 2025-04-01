import java.util.*

public class Exercise{
    private String name;
    private String force;
    private String level;
    private String mechanic;
    private String equipment;
    private String category;
    private String id;
    private List<String> primaryMuscles;
    private List<String> secondaryMuscles;
    private List<String> instructions;
    private List<String> images;

    public static void main(String[] args){
        new Exercise();
    } // end main

    Exercise(){
        this.name = "";
        this.force = "";
        this.level = "";
        this.mechanic = "";
        this.equipment = "";
        this.category = "";
        this.id = "";
        this.primaryMuscles = new List<String>();
        this.secondaryMuscles = new List<String>();
        this.instructions = new List<String>();
        this.images = new List<String>();
    }  // end class Exercise

    Exercise(String name, String force, String level, String mechanic, String equipment, String category, String id,
            List<String> primaryMuscles, List<String> secondaryMuscles, List<String> instructions, List<String> images){
        this.name = name;
        this.force = force;
        this.level = level;
        this.mechanic = mechanic;
        this.equipment = equipment;
        this.category = category;
        this.id = id;
        this.primaryMuscles = primaryMuscles;
        this.secondaryMuscles = secondaryMuscles;
        this.instructions = instructions;
        this.images = images;
    } // end constructor Exercise

    public String getInfo(){
        return "Exercise: " + name
                 + "\nCategory: " + category
                 + "Equiopment: " + equipment
                 + "Primary Muscles: " + primaryMuscles
                 + "\nSecondary Muscles: " + secondaryMuscles
                
    }