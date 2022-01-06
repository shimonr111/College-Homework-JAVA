package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Competition extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createRoot());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Voting Machine");
		primaryStage.show();
	}

	private int i = 0;

	private VBox createRoot() {
		Label label = new Label();
		label.setText("" + i);// put 0 inside this label
		label.setAlignment(Pos.CENTER);
		label.setStyle("-fx-background-color: red;");
		label.setMaxWidth(Double.MAX_VALUE);
		label.setPadding(new Insets(10));

		Button button1 = new Button("Ofra Haza");// location -> 0 0
		Button button2 = new Button("Yardena Arazi");// location -> 1,0

		class LabelIncreaser implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent event) {
				Button b = (Button) event.getSource();// check which button was pressed
				if (b == button1) {
					i++;
				} else {
					i--;
				}
				label.setText("" + i);
			}
		}
		button1.setOnAction(new LabelIncreaser());
		button2.setOnAction(new LabelIncreaser());

		HBox hBox = new HBox(20);
		VBox vBox = new VBox(20);

		hBox.getChildren().addAll(button1, button2);
		vBox.getChildren().addAll(hBox, label);
		vBox.setPadding(new Insets(10));

		return vBox;
	}

}
