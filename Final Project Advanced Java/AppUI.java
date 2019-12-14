import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import java.time.LocalTime;
import java.util.Stack;
import java.util.List;
import java.util.Queue;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.concurrent.TimeUnit;
import javax.swing.event.DocumentEvent.EventType;
import javafx.application.Platform;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class AppUI extends VBox {

	private Stack<Attempt> attempts;
	private Queue<Question> questions;
	private TextInputDialog promptBox;
	private Player player;
	private Label textLbl1;
	private Label textLbl2;
	private Label pointsLbl;
	private Label counterLbl;
	private Label playerLbl;
	private RadioButton optionRdo1;
	private RadioButton optionRdo2;
	private RadioButton optionRdo3;
	private RadioButton optionRdo4;
	private ToggleGroup optionGroup;
	private Alert alert;
	private Button reportBtn;
	private Timeline timeline;

	public AppUI() {
		super(5);
		getChildren().addAll(
				new HBox(5,
						newPanel(AppConst.TITLE_NAME, 100, getPlayerLbl()),
						newPanel(AppConst.TITLE_POINTS, 100, getPointsLbl()),
						newPanel(AppConst.TITLE_TIME, 100, getCounterLbl())
						),
				new HBox(5,
						newPanel(AppConst.ACTIONS, 60, getReportBtn()),
						newPanel(AppConst.QUESTION, 50, new HBox(5, getTextLbl1(), new Label("+"),
								getTextLbl2()))
						),
				newPanel(AppConst.OPTIONS, 200, new VBox(5, getOptionRdo1(), getOptionRdo2(),
						getOptionRdo3(), getOptionRdo4()))
				);
	}

	private Stack<Attempt> getAttempts() {
		if (null == attempts) {
			attempts = new Stack<>();
		}
		return attempts;
	}

	private Queue<Question> getQuestions() throws FileNotFoundException {
		if (null == questions) {
			questions = new FileHandler(AppConst.INPUT_FILE_PATH).getQuestions(); // Retrieve Questions from Input Text
		}
		return questions; // Send Questions
	}

	private TextInputDialog getPromptBox() {
		if (null == promptBox) {
			promptBox = new TextInputDialog(AppConst.DEFAULT_PLAYER);
			promptBox.setTitle(AppConst.PROMPT_NAME);
			promptBox.setHeaderText(null);
		}
		return promptBox;
	}

	public void prompPlayerName() { // Enter Player Name
		getPromptBox().showAndWait();
		String name = getPromptBox().getEditor().getText().trim();
		if (name.isEmpty()) {
			name = getPromptBox().getDefaultValue();
		}
		getPlayer().setName(name);
		getPlayerLbl().setText(name);
	}

	private Player getPlayer() {
		if (null == player) {
			player = new Player();
		}
		return player;
	}

	private void addSeconds(int seconds, int count) { // Countdown Timer
		for (int i = 1; count >= i; i++) {
			getPlayer().setDuration(getPlayer().getDuration()
					.plusSeconds(seconds));
		}
	}

	public void startGame() throws FileNotFoundException {
		getPlayer().setDuration(LocalTime.of(0, 0, 0));
		addSeconds(AppConst.ANSWER_TIMEOUT_SECONDS, getQuestions().size());
		updateStats();
		nextQuestion();
		getTimeline().play();
	}

	private void updateStats() {  
		getPointsLbl().setText(String.valueOf(getPlayer().getScore()));
		getCounterLbl().setText(getPlayer().getDuration().toString());
	}

	private void nextQuestion() throws FileNotFoundException {
		if (getQuestions().isEmpty()) {
			getTimeline().stop();

			Leaderboard.getInstance().insertPlayer(player.getName(), player.getScore());

			StringBuffer scoreInfo = new StringBuffer("Game Over!\n");
			while(!attempts.isEmpty()) {
				Attempt attempt = attempts.pop();
				scoreInfo.append(attempt.getQuestion().toString() + " : " + attempt.getScore() + "\n");
			}
			showInfo(scoreInfo.toString());
			return;
		}
		Question question = getQuestions().poll();
		getTextLbl1().setText(question.getText1());
		getTextLbl2().setText(question.getText2());
		getOptionRdo1().setText(question.getOption1());
		getOptionRdo2().setText(question.getOption2());
		getOptionRdo3().setText(question.getOption3());
		getOptionRdo4().setText(question.getOption4());
		Toggle radio = getOptionGroup().getSelectedToggle();
		if (null != radio) {
			radio.setSelected(false);
		}
		Attempt attempt = new Attempt();
		getAttempts().push(attempt);
		attempt.setStart(System.currentTimeMillis());
		attempt.setQuestion(question);
	}

	private TitledPane newPanel(String title, double width, Node... nodes) {
		TitledPane pnl = new TitledPane(title, new FlowPane(
				Orientation.HORIZONTAL, 5, 5, nodes));
		pnl.setAnimated(false);
		pnl.setExpanded(true);
		pnl.setCollapsible(false);
		pnl.setPrefWidth(width);
		pnl.setPrefHeight(80);
		return pnl;
	}
	
	// Label 
	private Label getLabel(Label lbl) {
		return ((null == lbl) ? (new Label()) : lbl);
	}
	
	private Label getTextLbl1() {
		return textLbl1 = getLabel(textLbl1);
	}

	private Label getTextLbl2() {
		return textLbl2 = getLabel(textLbl2);
	}

	private Label getCounterLbl() {
		return counterLbl = getLabel(counterLbl);
	}

	private Label getPointsLbl() {
		return pointsLbl = getLabel(pointsLbl);
	}

	private Label getPlayerLbl() {
		return playerLbl = getLabel(playerLbl);
	}

	// Radio Button
	private RadioButton getRadio(RadioButton radio, ToggleGroup group) {
		if (null == radio) {
			radio = new RadioButton();
			radio.setToggleGroup(group);
		}
		return radio;
	}

	private RadioButton getOptionRdo1() {
		return optionRdo1 = getRadio(optionRdo1, getOptionGroup());
	}

	private RadioButton getOptionRdo2() {
		return optionRdo2 = getRadio(optionRdo2, getOptionGroup());
	}

	private RadioButton getOptionRdo3() {
		return optionRdo3 = getRadio(optionRdo3, getOptionGroup());
	}

	private RadioButton getOptionRdo4() {
		return optionRdo4 = getRadio(optionRdo4, getOptionGroup());
	}

	// Toggle 
	private ToggleGroup getOptionGroup() {
		if (null == optionGroup) {
			optionGroup = new ToggleGroup();
			optionGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) -> {
				RadioButton radio = (RadioButton) getOptionGroup().getSelectedToggle();
				if (null == radio || gameTimeOut()) {
					return;
				}
				if (0 < getAttempts().size()) {
					Attempt attempt = getAttempts().peek();
					attempt.setEnd(System.currentTimeMillis());
					attempt.setChoice(radio.getText());
					if (attempt.getChoice().equals(attempt.getQuestion().getAnswer())) {
						getPlayer().setScore(getPlayer().getScore() + AppConst.CORRECT_ANSWER_POINTS);
						attempt.setScore(AppConst.CORRECT_ANSWER_POINTS);
					}
				}
				delayNextQuestion();
			});
		}
		return optionGroup;
	}

	private void delayNextQuestion() {
		Platform.runLater(() -> {
			try {
				updateStats();
				nextQuestion();
			} catch (FileNotFoundException e) {
				showError(e.getMessage());
			}
		});
	}

	// Alertbox
	private Alert getAlert() {
		if (null == alert) {
			alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle(AppConst.APP_TITLE);
		}
		return alert;
	}

	public void showInfo(String info) {
		showAlert(info, Alert.AlertType.INFORMATION);
	}

	public void showError(String error) {
		showAlert(error, Alert.AlertType.ERROR);
	}

	public void showAlert(String msg, Alert.AlertType type) {
		getAlert().setAlertType(type);
		getAlert().setContentText(msg);
		getAlert().show();
	}

	// Button
	private Button getReportBtn() {
		if (null == reportBtn) {
			reportBtn = new Button(AppConst.REPORT); // Create Report
			reportBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					List<String> topTenLst = Leaderboard.getInstance().findTopScore();
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(new FileWriter(AppConst.REPORT_FILE_PATH)); // Create Report in Path
						for(String score: topTenLst) {
							writer.write(score + "\n");
						}
					} catch(FileNotFoundException ex) {
						System.out.println(ex.getMessage());
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					} finally {
						try {
							if (writer != null) {
								writer.flush();
								writer.close();
							}
						} catch (Exception ex) {
							System.out.println(ex.getMessage());
						}
					}
				}

			});
		}
		return reportBtn;
	}

	private Timeline getTimeline() {
		if (null == timeline) {
			timeline = new Timeline(new KeyFrame(Duration.seconds(1), (e) -> {
				addSeconds(-1, 1);
				try {
					updateStats();
					if (attemptTimeOut() || gameTimeOut()) {
						if (gameTimeOut()) {
							getQuestions().removeAll(getQuestions());
						}
						nextQuestion();
					}
				} catch (FileNotFoundException ex) {
					showError(ex.getMessage());
				}
			}), new KeyFrame(Duration.seconds(1)));
			timeline.setCycleCount(Timeline.INDEFINITE);
		}
		return timeline;
	}

	private boolean gameTimeOut() {
		return 0 == LocalTime.of(0, 0, 0).compareTo(getPlayer().getDuration());
	}

	private boolean attemptTimeOut() { // Time out!
		long cmp = 0;
		if (0 < getAttempts().size()) {
			cmp = AppConst.ANSWER_TIMEOUT_SECONDS - timeTakenInSecs(getAttempts().peek());
		}
		return 0 > cmp;
	}

	private long timeTakenInSecs(Attempt attempt) {
		return TimeUnit.SECONDS.convert(System.currentTimeMillis() - attempt.getStart(), TimeUnit.MILLISECONDS);
	}
}
