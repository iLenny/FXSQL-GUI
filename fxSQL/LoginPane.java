package fxSQL;

import fxSQL.tools.FadeObject;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * 
 * @author Leibniz H. Berihuete
 * This is the Login sections: where the user must enter database authentication
 *
 */
public class LoginPane extends Pane {
	// Default text values
	private static final String TITLE_TEXT = "FXSQL GUI";
	private static final String USER_TEXT =     "Username:  ";
	private static final String PASSWORD_TEXT = "Password:   ";
	private static final String IP_TEXT =       "IP Address: ";
	private static final String DATABASE_TEXT = "Database:   ";
	private static final String LOGIN_BUTTON_TEXT = "Login";
	
	// Labels:
	private Label title;
	private Label userLabel;
	private Label passwordLabel;
	private Label ipLabel;
	private Label databaseLabel;
	private Label loginMessage;
	
	// TextFields
	private TextField userField;
	private TextField passwordField;
	private TextField ipField;
	private TextField databaseField;
	
	// Horizontal Containers
	private HBox userBox;
	private HBox passwordBox;
	private HBox ipBox;
	private HBox databaseBox;
	
	// Buttons
	private Button loginButton;
	
	/* *** CONSTRUCTOR *** */
	public LoginPane() {
		// Initialize:
		createTitle();
		createUserBox();
		createPasswordBox();
		createIPBox();
		createDatabaseBox();
		createLoginButton();		
		loginMessage = new Label();
		VBox dataVBox = new VBox(userBox, passwordBox, ipBox, databaseBox);
		VBox loginVBox = new VBox(title, dataVBox, new HBox(loginButton, loginMessage));
		
		
		// Set IDs:
		dataVBox.setId("dataVBox");
		loginVBox.setId("loginVBox");		
		loginMessage.setId("loginMessage");
		title.setId("title");
		userLabel.setId("userLabel");
		userField.setId("userField");
		userBox.setId("userBox");
		ipLabel.setId("ipLabel");
		ipField.setId("ipField");
		ipBox.setId("ipBox");
		passwordLabel.setId("passwordLabel");
		passwordField.setId("passwordField");
		passwordBox.setId("passwordBox");
		databaseLabel.setId("databaseLabel");
		databaseField.setId("databaseField");
		databaseBox.setId("databaseBox");
		loginButton.setId("loginButton");
		
		// Add to this container
		getChildren().add(loginVBox);
		
		// Set Initial Opacity:
		title.setOpacity(0);
		dataVBox.setOpacity(0);
		loginButton.setOpacity(0);
		
		// Create Fade animation:
		FadeObject.fadeIn(title, Duration.millis(800), ()-> {        // Fade in title first
			FadeObject.fadeIn(dataVBox, Duration.millis(800), ()->{  // Fade in dataVBox second 
				FadeObject.fadeIn(loginButton, Duration.millis(800));// Fade in loginButton third
			});
		});
		
		
	}
	
	
	private void createTitle() {
		title = new Label(TITLE_TEXT);
		title.relocate(75,100);
	}
	
	private void createUserBox() {
		userLabel = new Label(USER_TEXT);
		userField = new TextField();
		userBox = new HBox(userLabel, userField);
		
	}
	
	private void createPasswordBox() {
		passwordLabel = new Label(PASSWORD_TEXT);
		passwordField = new PasswordField();
		passwordBox = new HBox(passwordLabel, passwordField);
	}
	
	private void createIPBox() {
		ipLabel = new Label(IP_TEXT);
		ipField = new TextField();
		ipBox = new HBox(ipLabel, ipField);		
	}
	
	private void createDatabaseBox() {
		databaseLabel = new Label(DATABASE_TEXT);
		databaseField = new TextField();
		databaseBox = new HBox(databaseLabel, databaseField);
	}
	
	private void createLoginButton() {
		loginButton = new Button(LOGIN_BUTTON_TEXT);
		loginButton.relocate(100, 150);
		loginButton.setCursor(Cursor.HAND);
	}
	
	/* ********************
	 *      SETTERS
	 * ********************/
	public void setLoginMessage(String message) {
		this.loginMessage.setText(message);
	}
	
	/* **********************
	 * 		GETTERS
	 * **********************/
	public String getUser() {
		return userField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	public String getIP() {
		return ipField.getText();
	}
	
	public String getDatabase() {
		return databaseField.getText();
	}
	
	public Button getLoginButton() {
		return loginButton;
	}
	
	public Label getLoginMessage() {
		return loginMessage;
	}
	
	
}
