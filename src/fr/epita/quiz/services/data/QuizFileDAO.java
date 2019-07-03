package fr.epita.quiz.services.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.exception.SearchFailedException;

/**
 * 
 * @author mahesh
 *
 */
public class QuizFileDAO {

	
	private File file;

/**
 * QuizFileDAOFile
 * @param file
 * @throws IOException
 */
	public QuizFileDAO(File file) throws IOException {
		
		if (!file.exists()) {
			file.getAbsoluteFile().getParentFile().mkdirs();
			file.createNewFile(); 
		}
		this.file = file;
	}
	
/**
 * Create quiz
 * @param quiz
 * @throws CreateFailedException
 */
	public void create(Quiz quiz) throws CreateFailedException{
		
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println(quiz.getTitle());
			writer.flush();
			
		} catch (FileNotFoundException e) {
			throw new CreateFailedException(quiz, e);
		}
	
	}

	public void update(Quiz quiz) {

	}

	public void delete(Quiz quiz) {

	}

	public Quiz getById(int id) {
		return null;

	}
/**
 * Search  
 * @param quizCriterion
 * @return
 * @throws SearchFailedException
 */
	public List<Quiz> search(Quiz quizCriterion) throws SearchFailedException {
		List<Quiz> resultList = new ArrayList<>();
		try (Scanner scanner = new Scanner(file)) {
			// for each line in the file,
			while(scanner.hasNext()) {
				// 	read the line
				String line = scanner.nextLine();
				
				if (line.contains(quizCriterion.getTitle())) {
					Quiz quiz = new Quiz(line);
					resultList.add(quiz);
				}
			}
			
		} catch (FileNotFoundException e) {
			throw new SearchFailedException(quizCriterion, e);
		}
			
		return resultList;
	}

}
