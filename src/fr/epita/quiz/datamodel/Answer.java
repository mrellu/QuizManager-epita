package fr.epita.quiz.datamodel;

/**
 * 
 * @author mahesh
 *
 */
public class Answer {
	
	private String text;
	private Quiz quiz;
	private Question question;
	private String optA;
	private String optB;
	private String optC;
	private String optD;

/**
 * 	
 * @return quiz values, pojo to return quiz 
 */
	public Quiz getQuiz() {
		return quiz;
	}
/**
 * 
 * @param quiz values, pojo to set quiz 
 */

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

/**
 * 
 * @return question value, pojo to return question 
 */
	public Question getQuestion() {
		return question;
	}
/**
 * 
 * @param question values, pojo to set question 
 */

	public void setQuestion(Question question) {
		this.question = question;
	}
/**
 * POJO constructor to create Answer 
 * @param text
 * @param optA
 * @param optB
 * @param optC
 * @param optD
 */

	public Answer(String text, String optA, String optB, String optC, String optD) {
		this.text = text;
		this.optA = optA;
		this.optB = optB;
		this.optC =  optC;
		this.optD = optD;
	}
/**
 * 
 * @return text values, pojo to return text 
 */
	
	public String getText() {
		return text;
	}

/**
 * 
 * @param text values, pojo to set text
 */
	public void setText(String text) {
		this.text = text;
	}

/**
 * 
 * @return optA values, pojo to return optA 
 */
	public String getOptA() {
		return optA;
	}

/**
 * 
 * @param optA values, pojo to set optA 
 */
	public void setOptA(String optA) {
		this.optA = optA;
	}

/**
 * 
 * @return optB values, pojo to return optB 
 */
	public String getOptB() {
		return optB;
	}

/**
 * 
 * @param optB values, pojo to set optB 
 */
	public void setOptB(String optB) {
		this.optB = optB;
	}

/**
 * 
 * @return optC values, pojo to return optC 
 */
	public String getOptC() {
		return optC;
	}

/**
 * 
 * @param optC values, pojo to set  optC 
 */
	public void setOptC(String optC) {
		this.optC = optC;
	}

/**
 * 
 * @return optD values, pojo to return optD 
 */
	public String getOptD() {
		return optD;
	}

/**
 * 
 * @param optD values, pojo to set optD 
 */
	public void setOptD(String optD) {
		this.optD = optD;
	}

/**
 * 
 */
	@Override
	public String toString() {
		return "Answer \t[text=" + text + ", \n quiz=" + quiz + ", \n question=" + question + "\n]";
	}
	
	


	
	
	
	
	

}
