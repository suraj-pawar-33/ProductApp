package com.apps.digiple.npdapp.view;

import com.mysql.cj.util.StringUtils;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class AddressDataEditPanel extends  GridPane{

	protected TextField line1Text;
	protected TextField line2Text;
	protected TextField line3Text;
	protected ChoiceBox<String> cityChoiceBox;
	protected TextField zipText;
	protected ChoiceBox<String> stateChoiceBox;
	protected ChoiceBox<String> conChoiceBox;
	protected TextField otherText;
	protected int objectKey;
	public AddressDataEditPanel() {
		setMinSize(500, 500);
		setPrefSize(1800, 1000);
		
		setVgap(20);
		setHgap(40);

		setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: BEIGE;");
	}
	
	public TextField getLine1Text() {
		return line1Text;
	}

	public TextField getLine2Text() {
		return line2Text;
	}

	public TextField getLine3Text() {
		return line3Text;
	}

	public void setCityChoiceBox(String choice) {
		if (!StringUtils.isNullOrEmpty(choice)) {
			cityChoiceBox.getSelectionModel().select(choice);
		}
	}
	
	public String getSelectedCity() {
		return cityChoiceBox.getSelectionModel().getSelectedItem();
	}
	
	public boolean isCitySelected() {
		return !cityChoiceBox.getSelectionModel().isEmpty();
	}

	public TextField getZipText() {
		return zipText;
	}

	public void setStateChoiceBox(String choice) {
		if (!StringUtils.isNullOrEmpty(choice)) {
			stateChoiceBox.getSelectionModel().select(choice);
		}
	}
	
	public String getSelectedState() {
		return stateChoiceBox.getSelectionModel().getSelectedItem();
	}
	
	public boolean isStateSelected() {
		return !stateChoiceBox.getSelectionModel().isEmpty();
	}

	public void setConChoiceBox(String choice) {
		if (!StringUtils.isNullOrEmpty(choice)) {
			conChoiceBox.getSelectionModel().select(choice);
		}
	}
	
	public String getSelectedCountry() {
		return conChoiceBox.getSelectionModel().getSelectedItem();
	}

	public TextField getOtherText() {
		return otherText;
	}

	AddressDataEditPanel getGridPane() {
		return this;
	}

	protected ChoiceBox<String> getChoiceBox() {
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		choiceBox.setPrefSize(300, 30);
		choiceBox.setMinSize(50, 20);
		return choiceBox;
	}

	protected TextField getTextField() {
		TextField textField = new TextField();
		textField.setPrefSize(300, 30);
		textField.setMinSize(50, 20);
		return textField;
	}

	public ChoiceBox<String> getCityChoiceBox() {
		return cityChoiceBox;
	}
	
	public ChoiceBox<String> getStateChoiceBox() {
		return stateChoiceBox;
	}
	
	public ChoiceBox<String> getConChoiceBox() {
		return conChoiceBox;
	}

	public void clearAllText() {
		line1Text.clear();
		line2Text.clear();
		line3Text.clear();
		cityChoiceBox.getSelectionModel().clearSelection();
		zipText.clear();
		stateChoiceBox.getSelectionModel().clearSelection();
		conChoiceBox.getSelectionModel().clearSelection();
		otherText.clear();
	}

	public int getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(int objectKey) {
		this.objectKey = objectKey;
	}


}
