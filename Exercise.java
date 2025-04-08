//Exercise.java

import java.util.*;

public class Exercise{
    // Instance variables
    private String ID;
    private String name;
    private String force;
    private String level;
    private String mechanic;
    private String equipment;
    private String category;
    private List<String> primaryMuscles;
    private List<String> secondaryMuscles;
    private List<String> instructions;
    private List<String> images;


    public static void main(String[] args) {
        new Exercise();
    } // end main

    // Constructor
    public Exercise(){
        // Initialize instance variables
        ID = "";
        name = "";
        force = "";
        level = "";
        mechanic = "";
        equipment = "";
        category = "";
        primaryMuscles = new ArrayList<>();
        secondaryMuscles = new ArrayList<>();
        instructions = new ArrayList<>();
        images = new ArrayList<>();
    } // end constructor

    // Getters and Setters
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getForce() {
        return force;
    }
    public void setForce(String force) {
        this.force = force;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getMechanic() {
        return mechanic;
    }
    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<String> getPrimaryMuscles() {
        return primaryMuscles;
    }
    public void setPrimaryMuscles(List<String> primaryMuscles) {
        this.primaryMuscles = primaryMuscles;
    }
    public List<String> getSecondaryMuscles() {
        return secondaryMuscles;
    }
    public void setSecondaryMuscles(List<String> secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }
    public List<String> getInstructions() {
        return instructions;
    }
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }

    // Method to display exercise information
    public void displayExerciseInfo() {
        System.out.println("\nID: " + ID);
        System.out.println("\nName: " + name);
        System.out.println("\nForce: " + force);
        System.out.println("\nLevel: " + level);
        System.out.println("\nMechanic: " + mechanic);
        System.out.println("\nEquipment: " + equipment);
        System.out.println("\nCategory: " + category);
        System.out.println("\nPrimary Muscles: " + primaryMuscles);
        System.out.println("\nSecondary Muscles: " + secondaryMuscles);
        System.out.println("\nInstructions: " + instructions);
        System.out.println("\nImages: " + images);
    } // end displayExerciseInfo
} // end class