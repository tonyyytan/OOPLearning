
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: LessonLevel3Frame
 * 
 * Description:
 * The LessonLevel3Frame class is designed to present the first level of educational content within the CAI program. 
 * Users can view educational content and navigate through the lesson using scroll wheel. Users are able to view
 * at course content through JLabel image.
 * 
 * Additional features:
 * 	None
 * 
 * Major Skills:
 * - Scroll panel
 * - Custom leveling scheme
 * - Java GUI handling
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 * Areas of Concern:
 * 	None
 * 
 * Additional documentation:
 * 	None
 * 
 */

//Imports java packages
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//Creates the class extending into JFrame
public class LessonLevel3Frame extends JFrame implements ActionListener {

	// Scroll panel features
	private JPanel scrollPanel = new JPanel();
	private JScrollPane scroll = new JScrollPane(scrollPanel);

	// JLabel for the lesson image
	private JLabel lessonExample = new JLabel();
	private ImageIcon lessonImage = new ImageIcon("images/lesson3.png");

	// JButton array for the finish and back buttons
	private JButton[] buttonArray = new JButton[2];

	// Retrieve the lesson level from the user data
	private int lessonLevel = FileInput.userDataArray.get(SignUpFrame.userIdentity).getLessonLevel();

	// constructor
	public LessonLevel3Frame() {
		// Generates the scrolling features
		generateScroll();

		// Generates the lesson example
		generateLesson();

		// Generates the buttons
		generateButtons();

		// Generates the frame
		generateFrame();
	}

	// Method to generate the lesson image
	private void generateLesson() {
		lessonExample.setBounds(0, 0, 1186, 2500);
		lessonExample.setIcon(lessonImage);
		scrollPanel.add(lessonExample);
	}

	// Method to generate all the buttons
	private void generateButtons() {

		// Use for loop to place buttons efficiently
		for (int index = 0; index < buttonArray.length; index++) {

			// Create new JButton, set the bounds
			buttonArray[index] = new JButton();
			buttonArray[index].setBounds(958, 72 + 2320 * index, 154, 60);

			// Makes the button transparent on the JLabel
			buttonArray[index].setOpaque(false);
			buttonArray[index].setContentAreaFilled(false);
			buttonArray[index].setBorderPainted(false);

			// Adds action listener
			buttonArray[index].addActionListener(this);

			// adds the button to the frame
			lessonExample.add(buttonArray[index]);
		}
	}

	// Method to generate the scroll feature
	private void generateScroll() {

		// Sets the layout to null
		scrollPanel.setLayout(null);
		scrollPanel.setBackground(Color.white);

		// Set the bounds of the scroll panel
		scrollPanel.setPreferredSize(new Dimension(1200, 2500));

		// Place scroll bar
		scroll.setBounds(0, 0, 1186, 625);
		scroll.setBackground(Color.white);

		// Makes scroll vertically
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setUnitIncrement(15); // go down by 15 units
		scroll.setHorizontalScrollBar(null);
		add(scroll);
	}

	private void generateFrame() {

		// Creates a color variable, decoding hexcode
		Color backgroundColor = Color.decode("#131F24");

		// Sets title of the window
		setTitle("CodeSpace");

		// Sets the size of the window
		setSize(1200, 625);

		// Sets the color of the window
		scrollPanel.setBackground(backgroundColor);

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

	// Automatically added method for action listener
	private void addActionListener(LessonLevel3Frame lessonLevel3Frame) {
		// TODO Auto-generated method stub

	}

	// Method to implement button actions
	@Override
	public void actionPerformed(ActionEvent event) {

		// If the back button is clicked
		if (event.getSource() == buttonArray[0]) {

			// Redirect the page to the lessons frame
			dispose();
			LessonsFrame lessonsFrame = new LessonsFrame();
		}

		// if the finish button is clicked
		else if (event.getSource() == buttonArray[1]) {

			// If the user has not completed this level before
			if (lessonLevel < 3) {

				// Set their new level to the next level
				FileInput.userDataArray.get(SignUpFrame.userIdentity).setLessonLevel(3);
			}

			// Exit the frame, redirects to lesson frame
			dispose();
			LessonsFrame lessonsFrame = new LessonsFrame();
		}
	}
}
