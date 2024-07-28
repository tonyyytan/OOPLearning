
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Menu Frame
 * 
 * Description:
 * This class creates a main menu frame for the application, providing navigation buttons that lead to
 * different parts of the application such as learning modules, activities, and exams. The frame also
 * includes a logout button for exiting the session.
 * 
 * Major Skills:
 * 	Swing GUI design and layout management.
 * 	Event handling for button interactions.
 * 	Design patterns for user interface consistency.
 * 
 * Added Features:
 *		1. Integration of a logout button which saves information
 * 
 * Areas of Concern:
 * 	None
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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

//Create the class extending into JFrame
public class MenuFrame extends JFrame implements ActionListener {

	// JPanel for the header
	private JPanel headerPanel = new JPanel();

	// JButton array for the header buttons
	private JButton[] buttonArray = new JButton[3];
	private ImageIcon[] buttonImages = { new ImageIcon("images/learningButton.png"),
			new ImageIcon("images/activityButton.png"), new ImageIcon("images/examButton.png") };

	// JButton of the logout
	private JButton logoutButton = new JButton();
	private ImageIcon logoutIcon = new ImageIcon("images/logoutButton.png");

	// JLabel for the logo
	private JLabel logoLabel = new JLabel();
	private ImageIcon logoIcon = new ImageIcon("images/logoImage.png");

	// Welcome page label
	private JLabel welcomeLabel = new JLabel();
	private ImageIcon welcomeIcon = new ImageIcon("images/titlePage.png");

	// Border object that is gray
	private Border grayLine = BorderFactory.createLineBorder(new Color(0x37464F), 4);

	// Constructor of the class
	public MenuFrame() {
		// Generates the header panel
		generatePanel();

		// Generates the title
		generateTitle();

		// Generates the frame
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

	// Method to generate the header buttons
	private void generateHeaderButtons() {

		// For loop to efficiently place buttons
		for (int index = 0; index < buttonArray.length; index++) {
			// Creates the button
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

	// Method to generate the title/logo of the frame
	private void generateTitle() {

		// Set bounds of logo
		logoLabel.setBounds(57, 42, 236, 58);

		// Changes font
		logoLabel.setIcon(logoIcon);
		headerPanel.add(logoLabel);
		
		welcomeLabel.setBounds(387, 79, 793, 470);
		welcomeLabel.setIcon(welcomeIcon);
		
		add(welcomeLabel);
	}

	// Method to generate the frame properties
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

	// Automatic method for actions to work on the frame
	private void addActionListener(MenuFrame menuFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	@Override
	public void actionPerformed(ActionEvent event) {

		// If the lessons frame button is clicked
		if (event.getSource() == buttonArray[0]) {

			// Redirects page to the lesson frame
			dispose();
			LessonsFrame lessonFrame = new LessonsFrame();
		}

		// If the activity frame button is clicked
		else if (event.getSource() == buttonArray[1]) {

			// Redirects page to the activity frame
			dispose();
			ActivityFrame activityFrame = new ActivityFrame();
		}

		// If the exam frame button is clicked
		else if (event.getSource() == buttonArray[2]) {

			// Redirects page to the exam frame
			dispose();
			ExamFrame examFrame = new ExamFrame();
		}

		// If the log out button is clicked
		else if (event.getSource() == logoutButton) {

			// Saves the user's information
			saveInfo();

			// Redirects to the sign up frame
			dispose();
			SignUpFrame signUpFrame = new SignUpFrame();
		}
	}
}
