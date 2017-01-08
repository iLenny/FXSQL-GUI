package fxSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TableListPane extends VBox {
	private Label title;
	private ListView<String> tableList;
	
	public TableListPane() {
		title = new Label("Table List");
		title.setId("tableListTitle");
		this.getChildren().add(title);
	}
	
	
	public void buildList(Connection conn) {
		tableList = new ListView<>();
		try {
			Statement stmt = conn.createStatement();
			String sqlStatement = "SHOW TABLES";
			ResultSet result = stmt.executeQuery(sqlStatement);
			while(result.next()) {
				tableList.getItems().add(result.getString(1));
			}
			this.getChildren().add(tableList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
