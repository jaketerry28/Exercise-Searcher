Exercise.class: Exercise.java
	javac -g -cp ".:libraries/gson-2.10.1.jar" Exercise.java

ExerciseParser.class: ExerciseParser.java Exercise.java
	javac -g -cp ".:libraries/gson-2.10.1.jar" ExerciseParser.java Exercise.java

ExerciseParser: ExerciseParser.class 
	java -cp ".:libraries/gson-2.10.1.jar" ExerciseParser

Exercise: Exercise.class
	java -cp ".:libraries/gson-2.10.1.jar" Exercise

clean:
	rm *.class
