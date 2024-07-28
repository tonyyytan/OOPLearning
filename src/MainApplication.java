
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Main Application Class
 * 
 * Description:
 * This class serves as the entry point for the CAI program designed to enhance learning through
 * interactive questions and activities. The application loads user data and a question bank from external files, 
 * improving personalized learning experiences. It features multiple frames for different functionalities including:
 * - SignUpFrame: Allows new users to register and existing users to log in.
 * - LessonsFrame: Provides access to educational content through various lessons.
 * - ActivityFrame: Engages users with interactive activities to reinforce learning.
 * - ExamFrame: Challenges users with timed exams to assess their understanding.
 * - ExamResultsFrame: Displays the results post-exam, offering feedback and a chance to retry.
 * - TestFrame: Manages the real-time exam taking process with a countdown timer and question navigation.
 * 
 * Major Skills:
 * 	Utilizes OOP to organize programming
 * 	File handling for reading and writing data.
 * 	Event-driven programming for managing user interactions.
 * 	GUI development with Java Swing for an interactive user experience.
 * 
 * Added Features:
 *	1. Dynamic data loading to ensure application is constantly updated (new levels, saving data, etc.)
 *	2. Error handling with try-catch structures when reading files
 *	3. User authentication and session management for personalized experiences.
 *	4. Progress tracking through activities and exams to measure user learning.
 *	5. Cool sound effects in the interactive activities
 * 
 * Areas of Concern:
 *  None
 *  
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Other documentation:
 * 
 * Learned how to implement JTextField components effectively:
 * https://www.geeksforgeeks.org/java-swing-jtextfield/
 * 
 * Learned to disable auto focus on JTextField:
 * https://stackoverflow.com/questions/30309172/how-to-disable-automatic-focus-on-textfield
 * 
 * Learned to write onto a text file using BufferedWriter:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html
 * 
 * Learned how to use JProgressBar:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/javax/swing/JProgressBar.html
 * 
 * Learned how to make a timer:
 * https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java
 * 
 * Learned how to randomize arraylists:
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 
 * Learned to make line breaks:
 * https://stackoverflow.com/questions/1842223/java-linebreaks-in-jlabels
 * 
 * Learned how to play sound clips:
 * https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-multiple-sounds-in-a-game
 * 
 */

//Main application class
public class MainApplication {

	// Contains main method to run program
	public static void main(String[] args) {

		// Fills the user data and question bank arrays
		FileInput.fillUserData();
		FileInput.fillQuestionBankArray();

		// Creates the first frame
		SignUpFrame signUpFrame = new SignUpFrame();

	}
}
