package fr.epita.quiz.launcher;


import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.services.data.QuizJDBCDAO;

public class Student {
	private static Scanner sc;
	private static int opt;
	
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
		int count =0;
		for (Answer ans : ansList) {
			System.out.println("Question :"+ans.getQuestion().getContent());
			System.out.println("Opti-on A :"+ans.getOptA());
			System.out.println("Option B :"+ans.getOptB());
			System.out.println("Option C :"+ans.getOptC());
			System.out.println("Option D :"+ans.getOptD());
			
			System.out.println("Enter Your Choice: ");
			String crctAns = sc.nextLine();
				if(crctAns.equalsIgnoreCase(ans.getText())) {
					count++;
				}



		}
		System.out.println("Your Score is :::"+count );
		
		
		
		
		
	}

}
