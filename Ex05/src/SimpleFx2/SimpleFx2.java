package SimpleFx2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleFx2 extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	private int i = 0;

	public int setIFromOfra() {
		i++;
		return i;
	}
	
	public int setIFromYardena() {
		i--;
		return i;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root;
		MyController controller;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FxmlSimpleFx2.fxml"));
			root = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		controller.setMainClass(this);
		controller.setTextToLabel("" +i);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Voting Machine");
		primaryStage.show();
	}		

}
