# FitSearch
Final Project Proposal CS 121

FitSearch 

Proposal

Jake Terry, CS 121

## Overview

Many open-source exercise databases are in JSON format. The goal of this project is to convert JSON files into object instances, then store these objects in a data structure, such as an ArrayList. ArrayList will work well because we will not need to dynamically resize the ArrayList, I am not focused on adding elements to the ArrayList just yet. If progress permit, I may consider a different more dynamic data structure for users to add custom exercises. For now, an ArrayList will work to solve the main problem of this project -- converting JSON to objects, then presenting this information in a readable format.

## UML
<img src="images/FitSearchUML.png" alt="UML" width="400" height="600">

## Open-Source Exercise DataBase

I will be using this data base, found [here](https://github.com/yuhonas/free-exercise-db?tab=readme-ov-file).

The basic schema of the JSON file is this: 
```
{
  "id": "Alternate_Incline_Dumbbell_Curl",
  "name": "Alternate Incline Dumbbell Curl",
  "force": "pull",
  "level": "beginner",
  "mechanic": "isolation",
  "equipment": "dumbbell",
  "primaryMuscles": [
    "biceps"
  ],
  "secondaryMuscles": [
    "forearms"
  ],
  "instructions": [
    "Sit down on an incline bench with a dumbbell in each hand being held at arms length. Tip: Keep the elbows close to the torso.This will be your starting position.",
  ],
  "category": "strength",
  "images": [
    "Alternate_Incline_Dumbbell_Curl/0.jpg",
    "Alternate_Incline_Dumbbell_Curl/1.jpg"
  ]
}
```
 Important to note, I will be using ```https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/``` to access these files directly through github's hosting. By adding to the suffix, JSON and JPG files can be retrieved programmatically.

Example:  
[JSON](https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Alternate_Incline_Dumbbell_Curl.json)   
[JPG](https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Alternate_Incline_Dumbbell_Curl/0.jpg)


## Exercise Class

Using the JSON Schema, the Exercise class will have these attributes and methods:

```
public Class Exercise()
====================
String id
String name
String force
String level
String mechanic
String equipment
List<String> primaryMuscles
List<String> secondaryMuscles
List<String> instructions
String category
List<String> images
====================
Exercise()
String getID
String getName
String getForce
String getLevel
String getMechanic
String getEquipment
List<String> getPrimaryMuscles
List<String> getSecondaryMuscles
List<String> getInstructions
String getCategory
List<String> getImages
```

## ExerciseParser

The parser will be responsible for retrieving the JSON file and deserializing into object instances. So we do not make 800 requests from github, there exists a JSON file in this database that contains all the exercises in one file.  
Found here: [All Exercises](https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/dist/exercises.json)

GSON will be the libary used for deserialization. The idea is to parse the JSON file, and return an ArrayList of Exercise objects.

In order to implement the parser I will need these libraries:

* java.NET.* - gives me access to the URL class
* java.util.* - For the List interface
* java.io.* - for InputStreamReader 
* com.google.gson.* - using Googles JSON to Object convereter.
* com.google.gson.reflect.TypeToken - for the TypeToken class
* java.lang.reflect.Type - for the Type class


### Installing GSON Libraries
GSON is not native to Java so I will have to download the [jar](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/) file and give the compiler special instructions: ```- cp ".:libraries/gson-2.10.1.jar"```. This means use the code in the current directory and the GSON library in the libraries folder.

```
javac -cp ".:libraries/gson-2.10.1.jar" ....
java -cp ".:libraries/gson-2.10.1.jar" ....
```

### GSON Methods

Gson class contains a method ```.fromJson(input, type)```. This converts the JSON into an object of "type". Since we want to store the JSON strings into an ArrayList it is necessary to define the type before hand. Java needs a way to represent generic types, therefore we use do this:

```
Type exerciseList = new TypeToken<ArrayList<Exercise>>(){}.getType();
```

Gson provides ```TypeToken``` as a class for this situation. Gson docs state you do this by creating an empty anonymous class. 

```.getType()``` returns the ```ArrayList<Exercise>``` as a type object.

 

## ExerciseGUI

If possible, I'd want to present this information through a GUI. The ExerciseGui will retrieve the data stored in the ArrayList and present it in a clean manner. For now, I will focus on getting the information presented on commandline. If that works, then I will attempt a GUI.  

#### Use Case

Load the GUI and have pages users can cycle through to explore data. Have an interactable button that when clicked, will give more detailed information about that specific exercise.

Preferably, the GUI will be set up like this:  
<img src="images/DisplayIdea.png" alt="UML" width="600" height="400">  

If a user clicks on the button or name, use the Exercise class getter to grab the image String and call the URL through the link described above:
<img src="images/DetailedGUI.png" alt="UML" width="600" height="400"> 


## Milestones

1. Define Exercise object class
2. Get URL with JSON file.
3. Parse JSON into a ArrayList<Exercise>.
4. Read ArrayList<Exercise> into GUI interface.
5. Present data.

## Blackbelt 

* SearchBy/filter function - ability to search by name, equipment, muscle groups, etc.
* Workout planner - ability to select workouts to incorporate into a workout program.
* Progression tracker - use serialization to track workouts done, weight lifted, max, etc.

### Side Note

For now, I really just want to focus on parsing the JSON files. The GUI would be nice to implement, but it is not the main focus of my project. If I can satisfy the parsing and storing of object instances in an ArrayList, I will realign my focus towards building a user-friendly GUI.