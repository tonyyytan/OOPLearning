
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Login Frame
 * 
 * Description:
 * This class creates a login frame for user authentication in the learning app.
 * It provides text fields for entering a username and password, and buttons for logging in
 * or switching to a registration frame. It checks user credentials against stored data in a text file.
 * 
 * Major Skills:
 * 	File input/output for reading user data.
 * 	Handling GUI components using Java Swing.
 * 	Event handling for buttons, text fields, and focus events.
 * 
 * Added Features:
 * 	1. Placeholder text in text fields that disappears when the field is focused.
 * 	2. Password hidden while typing, revealed when field is not focused.
 * 	3. Validates user credentials against stored data, with error messages for incorrect or missing inputs.
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
 */

//Import necessary java packages
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

//Declare class extending into JFrame
public class LoginFrame extends JFrame implements ActionListener, FocusListener {

	// Create placeholders for the textfields
	private static final String USERNAME_PLACEHOLDER = "Email or username ";
	private static final String PASSWORD_PLACEHOLDER = "Password ";

	// Declare JTextfields for the username/password
	private JTextField usernameField = new JTextField(USERNAME_PLACEHOLDER);
	private JPasswordField passwordField = new JPasswordField(PASSWORD_PLACEHOLDER);

	// JButton for the login button
	private JButton loginButton = new JButton();
	private ImageIcon loginImage = new ImageIcon("images/loginButton.png");

	// JButton for the redirecting button to signup page
	private JButton signUpButton = new JButton("Don't have an account? Create one now!");

	// JLabel for title
	private JLabel loginLabel = new JLabel();

	// Stores the entered information
	private String enteredUsername;
	private String enteredPassword;

	// Gets a unique user identity (index of the arraylist)

	// Constructor of the class
	public LoginFrame() {
		// Generates the text fields
		generateTextFields();

		// Generates the buttons
		generateButtons();

		// Generates the title
		generateTitle();

		// Generates the frame
		generateFrame();

		// Focuses on the window frame first, instead of text field
		requestFocusInWindow();

	}

	// Method to generate the text fields of the class
	// https://www.geeksforgeeks.org/java-swing-jtextfield/
	private void generateTextFields() {

		// Define the properties of the text field
		usernameField.setBounds(368, 242, 464, 47);
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 14));

		// Adds focus listener to the field
		usernameField.addFocusListener(this);
		usernameField.setBackground(new Color(0x202F36));
		usernameField.setForeground(Color.gray);

		// Adds to the frame
		add(usernameField);

		// Sets the text cursor color to white
		usernameField.setCaretColor(Color.white);

		// Define the properties of the password field
		passwordField.setBounds(368, 312, 464, 47);
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));
		passwordField.addFocusListener(this);
		passwordField.setBackground(new Color(0x202F36));
		passwordField.setForeground(Color.gray);

		// Disable hidden char for the password, sets it back to normal char
		passwordField.setEchoChar((char) 0); // using ASCII TABLE
		add(passwordField);

		// Sets the text cursor color to white
		passwordField.setCaretColor(Color.white);

	}

	// Method to generate the buttons in the frame
	private void generateButtons() {

		// Adds the action listener to the button
		loginButton.addActionListener(this);

		// Sets the bounds of the buttons
		loginButton.setBounds(368, 383, 464, 71);

		// Define the properties of the button
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setFocusable(true);
		loginButton.setOpaque(true);
		loginButton.setIcon(loginImage);

		// Adds the button to the frame
		add(loginButton);

		// Adds the action listener to the button
		signUpButton.addActionListener(this);

		// Sets the bounds of the buttons
		signUpButton.setBounds(369, 471, 417, 27);

		// Define the properties of the button
		signUpButton.setFocusPainted(false);
		signUpButton.setBorderPainted(false);
		signUpButton.setFocusable(true);
		signUpButton.setOpaque(true);
		signUpButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		signUpButton.setForeground(Color.white);
		signUpButton.setBackground(new Color(0x131F24));
		// Adds the button to the frame
		add(signUpButton);
	}

	// Method to generate the "LOG IN" title
	private void generateTitle() {

		// Sets the text to "LOG IN"
		loginLabel.setText("LOG IN");
		loginLabel.setBounds(553, 170, 94, 35);

		// Changes font
		loginLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		loginLabel.setForeground(Color.white);
		add(loginLabel);
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
	private void addActionListener(LoginFrame loginFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	public void actionPerformed(ActionEvent event) {

		// If the login button is clicked
		if (event.getSource() == loginButton) {

			// If the check info with the text file returns true (matches a user in the
			// data)
			if (checkInfo() == true) {

				// Display success message
				JOptionPane.showMessageDialog(this, "Logging in!", "Success", JOptionPane.INFORMATION_MESSAGE);

				// Dipose and redirect frame to menu
				dispose();
				MenuFrame menuFrame = new MenuFrame();
			}
		}

		// If user clicks to redirect back to sign up page
		else if (event.getSource() == signUpButton) {

			// Dipose and redirects frame to sign up frame
			dispose();
			SignUpFrame signUpFrame = new SignUpFrame();
		}
	}

	// Method to validate entered information with the user data set
	private boolean checkInfo() {

		// Store the string inputs from the fields
		enteredUsername = usernameField.getText();
		enteredPassword = new String(passwordField.getPassword());

		// If the username/password is the same as the placeholder text, (means the user
		// hasn't entered anything)
		if (enteredUsername.equals(USERNAME_PLACEHOLDER) || enteredPassword.equals(PASSWORD_PLACEHOLDER)) {

			// Tell user to enter information
			JOptionPane.showMessageDialog(this, "Fields are empty. Please fill it in", "Error",
					JOptionPane.INFORMATION_MESSAGE);

			// Return false
			return false;
		}

		// Use for loop to check each index of the arraylist
		for (int index = 0; index < FileInput.userDataArray.size(); index++) {

			// If the username and password matches with a user in the data set
			if (FileInput.userDataArray.get(index).getUsername().equals(enteredUsername)
					&& FileInput.userDataArray.get(index).getPassword().equals(enteredPassword)) {

				// Assign the user identity to the index
				SignUpFrame.userIdentity = index;

				// Return true
				return true;
			}

		}

		// Because of sequential programming, all other cases are covered
		// Return incorrect username/password message
		JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Error",
				JOptionPane.INFORMATION_MESSAGE);

		// Return false
		return false;
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

	//Method to determine if focus is lost on an object
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
