
public interface AppConst {

	String APP_TITLE = "Jeopardy";
	String BASE_PATH = "C:/Users/Frank/eclipse-workspace/Final Project/";
	String DB_URL = "jdbc:sqlite:C:/sqlite3/java/connect/Report.db";
	String INPUT_FILE_PATH = BASE_PATH + "input.txt"; // Retrieve Input from Text
	String REPORT_FILE_PATH = BASE_PATH + "report.txt"; // Generate report for leaderboard
	int ANSWER_TIMEOUT_SECONDS = 5; // Timer
	int CORRECT_ANSWER_POINTS = 1;
	String TITLE_NAME = "Player";
	String TITLE_POINTS = "Points";
	String TITLE_TIME = "Countdown";
	String PROMPT_NAME = "Player's Name";
	String DEFAULT_PLAYER = "Player";
	String QUESTION = "Question";
	String OPTIONS = "Options";
	String REPORT = "Generate Report";
	String ACTIONS = "Actions";
	int MAX_PLAYERS = 10;
}