# Exercise Searcher
Final Project Proposal CS 121

FitSearch 

Proposal

Jake Terry, CS 121

## Overview

Many open-source exercise databases are in JSON format. The goal of this project is to convert JSON files into object instances, then store these objects in a data structure, such as an ArrayList. ArrayList will work well because we will not need to dynamically resize the ArrayList, I am not focused on adding elements to the ArrayList. An ArrayList will work to solve the main problem of this project -- converting JSON to objects, then presenting this information in a readable format.

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

## GUI

I want to present this information through a GUI. The GUI will retrieve the data stored in the ArrayList and present it in a clean manner. There will be two GUIs, one that shows a detailed view of a single exercise, and another that will allow users to browse the ArrayList of exercise data.  

#### Use Case

Load the GUI as a list the user can scroll through. The user can select an exercise then click a button to view more info.

### OverviewGUI
Preferably, the GUI will be set up like this:  
<img src="images/DisplayIdea.png" alt="UML" width="600" height="400">  

The user selects an exercise from the list, then clicks the view details button. This will open a new window with the detailed view.

```
public class OverviewGUI extends JFrame implements ActionListener{

  JButton viewDetailsButton = new JButton("View Details");
  JTable exerciseTable;
  ExerciseParser parser;
  ArrayList<Exercise> exercises;
  ========================================
  public static void main();
  public OverviewGUI();
  public void init();
  public void createJTable();
  public void actionPerformed();
}
```
* The ```init()``` method handles the creation of the JFrame, initialization of parser and the Exercise ArrayList, and calls the other methods in the class.  

```
public void createJTable(){

  Create column headers: "Name", "Primary Muscles", "Equipment";
  Initialize a DefaultTableModel called table with column headers and no initial rows;
  Create a JTable called exerciseTable using the table;

  for each exercise in the ArrayList<Exercise>{
    Create an array of three objects[] called row;
    row[0] = exercise name;
    row[1] = primary muscle group;
    row[2] = equipment needed;
    add to the table;
  }
}
```
* The exercises are displayed in a ```JTable``` to display the exercise name, primary muscle group, and the equipment needed at a glance.
When using JTable, rows are represented as arrays of objects, so that is why we define the size of the array in the for loop.

```
public void actionPerformed(ActionEvent e){
  create an Object called theButton and set it to the source event
  if theButton is the viewDetailsButton{
      initialize int row as the selected row in the table
      create an Exercise called selectedExercise set with get(row) to find the index
      Create a DetailedGUI instance using selectedExercise;
  }
}
```
* Since OverViewGUI implements ```ActionListener``` we need to include an ```actionPerformed()``` method. Here we are finding the index of the exercise in the JTable and calling DetailedGUI to open a new window with a detailed exercise view when the view details button is clicked.

### DetailsGUI:  
<img src="images/DetailedGUI.png" alt="UML" width="800" height="600">

```
public class DetailsGUI extends JFrame{
  private BufferedImage img1;
  private BufferedImage img2;
  private JLabel img1Lbl
  private JLabel arrowLbl 
  private JLabel img2Lbl
  private Exercise exercise; 
  ==================================
  public DetailsGUI(Exercise exercise);
  public void init(exercise);
  public void setImages();

}
```
* When an exercise is selected in the ```OverviewGUI``` it is passed as an argument in the DetailsGUI constructor. This is passed into the ```init()``` method that handles the creation of the JFrame, calls setImages() and creates an Instance of ResizeImage. The JFrame uses a GridLayout of 2 rows and 1 column. The exercise data will be at the top, with picture examples at the botom of the window.

* ```exerciseData``` will contain the Exercise JSON attributes and display them. I chose to use the JTextArea because the data for each exercise varies. Some have larger amounts of text than others. JTextArea supports text wrapping and so it is dynamic for resizing. This panel will be inserted into a ```JScrollPane``` in the event that the text is cut off the user can scroll.

* ```imgPanel``` will contain two images along a horizontal axis called from the ```setImages()``` method.
```
public void setImages(){
  try{
    create URL objects for each image;
    read images from URL into BufferedImage objects;
    create ImageIcon objects from BufferedImage objects;
    set icons to the labels;
  } catch{
    print out error message if images do not load;
  }
}
```
* We grab the images from ```"https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/"``` concatenated with the ```getImages()``` method. Then load the URL into a BufferedImage object. We then load the BufferedImage into an ImageIcon class to actually display the image. 


### ResizeImage

Images resize properly as the user adjusts the window size. A separate class was created to handle this.

```
public class ResizeImage extends ComponentAdapter{
  private JLabel img1lbl;
  private JLabel img2lbl;
  private Image img1;
  private Image img2;  
  =======================================
  public ResizeImage(img1Lbl, img2Lbl, img1, img2)
  public void compentResized(Component Event e)
  public void resize()
  public Image scaleImage(Image orig, int maxWidth, int maxHeight)
}
```

* The main thing this class focuses on in window resizing. There is an interface called ```ComponentListener``` that contains 4 methods. I only care one of them which is ```componentResized()```. However to avoid having to declare 3 other empty methods I use ```ComponentAdapter``` instead. ComponentAdapter is an abstract class that implements ComponentListener but provides empty implementations for all methods. Then I use polymorphism to override the componentResized() to call the ```resize()``` method whenever it detects the window has changed size. This makes for cleaner code and readability.

```
public void resize(){
  get the widths of both labels
  get the heights of both labels
  create Image objects using scaleImage(img, width, height)
  create ImageIcon objects with Image objects
  set labels to the ImageIcons 
}
```
* When the ComponentListener detects a ComponentEvent (in this case window resizing), this method is called. Here we just get current width and height of each label. Then call the ```scaleImage()``` method and pass these parameters to do dynamic scaling. Set the resized images to the labels.

```
private Image scaleImage(Image original, int labelWidth, int labelHeight) {
  get imgWidth of image
  get imgHeigth of image

  double widthRatio is  labelWidth /  imgWidth
  double heightRatio is labelHeight / imgHeight
  determine which ratio is smaller and assign it to ratio
  
  int newWidth is originalWidth * ratio 
  int newHeight is originalHeight * ratio

  convert newWidth, newHeight from double to integer using casting.

  scale original image with getScaledInstance method
  
  return scaled image
}
```
* The ```scaleImage()``` method dynamically resizes the images to match the size of the labels. Since not all the images in this database are the same size, I had to write a custom method that sizes the images based on their aspect ratio.

* Get the height and width of the image, calculate the aspect ratios, select the smaller ratio, then scale the image using ```getScaledInstance()```.
## Milestones

- [x] Define Exercise object class.  
- [x] Get URL with JSON file.  
- [x] Parse JSON into a ArrayList<Exercise>.  
- [x] Read ArrayList<Exercise> into GUI interface.  
- [x] Create Detailed GUI.  
- [x] Create Overview GUI. 

## Blackbelt 

* SearchBy/filter function - ability to search by name, equipment, muscle groups, etc.
* Workout planner - ability to select workouts to incorporate into a workout program.
* Progression tracker - use serialization to track workouts done, weight lifted, max, etc.

## After Action

* GUI ARE HARD but I learned a lot. The dynamic resizing is inefficient because anytime the window is resized it will recalculate and scale even if it was just by one pixel. 
