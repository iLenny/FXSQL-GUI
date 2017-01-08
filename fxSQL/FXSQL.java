package fxSQL;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * @author Leibniz H. Berihuete
 *  This is the main class
 */
public class FXSQL extends Application {
	private static final String STYLESHEET = FXSQL.class.getResource("css/style.css").toString();
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 600;
	ObservableList<String> seasonList = FXCollections.<String>observableArrayList();
	ListView<String> season = new ListView<>(seasonList);
	
	private Label supportMessage;
	
	// Panes:
	private LoginPane loginPane;
	
	// Scenes:
	private Scene loginScene;
	private Scene tableListScene;
	private Scene tableViewScene;
	
	@Override public void start(Stage primaryStage) {
		supportMessage = new Label("Supports MySQL");
		supportMessage.relocate(WINDOW_WIDTH*0.325, WINDOW_HEIGHT*0.9);
		supportMessage.setId("supportMessage");
		// Create loginPane
		loginPane = new LoginPane();
		loginPane.setId("loginPane");
		loginPane.relocate(45, 150);
		
		
		
		loginPane.getLoginButton().setOnMouseClicked(e-> {
			
			// Check Authentication
			String user = loginPane.getUser();
			String password = loginPane.getPassword();
			String ip = loginPane.getIP();
			String database = loginPane.getDatabase();
			final String DB_URL = "jdbc:mysql://"+ip+"/"+database + "?useSSL=true";
			
			try {
				Connection conn = DriverManager.getConnection(DB_URL,user, password);
				loginPane.setLoginMessage("Connected!");
				loginPane.getLoginMessage().setTextFill(Color.CHARTREUSE);
			} catch(Exception ex) {
				loginPane.setLoginMessage("Connection Failed!");
				loginPane.getLoginMessage().setTextFill(Color.FIREBRICK);
			}
		});	
		
		Pane root = new Pane(supportMessage,loginPane);
		root.setId("root");
		loginScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		loginScene.getStylesheets().add(STYLESHEET);
		primaryStage.setTitle("FXSQL GUI");
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}
