
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

public class ActivityLevel2Frame extends JFrame implements ActionListener {

	// JLabel array for the coding examples
	private JLabel[] codeLabels = new JLabel[2];
	private ImageIcon[] codeImages = { new ImageIcon("images/fillBlankImage1.png"),
			new ImageIcon("images/fillBlankImage2.png") };

	// JLabel text of the activity title
	private JLabel activityTitleLabel = new JLabel();

	// JTextField for filling in the blanks on the code
	private JTextField[] fillInBlankTextField = new JTextField[4];

	// String array of the correct answers
	private String[] answers = { "super();", "this.examScore = examScore;", "public void setUsername(String username)",
			"return password;" };

	// JButton array for the utility buttons in the game
	private JButton[] gameButtonArray = new JButton[3];
	private ImageIcon[] gameButtonIcons = { new ImageIcon("images/exitButton.png"),
			new ImageIcon("images/checkButton.png"), new ImageIcon("images/finishButton.png") };

	// Variable for the answer
	private int correctAnswers = 0;

	// Retrieve the activity level from the user data
	private int activityLevel = FileInput.userDataArray.get(SignUpFrame.userIdentity).getActivityLevel();

	// Constructor
	public ActivityLevel2Frame() {

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

	// Method to generate the title
	private void generateTitle() {

		// Sets the text to game name
		activityTitleLabel.setText("Fill in the blanks!");
		activityTitleLabel.setBounds(63, 37, 491, 56);

		// Changes font
		activityTitleLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		activityTitleLabel.setForeground(Color.white);
		add(activityTitleLabel);
	}

	// Method to generate the coding examples
	private void generateExamples() {

		// Creates the first coding example
		codeLabels[0] = new JLabel();
		codeLabels[0].setBounds(63, 100, 900, 200);
		codeLabels[0].setIcon(codeImages[0]);

		// Creates the second coding example
		codeLabels[1] = new JLabel();
		codeLabels[1].setBounds(63, 300, 588, 250);
		codeLabels[1].setIcon(codeImages[1]);

		// Adds them to the frame
		add(codeLabels[0]);
		add(codeLabels[1]);
	}

	// Method to generate the utility buttons
	private void generateButtons() {

		// For loop to efficiently place the buttons
		for (int index = 0; index < gameButtonArray.length; index++) {

			// Create the button, set bounds, and set the icon
			gameButtonArray[index] = new JButton();
			gameButtonArray[index].setBounds(634 + 175 * index, 495, 138, 59);
			gameButtonArray[index].setIcon(gameButtonIcons[index]);

			// Add an actionlistener to the button
			gameButtonArray[index].addActionListener(this);
			add(gameButtonArray[index]);
		}
	}

	// Method to generate the text fields to fill in blank
	private void generateTextFields() {

		// Fill in blank question 1
		fillInBlankTextField[0] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[0].setBounds(90, 53, 65, 20);
		fillInBlankTextField[0].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[0].setBackground(new Color(0x202F36));
		fillInBlankTextField[0].setForeground(Color.white);

		// Adds to the frame
		codeLabels[0].add(fillInBlankTextField[0]);

		// Fill in blank question 2
		fillInBlankTextField[1] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[1].setBounds(90, 133, 250, 20);
		fillInBlankTextField[1].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[1].setBackground(new Color(0x202F36));
		fillInBlankTextField[1].setForeground(Color.white);

		// Adds to the frame
		codeLabels[0].add(fillInBlankTextField[1]);

		// Fill in blank question 3
		fillInBlankTextField[2] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[2].setBounds(60, 70, 300, 20);
		fillInBlankTextField[2].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[2].setBackground(new Color(0x202F36));
		fillInBlankTextField[2].setForeground(Color.white);

		// Adds to the frame
		codeLabels[1].add(fillInBlankTextField[2]);

		// Fill in blank question 2
		fillInBlankTextField[3] = new JTextField();

		// Define the properties of the text field
		fillInBlankTextField[3].setBounds(90, 147, 150, 20);
		fillInBlankTextField[3].setFont(new Font("Dialog", Font.PLAIN, 12));

		fillInBlankTextField[3].setBackground(new Color(0x202F36));
		fillInBlankTextField[3].setForeground(Color.white);

		// Adds to the frame
		codeLabels[1].add(fillInBlankTextField[3]);
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

	// Automatically added method for actionlistener
	private void addActionListener(ActivityLevel2Frame activityLevel2Frame) {
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

		// If you click the exit button
		else if (event.getSource() == gameButtonArray[0]) {

			// Redirects the page back to the activity frame
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

			// If the user correctly answered all four blanks
			if (correctAnswers == 4) {

				// If the user has never completed this lvl before
				if (activityLevel < 2) {

					// Set the user's new level to this current one
					FileInput.userDataArray.get(SignUpFrame.userIdentity).setActivityLevel(2);
				}

				// Display a completion message
				JOptionPane.showMessageDialog(this, "All answers are correct! Well done!");

				// Redirect the page back to the activity frame
				dispose();
				ActivityFrame activityFrame = new ActivityFrame();
			}

			// If the user has not correctly filled in the blanks
			else {

				// Display an error message
				JOptionPane.showMessageDialog(this, "Incomplete level", "Finish the game first!",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}
