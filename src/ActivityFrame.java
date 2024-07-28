
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Activity Frame
 * 
 * Description:
 * The ActivityFrame class serves as an interface for users to navigate between different activity levels
 * in the application. It displays a panel with buttons for various activity levels and allows users to start,
 * resume, or review their progress on activities. The frame also includes a logout button that saves the 
 * current state and logs the user out.
 * 
 * Major Skills:
 * 	Handling GUI components using Java Swing.
 * 	File input/output to save user progress.
 * 	Event-driven programming for handling user actions.
 * 
 * Added Features:
 *		1. Dynamic level icons (some open/locked/completed)
 *		2. Logout functionality that saves the user's data.
 * 
 * Areas of Concern:
 *   None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Documentation Referenced:
 * Learned to write onto a text file using BufferedWriter:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html 
 * 
 */

//import necessary java packages
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

//Create the class extending into JFrame
public class ActivityFrame extends JFrame implements ActionListener {

	// JPanel header
	private JPanel headerPanel = new JPanel();

	// JButton array for the frame buttons
	private JButton[] buttonArray = new JButton[2];
	private ImageIcon[] buttonImages = { new ImageIcon("images/learningButton.png"),
			new ImageIcon("images/examButton.png") };

	// JButton for the logout button
	private JButton logoutButton = new JButton();
	private ImageIcon logoutIcon = new ImageIcon("images/logoutButton.png");

	// JLabel for the logo
	private JLabel logoLabel = new JLabel();
	private ImageIcon logoIcon = new ImageIcon("images/logoImage.png");

	// JLabel for the "selected activity frame"
	private JLabel activityLabel = new JLabel();
	private ImageIcon activityIcon = new ImageIcon("images/activityButtonSelected.png");

	// JButton array for the levels
	private JButton[] levelButtonArray = new JButton[4];
	private ImageIcon[] levelIconArray = { new ImageIcon("images/activityStartLevelImage.png"),
			new ImageIcon("images/activityCompletedLevelImage.png"), new ImageIcon("images/lockedLevelImage.png") };

	// Border object
	private Border grayLine = BorderFactory.createLineBorder(new Color(0x37464F), 4);

	// Activity level retrieved from the user data
	private int activityLevel = FileInput.userDataArray.get(SignUpFrame.userIdentity).getActivityLevel();

	// Constructor of the frame
	public ActivityFrame() {

		// Generate the panel
		generatePanel();

		// Generate the title
		generateTitle();

		// Generate the frame
		generateFrame();
	}

	// Method to generate the panel
	private void generatePanel() {

		// Create and define the properties of the header panel
		headerPanel.setBounds(0, 0, 347, 625);
		headerPanel.setBackground(new Color(0x131F24)); // background color
		headerPanel.setBorder(grayLine); // use the border
		headerPanel.setLayout(null); // Use absolute positioning
		add(headerPanel); // Make it visible

		// Call method to generate the buttons
		generateHeaderButtons();
		generateLevels();
	}

	private void generateHeaderButtons() {

		// Adds the "selected" page icon (so learningButton is highlighted)
		activityLabel.setBounds(56, 256, 232, 60);
		activityLabel.setIcon(activityIcon);

		headerPanel.add(activityLabel);

		// For loop to efficiently place the buttons
		for (int index = 0; index < buttonArray.length; index++) {
			buttonArray[index] = new JButton();
			// Adds the action listener to the button
			buttonArray[index].addActionListener(this);

			// Sets the bounds of the buttons
			buttonArray[index].setBounds(56, 148 + 202 * index, 232, 60);

			// Define the properties of the button
			buttonArray[index].setFocusPainted(false);
			buttonArray[index].setBorderPainted(false);
			buttonArray[index].setFocusable(true);
			buttonArray[index].setOpaque(true);
			buttonArray[index].setIcon(buttonImages[index]);

			// Adds the button to the frame
			headerPanel.add(buttonArray[index]);
		}

		// Adds the action listener to the button
		logoutButton.addActionListener(this);

		// Sets the bounds of the buttons
		logoutButton.setBounds(71, 475, 193, 69);

		// Define the properties of the button
		logoutButton.setFocusPainted(false);
		logoutButton.setBorderPainted(false);
		logoutButton.setFocusable(true);
		logoutButton.setOpaque(true);
		logoutButton.setIcon(logoutIcon);

		// Adds the button to the frame
		headerPanel.add(logoutButton);

	}

	// Method to generate the levels
	private void generateLevels() {

		// for loop to efficiently define the properties of all the level buttons
		for (int index = 0; index < levelButtonArray.length; index++) {
			levelButtonArray[index] = new JButton();

			// No focused outline, no border, opaque, set icon to default
			levelButtonArray[index].setFocusPainted(false);
			levelButtonArray[index].setBorderPainted(false);
			levelButtonArray[index].setFocusable(true);
			levelButtonArray[index].setOpaque(true);
			levelButtonArray[index].setIcon(levelIconArray[2]);
		}

		// Individually set the bounds of each level and add it to the frame
		levelButtonArray[0].setBounds(683, 63, 95, 95);
		add(levelButtonArray[0]);

		levelButtonArray[1].setBounds(460, 210, 95, 95);
		add(levelButtonArray[1]);

		levelButtonArray[2].setBounds(714, 282, 95, 95);
		add(levelButtonArray[2]);

		levelButtonArray[3].setBounds(557, 450, 95, 95);
		add(levelButtonArray[3]);

		// Call method to check which levels are unlocked by user
		checkLevel();

	}

	// Method to check the user's level
	private void checkLevel() {
		// If the user hasn't finished any levels
		if (activityLevel == 0) {

			// Set level 1 as a start icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[0]);
			levelButtonArray[0].addActionListener(this);
			levelButtonArray[0].setBounds(683, 43, 114, 145);
		}

		// If the user has finished level 1
		else if (activityLevel == 1) {

			// Set level 1 to the completed icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[1]);
			levelButtonArray[0].addActionListener(this);

			// Set level 2 as a start icon and add an action listener to it
			levelButtonArray[1].setIcon(levelIconArray[0]);
			levelButtonArray[1].addActionListener(this);
			levelButtonArray[1].setBounds(460, 185, 114, 145);

		}

		// If user has finished level 2
		else if (activityLevel == 2) {

			// Set level 1 to the completed icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[1]);
			levelButtonArray[0].addActionListener(this);

			// Set level 2 to the completed icon and add action listener to it
			levelButtonArray[1].setIcon(levelIconArray[1]);
			levelButtonArray[1].addActionListener(this);

			// Set level 3 as a start icon and add an action listener to it
			levelButtonArray[2].setIcon(levelIconArray[0]);
			levelButtonArray[2].addActionListener(this);
			levelButtonArray[2].setBounds(714, 282, 114, 145);
		}

		// If the user has finished level 3
		else if (activityLevel == 3) {

			// Set level 1 to the completed icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[1]);
			levelButtonArray[0].addActionListener(this);

			// Set level 2 to the completed icon and add action listener to it
			levelButtonArray[1].setIcon(levelIconArray[1]);
			levelButtonArray[1].addActionListener(this);

			// Set level 3 to the completed icon and add action listener to it
			levelButtonArray[2].setIcon(levelIconArray[1]);
			levelButtonArray[2].addActionListener(this);

			// Set level 4 as a start icon and add an action listener to it
			levelButtonArray[3].setIcon(levelIconArray[0]);
			levelButtonArray[3].addActionListener(this);
			levelButtonArray[3].setBounds(557, 400, 114, 145);
		}

		// If the user has finished level 4
		else if (activityLevel == 4) {

			// Set level 1 to the completed icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[1]);
			levelButtonArray[0].addActionListener(this);

			// Set level 2 to the completed icon and add action listener to it
			levelButtonArray[1].setIcon(levelIconArray[1]);
			levelButtonArray[1].addActionListener(this);

			// Set level 3 to the completed icon and add action listener to it
			levelButtonArray[2].setIcon(levelIconArray[1]);
			levelButtonArray[2].addActionListener(this);

			// Set level 4 to the completed icon and add action listener to it
			levelButtonArray[3].setIcon(levelIconArray[1]);
			levelButtonArray[3].addActionListener(this);
		}
	}

	// Method to generate the title
	private void generateTitle() {

		// Set the logo bounds
		logoLabel.setBounds(57, 42, 236, 58);

		// Changes icon
		logoLabel.setIcon(logoIcon);
		headerPanel.add(logoLabel);
	}

	// Method to generate the frame's properties
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

	// Method to save the information to the user's data
	// https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html
	private void saveInfo() {

		// Try catch structure to write
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt", false))) {

			// Use enhanced for loop to iterate through each index of the arraylist
			for (User user : FileInput.userDataArray) {

				// Write each index of the arraylist onto the text file to save
				writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getActivityLevel() + ","
						+ user.getLessonLevel() + "," + user.getExamScore());
				writer.newLine(); // Adds a new line for the next user
			}

			// Close the writer object
			writer.close(); // Ensures all data is written to the file
		}

		catch (IOException e) { // Handles IO Exception
			JOptionPane.showMessageDialog(this, "Error saving user information: " + e.getMessage(), "File Write Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// Automatic generated method for action listener
	private void addActionListener(ActivityFrame activityFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	public void actionPerformed(ActionEvent event) {

		// If the lessons frame button is clicked
		if (event.getSource() == buttonArray[0]) {

			// Redirects page to lesson frame
			dispose();
			LessonsFrame lessonFrame = new LessonsFrame();
		}

		// If the exam frame button is clicked
		else if (event.getSource() == buttonArray[1]) {

			// Redirects frame to the exam frame
			dispose();
			ExamFrame examFrame = new ExamFrame();

		}

		// If the first level is clicked
		else if (event.getSource() == levelButtonArray[0]) {

			// Give game rules
			String gameRules = "Welcome to the Matching Definitions Game!\n\n" + "Objective:\n"
					+ "Match each term on the left with its correct definition on the right.\n\n" + "Instructions:\n"
					+ "1. Click on a term and then click on what you think is the correct definition.\n"
					+ "2. If they match, both will disappear, signaling a correct match.\n"
					+ "3. Try to clear the board by matching all the terms correctly.\n"
					+ "4. Click the finish button after clearing the board to complete the level.\n"
					+ "5. Click the exit button to abandon the game and lose the level. \n \n"
					+ "Good luck and have fun!";

			// Redirects page to the game page
			dispose();
			ActivityLevel1Frame activityLevelFrame = new ActivityLevel1Frame();

			// Displays rules message
			JOptionPane.showMessageDialog(null, gameRules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);

		}

		// If the second level is clicked
		else if (event.getSource() == levelButtonArray[1]) {

			// Game rules
			String gameRules = "Welcome to the Fill in the Blank Game!\n\n" + "Objective:\n"
					+ "Fill in all the blanks with the correct terms to complete the sentences correctly.\n\n"
					+ "Instructions:\n" + "1. Type your answer into each blank space provided.\n"
					+ "2. Answers are case sensitive, so be sure to use the correct capitalization.\n"
					+ "3. After filling in the blanks, click the 'Check Answers' button.\n"
					+ "4. Each correct answer will turn the text field green; incorrect answers will turn red.\n"
					+ "5. You must correct all answers highlighted in red before you can finish the level.\n"
					+ "6. You can attempt to correct your answers as many times as needed.\n"
					+ "7. Click the 'Finish' button only once all answers are correct to complete the level.\n"
					+ "8. If you exit before completing, your progress will not be saved.\n\n"
					+ "Good luck, and pay attention to detail!";

			// Redirects the page to the level
			dispose();
			ActivityLevel2Frame activityLevelFrame = new ActivityLevel2Frame();

			// Displays the rules to the user
			JOptionPane.showMessageDialog(null, gameRules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);

		}

		// If the 3rd level is clicked
		else if (event.getSource() == levelButtonArray[2]) {

			// Game rules
			String gameRules = "Welcome to the Matching Definitions Game!\n\n" + "Objective:\n"
					+ "Match each term on the left with its correct definition on the right.\n\n" + "Instructions:\n"
					+ "1. Click on a term and then click on what you think is the correct definition.\n"
					+ "2. If they match, both will disappear, signaling a correct match.\n"
					+ "3. Try to clear the board by matching all the terms correctly.\n"
					+ "4. Click the finish button after clearing the board to complete the level.\n"
					+ "5. Click the exit button to abandon the game and lose the level. \n \n"
					+ "Good luck and have fun!";

			// Redirects page to the level
			dispose();
			ActivityLevel3Frame activityLevelFrame = new ActivityLevel3Frame();

			// Displays the rules to user
			JOptionPane.showMessageDialog(null, gameRules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);

		}

		// If the 4th level is clicked
		else if (event.getSource() == levelButtonArray[3]) {

			// Game rules
			String gameRules = "Welcome to the Fill in the Blank Game!\n\n" + "Objective:\n"
					+ "Fill in all the blanks with the correct terms to complete the sentences correctly.\n\n"
					+ "Instructions:\n" + "1. Type your answer into each blank space provided.\n"
					+ "2. Answers are case sensitive, so be sure to use the correct capitalization.\n"
					+ "3. After filling in the blanks, click the 'Check Answers' button.\n"
					+ "4. Each correct answer will turn the text field green; incorrect answers will turn red.\n"
					+ "5. You must correct all answers highlighted in red before you can finish the level.\n"
					+ "6. You can attempt to correct your answers as many times as needed.\n"
					+ "7. Click the 'Finish' button only once all answers are correct to complete the level.\n"
					+ "8. If you exit before completing, your progress will not be saved.\n\n"
					+ "Good luck, and pay attention to detail!";

			// Redirects page to the level
			dispose();
			ActivityLevel4Frame activityLevelFrame = new ActivityLevel4Frame();

			// Displays the rules
			JOptionPane.showMessageDialog(null, gameRules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);

		}

		// If the logout button is clicked
		else if (event.getSource() == logoutButton) {

			// Saves the information to a text file
			saveInfo();

			// Redirects the page to the sign up frame
			dispose();
			SignUpFrame signUpFrame = new SignUpFrame();
		}

	}

}
