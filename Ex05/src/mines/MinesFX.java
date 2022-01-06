package mines;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MinesFX extends Application implements EventHandler<MouseEvent> {
	private Mines gameBoard;
	public GridPane gridForButtons;
	private boolean isGameInProgres = true;
	private MyController controller;
	private TextField widthText = new TextField("10");
	private TextField heightText = new TextField("10");
	private TextField minesText = new TextField("10");
	Button rButton;
	private int width = 10, height = 10, mines = 10;
	VBox vBox;
	HBox hBox;
	StackPane root;
	Scene scene;
	Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			this.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FxmlMines.fxml"));
			root = loader.load();
			root.setPadding(new Insets(5));
			controller = loader.getController();
			gameBoard = new Mines(height, width, mines);
			vBox = new VBox();
			hBox = new HBox();
			rButton = new Button("Reset");
			Label widthLbl = new Label("width  ");
			Label heightLbl = new Label("height ");
			Label minesLbl = new Label("mines  ");
			HBox hBoxForLblAndText[] = new HBox[3];
			for (int i = 0; i < 3; i++) {
				hBoxForLblAndText[i] = new HBox();
			}
			hBoxForLblAndText[0].getChildren().addAll(widthLbl, widthText);
			hBoxForLblAndText[1].getChildren().addAll(heightLbl, heightText);
			hBoxForLblAndText[2].getChildren().addAll(minesLbl, minesText);
			for(int i=0;i<3;i++) {
				hBoxForLblAndText[i].setPadding(new Insets(10,0,0,0));
			}
			widthText.setMaxWidth(60);
			heightText.setMaxWidth(60);
			minesText.setMaxWidth(60);
			rButton.setOnMouseClicked(this);
			rButton.setMaxWidth(100);
			vBox.getChildren().addAll(rButton, hBoxForLblAndText[0], hBoxForLblAndText[1], hBoxForLblAndText[2]);
			vBox.setAlignment(Pos.CENTER);
			vBox.setPadding(new Insets(0,10,0,0));
			gridForButtons = createGrid();
			hBox.getChildren().addAll(vBox, gridForButtons);
			rButton.setOnMouseClicked(this);
			root.getChildren().add(hBox);
			scene = new Scene(root);
			primaryStage.setTitle("The Amazing Mines Sweeper");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}

	private GridPane createGrid() {
		GridPane tempGrid = new GridPane();// create grid
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ButtonsWithXY specialButton = new ButtonsWithXY(i, j, gameBoard.get(i, j));// create our button
				specialButton.setOnMouseClicked(this);// set for action
				specialButton.setPrefSize(40, 40);
				specialButton.setAlignment(Pos.CENTER);
				tempGrid.add(specialButton, i, j);
			}
		}
		return tempGrid;
	}

	@Override
	public void handle(MouseEvent event) {
		if ((event.getSource() instanceof ButtonsWithXY) && isGameInProgres) {// the game is going and the button that
																				// was
																				// clicked is from the grid
			ButtonsWithXY bIJ = (ButtonsWithXY) event.getSource();// do logic on the current button
			if (event.getButton() == MouseButton.PRIMARY) {// if thats the primary button on the mouse that clicked on
															// the grid
				if (gameBoard.open(bIJ.getValueX(), bIJ.getValueY())==false) {// open that current button and his
																		// neighbors using the logic of Mines
																		// class
					gameBoard.setShowAll(true);
					isGameInProgres = false;
					sendMessage("You have lost in the best game ever!");
				}
			} else if (gameBoard.isDone()) { // check if the game is finished

				gameBoard.setShowAll(true);// if the if above is true than we have finished set the game to show all
											// cells in the grid
				isGameInProgres = false;// game is finished!
				sendMessage("You have WON!!");
			} else if (event.getButton() == MouseButton.SECONDARY) {
				gameBoard.toggleFlag(bIJ.getValueX(), bIJ.getValueY());
			}
			// update scene to the new scene after opening part of the grid
			updateScene();
		} 
		else if (event.getSource() instanceof Button)// if thats the reset button
		{
			Button rButton = (Button) event.getSource();
			if (rButton.equals(this.rButton)) {// reset the game
				resetGame();
			}
		}

	}

	private void resetGame() {
		width = Integer.parseInt(widthText.getText());
		height = Integer.parseInt(heightText.getText());
		mines = Integer.parseInt(minesText.getText());
		gameBoard = new Mines(height,width, mines);
		createNewGameBoard();
		isGameInProgres = true;
	}

	private void createNewGameBoard() {
		gridForButtons = createGrid();
		hBox = new HBox();
		hBox.getChildren().add(vBox);
		hBox.getChildren().add(gridForButtons);
		root = new StackPane();
		root.setPadding(new Insets(5));
		root.getChildren().add(hBox);
		scene = new Scene(root);
		primaryStage.setScene(scene);
	}

	private void updateScene() {
		gridForButtons = createGrid();
		hBox = new HBox();
		hBox.getChildren().add(vBox);
		hBox.getChildren().add(gridForButtons);
		root = new StackPane();
		root.setPadding(new Insets(5));
		root.getChildren().add(hBox);
		scene = new Scene(root);
		primaryStage.setScene(scene);

	}

	
	private void sendMessage(String s)
	  {
		  Alert alertToUser = new Alert(AlertType.INFORMATION);
		  alertToUser.setTitle(" Hope For 100 :) ");
		  alertToUser.setHeaderText(null);
		  alertToUser.setContentText(s);
		  alertToUser.showAndWait();
	  }
		
}
