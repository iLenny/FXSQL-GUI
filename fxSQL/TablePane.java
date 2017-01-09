package fxSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TablePane extends VBox {
	private Label title;
	private TableView tableView;
	
	public TablePane() {
		title = new Label();
		this.getChildren().add(title);
	}
	
	public void buildTable(Connection conn, String selectedTable) {
		title.setText(selectedTable + " Table");
		try {
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT * FROM " + selectedTable;
			ResultSet result = stmt.executeQuery(sqlStatement);
			int numOfColumns = result.getMetaData().getColumnCount();
			tableView = new TableView();
			
			// Add columns:
			for(int i = 0; i < numOfColumns; i++) {
				final int j = i;
				TableColumn tableColumn = new TableColumn(result.getMetaData().getColumnName(i+1));
				tableColumn.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
				tableView.getColumns().add(tableColumn);				
			}
			
			// Add rows:
			
			ObservableList<ObservableList> rowData = FXCollections.observableArrayList();
			while(result.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i = 0; i < numOfColumns; i++) {
					row.add(result.getString(i+1));
					System.out.println(result.getString(i+1) + "added");
				}
				rowData.add(row);
			}
			tableView.setItems(rowData);
			
			this.getChildren().add(tableView);
		
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
