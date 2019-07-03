package fr.epita.quiz.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.exception.SearchFailedException;
import fr.epita.quiz.services.ConfigEntry;
import fr.epita.quiz.services.ConfigurationService;

public class QuizJDBCDAO {

	private static QuizJDBCDAO instance;

	private static final String UPDATE_QUERY = "UPDATE QUIZ SET NAME=? WHERE ID = ?";
	private static final String DELETE_QUERY = "DELETE FROM QUIZ  WHERE ID = ?";
	private static final String INSERT_TOPIC = "INSERT into QUIZ (NAME) values(?)";

	private static final String SELECT_USER = "SELECT PASS FROM USER WHERE USERNAME = ?";
	private static final String INSERT_QUES = "INSERT into QUESTION (ID,QUESTION,TOPIC,DIFF) values(?,?,?,?)";
	private static final String INSERT_ANS = "INSERT into ANSWER (ID,ANS,OPTA,OPTB,OPTC,OPTD) values(?,?,?,?,?,?)";
	private static final String UPDATE_QUES = "UPDATE QUESTION SET QUESTION=? WHERE QUESTION=? AND TOPIC=? AND DIFF=?";
	private static final String DELETE_QUES = "DELETE FROM QUESTION  WHERE TOPIC = ? AND  QUESTION=?";

	private static final String SELECT_QUESTN = "SELECT Q.QUESTION, A.ANS, A.OPTA,A.OPTB,A.OPTC,A.OPTD FROM QUESTION Q, ANSWER A, QUIZ QZ WHERE A.ANSID =Q.QUESID  AND  QZ.ID=Q.ID AND QZ.ID=?";
	private static final String SEARCH_QUES = "SELECT QUESTION FROM QUESTION  WHERE TOPIC =?";

	private QuizJDBCDAO() {

	}

	public static QuizJDBCDAO getInstance() {
		if (instance == null) {
			instance = new QuizJDBCDAO();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException {
		ConfigurationService conf = ConfigurationService.getInstance();
		String username = conf.getConfigurationValue("db.username", "");
		String password = conf.getConfigurationValue("db.password", "");
		String url = conf.getConfigurationValue("db.url", "");
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	/**
	 * Creates a quiz in the database, if a problem occurs then it throws an
	 * {@link CreateFailedException} usage example: QuizJDBCDAO dao = new ... try{
	 * dao.create(quizInstance); }catch(CreateFailed e){ //log exception }
	 * 
	 * @param quiz
	 * @throws CreateFailedException
	 */

	public boolean createTopic(String quiz) throws CreateFailedException {
		boolean var = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_TOPIC);) {
			pstmt.setString(1, quiz);
			pstmt.execute();
			var = true;
		} catch (SQLException sqle) {
			throw new CreateFailedException(quiz);
		}
		return var;

	}

	public void update(Quiz quiz) {
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);) {
			pstmt.setString(1, quiz.getTitle());
			pstmt.setInt(2, quiz.getId());
			pstmt.execute();
		} catch (SQLException sqle) {
		}

	}

	public void delete(Quiz quiz) {
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);) {
			pstmt.setInt(1, quiz.getId());
			pstmt.execute();
		} catch (SQLException sqle) {
		}
	}

	public Quiz getById(int id) {
		return null;

	}

	public String selectUser(String user) {
		String pass = "";
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(SELECT_USER);
			pstmt.setString(1, user);
			pstmt.execute();
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			pass = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass;
	}

	public List<Quiz> search(Quiz quizCriterion) throws SearchFailedException {
		String searchQuery = ConfigurationService.getInstance()
				.getConfigurationValue(ConfigEntry.DB_QUERIES_QUIZ_SEARCHQUERY, "");
		List<Quiz> quizList = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement pstmt = connection.prepareStatement(searchQuery)) {

			pstmt.setInt(1, quizCriterion.getId());
			pstmt.setString(2, "%" + quizCriterion.getTitle() + "%");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String topic = rs.getString("NAME");
				Quiz quiz = new Quiz(topic);
				quiz.setId(id);
				quizList.add(quiz);
			}

			rs.close();
		} catch (SQLException e) {
			throw new SearchFailedException(quizCriterion);
		}
		return quizList;
	}

	public boolean createQuestion(HashMap<String, String> quesMap) {
		boolean isExec = false;
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUES);
			pstmt.setInt(1, Integer.parseInt(quesMap.get("id")));
			pstmt.setString(2, quesMap.get("question"));
			pstmt.setString(3, quesMap.get("topic"));
			pstmt.setInt(4, Integer.parseInt(quesMap.get("diff")));
			pstmt.execute();

			createAns(quesMap);
			isExec = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExec;
	}

	private void createAns(HashMap<String, String> quesMap) {
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_ANS);
			pstmt.setInt(1, Integer.parseInt(quesMap.get("id")));
			pstmt.setString(2, quesMap.get("cAns"));
			pstmt.setString(3, quesMap.get("optA"));
			pstmt.setString(4, quesMap.get("optB"));
			pstmt.setString(5, quesMap.get("optC"));
			pstmt.setString(6, quesMap.get("optD"));
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Answer> selectQues(int opt) {
		Connection connection;
		List<Answer> ansList = new ArrayList<Answer>();
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUESTN);
			pstmt.setInt(1, opt);
			pstmt.execute();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String ques = rs.getString(1);
				Question questn = new Question(ques);
				Answer ans = new Answer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				ans.setQuestion(questn);
				ansList.add(ans);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ansList;
	}

	public List<Quiz> getQuiz() {
		List<Quiz> qList = new ArrayList<>();
		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select ID, NAME from QUIZ");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String topic = rs.getString("NAME");
				Quiz quiz = new Quiz(topic);
				quiz.setId(id);
				qList.add(quiz);
			}

			rs.close();
		} catch (SQLException e) {
		}
		return qList;
	}

	public boolean updtQuestion(HashMap<String, String> updtMap) {
		boolean isExec = false;
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUES);
			pstmt.setString(1, updtMap.get("newQ"));
			pstmt.setString(2, updtMap.get("oldQ"));
			pstmt.setString(3, updtMap.get("topic"));
			pstmt.setInt(4, Integer.parseInt(updtMap.get("diff")));
			pstmt.execute();

			isExec = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExec;
	}

	public boolean delQuestion(HashMap<String, String> delMap) {
		boolean isExec = false;
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUES);
			pstmt.setString(1, delMap.get("tpc"));
			pstmt.setString(2, delMap.get("question"));
			pstmt.execute();

			isExec = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExec;
	}

	public List<Question> searchQ(String tpc) {
		List<Question> queList = new ArrayList<Question>();
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(SEARCH_QUES);
			pstmt.setString(1, tpc);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String topic = rs.getString("QUESTION");
				Question ques = new Question(topic);
				queList.add(ques);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queList;
	}
}
