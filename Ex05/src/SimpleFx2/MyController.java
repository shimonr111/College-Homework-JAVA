package SimpleFx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MyController {

	@FXML
	private VBox vBox;

	@FXML
	private HBox hBox;

	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Label label;

	private SimpleFx2 simpleFx2Class;
	
	public void setMainClass(SimpleFx2 simpleFx2Class) {
		this.simpleFx2Class = simpleFx2Class;
	}
	
	@FXML
	void press1(ActionEvent event) {
		label.setText(""+simpleFx2Class.setIFromOfra());
	}

	@FXML
	void press2(ActionEvent event) {
		label.setText(""+simpleFx2Class.setIFromYardena());
	}
	
	public void setTextToLabel(String msg) {
		label.setText(msg);
	}

}
