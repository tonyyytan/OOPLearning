
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Test Frame
 * 
 * Description:
 * The TestFrame class includes the interactive exam for the app.
 * It has multiple-choice questions with a timer for users. Users can select their answers,
 * proceed through questions, and exit the test. The frame also handles the logic for answering
 * questions, navigating through them, and updating scores based on user responses.
 * 
 * Major Skills:
 *  Java Swing for GUI components.
 *  Event handling for interactive quiz functionality.
 *  Basic file operations for reading and writing exam data.
 *  Implementing timers in Java for timed tests.
 * 
 * Added Features:
 *  1. Randomized question and answer order to ensure a unique test experience each time.
 *  2. Progress bar timer that gives visual feedback on the remaining time.
 *  3. Immediate feedback mechanism that allows navigating through the quiz.
 * 
 * Areas of Concern:
 *   None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Additional documentation:
 * Learned how to use JProgressBar:
 * https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/javax/swing/JProgressBar.html
 * 
 * Learned how to make a timer:
 * https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java
 * 
 * Learned how to randomize arraylists:
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 
 * Learned to make line breaks:
 * https://stackoverflow.com/questions/1842223/java-linebreaks-in-jlabels
 * 
 */

//Import java packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

//Create the class extending into JFrame
public class TestFrame extends JFrame implements ActionListener {

	// JButton 2d array to display the multiple choice answer boxes
	private JButton[][] answerButtonArray = new JButton[2][2];
	private JLabel[][] answerLabelArray = new JLabel[2][2];
	private String[] answers = new String[4];

	// JButton for exiting out of the exam
	private JButton exitButton = new JButton();
	private ImageIcon exitIcon = new ImageIcon("images/exitButton.png");

	// JLabel text for questions
	private JLabel questionLabel = new JLabel();
	private String question;

	// Array list of the questions
	private List<Question> questionList;

	// The index of the question
	private int index = 0;

	// ImageIcon of the answer box
	private ImageIcon answerBoxImage = new ImageIcon("images/answerBox.png");

	// JProgress bar for the timer
	private JProgressBar timerBar = new JProgressBar();
	private Timer timer = new Timer(100, this);

	private long endTime; // When the countdown should end
	private int totalTime = 600; // Total time of the test - 10 mins

	// Currently selected answer
	private String selectedAnswer = null;
	// Current question
	private String selectedQuestion = null;

	// Number of correct answers
	private static double correctAnswers;

	// Boolean array to check which questions user got right/wrong
	public static boolean[] questionResults = new boolean[10];

	// Constructor
	public TestFrame() {
		
        correctAnswers = 0; // Reset correct answers count for a new test

		// Randomize test
		randomizeTest();

		// Generate the timer bar
		generateTimer();

		// Generate the exit button
		generateExitButton();

		// Generate the test
		generateTest();

		// Generate the frame
		generateFrame();
	}

	// Method to generate the progress bar timer
	// https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/javax/swing/JProgressBar.html
	// https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java
	private void generateTimer() {

		// Gets the current time of the system
		long currentTime = System.currentTimeMillis();

		// Gets the end time after timer started
		endTime = currentTime + totalTime * 1000;

		// Create the JProgress bar
		timerBar = new JProgressBar();

		// Set the maximum % to be the total time
		timerBar.setMaximum(totalTime * 1000);

		// Set the value to be the total time at the beginning
		timerBar.setValue(totalTime * 1000);

		// Change the color of the text
		timerBar.setStringPainted(true);
		timerBar.setForeground(new Color(0x3A97FD));

		// Color of the background
		timerBar.setBackground(new Color(0x43BEF7));
		timerBar.setBounds(100, 50, 1000, 50); // Set bounds since layout is null
		add(timerBar);

		// Start the timer
		timer.start();
	}

	// Generate the test
	private void generateTest() {

		// Generate the answer boxes
		generateAnswerBoxes();

		// Generate the question labels
		generateQuestion();

	}

	// Generate the questions
	// https://stackoverflow.com/questions/1842223/java-linebreaks-in-jlabels
	private void generateQuestion() {

		// Creates the random question
		question = questionList.get(index).getQuestions();

		// Convert the question into html format to break line properly
		String questionHtml = "<html><body style='width: 800 px'>" + (index + 1) + ". " + question + "</body></html>";

		// Sets the text to the question
		questionLabel.setText(questionHtml);
		questionLabel.setBounds(137, 125, 1000, 90);

		// Changes font
		questionLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		questionLabel.setForeground(Color.white);
		add(questionLabel);
	}

	// Method to randomize the test
	// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
	private void randomizeTest() {

		// Convert questionBank array to a list
		questionList = Arrays.asList(FileInput.questionBankArray);

		// Shuffle the list
		Collections.shuffle(questionList);

		// Call method to randomize the answers as well
		randomizeAnswers();

	}

	private void randomizeAnswers() {

		// Set all the answers to the current index of the question
		answers[0] = questionList.get(index).getAnswer();
		answers[1] = questionList.get(index).getFakeAnswer1();
		answers[2] = questionList.get(index).getFakeAnswer2();
		answers[3] = questionList.get(index).getFakeAnswer3();

		// Shuffle the answers array to randomize positions
		// Convert that answers array to a list
		List<String> answersList = Arrays.asList(answers);
		Collections.shuffle(answersList);

		// Converts back to array
		answersList.toArray(answers);
	}

	// Method to generate the answer multiple choice boxes
	private void generateAnswerBoxes() {

		// Counter for the answers text
		int count = 0;

		// Nested for loop to placed JButton 2d array
		for (int row = 0; row < answerButtonArray.length; row++) {
			for (int col = 0; col < answerButtonArray[row].length; col++) {

				// Create the JButtons, set bounds, and set icon
				answerButtonArray[row][col] = new JButton();
				answerButtonArray[row][col].setBounds(320 + 269 * row, 264 + 97 * col, 269, 97);
				answerButtonArray[row][col].setIcon(answerBoxImage);

				// Adds an action listener to button
				answerButtonArray[row][col].addActionListener(this);
				add(answerButtonArray[row][col]);

				// JLabel storing the text answer of each of the MC boxes
				answerLabelArray[row][col] = new JLabel(answers[count]); // Correct index
				answerLabelArray[row][col].setBounds(20 + 269 * row, 172 + 60 * col, 260, 80);
				answerLabelArray[row][col].setFont(new Font("Dialog", Font.BOLD, 12));
				answerLabelArray[row][col].setForeground(Color.WHITE);
				answerButtonArray[row][col].add(answerLabelArray[row][col]);

				// Accumulate
				count++;
			}
		}
	}

	// Method to generate the exit button
	private void generateExitButton() {

		// Create the exit button and its properties
		exitButton = new JButton();
		exitButton.setBounds(50, 495, 154, 60);
		exitButton.setIcon(exitIcon);

		// Add an action listener
		exitButton.addActionListener(this);
		add(exitButton);

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
	private void addActionListener(TestFrame testFrame) {
		// TODO Auto-generated method stub

	}

	// Method to implement actions for the buttons
	@Override
	public void actionPerformed(ActionEvent event) {

		// Takes the current time of the system, right when test starts
		long currentTime = System.currentTimeMillis();
		// Calculates the time left until the end
		long timeLeft = endTime - currentTime;

		// If there is still time left
		if (timeLeft > 0) {

			// Change the progressbar to go down
			timerBar.setValue((int) timeLeft);
			int secondsLeft = (int) (timeLeft / 1000);

			// Update the text to show remaining seconds
			timerBar.setString(secondsLeft + " seconds remaining");
		}

		// If the time is up
		else {

			// Set the bar to 0 and stop the timer
			timerBar.setValue(0);
			timer.stop();

			// Set the new exam score (even if they didn't make through all the questions)

			FileInput.userDataArray.get(SignUpFrame.userIdentity).setExamScore(correctAnswers);

			// Display a message that it is finished
			JOptionPane.showMessageDialog(null, "Exam is over! No more... ");

			// Redirect the page to the results exam frame
			dispose();
			ExamResultsFrame examResultsFrame = new ExamResultsFrame();
		}

		// Nested for loop to check all the actions of the multiple choice boxes (which
		// are in 2d arrays)
		for (int row = 0; row < answerButtonArray.length; row++) {
			for (int col = 0; col < answerButtonArray[row].length; col++) {

				// If the multiple choice option was selected
				if (event.getSource() == answerButtonArray[row][col]) {

					// Get the text of the MC answer
					selectedAnswer = answerLabelArray[row][col].getText();

					// If the answer is correct (determined by calling the method)
					if (checkAnswer()) {
						// Go onto the next question
						nextQuestion();

					}

					// If the answer is incorrect
					else {
						// Go onto the next question
						nextQuestion();
					}
					return; // Exit after handling the button press
				}
			}
		}

		// If the exit button is clicked
		if (event.getSource() == exitButton) {

			// Redirect the page to the exam frame
			dispose();
			ExamFrame examFrame = new ExamFrame();
		}
	}

	// Method to go to the next question
	private void nextQuestion() {

		// If there is still questions to go (less than 10)
		if (index < 9) {
			// Increment the index
			index++;

			// Call method to generate a new question
			generateQuestion();

			// Call method to randomize answers again
			randomizeAnswers();

			// Counter to place the answers in 2d array
			int count = 0;

			// Nested for loop to change the text of the JLabel 2d array
			for (int row = 0; row < answerButtonArray.length; row++) {
				for (int col = 0; col < answerButtonArray[row].length; col++) {

					// Change the text of the previous MC answers to the new ones
					answerLabelArray[row][col].setText(answers[count]);

					// Increment counter
					count++;
				}
			}
		}

		// If the test is over, no more questions leftover
		else {

			// Save the exam score the user data array
			FileInput.userDataArray.get(SignUpFrame.userIdentity).setExamScore(correctAnswers);

			// Display a message of the exam completion
			JOptionPane.showMessageDialog(this, "Exam is over", "All done.", JOptionPane.INFORMATION_MESSAGE);

			// Redirects the page to the exam results frame
			dispose();
			ExamResultsFrame examResultsFrame = new ExamResultsFrame();
		}
	}

	// Method to check the answer of the selected answers
	private boolean checkAnswer() {

		// If the selected answer is equal to the actual answer of the question
		// Known from the question bank which has the true answers
		if (selectedAnswer.equals(questionList.get(index).getAnswer())) {

			// Increment the correct answers variable
			correctAnswers++;

			// Change the index of the questionsResults array to true to
			// indicate that question was answered correctly
			questionResults[index] = true;
			
			//Return true
			return true;

		}
		
		//If it was an incorrect answer, return false
		return false;
	}
}
