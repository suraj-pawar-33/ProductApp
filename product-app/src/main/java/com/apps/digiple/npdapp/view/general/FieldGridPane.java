package com.apps.digiple.npdapp.view.general;

import com.apps.digiple.npdapp.impl.enhanced.EnhancedCallback;
import com.apps.digiple.npdapp.impl.enhanced.EnhancedStringConverter;
import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.view.CustLabel;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FieldGridPane extends GridPane {

	public FieldGridPane(FieldInfo... fieldInfo) {
		super();
		setId("ProductPanel");
		setStyle("-fx-background-color: #C0C0C0;");
		setVgap(5);
		setHgap(5);
		addAllNodes(fieldInfo);
	}
	
	public String getFieldText(String name) {
		for (Node node : getChildren()) {
			if (node.getId().equals(name)) {
				return ((TextField) node).getText();
			}
		}
		return null;
	}

	private void addAllNodes(FieldInfo[] fieldInfo) {
		for (int i = 0; i < fieldInfo.length; i++) {
			add(CustLabel.createLabel(fieldInfo[i].getLableText()), 0, i);
			add(getTextField(fieldInfo[i].getColumnName()), 1, i);
		}
	}
	
	protected TextField getTextField(String name) {
		TextField textField = new TextField();
		textField.setId(name);
		textField.setPrefSize(300, 30);
		textField.setMinSize(100, 20);
		return textField;
	}
	
	
	protected DatePicker getDatePicker() {
		DatePicker datePiceker = new DatePicker();
		datePiceker.setConverter(new EnhancedStringConverter("dd MMM yyyy"));
		datePiceker.setPrefSize(300, 30);
		datePiceker.setMinSize(100, 20);
		return datePiceker;
	}
	
	protected DatePicker getMonthPicker() {
		DatePicker datePiceker = new DatePicker();
		datePiceker.setConverter(new EnhancedStringConverter("MMM YY"));
		datePiceker.setDayCellFactory(new EnhancedCallback());
		datePiceker.setPrefSize(300, 30);
		datePiceker.setMinSize(100, 20);
		return datePiceker;
	}

	
}
