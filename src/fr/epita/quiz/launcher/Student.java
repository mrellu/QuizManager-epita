package fr.epita.quiz.launcher;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.services.data.QuizJDBCDAO;

/**
 * 
 * @author mahesh
 *
 */
public class Student {
	private static Scanner sc;
	private static int opt;
	private static int typeques;
	
	private static QuizJDBCDAO daoData = QuizJDBCDAO.getInstance();
	public Student() {
		System.out.println("Welcome TO THE Quiz");
		System.out.println("Please Select TOPIC From Below Options");
		List<Quiz> quizList = daoData.getQuiz();
		for(Quiz qui : quizList) {
			System.out.println(qui.getId()+ " -- "+qui.getTitle());		}
		sc = new Scanner(System.in);
		opt = Integer.parseInt(sc.nextLine());
		List<Answer> ansList = daoData.selectQues(opt);
		System.out.println("Select the type of question \n1.Open Questions \n2.Multiple Questions :");
		sc = new Scanner(System.in);
		typeques = Integer.parseInt(sc.nextLine());
		if(typeques==2) {
		//List<Answer> ansList = daoData.selectQues(opt);
		int count =0;
		for (Answer ans : ansList) {
			System.out.println("Question :"+ans.getQuestion().getContent());
			System.out.println("Opti-on A :"+ans.getOptA());
			System.out.println("Option B :"+ans.getOptB());
			System.out.println("Option C :"+ans.getOptC());
			System.out.println("Option D :"+ans.getOptD());
			
			System.out.println("Enter Your Choice: ");
			String crctAns = sc.next();
				if(crctAns.equalsIgnoreCase(ans.getText())) {
					count++;
				}
}
		exportFile(ansList, count);

		System.out.println("Your Score is :::"+count +"\n You can check the score with correct answers in text file \n ");
		}
		else if (typeques==1) {
			int count =0;
			for (Answer ans : ansList) {
				System.out.println("Question :"+ans.getQuestion().getContent());
				System.out.println("Hint :"+ans.getOptD());
				
				System.out.println("Enter Your Answer: ");
				String crctAns = sc.next();
					if(crctAns.equalsIgnoreCase(ans.getOptD())) {
						count++;
					}
	}
			exportFile(ansList, count);

			System.out.println("Your Score is :::"+count +"\n You can check the score with correct answers in text file \n ");
		}
		
		
		
		
	}
	private void exportFile(List<Answer> ansList, int count) {
		final String FNAME = "Quiz_result.txt";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FNAME))) {
		for (Answer ans : ansList) {
		bw.write("\nQuestion is :" +ans.getQuestion().getContent() + "\nCorrect Anser is :"+ans.getText());
		}
		bw.write("\nTotal Marks you socred in this Quiz is :"+count);

		bw.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}

}
