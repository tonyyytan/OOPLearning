
/*
 * Name: TONY TAN
 * Date: June 11th, 2024
 * Course Code: ICS 3U1
 * Title: Classes and Objects (CAI) Program
 * Title of Class: Question Template Class
 * 
 * Description:
 * This class represents a Question object used in the educational software. Each Question object contains a question
 * and multiple answers including one correct answer and three fake answers. This structure is utilized within quizzes
 * and tests to evaluate the user's knowledge on various topics.
 * 
 * Major Skills:
 * 	OOP utilized
 * 	Creating a template class for an object
 * 
 * Added Features:
 * 	Multiple choice answers of all the questions
 * 
 * Areas of Concern:
 * 	None
 * 
 * Contribution to Assignment: 100% Tony Tan
 * 
 */

//Question template class
public class Question {

	// Fields of the object
	String questions;
	String answer;
	String fakeAnswer1;
	String fakeAnswer2;
	String fakeAnswer3;

	// Constructor for the object
	public Question(String questions, String answer, String fakeAnswer1, String fakeAnswer2, String fakeAnswer3) {
		super(); // Super()
		this.questions = questions;
		this.answer = answer;
		this.fakeAnswer1 = fakeAnswer1;
		this.fakeAnswer2 = fakeAnswer2;
		this.fakeAnswer3 = fakeAnswer3;
	}

	// Getters and setters of the fields
	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFakeAnswer1() {
		return fakeAnswer1;
	}

	public void setFakeAnswer1(String fakeAnswer1) {
		this.fakeAnswer1 = fakeAnswer1;
	}

	public String getFakeAnswer2() {
		return fakeAnswer2;
	}

	public void setFakeAnswer2(String fakeAnswer2) {
		this.fakeAnswer2 = fakeAnswer2;
	}

	public String getFakeAnswer3() {
		return fakeAnswer3;
	}

	public void setFakeAnswer3(String fakeAnswer3) {
		this.fakeAnswer3 = fakeAnswer3;
	}

	// To string to read as a string in the console
	@Override
	public String toString() {
		return "Question [questions=" + questions + ", answer=" + answer + ", fakeAnswer1=" + fakeAnswer1
				+ ", fakeAnswer2=" + fakeAnswer2 + ", fakeAnswer3=" + fakeAnswer3 + "]";
	}

}
