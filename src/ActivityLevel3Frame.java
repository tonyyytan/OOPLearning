
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Activity Level 3 Frame
 * 
 * Description:
 * This class is a part of the CAI app on object-oriented programming
 * concepts through an interactive matching game. Users must match terms with their corresponding definitions.
 * The class provides visual and audio feedback for correct and incorrect matches and updates the user's progress upon
 * successful completion of the activity.
 * 
 * Major Skills:
 *  Handling GUI components using Java Swing.
 *  Implementing action listeners to handle user interactions.
 *  Manipulating audio playback for user feedback.
 *  Logical comparison of string values to check matches.
 * 
 * Added Features:
 *  1. Audio feedback to indicate correct or incorrect matches.
 *  2. Dynamic visibility changes in the GUI to show progress in the game.
 *  3. Integration with user progress tracking to update activity levels.
 * 
 * Areas of Concern:
 *  None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Additional documentation:
 * 
 * Learned how to read a music file: 
 * https://www.geeksforgeeks.org/play-audio-file-using-java/
 * 
 */

//Import necessary java packages
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//Create the class and extend to JFrame
public class ActivityLevel3Frame extends JFrame implements ActionListener {

	// Get the activity level from user data
	private int activityLevel = FileInput.userDataArray.get(SignUpFrame.userIdentity).getActivityLevel();

	// JLabel of the game title
	private JLabel gameTitleLabel = new JLabel();

	// JLabel for the definition and word section
	private JLabel[] sectionLabelArray = new JLabel[2];
	private String[] sectionName = { "Definitions", "Words" };

	// JButton 2d array to hold the definition and word arrays
	private JButton[][] definitionButtonArray = new JButton[2][3];
	private JButton[][] wordButtonArray = new JButton[2][3];

	// JLabel 2d array to hold the text for the definition and word boxes
	private JLabel[][] definitionLabels = new JLabel[2][3];
	private JLabel[][] wordLabels = new JLabel[2][3];

	// Array holding the words related to object-oriented programming
	String[] words = { "Object Oriented Programming", "toString() method", "Method overloading", "UML Class Diagram",
			"Main method", "Parameters" };

	// Array holding the corresponding definitions for each word
	String[] definitions = { "Having 2+ methods with same name, but different parameters",
			"Returns the object as a String", "A programming method using objects",
			"Graphical representation of an object", "Initiates the execution of a program",
			"Additional information a method requires to run" };

	// JButton array for the exit and finish buttons
	private JButton[] gameButtonArray = new JButton[2];
	private ImageIcon[] gameButtonImages = { new ImageIcon("images/exitButton.png"),
			new ImageIcon("images/finishButton.png") };

	// ImageIcons for the definition and word boxes
	private ImageIcon definitionImage = new ImageIcon("images/definitionButton.png");
	private ImageIcon wordImage = new ImageIcon("images/wordButton.png");

	// Keeps track of selected definition and words
	private String selectedDefinition = null;
	private String selectedWord = null;

	// Keeps track of the selected buttons
	private JButton selectedDefinitionButton = null;
	private JButton selectedWordButton = null;

	// Gets the number of correct (determines when the game is done)
	private int numberCorrect = 0;

	// Constructor
	public ActivityLevel3Frame() {

		// Generates the title
		generateTitle();

		// Generates the definition buttons
		generateDefinitionButtons();

		// Generates the word buttons
		generateWordButtons();

		// Generates the game utility buttons
		generateGameButtons();

		// Generates the frame
		generateFrame();
	}

	// Method to generate the JLabel title of the frame
	private void generateTitle() {
		// Sets the text to game name
		gameTitleLabel.setText("Definitions - Match Up!");
		gameTitleLabel.setBounds(63, 63, 491, 56);

		// Changes font
		gameTitleLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		gameTitleLabel.setForeground(Color.white);
		add(gameTitleLabel);

		// Uses a for loop to generate the definition and word JLabel section
		for (int index = 0; index < sectionLabelArray.length; index++) {
			// Sets the text to game name
			sectionLabelArray[index] = new JLabel(sectionName[index]);
			sectionLabelArray[index].setBounds(63 + 610 * index, 140, 175, 38);

			// Changes font
			sectionLabelArray[index].setFont(new Font("Dialog", Font.BOLD, 24));
			sectionLabelArray[index].setForeground(Color.white);
			add(sectionLabelArray[index]);
		}
	}

	// Method to generate the utility buttons
	private void generateGameButtons() {

		// Uses a for loop to place the buttons efficiently
		for (int index = 0; index < gameButtonArray.length; index++) {

			// Creates the button, sets the bounds, and sets the icon
			gameButtonArray[index] = new JButton();
			gameButtonArray[index].setBounds(63 + 898 * index, 450, 138, 59);
			gameButtonArray[index].setIcon(gameButtonImages[index]);

			// Adds an action listener to the button
			gameButtonArray[index].addActionListener(this);
			add(gameButtonArray[index]);
		}
	}

	// Method to generate the definition buttons
	private void generateDefinitionButtons() {

		// Counter for the JLabel array
		int count = 0;

		// Nested for loop to generate the buttons
		for (int row = 0; row < definitionButtonArray.length; row++) {
			for (int col = 0; col < definitionButtonArray[row].length; col++) {

				// Create the button, set the bounds, and set the icon
				definitionButtonArray[row][col] = new JButton();
				definitionButtonArray[row][col].setBounds(63 + 214 * row, 192 + 60 * col, 214, 60);
				definitionButtonArray[row][col].setIcon(definitionImage);

				// Add an action listener
				definitionButtonArray[row][col].addActionListener(this);
				add(definitionButtonArray[row][col]);

				String definition = "<html><body style='width: 150 px'>" + definitions[count] + "</body></html>";

				// Create the JLabel text, set the bounds, customize font, and add to the button
				definitionLabels[row][col] = new JLabel(definition); // Correct index
				definitionLabels[row][col].setBounds(63 + 214 * row, 172 + 60 * col, 214, 20);
				definitionLabels[row][col].setFont(new Font("Dialog", Font.BOLD, 12));
				definitionLabels[row][col].setForeground(Color.WHITE);
				definitionButtonArray[row][col].add(definitionLabels[row][col]);

				// Increment the counter
				count++;
			}
		}
	}

	// Method to generate the word buttons
	private void generateWordButtons() {
		// Counter for the JLabel array
		int count = 0;

		// Nested for loop to generate the buttons
		for (int row = 0; row < wordButtonArray.length; row++) {
			for (int col = 0; col < wordButtonArray[row].length; col++) {

				// Create the button, set the bounds, and set the icon
				wordButtonArray[row][col] = new JButton();
				wordButtonArray[row][col].setBounds(673 + 214 * row, 192 + 60 * col, 214, 60);
				wordButtonArray[row][col].setIcon(wordImage);

				// Add action listener to the button
				wordButtonArray[row][col].addActionListener(this);
				add(wordButtonArray[row][col]);

				// Create the JLabel text, set the bounds, customize font, and add to the button
				wordLabels[row][col] = new JLabel(words[count]); // Correct index
				wordLabels[row][col].setBounds(673 + 214 * row, 172 + 60 * col, 214, 20);
				wordLabels[row][col].setFont(new Font("Dialog", Font.BOLD, 12));
				wordLabels[row][col].setForeground(Color.WHITE);
				wordButtonArray[row][col].add(wordLabels[row][col]);

				// Increment the counter
				count++;
			}
		}
	}

	// Method to generate and define the properties of the frame
	private void generateFrame() {

		// Creates a color variable, decoding hexcode
		Color backgroundColor = Color.decode("#131F24");

		// Sets title of the window
		setTitle("CodeSpace");

		// Sets the size of the window
		setSize(1200, 625);

		// Sets the color of the window
		getContentPane().setBackground(backgroundColor);

		// Sets the coordinates to be absolute
		setLayout(null);

		// Adds key and action listeners to the frame
		addActionListener(this);

		// Sets the frame to not be resizable
		setResizable(false);

		// Closes the window on exit
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Screen is visible
		setVisible(true);

	}

	// Automatically generated method for actionlistener
	private void addActionListener(ActivityLevel3Frame activityLevel3Frame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	public void actionPerformed(ActionEvent event) {

		// Nested for loop to check all the button actions for definition
		for (int row = 0; row < definitionButtonArray.length; row++) {
			for (int col = 0; col < definitionButtonArray[row].length; col++) {

				// If it came from definition button
				if (event.getSource() == definitionButtonArray[row][col]) {

					// Make the selected definition from that button
					selectedDefinitionButton = definitionButtonArray[row][col];

					// Make the selected definition text to that button
					selectedDefinition = definitionLabels[row][col].getText();

					checkMatch(); // Method to check for a match

					// Stop the for loop
					return;
				}
			}
		}

		// Nested for loop to check if a word button was pressed
		for (int row = 0; row < wordButtonArray.length; row++) {
			for (int col = 0; col < wordButtonArray[row].length; col++) {

				// If it came from the word button
				if (event.getSource() == wordButtonArray[row][col]) {

					// Make the selected word to that button
					selectedWordButton = wordButtonArray[row][col];

					// Make the selected word text from that button
					selectedWord = wordLabels[row][col].getText();

					checkMatch(); // Method to check for a match

					// Stop the for loop
					return;
				}
			}
		}

		// If the exit frame button is clicked
		if (event.getSource() == gameButtonArray[0]) {
			dispose();
			ActivityFrame activityFrame = new ActivityFrame();

		}

		// If the finish frame button is clicked
		else if (event.getSource() == gameButtonArray[1]) {

			// If the user has matched all the definitions
			if (numberCorrect == 6) {

				// If the user has not completed this level before
				if (activityLevel < 3) {

					// Set the user to the new level
					FileInput.userDataArray.get(SignUpFrame.userIdentity).setActivityLevel(3);

					// Display a completion message
					JOptionPane.showMessageDialog(this, "Nice, you completed the level!", "Exiting...",
							JOptionPane.INFORMATION_MESSAGE);

					// Redirects the page back to the activity frame
					dispose();
					ActivityFrame activityFrame = new ActivityFrame();

				}

				// If the user has completed this level before
				else {

					// Redirects the user back to the activity frame
					dispose();
					ActivityFrame activityFrame = new ActivityFrame();
				}
			}

			// If the user has not finished matching definitions
			else {
				// Display an error message
				JOptionPane.showMessageDialog(this, "Incomplete level", "Finish the game first!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Void method which plays a sound
	private void playSound(String soundFileName) {

		// Try catch structure
		try {

			// Creates a sound file based on the parameter input
			File soundFile = new File(soundFileName);

			// Gets an audio input stream from file
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

			// Gets a clip resource to play the clip
			Clip clip = AudioSystem.getClip();

			// Opens the audio clip
			clip.open(audioIn);

			// Starts playing the clip
			clip.start();

		}

		catch (Exception event) { // Catch if an error
			System.err.println(event.getMessage());
		}
	}

	// Method to check match for answers
	private void checkMatch() {

		// If two boxes have been selected
		if (selectedDefinition != null && selectedWord != null) {

			// If the answer is correct
			if (matches(selectedDefinition, selectedWord)) {

				// Plays the correct audio
				playSound("correctSound.wav");

				// Makes the buttons dissappear
				selectedDefinitionButton.setVisible(false);
				selectedWordButton.setVisible(false);

				// Reset the selections
				selectedDefinition = null;
				selectedWord = null;
				selectedDefinitionButton = null;
				selectedWordButton = null;

				// Increments the number correct
				numberCorrect++;
			}

			// If the answer is incorrect
			else {

				// Play the wrong audio
				playSound("wrongSound.wav");

				// Optionally provide feedback or reset the selections if they don't match
				JOptionPane.showMessageDialog(this, "Not a match, try again!", "Mismatch", JOptionPane.ERROR_MESSAGE);

				// Reset the selections
				selectedDefinition = null;
				selectedWord = null;
			}
		}
	}

	// Method to check if the definitions and words match the correct answer
	private boolean matches(String selectedDefinition, String selectedWord) {

		// Checks if the definitions and words match up to the correct answers
		// Uses .contains instead of .equals because it has html wrapping
		if ((selectedDefinition.contains("A programming method using objects")
				&& selectedWord.equals("Object Oriented Programming"))
				|| (selectedDefinition.contains("Returns the object as a String")
						&& selectedWord.equals("toString() method"))
				|| (selectedDefinition.contains("Having 2+ methods with same name, but different parameters")
						&& selectedWord.equals("Method overloading"))
				|| (selectedDefinition.contains("Graphical representation of an object")
						&& selectedWord.equals("UML Class Diagram"))
				|| (selectedDefinition.contains("Initiates the execution of a program")
						&& selectedWord.equals("Main method"))
				|| (selectedDefinition.contains("Additional information a method requires to run")
						&& selectedWord.equals("Parameters"))) {
			// if it is the correct answer, return true
			return true;
		}

		// Otherwise return false
		return false;
	}
}
