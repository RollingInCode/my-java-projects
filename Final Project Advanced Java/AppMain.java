import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Franklyn Gonzalez, last edited 12/09/2019 

public class AppMain extends Application {
	public void start(Stage stage) {
		AppUI appUI = new AppUI();
		appUI.prompPlayerName();
		stage.setTitle("Jeopardy");
		stage.setScene(new Scene((Parent)appUI));
		stage.sizeToScene();
		stage.setResizable(false);
		stage.show();
		
		try {
			appUI.startGame(); // Start Game
			
		} catch (FileNotFoundException e) {
			appUI.showError(e.getMessage());
			stage.hide();
		} 
	}


	public static void main(String[] args) { 
		launch(args); 
		}
}
