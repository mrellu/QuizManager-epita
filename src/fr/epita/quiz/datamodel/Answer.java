package fr.epita.quiz.datamodel;

public class Answer {
	
	private String text;
	private Quiz quiz;
	private Question question;
	private String optA;
	private String optB;
	private String optC;
	private String optD;

	
	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public Answer(String text, String optA, String optB, String optC, String optD) {
		this.text = text;
		this.optA = optA;
		this.optB = optB;
		this.optC =  optC;
		this.optD = optD;
	}
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public String getOptA() {
		return optA;
	}


	public void setOptA(String optA) {
		this.optA = optA;
	}


	public String getOptB() {
		return optB;
	}


	public void setOptB(String optB) {
		this.optB = optB;
	}


	public String getOptC() {
		return optC;
	}


	public void setOptC(String optC) {
		this.optC = optC;
	}


	public String getOptD() {
		return optD;
	}


	public void setOptD(String optD) {
		this.optD = optD;
	}


	@Override
	public String toString() {
		return "Answer \t[text=" + text + ", \n quiz=" + quiz + ", \n question=" + question + "\n]";
	}
	
	


	
	
	
	
	

}
