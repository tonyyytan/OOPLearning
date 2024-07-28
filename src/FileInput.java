/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: FileInput Class
 * 
 * Description:
 * This class is responsible for handling all file input operations for the application. It reads user data and
 * question bank data from text files and initializes corresponding objects stored in arrays and ArrayLists.
 * This centralizes data management for user profiles and question banks used throughout the application.
 * 
 * Major Skills:
 * 	Use of ArrayLists and arrays to manage dynamic and fixed data collections.
 * 	File handling with Java’s Scanner and File classes.
 * 
 * Added Features:
 *	1. Reading user data and question bank text files
 * 
 * Areas of Concern:
 * 	Handling corrupted or incorrectly formatted data files could be improved with more comprehensive error checking.
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * References:
 * 	ArrayLists in Java: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 */

//Import necessary java packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Fileinput class
public class FileInput {

	// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
	public static ArrayList<User> userDataArray = new ArrayList<User>(); // user data arraylist
	public static Question[] questionBankArray = new Question[10]; // question bank array

	// Method to fill the user data
	public static void fillUserData() {

		// Try catch structure to read file
		try {

			// Create scanner object to read files
			Scanner inputFile = new Scanner(new File("userData.txt"));

			// Excludes these arguments from getting scanned
			inputFile.useDelimiter(",|\r\n");

			// For loop to create new object for each index
			while (inputFile.hasNext()) {

				// Declares and assigns a value to all the fields using .txt file
				String username = inputFile.next();
				String password = inputFile.next();
				int lessonLevel = inputFile.nextInt();
				int activityLevel = inputFile.nextInt();
				double examScore = inputFile.nextDouble();

				// Create a new object for each index
				userDataArray.add(new User(username, password, lessonLevel, activityLevel, examScore));
			}

			// Close scanner object
			inputFile.close(); // External file can now be used while this program is running

		}

		catch (FileNotFoundException e) { // Handles FileNotFoundException

			System.out.println("File error");
		}
	}

	// Method to fill the question bank array
	public static void fillQuestionBankArray() {

		// Uses try catch structure to read the file
		try {

			// Create scanner object to read files
			Scanner inputFile = new Scanner(new File("questionBank.txt"));

			// Excludes these arguments from getting scanned
			inputFile.useDelimiter(",|\r\n");

			// For loop to create new object for each index
			for (int index = 0; index < questionBankArray.length; index++) {

				// Declares and assigns a value to all the fields using .txt file
				String questions = inputFile.next();
				String answer = inputFile.next();
				String fakeAnswer1 = inputFile.next();
				String fakeAnswer2 = inputFile.next();
				String fakeAnswer3 = inputFile.next();

				// Create a new object for each index
				questionBankArray[index] = new Question(questions, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3);

			}

			// Close scanner object
			inputFile.close(); // External file can now be used while this program is running

		}

		catch (FileNotFoundException e) { //Handles FileNotFoundException

			System.out.println("File error");
		}
	}

}
