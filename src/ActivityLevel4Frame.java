
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Activity Level 2 Frame
 * 
 * Description:
 * This class creates an interactive fill-in-the-blank activity focused on coding concepts.
 * Users are presented with code snippets missing key pieces, which they must correctly fill in to advance. This frame
 * checks answers in real-time and provides immediate feedback on correctness, enhancing the learning experience.
 * 
 * Major Skills:
 *  Implementing GUI components using Java Swing for user interaction.
 *  Managing event handling to respond to user inputs and actions.
 *  Utilizing logical operations to validate user responses against correct answers.
 * 
 * Added Features:
 *  1. Immediate color-coded feedback to users for correct or incorrect entries.
 *  2. Functional buttons to navigate, check answers, or finalize the activity.
 *  3. Update of user progress upon successful completion or retry options for improvement.
 * 
 * Areas of Concern:
 * 	None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Additional documentation:
 * Learning to use JTextField:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//Create the class extending into JFrame
public class ActivityLevel4Frame extends JFrame implements ActionListener {

	// JLabel array for the coding examples
	private JLabel[] codeLabels = new JLabel[2];
	private ImageIcon[] codeImages = { new ImageIcon("images/fillBlankImage3.png"),
			new ImageIcon("images/fillBlankImage4.png") };

	// JLabel text of the activity title
	private JLabel activityTitleLabel = new JLabel();

	// JtextField to fill in the blank
	private JTextField[] fillInBlankTextField = new JTextField[3];

	// String array of the correct answers
	private String[] answers = { "new Scanner", "input.", "public MenuFrame()" };

	// JButtons for the utility buttons
	private JButton[] gameButtonArray = new JButton[3];
	private ImageIcon[] gameButtonIcons = { new ImageIcon("images/exitButton.png"),
			new ImageIcon("images/checkButton.png"), new ImageIcon("images/finishButton.png") };

	// Variable to keep track of the correct answers
	private int correctAnswers = 0;

	// Retrieve the user's activity level from the user data
	private int activityLevel = FileInput.userDataArray.get(SignUpFrame.userIdentity).getActivityLevel();

	// Constructor
	public ActivityLevel4Frame() {

		// Generate the title
		generateTitle();

		// Generate the coding examples
		generateExamples();

		// Generate the text fields
		generateTextFields();

		// Generate the utility buttons
		generateButtons();

		// Generate the frame
		generateFrame();
	}

	private void generateTitle() {
		// Sets the text to game name
		activityTitleLabel.setText("Fill in the blanks!");
		activityTitleLabel.setBounds(63, 37, 491, 56);

		// Changes font
		activityTitleLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		activityTitleLabel.setForeground(Color.white);
		add(activityTitleLabel);
	}

	private void generateExamples() {

		// Creates the first coding example
		codeLabels[0] = new JLabel();
		codeLabels[0].setBounds(63, 100, 900, 200);
		codeLabels[0].setIcon(codeImages[0]);

		// Creates the second coding example
		codeLabels[1] = new JLabel();
		codeLabels[1].setBounds(63, 300, 588, 250);
		codeLabels[1].setIcon(codeImages[1]);

		// Adds the examples to the frame
		add(codeLabels[0]);
		add(codeLabels[1]);
	}

	// Method to generate the utility buttons
	private void generateButtons() {

		// For loop to efficiently place the buttons
		for (int index = 0; index < gameButtonArray.length; index++) {

			// Create the buttons, set the bounds, and set the icon
			gameButtonArray[index] = new JButton();
			gameButtonArray[index].setBounds(634 + 175 * index, 460, 138, 59);
			gameButtonArray[index].setIcon(gameButtonIcons[index]);

			// Adds an action listener to the button
			gameButtonArray[index].addActionListener(this);
			add(gameButtonArray[index]);
		}
	}

	// Method to generate the text fields
	private void generateTextFields() {

		// Fill in blank question 1
		fillInBlankTextField[0] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[0].setBounds(130, 20, 85, 20);
		fillInBlankTextField[0].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[0].setBackground(new Color(0x202F36));
		fillInBlankTextField[0].setForeground(Color.white);

		// Adds to the frame
		codeLabels[0].add(fillInBlankTextField[0]);

		// Fill in blank question 2
		fillInBlankTextField[1] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[1].setBounds(175, 113, 50, 20);
		fillInBlankTextField[1].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[1].setBackground(new Color(0x202F36));
		fillInBlankTextField[1].setForeground(Color.white);

		// Adds to the frame
		codeLabels[0].add(fillInBlankTextField[1]);

		// Fill in blank question 3
		fillInBlankTextField[2] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[2].setBounds(50, 94, 140, 20);
		fillInBlankTextField[2].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[2].setBackground(new Color(0x202F36));
		fillInBlankTextField[2].setForeground(Color.white);

		// Adds to the frame
		codeLabels[1].add(fillInBlankTextField[2]);
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

	// Automatically generated action listener method
	private void addActionListener(ActivityLevel4Frame activityLevel4Frame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	@Override
	public void actionPerformed(ActionEvent event) {

		// If the check button is clicked
		if (event.getSource() == gameButtonArray[1]) {

			// Reset # of correct answers before every "check" button is clicked
			correctAnswers = 0;

			// Checks the text fields using for loop
			for (int index = 0; index < fillInBlankTextField.length; index++) {

				// If the textfields equal the answers
				if (fillInBlankTextField[index].getText().equals(answers[index])) {

					// Set background as green (correct)
					fillInBlankTextField[index].setBackground(new Color(0x36B49C));
					// Increment the correct answer #
					correctAnswers++;
				}

				// Otherwise set it as red (incorrect)
				else {
					fillInBlankTextField[index].setBackground(new Color(0xFF5757));
				}
			}
		}

		// If the exit button is clicked
		else if (event.getSource() == gameButtonArray[0]) {

			// Redirects the page back to the activity page
			dispose();
			ActivityFrame activityFrame = new ActivityFrame();
		}

		// If the finish button is clicked
		else if (event.getSource() == gameButtonArray[2]) {

			// Reset # of correct answers before every "check" button is clicked
			correctAnswers = 0;

			// Checks the text fields using for loop
			for (int index = 0; index < fillInBlankTextField.length; index++) {

				// If the textfields equal the answers
				if (fillInBlankTextField[index].getText().equals(answers[index])) {

					// Set background as green (correct)
					fillInBlankTextField[index].setBackground(new Color(0x36B49C));
					// Increment the correct answer #
					correctAnswers++;
				}

				// Otherwise set it as red (incorrect)
				else {
					fillInBlankTextField[index].setBackground(new Color(0xFF5757));
				}
			}

			// If the user has typed the correct fill in the blanks
			if (correctAnswers == 3) {

				// if the user hasn't completed this level before
				if (activityLevel < 4) {

					// Set the user's activity level to this new one
					FileInput.userDataArray.get(SignUpFrame.userIdentity).setActivityLevel(4);
				}

				// Display a completion message
				JOptionPane.showMessageDialog(this, "All answers are correct! Well done!");

				// Redirects the page back to the activity frame
				dispose();
				ActivityFrame activityFrame = new ActivityFrame();
			}

			// If the user has not typed all the answers correctly
			else {

				// Displays an error message
				JOptionPane.showMessageDialog(this, "Incomplete level", "Finish the game first!",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}
