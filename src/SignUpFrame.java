
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: SignUp Frame
 * 
 * Description:
 * This class creates a sign-up frame for user registration in the learning app.
 * It provides text fields for entering a username and password, and buttons for submitting the
 * registration or switching to a login frame. The user information is saved to a text file. In
 * addition, the sign up information is validated with the text file to limit duplicates.
 * 
 * Major Skills:
 * 	File input/output for saving user data.
 * 	Handling GUI components using Java Swing.
 * 	Event handling for buttons and text fields.
 * 
 * Added Features:
 *		1. Placeholder text in text fields that disappears when the field is focused.
 *		2. Password hidden while typing
 *		3. Validates that no duplicate users are created and non-empty fields before saving.
 * 		4. Integrates default user data that gets saved throughout the program.
 * 
 * Areas of Concern:
 * 	None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Learned how to implement JTextField components effectively:
 * https://www.geeksforgeeks.org/java-swing-jtextfield/
 * 
 * Learned to disable auto focus on JTextField:
 * https://stackoverflow.com/questions/30309172/how-to-disable-automatic-focus-on-textfield
 * 
 * Learned to write onto a text file using BufferedWriter:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html
 */

//Import necessary packages
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

//Declare the class name extending into JFrame
public class SignUpFrame extends JFrame implements ActionListener, FocusListener {

	// Create placeholders for the textfields
	private static final String USERNAME_PLACEHOLDER = "Email or username ";
	private static final String PASSWORD_PLACEHOLDER = "Password ";

	// Declare JTextfields for the username/password
	private JTextField usernameField = new JTextField(USERNAME_PLACEHOLDER);
	private JPasswordField passwordField = new JPasswordField(PASSWORD_PLACEHOLDER);

	// Declare a JButton array for the signup and already owned account button
	private JButton[] buttonArray = new JButton[2];
	private ImageIcon[] buttonImages = { new ImageIcon("images/signUpButton.png"),
			new ImageIcon("images/alreadyAccountButton.png") };

	// Declare the SignUp text label
	private JLabel signUpLabel = new JLabel();

	// Declare variables to track the saved username and passwords
	private String savedUsername;
	private String savedPassword;
	
	public static int userIdentity;

	// Constructor of the class
	public SignUpFrame() {

		// Call method to generate the text fields
		generateTextFields();

		// Call method to generate the buttons
		generateButtons();

		// Call method to generate the title
		generateTitle();

		// Call method to generate the frame window
		generateFrame();

		// Focuses on the window frame first, instead of text field
		requestFocusInWindow();

	}

	// Method to generate the text fields of the class
	// https://www.geeksforgeeks.org/java-swing-jtextfield/
	private void generateTextFields() {

		// Define the properties of the text field
		usernameField.setBounds(368, 191, 464, 47);
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 14));

		// Adds focus listener to the field
		usernameField.addFocusListener(this);

		// Sets the field's background
		usernameField.setBackground(new Color(0x202F36));
		usernameField.setForeground(Color.gray); // Sets the default text color to gray

		// Adds to the frame
		add(usernameField);

		// Sets the text cursor color to white
		usernameField.setCaretColor(Color.white);

		// Define the properties of the password field
		passwordField.setBounds(368, 260, 464, 47);
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));

		// Adds focus listener to the field
		passwordField.addFocusListener(this);
		passwordField.setBackground(new Color(0x202F36));
		passwordField.setForeground(Color.gray); // Sets the default text color to gray

		// Disable hidden char for the password, sets it back to normal char
		passwordField.setEchoChar((char) 0); // using ASCII TABLE
		add(passwordField);

		// Sets the text cursor color to white
		passwordField.setCaretColor(Color.white);

	}

	// Method to generate the buttons in the frame
	private void generateButtons() {

		// For loop to efficiently create the buttons
		for (int index = 0; index < buttonArray.length; index++) {
			// Create the button
			buttonArray[index] = new JButton();
			// Adds the action listener to the button
			buttonArray[index].addActionListener(this);

			// Sets the bounds of the buttons
			buttonArray[index].setBounds(368, 330 + 79 * index, 464, 71);

			// Define the properties of the button
			buttonArray[index].setFocusPainted(false);
			buttonArray[index].setBorderPainted(false);
			buttonArray[index].setFocusable(true);
			buttonArray[index].setOpaque(true);
			buttonArray[index].setIcon(buttonImages[index]);

			// Adds the button to the frame
			add(buttonArray[index]);
		}
	}

	// Method to generate the "SIGN UP" title
	private void generateTitle() {

		// Sets the text to "SIGN UP"
		signUpLabel.setText("SIGN UP");
		signUpLabel.setBounds(543, 134, 114, 35);

		// Changes font
		signUpLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		signUpLabel.setForeground(Color.white);
		add(signUpLabel);
	}

	// Method to generate the frame and define its properties
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

	// Automatic method for actions to work on the frame
	private void addActionListener(SignUpFrame signUpFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	public void actionPerformed(ActionEvent event) {

		// If the "sign up" button is clicked
		if (event.getSource() == buttonArray[0]) {

			// Checks if the info is duplicate
			if (checkInfo() == true) {

				// Saves the info
				saveInfo();

				// Disposes the frame, redirects to menu
				dispose();
				MenuFrame menuFrame = new MenuFrame();
			}
		}

		// If the "already have account" button is clicked
		else if (event.getSource() == buttonArray[1]) {

			// Disposes the frame, redirects to login page
			dispose();
			LoginFrame loginFrame = new LoginFrame();
		}
	}

	// Boolean return method to check the information
	private boolean checkInfo() {

		// Gets the string input form the fields
		savedUsername = usernameField.getText();
		savedPassword = new String(passwordField.getPassword());

		// If the username/password is the same as the placeholder text, (means the user
		// hasn't entered anything)
		if (savedUsername.equals(USERNAME_PLACEHOLDER) || savedPassword.equals(PASSWORD_PLACEHOLDER)) {

			// Tell user to enter information
			JOptionPane.showMessageDialog(this, "Fields are empty. Please fill it in", "Error",
					JOptionPane.INFORMATION_MESSAGE);

			// Return false
			return false;
		}

		// Because of sequential coding next line of codes mean there is entered info
		// Enhanced for loop to check all of the user data
		for (User user : FileInput.userDataArray) {

			// Checks if the entered username is equal to any username in the data base
			if (user.getUsername().equals(savedUsername)) {

				// Outputs a message that the user is taken already
				JOptionPane.showMessageDialog(this, "Username taken.", "Error", JOptionPane.INFORMATION_MESSAGE);

				// Returns false
				return false;
			}

		}

		// Any other scenario than the above, then return true.
		return true;
	}

	// Void method to save the information entered
	// https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/io/BufferedWriter.html
	private void saveInfo() {

		// Add a new index into the arraylist with the entered username/password and
		// default stats
		FileInput.userDataArray.add(new User(savedUsername, savedPassword, 0, 0, -1.0));
		
		userIdentity = FileInput.userDataArray.size() - 1;

		// Try catch structure to write on the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt", false))) {

			// Use an enhanced for loop to read through the full arraylist of user data
			for (User user : FileInput.userDataArray) {

				// Write the fields/attributes of each object in the user data array, separated
				// by the delimiters
				writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getActivityLevel() + ","
						+ user.getLessonLevel() + "," + user.getExamScore());
				writer.newLine(); // Adds a new line for the next user
			}

			// Closes the writer object
			writer.close(); // Ensures all data is written to the file
		}

		catch (IOException e) { // Handles IO exception
			JOptionPane.showMessageDialog(this, "Error saving user information: " + e.getMessage(), "File Write Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// Method to determine focus implementations
	// https://stackoverflow.com/questions/30309172/how-to-disable-automatic-focus-on-textfield
	public void focusGained(FocusEvent event) {

		// If the username field is clicked to type a username
		if (event.getSource() == usernameField && usernameField.getText().equals(USERNAME_PLACEHOLDER)) {

			// Sets the text to be blank, so user can type
			usernameField.setText("");
			usernameField.setForeground(Color.white);

		}
		// If the password field is clicked to type a password
		else if (event.getSource() == passwordField
				&& new String(passwordField.getPassword()).equals(PASSWORD_PLACEHOLDER)) {

			// Sets the text to be empty, so user can type
			passwordField.setText("");

			// Sets the text to be the hidden char to hide the password
			passwordField.setEchoChar('\u2022'); // using ASCII TABLE
			passwordField.setForeground(Color.white);
		}
	}

	// If focus is lost on an object
	@Override
	public void focusLost(FocusEvent event) {

		// If the username field is no longer clicked + empty
		if (event.getSource() == usernameField && usernameField.getText().isEmpty()) {

			// Sets the username field back to the default text
			usernameField.setForeground(Color.gray);
			usernameField.setText(USERNAME_PLACEHOLDER);
		}

		// If the password field is no longer clicked + empty
		else if (event.getSource() == passwordField && new String(passwordField.getPassword()).isEmpty()) {

			// Sets the password field back to the default text
			passwordField.setForeground(Color.gray);
			passwordField.setText(PASSWORD_PLACEHOLDER);

			// Disable hidden char for the password, sets it back to normal char
			passwordField.setEchoChar((char) 0); // using ASCII TABLE
		}
	}
}
