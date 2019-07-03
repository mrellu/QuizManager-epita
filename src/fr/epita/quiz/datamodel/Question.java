package fr.epita.quiz.datamodel;

import java.util.Arrays;
/**
 * 
 * @author mahesh
 *
 */
public class Question {
	
	private String content;
	private String[] topics;
	private Integer difficulty;
	
	
	public Question(String content, String[] topics, Integer difficulty) {
		this.content = content;
		this.topics = topics;
		this.difficulty = difficulty;
	}
	
	public Question(String content) {
		this.content = content;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}


	@Override
	public String toString() {
		return "Question [content=" + content + ", topics=" + Arrays.toString(topics) + ", difficulty=" + difficulty
				+ "]";
	}
	
	
	
	
	
	

}
