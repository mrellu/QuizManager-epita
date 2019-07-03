package fr.epita.quiz.launcher;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.services.data.QuizJDBCDAO;

/**
 * 
 * @author mahesh
 *
 */
public class Admin {

	private static  String LOGIN = "ADM";
	private static String PWD = "ADM";
	private static QuizJDBCDAO daoData = QuizJDBCDAO.getInstance();
	private static String topic;
	private static Scanner scanner;
	private static int qid;
/**
 * Constructor for Admin
 * @throws CreateFailedException
 */
	public  Admin() throws CreateFailedException {

		Scanner scanner = new Scanner(System.in);
		boolean authenticated = authenticate(scanner);
		if (!authenticated) {
			scanner.close();
			return;
		}
		
		System.out.println("You're authenticated");
		String answer = "";
		
		while (!answer.equals("q")) {			
			answer = menuDsply(scanner);
			switch (answer) {
			case "1":
				quizCreation(scanner);
				break;
			case "2":
				createQstn(scanner);
				break;
			case "3":
				updtQstn(scanner);
				break;
			case "4":
				delQstn(scanner);
				break;
			case "5":
				searchQstn(scanner);
				break;
			case "q":
				System.out.println("Good bye!");
				break;
			default:
				System.out.println("Option not recognized, please enter an other option");
				break;
			}
		}

		scanner.close();

	}
/**
 * 
 * @param sc
 */
	private static void searchQstn(Scanner sc) {
		
		System.out.println("Welcome to Search a Question");
		System.out.println("Enter Topic");
		String tpc = sc.nextLine();
		List<Question> quesList = daoData.searchQ(tpc);
		System.out.println("Questions retreveid basede on Topics are given below::");

		for (Question q : quesList) {
			System.out.println(			q.getContent());
		}

	}

	private static void delQstn(Scanner sc) {
		boolean isExec = false;
		System.out.println("Welcome to Delete a Question");
		System.out.println("Enter Topic");
		String tpc = sc.nextLine();
		System.out.println("Enter Question to delete: ");
		String question = sc.nextLine();
		
		HashMap<String, String> delMap = new HashMap<String, String>();
		delMap.put("tpc", tpc);
		delMap.put("question", question);
		

		isExec = daoData.delQuestion(delMap);
		if(isExec) {
			System.out.println("Question deleted Successfully.");
		}
		
	}

	private static void updtQstn(Scanner sc) {
		boolean isExec = false;
		System.out.println("Welcome to Update a Question");
		System.out.println("Enter Topic");
		String topic = sc.nextLine();
		System.out.println("Enter Difficulty");
		String diff = sc.nextLine();
		System.out.println("Enter Old Question");
		String oldQ = sc.nextLine();
		System.out.println("Enter New Question");
		String newQ = sc.nextLine();
		
		HashMap<String, String> updtMap = new HashMap<String, String>();
		updtMap.put("topic", topic);
		updtMap.put("diff", diff);
		updtMap.put("oldQ", oldQ);
		updtMap.put("newQ", newQ);

		isExec = daoData.updtQuestion(updtMap);
		if(isExec) {
			System.out.println("Question updated Successfully.");
		}

	}

	private static void createQstn(Scanner sc) {
		boolean isExec = false;
		
		List<Quiz> quizList = daoData.getQuiz();
		
		for (Quiz quiz : quizList) { 		      
	           System.out.println(quiz.getId() + " " + quiz.getTitle()); 		
	      }
		System.out.println("Select Quiz Title ? :");
		String id = sc.nextLine();
		System.out.println("\nSelect the type of question 1.Open Question\n2.Multiple Answer");
		qid = Integer.parseInt(sc.nextLine());
		if(qid==2) {
		
		System.out.println("Welcome to create a Multiple Question");
		System.out.println("Enter Question: ");
		String question = sc.nextLine();
		System.out.println("Enter Topic: ");
		String topic = sc.nextLine();
		System.out.println("Enter Difficulty: ");
		String diff = sc.nextLine();
		System.out.println("Enter Option A: ");
		String optA = sc.nextLine();
		System.out.println("Enter Option B: ");
		String optB = sc.nextLine();
		System.out.println("Enter Option C: ");
		String optC = sc.nextLine();
		System.out.println("Enter Option D: ");
		String optD = sc.nextLine();
		System.out.println("Enter Actual right Answer: ");
		String cAns = sc.nextLine();
		
		HashMap<String, String> quesMap = new HashMap<String, String>();
		quesMap.put("id", id);
		quesMap.put("question", question);
		quesMap.put("topic", topic);
		quesMap.put("diff", diff);
		quesMap.put("optA", optA);
		quesMap.put("optB", optB);
		quesMap.put("optC", optC);
		quesMap.put("optD", optD);
		quesMap.put("cAns", cAns);
		
		isExec = daoData.createQuestion(quesMap);
		if(isExec) {
			System.out.println("Question and Answers are created Successfully.");
		}
		}
		else if(qid==1) {
			System.out.println("Welcome to create a Open Question");
			System.out.println("Enter Question: ");
			String question = sc.nextLine();
			System.out.println("Enter Topic: ");
			String topic = sc.nextLine();
			System.out.println("Enter Difficulty: ");
			String diff = sc.nextLine();
			System.out.println("Enter Hint: ");
			String hint = sc.nextLine();
			System.out.println("Enter Actual right Answer: ");
			String cAns = sc.nextLine();
			
			HashMap<String, String> quesMap = new HashMap<String, String>();
			quesMap.put("id", id);
			quesMap.put("question", question);
			quesMap.put("topic", topic);
			quesMap.put("diff", diff);
			quesMap.put("optD", hint);
			quesMap.put("cAns", cAns);
			
			isExec = daoData.createOpenQuestion(quesMap);
			if(isExec) {
				System.out.println("Question and Answers are created Successfully.");
			}
			
		}
			


	}


	
	private static void quizCreation(Scanner s) throws CreateFailedException {
		boolean isExec=false;
		System.out.println("Please Enter Your Topic \n");
		scanner = new Scanner(System.in);
		topic = scanner.nextLine();
		isExec = daoData.createTopic(topic);
		if(isExec) {
			System.out.println("Question and Answers are created Successfully. \n");
		}
	}

	private static String menuDsply(Scanner scanner) {
		String answer;
		System.out.println(" Welcome to Admininstrator Console");
		System.out.println("1. Create Quiz");
		System.out.println("2. Create Question");
		System.out.println("3. Update Question");
		System.out.println("4. Delete Question");
		System.out.println("5. Search Question");
		System.out.println("q. Quit");
		System.out.println("What is your choice ? (1|2|q) : \n");
		answer = scanner.nextLine();
		return answer;
	}

	private static boolean authenticate(Scanner scanner) {
		boolean b=false;
		System.out.println("Please enter your login : \n");
		LOGIN = scanner.next();
		LOGIN=LOGIN.toUpperCase();
		System.out.println("Please enter your password : \n");
		PWD = scanner.next();
		String isExec = daoData.selectUser(LOGIN);
		if(isExec.equals(PWD)) {
			System.out.println("Login Sucessfull \n");
			b=true;
		}
		else {
			System.out.println("Invalid Credentilas \n");	
			b=false;
			}
		return b;
	}

}
