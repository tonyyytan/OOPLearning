
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Exam Frame
 * 
 * Description:
 * The ExamFrame class is designed to facilitate a real exam/test environment.
 * It displays the rules, user's last score, and options to start the exam.
 * The frame provides a panel with navigation to other sections like learning or activities and includes a logout option.
 * 
 * Major Skills:
 *  GUI Design using Java Swing.
 *  Event Handling in Java.
 *  File input/output operations to save user data.
 * 
 * Added Features:
 * 	1. Dynamic display of past exam scores.
 * 
 * Areas of Concern:
 *   None
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

//Create class extending into JFrame
public class ExamFrame extends JFrame implements ActionListener {

	//JPanel for header
	private JPanel headerPanel = new JPanel();

	//JButton array for the header frame buttons
	private JButton[] buttonArray = new JButton[2];
	private ImageIcon[] buttonImages = { new ImageIcon("images/learningButton.png"),
			new ImageIcon("images/activityButton.png") };

	//JButton for the logout button
	private JButton logoutButton = new JButton();
	private ImageIcon logoutIcon = new ImageIcon("images/logoutButton.png");

	//JLabel for the logo
	private JLabel logoLabel = new JLabel();
	private ImageIcon logoIcon = new ImageIcon("images/logoImage.png");

	//JLabel for the "selected exam" frame button
	private JLabel examLabel = new JLabel();
	private ImageIcon examIcon = new ImageIcon("images/examButtonSelected.png");

	//Retrieve the exam score from user data
	private double examScore = FileInput.userDataArray.get(SignUpFrame.userIdentity).getExamScore();

	//JLabel array for the exam title/past score
	private JLabel[] examInfoLabelArray = new JLabel[2];
	private String[] examInfoStringArray = { "Are you ready for this exam?",
			"Your previous score: " + examScore * 10 + "%" };

	//JButton array for the rules button and the start button
	private JButton[] examInfoButtonArray = new JButton[2];
	private ImageIcon[] examInfoImages = { new ImageIcon("images/rulesButton.png"),
			new ImageIcon("images/beginButton.png") };

	//Border object
	private Border grayLine = BorderFactory.createLineBorder(new Color(0x37464F), 4);

	//Constructor
	public ExamFrame() {
		
		//Generate the panel
		generatePanel();
		
		//Generate the title
		generateTitle();
		
		//Generate the title text and past exam score
		generateText();
		
		//Generate the buttons
		generateButtons();
		
		//Generate the frame
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

	//Generate the header buttons
	private void generateHeaderButtons() {

		// Adds the "selected" page icon (so learningButton is highlighted)
		examLabel.setBounds(56, 343, 232, 60);
		examLabel.setIcon(examIcon);

		headerPanel.add(examLabel);

		//Use for loop to place buttons efficiently
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

	//Method to generate the title
	private void generateTitle() {

		logoLabel.setBounds(57, 42, 236, 58);

		// Changes icon
		logoLabel.setIcon(logoIcon);
		headerPanel.add(logoLabel);
	}

	//Method to generate the exam text and past score
	private void generateText() {

		//If the user has a default score (-1.0)
		if (Double.toString(examScore).equals("-1.0")) {
			//Display past score as N/A
			examInfoStringArray[1] = "Your previous score: N/A";
		}

		//For loop to place label efficiently
		for (int index = 0; index < examInfoLabelArray.length; index++) {
			
			//Set the text according to string array and place button
			examInfoLabelArray[index] = new JLabel();
			examInfoLabelArray[index].setText(examInfoStringArray[index]);
			examInfoLabelArray[index].setBounds(522 + 37 * index, 106 + 327 * index, 557 - 74 * index, 51);

			// Changes font
			examInfoLabelArray[index].setFont(new Font("Dialog", Font.BOLD, 36));
			examInfoLabelArray[index].setForeground(Color.white);
			add(examInfoLabelArray[index]);
		}
	}

	//Method to generate the exam buttons (rules and start)
	private void generateButtons() {

		//For loop to efficiently place the buttons
		for (int index = 0; index < examInfoButtonArray.length; index++) {
			examInfoButtonArray[index] = new JButton();
			// Adds the action listener to the button
			examInfoButtonArray[index].addActionListener(this);

			// Sets the bounds of the buttons
			examInfoButtonArray[index].setBounds(672, 207 + 98 * index, 197, 77);

			// Define the properties of the button
			examInfoButtonArray[index].setFocusPainted(false);
			examInfoButtonArray[index].setBorderPainted(false);
			examInfoButtonArray[index].setFocusable(true);
			examInfoButtonArray[index].setOpaque(true);
			examInfoButtonArray[index].setIcon(examInfoImages[index]);

			// Adds the button to the frame
			add(examInfoButtonArray[index]);
		}
	}

	//Method to generate the frame and define its properties
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

	//Automatic method generated to use action listener
	private void addActionListener(ExamFrame examFrame) {
		// TODO Auto-generated method stub

	}

	//Method to implement button actions
	public void actionPerformed(ActionEvent event) {
		
		//If the lessons frame button is clicked
		if (event.getSource() == buttonArray[0]) {
			
			//Redirect the page to lessons frame
			dispose();
			LessonsFrame lessonFrame = new LessonsFrame();
		}

		//If the activity frame button is clicked
		else if (event.getSource() == buttonArray[1]) {
			
			//Redirects page to the activity frame
			dispose();
			ActivityFrame activityFrame = new ActivityFrame();

		}

		//If the rules button is clicked
		else if (event.getSource() == examInfoButtonArray[0]) {
			//String of rules
			String rules = "Exam Rules:\n" + "- Total of 10 multiple choice questions.\n"
					+ "- You have 10 minutes to complete this test.\n"
					+ "- All questions must be answered, and you cannot go back to questions.\n"
					+ "- Click the answer box to submit your answer.";

			//Display the rules in JOptionPane
			JOptionPane.showMessageDialog(null, rules, "Exam Rules", JOptionPane.INFORMATION_MESSAGE);
		}

		//If the "Begin" button is clicked
		else if (event.getSource() == examInfoButtonArray[1]) {
			
			//Start the exam and go to testing frame
			dispose();
			JOptionPane.showMessageDialog(null, "Starting exam...", "Exam", JOptionPane.INFORMATION_MESSAGE);
			TestFrame testFrame = new TestFrame();

		}

		//If the log out button is clicked
		else if (event.getSource() == logoutButton) {
			
			//Saves the user's info
			saveInfo();
			
			//Redirects page to the sign up frame
			dispose();
			SignUpFrame signUpFrame = new SignUpFrame();
		}
	}

}
