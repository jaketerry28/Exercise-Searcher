# Makefile 

run: OverviewGUI.class Exercise.class ExerciseParser.class DetailsGUI.class ResizeImage.class
	java -cp ".:libraries/gson-2.10.1.jar" OverviewGUI

OverviewGUI.class: OverviewGUI.java Exercise.class ExerciseParser.class DetailsGUI.class
	javac -g -cp ".:libraries/gson-2.10.1.jar" OverviewGUI.java Exercise.java ExerciseParser.java DetailsGUI.java

OverviewGUI: OverviewGUI.class
	java -cp ".:libraries/gson-2.10.1.jar" OverviewGUI	

DetailsGUI.class: DetailsGUI.java
	javac -g DetailsGUI.java

DetailsGUI: DetailsGUI.class
	java DetailsGUI

Exercise.class: Exercise.java
	javac -g Exercise.java

ExerciseParser.class: ExerciseParser.java Exercise.class
	javac -g -cp ".:libraries/gson-2.10.1.jar" ExerciseParser.java Exercise.java

ExerciseParser: ExerciseParser.class 
	java -cp ".:libraries/gson-2.10.1.jar" ExerciseParser

Exercise: Exercise.class
	java Exercise

clean:
	rm *.class
