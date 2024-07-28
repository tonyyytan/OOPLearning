
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: User Template Class
 * 
 * Description:
 * This class serves as a template for user data within the app. It encapsulates
 * user-related stats such as username, password, lesson level, activity level, and exam score.
 * The class provides a structure for creating user objects that can be managed throughout the application,
 * ensuring that user data is easily accessible and modifiable.
 * 
 * Major Skills:
 * 	OOP utilized
 * 	Creating a template class for an object
 * 
 * Added Features:
 * 	None
 *   
 * Areas of Concern:
 *  None 
 * Contribution to Assignment: 100% Tony Tan
 * 
 */

//Class User
public class User {

	// Fields of the template class
	private String username;
	private String password;
	private int lessonLevel;
	private int activityLevel;
	private double examScore;

	// Constructor for the object
	public User(String username, String password, int lessonLevel, int activityLevel, double examScore) {
		super(); // super method
		this.username = username;
		this.password = password;
		this.lessonLevel = lessonLevel;
		this.activityLevel = activityLevel;
		this.examScore = examScore;
	}

	// Getters and setters of the fields
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLessonLevel() {
		return lessonLevel;
	}

	public void setLessonLevel(int lessonLevel) {

		// if score is negative set to 0
		if (lessonLevel < 0) {
			System.out.println("Invalid level. Set to 0.");
			this.lessonLevel = 0;
		}

		else
			this.lessonLevel = lessonLevel;
	}

	public int getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(int activityLevel) {

		// if score is negative set to 0
		if (activityLevel < 0) {
			System.out.println("Invalid level. Set to 0.");
			this.activityLevel = 0;
		}

		else
			this.activityLevel = activityLevel;
	}

	public double getExamScore() {
		return examScore;
	}

	public void setExamScore(double examScore) {
		// if score is negative set to default -1.0 score
		if (examScore < -1 || examScore > 10) {
			System.out.println("Invalid score. Set to -1.");
			this.examScore = -1.0; // Set to -1.0 if the provided score is invalid
		}

		else {
			this.examScore = examScore; // Otherwise, set to the provided score
		}
	}

	// To string to read as a string in console
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", lessonLevel=" + lessonLevel
				+ ", activityLevel=" + activityLevel + ", exameScore=" + examScore + "]";
	}

}
