/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Lessons Frame
 * 
 * Description:
 * The LessonsFrame class provides a user interface for accessing various lessons in the app.
 * It has a navigation panel with buttons that link to different lesson levels, dynamically updated based on the user's progress.
 * Additional navigation options to other sections of the application and a logout button are also provided.
 * 
 * Major Skills:
 * 	GUI component handling using Java Swing.
 * 	Event-driven programming for user interaction.
 * 	Dynamic GUI updates based on the user's progress.
 * 
 * Added Features:
 *		1. Dynamic display of lesson levels with icons indicating the current progress.
 *		2. Logout functionality that ensures user progress is saved before exiting.
 *		3. Custom UI design for a consistent user experience.
 * 
 * Areas of Concern:
 * 	Ensuring data integrity when saving user progress.
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Documentation Referenced:
 * Learned to write onto a text file using BufferedWriter:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html 
 * 
 */

//Import necessary java packages
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

//Create class extending into JFrame
public class LessonsFrame extends JFrame implements ActionListener {

	// Declare header panel
	private JPanel headerPanel = new JPanel();

	// Create JButton array for the frame buttons
	private JButton[] buttonArray = new JButton[2];
	private ImageIcon[] buttonImages = { new ImageIcon("images/activityButton.png"),
			new ImageIcon("images/examButton.png") };

	// JButton for the logout button
	private JButton logoutButton = new JButton();
	private ImageIcon logoutIcon = new ImageIcon("images/logoutButton.png");

	// JLabel for the logo
	private JLabel logoLabel = new JLabel();
	private ImageIcon logoIcon = new ImageIcon("images/logoImage.png");

	// JLabel for the "selected learning frame" icon
	private JLabel learningLabel = new JLabel();
	private ImageIcon learningIcon = new ImageIcon("images/learningButtonSelected.png");

	// JButton array for the multiple levels
	private JButton[] levelButtonArray = new JButton[4];
	private ImageIcon[] levelIconArray = { new ImageIcon("images/lessonStartLevelImage.png"),
			new ImageIcon("images/lessonCompletedLevelImage.png"), new ImageIcon("images/lockedLevelImage.png") };

	//Border object
	private Border grayLine = BorderFactory.createLineBorder(new Color(0x37464F), 4);

	//Retrieve the user's lesson level from user data
	private int lessonLevel = FileInput.userDataArray.get(SignUpFrame.userIdentity).getLessonLevel();

	//Constructor
	public LessonsFrame() {
		
		//Generate the panel
		generatePanel();
		
		//Generate the title
		generateTitle();
		
		//Generate the frame
		generateFrame();
		
		//Generate the levels
		generateLevels();
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

	// Method to generate the header frame buttons
	private void generateHeaderButtons() {

		// Adds the "selected" page icon (so learningButton is highlighted)
		learningLabel.setBounds(56, 148, 232, 60);
		learningLabel.setIcon(learningIcon);

		headerPanel.add(learningLabel);

		// For loop to place the buttons efficiently
		for (int index = 0; index < buttonArray.length; index++) {
			buttonArray[index] = new JButton();
			// Adds the action listener to the button
			buttonArray[index].addActionListener(this);

			// Sets the bounds of the buttons
			buttonArray[index].setBounds(56, 256 + 100 * index, 232, 60);

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

		// Individually set the bounds and add the level button to the frame
		levelButtonArray[0].setBounds(683, 63, 95, 95);
		add(levelButtonArray[0]);

		levelButtonArray[1].setBounds(460, 210, 95, 95);
		add(levelButtonArray[1]);

		levelButtonArray[2].setBounds(714, 282, 95, 95);
		add(levelButtonArray[2]);

		levelButtonArray[3].setBounds(557, 450, 95, 95);
		add(levelButtonArray[3]);

		// Checks the playable levels of the user
		checkLevel();

	}

	// Method to check the user's locked levels
	private void checkLevel() {
		// If the user hasn't finished any levels
		if (lessonLevel == 0) {

			// Set level 1 as a start icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[0]);
			levelButtonArray[0].addActionListener(this);
			levelButtonArray[0].setBounds(683, 43, 114, 145);
		}

		// If the user has finished level 1
		else if (lessonLevel == 1) {

			// Set level 1 to the completed icon and add action listener to it
			levelButtonArray[0].setIcon(levelIconArray[1]);
			levelButtonArray[0].addActionListener(this);

			// Set level 2 as a start icon and add an action listener to it
			levelButtonArray[1].setIcon(levelIconArray[0]);
			levelButtonArray[1].addActionListener(this);
			levelButtonArray[1].setBounds(460, 185, 114, 145);

		}

		// If user has finished level 2
		else if (lessonLevel == 2) {

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
		else if (lessonLevel == 3) {

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
		else if (lessonLevel == 4) {

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

		// Logo set bounds
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

	// Automatic actionlistener method
	private void addActionListener(LessonsFrame lessonsFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	public void actionPerformed(ActionEvent event) {

		// If activity frame button is clicked
		if (event.getSource() == buttonArray[0]) {

			// Redirects the page to the activity frame
			dispose();
			ActivityFrame activityFrame = new ActivityFrame();
		}

		// If the exam frame button is clicked
		else if (event.getSource() == buttonArray[1]) {

			// Redirects the page to the exam frame
			dispose();
			ExamFrame examFrame = new ExamFrame();

		}

		// If the first level is clicked
		else if (event.getSource() == levelButtonArray[0]) {

			// Redirects page to the level
			dispose();
			LessonLevel1Frame activityLevelFrame = new LessonLevel1Frame();

		}

		// If the second level is clicked
		else if (event.getSource() == levelButtonArray[1]) {

			// Redirects page to the level
			dispose();
			LessonLevel2Frame activityLevelFrame = new LessonLevel2Frame();

		}

		// If the third level is clicked
		else if (event.getSource() == levelButtonArray[2]) {

			// Redirects page to the level
			dispose();
			LessonLevel3Frame activityLevelFrame = new LessonLevel3Frame();

		}

		// If the fourth level is clicked
		else if (event.getSource() == levelButtonArray[3]) {

			// Redirects page to the level
			dispose();
			LessonLevel4Frame activityLevelFrame = new LessonLevel4Frame();

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
