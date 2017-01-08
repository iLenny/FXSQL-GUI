package fxSQL;

import java.sql.Connection;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class TableListPane extends Pane {
	private Label title;
	private ListView<String> tableList;
	
	public TableListPane(Connection conn) {
		tableList = new ListView<>();
	}
}
