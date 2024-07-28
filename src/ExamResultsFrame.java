
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Exam Results Frame
 * 
 * Description:
 * This class represents the exam results frame in the app. It displays the user's score
 * and a detailed result of each question answered in the previous session. Users can navigate back to other
 * sections or attempt the exam again.
 * 
 * Major Skills:
 * 	GUI components arrangement using Java Swing.
 * 	Event Handling for interactive elements.
 * 
 * Added Features:
 *	1. Dynamic results (user can see which question they got wrong/right)
 *	2. Functional buttons for retrying the exam or logging out.
 *	3. Displays previous scores
 * 
 * Areas of Concern:
 *  None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Documentation Reference:
 * Learned to write onto a text file using BufferedWriter:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html 
 * 
 */

//Import necessary java packages
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

//Create the class extending into JFrame
public class ExamResultsFrame extends JFrame implements ActionListener {

	// JPanel for header
	private JPanel headerPanel = new JPanel();

	// JButton array for header frame buttons
	private JButton[] buttonArray = new JButton[2];
	private ImageIcon[] buttonImages = { new ImageIcon("images/learningButton.png"),
			new ImageIcon("images/activityButton.png") };

	// JButton for logout button
	private JButton logoutButton = new JButton();
	private ImageIcon logoutIcon = new ImageIcon("images/logoutButton.png");

	// JLabel for logo
	private JLabel logoLabel = new JLabel();
	private ImageIcon logoIcon = new ImageIcon("images/logoImage.png");

	// JLabel for the "selected exam" frame button
	private JLabel examLabel = new JLabel();
	private ImageIcon examIcon = new ImageIcon("images/examButtonSelected.png");

	// JLabel 2d array for results of the ten questions
	private JLabel[][] resultsIconArray = new JLabel[2][5];
	private JLabel[][] resultsLabelArray = new JLabel[2][5];

	// ImageIcons for correct and wrong answers
	private ImageIcon correctImage = new ImageIcon("images/correctAnswer.png");
	private ImageIcon wrongImage = new ImageIcon("images/wrongAnswer.png");

	// Retrieve the user's exam score from the text file, multiply by 100 to get
	// percentage
	private String examScore = Double
			.toString(FileInput.userDataArray.get(SignUpFrame.userIdentity).getExamScore() * 10);

	// JButton for retrying exam
	private JButton retryButton = new JButton();
	private ImageIcon retryIcon = new ImageIcon("images/retryButton.png");

	// JLabel array to display text results
	private JLabel[] informationLabelArray = new JLabel[2];
	private String[] informationArray = { "Here are your results!", "You scored " + examScore + "%!" };

	// Border object
	private Border grayLine = BorderFactory.createLineBorder(new Color(0x37464F), 4);

	// Constructor
	public ExamResultsFrame() {

		// Generate the panel
		generatePanel();

		// Generate the title
		generateTitle();

		// Generate the exam results
		generateResults();

		// Generate the text results
		generateText();

		// Generate the retry button
		generateRetryButton();

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
	}

	// Method to generate the text results
	private void generateText() {

		// For loop to efficiently place the JLabels
		for (int index = 0; index < informationLabelArray.length; index++) {
			informationLabelArray[index] = new JLabel();
			informationLabelArray[index].setText(informationArray[index]);
			informationLabelArray[index].setBounds(552, 47 + 430 * index, 437, 52);

			// Changes font
			informationLabelArray[index].setFont(new Font("Dialog", Font.BOLD, 36));
			informationLabelArray[index].setForeground(Color.white);
			add(informationLabelArray[index]);
		}
	}

	// Method to generate the retry button
	private void generateRetryButton() {
		retryButton = new JButton();
		// Adds the action listener to the button
		retryButton.addActionListener(this);

		// Sets the bounds of the buttons
		retryButton.setBounds(1000, 500, 154, 60);

		// Define the properties of the button
		retryButton.setFocusPainted(false);
		retryButton.setBorderPainted(false);
		retryButton.setFocusable(true);
		retryButton.setOpaque(true);
		retryButton.setIcon(retryIcon);

		add(retryButton);
	}

	// Method to generate the header frame buttons
	private void generateHeaderButtons() {
		// Adds the "selected" page icon (so learningButton is highlighted)
		examLabel.setBounds(56, 343, 232, 60);
		examLabel.setIcon(examIcon);

		headerPanel.add(examLabel);

		// For loop to efficiently place the buttons
		for (int index = 0; index < buttonArray.length; index++) {
			buttonArray[index] = new JButton();
			// Adds the action listener to the button
			buttonArray[index].addActionListener(this);

			// Sets the bounds of the buttons
			buttonArray[index].setBounds(56, 148 + 100 * index, 232, 60);

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

	// Generates the title
	private void generateTitle() {

		logoLabel.setBounds(57, 42, 236, 58);

		// Changes icon
		logoLabel.setIcon(logoIcon);
		headerPanel.add(logoLabel);
	}

	// Generates the results of the quiz
	private void generateResults() {

		// Counter to determine the question #
		int count = 0;

		// Nested for loop to place the JLabel 2d array icons
		for (int row = 0; row < resultsIconArray.length; row++) {
			for (int col = 0; col < resultsIconArray[row].length; col++) {

				// Create the JLabel icon, set the bounds, and assign an icon
				resultsIconArray[row][col] = new JLabel();
				resultsIconArray[row][col].setBounds(538 + 232 * row, 143 + 60 * col, 232, 60);
				resultsIconArray[row][col].setIcon(determineResult(count));

				add(resultsIconArray[row][col]);

				// Accumulate the counter
				count++;

				// Create the JLabel text on top of each of the icons
				resultsLabelArray[row][col] = new JLabel();
				resultsLabelArray[row][col].setText("Question " + count);
				resultsLabelArray[row][col].setBounds(80, 0, 232, 60);
				resultsLabelArray[row][col].setForeground(Color.white); // set text to white
				resultsIconArray[row][col].add(resultsLabelArray[row][col]);

			}
		}
	}

	// ImageIcon return method to determine the results
	private ImageIcon determineResult(int index) {

		// If the question was correct
		if (TestFrame.questionResults[index] == true) {
			// Return correct image
			return correctImage;
		}

		// If answer was wrong
		else {
			// Return the wrong image
			return wrongImage;
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

	// Automatically generated method for action listener
	private void addActionListener(ExamResultsFrame examResultsFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	@Override
	public void actionPerformed(ActionEvent event) {

		// If the retry button is clicked
		if (event.getSource() == retryButton) {

			// Redirects the page to the testing frame
			dispose();
			TestFrame testFrame = new TestFrame();
		}

		// If the lessons frame button is clicked
		else if (event.getSource() == buttonArray[0]) {

			// Redirects the page to the lessons frame
			dispose();
			LessonsFrame lessonFrame = new LessonsFrame();
		}

		// if the activity frame button is clicked
		else if (event.getSource() == buttonArray[1]) {

			// Redirects the page to the activity frame
			dispose();
			ActivityFrame activityFrame = new ActivityFrame();

		}

		// If the logout button is clicked
		else if (event.getSource() == logoutButton) {

			// Saves the user's info
			saveInfo();

			// Redirects the page to the sign up frame
			dispose();
			SignUpFrame signUpFrame = new SignUpFrame();
		}

	}
}
